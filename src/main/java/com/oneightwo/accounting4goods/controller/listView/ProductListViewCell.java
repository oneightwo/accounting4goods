package com.oneightwo.accounting4goods.controller.listView;

import com.oneightwo.accounting4goods.model.Product;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import net.rgielen.fxweaver.core.FxmlView;

import java.io.IOException;

@FxmlView("list_cell.fxml")
public class ProductListViewCell extends ListCell<Product> {

    @FXML
    private Label name_l;

    @FXML
    private Label type_l;

    @FXML
    private Label count_l;

    @FXML
    private Label price_l;

    private FXMLLoader mLLoader;

    @FXML
    private AnchorPane container_gp;

    @Override
    protected void updateItem(Product product, boolean b) {
        super.updateItem(product, b);
        if (b || product == null) {
//            System.out.println("null");
            setText(null);
            setGraphic(null);
        } else {
//            System.out.println(product);
            mLLoader = new FXMLLoader(getClass().getResource("/com/oneightwo/accounting4goods/controller/listView/list_cell.fxml"));
            mLLoader.setController(this);
            try {
                mLLoader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            name_l.setText(product.getName());
            type_l.setText(product.getType());
            count_l.setText(product.getCount().toString());
            price_l.setText(product.getPrice().toString());
            setText(null);
            setGraphic(container_gp);

        }

    }
}