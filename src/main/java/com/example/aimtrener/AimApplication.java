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
        stage.setTitle("Aim trainer");
        stage.setScene(scene);
        stage.show();

        scene.setOnKeyReleased(event -> {
            if(event.getCode() == KeyCode.ESCAPE)
                AimController.exitToMenu = true;
        });
        scene.setOnKeyPressed(event -> {
            if(event.getCode() == KeyCode.SPACE && !AimController.isPaused && !AimController.isIntroduction) {
                AimController.isPaused = true;
                AimController.timelineGameStop = true;
            }else if (event.getCode() == KeyCode.SPACE && !AimController.isIntroduction)
                AimController.introduction = true;
        });
        scene.setOnMouseClicked(_ -> {
            if(AimController.isAimed1 && AimController.gameMode != 4 && !AimController.isPaused && AimController.isGame)
                AimController.isHit1 = true;
            if(AimController.isAimed2 && !AimController.isPaused&& AimController.isGame)
                AimController.isHit2 = true;
            if(AimController.isAimed3 && !AimController.isPaused&& AimController.isGame)
                AimController.isHit3 = true;

            if(!AimController.isAimed3 && !AimController.isAimed2 && !AimController.isAimed1 && !AimController.isPaused&& AimController.isGame)
                AimController.isMiss1 = true;
        });

   }

    public static void main(String[] args) {
        launch();
    }
}