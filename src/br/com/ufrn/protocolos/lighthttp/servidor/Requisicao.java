package br.com.ufrn.protocolos.lighthttp.servidor;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Date;

import br.com.ufrn.protocolos.lighthttp.enumeradores.StatusEnum;
import br.com.ufrn.protocolos.lighthttp.requisicao.MensagemRequisicao;
import br.com.ufrn.protocolos.lighthttp.utils.ArquivoUtil;
import br.com.ufrn.protocolos.lighthttp.utils.ArrayUtils;
import br.com.ufrn.protocolos.lighthttp.validacao.ArquivoValida;
import br.com.ufrn.protocolos.lighthttp.validacao.TipoRequisicaoValida;

public class Requisicao extends Thread  {
	
	private int limite;
	private Socket socket;
	private StatusEnum status;
	private String tipoArquivo;
	private byte[] conteudo;
	private ArquivoUtil arquivoUtil;

	public Requisicao(int limite, Socket socket, StatusEnum status, byte[] conteudo) {
		super();
		this.limite = limite;
		this.socket = socket;
		this.status = status;
		this.conteudo = conteudo;
		this.arquivoUtil = new ArquivoUtil();
	}

	public Requisicao(Socket socket, int limite) {
		super();
		this.socket = socket;
		this.limite = limite;
		this.conteudo = null;
		this.arquivoUtil = new ArquivoUtil();
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			trataRequisicao();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void trataRequisicao() throws IOException {
		DataInputStream inBytes = new DataInputStream(socket.getInputStream());
		byte[] dadosRecebidos = new byte[limite];
		inBytes.read(dadosRecebidos);

		String mensagemRecebida = new String(dadosRecebidos);
		
		MensagemRequisicao mensagemRequisicao = new MensagemRequisicao(mensagemRecebida);			
		
		if(TipoRequisicaoValida.implementado(mensagemRequisicao.getTipo())) {
			if(ArquivoValida.existe(mensagemRequisicao.getRecurso())) {
				status = StatusEnum.OK;
				arquivoUtil.setCaminhoArquivo(mensagemRequisicao.getRecurso());
				tipoArquivo = arquivoUtil.getExtensaoFormatada();
				conteudo = arquivoUtil.getConteudo();
			} else {
				status = StatusEnum.NOT_FOUND;
				tipoArquivo = "text/plain";
				conteudo = "ERROR 404 - RECURSO NAO ENCONTRADO".getBytes();				
			}				
		} else {
			status = StatusEnum.NOT_IMPLEMENTED;
			tipoArquivo = "text/plain";			
			conteudo = "ERROR 501 - TIPO DE REQUISICAO NAO IMPLEMENTADO".getBytes();
		}
		
		byte[] resposta = montaMensagemResposta(status, tipoArquivo, conteudo);
		
		DataOutputStream outBytes = new DataOutputStream(socket.getOutputStream());
		outBytes.write(resposta);
		
		System.out.println("----------------------SERVER RESPONDE ---------------------");
		System.out.println(new String(resposta));
		resposta = null;
		System.out.println("-----------------------------------------------------------");
		
		socket.close();
	}

	
	public byte[] montaMensagemResposta(StatusEnum status, String tipoArquivo, byte[] conteudo) {
		String inicioMensagemResposta = "";		
		inicioMensagemResposta += "HTTP/1.1 " + status.status + " " + status.descricao + "\r\n";
		inicioMensagemResposta += "Date: " + new Date() + "\r\n";
		inicioMensagemResposta += "Content-Type: " + tipoArquivo + ";\r\n";		
		inicioMensagemResposta += "\r\n";
		
		byte[] mensagemResposta = ArrayUtils.concatenaArraysBytes(inicioMensagemResposta.getBytes(), conteudo);
		
		return mensagemResposta;
	}
}
