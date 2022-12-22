package com.hoshino.hoshinoscene.tools;

import com.hoshino.hoshinoscene.HoshinoApp;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Objects;

public class GenWarehouse extends Stage {
    public GenWarehouse() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(GenWarehouse.class.getResource("pages/gen.fxml"));
        Scene sc = new Scene(fxmlLoader.load());
        GenController gc = fxmlLoader.getController();
        gc.init(this);
        this.setScene(sc);
        this.setTitle("Hoshino");
        this.setResizable(false);
        this.setAlwaysOnTop(true);
        this.getIcons().add(new Image(Objects.requireNonNull(HoshinoApp.class.getResourceAsStream("icons/little.png"))));
    }
}
