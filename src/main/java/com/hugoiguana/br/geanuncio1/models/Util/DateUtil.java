package com.hugoiguana.br.geanuncio1.models.Util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public final class DateUtil {

	public static String formatDefault(LocalDate date) {
		if (date == null) {
			return null;
		}
		return date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
	}
	
}
