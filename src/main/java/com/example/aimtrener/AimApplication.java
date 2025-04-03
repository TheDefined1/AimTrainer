package com.example.aimtrener;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

import java.io.IOException;

public class AimApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(AimApplication.class.getResource("aimTester-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1920, 1080);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();

        scene.setOnKeyReleased(event -> {
            if(event.getCode() == KeyCode.ESCAPE)
                AimController.exitToMenu = true;
        });
        scene.setOnKeyPressed(event -> {

            if(event.getCode() == KeyCode.SPACE && !AimController.isPaused)
                AimController.isPaused = true;
            else if (event.getCode() == KeyCode.SPACE)
                AimController.introduction = true;

            if(event.getCode() == KeyCode.LEFT && AimController.isAimed1 && AimController.gameMode != 4)
                AimController.isHit1 = true;
            if(event.getCode() == KeyCode.LEFT && AimController.isAimed2)
                AimController.isHit2 = true;
            if(event.getCode() == KeyCode.LEFT && AimController.isAimed3)
                AimController.isHit3 = true;

            if(event.getCode() == KeyCode.LEFT && (!AimController.isAimed3 || !AimController.isAimed2 || !AimController.isAimed1))
                AimController.isMiss1 = true;
        });

   }

    public static void main(String[] args) {
        launch();
    }
}