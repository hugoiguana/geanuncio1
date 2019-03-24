package com.hugoiguana.br.geanuncio1.endpoint;


import com.hugoiguana.br.geanuncio1.models.Ad;
import com.hugoiguana.br.geanuncio1.models.AdReport;
import com.hugoiguana.br.geanuncio1.models.Address;
import com.hugoiguana.br.geanuncio1.models.ECity;
import com.hugoiguana.br.geanuncio1.models.ECountry;
import com.hugoiguana.br.geanuncio1.models.EGender;
import com.hugoiguana.br.geanuncio1.models.EState;
import com.hugoiguana.br.geanuncio1.models.Product;
import com.hugoiguana.br.geanuncio1.models.User;
import com.hugoiguana.br.geanuncio1.models.Report.AdReportBuilder;
import com.hugoiguana.br.geanuncio1.repository.AdRepository;
import com.hugoiguana.br.geanuncio1.repository.ProductRepository;
import com.hugoiguana.br.geanuncio1.service.AdService;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanArrayDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.view.JasperViewer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("ad")
public class AdEndpoint {

    @Autowired
    private AdRepository adRepository;
    
    @Autowired
    private ProductRepository productRepository; 

    @Autowired
    private AdService adService;
    
    @Autowired
    private AdReportBuilder adReportBuilder;
    
    @Autowired
    private Environment env;
    
    @RequestMapping(method = RequestMethod.GET, path = "list")
    public List<Ad> list(){

    	System.out.println(env.getProperty("teste.a"));
    	
        //insertSomeAdsToTestOny();

        Ad ads2 = adRepository.findFirstByOrderByDescriptionAsc();

        try {
            adService.insertSomeAdsTestTransation();
        } catch(Exception e) {
            e.printStackTrace();
        }

        List<Ad> ads = new ArrayList<>();
        adRepository.findAll().forEach(ad -> ads.add(ad));
        return ads;
    }
    
	@RequestMapping(path = "add_test1", method = RequestMethod.GET)
	public String testAddUserWithProducts() {

		Ad ad = Ad.builder().description("Anúncio 1").urlImage("https://img.olx.com.br/images/64/644821037823977.jpg")
				.value(BigDecimal.valueOf(6100)).build();

		ad.addProduct(Product.builder().description("Produto 1").value(BigDecimal.valueOf(5000)).build());
		ad.addProduct(Product.builder().description("Produto 2").value(BigDecimal.valueOf(1000)).build());
		ad.addProduct(Product.builder().description("Produto 3").value(BigDecimal.valueOf(100)).build());

		adRepository.save(ad);
		
		productRepository.save(Product.builder().description("Produto 1").value(BigDecimal.valueOf(160)).build());

		return "Anúncio adicionado : " + ad.toString();
	}

	@RequestMapping(path = "report_pdf", method = RequestMethod.GET)
	public ResponseEntity<byte[]> reportAllAdPDF() {

		byte[] bytes = adReportBuilder.generateReportPdf(adRepository.findAllByOrderByDescription());

		return ResponseEntity.ok()
				// Specify content type as PDF
				.header("Content-Type", "application/pdf; charset=UTF-8")
				// Tell browser to display PDF if it can
				.header("Content-Disposition", "inline; filename=\"relatorio_anuncios.pdf\"").body(bytes);
	}
	
	@RequestMapping(path = "report_exel", method = RequestMethod.GET)
	public ResponseEntity<byte[]> reportAllAdExcel() {

		byte[] bytes = adReportBuilder.generateReportExel(adRepository.findAllByOrderByDescription());

		return ResponseEntity.ok()
				// Specify content type as PDF
				.header("Content-Type", "application/xls; charset=UTF-8")
				// Tell browser to display PDF if it can
				.header("Content-Disposition", "inline; filename=\"relatorio_anuncios.xls\"").body(bytes);
	}
}
