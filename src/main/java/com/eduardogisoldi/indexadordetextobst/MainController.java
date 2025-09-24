package com.eduardogisoldi.indexadordetextobst;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainController {

    Parent root;
    Stage stage;
    Scene scene;

    public void showView(ActionEvent e, String fxml) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml));
        root = loader.load();
        stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void loadFile(ActionEvent e) throws IOException {
        Integer progress = 0;

        // loading screen
        showView(e, "indexing-view.fxml");

        if (progress == 100) {
            System.out.println("Arquivo indexado com sucesso!");
            startMainScreen(e);
        }
    }

    public void startMainScreen(ActionEvent e) throws IOException {
        System.out.println("Iniciando aplicação...");

        // loading screen
        showView(e, "main-view.fxml");

    }

    public void exitApplication(ActionEvent e) {
        System.out.println("Encerrando aplicação...");
        Stage stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        stage.close();
    }

}
