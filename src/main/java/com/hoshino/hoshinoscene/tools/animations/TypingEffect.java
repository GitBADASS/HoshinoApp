package com.hoshino.hoshinoscene.tools.animations;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class TypingEffect extends Application implements TextEffect {

    private int counter = 0;
    private String text = "Hello, World!";
    private Label label = new Label("");

    @Override
    public void start(Stage primaryStage) throws Exception {
        StackPane root = new StackPane();
        root.getChildren().add(label);

        Scene scene = new Scene(root, 300, 250);

        primaryStage.setTitle("Typing Effect Demo");
        primaryStage.setScene(scene);
        primaryStage.show();

        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(80), event -> {
            if (counter <= text.length()) {
                label.setText(text.substring(0, counter));
                counter++;
            }
        }));
        timeline.setCycleCount(text.length() + 1);
        timeline.play();
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void addEffect(String text, Label targetLabel) {

    }
}
