module com.hoshino.hoshinoscene {
    requires javafx.controls;
    requires javafx.graphics;
    requires javafx.fxml;
    requires com.alibaba.fastjson2;


    opens com.hoshino.hoshinoscene to javafx.fxml, com.alibaba.fastjson2;
    opens com.hoshino.hoshinoscene.tools to javafx.fxml, com.alibaba.fastjson2;
    exports com.hoshino.hoshinoscene;
    exports com.hoshino.hoshinoscene.tools;
    exports com.hoshino.hoshinoscene.tools.animations;
    exports com.hoshino.hoshinoscene.controllers;
    exports com.hoshino.hoshinoscene.custom;
    opens com.hoshino.hoshinoscene.controllers to com.alibaba.fastjson2, javafx.fxml;
}