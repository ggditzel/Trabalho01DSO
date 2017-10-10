package br.ufsc.ine5605;

import java.util.ArrayList;
import java.util.Date;

public class ControladorFuncionario {
	private ArrayList<Funcionario> listaFuncionarios;
	private static ControladorFuncionario instancia;
	
	private ControladorFuncionario(){
		listaFuncionarios = new ArrayList<>();
	}
	
	 public static ControladorFuncionario getInstance(){
	    	if(instancia == null){
	    		instancia = new ControladorFuncionario();
	    	}
	    	return instancia;
	 }
	
	public void incluirFuncionario(int matricula, String nome, Cargo cargo, String telefone, String dataNascimento, String salario){
		if (findFuncionarioByMatricula(matricula) == null){
			listaFuncionarios.add(new Funcionario(matricula, nome, cargo, telefone, dataNascimento, salario));
		}
		// else chamar tela e dizer que este funcionario ja esta cadastrado
	}
	
	public void excluirFuncionario(int matricula){
		Funcionario funcionario = findFuncionarioByMatricula(matricula);
		if (funcionario != null){
			listaFuncionarios.remove(funcionario);
		}
		// else chama tela pra dizer que nao existe este funcionario cadastrado
	}
	
	public ArrayList<Funcionario> listarFuncionarios() {
		return listaFuncionarios; // retorna para imprimir na tela
	}
	
	public ArrayList<Funcionario> listarFuncionariosPorCargo(int codigo) {
		ArrayList<Funcionario> listaPorCargo = new ArrayList<>();
		for (Funcionario f: listaFuncionarios){
			if (f.getCargo().getCodigo() == codigo){
				listaPorCargo.add(f);
			}
		}
		return listaPorCargo; // retorna para imprimir na tela
	}
	
	public Funcionario findFuncionarioByMatricula(int matricula){
		Funcionario funcionario = null;
		for (Funcionario f : listaFuncionarios){
			if (f.getMatricula() == matricula){
				funcionario = f;
			}
		}
		return funcionario;
	}

}
