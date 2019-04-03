package com.hugoiguana.br.geanuncio1.report;


import com.hugoiguana.br.geanuncio1.report.Exception.CellNotFoundException;
import com.hugoiguana.br.geanuncio1.report.Exception.ReportNameException;
import lombok.Getter;
import lombok.Setter;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ReportExcelBuilder {

    private Workbook wb;
    private String reportName;

    private int currentY;
    private int currentX;

    private SheetBuilder currentSheet;
    private List<SheetBuilder> sheetList;

    private ReportExcelBuilder(String reportName) {
        this.wb = new XSSFWorkbook();
        this.reportName = reportName;
        this.sheetList = new ArrayList<>();
    }

    public static ReportExcelBuilder init(String reportName) throws ReportNameException {
        return new ReportExcelBuilder(reportName);
    }

    public ReportExcelBuilder addSheet(String name) {
        this.currentSheet = SheetBuilder.create(wb, name);
        this.sheetList.add(this.currentSheet);
        this.currentY = 0;
        this.currentX = 0;
        return this;
    }

    public ReportExcelBuilder addRow() {
        this.currentY++;
        this.currentX = 0;
        return addRow(this.currentY);
    }

    public ReportExcelBuilder addRow(int y) {
        this.currentSheet.addRow(RowBuilder.create(y, this.currentSheet.getSheet()));
        this.currentY = y;
        return this;
    }

    public ReportExcelBuilder addCell(String value) {
        this.currentX++;
        return addCell(this.currentX, value);
    }

    public ReportExcelBuilder addCell(int x, String value) {
        this.currentSheet.getCurrentRow().addCell(x, value);
        this.currentX = x;
        return this;
    }

    public ReportExcelBuilder addCells(int x, List<Object> valueFieldList, String ... fieldNameList)
            throws NoSuchFieldException, IllegalAccessException {
        this.currentSheet.getCurrentRow().addCells(x, valueFieldList, fieldNameList);
        return this;
    }

    public ReportExcelBuilder addCellStyle(CellRangeAddress cellRangeAddress, CellStyle cs) throws CellNotFoundException {
        for (int i = cellRangeAddress.getFirstColumn(); i <= cellRangeAddress.getLastColumn(); i++) {
            Cell cell = this.currentSheet.getCurrentRow().getRow().getCell(i, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
            if (cell == null) {
                throw new CellNotFoundException(reportName, this.currentSheet.getName(), cellRangeAddress.formatAsString());
            }
            cell.setCellStyle(cs);
        }
        return this;
    }

    public ReportExcelBuilder setRowHeightInPoints(float heightInPoints) {
        this.currentSheet.getCurrentRow().getRow().setHeightInPoints(heightInPoints);
        return this;
    }

    //set column widths, the width is measured in units of 1/256th of a character width
    public ReportExcelBuilder setColumnWidth(int x, int width) {
        this.currentSheet.getSheet().setColumnWidth(x, 256 * width);
        return this;
    }

    public ReportExcelBuilder mergeCells(CellRangeAddress cellRangeAddress) {
        this.currentSheet.mergeCells(cellRangeAddress);
        return this;
    }



    public SheetBuilder getSheet(int index) {
        try {
            this.currentSheet = this.sheetList.get(index);
            return this.currentSheet;
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
    }

    public RowBuilder getRow(int indexSheet, int y) {
        getSheet(indexSheet);
        return currentSheet == null ? null : currentSheet.getRow(y);
    }

    public void build(String path) {
        try {
            File myFile = new File(path + reportName + ".xlsx");
            OutputStream fileOut = new FileOutputStream(myFile);
            wb.write(fileOut);
        } catch (IOException e) {
            System.out.println("Erro");
        }
    }

}
