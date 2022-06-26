package controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;

public class ReservationDetailsFormController {
    public AnchorPane reservationDetailContext;
    public JFXComboBox cmbRoomId;
    public JFXTextField txtRoomType;
    public JFXTextField txtKeyMoney;
    public JFXTextField txtQty;
    public TableView tblReservationDetails;
    public TableColumn colStId;
    public TableColumn colStName;
    public TableColumn colRdate;
    public TableColumn colKMoney;
}
