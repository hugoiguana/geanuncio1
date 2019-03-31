package com.hugoiguana.br.geanuncio1.report;

import com.hugoiguana.br.geanuncio1.models.Product;
import com.hugoiguana.br.geanuncio1.report.Exception.ReportNameException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class TestReportBuilder {

    public static void main(String args[]) {
        try {

            List<Object> productList = new ArrayList<>();
            productList.add(Product.builder().value(BigDecimal.valueOf(1599)).description("Tv samsung").build());
            productList.add(Product.builder().value(BigDecimal.valueOf(200)).description("Chroome Cats").build());
            productList.add(Product.builder().value(BigDecimal.valueOf(25)).description("Pendrive").build());
            productList.add(Product.builder().value(BigDecimal.valueOf(6000)).description("IPhone").build());
            productList.add(Product.builder().value(BigDecimal.valueOf(1900)).description("Play station 4").build());
            productList.add(Product.builder().value(BigDecimal.valueOf(3500)).description("Notbook asus").build());

            List<String> fieldNameList = new ArrayList<>();
            fieldNameList.add("value");
            fieldNameList.add("description");


            ReportExcelBuilder reportExcelBuilder = ReportExcelBuilder.init("Relatório de custos");
            Workbook wb = reportExcelBuilder.getWb();

            Font fontColumns = wb.createFont();
            fontColumns.setBold(true);
            fontColumns.setFontHeightInPoints((short)13);

            CellStyle csColumns = wb.createCellStyle();
            csColumns.setBorderTop(BorderStyle.THIN);
            csColumns.setBorderBottom(BorderStyle.THIN);
            csColumns.setBorderLeft(BorderStyle.THIN);
            csColumns.setBorderRight(BorderStyle.THIN);
            csColumns.setAlignment(HorizontalAlignment.CENTER);
            csColumns.setVerticalAlignment(VerticalAlignment.CENTER);
            csColumns.setFont(fontColumns);

            reportExcelBuilder
                    .addSheet("Gastos com Lazer")
                    .addRow(1)
                    .addCell(1, "Produto")
                    .addCell("Valor")
                    .addCellStyle(CellRangeAddress.valueOf("B1:C1"), csColumns)
                    .setRowHeightInPoints(30)
                    .setColumnWidth(1, 30)
                    .setColumnWidth(2, 40)
                    .addRow()
                    .addCells(1, productList, "description", "value")
                    .addSheet("Gasto com saúde")
                    .addRow(1)
                    .addCell(1, "ID")
                    .addCell("Data")
                    .addCell("Decrição")
                    .addCell("Valor")
                    .addCellStyle(CellRangeAddress.valueOf("B1:E1"), csColumns)
                    .setRowHeightInPoints(30)
                    .setColumnWidth(1, 4)
                    .setColumnWidth(2, 10)
                    .setColumnWidth(3, 20)
                    .setColumnWidth(4, 8)
                    .addRow()
                    .addCell(1, "1")
                    .addCell("01/04/2019")
                    .addCell("Benegripe")
                    .addCell("10,00")
                    .build("/home/hugoiguana/Downloads/");


            //CellRangeAddress

            System.out.println("Relatório gerado com sucesso!");
        } catch (ReportNameException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

}

