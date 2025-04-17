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
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import java.util.Random;
import javafx.animation.Timeline;
import javafx.util.Duration;

public class  AimController{
    @FXML
    public Label shoot;

    @FXML
    public Label inGamePause;

    @FXML
    public ProgressBar progressBar;

    @FXML
    public Label Results;

    @FXML
    public Label AccuracyLabel1;

    @FXML
    public Label RateLabel1;

    @FXML
    public Label AccuracyLabel2;

    @FXML
    public Label RateLabel2;

    @FXML
    public Circle circle1;

    @FXML
    public Circle circle2;

    @FXML
    public Circle circle3;

    @FXML
    public Label gameCount;

    @FXML
    public Label killsCounter;

    @FXML
    public Label missesCounter;
    public Label kpsLabel1;
    public Label kpsLabel2;
    public Label markLabel;
    public AnchorPane pane;

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
    static boolean isIntroduction = false;
    static boolean warning = false;

    private double initialSize; // Переменная для хранения начального размера круга
    private int timeValue; // Переменная для хранения значения времени
    private int gameTimeValue;
    static boolean isGame = false;
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
                killsCounter.setVisible(false);
                missesCounter.setVisible(false);
                RateLabel1.setVisible(false);
                RateLabel2.setVisible(false);
                AccuracyLabel2.setVisible(false);
                AccuracyLabel1.setVisible(false);
                AccuracyLabel2.setVisible(false);
                kpsLabel1.setVisible(false);
                kpsLabel2.setVisible(false);
                Results.setVisible(false);
                markLabel.setVisible(false);

                timelineGame.stop();
                gameCount.setText("10");
                timer.setText("Время: " + timeValue + " сек");
                killsCounter.setText("Kills: 0");
                missesCounter.setText("Misses: 0");
                missCounter = 0;
                hitsCounter = 0;

                titleText.setVisible(true);
                control.setVisible(true);
                exit.setVisible(true);
                modes.setVisible(true);

