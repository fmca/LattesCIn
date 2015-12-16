package br.ufpe.cin.opencin.lattes.leitor;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import br.ufpe.cin.opencin.lattes.entidades.Artigo;
import br.ufpe.cin.opencin.lattes.entidades.Autor;
import br.ufpe.cin.opencin.lattes.entidades.Capitulo;
import br.ufpe.cin.opencin.lattes.entidades.Doutorado;
import br.ufpe.cin.opencin.lattes.entidades.LinhaDePesquisa;
import br.ufpe.cin.opencin.lattes.entidades.Livro;
import br.ufpe.cin.opencin.lattes.entidades.Mestrado;
import br.ufpe.cin.opencin.lattes.entidades.PalavrasChave;
import br.ufpe.cin.opencin.lattes.entidades.Professor;
import br.ufpe.cin.opencin.lattes.entidades.TG;
import br.ufpe.cin.opencin.lattes.util.Utils;

public class LeitorXMLLattes {

	public static Professor lerXML(File f, List<Professor> professoresID) throws IOException, JDOMException {

		List<Capitulo> listaCapitulos = new ArrayList<Capitulo>();
		List<Livro> listaLivros = new ArrayList<Livro>(); // Lista com livros
		List<Artigo> listaArtigos = new ArrayList<Artigo>(); // lista com os
																// artigos
		List<Doutorado> listaDoutorado = new ArrayList<Doutorado>();
		List<Mestrado> listaMestrado = new ArrayList<Mestrado>();
		List<TG> listaTg = new ArrayList<TG>();
		Professor professor = new Professor();

		SAXBuilder sb = new SAXBuilder();
		FileInputStream fs = new FileInputStream(f);
		Document doc = sb.build(new InputStreamReader(fs, "UTF8"));

		Element root = (Element) doc.getRootElement();
		List<Element> lattes = root.getChildren();

		Iterator<Element> i = lattes.iterator();

		while (i.hasNext()) {

			Element parte = (Element) i.next();

			if (parte.getName().equals("DADOS-GERAIS")) {
				String nomeProfessor = parte.getAttributeValue("NOME-COMPLETO");
				professor = Utils.procurarProfessorPorNome(nomeProfessor, professoresID);
				System.out.println(professor);
				professor.setNomeCitacoes(parte.getAttributeValue("NOME-EM-CITACOES-BIBLIOGRAFICAS"));
				professor.setBirthDate(parte.getAttributeValue("PAIS-DE-NASCIMENTO"));
				professor.setNacionalidade(parte.getAttributeValue("NACIONALIDADE"));
				professor.setPaisNascimento(parte.getAttributeValue("PAIS-DE-NASCIMENTO"));
				professor.setUFNascimento(parte.getAttributeValue("UF-NASCIMENTO"));
				professor.setCidadeNascimento(parte.getAttributeValue("CIDADE-NASCIMENTO"));

				List<Element> atuacoesProfissionais = parte.getChild("ATUACOES-PROFISSIONAIS")
						.getChildren("ATUACAO-PROFISSIONAL");

				List<LinhaDePesquisa> linhasDePesquisa = new ArrayList<LinhaDePesquisa>();
				for (Element atuacaoProfissional : atuacoesProfissionais) {					
					try{
						List<Element> atividadesPesquisa = atuacaoProfissional
								.getChild("ATIVIDADES-DE-PESQUISA-E-DESENVOLVIMENTO")
								.getChildren("PESQUISA-E-DESENVOLVIMENTO");
						for (Element atividadePesquisa : atividadesPesquisa) {
							List<Element> linhasDePesquisaElement = atividadePesquisa.getChildren("LINHA-DE-PESQUISA");
							for (Element linhaDePesquisaElement : linhasDePesquisaElement) {
								linhasDePesquisa.add(new LinhaDePesquisa(linhaDePesquisaElement.getAttributeValue("TITULO-DA-LINHA-DE-PESQUISA")));
							}
						}
					}catch(NullPointerException e){
						//Ignore
					}
					
				}
				professor.setLinhasDePesquisas(linhasDePesquisa);
			}

			// Pega as producoes bibliograficas
			if (parte.getName().equals("PRODUCAO-BIBLIOGRAFICA")) {
				List<Element> producoesBibliograficas = parte.getChildren();

				Iterator<Element> iPB = producoesBibliograficas.iterator();

				while (iPB.hasNext()) {
					Element producaoBibliografica = (Element) iPB.next();

					if (producaoBibliografica.getName().equals("LIVROS-E-CAPITULOS")) {
						List<Element> livrosCapitulos = producaoBibliografica.getChildren();

						Iterator<Element> iLC = livrosCapitulos.iterator();

						while (iLC.hasNext()) {
							Element livroCapitulo = (Element) iLC.next();

							if (livroCapitulo.getName().equals("LIVROS-PUBLICADOS-OU-ORGANIZADOS")) {
								List<Element> livrosPublicados = livroCapitulo.getChildren();
								Iterator<Element> iLP = livrosPublicados.iterator();

								while (iLP.hasNext()) {
									Element livroPublicado = (Element) iLP.next();

									if (livroPublicado.getName().equals("LIVRO-PUBLICADO-OU-ORGANIZADO")) {
										List<Element> livros = livroPublicado.getChildren();
										Iterator<Element> iLivro = livros.iterator();

										Livro novoLivro = new Livro();
										List<Autor> autores = new ArrayList<Autor>();

										while (iLivro.hasNext()) {
											Element livro = (Element) iLivro.next();

											if (livro.getName().equals("DADOS-BASICOS-DO-LIVRO")) {

												novoLivro.setTipo(livro.getAttributeValue("TIPO"));
												novoLivro.setNatureza(livro.getAttributeValue("NATUREZA"));
												novoLivro.setTitulo(livro.getAttributeValue("TITULO-DO-LIVRO"));
												novoLivro.setAno(livro.getAttributeValue("ANO"));
												novoLivro.setPais(livro.getAttributeValue("PAIS-DE-PUBLICACAO"));
												novoLivro.setIdioma(livro.getAttributeValue("IDIOMA"));

											} else if (livro.getName().equals("DETALHAMENTO-DO-LIVRO")) {

												novoLivro
														.setNumeroPaginas(livro.getAttributeValue("NUMERO-DE-PAGINAS"));
												novoLivro.setIsbn(livro.getAttributeValue("ISBN"));
												novoLivro.setNomeEditora(livro.getAttributeValue("NOME-DA-EDITORA"));

											} else if (livro.getName().equals("AUTORES")) {

												autores.add(new Autor(livro.getAttributeValue("NOME-COMPLETO-DO-AUTOR"),
														livro.getAttributeValue("NOME-PARA-CITACAO")));
											}

										}
										novoLivro.setAutores(autores);
										listaLivros.add(novoLivro);

									}
								}

							} else if (livroCapitulo.getName().equals("CAPITULOS-DE-LIVROS-PUBLICADOS")) {
								List<Element> capitulosPublicados = livroCapitulo.getChildren();
								Iterator<Element> iCP = capitulosPublicados.iterator();

								while (iCP.hasNext()) {
									Element capitulos = (Element) iCP.next();
									if (capitulos.getName().equals("CAPITULO-DE-LIVRO-PUBLICADO")) {
										List<Element> capitulosLista = capitulos.getChildren();
										Iterator<Element> iCapitulo = capitulosLista.iterator();

										Capitulo novoCapitulo = new Capitulo();
										List<Autor> autores = new ArrayList<Autor>();

										while (iCapitulo.hasNext()) {

											Element capitulo = (Element) iCapitulo.next();

											if (capitulo.getName().equals("DADOS-BASICOS-DO-CAPITULO")) {

												novoCapitulo.setTipo(capitulo.getAttributeValue("TIPO"));
												novoCapitulo.setTituloCapitulo(
														capitulo.getAttributeValue("TITULO-DO-CAPITULO-DO-LIVRO"));
												novoCapitulo.setAno(capitulo.getAttributeValue("ANO"));
												novoCapitulo.setPais(capitulo.getAttributeValue("PAIS-DE-PUBLICACAO"));
												novoCapitulo.setIdioma(capitulo.getAttributeValue("IDIOMA"));

											} else if (capitulo.getName().equals("DETALHAMENTO-DO-CAPITULO")) {

												novoCapitulo
														.setTituloLivro(capitulo.getAttributeValue("TITULO-DO-LIVRO"));
												novoCapitulo
														.setPagInicial(capitulo.getAttributeValue("PAGINA-INICIAL"));
												novoCapitulo.setPagFinal(capitulo.getAttributeValue("PAGINA-FINAL"));
												novoCapitulo.setIsbn(capitulo.getAttributeValue("ISBN"));
												novoCapitulo
														.setOrganizadores(capitulo.getAttributeValue("ORGANIZADORES"));
												novoCapitulo
														.setNomeEditora(capitulo.getAttributeValue("NOME-DA-EDITORA"));

											} else if (capitulo.getName().equals("AUTORES")) {

												autores.add(
														new Autor(capitulo.getAttributeValue("NOME-COMPLETO-DO-AUTOR"),
																capitulo.getAttributeValue("NOME-PARA-CITACAO")));

											}

										}

										novoCapitulo.setAutores(autores);
										listaCapitulos.add(novoCapitulo);

									}

								}
							}

						}

					}

					// Pega a parte de artigos publicados
					if (producaoBibliografica.getName().equals("ARTIGOS-PUBLICADOS")) {
						List artigosPublicados = producaoBibliografica.getChildren();

						Iterator iAP = artigosPublicados.iterator();

						while (iAP.hasNext()) {
							Element artigoPublicado = (Element) iAP.next();

							// pega os atributos do artigo
							if (artigoPublicado.getName().equals("ARTIGO-PUBLICADO")) {
								List artigos = artigoPublicado.getChildren();

								Iterator iArtigo = artigos.iterator();

								// Cria lista de Autores
								List<Autor> autores = new ArrayList<Autor>();
								Artigo novoArtigo = new Artigo();

								// enqaunto houver atributos dentro de artigo
								while (iArtigo.hasNext()) {
									Element artigo = (Element) iArtigo.next();

									if (artigo.getName().equals("DADOS-BASICOS-DO-ARTIGO")) {

										// colocando no objeto Artigo
										novoArtigo.setTitulo(artigo.getAttributeValue("TITULO-DO-ARTIGO"));
										novoArtigo.setPais(artigo.getAttributeValue("PAIS-DE-PUBLICACAO"));
										novoArtigo.setIdioma(artigo.getAttributeValue("IDIOMA"));
										novoArtigo.setMeioDeDivulgacao(artigo.getAttributeValue("MEIO-DE-DIVULGACAO"));
										novoArtigo.setAno(Integer.parseInt(artigo.getAttributeValue("ANO-DO-ARTIGO")));

									} else if (artigo.getName().equals("DETALHAMENTO-DO-ARTIGO")) {

										novoArtigo.setTituloPeriodicoRevista(
												artigo.getAttributeValue("TITULO-DO-PERIODICO-OU-REVISTA"));
										novoArtigo.setIssn(artigo.getAttributeValue("ISSN"));
										novoArtigo.setVolume(artigo.getAttributeValue("VOLUME"));
										novoArtigo.setFasciculo(artigo.getAttributeValue("FASCICULO"));
										novoArtigo.setPagInicial(artigo.getAttributeValue("PAGINA-INICIAL"));
										novoArtigo.setPagFinal(artigo.getAttributeValue("PAGINA-FINAL"));
										novoArtigo.setLocalPublicacao(artigo.getAttributeValue("LOCAL-DE-PUBLICACAO"));

									} else if (artigo.getName().equals("AUTORES")) {
										autores.add(new Autor(artigo.getAttributeValue("NOME-COMPLETO-DO-AUTOR"),
												artigo.getAttributeValue("NOME-PARA-CITACAO")));

									} else if (artigo.getName().equals("PALAVRAS-CHAVE")) {

										novoArtigo.setPalavrasChave(
												new PalavrasChave(artigo.getAttributeValue("PALAVRA-CHAVE-1"),
														artigo.getAttributeValue("PALAVRA-CHAVE-2"),
														artigo.getAttributeValue("PALAVRA-CHAVE-3"),
														artigo.getAttributeValue("PALAVRA-CHAVE-4"),
														artigo.getAttributeValue("PALAVRA-CHAVE-5"),
														artigo.getAttributeValue("PALAVRA-CHAVE-6")));

									}

								}
								novoArtigo.setAutores(autores);
								listaArtigos.add(novoArtigo);
							}
						}
					}

					// Pega a parte de artigos publicados
					if (producaoBibliografica.getName().equals("TRABALHOS-EM-EVENTOS")) {
						List artigosPublicados = producaoBibliografica.getChildren();

						Iterator iAP = artigosPublicados.iterator();

						while (iAP.hasNext()) {
							Element artigoPublicado = (Element) iAP.next();

							// pega os atributos do artigo
							if (artigoPublicado.getName().equals("TRABALHO-EM-EVENTOS")) {
								List artigos = artigoPublicado.getChildren();

								Iterator iArtigo = artigos.iterator();

								// Cria lista de Autores
								List<Autor> autores = new ArrayList<Autor>();
								Artigo novoArtigo = new Artigo();

								// enqaunto houver atributos dentro de artigo
								while (iArtigo.hasNext()) {
									Element artigo = (Element) iArtigo.next();

									if (artigo.getName().equals("DADOS-BASICOS-DO-TRABALHO")) {

										// colocando no objeto Artigo
										novoArtigo.setTitulo(artigo.getAttributeValue("TITULO-DO-TRABALHO"));
										novoArtigo.setPais(artigo.getAttributeValue("PAIS-DO-EVENTO"));
										novoArtigo.setIdioma(artigo.getAttributeValue("IDIOMA"));
										novoArtigo.setMeioDeDivulgacao(artigo.getAttributeValue("MEIO-DE-DIVULGACAO"));
										novoArtigo
												.setAno(Integer.parseInt(artigo.getAttributeValue("ANO-DO-TRABALHO")));

									} else if (artigo.getName().equals("DETALHAMENTO-DO-TRABALHO")) {

										novoArtigo
												.setTituloPeriodicoRevista(artigo.getAttributeValue("NOME-DO-EVENTO"));

										novoArtigo.setPagInicial(artigo.getAttributeValue("PAGINA-INICIAL"));
										novoArtigo.setPagFinal(artigo.getAttributeValue("PAGINA-FINAL"));
										novoArtigo.setLocalPublicacao(artigo.getAttributeValue("CIDADE-DO-EVENTO"));

									} else if (artigo.getName().equals("AUTORES")) {
										autores.add(new Autor(artigo.getAttributeValue("NOME-COMPLETO-DO-AUTOR"),
												artigo.getAttributeValue("NOME-PARA-CITACAO")));

									}

								}
								novoArtigo.setAutores(autores);
								listaArtigos.add(novoArtigo);
							}
						}
					}
				}

			} else if (parte.getName().equals("OUTRA-PRODUCAO")) {

				List outrasProducoes = parte.getChildren();

				Iterator iOP = outrasProducoes.iterator();

				while (iOP.hasNext()) {
					Element outraProducao = (Element) iOP.next();

					if (outraProducao.getName().equals("ORIENTACOES-CONCLUIDAS")) {

						List orientacoesConcluidas = outraProducao.getChildren();

						Iterator iOC = orientacoesConcluidas.iterator();

						while (iOC.hasNext()) {

							Element orientacaoConcluida = (Element) iOC.next();

							if (orientacaoConcluida.getName().equals("ORIENTACOES-CONCLUIDAS-PARA-MESTRADO")) {

								Mestrado novoMestrado = new Mestrado();
								List orientacoesMestrado = orientacaoConcluida.getChildren();
								Iterator iOM = orientacoesMestrado.iterator();

								while (iOM.hasNext()) {

									Element orientacaoMestrado = (Element) iOM.next();

									if (orientacaoMestrado.getName()
											.equals("DADOS-BASICOS-DE-ORIENTACOES-CONCLUIDAS-PARA-MESTRADO")) {
										novoMestrado.setTitulo(orientacaoMestrado.getAttributeValue("TITULO"));
										novoMestrado.setTipo_mestrado(orientacaoMestrado.getAttributeValue("TIPO"));
										novoMestrado.setAno(orientacaoMestrado.getAttributeValue("ANO"));
										novoMestrado.setIdioma(orientacaoMestrado.getAttributeValue("IDIOMA"));
									}
									if (orientacaoMestrado.getName()
											.equals("DETALHAMENTO-DE-ORIENTACOES-CONCLUIDAS-PARA-MESTRADO")) {
										novoMestrado.setTipo_orientacao(
												orientacaoMestrado.getAttributeValue("TIPO-DE-ORIENTACAO"));
										novoMestrado.setInstituicao(
												orientacaoMestrado.getAttributeValue("NOME-DA-INSTITUICAO"));
										novoMestrado.setNome_orientado(
												orientacaoMestrado.getAttributeValue("NOME-DO-ORIENTADO"));
										novoMestrado.setAgencia_financiadora(
												orientacaoMestrado.getAttributeValue("NOME-DA-AGENCIA"));
										novoMestrado.setCurso(orientacaoMestrado.getAttributeValue("NOME-DO-CURSO"));
									}
								}

								listaMestrado.add(novoMestrado);

							} else if (orientacaoConcluida.getName().equals("ORIENTACOES-CONCLUIDAS-PARA-DOUTORADO")) {

								Doutorado novoDoutorado = new Doutorado();
								List orientacoesDoutorado = orientacaoConcluida.getChildren();
								Iterator iOD = orientacoesDoutorado.iterator();

								while (iOD.hasNext()) {

									Element orientacaoDoutorado = (Element) iOD.next();

									if (orientacaoDoutorado.getName()
											.equals("DADOS-BASICOS-DE-ORIENTACOES-CONCLUIDAS-PARA-DOUTORADO")) {
										novoDoutorado.setTitulo(orientacaoDoutorado.getAttributeValue("TITULO"));
										novoDoutorado.setAno(orientacaoDoutorado.getAttributeValue("ANO"));
										novoDoutorado.setIdioma(orientacaoDoutorado.getAttributeValue("IDIOMA"));
									}
									if (orientacaoDoutorado.getName()
											.equals("DETALHAMENTO-DE-ORIENTACOES-CONCLUIDAS-PARA-DOUTORADO")) {
										novoDoutorado.setTipo_orientacao(
												orientacaoDoutorado.getAttributeValue("TIPO-DE-ORIENTACAO"));
										novoDoutorado.setInstituicao(
												orientacaoDoutorado.getAttributeValue("NOME-DA-INSTITUICAO"));
										novoDoutorado.setNome_orientado(
												orientacaoDoutorado.getAttributeValue("NOME-DO-ORIENTADO"));
										novoDoutorado.setAgencia_financiadora(
												orientacaoDoutorado.getAttributeValue("NOME-DA-AGENCIA"));
										novoDoutorado.setCurso(orientacaoDoutorado.getAttributeValue("NOME-DO-CURSO"));
									}
								}
								listaDoutorado.add(novoDoutorado);

							} else if (orientacaoConcluida.getName().equals("OUTRAS-ORIENTACOES-CONCLUIDAS")) {

								TG novoTg = new TG();
								List orientacoesTg = orientacaoConcluida.getChildren();
								Iterator iTG = orientacoesTg.iterator();
								boolean eTG = false;

								while (iTG.hasNext()) {

									Element orientacaoTg = (Element) iTG.next();

									if (orientacaoTg.getName()
											.equals("DADOS-BASICOS-DE-OUTRAS-ORIENTACOES-CONCLUIDAS")) {

										if (orientacaoTg.getAttributeValue("NATUREZA")
												.equals("TRABALHO_DE_CONCLUSAO_DE_CURSO_GRADUACAO")) {
											eTG = true;
											novoTg.setTitulo(orientacaoTg.getAttributeValue("TITULO"));
											novoTg.setAno(orientacaoTg.getAttributeValue("ANO"));
											novoTg.setIdioma(orientacaoTg.getAttributeValue("IDIOMA"));
										} else {
											eTG = false;
										}

									}
									if (orientacaoTg.getName().equals("DETALHAMENTO-DE-OUTRAS-ORIENTACOES-CONCLUIDAS")
											&& eTG == true) {
										novoTg.setInstituicao(orientacaoTg.getAttributeValue("NOME-DA-INSTITUICAO"));
										novoTg.setNome_orientado(orientacaoTg.getAttributeValue("NOME-DO-ORIENTADO"));
										novoTg.setCurso(orientacaoTg.getAttributeValue("NOME-DO-CURSO"));
										listaTg.add(novoTg);
									}
								}

							}

						}
					}

				}
			}

		}
		// Finaliza professor com artigos
		professor.setArtigos(listaArtigos);
		professor.setLivros(listaLivros);
		professor.setCapitulos(listaCapitulos);
		professor.setMestrado(listaMestrado);
		professor.setDoutorado(listaDoutorado);
		professor.setTg(listaTg);

		System.out.println(professor);

		return professor;

	}

}
