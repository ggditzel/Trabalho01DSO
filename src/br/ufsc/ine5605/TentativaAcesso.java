package br.ufsc.ine5605;

import java.sql.Date;

public class TentativaAcesso {
	private Date data;
	private Hora hora;
	private int matricula;
	private MotivoNegacaoAcesso motivo;
	
	public TentativaAcesso(Date data, Hora hora, int matricula,
			MotivoNegacaoAcesso motivo) {
		this.data = data;
		this.hora = hora;
		this.matricula = matricula;
		this.motivo = motivo;
	}
	
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	public Hora getHora() {
		return hora;
	}
	public void setHora(Hora hora) {
		this.hora = hora;
	}
	public int getMatricula() {
		return matricula;
	}
	public void setMatricula(int matricula) {
		this.matricula = matricula;
	}
	public MotivoNegacaoAcesso getMotivo() {
		return motivo;
	}
	public void setMotivo(MotivoNegacaoAcesso motivo) {
		this.motivo = motivo;
	}
	
}
