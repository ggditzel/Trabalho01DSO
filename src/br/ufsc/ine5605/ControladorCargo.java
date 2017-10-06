package br.ufsc.ine5605;

import java.util.ArrayList;
import java.util.Date;

public class ControladorCargo {
	private ArrayList<Cargo> listaCargos = new ArrayList();
	
	/**
	 * 
	 * @param codigo Codigo numerico dado para o cargo
	 * @param nome Nome do cargo
	 * @param ehGerencial indica se o cargo eh gerencial ou nao
	 * @param possuiAcesso indica se o cargo possui acesso ou nao
	 * @return String indicando sucesso ou fracasso da inclusao
	 */
	public String incluirCargo(int codigo, String nome, boolean ehGerencial, boolean possuiAcesso, ArrayList<Horario> horarios){
		if (findCargoByCodigo(codigo) == null){
			listaCargos.add(new Cargo (codigo, nome, ehGerencial, possuiAcesso, horarios));
			return "Cargo cadastrado com sucesso";
		}
		return "Este cargo ja esta cadastrado";
	}
	
	/**
	 * 
	 * @param codigo Codigo numerico do cargo a ser excluido
	 * @return String indicando sucesso ou fracasso na remocao
	 */
	public String excluirCargo(int codigo){
		Cargo c = findCargoByCodigo(codigo);
		if (c != null){
			listaCargos.remove(c);
			return "Cargo removido com sucesso";
		}
		return "Cargo inexistente, impossivel excluir";
	}
	
	/** 
	 * 
	 * @return listaCargos, ArrayList com os cargos cadastrados
	 */
	public ArrayList<Cargo> listaCargos() {
		return listaCargos; // retorna para imprimir na tela
	}
	
	
	public Cargo findCargoByCodigo(int codigo){
		Cargo cargo = null;
		for (Cargo c: listaCargos){
			if (c.getCodigo() == codigo){
				cargo = c;
			}
		}
		return cargo;
	}

}
