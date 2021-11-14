package Task3;

import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import java.text.DecimalFormat;
import java.time.LocalTime;
import java.util.concurrent.atomic.AtomicInteger;

public class Task3 extends Application {
    private static final String PANEL_STYLE = "-fx-border-radius: 10px;" +
            "-fx-border-width: 3px;" +
            "-fx-border-color: #ffffff;" +
            "-fx-font-family: Arial;";

    private static final String PANEL_HEADER_STYLE = "-fx-background-color: #453223;" +
            "-fx-padding: 5px;" +
            "-fx-font-weight: bold;" +
            "-fx-font-family: Arial;";

    private DecimalFormat temperatureFormat = new DecimalFormat("##.0");

    private Label timerLabel;
    private Label[][] lightDisplays = new Label[3][6];
    private static final String[] lightDisplayColours = new String[]{"#656600", "#999a01", "#cdcc00", "#ffff01", "#feff99", "#feff99"};

    @Override
    public void start(Stage primaryStage) {
        Pane rootpane = new Pane();

        VBox container = new VBox();
        container.setPrefWidth(1280);
        container.setPrefHeight(720);
        container.setStyle("-fx-background-color: #453223");

        rootpane.getChildren().add(container);

        /*
         * ROW 1
         */
        HBox row1HBox = new HBox();
        row1HBox.setPrefWidth(1280);
        row1HBox.setPrefHeight(240);
        row1HBox.setMaxHeight(240);

        /*
         * Day Time Clock
         */
        HBox dayTimeClockHBox = new HBox();

        // styling
        dayTimeClockHBox.setPrefWidth(540);
        HBox.setMargin(dayTimeClockHBox, new Insets(20));
        dayTimeClockHBox.setStyle(PANEL_STYLE);
        dayTimeClockHBox.setAlignment(Pos.CENTER);

        // label heading
        Label dayTimeClockLabel = new Label("Day Time Clock");
        dayTimeClockLabel.setLayoutX(35);
        dayTimeClockLabel.setLayoutY(7);
        dayTimeClockLabel.setStyle(PANEL_HEADER_STYLE);
        dayTimeClockLabel.setTextFill(Color.web("#ffffff"));
        dayTimeClockLabel.setLineSpacing(10);
        rootpane.getChildren().add(dayTimeClockLabel);

        // content
        Label timeLabel = new Label("hh : mm : ss");
        timeLabel.setTextFill(Color.web("#00ff00"));
        timeLabel.setFont(new Font(90));
        dayTimeClockHBox.getChildren().add(timeLabel);

        /*
         * Elapsed Time
         */
        HBox elapsedTimeHBox = new HBox();

        // styling
        elapsedTimeHBox.setPrefWidth(740);
        HBox.setMargin(elapsedTimeHBox, new Insets(20));
        elapsedTimeHBox.setStyle(PANEL_STYLE);
        elapsedTimeHBox.setAlignment(Pos.CENTER);

        // label heading
        Label elapsedTimeLabel = new Label("Elapsed Time");
        elapsedTimeLabel.setLayoutX(575);
        elapsedTimeLabel.setLayoutY(7);
        elapsedTimeLabel.setStyle(PANEL_HEADER_STYLE);
        elapsedTimeLabel.setTextFill(Color.web("#ffffff"));
        elapsedTimeLabel.setLineSpacing(10);
        rootpane.getChildren().add(elapsedTimeLabel);

        // content
        timerLabel = new Label("00 : 00 : 00");
        timerLabel.setTextFill(Color.web("#ff0000"));
        timerLabel.setFont(new Font(90));
        timerLabel.setStyle("-fx-font-family: Arial;");

        VBox timerButtonsVBox = new VBox(5);
        timerButtonsVBox.setAlignment(Pos.CENTER);
        timerButtonsVBox.setPadding(new Insets(0, 0, 0, 50));

        Button startTimer = new Button("START");
        Button stopTimer = new Button("STOP");
        Button resetTimer = new Button("RESET");

        String timerButtonStyle = "-fx-background-color: #0000ff;" +
                "-fx-border-width: 2px;" +
                "-fx-border-color: #ffffff;" +
                "-fx-border-radius: 5px;" +
                "-fx-background-radius: 5px;" +
                "-fx-text-fill: #ffffff;" +
                "-fx-font-size: 20;" +
                "-fx-font-weight: bold;" +
                "-fx-background-insets: 1;" +
                "-fx-font-family: Arial;";
        startTimer.setStyle(timerButtonStyle);
        stopTimer.setStyle(timerButtonStyle);
        resetTimer.setStyle(timerButtonStyle);

        startTimer.setPrefWidth(100);
        startTimer.setPrefHeight(40);
        stopTimer.setPrefWidth(100);
        stopTimer.setPrefHeight(40);
        resetTimer.setPrefWidth(100);
        resetTimer.setPrefHeight(40);

        timerButtonsVBox.getChildren().addAll(startTimer, stopTimer, resetTimer);

        elapsedTimeHBox.getChildren().addAll(timerLabel, timerButtonsVBox);

        row1HBox.getChildren().addAll(dayTimeClockHBox, elapsedTimeHBox);

        /*
         * ROW 2
         */
        HBox row2HBox = new HBox();
        row2HBox.setPrefWidth(1280);
        row2HBox.setPrefHeight(240);
        row2HBox.setMaxHeight(240);

        /*
         * Temperature Control
         */
        HBox temperatureControlHBox = new HBox();

        // styling
        temperatureControlHBox.setPrefWidth(426);
        HBox.setMargin(temperatureControlHBox, new Insets(20));
        temperatureControlHBox.setStyle(PANEL_STYLE);
        temperatureControlHBox.setAlignment(Pos.CENTER);

        // label heading
        Label temperatureControlLabel = new Label("Temperature Control");
        temperatureControlLabel.setLayoutX(35);
        temperatureControlLabel.setLayoutY(247);
        temperatureControlLabel.setStyle(PANEL_HEADER_STYLE);
        temperatureControlLabel.setTextFill(Color.web("#ffffff"));
        temperatureControlLabel.setLineSpacing(10);
        rootpane.getChildren().add(temperatureControlLabel);

        // content
        VBox tempControlVBox = new VBox(10);
        tempControlVBox.setPadding(new Insets(20));

        // row 1
        HBox tempControlRow1HBox = new HBox(10);
        tempControlRow1HBox.setAlignment(Pos.CENTER);
        
        Image tempControlImage;
        tempControlImage = new Image(getClass().getResource("/images/temperature_control.png").toExternalForm(), 75, 75, true, true);
        ImageView tempControlImageView = new ImageView(tempControlImage);
        tempControlImageView.setCache(true);
        
        
        
        Label temperatureLabel = new Label("20.0");
        temperatureLabel.setStyle("-fx-text-fill: #ff0000;" +
                "-fx-font-size: 60;" +
                "-fx-font-family: Arial;");
        Label temperatureMeasurementLabel = new Label("°C");
        temperatureMeasurementLabel.setStyle("-fx-text-fill: #ffffff;" +
                "-fx-font-size: 30;" +
                "-fx-font-family: Arial;");

        tempControlRow1HBox.getChildren().addAll(tempControlImageView, temperatureLabel, temperatureMeasurementLabel);

        // row 2
        HBox tempControlRow2HBox = new HBox(30);
        tempControlRow2HBox.setAlignment(Pos.CENTER);

        Button tempUpButton = new Button(Character.toString((char) 11205));
        Button tempDownButton = new Button(Character.toString((char) 11206));

        String yellowButtonArrowStyle = "-fx-background-color: #FFFB00;" +
                "-fx-border-width: 2;" +
                "-fx-border-color: #000000;" +
                "-fx-border-radius: 5;" +
                "-fx-background-radius: 5;" +
                "-fx-text-fill: #000000;" +
                "-fx-font-size: 20;" +
                "-fx-font-weight: bold;" +
                "-fx-background-insets: 1;";

        tempUpButton.setStyle(yellowButtonArrowStyle);
        tempDownButton.setStyle(yellowButtonArrowStyle);

        tempControlRow2HBox.getChildren().addAll(tempUpButton, tempDownButton);

        tempControlVBox.getChildren().addAll(tempControlRow1HBox, tempControlRow2HBox);
        temperatureControlHBox.getChildren().add(tempControlVBox);


        /*
         * Humidity Control
         */
        HBox humidityControlHBox = new HBox();

        // styling
        humidityControlHBox.setPrefWidth(426);
        HBox.setMargin(humidityControlHBox, new Insets(20));
        humidityControlHBox.setStyle(PANEL_STYLE);
        humidityControlHBox.setAlignment(Pos.CENTER);

        // label heading
        Label humidityControlLabel = new Label("Humidity Control");
        humidityControlLabel.setLayoutX(462);
        humidityControlLabel.setLayoutY(247);
        humidityControlLabel.setStyle(PANEL_HEADER_STYLE);
        humidityControlLabel.setTextFill(Color.web("#ffffff"));
        humidityControlLabel.setLineSpacing(10);
        rootpane.getChildren().add(humidityControlLabel);

        // content
        VBox humidityControlVBox = new VBox(10);
        humidityControlVBox.setPadding(new Insets(20));

        // row 1
        HBox humidityControlRow1HBox = new HBox(50);
        humidityControlRow1HBox.setAlignment(Pos.CENTER);

        Image humidityControlImage;
        humidityControlImage = new Image(getClass().getResource("/images/humidity_control.png").toExternalForm(), 75, 75, true, true);
        ImageView humidityControlImageView = new ImageView(humidityControlImage);
        humidityControlImageView.setCache(true);

        Label humidityLabel = new Label("40");
        humidityLabel.setStyle("-fx-text-fill: #ff0000;" +
                "-fx-font-size: 60;" +
                "-fx-font-family: Arial;");
        Label humidityMeasurementLabel = new Label("%");
        humidityMeasurementLabel.setStyle("-fx-text-fill: #ffffff;" +
                "-fx-font-size: 30;" +
                "-fx-font-family: Arial;");

        humidityControlRow1HBox.getChildren().addAll(humidityControlImageView, humidityLabel, humidityMeasurementLabel);

        // row 2
        HBox humidityControlRow2HBox = new HBox(30);
        humidityControlRow2HBox.setAlignment(Pos.CENTER);

        Button humidityUpButton = new Button(Character.toString((char) 11205));
        Button humidityDownButton = new Button(Character.toString((char) 11206));

        humidityUpButton.setStyle(yellowButtonArrowStyle);
        humidityDownButton.setStyle(yellowButtonArrowStyle);

        humidityControlRow2HBox.getChildren().addAll(humidityUpButton, humidityDownButton);
        humidityControlVBox.getChildren().addAll(humidityControlRow1HBox, humidityControlRow2HBox);
        humidityControlHBox.getChildren().add(humidityControlVBox);

        /*
         * Pressure Control
         */
        HBox pressureControlHBox = new HBox();

        // styling
        pressureControlHBox.setPrefWidth(426);
        HBox.setMargin(pressureControlHBox, new Insets(20));
        pressureControlHBox.setStyle(PANEL_STYLE);
        pressureControlHBox.setAlignment(Pos.CENTER);

        // label heading
        Label pressureControlLabel = new Label("Pressure Control");
        pressureControlLabel.setLayoutX(888);
        pressureControlLabel.setLayoutY(247);
        pressureControlLabel.setStyle(PANEL_HEADER_STYLE);
        pressureControlLabel.setTextFill(Color.web("#ffffff"));
        pressureControlLabel.setLineSpacing(10);
        rootpane.getChildren().add(pressureControlLabel);

        // content
        VBox pressureControlVBox = new VBox(10);
        pressureControlVBox.setPadding(new Insets(20));

        // row 1
        HBox pressureControlRow1HBox = new HBox(50);
        pressureControlRow1HBox.setAlignment(Pos.CENTER);

        Image pressureControlImage;
        pressureControlImage = new Image(getClass().getResource("/images/pressure_control.png").toExternalForm(), 75, 75, true, true);
        ImageView pressureControlImageView = new ImageView(pressureControlImage);
        pressureControlImageView.setCache(true);

        Label pressureLabel = new Label("50");
        pressureLabel.setStyle("-fx-text-fill: #ff0000;" +
                "-fx-font-size: 60;" +
                "-fx-font-family: Arial;");
        pressureLabel.setPrefWidth(100);
        pressureLabel.setAlignment(Pos.CENTER);
        Label pressureMeasurementLabel = new Label("kPa");
        pressureMeasurementLabel.setStyle("-fx-text-fill: #ffffff;" +
                "-fx-font-size: 30;" +
                "-fx-font-family: Arial;");

        pressureControlRow1HBox.getChildren().addAll(pressureControlImageView, pressureLabel, pressureMeasurementLabel);

        // row 2
        HBox pressureControlRow2HBox = new HBox(30);
        pressureControlRow2HBox.setAlignment(Pos.CENTER);

        Button pressureUpButton = new Button(Character.toString((char) 11205));
        Button pressureDownButton = new Button(Character.toString((char) 11206));

        pressureUpButton.setStyle(yellowButtonArrowStyle);
        pressureDownButton.setStyle(yellowButtonArrowStyle);

        pressureControlRow2HBox.getChildren().addAll(pressureUpButton, pressureDownButton);
        pressureControlVBox.getChildren().addAll(pressureControlRow1HBox, pressureControlRow2HBox);
        pressureControlHBox.getChildren().add(pressureControlVBox);


        row2HBox.getChildren().addAll(temperatureControlHBox, humidityControlHBox, pressureControlHBox);

        /*
         * ROW 3
         */
        HBox row3HBox = new HBox();
        row3HBox.setPrefWidth(1280);
        row3HBox.setPrefHeight(240);
        row3HBox.setMaxHeight(240);

        /*
         * Light Control
         */
        HBox lightControlHBox = new HBox();

        // styling
        lightControlHBox.setPrefWidth(640);
        HBox.setMargin(lightControlHBox, new Insets(20));
        lightControlHBox.setPadding(new Insets(20, 0, 0, 0));
        lightControlHBox.setStyle(PANEL_STYLE);
        lightControlHBox.setAlignment(Pos.CENTER);

        // label heading
        Label lightControlLabel = new Label("Light Control");
        lightControlLabel.setLayoutX(35);
        lightControlLabel.setLayoutY(487);
        lightControlLabel.setStyle(PANEL_HEADER_STYLE);
        lightControlLabel.setTextFill(Color.web("#ffffff"));
        lightControlLabel.setLineSpacing(10);
        rootpane.getChildren().add(lightControlLabel);

        // content
        VBox lightControlVBox = new VBox(5);
        HBox lightControlRow1HBox = new HBox(5);
        lightControlRow1HBox.setAlignment(Pos.CENTER);

        AtomicInteger row1Value = new AtomicInteger(0);

        String lightControlButtonStyle = "-fx-background-color: transparent;" +
                "-fx-border-radius: 10px;" +
                "-fx-border-width: 3px;" +
                "-fx-border-color: #ffffff;" +
                "-fx-text-fill: #ffffff;" +
                "-fx-font-weight: bold;" +
                "-fx-font-size: 20;" +
                "-fx-font-family: Arial;";
        Button row1Minus = new Button("-");
        row1Minus.setStyle(lightControlButtonStyle);
        row1Minus.setPrefWidth(60);
        row1Minus.setPrefHeight(20);

        lightControlRow1HBox.getChildren().add(row1Minus);

        String lightBoxStyle = "-fx-background-color: #222918;" +
                "-fx-background-radius: 0;";

        // add light panels
        for (int i = 0; i < 6; i++) {
            Label l = new Label();
            l.setStyle(lightBoxStyle);
            l.setPrefWidth(65);
            l.setPrefHeight(30);
            lightDisplays[0][i] = l;
            lightControlRow1HBox.getChildren().add(l);
        }

        Button row1Plus = new Button("+");
        row1Plus.setStyle(lightControlButtonStyle);
        row1Plus.setPrefWidth(60);
        row1Plus.setPrefHeight(20);

        lightControlRow1HBox.getChildren().add(row1Plus);

        // row 2
        HBox lightControlRow2HBox = new HBox(5);
        lightControlRow2HBox.setAlignment(Pos.CENTER);

        AtomicInteger row2Value = new AtomicInteger(0);

        Button row2Minus = new Button("-");
        row2Minus.setStyle(lightControlButtonStyle);
        row2Minus.setPrefWidth(60);
        row2Minus.setPrefHeight(20);

        lightControlRow2HBox.getChildren().add(row2Minus);

        // add light panels
        for (int i = 0; i < 6; i++) {
            Label l = new Label();
            l.setStyle(lightBoxStyle);
            l.setPrefWidth(65);
            l.setPrefHeight(30);
            lightDisplays[1][i] = l;
            lightControlRow2HBox.getChildren().add(l);
        }

        Button row2Plus = new Button("+");
        row2Plus.setStyle(lightControlButtonStyle);
        row2Plus.setPrefWidth(60);
        row2Plus.setPrefHeight(20);

        lightControlRow2HBox.getChildren().add(row2Plus);

        // row 3
        HBox lightControlRow3HBox = new HBox(5);
        lightControlRow3HBox.setAlignment(Pos.CENTER);

        AtomicInteger row3Value = new AtomicInteger(0);

        Button row3Minus = new Button("-");
        row3Minus.setStyle(lightControlButtonStyle);
        row3Minus.setPrefWidth(60);
        row3Minus.setPrefHeight(30);

        lightControlRow3HBox.getChildren().add(row3Minus);

        // add light panels
        for (int i = 0; i < 6; i++) {
            Label l = new Label();
            l.setStyle(lightBoxStyle);
            l.setPrefWidth(65);
            l.setPrefHeight(30);
            lightDisplays[2][i] = l;
            lightControlRow3HBox.getChildren().add(l);
        }

        Button row3Plus = new Button("+");
        row3Plus.setStyle(lightControlButtonStyle);
        row3Plus.setPrefWidth(60);
        row3Plus.setPrefHeight(20);

        lightControlRow3HBox.getChildren().add(row3Plus);

        lightControlVBox.getChildren().addAll(lightControlRow1HBox, lightControlRow2HBox, lightControlRow3HBox);
        lightControlHBox.getChildren().add(lightControlVBox);


        /*
         * Medical Gases
         */
        HBox medicalGasesHBox = new HBox(10);

        // styling
        medicalGasesHBox.setPrefWidth(640);
        HBox.setMargin(medicalGasesHBox, new Insets(20));
        medicalGasesHBox.setStyle(PANEL_STYLE);
        medicalGasesHBox.setAlignment(Pos.CENTER);

        // label heading
        Label medicalGaseslLabel = new Label("Medical Gases");
        medicalGaseslLabel.setLayoutX(675);
        medicalGaseslLabel.setLayoutY(487);
        medicalGaseslLabel.setStyle(PANEL_HEADER_STYLE);
        medicalGaseslLabel.setTextFill(Color.web("#ffffff"));
        medicalGaseslLabel.setLineSpacing(10);
        rootpane.getChildren().add(medicalGaseslLabel);

        String squareStyle = "-fx-text-fill: #000000;";
        String headerStyle = "-fx-text-fill: #dea034;";

        // content
        // 02
        VBox O2VBox = new VBox(5);
        O2VBox.setAlignment(Pos.CENTER);

        Label labelO2 = new Label("O2");
        labelO2.setStyle(headerStyle + "-fx-font-size: 20; -fx-font-weight: bold; -fx-font-family: Arial;");

        Label squareO2 = new Label("Low");
        squareO2.setStyle(squareStyle + "-fx-background-color: #ff4300; -fx-font-size: 20; -fx-font-family: Arial;");
        squareO2.setPrefWidth(90);
        squareO2.setPrefHeight(90);
        squareO2.setAlignment(Pos.CENTER);

        O2VBox.getChildren().addAll(labelO2, squareO2);

        // N20
        VBox N20VBox = new VBox(5);
        N20VBox.setAlignment(Pos.CENTER);

        Label labelN20 = new Label("N20");
        labelN20.setStyle(headerStyle + "-fx-font-size: 20; -fx-font-weight: bold; -fx-font-family: Arial;");

        Label squareN20 = new Label("High");
        squareN20.setStyle(squareStyle + "-fx-background-color: #a8ff6c; -fx-font-size: 20; -fx-font-family: Arial;");
        squareN20.setPrefWidth(90);
        squareN20.setPrefHeight(90);
        squareN20.setAlignment(Pos.CENTER);

        N20VBox.getChildren().addAll(labelN20, squareN20);

        // AIR1
        VBox AIR1VBox = new VBox(5);
        AIR1VBox.setAlignment(Pos.CENTER);

        Label labelAIR1 = new Label("AIR1");
        labelAIR1.setStyle(headerStyle + "-fx-font-size: 20; -fx-font-weight: bold; -fx-font-family: Arial;");

        Label squareAIR1 = new Label("Norm");
        squareAIR1.setStyle(squareStyle + "-fx-background-color: #a8ff6c; -fx-font-size: 20; -fx-font-family: Arial;");
        squareAIR1.setPrefWidth(90);
        squareAIR1.setPrefHeight(90);
        squareAIR1.setAlignment(Pos.CENTER);

        AIR1VBox.getChildren().addAll(labelAIR1, squareAIR1);

        // CO2
        VBox CO2VBox = new VBox(5);
        CO2VBox.setAlignment(Pos.CENTER);

        Label labelCO2 = new Label("CO2");
        labelCO2.setStyle(headerStyle + "-fx-font-size: 20; -fx-font-weight: bold; -fx-font-family: Arial;");

        Label squareCO2 = new Label("Norm");
        squareCO2.setStyle(squareStyle + "-fx-background-color: #ff4300; -fx-font-size: 20; -fx-font-family: Arial;");
        squareCO2.setPrefWidth(90);
        squareCO2.setPrefHeight(90);
        squareCO2.setAlignment(Pos.CENTER);

        CO2VBox.getChildren().addAll(labelCO2, squareCO2);

        // VAC
        VBox VACVBox = new VBox(5);
        VACVBox.setAlignment(Pos.CENTER);

        Label labelVAC = new Label("VAC");
        labelVAC.setStyle(headerStyle + "-fx-font-size: 20; -fx-font-weight: bold; -fx-font-family: Arial;");

        Label squareVAC = new Label("High");
        squareVAC.setStyle(squareStyle + "-fx-background-color: #ff4300; -fx-font-size: 20; -fx-font-family: Arial;");
        squareVAC.setPrefWidth(90);
        squareVAC.setPrefHeight(90);
        squareVAC.setAlignment(Pos.CENTER);

        VACVBox.getChildren().addAll(labelVAC, squareVAC);


        medicalGasesHBox.getChildren().addAll(O2VBox, N20VBox, AIR1VBox, CO2VBox, VACVBox);

        row3HBox.getChildren().addAll(lightControlHBox, medicalGasesHBox);

        container.getChildren().addAll(row1HBox, row2HBox, row3HBox);

        /*
         * Event Handling
         */

        // Day Time Clock
        updateCurrentTime(timeLabel);

        // Elapsed Time
        ElapsedTimer timer = new ElapsedTimer();
        startTimer.setOnAction(event -> timer.start());
        stopTimer.setOnAction(event -> timer.stop());
        resetTimer.setOnAction(event -> timer.reset());

        // Temperature Control
        tempUpButton.setOnAction(event -> {
            String value = temperatureLabel.getText();
            double newValue = Double.parseDouble(value) + 0.1;

            if (newValue <= 27.5)
                temperatureLabel.setText(temperatureFormat.format(newValue));
        });
        tempDownButton.setOnAction(event -> {
            String value = temperatureLabel.getText();
            double newValue = Double.parseDouble(value) - 0.1;

            if (newValue >= 10.0)
                temperatureLabel.setText(temperatureFormat.format(newValue));
        });

        // Humidity Control
        humidityUpButton.setOnAction(event -> {
            String value = humidityLabel.getText();
            int newValue = Integer.parseInt(value) + 1;

            if (newValue <= 55)
                humidityLabel.setText(String.valueOf(newValue));
        });
        humidityDownButton.setOnAction(event -> {
            String value = humidityLabel.getText();
            int newValue = Integer.parseInt(value) - 1;

            if (newValue >= 30)
                humidityLabel.setText(String.valueOf(newValue));
        });

        // Pressure Control
        pressureUpButton.setOnAction(event -> {
            String value = pressureLabel.getText();
            int newValue = Integer.parseInt(value) + 1;

            if (newValue <= 120)
                pressureLabel.setText(String.valueOf(newValue));
        });
        pressureDownButton.setOnAction(event -> {
            String value = pressureLabel.getText();
            int newValue = Integer.parseInt(value) - 1;

            if (newValue >= 50)
                pressureLabel.setText(String.valueOf(newValue));
        });

        // Light Control
        // row 1
        row1Minus.setOnAction(event -> {
            if (row1Value.get() > 0) {
                row1Value.getAndDecrement();
                updateLight(row1Value.get(), 0);
            }
        });
        row1Plus.setOnAction(event -> {
            if (row1Value.get() < 6) {
                row1Value.getAndIncrement();
                updateLight(row1Value.get(), 0);
            }
        });

        // row 2
        row2Minus.setOnAction(event -> {
            if (row2Value.get() > 0) {
                row2Value.getAndDecrement();
                updateLight(row2Value.get(), 1);
            }
        });
        row2Plus.setOnAction(event -> {
            if (row2Value.get() < 6) {
                row2Value.getAndIncrement();
                updateLight(row2Value.get(), 1);
            }
        });

        // row 3
        row3Minus.setOnAction(event -> {
            if (row3Value.get() > 0) {
                row3Value.getAndDecrement();
                updateLight(row3Value.get(), 2);
            }
        });
        row3Plus.setOnAction(event -> {
            if (row3Value.get() < 6) {
                row3Value.getAndIncrement();
                updateLight(row3Value.get(), 2);
            }
        });

        // add rootpane to scene and display stage
        Scene scene = new Scene(rootpane, 1270, 710);
        primaryStage.setTitle("Surgery Control Panel");
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * Function to set the passed label to the current time.
     *
     * @param label label to set
     */
    private void updateCurrentTime(Label label) {
        // https://stackoverflow.com/questions/42383857/javafx-live-time-and-date/42384436
        Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            LocalTime currentTime = LocalTime.now();

            int hour = currentTime.getHour();
            int minute = currentTime.getMinute();
            int second = currentTime.getSecond();

            label.setText(String.format("%02d : %02d : %02d", hour, minute, second));
        }),
                new KeyFrame(Duration.seconds(1))
        );
        clock.setCycleCount(Animation.INDEFINITE);
        clock.play();
    }

    /**
     * Class to run a timer with hours, minutes and seconds.
     */
    private class ElapsedTimer extends AnimationTimer {
        // https://stackoverflow.com/questions/40821849/creating-simple-stopwatch-javafx
        private long timestamp;
        private long time = 0;
        private long fraction = 0;

        @Override
        public void start() {
            // current time adjusted by remaining time from last run
            timestamp = System.currentTimeMillis() - fraction;
            super.start();
        }

        @Override
        public void stop() {
            super.stop();
            // save leftover time
            fraction = System.currentTimeMillis() - timestamp;
        }

        public void reset() {
            // this.stop(); // uncomment this to stop timer on reset
            time = 0;
            fraction = 0;
            timerLabel.setText("00 : 00 : 00");
        }

        @Override
        public void handle(long now) {
            long newTime = System.currentTimeMillis();

            // update every second
            if (timestamp + 1000 <= newTime) {
                // calculate difference in time from last update to current time
                long deltaT = (newTime - timestamp) / 1000;

                // add difference to time
                time += deltaT;

                // update timestamp in case of stop
                timestamp += 1000 * deltaT;

                // calculate hours, minutes and seconds
                int input = (int) time;
                int hours = (input % 86400) / 3600;
                int minutes = ((input % 86400) % 3600) / 60;
                int seconds = ((input % 86400) % 3600) % 60;

                timerLabel.setText(String.format("%02d : %02d : %02d", hours, minutes, seconds));
            }
        }
    }

    /**
     * Function to update the light control panel given a value and a row.
     *
     * @param value value to set up to. 2 would turn on the first 2 lights.
     * @param row   row to set. 2 would be the bottom row.
     */
    private void updateLight(int value, int row) {
        for (int i = 1; i <= 6; i++) {
            if (i <= value)
                lightDisplays[row][i - 1].setStyle("-fx-background-color: " + lightDisplayColours[i - 1] + ";");
            else
                lightDisplays[row][i - 1].setStyle("-fx-background-color: #222918;");
        }
    }
}