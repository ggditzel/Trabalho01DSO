package br.ufsc.ine5605;

import java.util.ArrayList;

public class Testador {

	public static void main(String[] args) {
	
//	String[] n1 = {"Cargo top", "Carguinho de Merda", "Carguinho de merda @", "#cargo"};
//		
//	String regraNomeCargo = "[a-zA-Z]+([ ][a-zA-Z]*)*"; //aceita apenas letras, maiusculas ou minusculas, e 1 espaco entre as partes;
//	
//	for (String c : n1){
//		System.out.println(c.matches(regraNomeCargo));
//	}
	
	

		ControladorCargo cc = ControladorCargo.getInstance();
		ControladorFuncionario cf = ControladorFuncionario.getInstance();
		ControladorTentativaAcesso cta = ControladorTentativaAcesso.getInstance();
		
		cc.mostraMenu();
				
		/* inclui funcionarios com matricula 1, 2 e 3 para testes */
		cf.incluirFuncionario(1, "jose", cc.findCargoByCodigo(1), "9999-0000", "12-12-12", "1000"); //chefao (gerente)
		cf.incluirFuncionario(2, "joao", cc.findCargoByCodigo(2), "9999-0000", "12-12-12", "1000"); // do financeiro
		cf.incluirFuncionario(3, "silva", cc.findCargoByCodigo(3), "9999-0000", "12-12-12", "1000");// peao
		
		while (true){
			cta.iniciaTentativa();
			cta.menuRelatorioTentativas();
		}

	}

}
