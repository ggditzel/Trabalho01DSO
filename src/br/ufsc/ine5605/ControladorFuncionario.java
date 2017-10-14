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

	public static ControladorFuncionario getInstance() {
		if (instancia == null) {
			instancia = new ControladorFuncionario();
		}
		return instancia;
	}

	public void incluirFuncionario() {
		printaListaCargoNomeCodigoHorario();
		DadosCadastroFuncionario funcionario = tela.IncluirFuncionario();

		if (findFuncionarioByMatricula(funcionario.matricula) == null
				&& findFuncionarioByNome(funcionario.nome) == null) {
			listaFuncionarios.add(new Funcionario(funcionario.matricula, funcionario.nome, funcionario.cargo,
					funcionario.telefone, funcionario.dataNascimento, funcionario.salario));

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
		boolean eae = false;
		do {
			eae = false;
		
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
				else {
					alteraMatricula(matTemp);
				tela.mostraMensagem("Matricula alterada com sucesso");
				}
				break;

			case 2:
				tela.mostraMensagem("Digite o nome do Funcionario a ser alterado");
				String nomeTemp = tela.leitor.nextLine();
				alteraNome(nomeTemp);
				break;
			case 3:
				tela.mostraMensagem("Digite a matricula do funcionario cujo cargo será editado");
				int maat = tela.leInteiroPositivo();
				printaListaCargoNomeCodigoHorario();
				tela.mostraMensagem("Digite o código do cargo da lista");
				int novoCc = tela.leInteiroPositivoAte(ControladorCargo.getInstance().getListaCargos().size());
				eae = false;
				do {


					if(findFuncionarioByMatricula(maat) != null) {
						findFuncionarioByMatricula(maat).setCargo(ControladorCargo.getInstance().getListaCargos().get(novoCc));
						eae = true;
					}
					else {
						tela.mostraMensagem("Digite uma matrícula e um código de cargo válidos");
					} 
				} while (!eae);
				break;
			case 4:
				
				tela.mostraMensagem("Digite a matricula do funcionario cujo telefone será alterado");
				int mat = tela.leInteiroPositivo();
				eae = false;
					do {
						if (findFuncionarioByMatricula(mat) != null) {
							
							tela.mostraMensagem("Digite o novo telefone do funcionario(Apenas numeros)");
							String telefone = tela.leitor.nextLine();
							if(!telefone.matches("^[0-9]${1,}")) {
								System.out.println("Digite o telefone do funcionario(APENAS NUMEROS)");
								telefone = tela.leitor.nextLine();
							findFuncionarioByMatricula(mat).setTelefone(telefone);
							 eae = true;
						}
							else {
								tela.mostraMensagem("Digite uma matricula e um NÚMERO de telefone");
							}
						}
					} while(!eae);
					break;
					
					case 5:
					
						
						tela.mostraMensagem("Digite a matricula do funcionario cuja Data de Nascimento será alterada");
						mat = tela.leInteiroPositivo();
						eae = false;
							do {
								if (findFuncionarioByMatricula(mat) != null) {
									
									System.out.println("digite a data de nascimento do funcionario(No formato ddMMaaaa");
									String dataNascimento = tela.leitor.nextLine();
									if (!dataNascimento.matches("^[0-9]${8}")){
										System.out.println("digite a data de nascimento do funcionario(No formato ddMMaaaa");
										dataNascimento = tela.leitor.nextLine();
									findFuncionarioByMatricula(mat).setDataNascimento(dataNascimento);
									tela.mostraMensagem(formataDataPraPrintar(findFuncionarioByMatricula(mat).getDataNascimento()));
									 eae = true;
									 }
								}
									else {
										tela.mostraMensagem("Digite uma matricula e um NÚMERO de telefone");
									}
							} while(!eae);
					case 6:
						tela.mostraMensagem("Digite a matricula do funcionario cujo salário será alterado");
						mat = tela.leInteiroPositivo();
						eae = false;
							do {
								if (findFuncionarioByMatricula(mat) != null) {
									System.out.println("digite o salario do Funcionario");
									int salario = tela.leInteiroPositivo();
									findFuncionarioByMatricula(mat).setSalario(salario);
									tela.mostraMensagem("Salário alterado para: " +salario +"com sucesso.");
									eae = true;
								}
								else {
									tela.mostraMensagem("Digite uma matricula e um salário válidos");
								}
							
							}while (!eae);
							
				}
		}while (!eae);
	}
		
		
		

	private void alteraNome(String nome) {
			String nomeT = tela.pedeNome();
			boolean eae = false;
			do{
				if(findFuncionarioByNome(nomeT) != null) {
					findFuncionarioByNome(nomeT).setNome(tela.pedeNome());
					eae = true;
					}
			}while (!eae);
			

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
			tela.mostraMensagem("Cargo: " + (ControladorCargo.getInstance().getListaCargos().get(a).getNome()));
			if (ControladorCargo.getInstance().getListaCargos().get(a).getEhGerencial()) {
				tela.mostraMensagem("CARGO COM ACESSO IRRESTRITO AO FINANCEIRO");
			}
			else {
				tela.mostraMensagem("Este cargo pode acessar a sala durante o intervalo :"
					+ (ControladorCargo.getInstance().getListaCargos().get(a).getHorariosPermitidos().toString()));
			}
			

		}

	}

	public void adicionaCargo() {
		// recebe o 0 da tela dps de imprimir a lista
		ControladorCargo.getInstance().incluirCargo();
	}

	public String formataDataPraPrintar(String a) {
		String dataPraPrintar;

		dataPraPrintar = a.substring(0, 2) + "/";
		dataPraPrintar = dataPraPrintar + a.substring(2, 4) + "/";
		dataPraPrintar = dataPraPrintar + a.substring(4);
		return dataPraPrintar;
	}

	public void printarFuncionariosCompleto() {
		for (int a = 0; a <= listaFuncionarios.size(); a++) {
			tela.mostraMensagem(
					listarFuncionarios().get(a).getNome() + "Matricula :" + listarFuncionarios().get(a).getMatricula()
							+ "--Cargo : " + listarFuncionarios().get(a).getCargo().getNome() + "--Telefone : "
							+ listarFuncionarios().get(a).getTelefone() + "--Data Nascimento : "
							+ formataDataPraPrintar(listarFuncionarios().get(a).getDataNascimento()) + "--Salario : "
							+ listarFuncionarios().get(a).getSalario());
		}
	}

}