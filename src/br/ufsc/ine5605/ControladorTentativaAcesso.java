package br.ufsc.ine5605;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
//TODO
//TUDO
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
			formata = new SimpleDateFormat("hh:mm");
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
		} else if(!hora.estaPresente(funcionario.getCargo().getHorariosPermitidos())) {
			tentativas.add(new TentativaAcesso(data, hora, matricula, MotivoNegacaoAcesso.HORARIO_NAO_PERMITIDO) );
			funcionario.setNumeroAcessosNegados(funcionario.getNumeroAcessosNegados() + 1);
			tela.mostraNegacao(MotivoNegacaoAcesso.HORARIO_NAO_PERMITIDO);
		} else if(funcionario.getNumeroAcessosNegados() >= 3) {
			tentativas.add(new TentativaAcesso(data, hora, matricula, MotivoNegacaoAcesso.ACESSO_BLOQUEADO) );
			funcionario.setNumeroAcessosNegados(funcionario.getNumeroAcessosNegados() + 1);
			tela.mostraNegacao(MotivoNegacaoAcesso.ACESSO_BLOQUEADO);
		} else {
			tela.confirmaAcesso();
		}

	}
	
	




}
