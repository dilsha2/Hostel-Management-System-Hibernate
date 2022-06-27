package controller;

import bo.BOFactory;
import bo.Custom.UserBO;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import entity.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import javax.security.auth.login.LoginContext;
import java.io.IOException;
import java.net.URL;

public class LoginFormController {
    public AnchorPane loginContext;
    public JFXTextField txtUserName;
    public JFXPasswordField txtPassword;
    int attemptsLogin = 0;

    private final UserBO loginBO = (UserBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.LOGIN_USER);

    public void loginOnAction(ActionEvent actionEvent) throws Exception {
        attemptsLogin++;
        if (attemptsLogin < 5) {  // attempts calculate


            User userData = loginBO.getUserData("UID-001");


            if (txtUserName.getText().equals(userData.getUserName()) & txtPassword.getText().equals(userData.getPassword())) {


                Stage stage = (Stage) loginContext.getScene().getWindow();
                stage.close();
                URL resource = getClass().getResource("view/DashBoardform.fxml");

                Parent load = FXMLLoader.load(resource);
                Scene scene = new Scene(load);
                Stage stage1 = new Stage();
                stage1.setScene(scene);
                stage1.centerOnScreen();
                stage1.show();
            } else {
                //  number of wrong input visible false option
                txtUserName.setVisible(false);
                txtPassword.setVisible(false);
            }
        }
    }
}
