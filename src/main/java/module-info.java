module com.hoshino.hoshinoscene {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.hoshino.hoshinoscene to javafx.fxml;
    opens com.hoshino.hoshinoscene.tools to javafx.fxml;
    exports com.hoshino.hoshinoscene;
}