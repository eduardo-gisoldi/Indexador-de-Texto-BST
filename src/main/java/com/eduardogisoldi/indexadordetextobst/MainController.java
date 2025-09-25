package com.eduardogisoldi.indexadordetextobst;

import com.eduardogisoldi.indexadordetextobst.arvoreBST.ArvoreBST;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ProgressBar;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

public class MainController {

    Parent root;
    Stage stage;
    Scene scene;

    @FXML
    ProgressBar progressBar;

    ArvoreBST tree = new ArvoreBST();


    public void showView(ActionEvent e, String fxml) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml));
        root = loader.load();
        stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    private void processFileAsync(File file, Stage currentStage) {
        Task<Void> task = new Task<>() {
            @Override
            protected Void call() throws Exception {
                // First pass - count total words
                int total = 0;
                try (Scanner scanner = new Scanner(new FileInputStream(file), "UTF-8")) {
                    while (scanner.hasNext()) {
                        scanner.next();
                        total++;
                    }
                }

                // Second pass - process words
                int count = 0;
                try (Scanner leitor = new Scanner(new FileInputStream(file), "UTF-8")) {
                    while (leitor.hasNext()) {
                        String palavra = leitor.next();
                        tree.insere(palavra, true);
                        count++;
                        updateProgress(count, total);

                        if (count % 100 == 0) {
                            updateMessage(count + " palavras indexadas ate o momento...");
                        }
                    }
                }
                return null;
            }
        };

        // Handle task completion
        task.setOnSucceeded(event -> {
            try {
                System.out.println("Arquivo indexado com sucesso!");
                FXMLLoader loader = new FXMLLoader(getClass().getResource("main-view.fxml"));
                Scene scene = new Scene(loader.load());
                currentStage.setScene(scene);
            } catch (IOException ex) {
                System.out.println("Erro ao carregar tela principal: " + ex.getMessage());
            }
        });

        // Handle task failure
        task.setOnFailed(event -> {
            System.out.println("Erro ao processar arquivo: " + task.getException().getMessage());
        });

        // Bind progress bar to task progress
        progressBar.progressProperty().bind(task.progressProperty());

        // Start the task
        new Thread(task).start();
    }

    public void loadFile(ActionEvent e, File file) throws IOException {
        // loading screen
        FXMLLoader loader = new FXMLLoader(getClass().getResource("indexing-view.fxml"));
        root = loader.load();
        MainController controller = loader.getController();

        // Set up the new scene
        stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

        // Start the async loading
        controller.processFileAsync(file, stage);

    }

    public void exitApplication(ActionEvent e) {
        System.out.println("Encerrando aplicação...");
        Stage stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        stage.close();
    }

}
