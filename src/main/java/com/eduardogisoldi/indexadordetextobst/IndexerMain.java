package com.eduardogisoldi.indexadordetextobst;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class IndexerMain extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        //FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("main-view.fxml")); //pre-built

        Parent welcomeRoot = FXMLLoader.load(getClass().getResource("welcome-view.fxml"));
        Parent mainRoot = FXMLLoader.load(getClass().getResource("main-view.fxml"));
        Scene scene = new Scene(welcomeRoot);
        stage.setTitle("Indexador de Texto v2.0");
        stage.setScene(scene);
        stage.show();



    }

    public static void main(String[] args) {
        launch();
    }
}