                exitToMenu = false;
                isGame = false;
            }
            if (introduction){
                introduction = false;
                isIntroduction = true;
                inGamePause.setVisible(false);
                gameCount.setVisible(true);
                gameCount.setText("3");
                Timeline timelineIntroduction = new Timeline(new KeyFrame(Duration.seconds(1), _ -> {
                    int currentSeconds = Integer.parseInt(gameCount.getText());
                    if (currentSeconds > 0) {
                        gameCount.setText(String.valueOf(currentSeconds - 1));
                    }else{
                        timelineGame.play();
                        isGame = true;
                        gameCount.setVisible(false);
                        isPaused = false;
                        isIntroduction = false;
                    }
                }));
                timelineIntroduction.setCycleCount(4);
                timelineIntroduction.getStatus();
                timelineIntroduction.play();
            }
            if (isGame){
                if (!isPaused) {
                    double shrinkingSpeed = 0.05;
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

                            if (circle1.getRadius()<= 3){isMiss1 = true;circle1.setRadius(getInitialSize());}
                            if (circle2.getRadius()<= 3){isMiss2 = true;circle2.setRadius(getInitialSize());}
                            if (circle3.getRadius()<= 3){isMiss3 = true;circle3.setRadius(getInitialSize());}

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
                    inGamePause.setVisible(true);
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
            double accuracy;
            double kps = (double) Math.round((double) hitsCounter / (timeValue * 0.01)) /100;
            int gameModeCoefficient = 0;
            double sizeCoefficient;
            double timeCoefficient = 0;
            double rate;
            String mark;
            isGame = false;
            killsCounter.setVisible(false);
            missesCounter.setVisible(false);
            Results.setVisible(true);
            markLabel.setVisible(true);
            AccuracyLabel1.setVisible(true);
            AccuracyLabel2.setVisible(true);
            if (missCounter == 0) {
                accuracy = hitsCounter * 100;
            } else{
                accuracy = (double) Math.round((double) (hitsCounter * 100) / missCounter);
            }
            AccuracyLabel2.setText(hitsCounter + "/" + missCounter + " = " + (accuracy/100));
            gameModeCoefficient = switch (gameMode) {
                case 1 -> 6;
                case 2,3 -> 5;
                case 4 -> 7;
                default -> gameModeCoefficient;
            };
            if (getInitialSize() < 20){
                sizeCoefficient = 1.1;
            }else if (getInitialSize() < 40){
                sizeCoefficient = 1;
            }else if (getInitialSize() < 60){
                sizeCoefficient = 0.7;
            }else{
                sizeCoefficient = 0.6;
            }
            if (timeValue <= 30){
                timeCoefficient = 1.05;
            }else if (timeValue <= 60){
                timeCoefficient = 1;
            }else if (timeValue <= 100){
                timeCoefficient = 0.95;
            }else if (timeValue <= 150){
                timeCoefficient = 0.9;
            }else if (timeValue <= 200){
                timeCoefficient = 0.85;
            }else if (timeValue <= 250){
                timeCoefficient = 0.8;
            }else if (timeValue <= 300){
                timeCoefficient = 0.75;
            }
            rate = (double) Math.round(accuracy * sizeCoefficient * gameModeCoefficient * Math.pow(timeCoefficient, 3)) /100;
            if (rate <= 50){
                mark = "F";
            } else if (rate <= 60) {
                mark = "E";
            }else if (rate <= 70) {
                mark = "D";
            }else if (rate <= 80) {
                mark = "C";
            }else if (rate <= 90) {
                mark = "B";
            }else if (rate < 100) {
                mark = "A";
            }else{
                mark = "A+";
            }
            markLabel.setText(mark);
            kpsLabel1.setVisible(true);
            kpsLabel2.setVisible(true);
            kpsLabel2.setText(String.valueOf(kps));
            RateLabel1.setVisible(true);
            RateLabel2.setVisible(true);
            RateLabel2.setText(rate + "%");
            back.setVisible(true);
        }
    }));
    public void hit(int circleNumber){
        switch(circleNumber){
            case 1:
                circle1.setLayoutX(random.nextInt(75, 1845));
                circle1.setLayoutY(random.nextInt(75, 950));
                isHit1 = false;
                break;
            case 2:
                circle2.setLayoutX(random.nextInt(75, 1845));
                circle2.setLayoutY(random.nextInt(75, 950));
                isHit2 = false;
                break;
            case 3:
                circle3.setLayoutX(random.nextInt(75, 1845));
                circle3.setLayoutY(random.nextInt(75, 950));
                isHit3 = false;
                break;
        }
        hitsCounter++;
        killsCounter.setText("Kills: " + hitsCounter);
    }
    public void miss(int circleNumber){
        switch(circleNumber){
            case 1:
                circle1.setLayoutX(random.nextInt(75, 1845));
                circle1.setLayoutY(random.nextInt(75, 950));
                isMiss1 = false;
                break;
            case 2:
                circle2.setLayoutX(random.nextInt(75, 1845));
                circle2.setLayoutY(random.nextInt(75, 950));
                isMiss2 = false;
                break;
            case 3:
                circle3.setLayoutX(random.nextInt(75, 1845));
                circle3.setLayoutY(random.nextInt(75, 950));
                isMiss3 = false;
                break;
        }
        missCounter++;
        missesCounter.setText("Misses: " + missCounter);
        warning = true;
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
            double maxCircleSize = 80.0;
            sliderSize.setMax(maxCircleSize);

            // Устанавливаем начальное значение слайдера (например, половина от maxCircleSize)
            initialSize = 25;
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
        gameMode = 4;
    }

    public void handle3ShrinkingCirclesModeAction() {
        modeButtonsClosing();
        gameMode = 3;
    }

    public void handle3CirclesModeAction() {
        modeButtonsClosing();
        gameMode = 2;
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
        killsCounter.setVisible(true);
        missesCounter.setVisible(true);

        circle1.setRadius(getInitialSize());
        circle2.setRadius(getInitialSize());
        circle3.setRadius(getInitialSize());
        gameTimeValue = timeValue;
        timelineGame.setCycleCount(gameTimeValue--);

        circle1.setVisible(true);
        circle1.setLayoutX(random.nextInt(75, 1845));
        circle1.setLayoutY(random.nextInt(75, 950));

        switch (gameMode){
            case 2,3:
                circle2.setLayoutX(random.nextInt(75, 1845));
                circle2.setLayoutY(random.nextInt(75, 950));
                circle3.setLayoutX(random.nextInt(75, 1845));
                circle3.setLayoutY(random.nextInt(75, 950));
                circle2.setVisible(true);
                circle3.setVisible(true);
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
