package br.ufsc.ine5605;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.ArrayList;

public class TelaCargo {

	private int codigo = 2;
	private String nome;
	private boolean ehGerencial = false;
	private boolean possuiAcesso = false;
	private ArrayList<Horario> horariosPermitidos = new ArrayList<Horario>();
	private boolean respostaOK = false;

	private Scanner sc = new Scanner(System.in);
	//usar scanner da classe pai tela
	//extends tela
	//atributos deveriam ser temporarios do metodo incluirCargo
	//sysout.OUT no lugar de err
	
	/**
	 * Solicita via Scanner os dados necessarios para o cadastro de um novo usuario, retornando as informacoes
	 * em uma classe temporaria a ser utilizada pelo ControladorCargo
	 * 
	 * @return DadosCadastroCargo(int codigo, String nome, boolean ehGerencial, boolean possuiAcesso, ArrayList horariosPermitidos) 
	 */
	public DadosCadastroCargo incluirCargo() {
		System.out.println("==== Digite os dados solicitados ====");

		do {
			respostaOK = false;
			try {
				System.out.println("Digite um codigo para o cargo (numero inteiro positivo): ");
				codigo = sc.nextInt();
				if (codigo > 0) {
					respostaOK = true;
				} else {
					respostaOK = false;
				}
			} catch (InputMismatchException e) {
				System.err.println("\nDigite um numero inteiro positivo\n");
			}
			sc.nextLine(); // limpa o buffer
		} while (!respostaOK);

		System.out.println("Digite um nome para o cargo: ");
		nome = sc.nextLine();

		do {
			respostaOK = false;
			try {
				System.out.println("Este cargo eh gerencial (s/n)? ");
				ehGerencial = verificaSN(sc.nextLine());
				respostaOK = true;
			}
			catch (Exception e) {
				System.err.println(e.getMessage());
				respostaOK = false;
			}

		} while (!respostaOK);

		do {
			respostaOK = false;
			try {
				System.out.println("Este cargo possui acesso ao financeiro (s/n)? ");
				possuiAcesso = verificaSN(sc.nextLine());
				respostaOK = true;
			}
			catch (Exception e) {
				System.err.println(e.getMessage());
				respostaOK = false;
			}

		} while (!respostaOK);

		do {
			respostaOK = false;
			if (possuiAcesso && !ehGerencial) {
				System.out.println("Este cargo necessita do cadastro de horario de acesso");

				try {
					System.out.println("Deseja cadastrar horario agora (s/n)? Obs: podera ser alterado posteriormente");
					if (verificaSN(sc.nextLine())) {
						horariosPermitidos = ControladorHorario.getInstance().iniciaCadastro();
					}
					respostaOK = true;
				}
				catch (Exception e) {
					System.err.println(e.getMessage());
					respostaOK = false;
				}
			} else {
				break;
			}
			
		} while (!respostaOK);

		return new DadosCadastroCargo(codigo, nome, ehGerencial, possuiAcesso, horariosPermitidos);

	}

	/**
	 * Solicita ao usuario o codigo do cargo a ser excluido
	 * @return codigo do cargo a ser excluido
	 */
	public int excluirCargo() {

		do {
			respostaOK = false;
			try {
				System.out.println("Digite o codigo do cargo a ser excluido (numero inteiro positivo): ");
				codigo = sc.nextInt();
				if (codigo > 0) {
					respostaOK = true;
				} else {
					respostaOK = false;
				}
			} catch (InputMismatchException e) {
				System.err.println("\nDigite um numero inteiro positivo\n");
			}
			sc.nextLine(); // limpa o buffer
		} while (!respostaOK);
	
		return codigo;
	}
	
	
	/**
	 * Solicita ao usuario a Hora Inicial e a Hora Final do intervalo a ser cadastrado
	 */
	/*private void incluirHorario() {

		System.out.println("Digite a hora inicial do intervalo: ");
		String horaInicial = sc.nextLine();
		System.out.println("Digite a hora final do intervalo: ");
		String horaFinal = sc.nextLine();
		horariosPermitidos.add(new Horario(horaInicial, horaFinal));
	}*/

	/**
	 * Verifica se o usuario digitou "s", "n", "S" ou "N"; gera excecao caso tenha sido digitado algo nao permitido
	 * @param resposta Resposta digitada pelo usuario
	 * @return "true", caso digitado "s" ou "S"; "false", caso digitado "n" ou "N"
	 * @throws Exception no caso de algo nao permitido ter sido digitado
	 */
	private boolean verificaSN(String resposta) throws Exception {
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
}
