package br.ufpe.cin.leitor;

import java.io.File;
import java.io.IOException;
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


	public static Professor lerXML(File f)
			throws IOException, JDOMException{
		
		List<Capitulo> listaCapitulos = new ArrayList<Capitulo>();
		List<Livro> listaLivros = new ArrayList<Livro>(); //Lista com livros
		List<Artigo> listaArtigos = new ArrayList<Artigo>(); //lista com os artigos
		Professor professor = new Professor();

		SAXBuilder sb = new SAXBuilder();

		Document doc = sb.build(f);

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
												
												/*
												System.out.println(livro.getAttributeValue("TIPO"));
												System.out.println(livro.getAttributeValue("NATUREZA"));
												System.out.println(livro.getAttributeValue("TITULO-DO-LIVRO"));
												System.out.println(livro.getAttributeValue("ANO"));
												System.out.println(livro.getAttributeValue("PAIS-DE-PUBLICACAO"));
												System.out.println(livro.getAttributeValue("IDIOMA"));
												*/
												
											}else if(livro.getName().equals("DETALHAMENTO-DO-LIVRO")){
												
												novoLivro.setNumeroPaginas(livro.getAttributeValue("NUMERO-DE-PAGINAS"));
												novoLivro.setIsbn(livro.getAttributeValue("ISBN"));
												novoLivro.setNomeEditora(livro.getAttributeValue("NOME-DA-EDITORA"));
												
												/*
												System.out.println(livro.getAttributeValue("NUMERO-DE-PAGINAS"));
												System.out.println(livro.getAttributeValue("ISBN"));
												System.out.println(livro.getAttributeValue("NOME-DA-EDITORA"));
												*/
												
											}else if(livro.getName().equals("AUTORES")){
												
												autores.add(new Autor(livro.getAttributeValue("NOME-COMPLETO-DO-AUTOR"),livro.getAttributeValue("NOME-PARA-CITACAO")));
												/*
												System.out.println(livro.getAttributeValue("NOME-COMPLETO-DO-AUTOR"));
												System.out.println(livro.getAttributeValue("NOME-PARA-CITACAO"));
												*/
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
										/*
										System.out.println("Titulo: " + artigo.getAttributeValue("TITULO-DO-ARTIGO"));
										System.out.println("País: " + artigo.getAttributeValue("PAIS-DE-PUBLICACAO"));
										System.out.println("Idioma: " + artigo.getAttributeValue("IDIOMA"));
										System.out.println("Meio de Divulgação: " + artigo.getAttributeValue("MEIO-DE-DIVULGACAO"));
										 */

										//colocando no objeto Artigo
										novoArtigo.setTitulo(artigo.getAttributeValue("TITULO-DO-ARTIGO"));
										novoArtigo.setPais(artigo.getAttributeValue("PAIS-DE-PUBLICACAO"));
										novoArtigo.setIdioma(artigo.getAttributeValue("IDIOMA"));
										novoArtigo.setMeioDeDivulgacao(artigo.getAttributeValue("MEIO-DE-DIVULGACAO"));

									}else if(artigo.getName().equals("DETALHAMENTO-DO-ARTIGO")){

										novoArtigo.setTituloPeriodicoRevista(artigo.getAttributeValue("TITULO-DO-PERIODICO-OU-REVISTA"));
										novoArtigo.setIssn(artigo.getAttributeValue("ISSN"));
										novoArtigo.setVolume(artigo.getAttributeValue("VOLUME"));
										novoArtigo.setFasciculo(artigo.getAttributeValue("FASCICULO"));
										if(artigo.getAttributeValue("PAGINA-INICIAL").equals("")){
											novoArtigo.setPagInicial(0);
										}else{
											novoArtigo.setPagInicial(Integer.parseInt(artigo.getAttributeValue("PAGINA-INICIAL")));
										}
										if(artigo.getAttributeValue("PAGINA-FINAL").equals("")){
											novoArtigo.setPagFinal(0);
										}else{
											novoArtigo.setPagFinal(Integer.parseInt(artigo.getAttributeValue("PAGINA-FINAL")));
										}
										novoArtigo.setLocalPublicacao(artigo.getAttributeValue("LOCAL-DE-PUBLICACAO"));

										/*
										System.out.println("Periódico ou Revista: " + artigo.getAttributeValue("TITULO-DO-PERIODICO-OU-REVISTA"));
										System.out.println("ISSN: " + artigo.getAttributeValue("ISSN"));
										System.out.println("Volume: " + artigo.getAttributeValue("VOLUME"));
										System.out.println("Fasciculo: " + artigo.getAttributeValue("FASCICULO"));
										System.out.println("Pagina Inicial: " + artigo.getAttributeValue("PAGINA-INICIAL"));
										System.out.println("Página Final: " + artigo.getAttributeValue("PAGINA-FINAL"));
										System.out.println("Local de Publicação: " + artigo.getAttributeValue("LOCAL-DE-PUBLICACAO"));
										 */
									}else if(artigo.getName().equals("AUTORES")){
										autores.add(new Autor(artigo.getAttributeValue("NOME-COMPLETO-DO-AUTOR"),artigo.getAttributeValue("NOME-PARA-CITACAO")));
										//System.out.println("Nome Completo: " + artigo.getAttributeValue("NOME-COMPLETO-DO-AUTOR"));
										//System.out.println("Nome para citação: " + artigo.getAttributeValue("NOME-PARA-CITACAO"));
									}else if(artigo.getName().equals("PALAVRAS-CHAVE")){

										novoArtigo.setPalavrasChave(new PalavrasChave(artigo.getAttributeValue("PALAVRA-CHAVE-1"), artigo.getAttributeValue("PALAVRA-CHAVE-2"), 
												artigo.getAttributeValue("PALAVRA-CHAVE-3"), artigo.getAttributeValue("PALAVRA-CHAVE-4"), artigo.getAttributeValue("PALAVRA-CHAVE-5"), 
												artigo.getAttributeValue("PALAVRA-CHAVE-6")));

										/*
										System.out.println("Palavra Chave 1: " + artigo.getAttributeValue("PALAVRA-CHAVE-1"));
										System.out.println("Palavra Chave 2: " + artigo.getAttributeValue("PALAVRA-CHAVE-2"));
										System.out.println("Palavra Chave 3: " + artigo.getAttributeValue("PALAVRA-CHAVE-3"));
										System.out.println("Palavra Chave 4: " + artigo.getAttributeValue("PALAVRA-CHAVE-4"));
										System.out.println("Palavra Chave 5: " + artigo.getAttributeValue("PALAVRA-CHAVE-5"));
										System.out.println("Palavra Chave 6: " + artigo.getAttributeValue("PALAVRA-CHAVE-6"));
										 */
									}

								}
								novoArtigo.setAutores(autores);
								listaArtigos.add(novoArtigo);
							}
						}
					}
				}


			}

		}

		//Finaliza professor com artigos
		professor.setArtigos(listaArtigos);
		professor.setLivros(listaLivros);
		professor.setCapitulos(listaCapitulos);
		/*
		System.out.println(professor.getNomeCompleto());
		System.out.println(professor.getArtigos().get(0).getTitulo());
		System.out.println(professor.getArtigos().get(0).getTituloPeriodicoRevista());
		System.out.println(professor.getArtigos().get(1).getTitulo());
		System.out.println(professor.getArtigos().get(1).getTituloPeriodicoRevista());
		 */
		return professor;

	}

}
