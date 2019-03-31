package com.hugoiguana.br.geanuncio1.report;

import lombok.Getter;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.WorkbookUtil;

import java.util.ArrayList;
import java.util.List;

@Getter
public class SheetBuilder {

    private Workbook wb;
    private Sheet sheet;
    private String name;

    private RowBuilder currentRow;
    private List<RowBuilder> rowList;

    private SheetBuilder(Workbook wb, String name) {
        this.wb = wb;
        this.name = WorkbookUtil.createSafeSheetName(name);
        this.sheet = wb.createSheet(this.name);
        this.rowList = new ArrayList<>();
    }

    static SheetBuilder create(Workbook wb, String name) {
        return new SheetBuilder(wb, name);
    }

    void addRow(RowBuilder row) {
        currentRow = row;
        rowList.add(row);
    }

    RowBuilder getRow(int y) {
        try {
            currentRow = rowList.get(y);
            return currentRow;
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
    }

}
