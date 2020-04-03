package com.oneightwo.accounting4goods.controller.tabs;

import com.oneightwo.accounting4goods.controller.SalePieceProduct;
import com.oneightwo.accounting4goods.controller.listView.ProductListViewCell;
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
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import net.rgielen.fxweaver.core.FxWeaver;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Component
@FxmlView("sale_tab.fxml")
public class SaleTabController {

    @Autowired
    private FxWeaver fxWeaver;

    @Autowired
    private ProductService productService;

    @Autowired
    private SalePieceProduct salePieceProduct;

    @Autowired
    private TypeService typeService;

    private ObservableList<String> typeList = FXCollections.observableArrayList();

    private ObservableList<Product> productObservableList = FXCollections.observableArrayList();

    @FXML
    private TextField search_tf;

    @FXML
    private ComboBox<String> type_cb;

    @FXML
    private Button search_b;

    @FXML
    private ListView<Product> container_lv;

    @FXML
    public void initialize() {
        update();
        container_lv.setItems(productObservableList);
        container_lv.setCellFactory(studentListView -> new ProductListViewCell());
        container_lv.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> {
            salePieceProduct.setData(container_lv.getSelectionModel().getSelectedItem());
            Stage stage = new Stage();
            Parent parent = fxWeaver.loadView(salePieceProduct.getClass());
            Scene scene = new Scene(parent);
            stage.setScene(scene);
            stage.show();
        });
        type_cb.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> {
            typeList.clear();
            typeService.getAll().forEach(v -> {
                typeList.add(v.getName());
            });
            type_cb.setItems(typeList);
        });
        search_b.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> {
            String productName = search_tf.getText();
            String typeName = type_cb.getSelectionModel().getSelectedItem();
            if (!productName.isEmpty() && typeName != null) {
                update(productService.getFilteredProductByNameAndType(productName, typeName));
            } else {
                if (!productName.isEmpty()) {
                    update(productService.getFilteredProductByName(productName));
                }
                if (typeName != null) {
                    update(productService.getFilteredProductByType(typeName));
                }
            }
        });
    }

    public void update() {
        container_lv.getItems().clear();
        productObservableList = FXCollections.observableArrayList(productService.getAll().stream().filter((v) -> v.getCount() > 0).collect(Collectors.toList()));
        productObservableList.sort(Comparator.comparing(Product::getName));
        container_lv.setItems(productObservableList);
    }

    private void update(List<Product> products) {
        container_lv.getItems().clear();
        productObservableList = FXCollections.observableArrayList(products.stream().filter((v) -> v.getCount() > 0).collect(Collectors.toList()));
        productObservableList.sort(Comparator.comparing(Product::getName));
        container_lv.setItems(productObservableList);
    }

}
