package controller;

import bo.BOFactory;
import bo.Custom.RegisterStudentBO;
import bo.Custom.ReservationBO;
import entity.Room;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

public class DashBoardformController {
    public AnchorPane dashboardContext;
    public Label lblDate;
    public Label lblTime;
    public Label lblDay;
    public Label lblQty;
    public Label lblAvailable;
    public ComboBox<String> cmbRoomId;

    private final ReservationBO reservationDetailsBO = (ReservationBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.RESERVATION_DETAILS);
    private final RegisterStudentBO registerStudentBO = (RegisterStudentBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.REGISTER_STUDENT);

    public void initialize() throws IOException {
        loadTimeDate();
        loadRoomIds();

        cmbRoomId.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue!=null){

                setRoomData(newValue);
                try {

                    availableRoomCheck(newValue);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void availableRoomCheck(String rid) throws SQLException, IOException, ClassNotFoundException{
        String RoomTypeCount = registerStudentBO.generateRoomAvailableStatus(rid);
        int count = Integer.parseInt(RoomTypeCount);

        int roomQty= Integer.parseInt(lblQty.getText());

        int availableRM= roomQty-count;

        if(count >= roomQty){
            lblAvailable.setTextFill(Paint.valueOf("RED"));
            lblAvailable.setText("   OUT OF ROOM");
        }else{
            lblAvailable.setTextFill(Paint.valueOf("WHITE"));
            lblAvailable.setText(availableRM+"  ROOMS");
        }
    }

    private void setRoomData(String rid) {
        try {
            Room roomData = reservationDetailsBO.getRoomData(rid);

            lblQty.setText(String.valueOf(roomData.getQty()));


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadRoomIds() throws IOException {
        cmbRoomId.getItems().addAll(reservationDetailsBO.getRoomIds());
    }

    private void loadTimeDate() {
            lblDate.setText(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));

            Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, event -> {

                LocalTime currentTime = LocalTime.now();

                lblTime.setText(currentTime.getHour() + ":" + currentTime.getMinute() + ":" + currentTime.getSecond());

                lblDay.setText(LocalDate.now().getDayOfWeek().toString());
            }),
                    new KeyFrame(Duration.seconds(1))

            );
            clock.setCycleCount(Animation.INDEFINITE);
            clock.play();
    }

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

    public void logOutOnAction(ActionEvent actionEvent) throws IOException {
        dashboardContext.getChildren().clear();
        Parent parent = FXMLLoader.load(getClass().getResource("../view/LoginForm.fxml"));
        dashboardContext.getChildren().add(parent);
    }
}
