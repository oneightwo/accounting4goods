package com.oneightwo.accounting4goods.controller;

import com.oneightwo.accounting4goods.controller.listView.ProductListViewCell;
import com.oneightwo.accounting4goods.model.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.stereotype.Component;

@Component
@FxmlView("main.fxml")
public class MainController {

    @FXML
    private ListView<Product> listView;

    private ObservableList<Product> productObservableList;


    @FXML
    public void initialize() {
        productObservableList = FXCollections.observableArrayList();
        productObservableList.addAll(
                new Product("name_", "type?", "dfcwefc",10L, 19.5, "fewvrf"),
                new Product("_name_name_name_name_name_name_name_nam_name_name_name_name_", "type?", "dfcwefc",10L, 19.5, "fewvrf"),
                new Product("name_", "type?", "dfcwefcdfcwefcdfcwefcdfcwefcdfcwefc",10L, 19.5, "fewvrf"),
                new Product("name_", "type?", "dfcwefc",10L, 19.5, "fewvrf"),
                new Product("name_", "type?", "dfcwefc",10L, 19.5, "fewvrf"),
                new Product("name_name_name_name_name_name_name_name_name_name_name_name_", "dfcwefcdfcwefcdfcwefcdfcwefcdfcwefcdfcwefc?", "dfcwefc",10L, 19.5, "fewvrf"),
                new Product("name_", "type?", "dfcwefc",10L, 19.5, "fewvrf"),
                new Product("name_", "type?", "dfcwefcdfcwefcdfcwefcdfcwefcdfcwefcdfcwefcdfcwefcdfcwefc",10L, 19.5, "fewvrf"),
                new Product("name_", "type?", "dfcwefc",10L, 19.5, "fewvrf"),
                new Product("name_", "type?", "dfcwefc",10L, 19.5, "fewvrf"),
                new Product("name_", "type?", "dfcwefc",10L, 19.5, "fewvrf"),
                new Product("name_", "type?", "dfcwefc",10L, 19.5, "fewvrf"),
                new Product("name_", "type?", "dfcwefc",10L, 19.5, "fewvrf"),
                new Product("name_", "type?", "dfcwefc",10L, 19.5, "fewvrf"),
                new Product("name_", "type?", "dfcwefc",10L, 19.5, "fewvrf"),
                new Product("name_", "type?", "dfcwefc",10L, 19.5, "fewvrf"),
                new Product("name_", "type?", "dfcwefc",10L, 19.5, "fewvrf"),
                new Product("name_", "type?", "dfcwefc",10L, 19.5, "fewvrf"),
                new Product("name_", "type?", "dfcwefc",10L, 19.5, "fewvrf"),
                new Product("name_", "type?", "dfcwefc",10L, 19.5, "fewvrf"),
                new Product("name_", "type?", "dfcwefc",10L, 19.5, "fewvrf"),
                new Product("name_", "type?", "dfcwefc",10L, 19.5, "fewvrf"),
                new Product("name_", "type?", "dfcwefc",10L, 19.5, "fewvrf"),
                new Product("name_", "type?", "dfcwefc",10L, 19.5, "fewvrf"),
                new Product("name_", "type?", "dfcwefc",10L, 19.5, "fewvrf"),
                new Product("name_", "type?", "dfcwefc",10L, 19.5, "fewvrf"),
                new Product("name_", "type?", "dfcwefc",10L, 19.5, "fewvrf"),
                new Product("name_", "type?", "dfcwefc",10L, 19.5, "fewvrf"),
                new Product("name_", "type?", "dfcwefc",10L, 19.5, "fewvrf"),
                new Product("name_", "type?", "dfcwefc",10L, 19.5, "fewvrf"),
                new Product("name_", "type?", "dfcwefc",10L, 19.5, "fewvrf"),
                new Product("name_", "type?", "dfcwefc",10L, 19.5, "fewvrf"),
                new Product("name_", "type?", "dfcwefc",10L, 19.5, "fewvrf")
        );
        listView.setItems(productObservableList);
        listView.setCellFactory(studentListView -> new ProductListViewCell());
    }
}
