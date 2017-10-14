package br.ufsc.ine5605;

import java.util.ArrayList;
import java.util.Date;

public class ControladorFuncionario {
	private ArrayList<Funcionario> listaFuncionarios;
	private static ControladorFuncionario instancia;
	private TelaFuncionario tela;

	/****** OPCOES DO MENU PRINCIPAL ******/
	private final String[] opcoesMenuPrincipal = { "Voltar", "Listar Funcionarios Cadastrados",
			"Cadastrar Funcionarios", "Excluir Funcionario", "Alterar Cadastro Funcionario" };

	/****** OPCOES DO MENU PARA EDICAO DOS FUNCIONARIOS ******/
	private final String[] opcoesMenuEditarFuncionario = { "Voltar", "Alterar Matricula", "Alterar Nome",
			"Alterar Cargo", "Alterar Telefone", "Alterar dataNascimento", "Alterar Salario" };

	private ControladorFuncionario() {
		listaFuncionarios = new ArrayList<>();
		tela = new TelaFuncionario();
	}
	  public static ControladorFuncionario getInstance(){
	    	if(instancia == null){
	    		instancia = new ControladorFuncionario();
	    	}
	    	return instancia;
	    }

	public void incluirFuncionario() {
		DadosCadastroFuncionario funcionario = tela.IncluirFuncionario();
		if (findFuncionarioByMatricula(funcionario.matricula) == null
				&& findFuncionarioByNome(funcionario.nome) == null) {
			tela.mostraMensagem("Funcionario cadastrado com sucesso.");
		} else
			tela.mostraMensagem(
					"Já existe com esta matrícula e ou com este nome no sistema, cheque a lista e tente novamente");
	}
	

	/**
	 * Chama a tela que mostra o menu principal (relacionado a Funcionarios)
	 */
	public void mostraMenu() {
		int opcao = -1;
		do {
			opcao = tela.mostraMenu(opcoesMenuPrincipal);
			switch (opcao) {
			case 0:
				break;
			case 1:
				printarFuncionariosCompleto();
				;
				break;
			case 2:
				incluirFuncionario();
				break;
			case 3:
				excluirFuncionario();
				break;
			case 4:
				alterarCadastroFuncionario();
				break;
			}
		} while (opcao != 0);
	}

