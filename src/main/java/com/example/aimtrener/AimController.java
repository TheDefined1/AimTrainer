package com.example.aimtrener;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.shape.Circle;
import java.util.Random;
import javafx.animation.Timeline;
import javafx.util.Duration;
import javafx.scene.control.Label;




public class AimController{

    public Label shoot;
    public static Circle circle1;
    public static Circle circle3;
    public static Circle circle2;
    public Label gameCount;
    public Label inGamePause;
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button back, control;

    @FXML
    private Button exit;

    @FXML
    private Label menu;

    @FXML
    private Button modeFour;

    @FXML
    private Button modeOne;

    @FXML
    private Button modeTree;

    @FXML
    private Button modeTwo;

    @FXML
    private Button modes;

    @FXML
    private Label pause;

    @FXML
    private Button play;

    @FXML
    private Label sizeSett;

    @FXML
    private Slider sliderTime;

    @FXML
    private Slider sliderSize;

    @FXML
    private Label timeCounter;

    @FXML
    private Label timer;

    @FXML
    private Circle circleSett;

    @FXML
    private Label titleText;

    static boolean exitToMenu = false;
    static boolean isPaused = false;
    static boolean isHit1 = false;
    static boolean isHit2 = false;
    static boolean isHit3 = false;
    static boolean isMiss1 = false;
    static boolean isMiss2 = false;
    static boolean isMiss3 = false;

    private double initialSize; // Переменная для хранения начального размера круга
    private int timeValue; // Переменная для хранения значения времени
    private boolean isGame = false;
    private int gameMode;
    static int hitsCounter = 0;
    static int missCounter = 0;

    private final double maxCircleSize = 50.0;
    private final int timeSpeed = 1;

    static Random random = new Random(1);

