package io.github.alphameo.darr_visualization.visualization;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

/** JavaFX DynamicArray demo GUI */
public class DemoApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(DemoApplication.class.getResource("mainwindow.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

        stage.setTitle("DeezMos");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) throws IOException, RuntimeException {
        launch();
    }
}