	public void mostraMenuEd(){
    	int opcao = -1;
    	
    	do {
    		opcao = tela.mostraMenu(opcoesMenuEditarFuncionario);
    		
    		switch (opcao){
    		case 0: 
    			mostraMenu();
    			break;
    			
    		case 1:
    			tela.mostraMensagem("Digite a nova Matricula do Funcionario");
    			int matTemp = tela.leInteiroPositivo();
    				if (findFuncionarioByMatricula(matTemp) == null) {
    				tela.mostraMensagem("Esta matrícula não está cadastrada, tente novamente");
    				matTemp = tela.leInteiroPositivo();
    			}
    				else 
    			alteraMatricula(matTemp);
    				tela.mostraMensagem("Matricula alterada com sucesso");
    				break;
    		
    		case 2:
    			tela.mostraMensagem("Digite o novo Nome do Funcionario");
    			String nomeTemp = tela.leitor.nextLine();
    				if (tela.validaNome(nomeTemp, RegrasValidacao.VALIDA_NOME_FUNCIONARIO.getRegraValidacao())){
    					alteraNome(nomeTemp);
    				
    			
     			
    		}
    	
    			
    		private void alteraNome(String nomeTemp) {
    			
		
	}

			private void alteraMatricula(int matricula) {
    			int matricula2 = tela.leInteiroPositivo();
    		
    			findFuncionarioByMatricula(matricula).setMatricula(matricula2);
    		}
    			
    		
	private Funcionario alterarCadastroFuncionario() {
		tela.mostraMensagem(
				"Procure na lista e em seguida digite a Matricula do funcionario que cujos dados serão editados");
		printaListaCargoNomeCodigoHorario();
		int matricula = tela.leitor.nextInt();
		if (findFuncionarioByMatricula(matricula) != null) {
			mostraMenuEd();
			return findFuncionarioByMatricula(matricula);
			
		}
		
		else
			tela.mostraMensagem("Matricula inexistente, verifique a lista e tente novamente");
			return null;

	}

	public void excluirFuncionario() {
		tela.mostraMensagem("Procure na lista e em seguida digite a Matricula do funcionario que será excluído");
		printaListaCargoNomeCodigoHorario();
		int matricula = tela.leitor.nextInt();
		if (findFuncionarioByMatricula(matricula) != null) {
			Funcionario funcionario = findFuncionarioByMatricula(matricula);
			listaFuncionarios.remove(funcionario);
			tela.mostraMensagem("Funcionaro de Matricula " + matricula + "foi removido do sistema");
		} else
			tela.mostraMensagem("Não existe Funcionário cadastrado com este número de matrícula");
	}

	public ArrayList<Funcionario> listarFuncionarios() {
		return listaFuncionarios; // retorna para imprimir na tela
	}

	public ArrayList<Funcionario> listarFuncionariosPorCargo(int codigo) {
		ArrayList<Funcionario> listaPorCargo = new ArrayList<>();
		for (Funcionario f : listaFuncionarios) {
			if (f.getCargo().getCodigo() == codigo) {
				listaPorCargo.add(f);
			}
		}
		return listaPorCargo; // retorna para imprimir na tela
	}

	public Funcionario findFuncionarioByMatricula(int matricula) {
		Funcionario funcionario = null;
		for (Funcionario f : listaFuncionarios) {
			if (f.getMatricula() == matricula) {
				funcionario = f;
			}
		}
		return funcionario;
	}

	public Funcionario findFuncionarioByNome(String nome) {
		Funcionario funcionario = null;
		for (Funcionario f : listaFuncionarios) {
			if (f.getNome().equals(nome)) {
				funcionario = f;
			}
		}
		return funcionario;
	}

	/*
	 * Printa lista no formato: Codigo Cargo (a) + Nome Cargo (a) Horarios de acesso
	 * do (a)
	 */

	public void printaListaCargoNomeCodigoHorario() {
		for (int a = 0; a < ControladorCargo.getInstance().getListaCargos().size(); a++) {
			tela.mostraMensagem("Código :" + (ControladorCargo.getInstance().getListaCargos().get(a).getCodigo()));
			tela.mostraMensagem("Cargo" + (ControladorCargo.getInstance().getListaCargos().get(a).getNome()));
			tela.mostraMensagem("O cargo " + a + "pode acessar a sala durante o intervalo :"
					+ (ControladorCargo.getInstance().getListaCargos().get(a).getHorariosPermitidos().toString()));

		}

	}

	public void adicionaCargo() {
		// recebe o 0 da tela dps de imprimir a lista
		ControladorCargo.getInstance().incluirCargo();
	}
	public String formataDataPraPrintar(int a) {
		String dataPraPrintar;
	
		dataPraPrintar = listarFuncionarios().get(a).getDataNascimento().substring(0, 1) +"/" ;
		dataPraPrintar = dataPraPrintar + listarFuncionarios().get(a).getDataNascimento().substring(2, 3) + "/" ;
		dataPraPrintar = dataPraPrintar + listarFuncionarios().get(a).getDataNascimento().substring(4, 7) ;
		return dataPraPrintar ;
		}
		
	

	public void printarFuncionariosCompleto() {
		for(int a = 0; a < ControladorCargo.getInstance().getListaCargos().size(); a++) {
			tela.mostraMensagem(listarFuncionarios().get(a).getNome() + 
						"Matricula :" + listarFuncionarios().get(a).getMatricula() + 
						"Cargo : " + listarFuncionarios().get(a).getCargo().getNome() +
						"Telefone : " + listarFuncionarios().get(a).getTelefone() +
						"Data Nascimento : " + formataDataPraPrintar(a) +
						"Salario : " + listarFuncionarios().get(a).getSalario());
		}
	}
			
					
			
		
	
	
}
