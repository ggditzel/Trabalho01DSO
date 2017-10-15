package br.ufsc.ine5605;

public class TentativaAcessoNegado extends TentativaAcesso {

	private MotivoNegacaoAcesso motivo;
	
	public TentativaAcessoNegado(String data, Hora hora, int matricula,
			MotivoNegacaoAcesso motivo) {
		super(data, hora, matricula);
		this.motivo = motivo;

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
