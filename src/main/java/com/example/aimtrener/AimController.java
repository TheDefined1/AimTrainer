package com.example.aimtrener;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Slider;
import javafx.scene.shape.Circle;
import java.util.Random;
import javafx.animation.Timeline;
import javafx.util.Duration;


public class AimController{
    @FXML
    public Label shoot;

    @FXML
    public Label inGamePause;
    public ProgressBar progressBar;
    public Label Results;
    public Label AccuracyLabel1;
    public Label RateLabel1;
    public Label AccuracyLabel2;
    public Label RateLabel2;
    public Circle circle1;
    public Circle circle2;
    public Circle circle3;
    public Label gameCount;
    public Label killsCounter;
    public Label missesCounter;

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
    static boolean introduction = false;
    static boolean isPaused = false;
    static boolean isHit1 = false;
    static boolean isHit2 = false;
    static boolean isHit3 = false;
    static boolean isMiss1 = false;
    static boolean isMiss2 = false;
    static boolean isMiss3 = false;
    static boolean isAimed1 = false;
    static boolean isAimed2 = false;
    static boolean isAimed3 = false;
    static boolean timelineGameStop = false;

    private double initialSize; // Переменная для хранения начального размера круга
    private int timeValue; // Переменная для хранения значения времени
    private int gameTimeValue;
    private static boolean isGame = false;
    static byte gameMode;
    static int hitsCounter = 0;
    static int missCounter = 0;

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
            if (introduction){
                introduction = false;
                gameCount.setVisible(true);
                gameCount.setText("3");
                Timeline timelineIntroduction = new Timeline(new KeyFrame(Duration.seconds(1), _ -> {
                    int currentSeconds = Integer.parseInt(gameCount.getText());
                    if (currentSeconds > 0) {
                        gameCount.setText(String.valueOf(currentSeconds - 1));
                    }else{
                        timelineGame.setCycleCount(gameTimeValue--);
                        timelineGame.play();
                        isGame = true;
                        gameCount.setVisible(false);
                        isPaused = false;
                    }
                }));
                timelineIntroduction.setCycleCount(4);
                timelineIntroduction.getStatus();
                timelineIntroduction.play();
            }
            if (isGame){
                if (!isPaused) {
                    double shrinkingSpeed = 0.02;
                    switch (gameMode) {
                        case 1:
                            if (isHit1) {hit(1);}

                            if (isMiss1) {miss(1);}

                            break;
                        case 2:
                            if (isHit1) {hit(1);}
                            if (isHit2) {hit(2);}
                            if (isHit3) {hit(3);}

                            if (isMiss1) {miss(1);}

                            break;
                        case 3:
                            circle1.setRadius(circle1.getRadius() - shrinkingSpeed);
                            circle2.setRadius(circle2.getRadius() - shrinkingSpeed);
                            circle3.setRadius(circle3.getRadius() - shrinkingSpeed);

                            if (circle1.getRadius()<= 1){isMiss1 = true;}
                            if (circle2.getRadius()<= 1){isMiss2 = true;}
                            if (circle3.getRadius()<= 1){isMiss3 = true;}

                            if (isHit1) {
                                hit(1);
                                circle1.setRadius(getInitialSize());
                            }
                            if (isHit2) {
                                hit(2);
                                circle2.setRadius(getInitialSize());
                            }
                            if (isHit3) {
                                hit(3);
                                circle3.setRadius(getInitialSize());
                            }
                            if (isMiss1) {
                                miss(1);
                                circle1.setRadius(getInitialSize());
                            }
                            if (isMiss2) {
                                miss(2);
                                circle1.setRadius(getInitialSize());
                            }
                            if (isMiss3) {
                                miss(3);
                                circle1.setRadius(getInitialSize());
                            }
                    }
                }else if(timelineGameStop){
                    timelineGame.stop();
                    timelineGameStop = false;
                }
            }
        }
    };
    Timeline timelineGame = new Timeline(new KeyFrame(Duration.seconds(1), _ -> {
        int currentSeconds = gameTimeValue;
        if (currentSeconds > 0) {
            timer.setText("Время: " + gameTimeValue + " сек");
            gameTimeValue--;
        }else{
            isGame = false;
        }
    }));
    public void hit(int circleNumber){
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
    public void miss(int circleNumber){
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
            double maxCircleSize = 50.0;
            sliderSize.setMax(maxCircleSize);

            // Устанавливаем начальное значение слайдера (например, половина от maxCircleSize)
            initialSize = maxCircleSize / 2;
            sliderSize.setValue(initialSize);

            // Устанавливаем радиус круга равным начальному значению слайдера
            circleSett.setRadius(initialSize);

            // Слушатель изменений слайдера размера
            sliderSize.valueProperty().addListener((_, _, newValue) -> {
                // Устанавливаем радиус круга равным текущему значению слайдера
                circleSett.setRadius(newValue.doubleValue());
                initialSize = newValue.doubleValue();
            });
        } else {
            System.err.println("sliderSize или circleSett не были внедрены! Проверьте FXML.");
        }

        // Инициализация слайдера времени и таймера
        if (sliderTime != null && timer != null) {
            sliderTime.setMin(15);
            sliderTime.setMax(300);
            sliderTime.setValue(60);
            timeValue = (int) sliderTime.getValue(); // Получение начального значения времени
            updateTimerLabel(); // Обновление текста таймера

            // Слушатель изменений слайдера времени
            sliderTime.valueProperty().addListener((_, _, newValue) -> {
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

    public void handleHoldingModeAction() {
        modeButtonsClosing();
        gameMode = 2;
    }

    public void handle3ShrinkingCirclesModeAction() {
        modeButtonsClosing();
        gameMode = 3;
    }

    public void handle3CirclesModeAction() {
        modeButtonsClosing();
        gameMode = 4;
    }

    public void handleStartGame() {
        introduction = true;
        titleText.setVisible(false);
        sliderSize.setVisible(false);
        sliderTime.setVisible(false);
        timeCounter.setVisible(false);
        sizeSett.setVisible(false);
        circleSett.setVisible(false);
        play.setVisible(false);
        back.setVisible(false);
        circle1.setRadius(getInitialSize());
        circle2.setRadius(getInitialSize());
        circle3.setRadius(getInitialSize());
        gameTimeValue = timeValue;
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

    public void aim1() {
        if (!AimController.isPaused)
            isAimed1 = true;
    }

    public void disaim1() {
        isAimed1 = false;
    }

    public void aim2() {
        if (!AimController.isPaused)
            isAimed2 = true;
    }

    public void disaim2() {
        isAimed2 = false;
    }

    public void disaim3() {
        isAimed3 = false;
    }

    public void aim3() {
        if (!AimController.isPaused)
            isAimed3 = true;
    }
}
