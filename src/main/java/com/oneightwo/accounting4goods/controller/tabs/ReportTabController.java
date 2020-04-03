package com.oneightwo.accounting4goods.controller.tabs;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import com.oneightwo.accounting4goods.model.ActiveUser;
import com.oneightwo.accounting4goods.model.Product;
import com.oneightwo.accounting4goods.model.Sale;
import com.oneightwo.accounting4goods.service.ProductService;
import com.oneightwo.accounting4goods.service.SaleService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.*;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@FxmlView("report_tab.fxml")
public class ReportTabController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ActiveUser activeUser;

    @Autowired
    private SaleService saleService;

    private ObservableList<PieChart.Data> valueList = FXCollections.observableArrayList();

    private List<XYChart.Data> dataList = FXCollections.observableArrayList();

    @FXML
    private PieChart type_product_pc;

    @FXML
    private BarChart<?, ?> sale_last_week_bc;

    @FXML
    private CategoryAxis x_ca;

    @FXML
    private NumberAxis y_na;

    @FXML
    private Button generate_report_b;

    @FXML
    private void initialize() {
        update();
        generate_report_b.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> {
            generateReport();
        });
    }

    private void getTypeList() {
        valueList = FXCollections.observableArrayList();
        Map<String, Integer> result = new HashMap<>();
        for (Product p : productService.getAll()) {
            String type = p.getType().getName();
            if (!result.containsKey(type)) {
                result.put(type, 1);
            } else {
                result.put(type, result.get(type) + 1);
            }
        }
        for (Map.Entry<String, Integer> i : result.entrySet()) {
            valueList.add(new PieChart.Data(i.getKey(), i.getValue()));
        }
    }

    private void getSaleLastWeek() {
        List<String> dayOfWeek = List.of("Понедельник", "Вторник", "Среда", "Четверг", "Пятница", "Суббота", "Воскресенье");
        dataList = FXCollections.observableArrayList();
        HashMap<String, Integer> result = new HashMap<>() {{
            dayOfWeek.forEach(v -> put(v, 0));
        }};
        for (Sale sale : saleService.getSaleByUserOfLastWeek(activeUser.getActiveUser().getId())) {
            String curDay = dayOfWeek.get(sale.getDate().getDayOfWeek().getValue() - 1);
            result.put(curDay, result.get(curDay) + 1);
        }
        for (Map.Entry<String, Integer> i : result.entrySet()) {
            dataList.add(new XYChart.Data(i.getKey(), i.getValue()));
        }
    }

    public void update() {
        sale_last_week_bc.getData().clear();
        type_product_pc.getData().clear();
        getTypeList();
        getSaleLastWeek();
        type_product_pc.setData(valueList);
        XYChart.Series dataSeries = new XYChart.Series();
        dataSeries.getData().addAll(dataList);
        sale_last_week_bc.getData().add(dataSeries);
    }

    private void generateReport() {
        Document document = new Document(PageSize.A4, 50, 50, 50, 50);
        try {
            PdfWriter writer = PdfWriter.getInstance(document,
                    new FileOutputStream(javax.swing.filechooser.FileSystemView.getFileSystemView().getHomeDirectory() + "\\Report.pdf"));
            document.open();
            BaseFont bf = BaseFont.createFont("c:/Windows/Fonts/arial.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED); //подключаем файл шрифта, который поддерживает кириллицу
            Font font = new Font(bf);
            Paragraph title1 = new Paragraph("Chapter 1",
                    FontFactory.getFont(FontFactory.HELVETICA,
                            18, Font.BOLDITALIC, new CMYKColor(0, 255, 255, 17)));
            Chapter chapter1 = new Chapter(title1, 1);
            chapter1.setNumberDepth(0);
            Paragraph title11 = new Paragraph("Отчет по товарам",font);
            Section section1 = chapter1.addSection(title11);
            PdfPTable t = new PdfPTable(4);
            t.setSpacingBefore(25);
            t.setSpacingAfter(25);
            PdfPCell c1 = new PdfPCell(new Phrase("Название", font));
            t.addCell(c1);
            PdfPCell c2 = new PdfPCell(new Phrase("Тип", font));
            t.addCell(c2);
            PdfPCell c3 = new PdfPCell(new Phrase("Количесвто", font));
            t.addCell(c3);
            PdfPCell c4 = new PdfPCell(new Phrase("Цена", font));
            t.addCell(c4);
            for (Product product : productService.getAll()) {
                t.addCell(new Phrase(product.getName(), font));
                t.addCell(new Phrase(product.getType().getName(), font));
                t.addCell(new Phrase(product.getCount().toString(), font));
                t.addCell(new Phrase(product.getPrice().toString(), font));
            }
            section1.add(t);
            document.add(section1);
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        document.close();
    }

//    private Map<String, String> getDataByProduct() {
//        Map<String, String> map = new HashMap<>();
//        for (Product product : productService.getAll()) {
//            if (map.containsKey(product.getName()))
//        }
//    }
}
