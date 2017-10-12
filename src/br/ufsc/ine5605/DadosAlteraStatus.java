package br.ufsc.ine5605;

public class DadosAlteraStatus {
	public int codigo;
	public boolean statusGerencial;
	public boolean StatusAcesso;
	
	public DadosAlteraStatus(int codigo, boolean statusGerencial, boolean statusAcesso) {
		this.codigo = codigo;
		this.statusGerencial = statusGerencial;
		this.StatusAcesso = statusAcesso;
	}
}
