package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;

public class ManageStudentFormController {
    public AnchorPane StudentContext;
    public JFXTextField txtStId;
    public JFXTextField txtStName;
    public JFXTextField txtStAddress;
    public JFXTextField txtStContact;
    public JFXComboBox cmbGender;
    public JFXTextField txtSearchId;
    public JFXDatePicker Date;
    public TableView tblStudent;
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

    public void text_feild_key_released(KeyEvent keyEvent) {
    }

    public void addOnAction(ActionEvent actionEvent) {
    }

    public void updateOnAction(ActionEvent actionEvent) {
    }

    public void deleteOnAction(ActionEvent actionEvent) {
    }

    public void clearOnAction(ActionEvent actionEvent) {
    }
}
