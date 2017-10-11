package br.ufsc.ine5605;


public class TentativaAcesso {
	private String data;
	private Hora hora;
	private int matricula;
	private MotivoNegacaoAcesso motivo;
	
	public TentativaAcesso(String data, Hora hora, int matricula,
			MotivoNegacaoAcesso motivo) {
		this.data = data;
		this.hora = hora;
		this.matricula = matricula;
		this.motivo = motivo;
	}
	
	public String getData() {
		return data;
	}
	public void setData(String data) {
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
	
	@Override
	public String toString(){
		return "" + matricula + " tentou acessar a sala no dia " + data + " as " + hora + " e nao conseguiu. Motivo: " + motivo.getNome();
	}
	
}
