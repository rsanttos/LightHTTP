package br.com.ufrn.protocolos.lighthttp.enumeradores;

public enum StatusEnum {
	
	OK(200, "OK"),
	NOT_FOUND(404, "Not Found"),
	NOT_IMPLEMENTED(501, "Not implemented");
	
	public int status;
	public String descricao;
	
	private StatusEnum(int status, String descricao) {
		this.status = status;
		this.descricao = descricao;
	}
		
}
