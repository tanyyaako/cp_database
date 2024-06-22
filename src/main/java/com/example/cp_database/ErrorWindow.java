package com.example.cp_database;

import javafx.animation.PauseTransition;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class ErrorWindow {
    public static void showError(Text text){
        text.setVisible(true);
        PauseTransition visiblePause = new PauseTransition(Duration.seconds(5));
        visiblePause.setOnFinished(event -> text.setVisible(false));
        visiblePause.play();
    }
}
