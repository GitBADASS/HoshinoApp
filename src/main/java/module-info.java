module com.hoshino.hoshinoscene {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.alibaba.fastjson2;


    opens com.hoshino.hoshinoscene to javafx.fxml;
    opens com.hoshino.hoshinoscene.tools to javafx.fxml, com.alibaba.fastjson2;
    exports com.hoshino.hoshinoscene;
}