package com.jtmjinfo.calculonotas;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("views/alunos.fxml"));

        Scene cenaPrincipal = new Scene(fxmlLoader.load());

        stage.setScene(cenaPrincipal);
        stage.show();

    }

    public static void main(String[] args) {
        launch();
    }
}

