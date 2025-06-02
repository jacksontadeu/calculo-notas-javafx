package com.jtmjinfo.calculonotas;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;


public class App extends Application {

    public static Scene scene;
    public static Stage stage;

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("views/telamenu.fxml"));
        Parent menu = fxmlLoader.load();
        scene = new Scene(menu);


        scene.getStylesheets().add("styles.css");
        stage.setScene(scene);
        stage.setTitle("Sistema Notas LPI");
        stage.getIcons().add(new Image(getClass().getResourceAsStream("imagens/sistema.png")));
        stage.show();

    }

    public static void main(String[] args) {
        launch();
    }

    public static void setRoot(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml));
        Parent root = fxmlLoader.load();
        scene.setRoot(root);
    }
}

