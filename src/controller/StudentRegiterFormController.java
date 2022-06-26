package controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class StudentRegiterFormController {
    public AnchorPane reservationFormContext;
    public JFXComboBox cmbStRegNumber;
    public JFXComboBox cmbRoomId;
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

    public void registerOnAction(ActionEvent actionEvent) {
    }
}
