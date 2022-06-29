package controller;

import bo.BOFactory;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.net.URL;

public class LoginFormController {
    public AnchorPane loginContext;
    public JFXTextField txtUserName;
    public JFXPasswordField txtPassword;



    public void loginOnAction(ActionEvent actionEvent) throws Exception {
        if(txtUserName.getText().equals("admin")& txtPassword.getText().equals("1234")){
            Stage stage=(Stage) loginContext.getScene().getWindow();
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/DashBoardform.fxml"))));
            stage.centerOnScreen();
        }else{
            new Alert(Alert.AlertType.WARNING,"User Name or Password Invalid").show();
        }
    }
}
