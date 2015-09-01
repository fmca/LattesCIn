package br.ufpe.cin.leitor;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import br.ufpe.cin.entidades.Artigo;
import br.ufpe.cin.entidades.Autor;
import br.ufpe.cin.entidades.Capitulo;
import br.ufpe.cin.entidades.Livro;
import br.ufpe.cin.entidades.PalavrasChave;
import br.ufpe.cin.entidades.Professor;

public class LeitorXML {


	public static Professor lerXML(File f, List<Professor> professoresID)
			throws IOException, JDOMException{

		List<Capitulo> listaCapitulos = new ArrayList<Capitulo>();
		List<Livro> listaLivros = new ArrayList<Livro>(); //Lista com livros
		List<Artigo> listaArtigos = new ArrayList<Artigo>(); //lista com os artigos
		Professor professor = new Professor();

		SAXBuilder sb = new SAXBuilder();
		FileInputStream fs = new FileInputStream(f);
		Document doc = sb.build(new InputStreamReader(fs, "UTF8"));

		Element root = (Element) doc.getRootElement();
		List lattes = root.getChildren();

		Iterator i = lattes.iterator();

		while(i.hasNext()){
			Element partes = (Element) i.next();

			if(partes.getName().equals("DADOS-GERAIS")){

				professor = new Professor(partes.getAttributeValue("NOME-COMPLETO"), partes.getAttributeValue("NOME-EM-CITACOES-BIBLIOGRAFICAS"), partes.getAttributeValue("NACIONALIDADE"), 
						partes.getAttributeValue("PAIS-DE-NASCIMENTO"), partes.getAttributeValue("UF-NASCIMENTO"), partes.getAttributeValue("CIDADE-NASCIMENTO"));

			}


			//Pega as producoes bibliograficas
			if(partes.getName().equals("PRODUCAO-BIBLIOGRAFICA")){
				List producoesBibliograficas = partes.getChildren();

				Iterator iPB = producoesBibliograficas.iterator();

				while(iPB.hasNext()){
					Element producaoBibliografica = (Element) iPB.next();


					if(producaoBibliografica.getName().equals("LIVROS-E-CAPITULOS")){
						List livrosCapitulos = producaoBibliografica.getChildren();

						Iterator iLC = livrosCapitulos.iterator();

						while(iLC.hasNext()){
							Element livroCapitulo = (Element) iLC.next();

							if(livroCapitulo.getName().equals("LIVROS-PUBLICADOS-OU-ORGANIZADOS")){
								List livrosPublicados = livroCapitulo.getChildren();
								Iterator iLP = livrosPublicados.iterator();

								while(iLP.hasNext()){
									Element livroPublicado = (Element) iLP.next();

									if(livroPublicado.getName().equals("LIVRO-PUBLICADO-OU-ORGANIZADO")){
										List livros = livroPublicado.getChildren();
										Iterator iLivro = livros.iterator();

										Livro novoLivro = new Livro();
										List<Autor> autores = new ArrayList<Autor>();

										while(iLivro.hasNext()){
											Element livro = (Element) iLivro.next();

											if(livro.getName().equals("DADOS-BASICOS-DO-LIVRO")){

												novoLivro.setTipo(livro.getAttributeValue("TIPO"));
												novoLivro.setNatureza(livro.getAttributeValue("NATUREZA"));
												novoLivro.setTitulo(livro.getAttributeValue("TITULO-DO-LIVRO"));
												novoLivro.setAno(livro.getAttributeValue("ANO"));
												novoLivro.setPais(livro.getAttributeValue("PAIS-DE-PUBLICACAO"));
												novoLivro.setIdioma(livro.getAttributeValue("IDIOMA"));


											}else if(livro.getName().equals("DETALHAMENTO-DO-LIVRO")){

												novoLivro.setNumeroPaginas(livro.getAttributeValue("NUMERO-DE-PAGINAS"));
												novoLivro.setIsbn(livro.getAttributeValue("ISBN"));
												novoLivro.setNomeEditora(livro.getAttributeValue("NOME-DA-EDITORA"));

											}else if(livro.getName().equals("AUTORES")){

												autores.add(new Autor(livro.getAttributeValue("NOME-COMPLETO-DO-AUTOR"),livro.getAttributeValue("NOME-PARA-CITACAO")));
											}

										}
										novoLivro.setAutores(autores);
										listaLivros.add(novoLivro);

									}
								}


							}else if(livroCapitulo.getName().equals("CAPITULOS-DE-LIVROS-PUBLICADOS")){
								List capitulosPublicados = livroCapitulo.getChildren();
								Iterator iCP = capitulosPublicados.iterator();


								while(iCP.hasNext()){
									Element capitulos = (Element) iCP.next();
									if(capitulos.getName().equals("CAPITULO-DE-LIVRO-PUBLICADO")){
										List capitulosLista = capitulos.getChildren();
										Iterator iCapitulo = capitulosLista.iterator();

										Capitulo novoCapitulo = new Capitulo();
										List<Autor> autores = new ArrayList<Autor>();

										while(iCapitulo.hasNext()){

											Element capitulo = (Element) iCapitulo.next();

											if(capitulo.getName().equals("DADOS-BASICOS-DO-CAPITULO")){

												novoCapitulo.setTipo(capitulo.getAttributeValue("TIPO"));
												novoCapitulo.setTituloCapitulo(capitulo.getAttributeValue("TITULO-DO-CAPITULO-DO-LIVRO"));
												novoCapitulo.setAno(capitulo.getAttributeValue("ANO"));
												novoCapitulo.setPais(capitulo.getAttributeValue("PAIS-DE-PUBLICACAO"));
												novoCapitulo.setIdioma(capitulo.getAttributeValue("IDIOMA"));

											}else if(capitulo.getName().equals("DETALHAMENTO-DO-CAPITULO")){

												novoCapitulo.setTituloLivro(capitulo.getAttributeValue("TITULO-DO-LIVRO"));
												novoCapitulo.setPagInicial(capitulo.getAttributeValue("PAGINA-INICIAL"));
												novoCapitulo.setPagFinal(capitulo.getAttributeValue("PAGINA-FINAL"));
												novoCapitulo.setIsbn(capitulo.getAttributeValue("ISBN"));
												novoCapitulo.setOrganizadores(capitulo.getAttributeValue("ORGANIZADORES"));
												novoCapitulo.setNomeEditora(capitulo.getAttributeValue("NOME-DA-EDITORA"));

											}else if(capitulo.getName().equals("AUTORES")){

												autores.add(new Autor(capitulo.getAttributeValue("NOME-COMPLETO-DO-AUTOR"),capitulo.getAttributeValue("NOME-PARA-CITACAO")));

											}

										}

										novoCapitulo.setAutores(autores);
										listaCapitulos.add(novoCapitulo);

									}


								}
							}

						}


					}


					//Pega a parte de artigos publicados
					if(producaoBibliografica.getName().equals("ARTIGOS-PUBLICADOS")){
						List artigosPublicados = producaoBibliografica.getChildren();

						Iterator iAP = artigosPublicados.iterator();

						while(iAP.hasNext()){
							Element artigoPublicado = (Element) iAP.next();

							//pega os atributos do artigo
							if(artigoPublicado.getName().equals("ARTIGO-PUBLICADO")){
								List artigos = artigoPublicado.getChildren();

								Iterator iArtigo = artigos.iterator();

								//Cria lista de Autores
								List<Autor> autores = new ArrayList<Autor>();
								Artigo novoArtigo = new Artigo();

								//enqaunto houver atributos dentro de artigo
								while(iArtigo.hasNext()){
									Element artigo = (Element) iArtigo.next();

									if(artigo.getName().equals("DADOS-BASICOS-DO-ARTIGO")){


										//colocando no objeto Artigo
										novoArtigo.setTitulo(artigo.getAttributeValue("TITULO-DO-ARTIGO"));
										novoArtigo.setPais(artigo.getAttributeValue("PAIS-DE-PUBLICACAO"));
										novoArtigo.setIdioma(artigo.getAttributeValue("IDIOMA"));
										novoArtigo.setMeioDeDivulgacao(artigo.getAttributeValue("MEIO-DE-DIVULGACAO"));
										novoArtigo.setAno(Integer.parseInt(artigo.getAttributeValue("ANO-DO-ARTIGO")));

									}else if(artigo.getName().equals("DETALHAMENTO-DO-ARTIGO")){

										novoArtigo.setTituloPeriodicoRevista(artigo.getAttributeValue("TITULO-DO-PERIODICO-OU-REVISTA"));
										novoArtigo.setIssn(artigo.getAttributeValue("ISSN"));
										novoArtigo.setVolume(artigo.getAttributeValue("VOLUME"));
										novoArtigo.setFasciculo(artigo.getAttributeValue("FASCICULO"));
										novoArtigo.setPagInicial(artigo.getAttributeValue("PAGINA-INICIAL"));
										novoArtigo.setPagFinal(artigo.getAttributeValue("PAGINA-FINAL"));
										novoArtigo.setLocalPublicacao(artigo.getAttributeValue("LOCAL-DE-PUBLICACAO"));


									}else if(artigo.getName().equals("AUTORES")){
										autores.add(new Autor(artigo.getAttributeValue("NOME-COMPLETO-DO-AUTOR"),artigo.getAttributeValue("NOME-PARA-CITACAO")));

									}else if(artigo.getName().equals("PALAVRAS-CHAVE")){

										novoArtigo.setPalavrasChave(new PalavrasChave(artigo.getAttributeValue("PALAVRA-CHAVE-1"), artigo.getAttributeValue("PALAVRA-CHAVE-2"), 
												artigo.getAttributeValue("PALAVRA-CHAVE-3"), artigo.getAttributeValue("PALAVRA-CHAVE-4"), artigo.getAttributeValue("PALAVRA-CHAVE-5"), 
												artigo.getAttributeValue("PALAVRA-CHAVE-6")));


									}

								}
								novoArtigo.setAutores(autores);
								listaArtigos.add(novoArtigo);
							}
						}
					}

					//Pega a parte de artigos publicados
					if(producaoBibliografica.getName().equals("TRABALHOS-EM-EVENTOS")){
						List artigosPublicados = producaoBibliografica.getChildren();

						Iterator iAP = artigosPublicados.iterator();

						while(iAP.hasNext()){
							Element artigoPublicado = (Element) iAP.next();

							//pega os atributos do artigo
							if(artigoPublicado.getName().equals("TRABALHO-EM-EVENTOS")){
								List artigos = artigoPublicado.getChildren();

								Iterator iArtigo = artigos.iterator();

								//Cria lista de Autores
								List<Autor> autores = new ArrayList<Autor>();
								Artigo novoArtigo = new Artigo();

								//enqaunto houver atributos dentro de artigo
								while(iArtigo.hasNext()){
									Element artigo = (Element) iArtigo.next();

									if(artigo.getName().equals("DADOS-BASICOS-DO-TRABALHO")){


										//colocando no objeto Artigo
										novoArtigo.setTitulo(artigo.getAttributeValue("TITULO-DO-TRABALHO"));
										novoArtigo.setPais(artigo.getAttributeValue("PAIS-DO-EVENTO"));
										novoArtigo.setIdioma(artigo.getAttributeValue("IDIOMA"));
										novoArtigo.setMeioDeDivulgacao(artigo.getAttributeValue("MEIO-DE-DIVULGACAO"));
										novoArtigo.setAno(Integer.parseInt(artigo.getAttributeValue("ANO-DO-TRABALHO")));

									}else if(artigo.getName().equals("DETALHAMENTO-DO-TRABALHO")){

										novoArtigo.setTituloPeriodicoRevista(artigo.getAttributeValue("NOME-DO-EVENTO"));

										novoArtigo.setPagInicial(artigo.getAttributeValue("PAGINA-INICIAL"));
										novoArtigo.setPagFinal(artigo.getAttributeValue("PAGINA-FINAL"));
										novoArtigo.setLocalPublicacao(artigo.getAttributeValue("CIDADE-DO-EVENTO"));


									}else if(artigo.getName().equals("AUTORES")){
										autores.add(new Autor(artigo.getAttributeValue("NOME-COMPLETO-DO-AUTOR"),artigo.getAttributeValue("NOME-PARA-CITACAO")));

									}

								}
								novoArtigo.setAutores(autores);
								listaArtigos.add(novoArtigo);
							}
						}
					}
				}


			}else if(partes.getName().equals("OUTRA-PRODUCAO")){

				List outrasProducoes = partes.getChildren();

				Iterator iOP = outrasProducoes.iterator();

				while(iOP.hasNext()){
					Element outraProducao = (Element) iOP.next();


					if(outraProducao.getName().equals("ORIENTACOES-CONCLUIDAS")){
						
						List orientacoesConcluidas = outraProducao.getChildren();

						Iterator iOC = orientacoesConcluidas.iterator();
						
						while(iOC.hasNext()){
							
							Element orientacaoConcluida = (Element) iOC.next();
							
							if(orientacaoConcluida.getName().equals("ORIENTACOES-CONCLUIDAS-PARA-MESTRADO")){
								
								
								
							}else if(orientacaoConcluida.getName().equals("ORIENTACOES-CONCLUIDAS-PARA-DOUTORADO")){
								
								
								
							}else if(orientacaoConcluida.getName().equals("OUTRAS-ORIENTACOES-CONCLUIDAS")){
								
								
								
							}
							
						}
					}


				}
			}

		}

		professor.setProfessorID(LeitorXML.verURL(professor.getNomeCompleto(), professoresID));
		//Finaliza professor com artigos
		professor.setArtigos(listaArtigos);
		professor.setLivros(listaLivros);
		professor.setCapitulos(listaCapitulos);

		return professor;

	}

	public static String verURL(String name, List<Professor> professoresID){

		for(Professor p:professoresID){
			//System.out.println(name);
			if(p.getNomeCompleto().equals(name)){
				return p.getProfessorID();
			}

		}

		//System.out.println(name);
		return "";
	}

}