    AnimationTimer Timer = new AnimationTimer() {
        @Override
        public void handle(long now) {
            if (exitToMenu){
                circleSett.setVisible(false);
                timer.setVisible(false);
                modeOne.setVisible(false);
                modeTwo.setVisible(false);
                modeTree.setVisible(false);
                modeFour.setVisible(false);
                sliderSize.setVisible(false);
                sliderTime.setVisible(false);
                timeCounter.setVisible(false);
                sizeSett.setVisible(false);
                back.setVisible(false);
                menu.setVisible(false);
                shoot.setVisible(false);
                pause.setVisible(false);
                play.setVisible(false);
                circle1.setVisible(false);
                circle2.setVisible(false);
                circle3.setVisible(false);
                gameCount.setVisible(false);
                inGamePause.setVisible(false);

                titleText.setVisible(true);
                control.setVisible(true);
                exit.setVisible(true);
                modes.setVisible(true);

                exitToMenu = false;
            }
            if (isGame){
                if (!isPaused) {
                    switch (gameMode) {
                        case 1:
                            if (isHit1) {
                                hit(1);
                            }
                            if (isMiss1) {
                                miss(1);
                            }
                    }
                }else{
                    introduction();
                }
            }
        }
    };
    void introduction(){
        gameCount.setVisible(true);
        gameCount.setText("3");
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            int currentSeconds = Integer.parseInt(gameCount.getText());
            if (currentSeconds > 0) {
                gameCount.setText(String.valueOf(currentSeconds - 1));
            }else{
                isGame = true;
                gameCount.setVisible(false);
                isPaused = false;
            }
        }));
    }
    public static void hit(int circleNumber){
        switch(circleNumber){
            case 1:
                circle1.setLayoutX(random.nextInt(75, 1845));
                circle1.setLayoutY(random.nextInt(75, 1005));
                isHit1 = false;
                hitsCounter++;
                break;
            case 2:
                circle2.setLayoutX(random.nextInt(75, 1845));
                circle2.setLayoutY(random.nextInt(75, 1005));
                isHit2 = false;
                hitsCounter++;
                break;
            case 3:
                circle3.setLayoutX(random.nextInt(75, 1845));
                circle3.setLayoutY(random.nextInt(75, 1005));
                isHit3 = false;
                hitsCounter++;
                break;
        }
    }
    public static void miss(int circleNumber){
        switch(circleNumber){
            case 1:
                circle1.setLayoutX(random.nextInt(75, 1845));
                circle1.setLayoutY(random.nextInt(75, 1005));
                isMiss1 = false;
                missCounter++;
                break;
            case 2:
                circle2.setLayoutX(random.nextInt(75, 1845));
                circle2.setLayoutY(random.nextInt(75, 1005));
                isMiss2 = false;
                missCounter++;
                break;
            case 3:
                circle3.setLayoutX(random.nextInt(75, 1845));
                circle3.setLayoutY(random.nextInt(75, 1005));
                isMiss3 = false;
                missCounter++;
                break;
        }
    }
    @FXML
    private void modeButtonsClosing(){
        modeOne.setVisible(false);
        modeTwo.setVisible(false);
        modeTree.setVisible(false);
        modeFour.setVisible(false);
        menu.setVisible(false);

        circleSett.setVisible(true);
        timer.setVisible(true);
        sliderSize.setVisible(true);
        sliderTime.setVisible(true);
        timeCounter.setVisible(true);
        sizeSett.setVisible(true);
        play.setVisible(true);
    }


    @FXML
    void initialize() {
        // Инициализация слайдера размера и круга
        if (sliderSize != null && circleSett != null) {
            // Устанавливаем минимальное и максимальное значения слайдера
            sliderSize.setMin(10);
            sliderSize.setMax(maxCircleSize);

            // Устанавливаем начальное значение слайдера (например, половина от maxCircleSize)
            double initialSize = maxCircleSize / 2;
            sliderSize.setValue(initialSize);

            // Устанавливаем радиус круга равным начальному значению слайдера
            circleSett.setRadius(initialSize);

            // Слушатель изменений слайдера размера
            sliderSize.valueProperty().addListener((observable, oldValue, newValue) -> {
                // Устанавливаем радиус круга равным текущему значению слайдера
                circleSett.setRadius(newValue.doubleValue());
            });
        } else {
            System.err.println("sliderSize или circleSett не были внедрены! Проверьте FXML.");
        }

        // Инициализация слайдера времени и таймера
        if (sliderTime != null && timer != null) {
            sliderTime.setMin(15);
            sliderTime.setMax(300);
            timeValue = (int) sliderTime.getValue(); // Получение начального значения времени
            updateTimerLabel(); // Обновление текста таймера

            // Слушатель изменений слайдера времени
            sliderTime.valueProperty().addListener((observable, oldValue, newValue) -> {
                timeValue = newValue.intValue(); // Обновление значения времени
                updateTimerLabel(); // Обновление текста таймера
            });
        } else {
            System.err.println("sliderTime или timer не были внедрены! Проверьте FXML.");
        }

        Timer.start();
        exitToMenu = true;
    }


    // Метод для обработки нажатия на кнопку Modes
    @FXML
    void handleModesAction() {
        // Скрыть текущие элементы
        control.setVisible(false);
        exit.setVisible(false);
        modes.setVisible(false);

        // Показать кнопки выбора режима и кнопку Back
        modeOne.setVisible(true);
        modeTwo.setVisible(true);
        modeTree.setVisible(true);
        modeFour.setVisible(true);
        back.setVisible(true);
    }

    // Метод для обработки нажатия на кнопку Control
    @FXML
    void handleControlAction() {
        // Скрыть текущие элементы
        control.setVisible(false);
        exit.setVisible(false);
        modes.setVisible(false);
        titleText.setVisible(false);

        back.setVisible(true);
        pause.setVisible(true);
        menu.setVisible(true);
        shoot.setVisible(true);
    }



    // Метод для обработки нажатия на кнопку Exit
    @FXML
    void handleExitAction() {
        // Закрыть приложение
        System.exit(0);
    }

    // Метод для обработки нажатия на кнопку Back
    @FXML
    void handleBackAction() {
        exitToMenu = true;
    }

    @FXML
    void handle1CircleModeAction(){
        modeButtonsClosing();
        gameMode = 1;
    }

    // Метод для обновления текста в Label timer
    private void updateTimerLabel() {
        timer.setText("Время: " + timeValue + " сек");
    }

    // Метод для получения значения времени
    public int getTimeValue() {
        return timeValue;
    }

    // Метод для получения значения размера круга
    public double getInitialSize() {
        return initialSize;
    }

    public void handleHoldingModeAction(ActionEvent actionEvent) {
        modeButtonsClosing();
        gameMode = 2;
    }

    public void handle3ShrinkingCirclesModeAction(ActionEvent actionEvent) {
        modeButtonsClosing();
        gameMode = 3;
    }

    public void handle3CirclesModeAction(ActionEvent actionEvent) {
        modeButtonsClosing();
        gameMode = 4;
    }

    public void handleStartGame(ActionEvent actionEvent) {
        introduction();
        titleText.setVisible(false);
        sliderSize.setVisible(false);
        sliderTime.setVisible(false);
        timeCounter.setVisible(false);
        sizeSett.setVisible(false);
        play.setVisible(false);
        back.setOpacity(0.2);
        switch (gameMode){
            case 1,4:
                hit(1);
                hitsCounter = 0;
                circle1.setVisible(true);
                break;
            case 2,3:
                hit(1);
                hit(2);
                hit(3);
                circle1.setVisible(true);
                circle2.setVisible(true);
                circle3.setVisible(true);
                hitsCounter = 0;
                break;
        }
    }
}
