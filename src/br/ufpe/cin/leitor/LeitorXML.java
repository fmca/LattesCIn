package br.ufpe.cin.leitor;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

public class LeitorXML {


	public static void lerXML(File f)
			throws IOException, JDOMException{

		SAXBuilder sb = new SAXBuilder();

		Document doc = sb.build(f);

		Element root = (Element) doc.getRootElement();
		List lattes = root.getChildren();

		Iterator i = lattes.iterator();

		while(i.hasNext()){
			Element partes = (Element) i.next();

			//Pega as producoes bibliograficas
			if(partes.getName().equals("PRODUCAO-BIBLIOGRAFICA")){
				List producoesBibliograficas = partes.getChildren();

				Iterator iPB = producoesBibliograficas.iterator();

				while(iPB.hasNext()){
					Element producaoBibliografica = (Element) iPB.next();
					
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
								
								while(iArtigo.hasNext()){
									Element artigo = (Element) iArtigo.next();
									
									if(artigo.getName().equals("DADOS-BASICOS-DO-ARTIGO")){
										System.out.println("Titulo: " + artigo.getAttributeValue("TITULO-DO-ARTIGO"));
										System.out.println("País: " + artigo.getAttributeValue("PAIS-DE-PUBLICACAO"));
										System.out.println("Idioma: " + artigo.getAttributeValue("IDIOMA"));
										System.out.println("Meio de Divulgação: " + artigo.getAttributeValue("MEIO-DE-DIVULGACAO"));
									}else if(artigo.getName().equals("DETALHAMENTO-DO-ARTIGO")){
										System.out.println("Periódico ou Revista: " + artigo.getAttributeValue("TITULO-DO-PERIODICO-OU-REVISTA"));
										System.out.println("ISSN: " + artigo.getAttributeValue("ISSN"));
										System.out.println("Volume: " + artigo.getAttributeValue("VOLUME"));
										System.out.println("Fasciculo: " + artigo.getAttributeValue("FASCICULO"));
										System.out.println("Pagina Inicial: " + artigo.getAttributeValue("PAGINA-INICIAL"));
										System.out.println("Página Final: " + artigo.getAttributeValue("PAGINA-FINAL"));
										System.out.println("Local de Publicação: " + artigo.getAttributeValue("LOCAL-DE-PUBLICACAO"));
									}
									//System.out.println(artigo.getName());
								}
							}
						}
					}
				}


			}

		}

	}

	private List criaLista(){



		return null;

	}
}

/*
if(livro.getName().equals("DADOS-BASICOS-DO-LIVRO")){
	System.out.println("Ano: " + livro.getAttributeValue("ANO"));
	System.out.println("Título: " + livro.getAttributeValue("TITULO-DO-LIVRO"));
}else if(livro.getName().equals("AUTORES")){
	System.out.println("Autor: " + livro.getAttributeValue("NOME-COMPLETO-DO-AUTOR"));
}*/