package controller;

import bo.BOFactory;
import bo.Custom.ReservationBO;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import entity.CustomEntity;
import entity.Room;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import view.tm.RoomReservationTM;

import java.io.IOException;
import java.util.List;

public class ReservationDetailsFormController {
    public AnchorPane reservationDetailContext;
    public JFXComboBox <String>cmbRoomId;
    public JFXTextField txtRoomType;
    public JFXTextField txtKeyMoney;
    public JFXTextField txtQty;
    public TableView tblReservationDetails;
    public TableColumn colStId;
    public TableColumn colStName;
    public TableColumn colRdate;

    private final ReservationBO reservationDetailsBO = (ReservationBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.RESERVATION_DETAILS);

    public void initialize(){
        loadRoomIds();

        cmbRoomId.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue!=null){

                setRoomData(newValue);
                try {

                    setProgrammeDetailsToTable (newValue);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        colStId.setCellValueFactory(new PropertyValueFactory<>("student_id"));
        colStName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colRdate.setCellValueFactory(new PropertyValueFactory<>("reservationDate"));
    }

    private void setProgrammeDetailsToTable(String roomId) throws Exception {
        ObservableList<CustomEntity> tmList= FXCollections.observableArrayList();
        List<CustomEntity> customEntities = reservationDetailsBO.loadAllStudentDetails(roomId);
        for (CustomEntity customEntity : customEntities) {
            RoomReservationTM reservationDetailsTM = new RoomReservationTM(
                    customEntity.getStudent_id(),
                    customEntity.getName(),
                    customEntity.getReservationDate()
            );
            tmList.add(customEntity);
            tblReservationDetails.setItems(tmList);
        }
        System.out.println(tmList);
    }

    private void setRoomData(String rid) {
        try {
            Room roomData = reservationDetailsBO.getRoomData(rid);
            txtRoomType.setText(roomData.getType());
            txtKeyMoney.setText(String.valueOf(roomData.getKey_money()));
            txtQty.setText(String.valueOf(roomData.getQty()));


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadRoomIds() {
        try {
            cmbRoomId.getItems().addAll(reservationDetailsBO.getRoomIds());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
