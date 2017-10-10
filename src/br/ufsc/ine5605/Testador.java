package br.ufsc.ine5605;

import java.util.ArrayList;

public class Testador {

	public static void main(String[] args) {

		ControladorCargo cc = ControladorCargo.getInstance();
		ControladorFuncionario cf = ControladorFuncionario.getInstance();
		cc.mostraMenu();
//		
//		// criar os cargos 1 e 2, so para testes, para cadastrar os seguintes funcionarios
//		cf.incluirFuncionario(1, "jose", cc.findCargoByCodigo(1), "9999-0000", "12-12-12", "1000");
//		cf.incluirFuncionario(2, "joao", cc.findCargoByCodigo(1), "9999-0001", "12-12-13", "1000");
//		cf.incluirFuncionario(3, "maria", cc.findCargoByCodigo(1), "9999-0002", "12-12-14", "2000");
//		cf.incluirFuncionario(4, "marcos", cc.findCargoByCodigo(2), "9999-0003", "12-12-15", "3000");
//		
//		// chama novamente o menu, para tentativa de exclusao de um cargo que ja possui funcionarios
//		cc.mostraMenu();

		System.out.println("FIM");
		
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
		//TelaCargo tc = new TelaCargo();
		
		
//		
		
//		int opcao = 0;
//		do {
//			opcao = cc.mostraMenu();;
//			switch (opcao) {
//			case 0: break;
//			
//			case 1: tc.listarCargos(cc.listarCargos());break;
//			
//			case 2: {
//				DadosCadastroCargo cargo = tc.incluirCargo();
//				cc.incluirCargo(cargo.codigo, cargo.nome, cargo.ehGerencial, cargo.possuiAcesso, cargo.horariosPermitidos); break;
//			}
//			
//			case 3: {
//				try {
//					cc.excluirCargo(tc.excluirCargo());
//				}
//				catch (Exception e){
//					System.out.println(e.getMessage());
//				}
//				break;
//			}
//			
//			case 4: System.out.println("opcao 4");break;
//			
//			}
//		} while (opcao != 0);
		
		

//		for (int i = 0; i < 2; i++){
//			DadosCadastroCargo cargo = tc.incluirCargo();
//			System.out.println(cargo.codigo);
//			System.out.println(cc.incluirCargo(cargo.codigo, cargo.nome, cargo.ehGerencial, cargo.possuiAcesso, cargo.horariosPermitidos));
//		}
		
//		ArrayList<Cargo> lista = cc.listarCargos();
//		for (Cargo c : lista){
//			System.out.println("\nCodigo cadastrado: " + c.getCodigo());
//			System.out.println("nome cadastrado: "+ c.getNome());
//			System.out.println("Gerente? "+ c.ehGerencial());
//			System.out.println("Acesso? "+ c.getPossuiAcesso());
//			for (Horario h : c.getHorariosPermitidos()){
//				System.out.println("Horario: "+ h.getInicio() +" ate " + h.getFim());
//			}
//			
//		}
//		

//		ControladorHorario ch = ControladorHorario.getInstance();
//		
//		try {
//			if (cc.excluirCargo(tc.excluirCargo())){
//				System.out.println("Cargo Excluido com sucesso");
//			} else {
//				System.out.println("Possui funcionarios associados, impossivel excluir");
//			}
//		}
//		catch (Exception e){
//			System.out.println(e.getMessage());
//		}
//		
//		tc.listarCargos(lista);
////		for (Cargo c : lista){
////			System.out.println("Cargo cadastrado: " + c.getNome());
////		}
	}

}
