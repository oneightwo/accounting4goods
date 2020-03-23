package com.oneightwo.accounting4goods.controller;

import com.oneightwo.accounting4goods.constants.Constants;
import com.oneightwo.accounting4goods.model.Role;
import com.oneightwo.accounting4goods.model.User;
import com.oneightwo.accounting4goods.service.RoleService;
import com.oneightwo.accounting4goods.service.UserService;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import net.rgielen.fxweaver.core.FxWeaver;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;


@Component
@FxmlView("first_sign_in.fxml")
public class FirstSignInController {

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserService userService;

    @FXML
    private Label label_registration_l;

    @FXML
    private Label surname_l;

    @FXML
    private TextField surname_tf;

    @FXML
    private Label name_l;

    @FXML
    private TextField name_tf;

    @FXML
    private Label patronymic_l;

    @FXML
    private TextField patronymic_tf;

    @FXML
    private Label login_l;

    @FXML
    private TextField login_tf;

    @FXML
    private Label password_first_l;

    @FXML
    private TextField password_first_tf;

    @FXML
    private Label password_second_l;

    @FXML
    private TextField password_second_tf;

    @FXML
    private Button finish_registration_b;

    @FXML
    private Button save_b;

    private Role role;

    @Autowired
    FxWeaver fxWeaver;

    @FXML
    public void initialize() {

        init();
//        System.out.println(roleService.getRoleByString(Constants.ROLE_ADMIN));
        save_b.addEventHandler(MouseEvent.MOUSE_CLICKED, (e -> {
            fxWeaver.loadController(LoginController.class);
                    if (isСontainsUser()) {
                        showAlert();
                        clear();
                    } else {
                        if (checkValidation()) {
                            saveUser(role);
                            clear();
                            init();
                        }
                    }
                })
        );

        finish_registration_b.addEventHandler(MouseEvent.MOUSE_CLICKED, (e -> {

        }));
    }

    private void init() {
        if (userService.getUserByRole(roleService.getRoleByString(Constants.ROLE_ADMIN)).isPresent()) {
            label_registration_l.setText(Constants.LABEL_ACCOUNT_WORKER);
            role = roleService.getRoleByString(Constants.ROLE_WORKER);
        } else {
            label_registration_l.setText(Constants.LABEL_ACCOUNT_ADMINISTRATOR);
            role = roleService.getRoleByString(Constants.ROLE_ADMIN);
        }
    }

    private boolean isСontainsUser() {
        return userService.getUserByFullName(surname_tf.getText(), name_tf.getText(), patronymic_tf.getText()).isPresent();
    }

    private void saveUser(Role role) {
        userService.save(new User(
                surname_tf.getText(),
                name_tf.getText(),
                patronymic_tf.getText(),
                login_tf.getText(),
                password_first_tf.getText(),
                role
        ));
    }

    private void clear() {
        List<TextField> textFieldList = Arrays.asList(surname_tf, name_tf, patronymic_tf, login_tf, password_first_tf, password_second_tf);
        textFieldList.forEach(v -> {
            v.setText("");
            v.setPromptText("");
        });
    }

    private void showAlert() {
        Alert alert = new Alert(Alert.AlertType.ERROR, Constants.MSG_USER_ALREADY_EXIST, ButtonType.OK);
        alert.showAndWait();
    }

    private boolean checkValidation() {
        boolean isValid = true;
        List<TextField> textFieldList = Arrays.asList(surname_tf, name_tf, patronymic_tf, login_tf);

        for (TextField textField : textFieldList) {
            if (textField.getText().length() < 2) {
                textField.setPromptText(Constants.MSG_MIN_LENGTH);
                isValid = false;
            }
        }
        return isValid;
    }
}
