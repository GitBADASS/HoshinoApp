module com.hoshino.hoshinoscene {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.alibaba.fastjson2;


    opens com.hoshino.hoshinoscene to javafx.fxml, com.alibaba.fastjson2;
    opens com.hoshino.hoshinoscene.tools to javafx.fxml, com.alibaba.fastjson2;
    exports com.hoshino.hoshinoscene;
    exports com.hoshino.hoshinoscene.tools;
    exports com.hoshino.hoshinoscene.controllers;
    opens com.hoshino.hoshinoscene.controllers to com.alibaba.fastjson2, javafx.fxml;
}