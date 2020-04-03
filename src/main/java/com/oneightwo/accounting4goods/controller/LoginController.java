package com.oneightwo.accounting4goods.controller;

import com.oneightwo.accounting4goods.constants.Constants;
import com.oneightwo.accounting4goods.model.ActiveUser;
import com.oneightwo.accounting4goods.model.User;
import com.oneightwo.accounting4goods.service.UserService;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import net.rgielen.fxweaver.core.FxWeaver;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
@FxmlView("login.fxml")
public class LoginController {

    @Autowired
    private UserService userService;

    @Autowired
    private FxWeaver fxWeaver;

    @Autowired
    private ActiveUser activeUser;

    @FXML
    private VBox container_vb;

    @FXML
    private TextField login_l;

    @FXML
    private TextField password_l;

    @FXML
    private Button sign_in_b;


    @FXML
    public void initialize() {
        sign_in_b.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> {
            Optional<User> user = userService.getByUsernameAndPassword(login_l.getText(), password_l.getText());
            if (user.isPresent()) {
                activeUser.setActiveUser(user.get());
                Stage stage = (Stage) sign_in_b.getScene().getWindow();
                Parent parent = fxWeaver.loadView(MainController.class);
                stage.setScene(new Scene(parent));
                stage.show();
            } else {
                clear();
                showAlert();
            }
        });
    }

    private void clear() {
        List<TextField> textFieldList = Arrays.asList(login_l, password_l);
        textFieldList.forEach(v -> {
            v.setText("");
        });
    }

    private void showAlert() {
        Alert alert = new Alert(Alert.AlertType.ERROR, Constants.MSG_LOGIN_ERROR, ButtonType.OK);
        alert.showAndWait();
    }
}
