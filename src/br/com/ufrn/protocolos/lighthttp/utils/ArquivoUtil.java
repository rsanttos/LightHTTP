package br.com.ufrn.protocolos.lighthttp.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ArquivoUtil {

	private byte[] conteudo;
	private String caminhoArquivo;
	private String extensao;
	
	private Map<String, String> tiposArquivo;

	public ArquivoUtil() {
		super();
		this.conteudo = null;
		this.caminhoArquivo = "";
		this.extensao = "";
		pesquisaExtensao();
		populaTiposArquivo();
	}

	public ArquivoUtil(String conteudo, String caminhoArquivo) {
		super();
		this.conteudo = null;
		this.caminhoArquivo = caminhoArquivo;
		this.extensao = "";
		pesquisaExtensao();
		populaTiposArquivo();
	}

	public ArquivoUtil(String caminhoArquivo) {
		super();
		this.caminhoArquivo = caminhoArquivo;
		this.conteudo = null;
		this.extensao = "";
		populaTiposArquivo();
	}

	public void pesquisaExtensao() {
		int posicao = caminhoArquivo.lastIndexOf(".");
		if (posicao > 0) {
			for (int i = posicao; i < caminhoArquivo.length(); i++) {
				extensao += caminhoArquivo.charAt(i);
			}
		}
	}

	public String getExtensao() {

		return extensao;
	}

	public void setExtensao(String extensao) {
		this.extensao = extensao;
	}

	public byte[] getConteudo() {
		String caminhoCompletoArquivo = "http_docs" + caminhoArquivo;
		
		conteudo = fileToByte(new File(caminhoCompletoArquivo));
		
//		try {
//			scanner = new Scanner(new InputStreamReader(new FileInputStream(caminhoCompletoArquivo)));
//			while (scanner.hasNext()) {
//				conteudo += scanner.next();
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}

		return conteudo;
	}

	public String getCaminhoArquivo() {
		return caminhoArquivo;
	}

	public void setCaminhoArquivo(String caminhoArquivo) {
		this.caminhoArquivo = caminhoArquivo;
		pesquisaExtensao();
	}


	public void setConteudo(byte[] conteudo) {
		this.conteudo = conteudo;
	}

	public void populaTiposArquivo() {
		tiposArquivo = new HashMap<String, String>();
		tiposArquivo.put(".html", "text/html");
		tiposArquivo.put(".jpg", "image/jpeg");
		tiposArquivo.put(".gif", "image/gif");
		tiposArquivo.put(".png", "image/png");		
	}

	public Map<String, String> getTiposArquivo() {
		return tiposArquivo;
	}

	public void setTiposArquivo(Map<String, String> tiposArquivo) {
		this.tiposArquivo = tiposArquivo;
	}
	
	public String getExtensaoFormatada() {
		return tiposArquivo.get(extensao);
	}
	


	public byte[] fileToByte(File arquivo) {
		int len = (int) arquivo.length();
		byte[] sendBuf = new byte[len];
		FileInputStream inFile = null;
		try {
			inFile = new FileInputStream(arquivo);
			inFile.read(sendBuf, 0, len);
			inFile.close();
		} catch (FileNotFoundException fnfex) {
			fnfex.printStackTrace();
		} catch (IOException ioex) {
			ioex.printStackTrace();
		}
		return sendBuf;
	}
}
