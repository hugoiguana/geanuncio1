package com.hugoiguana.br.geanuncio1.report;

import com.hugoiguana.br.geanuncio1.models.Product;
import com.hugoiguana.br.geanuncio1.report.Exception.CellNotFoundException;
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

            Font fontColumnsValue = wb.createFont();
            fontColumns.setFontHeightInPoints((short)13);

            CellStyle csColumnsValue = wb.createCellStyle();
            csColumnsValue.setBorderTop(BorderStyle.THIN);
            csColumnsValue.setBorderBottom(BorderStyle.THIN);
            csColumnsValue.setBorderLeft(BorderStyle.THIN);
            csColumnsValue.setBorderRight(BorderStyle.THIN);
            csColumnsValue.setAlignment(HorizontalAlignment.LEFT);
            csColumnsValue.setVerticalAlignment(VerticalAlignment.CENTER);
            csColumnsValue.setFont(fontColumnsValue);

            reportExcelBuilder
                    .addSheet("Gastos com Lazer")
                    .addRow(1)
                    .addCell(1, "Produto")
                    .addCell("Valor")
                    .addCell("Descrição")
                    .addCell("")
                    .addCellStyle(CellRangeAddress.valueOf("B2:E2"), csColumns)
                    .mergeCells(CellRangeAddress.valueOf("D2:E2"))
                    .setRowHeightInPoints(30)
                    .setColumnWidth(1, 30)
                    .setColumnWidth(2, 40)
                    .addRow()
                    .addCells(1, productList, "description", "value", "description")
                    //.addCellStyle(CellRangeAddress.valueOf("B2:D2"), csColumnsValue)
                    //.mergeCells(CellRangeAddress.valueOf("C2:D2"))
                    .addSheet("Gasto com saúde")
                    .addRow(1)
                    .addCell(1, "ID")
                    .addCell("Data")
                    .addCell("Decrição")
                    .addCell("Valor")
                    .addCellStyle(CellRangeAddress.valueOf("B2:E2"), csColumns)
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
                    .addCellStyle(CellRangeAddress.valueOf("B3:E3"), csColumnsValue)
                    //.build("/home/hugoiguana/Downloads/");
                    .build("");

            //CellRangeAddress

            System.out.println("Relatório gerado com sucesso!");
        } catch (ReportNameException e) {
            e.printStackTrace();
        } catch (CellNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

}

