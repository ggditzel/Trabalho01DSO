package br.ufsc.ine5605;

import java.util.ArrayList;

public interface ICargo {
		
	public int getCodigo();
	public void setCodigo(int codigo);
	public String getNome();
	public void setNome(String nome);
	public boolean ehGerencial();
	public void setEhGerencial(boolean ehGerencial);
	public boolean getPossuiAcesso();
	public void setPossuiAcesso(boolean possuiAcesso);
	public ArrayList<Horario> getHorariosPermitidos();
	public boolean AdicionarHorarioPermitido(Horario horario);	
	public boolean RemoverHorarioPermitido(Horario horario);
}

