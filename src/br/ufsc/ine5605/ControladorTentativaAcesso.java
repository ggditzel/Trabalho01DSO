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
			tentativas.add(new TentativaAcessoNegado(data, hora, matricula, MotivoNegacaoAcesso.MATRICULA_INEXISTENTE) );
			tela.mostraNegacao(MotivoNegacaoAcesso.MATRICULA_INEXISTENTE);
		} else if(!funcionario.getCargo().getPossuiAcesso()){
			tentativas.add(new TentativaAcessoNegado(data, hora, matricula, MotivoNegacaoAcesso.NAO_POSSUI_ACESSO) );
			funcionario.setNumeroAcessosNegados(funcionario.getNumeroAcessosNegados() + 1);
			tela.mostraNegacao(MotivoNegacaoAcesso.NAO_POSSUI_ACESSO);
		} else if(funcionario.getNumeroAcessosNegados() >= 3) {
			tentativas.add(new TentativaAcessoNegado(data, hora, matricula, MotivoNegacaoAcesso.ACESSO_BLOQUEADO) );
			funcionario.setNumeroAcessosNegados(funcionario.getNumeroAcessosNegados() + 1);
			tela.mostraNegacao(MotivoNegacaoAcesso.ACESSO_BLOQUEADO);
		} else if(!funcionario.getCargo().getEhGerencial() && !hora.estaPresente(funcionario.getCargo().getHorariosPermitidos()) ) {
			tentativas.add(new TentativaAcessoNegado(data, hora, matricula, MotivoNegacaoAcesso.HORARIO_NAO_PERMITIDO) );
			funcionario.setNumeroAcessosNegados(funcionario.getNumeroAcessosNegados() + 1);
			tela.mostraNegacao(MotivoNegacaoAcesso.HORARIO_NAO_PERMITIDO);
		}  else {
			tentativas.add(new TentativaAcessoPermitido(data, hora, matricula));
			tela.confirmaAcesso();
		}

	}
	
	public void menuRelatorioTentativas(){
		int opcao = tela.mostraMenuTentativas();
		switch(opcao){
		/*		"1- Listar todas as tentativas de acesso\n" +
				"2- Listar todas as tentativas de acesso negadas\n" +
				"3- Listar todos os acessos\n" +
				"4- Listar todos as tentativas de acesso a partir de uma matricula" +
				"5- Listar tentativas de acesso negadas a partir de uma matricula\n" +
				"6- Listar os acessos a partir de uma matricula\n" +
				"7- Listar tentativas de acesso negadas por um motivo\n" +
				"0- Voltar"*/
			case 0:
				break;
			case 1:
				tela.listaTentativas(tentativas);
				break;
			case 2:
				tela.listaTentativas(getTentativasNegadas());
				break;
			case 3:
				tela.listaTentativas(getAcessos());
				break;
			case 4:
				int matricula = tela.perguntaMatricula();
				tela.listaTentativas(findTentativasByMatricula(matricula));
				break;
			case 5:
				matricula = tela.perguntaMatricula();
				tela.listaTentativas(findTentativasNegadasByMatricula(matricula));
				break;
			case 6:
				matricula = tela.perguntaMatricula();
				tela.listaTentativas(findAcessosByMatricula(matricula));
				break;
			case 7:
				int valorMotivo = tela.perguntaMotivo();
				if(valorMotivo == 0) break;
				MotivoNegacaoAcesso motivo = valorMotivo == 1 ? MotivoNegacaoAcesso.ACESSO_BLOQUEADO : valorMotivo == 2 ? MotivoNegacaoAcesso.HORARIO_NAO_PERMITIDO : valorMotivo == 3 ? MotivoNegacaoAcesso.MATRICULA_INEXISTENTE : MotivoNegacaoAcesso.NAO_POSSUI_ACESSO; // posso usar switch aqui
				tela.listaTentativas(findTentativasNegadasByMotivo(motivo));
				break;
		}
		
	}
	
	
	public ArrayList<TentativaAcesso> getTentativasNegadas() {
		ArrayList<TentativaAcesso> tentativasNegadas = new ArrayList<>();
		for(TentativaAcesso t: tentativas){
			if(t instanceof TentativaAcessoNegado){
				tentativasNegadas.add(t);
			}
		}
		return tentativasNegadas;
	}
	
	public ArrayList<TentativaAcesso> getAcessos() {
		ArrayList<TentativaAcesso> acessos = new ArrayList<>();
		for(TentativaAcesso t: tentativas){
			if(t instanceof TentativaAcessoPermitido){
				acessos.add(t);
			}
		}
		return acessos;
	}
	
	public ArrayList<TentativaAcesso> findTentativasByMatricula(int matricula) {
		ArrayList<TentativaAcesso> tentativasDaMatricula = new ArrayList<>();
		for(TentativaAcesso t: tentativas) {
			if(t.getMatricula() == matricula) {
				tentativasDaMatricula.add(t);
			}
		}
		return tentativasDaMatricula;
	}
	
	public ArrayList<TentativaAcesso> findTentativasNegadasByMatricula(int matricula) {
		ArrayList<TentativaAcesso> tentativasDaMatricula = new ArrayList<>();
		for(TentativaAcesso t: findTentativasByMatricula(matricula)) {
			if(t instanceof TentativaAcessoNegado){
				tentativasDaMatricula.add(t);
			}
		}
		return tentativasDaMatricula;
	}
	
	public ArrayList<TentativaAcesso> findAcessosByMatricula(int matricula) {
		ArrayList<TentativaAcesso> acessosDaMatricula = new ArrayList<>();
		for(TentativaAcesso t: findTentativasByMatricula(matricula)) {
			if(t instanceof TentativaAcessoPermitido){
				acessosDaMatricula.add(t);
			}
		}
		return acessosDaMatricula;
	}
	
	public ArrayList<TentativaAcesso> findTentativasNegadasByMotivo(MotivoNegacaoAcesso motivo) {
		ArrayList<TentativaAcesso> tentativasDoMotivo = new ArrayList<>();
		for(TentativaAcesso t: tentativas) {
			if(t instanceof TentativaAcessoNegado) {	
				if(((TentativaAcessoNegado) (t)).getMotivo().equals(motivo)){
					tentativasDoMotivo.add(t);
				}
			}
		}
		return tentativasDoMotivo;
	}
	




}
