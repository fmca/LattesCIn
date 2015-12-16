package br.ufpe.cin.opencin.lattes.util;

import java.text.Normalizer;

public class StringUtils {
	public static String removerAcentos(String str) {

		  str = Normalizer.normalize(str, Normalizer.Form.NFD);
		  str = str.replaceAll("[^\\p{ASCII}]", "");
		  str = str.replaceAll("de", "");
		  str = str.replaceAll("da", "");
		  str = str.replaceAll("  ", " ");
		  return str;

		}
}
