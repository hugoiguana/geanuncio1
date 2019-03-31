package com.hugoiguana.br.geanuncio1.report;

import lombok.Getter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

@Getter
public class RowBuilder {

    int y;
    Sheet sheet;
    Row row;

    CellBuilder currentCell;
    List<CellBuilder> cellList;

    private RowBuilder(int y, Sheet sheet) {
        this.y = y;
        this.sheet = sheet;
        createRow();
        cellList = new ArrayList<>();
    }

    public static RowBuilder create(int y, Sheet sheet) {
        return new RowBuilder(y, sheet);
    }

    private void createRow() {
        this.row = sheet.createRow(this.y);
    }

    void addCell(int x, String value) {
        this.currentCell = CellBuilder.create(this.row, x, value);
        this.cellList.add(this.currentCell);
    }

    public void addCells(int x, List<Object> valueFieldList, String ... fieldNameList)
            throws NoSuchFieldException, IllegalAccessException {
        for (Object valueField : valueFieldList) {
            int indexColumn = x;
            for (String fieldName : fieldNameList) {
                Field field = valueField.getClass().getDeclaredField(fieldName);
                field.setAccessible(true);

                addCell(indexColumn, field.get(valueField).toString());
                indexColumn++;
                /*
                if (field.getType() == int.class)
                    System.out.println("Int = " + field.getInt(valueField));
                else if (field.getType() == double.class)
                    System.out.println("double = " + field.getDouble(valueField));
                else if (field.getType() == boolean.class)
                    System.out.println("boolean = " + field.getBoolean(valueField));
                else if (field.getType() == BigDecimal.class)
                    System.out.println("BigDecimal = " + ((BigDecimal) field.get(valueField)));
                */
            }
            y++;
            createRow();
        }
    }

}
