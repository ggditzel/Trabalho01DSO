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
		boolean cargoJaExiste = false;
		boolean ehGerencial = false;
		boolean possuiAcesso = true;
		ArrayList<Horario> horariosPermitidos = new ArrayList<Horario>();
		boolean respostaOK = false;
		System.out.println("==== Digite os dados solicitados ====");
		do {
			System.out.println("Digite um codigo para o cargo (numero inteiro positivo): ");
			codigo = leInteiroPositivo();
			if(ControladorCargo.getInstance().findCargoByCodigo(codigo) != null){
				System.out.println("Já existe cargo com este codigo, favor digitar um codigo diferente");
				cargoJaExiste = true;
			} else {
				cargoJaExiste = false;
			}
		} while (cargoJaExiste);
		
		System.out.println("Digite um nome para o cargo: ");
		nome = leitor.nextLine();

		do {
			respostaOK = false;
			try {
				System.out.println("Este cargo eh gerencial (s/n)? ");
				ehGerencial = super.verificaSN(leitor.nextLine());
				respostaOK = true;
			} catch (Exception e) {
				System.out.println(e.getMessage());
				respostaOK = false;
			}

		} while (!respostaOK);
		
		if(!ehGerencial){
			do {
				respostaOK = false;
				try {
					System.out.println("Este cargo possui acesso ao financeiro (s/n)? ");
					possuiAcesso = super.verificaSN(leitor.nextLine());
					respostaOK = true;
				} catch (Exception e) {
					System.out.println(e.getMessage());
					respostaOK = false;
				}

			} while (!respostaOK);

			do {
				respostaOK = false;
				if (possuiAcesso) {
					System.out.println("Este cargo necessita do cadastro de horario de acesso");

					try {
						System.out.println("Deseja cadastrar horario agora (s/n)? Obs: podera ser alterado posteriormente");
						if (verificaSN(leitor.nextLine())) {
							horariosPermitidos = ControladorHorario.getInstance().iniciaCadastro();
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
		}
		return new DadosCadastroCargo(codigo, nome, ehGerencial, possuiAcesso, horariosPermitidos);

	}

	/**
	 * Solicita ao usuario o codigo do cargo a ser excluido
	 * 
	 * @return codigo do cargo a ser excluido
	 */
	public int excluirCargo() {
		System.out.println("Digite o codigo do cargo a ser excluido (numero inteiro positivo)");
		return super.leInteiroPositivo();
	}

	/**
	 * Recebe uma lista de cargos para mostrar na tela, mostrando codigo, nome, status gerencial e status de acesso
	 * 
	 * @param lista Lista de cargos a ser mostrada
	 */
	public void listarCargos(ArrayList<Cargo> lista) {
		System.out.println("\n=== Cargos Cadastrados ===");
		for (Cargo c : lista) {
			System.out.println("\nCodigo: " + c.getCodigo() + "; "
					+ "Nome: " + c.getNome() + "; "
					+ "Cargo Gerencial? " + converteBooleanSimNao(c.ehGerencial()) + "; "
					+ "Possui Acesso? " + converteBooleanSimNao(c.getPossuiAcesso()) + ".");
			if (c.ehGerencial()){
				System.out.println("Gerentes podem acessar a qualquer hora.");
			} else if (!c.getPossuiAcesso()){
				System.out.println("Este cargo nao possui permissao de acesso.");
			} else {
				System.out.println("Horarios permitidos para acesso: ");
				ControladorHorario.getInstance().listaHorarios(c);
			}
		}
		System.out.println("");
	}

	private String converteBooleanSimNao(boolean b) {
		if (b) {
			return "Sim";
		} else {
			return "Nao";
		}
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

	/**
         * Altera o status gerencial ou o status de acesso, dependendo do parametro passado
         * @param status String "gerencial" ou "acesso", de acordo com o status a ser editado
         * @return 
         */
        public DadosAlteraStatus alterarStatus (String status){
		int codigo = -1;
		boolean novoStatus = false;
		boolean respostaOK = false;

		System.out.println("Digite o codigo do cargo a ser alterado (numero inteiro positivo): ");
		codigo = leInteiroPositivo();
		
		do {
			respostaOK = false;
			try {
				
                                if (status.equals("gerencial")){
                                System.out.println("Este cargo sera gerencial (s/n)?");
                        }
                                if (status.equals("acesso")){
                                    System.out.println("Este cargo tera acesso (s/n)?");
                                }
				novoStatus = super.verificaSN(leitor.nextLine());
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

		String[] opcoesMenu = {"Voltar", "Listar Cargos Cadastrados", "Incluir Cargo", "Excluir Cargo", "Alterar Cargo"};
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
        
        public int mostraMenuEditar (){
            String[] opcoesMenu = {"Voltar", "Alterar Descricao", "Alterar status gerencial", "Alterar status de acesso", "Alterar Horarios de Acesso"};
		boolean respostaOK = false;
		int opcao = 0;

		do {
			respostaOK = false;
			try{
				System.out.println("=== Edicao de Cargos ===");
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

}
