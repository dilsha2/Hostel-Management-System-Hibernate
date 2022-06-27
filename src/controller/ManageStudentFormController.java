package controller;

import bo.BOFactory;
import bo.Custom.ManageStudentBO;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import dto.StudentDTO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import util.ValidationUtil;
import view.tm.StudentTM;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.regex.Pattern;

public class ManageStudentFormController {
    public AnchorPane StudentContext;
    public JFXTextField txtStId;
    public JFXTextField txtStName;
    public JFXTextField txtStAddress;
    public JFXTextField txtStContact;
    public JFXComboBox cmbGender;
    public JFXTextField txtSearchId;
    public JFXDatePicker Date;
    public TableView <StudentDTO> tblStudent;
    public TableColumn colStId;
    public TableColumn colStName;
    public TableColumn colStAddress;
    public TableColumn colStContact;
    public TableColumn colGender;
    public TableColumn colDateOfBirth;
    public JFXButton btnAdd;
    public JFXButton btnUpdate;
    public JFXButton btnDelete;
    public JFXButton btnClear;

    private final ManageStudentBO manageStudentBO = (ManageStudentBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.MANAGE_STUDENT);

    LinkedHashMap<JFXTextField, Pattern> map = new LinkedHashMap<>();

    public void initialize() throws Exception {
        colStId.setCellValueFactory(new PropertyValueFactory<>("student_id"));
        colStName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colStAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colStContact.setCellValueFactory(new PropertyValueFactory<>("contact_no"));
        colDateOfBirth.setCellValueFactory(new PropertyValueFactory<>("date"));
        colGender.setCellValueFactory(new PropertyValueFactory<>("gender"));


        btnAdd.setDisable(true);
        btnUpdate.setDisable(true);
        btnDelete.setDisable(true);

        setStudentData();
        comboLoad();
        tblStudent.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue!=null){
                setDataFields(newValue);
            }
        });

        Pattern idPattern = Pattern.compile("^(SI-)[0-9]{3,5}$");
        Pattern namePattern = Pattern.compile("^[A-z]{3,20}$");
        Pattern AddressPattern = Pattern.compile("^[A-z0-9 ,/]{4,20}$");
        Pattern TPNumberPattern = Pattern.compile("^(?:7|0|(?:\\+94))[0-9]{9}$");

        //add pattern and text to the map
        map.put(txtStId,idPattern);
        map.put(txtStName,namePattern);
        map.put(txtStAddress,AddressPattern);
        map.put(txtStContact,TPNumberPattern);
    }

    private void setDataFields(StudentDTO s) {
        txtStId.setText(s.getStudent_id());
        txtStName.setText(s.getName());
        txtStAddress.setText(s.getAddress());
        txtStContact.setText(s.getContact_no());
        Date.setValue(LocalDate.parse(s.getDate()));
        cmbGender.setValue(s.getGender());
        btnUpdate.setDisable(false);
        btnDelete.setDisable(false);
        btnAdd.setDisable(true);
    }

    private void comboLoad() {
        cmbGender.getItems().add("MALE");
        cmbGender.getItems().add("FEMALE");
    }

    private void setStudentData() throws Exception {
        ObservableList<StudentDTO> tmList= FXCollections.observableArrayList();
        List<StudentDTO> studentDTOS = manageStudentBO.loadAllStudent();

        for (StudentDTO dto : studentDTOS) {


            tmList.add(new StudentTM(
                    dto.getStudent_id(),
                    dto.getName(),
                    dto.getAddress(),
                    dto.getContact_no(),
                    dto.getDate(),
                    dto.getGender()));
        }
        tblStudent.setItems(tmList);
        FilteredList<StudentDTO> filterData = new FilteredList(tmList, b -> true);

        txtSearchId.textProperty().addListener((observable, oldValue, newValue) -> {
            filterData.setPredicate(StudentDTO -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                if (StudentDTO.getStudent_id().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                }
                else
                    return false;
            });
        });

        SortedList<StudentDTO> sortedData = new SortedList<>(filterData);
        sortedData.comparatorProperty().bind(tblStudent.comparatorProperty());
        tblStudent.setItems(sortedData);
    }

    public void text_feild_key_released(KeyEvent keyEvent) {
        ValidationUtil.validate(map,btnAdd);

        if (keyEvent.getCode()== KeyCode.ENTER){
            Object response = ValidationUtil.validate(map,btnAdd);
            if (response instanceof JFXTextField){
                JFXTextField textField = (JFXTextField) response;
                textField.requestFocus();
            }else if (response instanceof Boolean){

            }
        }
    }

    public void addOnAction(ActionEvent actionEvent) throws Exception {
        try {

            if (manageStudentBO.add(new StudentDTO(txtStId.getText(),
                    txtStName.getText(),
                    txtStAddress.getText(),
                    txtStContact.getText(),
                    Date.getValue().toString(),
                    cmbGender.getValue().toString()))) {
                new Alert(Alert.AlertType.CONFIRMATION, " Student Saved... Successfully").showAndWait();
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Something Went Wrong. try again carefully!").showAndWait();
        }
        clear();
        setStudentData();
    }

    private void clear() {
        txtStId.clear();
        txtStName.clear();
        txtStAddress.clear();
        txtStContact.clear();
        Date.setValue(null);
        cmbGender.getSelectionModel().clearSelection();
        txtSearchId.clear();
        ValidationUtil.validate(map,btnAdd);
        btnUpdate.setDisable(true);
        btnDelete.setDisable(true);
        tblStudent.refresh();
    }

    public void updateOnAction(ActionEvent actionEvent) throws Exception {
        try {
            if (manageStudentBO.updateStudent(new StudentDTO(
                    txtStId.getText(),
                    txtStName.getText(),
                    txtStAddress.getText(),
                    txtStContact.getText(),
                    Date.getValue().toString(),
                    cmbGender.getValue().toString()
            ))) {
                new Alert(Alert.AlertType.CONFIRMATION, "Do you want to Update it?").showAndWait();
            }

        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Something Went Wrong. try again carefully!").showAndWait();
        }
        clear();
        setStudentData();
    }

    public void deleteOnAction(ActionEvent actionEvent) throws Exception {
        try {
            if (manageStudentBO.deleteStudent(txtStId.getText())) {
                new Alert(Alert.AlertType.CONFIRMATION, "Do you want to Delete this record?").showAndWait();
            }

        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Something Went Wrong. Please Try again!").showAndWait();
        }
        clear();
        setStudentData();
    }

    public void clearOnAction(ActionEvent actionEvent) {
        clear();
    }
}
