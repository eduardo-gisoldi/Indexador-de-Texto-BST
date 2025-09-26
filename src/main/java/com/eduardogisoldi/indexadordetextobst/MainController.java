package com.eduardogisoldi.indexadordetextobst;

import com.eduardogisoldi.indexadordetextobst.arvoreBST.ArvoreBST;
import com.eduardogisoldi.indexadordetextobst.arvoreBST.No;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Label;
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
    private Label statusLabel;
    @FXML
    private ProgressBar progressBar;
    @FXML
    private ListView<String> wordListView;
    @FXML
    private Label totalWordsLabel;
    @FXML
    private Button okBtn;

    ArvoreBST tree = new ArvoreBST();


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

                        // Updating List and total words label
                        Platform.runLater(() -> {
                            if (tree.busca(palavra) != null) {
                                String entry = palavra + " (" + tree.busca(palavra).getQtd() + ")";
                                wordListView.getItems().add(entry);
                            }

                            totalWordsLabel.setText(tree.qtdNos() + " palavras únicas indexadas.");
                        });

                        // Update message and every 100 words + small pause
                        if (count % 100 == 0) {
                            updateMessage(count + " palavras indexadas...");
                            Thread.sleep(100);
                        }
                    }
                }

                return null;
            }
        };

        // Handle task completion
        task.setOnSucceeded(event -> {
            statusLabel.setText("Arquivo indexado com sucesso!");
            okBtn.setDisable(false);
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

    public void ok(ActionEvent e) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("main-view.fxml"));
        stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        scene = new Scene(loader.load());
        stage.setScene(scene);
        stage.show();
    }
}
