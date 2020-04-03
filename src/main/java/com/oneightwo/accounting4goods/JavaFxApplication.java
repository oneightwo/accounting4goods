package com.oneightwo.accounting4goods;

import com.oneightwo.accounting4goods.constants.Constants;
import com.oneightwo.accounting4goods.controller.FirstSignInController;
import com.oneightwo.accounting4goods.controller.LoginController;
import com.oneightwo.accounting4goods.controller.MainController;
import com.oneightwo.accounting4goods.service.RoleService;
import com.oneightwo.accounting4goods.service.UserService;
import com.oneightwo.accounting4goods.service.impl.RoleServiceImpl;
import com.oneightwo.accounting4goods.service.impl.UserServiceImpl;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import net.rgielen.fxweaver.core.FxWeaver;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

public class JavaFxApplication extends Application {

    private ConfigurableApplicationContext context;

    @Override
    public void init() throws Exception {
        this.context = new SpringApplicationBuilder()
                .sources(Accounting4GoodsApplication.class)
                .run(getParameters().getRaw().toArray(new String[0]));
    }

    @Override
    public void start(Stage stage) throws Exception {
        UserService userService = context.getBean(UserServiceImpl.class);
        RoleService roleService = context.getBean(RoleServiceImpl.class);
        FxWeaver fxWeaver = context.getBean(FxWeaver.class);
        Parent root;
        System.out.println(userService.getUserByRole(roleService.getRoleByString(Constants.ROLE_ADMIN)).isPresent());
        if (userService.getUserByRole(roleService.getRoleByString(Constants.ROLE_ADMIN)).isPresent()) {
//            root = fxWeaver.loadView(MainController.class);
            root = fxWeaver.loadView(LoginController.class);
//            root = fxWeaver.loadView(AddProductController.class);
        } else {
            root = fxWeaver.loadView(FirstSignInController.class);
        }
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void stop() throws Exception {
        this.context.close();
        Platform.exit();
    }

}
