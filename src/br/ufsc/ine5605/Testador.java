package br.ufsc.ine5605;

import java.util.ArrayList;

public class Testador {

	public static void main(String[] args) {
		
//		ControladorCargo cc = new ControladorCargo();
//		ControladorFuncionario cf = new ControladorFuncionario();
//		
//		cc.incluirCargo(1, "Cargo top", true, true);
//		cc.incluirCargo(2, "Cargo bicheira", true, true);
//		
//		cf.incluirFuncionario(13, "Jose", cc.findCargoByCodigo(1), "32333212", "1111", "5000");
//		
//		ArrayList<Cargo> listaCargos = cc.listarCargos();
//		for (Cargo c : listaCargos){
//			System.out.println(c.getCodigo() + " " + c.getNome());
//		}
//
//		ArrayList<Funcionario> listaFuncionarios = cf.listarFuncionarios();
//		for (Funcionario f : listaFuncionarios){
//			System.out.println(f.getMatricula() + " " + f.getNome());
//		}
		TelaCargo tc = new TelaCargo();
		ControladorCargo cc = new ControladorCargo();
		
		for (int i = 0; i < 2; i++){
			DadosCadastroCargo cargo = tc.incluirCargo();
			System.out.println(cargo.codigo);
			System.out.println(cc.incluirCargo(cargo.codigo, cargo.nome, cargo.ehGerencial, cargo.possuiAcesso, cargo.horariosPermitidos));
		}
		
		ArrayList<Cargo> lista = cc.listaCargos();
		int i = 0;
		for (Cargo c : lista){
			System.out.println("\nCodigo cadastrado: " + c.getCodigo());
			System.out.println("nome cadastrado: "+ c.getNome());
			System.out.println("Gerente? "+ c.ehGerencial());
			System.out.println("Acesso? "+ c.getPossuiAcesso());
			
		}
		
		cc.excluirCargo(tc.excluirCargo());
		
		for (Cargo c : lista){
			System.out.println("Cargo cadastrado: " + c.getNome());
		}
	}

}
