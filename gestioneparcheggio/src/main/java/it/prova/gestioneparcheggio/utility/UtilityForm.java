package it.prova.gestioneparcheggio.utility;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

public class UtilityForm {

	public static boolean validateParcheggioFormInput(String nomeInputParam, String indirizzoInputParam,
			String orarioAperturaInputParam, String orarioChiusuraInputParam, String giornoChiusuraInputParam,
			String capienzaStringParam) {

		if (StringUtils.isBlank(nomeInputParam) || StringUtils.isBlank(indirizzoInputParam)
				|| StringUtils.isBlank(orarioAperturaInputParam) || StringUtils.isBlank(orarioChiusuraInputParam)
				|| StringUtils.isBlank(giornoChiusuraInputParam) || !NumberUtils.isCreatable(capienzaStringParam)) {
			return false;
		}
		return true;
	}

	public static boolean validateAutomobileInputForm(String marcaInputParam, String modelloInputParam,
			String targaInputParam, String orarioStampaTicketStringParam, String minutiDurataTicketStringParam,
			String parcheggioIdStringParam) {

		if (StringUtils.isBlank(marcaInputParam) || StringUtils.isBlank(modelloInputParam)
				|| StringUtils.isBlank(targaInputParam) || StringUtils.isBlank(orarioStampaTicketStringParam)
				|| StringUtils.isBlank(minutiDurataTicketStringParam)
				|| !NumberUtils.isCreatable(parcheggioIdStringParam)) {
			return false;
		}
		return true;
	}

	public static Date parseDateEntryFromString(String giornoChiusuraStringParam) {
		if (StringUtils.isBlank(giornoChiusuraStringParam))
			return null;

		try {
			return new SimpleDateFormat("yyyy-MM-dd").parse(giornoChiusuraStringParam);
		} catch (ParseException e) {
			return null;
		}
	}

	public static LocalTime parseTimeEntryFromString(String orarioAperturaInputParam) {
		if (StringUtils.isBlank(orarioAperturaInputParam))
			return null;

		return LocalTime.parse(orarioAperturaInputParam, DateTimeFormatter.ofPattern("HH:mm:ss"));
	}

	public static Long parseIdEntryToLongFromString(String idEntryStringParam) {
		if (StringUtils.isBlank(idEntryStringParam))
			return null;

		try {
			return Long.parseLong(idEntryStringParam);
		} catch (Exception e) {
			return null;
		}
	}

	public static Integer parseIntegerFromString(String minutiDurataParam) {
		if (StringUtils.isBlank(minutiDurataParam))
			return null;

		try {
			return Integer.parseInt(minutiDurataParam);
		} catch (Exception e) {
			return null;
		}
	}
}
