package com.hugoiguana.br.geanuncio1.report;

import lombok.Getter;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;

@Getter
public class CellPropertieBuilder {

    private HorizontalAlignment horizontalAlignment;
    private BorderStyle borderStyle;

    private CellPropertieBuilder() {

    }

    public static CellPropertieBuilder create() {
        return new CellPropertieBuilder();
    }

    public CellPropertieBuilder addHorizontalAlignment(HorizontalAlignment horizontalAlignment) {
        this.horizontalAlignment = horizontalAlignment;
        return this;
    }

    public CellPropertieBuilder addBorderStyle(BorderStyle borderStyle) {
        this.borderStyle = borderStyle;
        return this;
    }

}
