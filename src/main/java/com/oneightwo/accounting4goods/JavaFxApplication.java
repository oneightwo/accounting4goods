package com.oneightwo.accounting4goods;

import com.oneightwo.accounting4goods.constants.Constants;
import com.oneightwo.accounting4goods.controller.FirstSignInController;
import com.oneightwo.accounting4goods.controller.LoginController;
import com.oneightwo.accounting4goods.controller.Node;
import com.oneightwo.accounting4goods.service.RoleService;
import com.oneightwo.accounting4goods.service.UserService;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import net.rgielen.fxweaver.core.FxWeaver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

public class JavaFxApplication extends Application {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    private ConfigurableApplicationContext applicationContext;


    @Override
    public void init() {
        String[] args = getParameters().getRaw().toArray(new String[0]);
        this.applicationContext = new SpringApplicationBuilder()
                .sources(Accounting4GoodsApplication.class)
                .run(args);

    }



    @Override
    public void start(Stage stage) {
        FxWeaver fxWeaver = applicationContext.getBean(FxWeaver.class);

        Parent root;
//        if (firstEntrance) {
//            root = fxWeaver.loadView(LoginController.class);
//        } else {
            root = fxWeaver.loadView(FirstSignInController.class);
//        }
//        if (userService.getUserByRole(roleService.getRoleByString(Constants.ROLE_ADMIN)).isPresent()) {
//             root = fxWeaver.loadView(LoginController.class);
//        } else {
//        root = fxWeaver.loadView(FirstSignInController.class);
//        }
//        System.out.println(roleService.getRoleByString(Constants.ROLE_ADMIN));
//        System.out.println(userService.getUserByRole(roleService.getRoleByString(Constants.ROLE_ADMIN)).isPresent());
        Scene scene = new Scene(root);
        stage.getIcons().add(new Image("/images/Br8QXIKBF3s.jpg"));
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }

    @Override
    public void stop() {
        this.applicationContext.close();
        Platform.exit();
    }

}
