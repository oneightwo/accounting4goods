package com.oneightwo.accounting4goods.controller;

import com.oneightwo.accounting4goods.constants.Constants;
import com.oneightwo.accounting4goods.model.ActiveUser;
import com.oneightwo.accounting4goods.model.Product;
import com.oneightwo.accounting4goods.model.Sale;
import com.oneightwo.accounting4goods.model.SalePiece;
import com.oneightwo.accounting4goods.service.ProductService;
import com.oneightwo.accounting4goods.service.SaleService;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigInteger;

@Component
@FxmlView("sale_piece_product.fxml")
public class SalePieceProduct {

    private Product product;

    public void setData(Product product) {
        this.product = product;
    }

    @Autowired
    private ProductService productService;

    @Autowired
    private SaleService saleService;

    @Autowired
    private ActiveUser activeUser;

    @FXML
    private TextField count_sale_tf;

    @FXML
    private Button save_b;

    @FXML
    private Button cancel_b;

    @FXML
    private Label count_product_l;

    @FXML
    private void initialize() {
        count_product_l.setText(product.getCount().toString());
        save_b.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> {
            try {
                Long number = Long.parseLong(count_sale_tf.getText());
                if (number > 0 && product.getCount() >= number) {
                    saleService.save(new SalePiece(
                            product,
                            activeUser.getActiveUser(),
                            BigInteger.valueOf(number)
                    ));
                    close();
                } else {
                    count_sale_tf.clear();
                    count_sale_tf.setPromptText("Значение больше, чем " + product.getCount());
                }
            } catch (NumberFormatException ex) {
                count_sale_tf.clear();
                count_sale_tf.setPromptText(Constants.WHOLE_NUMBER);
            }

        });
        cancel_b.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> {
            close();
        });
    }

    private void close() {
        Stage stage = (Stage) save_b.getScene().getWindow();
        stage.close();
    }

}
