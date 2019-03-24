package com.hugoiguana.br.geanuncio1.models.Report;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hugoiguana.br.geanuncio1.models.Ad;
import com.hugoiguana.br.geanuncio1.models.AdReport;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanArrayDataSource;
import net.sf.jasperreports.engine.design.JRDesignBand;
import net.sf.jasperreports.engine.design.JRDesignExpression;
import net.sf.jasperreports.engine.design.JRDesignField;
import net.sf.jasperreports.engine.design.JRDesignSection;
import net.sf.jasperreports.engine.design.JRDesignStaticText;
import net.sf.jasperreports.engine.design.JRDesignTextField;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.type.HorizontalAlignEnum;
import net.sf.jasperreports.engine.type.VerticalAlignEnum;

@Component
public class AdReportBuilder {

	public final String PDF_TYPE = "PDF";
	public final String EXCEL_TYPE = "XLS";

	@Autowired
	private AdReport adReport;

	public byte[] generateReportPdf(List<Ad> adList) {
		return generateReport(adList, PDF_TYPE);
	}

	public byte[] generateReportExel(List<Ad> adList) {
		return generateReport(adList, EXCEL_TYPE);
	}

	private byte[] generateReport(List<Ad> adList, String typeReport) {

		byte[] bytes = null;

		for (Ad ad : adList) {
			adReport.add(ad.getId(), ad.getDescription(), ad.getValue());
		}

		try {
			JasperDesign jasperDesign = getTemplate();
			JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null,
					new JRBeanArrayDataSource(adReport.getAdReportList().toArray()));
			// JasperViewer.viewReport(jasperPrint);

			if (typeReport.equals(PDF_TYPE)) {
				bytes = JasperExportManager.exportReportToPdf(jasperPrint);
			} else {
				JRXlsExporter xlsExporter = new JRXlsExporter();
				xlsExporter.getExporterOutput().getOutputStream().write(bytes);
			}

		} catch (JRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return bytes;
	}

	private JasperDesign getTemplate() throws JRException {

		final JasperDesign design = new JasperDesign();
		final int pageWidth = 595;
		final int pageHeight = 842;
		final int margin = 20;
		final int widthUtil = pageWidth - (margin * 2);
		final int posicao_y_coluna = 0;
		final int posicao_y_filed = 15;

		// Confing report
		design.setName("Relatório de anúncios");
		design.setPageWidth(pageWidth);
		design.setPageHeight(pageHeight);
		design.setColumnWidth(widthUtil);
		design.setColumnSpacing(BigDecimal.ZERO.intValue());
		design.setLeftMargin(margin);
		design.setRightMargin(margin);
		design.setTopMargin(margin);
		design.setBottomMargin(margin);

		// Colunas
		final JRDesignStaticText textColumnId = new JRDesignStaticText();
		textColumnId.setX(BigDecimal.ZERO.intValue());
		textColumnId.setY(posicao_y_coluna);
		textColumnId.setWidth(40);
		textColumnId.setHeight(13);
		textColumnId.setHorizontalAlignment(HorizontalAlignEnum.LEFT);
		textColumnId.setVerticalAlignment(VerticalAlignEnum.MIDDLE);
		textColumnId.setText("Código");
		textColumnId.setBold(true);
		textColumnId.getParagraph().setLeftIndent(2);

		final JRDesignStaticText textColumnDescription = new JRDesignStaticText();
		textColumnDescription.setX(50);
		textColumnDescription.setY(posicao_y_coluna);
		textColumnDescription.setWidth(200);
		textColumnDescription.setHeight(13);
		textColumnDescription.setHorizontalAlignment(HorizontalAlignEnum.LEFT);
		textColumnDescription.setVerticalAlignment(VerticalAlignEnum.MIDDLE);
		textColumnDescription.setText("Descrição");
		textColumnId.setBold(true);
		textColumnDescription.getParagraph().setLeftIndent(2);

		final JRDesignStaticText textColumnValue = new JRDesignStaticText();
		textColumnValue.setX(460);
		textColumnValue.setY(posicao_y_coluna);
		textColumnValue.setWidth(80);
		textColumnValue.setHeight(13);
		textColumnValue.setHorizontalAlignment(HorizontalAlignEnum.LEFT);
		textColumnValue.setVerticalAlignment(VerticalAlignEnum.MIDDLE);
		textColumnValue.setText("Valor(R$)");
		textColumnId.setBold(true);
		textColumnValue.getParagraph().setLeftIndent(2);

		final JRDesignBand designColumn = new JRDesignBand();
		designColumn.setHeight(13);
		designColumn.addElement(textColumnId);
		designColumn.addElement(textColumnDescription);
		designColumn.addElement(textColumnValue);
		design.setColumnHeader(designColumn);

		// Confing fields
		design.addField(createField("id", Integer.class));
		design.addField(createField("description", String.class));
		design.addField(createField("value", BigDecimal.class));

		// Banda de detalhe
		final JRDesignTextField textFieldId = new JRDesignTextField();
		textFieldId.setX(BigDecimal.ZERO.intValue());
		textFieldId.setY(posicao_y_filed);
		textFieldId.setWidth(40);
		textFieldId.setHeight(13);
		textFieldId.setHorizontalAlignment(HorizontalAlignEnum.LEFT);
		textFieldId.setVerticalAlignment(VerticalAlignEnum.MIDDLE);
		textFieldId.setExpression(new JRDesignExpression("$F{id}"));
		textFieldId.getParagraph().setLeftIndent(2);

		final JRDesignTextField textFieldDescription = new JRDesignTextField();
		textFieldDescription.setX(50);
		textFieldDescription.setY(posicao_y_filed);
		textFieldDescription.setWidth(200);
		textFieldDescription.setHeight(13);
		textFieldDescription.setHorizontalAlignment(HorizontalAlignEnum.LEFT);
		textFieldDescription.setVerticalAlignment(VerticalAlignEnum.MIDDLE);
		textFieldDescription.setExpression(new JRDesignExpression("$F{description}"));
		textFieldDescription.getParagraph().setLeftIndent(2);

		final JRDesignTextField textFieldValue = new JRDesignTextField();
		textFieldValue.setX(460);
		textFieldValue.setY(posicao_y_filed);
		textFieldValue.setWidth(80);
		textFieldValue.setHeight(13);
		textFieldValue.setHorizontalAlignment(HorizontalAlignEnum.LEFT);
		textFieldValue.setVerticalAlignment(VerticalAlignEnum.MIDDLE);
		textFieldValue.setExpression(new JRDesignExpression("$F{value}"));
		textFieldValue.getParagraph().setLeftIndent(2);

		final JRDesignBand designBand = new JRDesignBand();
		designBand.setHeight(50);
		designBand.addElement(textFieldId);
		designBand.addElement(textFieldDescription);
		designBand.addElement(textFieldValue);

		((JRDesignSection) design.getDetailSection()).addBand(designBand);

		return design;
	}

	private JRDesignField createField(String name, Class<?> clazz) {
		final JRDesignField fieldId = new JRDesignField();
		fieldId.setName(name);
		fieldId.setDescription(name);
		fieldId.setValueClass(clazz);
		return fieldId;
	}

}
