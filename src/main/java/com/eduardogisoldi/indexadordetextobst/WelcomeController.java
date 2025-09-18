package com.eduardogisoldi.indexadordetextobst;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;

public class WelcomeController {
    @FXML
    private Label welcomeLbl;
    @FXML
    private Button fileChooserBtn;
    @FXML
    private Label selectedLbl;
    @FXML
    private TextArea txtView;
    @FXML
    private Label confirmLbl;
    @FXML
    private Button selectionConfirmedBtn;


    @FXML
    public void chooseFile(ActionEvent event) throws IOException {
        // Selecting file with FileChooser
        FileChooser txtChooser = new FileChooser();
        txtChooser.setTitle("Escolha um arquivo:");
        txtChooser.setInitialDirectory(new File("C:\\"));
        txtChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Arquivo TXT", "*.txt"));

        File selectedArchive = txtChooser.showOpenDialog(null);
        if (selectedArchive != null) {
            selectedLbl.setVisible(true);
            txtView.setVisible(true);
            confirmLbl.setVisible(true);
            selectionConfirmedBtn.setVisible(true);

            selectedLbl.setText(selectedArchive.getName().concat(" selecionado!"));

            displayArchive(selectedArchive.getPath());
        }
    }

    @FXML
    public void displayArchive(String filePath) {
        System.out.println(filePath);
    }


}