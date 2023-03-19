package com.pe.crce.biblioteca.util;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import com.pe.crce.biblioteca.dto.PageableDTO;

@Component
public final class BibliotecaUtil {

	private static final String[] UNIDADES = { "", "uno ", "dos ", "tres ", "cuatro ", "cinco ", "seis ", "siete ",
			"ocho ", "nueve " };
	private static final String[] DECENAS = { "diez ", "once ", "doce ", "trece ", "catorce ", "quince ", "dieciseis ",
			"diecisiete ", "dieciocho ", "diecinueve", "veinte ", "treinta ", "cuarenta ", "cincuenta ", "sesenta ",
			"setenta ", "ochenta ", "noventa " };
	private static final String[] CENTENAS = { "", "ciento ", "doscientos ", "trecientos ", "cuatrocientos ",
			"quinientos ", "seiscientos ", "setecientos ", "ochocientos ", "novecientos " };

	public String resolveToken(String bearerToken) {
		if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
			return bearerToken.substring(7);
		}
		return null;
	}

	public Pageable getPageable(PageableDTO pageableDTO) {
		Optional<Integer> sortOrder = pageableDTO.getOrder();
		Optional<String> sortField = pageableDTO.getField();
		Integer pageNumber = pageableDTO.getPageNumber();
		Integer perPage = pageableDTO.getPageSize();

		Pageable pageable;
		if (sortOrder.isPresent() && sortField.isPresent()) {
			Sort.Direction direction = sortOrder.get().equals(1) ? Sort.Direction.ASC : Sort.Direction.DESC;
			pageable = PageRequest.of(pageNumber, perPage, Sort.by(direction, sortField.get()));
		} else {
			pageable = PageRequest.of(pageNumber, perPage, Sort.by(Sort.Direction.DESC, "id"));
		}
		return pageable;
	}

	public static String convertirNumero_Letras(String numero, boolean mayusculas) {

		String literal = "";
		String Num[] = numero.split(",");

		if (Integer.parseInt(Num[0]) > 999) {// si es miles
			literal = getMiles(Num[0]);
		} else if (Integer.parseInt(Num[0]) > 99) {// si es centena
			literal = getCentenas(Num[0]);
		} else if (Integer.parseInt(Num[0]) > 9) {// si es decena
			literal = getDecenas(Num[0]);
		} else {// sino unidades -> 9
			literal = getUnidades(Num[0]);
		}
		// devuelve el resultado en mayusculas o minusculas
		if (mayusculas) {
			return (literal).toUpperCase();
		} else {
			return (literal);
		}
	}

	private static String getUnidades(String numero) {// 1 - 9
		// si tuviera algun 0 antes se lo quita -> 09 = 9 o 009=9
		String num = numero.substring(numero.length() - 1);
		return UNIDADES[Integer.parseInt(num)];
	}

	private static String getMiles(String numero) {// 999 999
		// obtiene las centenas
		String c = numero.substring(numero.length() - 3);
		// obtiene los miles
		String m = numero.substring(0, numero.length() - 3);
		String n;
		// se comprueba que miles tenga valor entero
		if (Integer.parseInt(m) > 0) {
			if (Integer.parseInt(m) == 1) {
				return "mil " + getCentenas(c);
			} else {
				n = getCentenas(m);
				return n + "mil " + getCentenas(c);
			}
		} else {
			return "" + getCentenas(c);
		}
	}

	private static String getDecenas(String num) {// 99
		int n = Integer.parseInt(num);
		if (n < 10) {// para casos como -> 01 - 09
			return getUnidades(num);
		}
		////////////////////////////
		else if (n >= 20 && n < 30) {// para 20...99
			String u = getUnidades(num);
			if (u.equals("")) { // para 20,30,40,50,60,70,80,90
				return DECENAS[Integer.parseInt(num.substring(0, 1)) + 8];
			} else {
				return "veinti" + u;
			}
		}
		///////////////
		else if (n >= 30) {// para 20...99
			String u = getUnidades(num);
			if (u.equals("")) { // para 20,30,40,50,60,70,80,90
				return DECENAS[Integer.parseInt(num.substring(0, 1)) + 8];
			} else {
				return DECENAS[Integer.parseInt(num.substring(0, 1)) + 8] + "y " + u;
			}
		} else {// numeros entre 11 y 19
			return DECENAS[n - 10];
		}
	}

	private static String getCentenas(String num) {// 999 o 099
		if (Integer.parseInt(num) > 99) {// es centena
			if (Integer.parseInt(num) == 100) {// caso especial
				return " cien ";
			} else {
				return CENTENAS[Integer.parseInt(num.substring(0, 1))] + getDecenas(num.substring(1));
			}
		} else {// por Ej. 099
				// se quita el 0 antes de convertirNumero_Letras a decenas
			return getDecenas(Integer.parseInt(num) + "");
		}
	}

	public Boolean validateEmail(String email) {
		Pattern emailRegex = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
		Matcher matcher = emailRegex.matcher(email);
		return matcher.find();
	}

	public String remplace(String cadena, String word_initial, String word_final) {
		return cadena.replaceAll(word_initial, word_final);
	}

	public Boolean validateDocumentNumber(String documentNumber) {
		return documentNumber != null && documentNumber.matches("\\d{8}");
	}

	public Boolean validateNumberRUC(String ruc) {
		return ruc != null && ruc.matches("\\d{11}");
	}

	public static String numberFormat(double src, String fmt) {// Format : ###.####
		fmt = fmt.replaceAll("#", "0");
		DecimalFormat df = new DecimalFormat(fmt);
		return df.format(src);
	}

	public static String numberFormat(long src, String fmt) {// Format : ###.####
		fmt = fmt.replaceAll("#", "0");
		DecimalFormat df = new DecimalFormat(fmt);
		return df.format(src);
	}

	public static java.util.Date castDate_SQL_UTIL(java.sql.Date df) {
		return new java.util.Date(df.getTime());
	}

	public static java.sql.Date castDate_UTIL_SQL(java.util.Date df) {
		return new java.sql.Date(df.getTime());
	}

	public static Float roundTwoDecimal(Float number) {
		return (float) (Math.round(number * 100.0) / 100.0);
	}

	public static Date parseDate(String date) {
		SimpleDateFormat formato = new SimpleDateFormat("yyyy-mm-dd");
		Date fechaDate = null;
		try {
			fechaDate = formato.parse(date);
		} catch (ParseException ex) {
			System.out.println(ex);
		}
		return fechaDate;
	}

	public static java.sql.Timestamp castTimestamp_UTIL_SQL(java.util.Date df) {
		Date dat = new Date();
		df.setHours(dat.getHours());
		df.setMinutes(dat.getMinutes());
		df.setSeconds(dat.getSeconds());
		return new java.sql.Timestamp(df.getTime());
	}

	public static Integer toIntExact(Long number) {
		return Math.toIntExact(number);
	}

	public static Integer toIntExact(Float number) {
		String value = String.valueOf(number);
		return Integer.parseInt(value.substring(0, value.indexOf('.')));
	}

	public static Integer toIntExactDec(Float number) {
		String value = String.valueOf(number);
		return Integer.parseInt(value.substring(value.indexOf('.') + 1));
	}

	public static String saleTotal(Float number) {
		return convertirNumero_Letras(String.valueOf(toIntExact(number)), true) + "CON "
				+ String.valueOf(zfill(toIntExactDec(number).toString(), 2)) + "/100";
	}

	public static String toDigitExtract(String value, Integer nro_caracteres_extraer) {
		return value.substring(0, nro_caracteres_extraer);
	}

	public static String zfill(String value, Integer countDigito) {
		if (value.length() == countDigito || value.length() > countDigito) {
			return value;
		} else {
			return zfill(0 + value, countDigito);
		}
	}
}
