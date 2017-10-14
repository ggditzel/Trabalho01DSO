package br.ufsc.ine5605;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
//TODO
//javadoc
public class ControladorTentativaAcesso {
	private TelaTentativaAcesso tela;
	private ArrayList<TentativaAcesso> tentativas;
    private static ControladorTentativaAcesso instancia;
    
    private ControladorTentativaAcesso() {
    	tentativas = new ArrayList<>();
        tela = new TelaTentativaAcesso();
    }
    public static ControladorTentativaAcesso getInstance(){
    	if(instancia == null){
    		instancia = new ControladorTentativaAcesso();
    	}
    	return instancia;
    }
	
	public void iniciaTentativa() { 
		int opcao = tela.mostraOpcoes();
		String data;
		Hora hora;
		if(opcao == 0){
			return;
		} else if(opcao == 1) {
			java.util.Date agora = new java.util.Date();
			SimpleDateFormat formata = new SimpleDateFormat("dd/MM/yyyy");
			data = formata.format(agora);
			formata = new SimpleDateFormat("HH:mm");
			hora = ControladorHorario.converte(formata.format(agora));
		} else {
			hora = ControladorHorario.converte(tela.perguntaHora());
			data = tela.perguntaData();
		}
		int matricula = tela.perguntaMatricula();
		Funcionario funcionario = ControladorFuncionario.getInstance().findFuncionarioByMatricula(matricula);
		if(funcionario == null) {
			tentativas.add(new TentativaAcesso(data, hora, matricula, MotivoNegacaoAcesso.MATRICULA_INEXISTENTE) );
			tela.mostraNegacao(MotivoNegacaoAcesso.MATRICULA_INEXISTENTE);
		} else if(!funcionario.getCargo().getPossuiAcesso()){
			tentativas.add(new TentativaAcesso(data, hora, matricula, MotivoNegacaoAcesso.NAO_POSSUI_ACESSO) );
			funcionario.setNumeroAcessosNegados(funcionario.getNumeroAcessosNegados() + 1);
			tela.mostraNegacao(MotivoNegacaoAcesso.NAO_POSSUI_ACESSO);
		} else if(funcionario.getNumeroAcessosNegados() >= 3) {
			tentativas.add(new TentativaAcesso(data, hora, matricula, MotivoNegacaoAcesso.ACESSO_BLOQUEADO) );
			funcionario.setNumeroAcessosNegados(funcionario.getNumeroAcessosNegados() + 1);
			tela.mostraNegacao(MotivoNegacaoAcesso.ACESSO_BLOQUEADO);
		} else if(!funcionario.getCargo().getEhGerencial() && !hora.estaPresente(funcionario.getCargo().getHorariosPermitidos()) ) {
			tentativas.add(new TentativaAcesso(data, hora, matricula, MotivoNegacaoAcesso.HORARIO_NAO_PERMITIDO) );
			funcionario.setNumeroAcessosNegados(funcionario.getNumeroAcessosNegados() + 1);
			tela.mostraNegacao(MotivoNegacaoAcesso.HORARIO_NAO_PERMITIDO);
		}  else {
			tela.confirmaAcesso();
		}

	}
	
	public void menuRelatorioTentativas(){
		int opcao = tela.mostraMenuTentativas();
		switch(opcao){
			case 0:
				break;
			case 1:
				tela.listaTentativas(tentativas);
				break;
			case 2:
				int matricula = tela.perguntaMatricula();
				tela.listaTentativas(findTentativasByMatricula(matricula));
				break;
			case 3:
				int valorMotivo = tela.perguntaMotivo();
				if(valorMotivo == 0) break;
				MotivoNegacaoAcesso motivo = valorMotivo == 1 ? MotivoNegacaoAcesso.ACESSO_BLOQUEADO : valorMotivo == 2 ? MotivoNegacaoAcesso.HORARIO_NAO_PERMITIDO : valorMotivo == 3 ? MotivoNegacaoAcesso.MATRICULA_INEXISTENTE : MotivoNegacaoAcesso.NAO_POSSUI_ACESSO; // posso usar switch aqui
				tela.listaTentativas(findTentativasByMotivo(motivo));
				break;
		}
		
	}
	
	private ArrayList<TentativaAcesso> findTentativasByMatricula(int matricula) {
		ArrayList<TentativaAcesso> tentativasDaMatricula = new ArrayList<>();
		for(TentativaAcesso t: tentativas) {
			if(t.getMatricula() == matricula) {
				tentativasDaMatricula.add(t);
			}
		}
		return tentativasDaMatricula;
	}
	
	private ArrayList<TentativaAcesso> findTentativasByMotivo(MotivoNegacaoAcesso motivo) {
		ArrayList<TentativaAcesso> tentativasDoMotivo = new ArrayList<>();
		for(TentativaAcesso t: tentativas) {
			if(t.getMotivo().equals(motivo)){
				tentativasDoMotivo.add(t);
			}
		}
		return tentativasDoMotivo;
	}
	




}
