package br.com.ufrn.protocolos.lighthttp.utils;

public class ArrayUtils {
	public static byte[] concatenaArraysBytes(byte[] inicio, byte[] fim) {
		byte[] arrayCompleto = new byte[inicio.length + fim.length];
		
		for(int i = 0 ; i < inicio.length ; i++) {
			arrayCompleto[i] = inicio[i];
		}
		
		int i = inicio.length;
		for(int j = 0 ; j < fim.length ; j++, i++) {
			arrayCompleto[i] = fim[j];
		} 
		
		return arrayCompleto;
	}
}
