package com.example.exam.Controller;

import com.example.exam.DbFunctions.DbFunctions;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.w3c.dom.events.MouseEvent;

import java.io.IOException;

public class MainController {

    @FXML
    private Button btn1,btn2;

    @FXML
    private GridPane grid1, grid2;
    @FXML
    private ImageView logOut;

    @FXML
    private TableColumn<Services, String> nameS;

    @FXML
    private TableColumn<Services, Float> priceS;
    @FXML
    private TableView<Services> tableService;
    @FXML
    private TableColumn<Clients, String> surname, abonement, dateC, dateD,lastname, name;
    @FXML
    private TableView<Clients> table;
    private DbFunctions dbFunctions = new DbFunctions();
    @FXML
    void onClick(ActionEvent event) {
        if (event.getSource() == btn1){
            grid1.toFront();
        } else if (event.getSource() == btn2) {
            grid2.toFront();
        }


    }



    @FXML
    void initialize() {

        surname.setCellValueFactory(new PropertyValueFactory<>("surname"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        lastname.setCellValueFactory(new PropertyValueFactory<>("lastname"));
        abonement.setCellValueFactory(new PropertyValueFactory<>("abonement"));
        dateC.setCellValueFactory(new PropertyValueFactory<>("dateC"));
        dateD.setCellValueFactory(new PropertyValueFactory<>("dateD"));
        table.setItems(dbFunctions.getClients());

        nameS.setCellValueFactory(new PropertyValueFactory<>("name"));
        priceS.setCellValueFactory(new PropertyValueFactory<>("price"));
        tableService.setItems(dbFunctions.getServices());

    }

    public void logOutClick(javafx.scene.input.MouseEvent mouseEvent) throws IOException {
        Stage stage = (Stage) logOut.getScene().getWindow();
        stage.close();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/exam/auth.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Главное меню");
        stage.setScene(new Scene(root1));
        stage.show();
    }
}
