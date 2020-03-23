package com.oneightwo.accounting4goods.controller;

import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.stereotype.Component;

@Component
@FxmlView("node.fxml")
public class Node {

    @FXML
    public void initialize() {
        Text text = new Text("Hello from JavaFX!");
        text.setLayoutY(80);    // установка положения надписи по оси Y
        text.setLayoutX(100);   // установка положения надписи по оси X

        Group group = new Group(text);

        Scene scene = new Scene(group);
        Stage stage = new Stage();
        stage.show();
    }

}
