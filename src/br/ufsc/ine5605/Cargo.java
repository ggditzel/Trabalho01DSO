package br.ufsc.ine5605;

import java.util.ArrayList;

public class Cargo implements ICargo{
	private int codigo;
	private String nome;
	private boolean ehGerencial;
	private boolean possuiAcesso;
	private ArrayList<Horario> horariosPermitidos;
	
	/**
	 * 
	 * @param codigo Codigo numerico para o cargo
	 * @param nome Nome para o cargo
	 * @param ehGerencial Indica se o cargo eh gerencial
	 * @param possuiAcesso Indica se o cargo possui Acesso ao financeiro
	 * @param horariosPermitidos lista com os intervalos permitidos
	 */
	public Cargo(int codigo, String nome, boolean ehGerencial, boolean possuiAcesso, ArrayList<Horario> horariosPermitidos) {
		this.codigo = codigo;
		this.nome = nome;
		this.ehGerencial = ehGerencial;
		this.possuiAcesso = possuiAcesso;
		this.horariosPermitidos = horariosPermitidos;
	}
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public String getNome() {
		return this.nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public boolean ehGerencial() {
		return this.ehGerencial;
	}
	public void setEhGerencial(boolean ehGerencial) {
		this.ehGerencial = ehGerencial;
	}
	public boolean getPossuiAcesso() {
		return this.possuiAcesso;
	}
	public void setPossuiAcesso(boolean possuiAcesso) {
		this.possuiAcesso = possuiAcesso;
	}
	public ArrayList<Horario> getHorariosPermitidos() {
		return horariosPermitidos;
	}
	//boolean
	
	/**
	 * Adiciona um horario apenas se o cargo nao for gerencial e se possuir acesso.
	 * Retorna "true" caso os criterios tenham sido atendidos e o horario tenha
	 * sido cadastrado com sucesso
	 */
	public boolean AdicionarHorarioPermitido(Horario horario) {
		if (this.ehGerencial || !this.possuiAcesso){
			return false;
		} else {
			this.horariosPermitidos.add(horario);
			return true;
		}

	}
	
	/**
	 * Se existir o horario a ser excluido, retorna "true" apos a exclusao, senao "false"
	 */
	public boolean RemoverHorarioPermitido(Horario horario) {
		for (Horario h : horariosPermitidos){
			if (h.getInicio().equals(horario.getInicio()) && h.getFim().equals(horario.getFim())){
				horariosPermitidos.remove(horario);
				return true;				
			}
		}
		return false;

	}
	
}
