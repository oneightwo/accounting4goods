package com.oneightwo.accounting4goods.controller;

import com.oneightwo.accounting4goods.constants.Constants;
import com.oneightwo.accounting4goods.controller.tabs.ProductTabController;
import com.oneightwo.accounting4goods.controller.tabs.SaleTabController;
import com.oneightwo.accounting4goods.model.ActiveUser;
import com.oneightwo.accounting4goods.model.Product;
import javafx.collections.ObservableList;
import javafx.event.EventDispatchChain;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import net.rgielen.fxweaver.core.FxWeaver;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import java.io.IOException;


@Component
@FxmlView("main.fxml")
public class MainController {

    @Autowired
    private FxWeaver fxWeaver;

    @Autowired
    private ActiveUser activeUser;

    @FXML
    private TabPane container_tp;

    @FXML
    private Tab product_t;

    @FXML
    private Button sign_out_b;

    @FXML
    private SaleTabController saleTab;

    @FXML
    private ProductTabController productTab = new ProductTabController();

    private ObservableList<Product> productObservableList;
    private ConfigurableApplicationContext applicationContext;

    @FXML
    public void initialize() throws IOException {
        if (activeUser.getActiveUserRole().equals(Constants.ROLE_WORKER)) {
            container_tp.getTabs().remove(product_t);
        }
        System.out.println(container_tp.getSelectionModel().getSelectedItem().getId());
        sign_out_b.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> {
            Stage stage = (Stage) sign_out_b.getScene().getWindow();
            Parent parent = fxWeaver.loadView(LoginController.class);
            stage.setScene(new Scene(parent));
            stage.show();
        });
    }
}
