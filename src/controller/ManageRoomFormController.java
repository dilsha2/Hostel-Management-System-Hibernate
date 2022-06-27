package controller;

import bo.BOFactory;
import bo.Custom.ManageRoomBO;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import dto.RoomDTO;
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
import view.tm.RoomTM;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.regex.Pattern;

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
    public TableView<RoomDTO> tblRooms;
    public TableColumn colRoomId;
    public TableColumn colRoomType;
    public TableColumn colQty;
    public TableColumn colKeyMoney;

    private final ManageRoomBO manageRoomBO = (ManageRoomBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.MANAGE_ROOM);


    LinkedHashMap<JFXTextField, Pattern> map = new LinkedHashMap<>();

    public void initialize() throws Exception {
        btnAdd.setDisable(true);
        btnUpdate.setDisable(true);
        btnDelete.setDisable(true);

        comboLoad();
        setRoomDataLoad();
        tblRooms.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                setDataFields(newValue);
            }
        });

        Pattern idPattern = Pattern.compile("^(RM-)[0-9]{3,5}$");
        Pattern keyMoneyPattern = Pattern.compile("^[1-9][0-9]*(.[0-9]{2})?$");
        Pattern qtyPattern = Pattern.compile("^[0-9]{1,}$");

        //add pattern and text to the map
        map.put(txtRoomId, idPattern);
        map.put(txtKeyMoney, keyMoneyPattern);
        map.put(txtQty, qtyPattern);

        colRoomId.setCellValueFactory(new PropertyValueFactory<>("room_id"));
        colRoomType.setCellValueFactory(new PropertyValueFactory<>("type"));
        colKeyMoney.setCellValueFactory(new PropertyValueFactory<>("key_money"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
    }

    private void setDataFields(RoomDTO newValue) {
        txtRoomId.setText(newValue.getRoom_id());
        cmbRoomType.setValue(newValue.getType());
        txtKeyMoney.setText(String.valueOf(newValue.getKey_money()));
        txtQty.setText(String.valueOf(newValue.getQty()));

        btnUpdate.setDisable(false);
        btnDelete.setDisable(false);
        btnAdd.setDisable(true);
    }

    private void setRoomDataLoad() throws Exception {
        List<RoomDTO> roomDTOS = manageRoomBO.loadAllStudent();
        ObservableList<RoomDTO> observableList = FXCollections.observableArrayList();
        for (RoomDTO roomDTO : roomDTOS) {
            observableList.add(new RoomTM(
                    roomDTO.getRoom_id(),
                    roomDTO.getType(),
                    roomDTO.getKey_money(),
                    roomDTO.getQty()
            ));
        }
        tblRooms.setItems(observableList);
        FilteredList<RoomDTO> filterData = new FilteredList(observableList, b -> true);

        txtSearchRoomId.textProperty().addListener((observable, oldValue, newValue) -> {
            filterData.setPredicate(RoomDTO -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                if (RoomDTO.getRoom_id().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else
                    return false;
            });
        });

        SortedList<RoomDTO> sortedData = new SortedList<>(filterData);
        sortedData.comparatorProperty().bind(tblRooms.comparatorProperty());
        tblRooms.setItems(sortedData);
    }

    private void comboLoad() {
        cmbRoomType.getItems().add("AC");
        cmbRoomType.getItems().add("NON-AC");
        cmbRoomType.getItems().add("AC / FOOD");
        cmbRoomType.getItems().add("NON-AC / FOOD");
    }

    public void addOnAction(ActionEvent actionEvent) throws Exception {
        try {
            if (manageRoomBO.add(new RoomDTO(txtRoomId.getText(),
                    cmbRoomType.getValue().toString(),
                    Double.parseDouble(txtKeyMoney.getText()),
                    Integer.parseInt(txtQty.getText())
            ))) {
                new Alert(Alert.AlertType.CONFIRMATION, " Room Saved... Successfully").showAndWait();
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Something Went Wrong. try again carefully!").showAndWait();
        }
        clear();
        setRoomDataLoad();
    }


    public void updateOnAction(ActionEvent actionEvent) throws Exception {
        try {
            if (manageRoomBO.updateRoom(new RoomDTO(
                    txtRoomId.getText(),
                    cmbRoomType.getValue().toString(),
                    Double.parseDouble(txtKeyMoney.getText()),
                    Integer.parseInt(txtQty.getText())
            ))) {
                new Alert(Alert.AlertType.CONFIRMATION, "Do you want to Update it?").showAndWait();
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Something Went Wrong. try again carefully!").showAndWait();
        }
        clear();
        setRoomDataLoad();
    }

    public void deleteOnAction(ActionEvent actionEvent) throws Exception {
        try {
            if (manageRoomBO.deleteRoom(txtRoomId.getText())) {
                new Alert(Alert.AlertType.CONFIRMATION, "Do you want to Delete this record?").showAndWait();
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Something Went Wrong. Please Try again!").showAndWait();
        }
        clear();
        setRoomDataLoad();
    }

    public void clearOnAction(ActionEvent actionEvent) {
        clear();
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

    private void clear() {
        txtRoomId.clear();
        cmbRoomType.getSelectionModel().clearSelection();
        txtKeyMoney.clear();
        txtQty.clear();
        txtSearchRoomId.clear();
        ValidationUtil.validate(map, btnAdd);
        btnUpdate.setDisable(true);
        btnDelete.setDisable(true);
        tblRooms.refresh();
    }
}
