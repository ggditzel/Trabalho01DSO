package br.ufsc.ine5605;

import java.util.InputMismatchException;
import java.util.ArrayList;

public class TelaCargo extends Tela {

	/**
	 * Solicita via Scanner os dados necessarios para o cadastro de um novo
	 * usuario, retornando as informacoes em uma classe temporaria a ser
	 * utilizada pelo ControladorCargo
	 * 
	 * @return DadosCadastroCargo(int codigo, String nome, boolean ehGerencial,
	 *         boolean possuiAcesso, ArrayList horariosPermitidos)
	 */
	public DadosCadastroCargo incluirCargo() {
		int codigo = -1;
		String nome;
		boolean ehGerencial = false;
		boolean possuiAcesso = false;
		ArrayList<Horario> horariosPermitidos = new ArrayList<Horario>();
		boolean respostaOK = false;
		System.out.println("==== Digite os dados solicitados ====");
		System.out.println("Digite um codigo para o cargo (numero inteiro positivo): ");
		codigo = leInteiroPositivo();

		System.out.println("Digite um nome para o cargo: ");
		nome = leitor.nextLine();

		do {
			respostaOK = false;
			try {
				System.out.println("Este cargo eh gerencial (s/n)? ");
				ehGerencial = verificaSN(leitor.nextLine());
				respostaOK = true;
			} catch (Exception e) {
				System.out.println(e.getMessage());
				respostaOK = false;
			}

		} while (!respostaOK);

		do {
			respostaOK = false;
			try {
				System.out.println("Este cargo possui acesso ao financeiro (s/n)? ");
				possuiAcesso = verificaSN(leitor.nextLine());
				respostaOK = true;
			} catch (Exception e) {
				System.out.println(e.getMessage());
				respostaOK = false;
			}

		} while (!respostaOK);

		do {
			respostaOK = false;
			if (possuiAcesso && !ehGerencial) {
				System.out.println("Este cargo necessita do cadastro de horario de acesso");

				try {
					System.out.println("Deseja cadastrar horario agora (s/n)? Obs: podera ser alterado posteriormente");
					if (verificaSN(leitor.nextLine())) {
						ControladorHorario.getInstance().iniciaCadastro();
					}
					respostaOK = true;
				} catch (Exception e) {
					System.out.println(e.getMessage());
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
	 * 
	 * @return codigo do cargo a ser excluido
	 */
	public int excluirCargo() {
		System.out.println("Digite o codigo do cargo a ser excluido (numero inteiro positivo)");
		return leInteiroPositivo();
	}

	/**
	 * Recebe uma lista de cargos para mostrar na tela, mostrando codigo, nome, status gerencial e status de acesso
	 * 
	 * @param lista Lista de cargos a ser mostrada
	 */
	public void listarCargos(ArrayList<Cargo> lista) {
		System.out.println("\n=== Cargos Cadastrados ===");
		for (Cargo c : lista) {
			System.out.println("Codigo: " + c.getCodigo() + "; "
					+ "Nome: " + c.getNome() + "; "
					+ "Cargo Gerencial? " + c.ehGerencial() + "; "
					+ "Possui Acesso? " + c.getPossuiAcesso());
		}
		System.out.println("");
	}

	/**
	 * Solicita o codigo do Cargo a ter o nome alterado, e na sequencia solicita o novo nome.
	 * @return Retorna uma classe temporaria com o codigo e o novo nome, para ser utilizada pelo controlador
	 */
	public DadosAlteraDescricao alterarDescricao (){
		int codigo = -1;
		String novoNome = "";
		
		System.out.println("Digite o codigo do cargo a ser alterado (numero inteiro positivo): ");
		codigo = leInteiroPositivo();
		
		System.out.println("Digite a nova descricao para o cargo (nome): ");
		novoNome = leitor.nextLine();
		return new DadosAlteraDescricao(codigo, novoNome);
	}

	public DadosAlteraStatus alterarStatus (){
		int codigo = -1;
		boolean novoStatus = false;
		boolean respostaOK = false;

		System.out.println("Digite o codigo do cargo a ser alterado (numero inteiro positivo): ");
		codigo = leInteiroPositivo();
		
		do {
			respostaOK = false;
			try {
				System.out.println("Este cargo sera gerencial (s/n)?");
				novoStatus = verificaSN(leitor.nextLine());
				respostaOK = true;
			} catch (Exception e){
				System.out.println(e.getMessage());
				respostaOK = false;
			}
		} while (!respostaOK);
		return new DadosAlteraStatus(codigo, novoStatus);
	}

	/**
	 * Mostra o menu principal relacionado aos Cargos
	 * @return opcao Numero inteiro que representa a opcao escolhida
	 */
	public int mostraMenuPrincipal () {

		String[] opcoesMenu = {"Voltar", "Listar Cargos Cadastrados", "Incluir Cargo", "Excluir Cargo", "Alterar Descricao",
				"Alterar status gerencial", "Alterar status de acesso", "Alterar Horarios de Acesso"};
		boolean respostaOK = false;
		int opcao = 0;

		do {
			respostaOK = false;
			try{
				System.out.println("=== Opcoes para Cargos ===");
				for (int i = 0; i < opcoesMenu.length; i++){
					System.out.println("" + i  + " - " + opcoesMenu[i]);
				}

				opcao = leitor.nextInt();
				if (opcao < 0 ||  opcao > opcoesMenu.length -1){
					respostaOK = false;
				} else {
					respostaOK = true;
				}
			}
			catch (InputMismatchException e) {
				System.out.println("\nDigite apenas numeros\n");
				respostaOK = false;
			}
			leitor.nextLine(); // limpa o buffer

		} while (!respostaOK);
		return opcao;
	}

	public void mostraMensagem(String mensagem){
		System.out.println(mensagem);
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
	
	/**
	 * Solicita a digitacao de um numero inteiro positivo, ate que seja digitado corretamente
	 * @return
	 * 	Valor inteiro positivo lido do teclado
	 */
	private int leInteiroPositivo() {
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
}
