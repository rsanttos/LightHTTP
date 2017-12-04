package br.com.ufrn.protocolos.lighthttp.servidor;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {

	private static int LIMITE_TAMANHO_ARQUIVO = 1080;
	private static int PORTA = 7777;

	public void executa() throws IOException {

		System.out.println("Servidor em execucao.");
		ServerSocket connectionSocket = new ServerSocket(PORTA);
		while (true) {

			Socket socket = connectionSocket.accept();
			
			Requisicao requisicao = new Requisicao(socket, LIMITE_TAMANHO_ARQUIVO);
			requisicao.start();
		}
	}
}
