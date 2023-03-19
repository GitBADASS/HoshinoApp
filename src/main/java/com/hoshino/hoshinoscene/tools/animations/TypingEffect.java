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
    private String text = "Hello, World! Typing Effect Demo";
    private Label label = new Label("");

    @Override
    public void start(Stage primaryStage) throws Exception {
        StackPane root = new StackPane();
        root.getChildren().add(label);

        Scene scene = new Scene(root, 800, 600);

        primaryStage.setTitle("Typing Effect Demo");
        primaryStage.setScene(scene);
        primaryStage.show();
        final int[] e = {0};

        Timeline in = new Timeline(new KeyFrame(Duration.millis(80), event -> {
            if (counter <= text.length()) {
                //label.setStyle("-fx-background-color: black; -fx-text-fill: white");
                if (e[0] == 0) {
                    label.setText(text.substring(0, counter) + "_");
                    e[0] = 5;
                } else {
                    label.setText(text.substring(0, counter));
                    e[0] --;
                }
                counter++;
            }
        }));
        /*
        消失：
        counter = text.length();
        Timeline in = new Timeline(new KeyFrame(Duration.millis(80), event -> {
            if (counter <= text.length()) {
                label.setText(text.substring(0, counter));
                counter--;
            }
        }));*/
        in.setCycleCount(text.length() + 1);
        in.play();
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void addEffect(String text, Label targetLabel) {

    }
}
