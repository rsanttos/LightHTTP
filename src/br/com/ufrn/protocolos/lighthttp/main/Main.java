package br.com.ufrn.protocolos.lighthttp.main;

import java.io.IOException;

import br.com.ufrn.protocolos.lighthttp.servidor.Servidor;

public class Main {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		Servidor servidor = new Servidor();
		servidor.executa();
	}

}
