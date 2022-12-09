module com.hoshino.hoshinoscene {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.hoshino.hoshinoscene to javafx.fxml;
    exports com.hoshino.hoshinoscene;
}