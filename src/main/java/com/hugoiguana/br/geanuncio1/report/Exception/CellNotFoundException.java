package com.hugoiguana.br.geanuncio1.report.Exception;

public class CellNotFoundException extends Exception {

    public CellNotFoundException(String reportName, String sheetName, String cell) {
        super(getMsg(reportName, sheetName, cell));
    }

    private static String getMsg(String reportName, String sheetName, String cell) {
        StringBuilder msg = new StringBuilder();
        msg.append("Cell ");
        msg.append("[").append(cell).append("]");
        msg.append(" of report ");
        msg.append("\"").append(reportName).append("\"");
        msg.append(" and sheet ");
        msg.append("\"").append(sheetName).append("\"");
        msg.append(" not found.");
        return msg.toString();
    }
}
