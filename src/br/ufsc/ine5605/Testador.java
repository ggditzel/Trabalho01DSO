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
//		ControladorTentativaAcesso cta = ControladorTentativaAcesso.getInstance();
		
		do {
			cc.mostraMenu();
			cf.mostraMenu();
		} while (true);
		
	}

}
