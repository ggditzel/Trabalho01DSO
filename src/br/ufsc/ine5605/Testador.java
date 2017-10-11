package br.ufsc.ine5605;

import java.util.ArrayList;

public class Testador {

	public static void main(String[] args) {

		ControladorCargo cc = ControladorCargo.getInstance();
		ControladorFuncionario cf = ControladorFuncionario.getInstance();
		ControladorTentativaAcesso cta = ControladorTentativaAcesso.getInstance();
		
		cc.mostraMenu(); /* CADASTRAR CARGO COM CODIGO "1" PARA TESTES */
		
		/* inclui funcionario com matricula "1" para testes */
		cf.incluirFuncionario(1, "jose", cc.findCargoByCodigo(1), "9999-0000", "12-12-12", "1000");
		
		while (true){
			cta.iniciaTentativa();
			cta.menuRelatorioTentativas();
		}

	}

}
