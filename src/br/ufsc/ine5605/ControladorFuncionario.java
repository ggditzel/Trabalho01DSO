package br.ufsc.ine5605;

import java.util.ArrayList;
import java.util.Date;

public class ControladorFuncionario {
	private ArrayList<Funcionario> listaFuncionarios;
	private static ControladorFuncionario instancia;
	private TelaFuncionario tela;

	/****** OPCOES DO MENU PRINCIPAL ******/
	private final String[] opcoesMenuPrincipal = { "Voltar", "Listar Funcionarios Cadastrados", "Cadastrar Funcionarios", "Excluir Funcionario",
	"Alterar Cargo" };
	
	/****** OPCOES DO MENU PARA EDICAO DOS FUNCIONARIOS ******/
	private final String[] opcoesMenuEditarFuncionario = { "Voltar", "Alterar Nome", "Alterar Matricula", "Alterar CPF", "Alterar Telefone", "Alterar dataNascimento","Alterar Salario"
	};
	private ControladorFuncionario() {
		listaFuncionarios = new ArrayList<>();
		tela = new TelaFuncionario();
	}
	
	public int menuzao() {
		int opcao = 0;
		do {
			opcao = tela.leitor.nextInt();
			switch (opcao) {
			case 0:
				break;
			case 1:
				tela.listarFuncionarios();
			}
		}
	}

	public static ControladorFuncionario getInstance() {
		if (instancia == null) {
			instancia = new ControladorFuncionario();
		}
		return instancia;
	}
	
	public void mostraMenu() {
		int opcao = -1;
		do {
			
		}
	}

	public void incluirFuncionario(int matricula, String nome, Cargo cargo, String telefone, String dataNascimento,
			String salario) {
		if (findFuncionarioByMatricula(matricula) == null) {
			listaFuncionarios.add(new Funcionario(matricula, nome, cargo, telefone, dataNascimento, salario));
		}
		// else chamar tela e dizer que este funcionario ja esta cadastrado
	}

	public void excluirFuncionario(int matricula) {
		Funcionario funcionario = findFuncionarioByMatricula(matricula);
		if (funcionario != null) {
			listaFuncionarios.remove(funcionario);
		}
		// else chama tela pra dizer que nao existe este funcionario cadastrado
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
	
	/*  Printa lista no formato:
	 * Codigo Cargo (a) +  Nome Cargo (a)
	 * Horarios de acesso do (a)
	 */
		
	public void printaListaCargoNomeCodigoHorario() {
		for (int a = 0; a < ControladorCargo.getInstance().getListaCargos().size(); a++) {
			System.out.print("Código :" + (ControladorCargo.getInstance().getListaCargos().get(a).getCodigo()));
			System.out.println("Cargo" + (ControladorCargo.getInstance().getListaCargos().get(a).getNome()));
			System.out.println("O cargo " + a + "pode acessar a sala durante o intervalo :"
					+ (ControladorCargo.getInstance().getListaCargos().get(a).getHorariosPermitidos().toString()));
			
		}
	
		

	}
	public void adicionaCargo() {
		//recebe o 0 da tela dps de imprimir a lista
		ControladorCargo.getInstance().incluirCargo();
	}
	public void listarFuncionarioss() {
			System.out.println("ControladorFuncionario.getInstance().listarFuncionarios().get(1).getNome()\r\n" + 
					"			ControladorFuncionario.getInstance().listarFuncionarios().get(1).getMatricula()\r\n" + 
					"			ControladorFuncionario.getInstance().listarFuncionarios().get(1).getDataNascimento()\r\n" + 
					"			ControladorFuncionario.getInstance().listarFuncionarios().get(1).getTelefone()\r\n" + 
					"			ControladorFuncionario.getInstance().listarFuncionarios().get(1).getSalario()\r\n" + 
					"			ControladorFuncionario.getInstance().listarFuncionarios().get(1).getNumeroAcessosNegados()");
			
		
	}
	
}