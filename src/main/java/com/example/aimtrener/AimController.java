package com.example.aimtrener;


import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Slider;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;

import javafx.scene.paint.Color;
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
    public Pane warningPane;

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

    double movementSpeedX, movementSpeedY;
    int aimDotX;
    int aimDotY;

    private double initialSize; // Переменная для хранения начального размера круга
    private int timeValue; // Переменная для хранения значения времени
    private int gameTimeValue;
    static boolean isGame = false;
    static byte gameMode;
    static double hitsCounter = 0;
    static int missCounter = 0;

    static Random random = new Random(1);

    AnimationTimer Timer = new AnimationTimer() {
        @Override
        public void handle(long now) {
            if (warningPane.getOpacity() > 0){
                warningPane.setOpacity(warningPane.getOpacity() - 0.1);
            }
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
                sound.musicStop();
                gameCount.setText("10");
                timer.setText("Time: " + timeValue + " sec");
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
                        sound.musicContinue();
                        timelineGame.play();
                        isGame = true;
                        gameCount.setVisible(false);
                        isPaused = false;
                        isIntroduction = false;
                    }
                }));
                timelineIntroduction.setCycleCount(4);
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
                            break;
                        case 4:
                            if (isAimed1){
                                hitsCounter += 0.01666;
                                circle1.setFill(Color.RED);
                            }
                            if ((circle1.getLayoutX() >= aimDotX - 1 && circle1.getLayoutX() <= aimDotX + 1) || (circle1.getLayoutY() >= aimDotY - 1 && circle1.getLayoutY() <= aimDotY + 1)){
                                aimDotX = random.nextInt(75, 1845);
                                aimDotY = random.nextInt(75, 950);
                                movementSpeedX = (aimDotX - circle1.getLayoutX()) / random.nextInt(45,200);
                                movementSpeedY = (aimDotY - circle1.getLayoutY()) / random.nextInt(45,200);
                            }
                            circle1.setLayoutX(circle1.getLayoutX() + movementSpeedX);
                            circle1.setLayoutY(circle1.getLayoutY() + movementSpeedY);
                            killsCounter.setText("Holding time: " + (Math.round(hitsCounter*100) / 100));
                            break;
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
            timer.setText("Time: " + gameTimeValue + " sec");
            gameTimeValue--;
        }else{
            sound.musicStop();
            double accuracy;
            double kps;
            int gameModeCoefficient = 0;
            double sizeCoefficient;
            double missesCoefficient;
            double rate;
            String mark;
            isGame = false;
            killsCounter.setVisible(false);
            missesCounter.setVisible(false);
            Results.setVisible(true);
            markLabel.setVisible(true);
            AccuracyLabel1.setVisible(true);
            AccuracyLabel2.setVisible(true);
            gameModeCoefficient = switch (gameMode) {
                case 1 -> 6;
                case 2,3 -> 5;
                default -> gameModeCoefficient;
            };
            if (getInitialSize() < 20){
                sizeCoefficient = 1.1;
            }else if (getInitialSize() < 40){
                sizeCoefficient = 1;
            }else if (getInitialSize() < 60){
                sizeCoefficient = 0.8;
            }else{
                sizeCoefficient = 0.7;
            }
            if (missCounter == 0){
                missesCoefficient = 1.05;
            }else if (missCounter <= 5){
                missesCoefficient = 1;
            }else if (missCounter <= 10){
                missesCoefficient = 0.9;
            }else if (missCounter <= 20){
                missesCoefficient = 0.8;
            }else if (missCounter <= 30){
                missesCoefficient = 0.7;
            }else if (missCounter <= 50){
                missesCoefficient = 0.6;
            }else {
                missesCoefficient = 0.5;
            }
            if (missCounter == 0) {
                if (gameMode == 4) {
                    accuracy = (double) Math.round((hitsCounter * 100) / timeValue);
                }else{
                accuracy = hitsCounter * 100;
                }
            } else{
                accuracy = (double) Math.round((hitsCounter * 100) / missCounter);
            }
            if (gameMode == 4){
                AccuracyLabel2.setText((Math.round(hitsCounter * 100) / 100) + "/" + timeValue + "   (" + (accuracy / 100) + ")");
                rate = (double) Math.round(sizeCoefficient * accuracy * 120) /100;
            }else{
                AccuracyLabel2.setText(Math.round(hitsCounter) + "/" + missCounter + "   (" + (accuracy / 100) + ")");
                kps = (double) Math.round((hitsCounter/timeValue) * 100) / 100;
                kpsLabel1.setVisible(true);
                kpsLabel2.setVisible(true);
                kpsLabel2.setText(String.valueOf(kps));
                rate = (double) Math.round(kps * sizeCoefficient * gameModeCoefficient * missesCoefficient * 1200) /100;
            }
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
        killsCounter.setText("Kills: " + Math.round(hitsCounter));
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
        warningPane.setOpacity(1);
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
        timer.setText("Time: " + timeValue + " sec");
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
        if (timeValue <= 30) {
            sound.musicPlay("src/main/resources/com/example/aimtrener/music/579560_Corrosive.mp3");
        }else if (timeValue <= 60){
            sound.musicPlay("src/main/resources/com/example/aimtrener/music/574484_F-777---Sonic-Blaster.mp3");
        }else if (timeValue <= 120){
            sound.musicPlay("src/main/resources/com/example/aimtrener/music/478048_Heartbeat-Extended.mp3");
        }else if (timeValue <= 180){
            sound.musicPlay("src/main/resources/com/example/aimtrener/music/739991_Creo---Sphere.mp3");
        }else{
            sound.musicPlay("src/main/resources/com/example/aimtrener/music/827836_A-New-Dawn.mp3");
        }
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

        circle1.setVisible(true);
        circle1.setLayoutX(random.nextInt(75, 1845));
        circle1.setLayoutY(random.nextInt(75, 950));

        if (gameMode == 4){
            missesCounter.setVisible(false);
            aimDotX = random.nextInt(75, 1845);
            aimDotY = random.nextInt(75, 950);
            movementSpeedX = (aimDotX - circle1.getLayoutX()) / random.nextInt(60,300);
            movementSpeedY = (aimDotY - circle1.getLayoutY()) / random.nextInt(60,300);
            killsCounter.setText("Holding time:");
        }else{
            missesCounter.setVisible(true);
        }
        aimDotX = random.nextInt(75, 1845);
        aimDotY = random.nextInt(75, 950);
        movementSpeedX = (aimDotX - circle1.getLayoutX()) / random.nextInt(60,300);
        movementSpeedY = (aimDotY - circle1.getLayoutY()) / random.nextInt(60,300);
        circle1.setRadius(getInitialSize());
        circle2.setRadius(getInitialSize());
        circle3.setRadius(getInitialSize());
        gameTimeValue = timeValue;
        timelineGame.setCycleCount(gameTimeValue--);

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

    public void notAim1() {
        isAimed1 = false;
        circle1.setFill(Color.DODGERBLUE);
    }

    public void aim2() {
        if (!AimController.isPaused)
            isAimed2 = true;
    }

    public void notAim2() {
        isAimed2 = false;
    }

    public void notAim3() {
        isAimed3 = false;
    }

    public void aim3() {
        if (!AimController.isPaused)
            isAimed3 = true;
    }

    public void shoot3() {
        if (isAimed3){
            isHit3 = true;
        }
    }

    public void shoot2() {
        if (isAimed2){
            isHit2 = true;
        }
    }

    public void shoot1() {
        if (isAimed1 && gameMode != 4){
            isHit1 = true;
        }
    }
}
