package com.hugoiguana.br.geanuncio1.models;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Component
@Scope("prototype")
@NoArgsConstructor
public class AdReport {

	@Getter
	@Setter
	private Integer id;

	@Getter
	@Setter
	private String description;

	@Getter
	@Setter
	private BigDecimal value;

	private List<AdReport> adReportList = new ArrayList<>();

	
	public AdReport(Integer id, String description, BigDecimal value) {
		this.id = id;
		this.description = description;
		this.value = value;
	}

	public void add(Integer id, String description, BigDecimal value) {
		adReportList.add(new AdReport(id, description, value));
	}

	public List<AdReport> getAdReportList() {
		return adReportList;
	}

}
