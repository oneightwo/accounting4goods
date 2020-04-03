package com.oneightwo.accounting4goods.controller;

import com.oneightwo.accounting4goods.model.Type;
import com.oneightwo.accounting4goods.service.TypeService;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
@FxmlView("add_type.fxml")
public class AddTypeController {

    @Autowired
    private TypeService typeService;

    @FXML
    private TextField name_tf;

    @FXML
    private Button save_b;

    @FXML
    private Button cancel_b;

    @FXML
    private void initialize() {
        save_b.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> {
            String name = name_tf.getText();
            if (!name.isEmpty()) {
                typeService.save(new Type(name_tf.getText()));
                Stage stage = (Stage) save_b.getScene().getWindow();
                stage.close();
            }
        });
        cancel_b.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> {
            Stage stage = (Stage) cancel_b.getScene().getWindow();
            stage.close();
        });
    }

}
