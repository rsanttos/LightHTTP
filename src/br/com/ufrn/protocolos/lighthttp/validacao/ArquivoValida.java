package br.com.ufrn.protocolos.lighthttp.validacao;

import java.io.File;

public class ArquivoValida {
	
	private static String raiz = "http_docs";

	public static boolean existe(String caminhoArquivo) {		
		
		File arquivo = new File(raiz + caminhoArquivo) ;
		
		if(arquivo.exists() && arquivo.isFile()) {
			return true;
		} else {
			return false;
		} 
		
	}
}
