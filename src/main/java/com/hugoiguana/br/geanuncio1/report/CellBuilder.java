package com.hugoiguana.br.geanuncio1.report;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;

public class CellBuilder {

    int x;
    Cell cell;
    Row row;

    private CellBuilder(Row row, int x, String value) {
        this.x = x;
        this.row = row;
        this.cell = this.row.createCell(x);
        this.cell.setCellValue(value);
    }

    public static CellBuilder create(Row row, int x, String value) {
        return new CellBuilder(row, x, value);
    }




}
