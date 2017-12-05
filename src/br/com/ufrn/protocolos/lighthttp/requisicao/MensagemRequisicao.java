package br.com.ufrn.protocolos.lighthttp.requisicao;

public class MensagemRequisicao {
	
	private String tipo;
	private String recurso;
	private String mensagem;
	
	public MensagemRequisicao(String mensagem) {
		this.mensagem = mensagem;
		tipo = "";
		recurso = "";
		define();
	}
	
	public void define() {
		
		int i = 0;
		while(i < mensagem.length() && mensagem.charAt(i) != ' ') {
			tipo += mensagem.charAt(i);
			i++;
		}

		i++;
		while(i < mensagem.length() && mensagem.charAt(i) != ' ') {
			recurso += mensagem.charAt(i);
			i++;
		}
	}
	
	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getRecurso() {
		return recurso;
	}

	public void setRecurso(String recurso) {
		this.recurso = recurso;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
	
}
