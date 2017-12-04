package br.com.ufrn.protocolos.lighthttp.validacao;

public class TipoRequisicaoValida {

	public static boolean implementado(String tipo) {
		if(tipo.equals("GET")) {
			return true;
		} else {
			return false;
		}
	}
}
