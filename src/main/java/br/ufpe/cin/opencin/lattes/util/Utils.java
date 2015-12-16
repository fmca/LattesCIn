package br.ufpe.cin.opencin.lattes.util;

import java.util.List;

import br.ufpe.cin.opencin.lattes.entidades.Professor;
import info.debatty.java.stringsimilarity.JaroWinkler;

public class Utils {
	public static Professor procurarProfessorPorNome(String name, List<Professor> professoresID) {

		Professor melhorMatch = null;
		double melhorSimilaridade = 0;
		JaroWinkler jw = new JaroWinkler();

		for (Professor p : professoresID) {

			double novaSimilaridade = jw.similarity(name, p.getNomeCompleto());
			if (novaSimilaridade > melhorSimilaridade) {
				melhorSimilaridade = novaSimilaridade;
				melhorMatch = p;
			}

		}
		return melhorMatch;
	}
}
