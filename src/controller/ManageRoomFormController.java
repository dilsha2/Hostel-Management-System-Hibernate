package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;

public class ManageRoomFormController {
    public AnchorPane roomsContext;
    public JFXTextField txtRoomId;
    public JFXTextField txtKeyMoney;
    public JFXTextField txtQty;
    public JFXComboBox cmbRoomType;
    public JFXTextField txtSearchRoomId;
    public JFXButton btnAdd;
    public JFXButton btnUpdate;
    public JFXButton btnDelete;
    public JFXButton btnClear;
    public TableView tblRooms;
    public TableColumn colRoomId;
    public TableColumn colRoomType;
    public TableColumn colQty;
    public TableColumn colKeyMoney;

    public void addOnAction(ActionEvent actionEvent) {
    }

    public void updateOnAction(ActionEvent actionEvent) {
    }

    public void deleteOnAction(ActionEvent actionEvent) {
    }

    public void clearOnAction(ActionEvent actionEvent) {
    }

    public void text_feild_key_released(KeyEvent keyEvent) {
    }
}
