package controller;

import bo.BOFactory;
import bo.Custom.RegisterStudentBO;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import dto.RoomReservationDTO;
import entity.Room;
import entity.Student;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;

import java.io.IOException;
import java.sql.SQLException;

public class StudentRegiterFormController {
    public AnchorPane reservationFormContext;
    public JFXComboBox <String> cmbStRegNumber;
    public JFXComboBox <String> cmbRoomId;
    public JFXDatePicker date;
    public JFXTextField txtStName;
    public JFXTextField txtStAddress;
    public JFXTextField txtStContact;
    public JFXTextField txtDateOfBirth;
    public JFXTextField txtGender;
    public JFXTextField txtRoomType;
    public JFXTextField txtKeyMoney;
    public JFXTextField txtQty;
    public Label lblAvailable;
    public JFXButton btnRegister;
    int roomCount;

    private final RegisterStudentBO registerStudentBO = (RegisterStudentBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.REGISTER_STUDENT);
    

    public void initialize() {
        try {
            loadStudentIds();
            loadRoomIds();
            registerStudentBO.generateNewReservationID();
            btnRegister.setDisable(false);
        } catch (Exception e) {
            e.printStackTrace();
        }

        cmbStRegNumber.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue!=null){
                try {
                    setStudentDataToFields(newValue);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        });

        cmbRoomId.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {

            if(newValue!=null){
                try {
                    setRoomDataToFields(newValue);
                    availableRoomCheck(newValue);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        });

    }

    private void availableRoomCheck(String rid) {
        try {
            String RoomTypeCount = registerStudentBO.generateRoomAvailableStatus(rid);
            int count = Integer.parseInt(RoomTypeCount);

            if (count >= roomCount) {
                lblAvailable.setTextFill(Paint.valueOf("RED"));
                lblAvailable.setText("NOT AVAILABLE");
                new Alert(Alert.AlertType.WARNING, "That " + rid + " Room is Out Of Quantity..!!!").showAndWait();
                btnRegister.setDisable(true);

            } else {
                lblAvailable.setTextFill(Paint.valueOf("GREEN"));
                lblAvailable.setText("AVAILABLE");
                btnRegister.setDisable(false);
            }

        } catch (SQLException | ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
    }

    private void setRoomDataToFields(String id) throws Exception {
        Room room = registerStudentBO.getRoom(id);
        txtRoomType.setText(room.getType());
        txtKeyMoney.setText(String.valueOf(room.getKey_money()));
        txtQty.setText(String.valueOf(room.getQty()));


        roomCount = Integer.parseInt(txtQty.getText());
    }

    private void setStudentDataToFields(String id) throws Exception {
        Student student = registerStudentBO.getStudent(id);
        txtStName.setText(student.getName());
        txtStAddress.setText(student.getAddress());
        txtStContact.setText(student.getContact_no());
        txtDateOfBirth.setText(student.getDate());
        txtGender.setText(student.getGender());
    }

    private void loadRoomIds() throws IOException {
        cmbRoomId.getItems().addAll(registerStudentBO.getRoomIds());
    }

    private void loadStudentIds() throws IOException {
        cmbStRegNumber.getItems().addAll(registerStudentBO.getStudentIds());
    }

    public void registerOnAction(ActionEvent actionEvent) {
        if (lblAvailable.equals("NOT AVAILABLE") | cmbStRegNumber.getSelectionModel().isEmpty() || cmbRoomId.getSelectionModel().isEmpty() || date.getValue() == null) {
            new Alert(Alert.AlertType.ERROR, "Something Went Wrong. Pleases try again !").showAndWait();
        } else {
            if (registerStudentBO.addReservation(new RoomReservationDTO(
                    txtRejFormNum.getText(),
                    date.getValue(),
                    cmbStRegNumber.getValue(),
                    cmbRoomId.getValue(),
                    lblAvailable.getText()
            ))) {

                new Alert(Alert.AlertType.INFORMATION, " Saved..Registration Successfully?").showAndWait();
            } else {
                new Alert(Alert.AlertType.ERROR, "Something Went Wrong. Pleases try again !").showAndWait();
            }
            clear();
            generateNewReservationID();
        }
    }

    private void generateNewReservationID() {
    }

    private void clear(){
        txtStName.clear();
        txtStAddress.clear();
        txtStContact.clear();
        txtDateOfBirth.clear();
        txtGender.clear();
        txtRoomType.clear();
        txtKeyMoney.clear();
        txtQty.clear();
        cmbStRegNumber.setValue(null);
        cmbRoomId.setValue(null);
        lblAvailable.setText("");
    }
}
