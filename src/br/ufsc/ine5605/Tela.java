package br.ufsc.ine5605;

import java.util.InputMismatchException;
import java.util.Scanner;

public abstract class Tela {
	protected Scanner leitor = new Scanner(System.in);
	
	/**
	 * Solicita a digitacao de um numero inteiro, entre 0 e o limite, ate que seja digitado corretamente
	 * @param limite
	 * 	valor maximo aceitavel
	 * @return
	 * 	Valor inteiro positivo lido do teclado
	 */
	protected int leInteiroPositivoAte(int limite) {
		int codigo = -1;
		boolean respostaOK = false;
		do {
			try {
				codigo = leitor.nextInt();
				if (codigo >= 0 && codigo <= limite) {
					respostaOK = true;
				} else {
					System.out.println("\nDigite um numero inteiro ate " + limite +"\n");
					respostaOK = false;
				}
			} catch (InputMismatchException e) {
				System.out.println("\nDigite um numero inteiro ate " + limite + "\n");
			}
			leitor.nextLine();
		} while (!respostaOK);
		
		return codigo;
	}
	
	/**
	 * Solicita a digitacao de um numero inteiro positivo, ate que seja digitado corretamente
	 * @return
	 * 	Valor inteiro positivo lido do teclado
	 */
	protected int leInteiroPositivo() {
		int codigo = -1;
		boolean respostaOK = false;
		do {
			try {
				codigo = leitor.nextInt();
				if (codigo > 0) {
					respostaOK = true;
				} else {
					respostaOK = false;
				}
			} catch (InputMismatchException e) {
				System.out.println("\nDigite um numero inteiro positivo\n");
			}
			leitor.nextLine();
		} while (!respostaOK);
		return codigo;
	}
	
	/**
	 * Verifica se o usuario digitou "s", "n", "S" ou "N"; gera excecao caso
	 * tenha sido digitado algo nao permitido
	 * 
	 * @param resposta
	 *            Resposta digitada pelo usuario
	 * @return "true", caso digitado "s" ou "S"; "false", caso digitado "n" ou
	 *         "N"
	 * @throws Exception
	 *             no caso de algo nao permitido ter sido digitado
	 */
	protected boolean verificaSN(String resposta) throws Exception {
		boolean opcao = false;
		if (resposta.equalsIgnoreCase("s")) {
			opcao = true;
		} else if (resposta.equalsIgnoreCase("n")) {
			opcao = false;
		} else {
			throw new Exception("\nDigite apenas uma letra, 's' ou 'n' \n");
		}
		return opcao;
	}
	
	/**
	 * Valida um nome (string) de acordo com a regra definida
	 * @param nome nome que sera validado
	 * @param regra regra segundo a qual o nome sera validado
	 * @return "true" ou "false"
	 */
	protected boolean validaNome(String nome, String regra) {
		return nome.matches(regra);
	}
	
}
