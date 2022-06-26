package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class DashBoardformController {
    public AnchorPane dashboardContext;

    public void dashboardOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage=(Stage) dashboardContext.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/DashBoardForm.fxml"))));
    }

    public void manageStudentsOnAction(ActionEvent actionEvent) throws IOException {
        dashboardContext.getChildren().clear();
        Parent parent = FXMLLoader.load(getClass().getResource("../view/ManageStudentForm.fxml"));
        dashboardContext.getChildren().add(parent);
    }

    public void manageRoomsOnAction(ActionEvent actionEvent) throws IOException {
        dashboardContext.getChildren().clear();
        Parent parent = FXMLLoader.load(getClass().getResource("../view/ManageRoomForm.fxml"));
        dashboardContext.getChildren().add(parent);
    }

    public void registerStudentsOnAction(ActionEvent actionEvent) throws IOException {
        dashboardContext.getChildren().clear();
        Parent parent = FXMLLoader.load(getClass().getResource("../view/StudentRegiterForm.fxml"));
        dashboardContext.getChildren().add(parent);
    }

    public void reserveDetailsOnAction(ActionEvent actionEvent) throws IOException {
        dashboardContext.getChildren().clear();
        Parent parent = FXMLLoader.load(getClass().getResource("../view/ReservationDetailsForm.fxml"));
        dashboardContext.getChildren().add(parent);
    }

    public void logOutOnAction(ActionEvent actionEvent) {
    }
}
