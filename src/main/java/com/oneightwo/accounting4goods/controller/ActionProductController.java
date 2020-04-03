package com.oneightwo.accounting4goods.controller;

import com.oneightwo.accounting4goods.constants.Action;
import com.oneightwo.accounting4goods.constants.Constants;
import com.oneightwo.accounting4goods.controller.tabs.ProductTabController;
import com.oneightwo.accounting4goods.controller.tabs.ReportTabController;
import com.oneightwo.accounting4goods.controller.tabs.SaleTabController;
import com.oneightwo.accounting4goods.model.Product;
import com.oneightwo.accounting4goods.service.ProductService;
import com.oneightwo.accounting4goods.service.TypeService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import net.rgielen.fxweaver.core.FxWeaver;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@FxmlView("action_product.fxml")
public class ActionProductController {

    private Action action;
    private Product product;

    public void setData(Action action, Product product) {
        this.action = action;
        this.product = product;
    }

    public void setData(Action action) {
        this.action = action;
    }

    @Autowired
    private FxWeaver fxWeaver;

    @Autowired
    private TypeService typeService;

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductTabController productTabController;

    @Autowired
    private SaleTabController saleTabController;

    @Autowired
    private ReportTabController reportTabController;

    private ObservableList<String> typeList = FXCollections.observableArrayList();

    @FXML
    private Button add_type_b;

    @FXML
    private TextField name_tf;

    @FXML
    private ComboBox<String> type_cb;

    @FXML
    private TextField count_tf;

    @FXML
    private TextField price_tf;

    @FXML
    private TextField manufacturer_еа;

    @FXML
    private TextArea description_ta;

    @FXML
    private Button delete_b;

    @FXML
    private Button save_b;

    @FXML
    private void initialize() {
        init();
        type_cb.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> {
            typeList.clear();
            typeService.getAll().forEach(v -> {
                typeList.add(v.getName());
            });
            type_cb.setItems(typeList);
        });
        add_type_b.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> {
            Stage stage = new Stage();
            Parent parent = fxWeaver.loadView(AddTypeController.class);
            stage.setScene(new Scene(parent));
            stage.setResizable(false);
            stage.show();
        });
    }

    private void init() {
        switch (action) {
            case CREATE: {
                delete_b.setText(Constants.CANCEL);
                delete_b.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> {
                    close();
                });
                save_b.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> {
                    System.out.println(type_cb.getSelectionModel().getSelectedItem());
                    if (checkValidation()) {
                        productService.save(new Product(
                                name_tf.getText(),
                                typeService.getTypeByName(type_cb.getSelectionModel().getSelectedItem()).get(),
                                description_ta.getText(),
                                Long.valueOf(count_tf.getText()),
                                Double.valueOf(price_tf.getText()),
                                manufacturer_еа.getText()
                        ));
                        clear();
                        productTabController.update();
                        saleTabController.update();
                        reportTabController.update();
                    }
                });
                break;
            }
            case EDIT: {
                delete_b.setText(Constants.DELETE);
                name_tf.setText(product.getName());
                type_cb.getSelectionModel().select(product.getType().getName());
                description_ta.setText(product.getDescription());
                count_tf.setText(product.getCount().toString());
                price_tf.setText(product.getPrice().toString());
                manufacturer_еа.setText(product.getManufacturer());
                delete_b.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> {
                    productService.delete(product);
                    close();
                });
                save_b.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> {
                    System.out.println(type_cb.getSelectionModel().getSelectedItem());
                    if (checkValidation()) {
                        productService.update(new Product(
                                product.getId(),
                                name_tf.getText(),
                                typeService.getTypeByName(type_cb.getSelectionModel().getSelectedItem()).get(),
                                description_ta.getText(),
                                Long.valueOf(count_tf.getText()),
                                Double.valueOf(price_tf.getText()),
                                manufacturer_еа.getText()
                        ));
                        productTabController.update();
                        saleTabController.update();
                        reportTabController.update();
                        clear();
                        close();
                    }
                });
                break;
            }
            case SHOW: {
                delete_b.setVisible(false);
                name_tf.setText(product.getName());
                name_tf.setEditable(false);
                type_cb.getSelectionModel().select(product.getType().getName());
                type_cb.setEditable(false);
                add_type_b.setVisible(false);
                description_ta.setText(product.getDescription());
                description_ta.setEditable(false);
                count_tf.setText(product.getCount().toString());
                count_tf.setEditable(false);
                price_tf.setText(product.getPrice().toString());
                manufacturer_еа.setText(product.getManufacturer());
                price_tf.setEditable(false);
                delete_b.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> {
                    close();
                });
                save_b.setText(Constants.CANCEL);
                save_b.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> {
                    close();
                });
            }
        }
    }

    private boolean checkValidation() {
        boolean isValid = true;
        if (name_tf.getText().isEmpty()) {
            isValid = false;
            name_tf.clear();
            name_tf.setPromptText(Constants.MSG_REQUIRED_FIELD);
        }
        try {
            if (count_tf.getText().isEmpty() || Integer.parseInt(count_tf.getText()) < 0) {
                isValid = false;
                count_tf.setPromptText(Constants.MSG_REQUIRED_FIELD);
            }
        } catch (NumberFormatException e) {
            isValid = false;
            count_tf.clear();
            count_tf.setPromptText(Constants.MSG_REQUIRED_FIELD);
        }
        try {
            if (price_tf.getText().isEmpty() || Double.parseDouble(count_tf.getText()) < 0) {
                isValid = false;
                price_tf.setPromptText(Constants.MSG_REQUIRED_FIELD);
            }
        } catch (NumberFormatException e) {
            isValid = false;
            price_tf.clear();
            price_tf.setPromptText(Constants.MSG_REQUIRED_FIELD);
        }

        if (type_cb.getSelectionModel().getSelectedItem() == null) {
            isValid = false;
        }
        return isValid;
    }

    private void clear() {
        name_tf.clear();
        name_tf.setPromptText("");
        count_tf.clear();
        count_tf.setPromptText("");
        price_tf.clear();
        price_tf.setPromptText("");
        description_ta.clear();
        manufacturer_еа.clear();
    }

    private void close() {
        Stage stage = (Stage) save_b.getScene().getWindow();
        stage.close();
    }
}
