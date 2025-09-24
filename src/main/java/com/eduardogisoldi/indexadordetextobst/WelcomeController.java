package com.eduardogisoldi.indexadordetextobst;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class WelcomeController {

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private Label instructionLbl;
    @FXML
    private Button fileChooserBtn;
    @FXML
    private TextArea txtArea;
    @FXML
    private Button voltarBtn;
    @FXML
    private Button confirmarBtn;

    public void showView(ActionEvent e, String fxml) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml));
        root = loader.load();
        stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void chooseFile(ActionEvent e) throws IOException {
        // Selecting file with FileChooser
        FileChooser txtChooser = new FileChooser();
        txtChooser.setTitle("Escolha um arquivo:");
        txtChooser.setInitialDirectory(new File("C:\\"));
        txtChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Arquivo TXT", "*.txt"));
        File selectedArchive = txtChooser.showOpenDialog(null);

        // validating selected file
        boolean validArchive = false;
        if (selectedArchive.exists() && selectedArchive.isFile() && selectedArchive.canRead()) {
            validArchive = true;
        }

        if (validArchive) {
            // loading confirmation screen
            FXMLLoader loader = new FXMLLoader(getClass().getResource("welcome-2-view.fxml"));
            root = loader.load();
            WelcomeController controller = loader.getController();
            controller.displayArchive(selectedArchive);
            stage = (Stage)((Node)e.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
        } else { // no valid file was selected
            instructionLbl.setText("Nenhum arquivo válido selecionado. Tente novamente.");
            instructionLbl.setFont(Font.font("System", FontWeight.BOLD, 24));
        }
    }

    @FXML
    public void displayArchive(File file) throws IOException {
        // Displaying file content in TextArea
        String content = new String(Files.readAllBytes(Paths.get(file.getAbsolutePath())));
        txtArea.setText(content);
        txtArea.setEditable(false);
    }

    @FXML
    public void back(ActionEvent e) throws IOException {
        // loading welcome screen
        showView(e, "welcome-view.fxml");
    }

    @FXML
    public void confirm(ActionEvent e) throws IOException {
        // loading main screen
        MainController mainController = new MainController();
        mainController.loadFile(e);
    }
}