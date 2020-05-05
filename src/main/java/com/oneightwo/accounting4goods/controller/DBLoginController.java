package com.oneightwo.accounting4goods.controller;

import com.oneightwo.accounting4goods.service.ApplicationPropertiesService;
import com.oneightwo.accounting4goods.service.RoleService;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import net.rgielen.fxweaver.core.FxWeaver;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@FxmlView("db_login.fxml")
public class DBLoginController {

    @Autowired
    private FxWeaver fxWeaver;

    @Autowired
    private RoleService roleService;
    @FXML
    private VBox container_vb;

    @FXML
    private TextField login_l;

    @FXML
    private TextField password_l;

    @FXML
    private Button sign_in_b;

    @Autowired
    private ApplicationPropertiesService applicationPropertiesService;

    @FXML
    public void initialize() {
        sign_in_b.addEventHandler(MouseEvent.MOUSE_CLICKED, (e -> {
            applicationPropertiesService.writeUserAndPassword(login_l.getText(), password_l.getText());
            roleService.baseInsert();
            update();
        }));
    }

    private void update() {
        Stage stage = (Stage) sign_in_b.getScene().getWindow();
        Parent parent = fxWeaver.loadView(FirstSignInController.class);
        stage.setScene(new Scene(parent));
        stage.show();
    }


}
