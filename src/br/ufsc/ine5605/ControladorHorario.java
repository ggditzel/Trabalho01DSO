package br.ufsc.ine5605;

import java.util.ArrayList;


public class ControladorHorario {
    private ArrayList<Horario> horariosAcesso;
    private TelaHorario tela;
     

    public ControladorHorario() {
        horariosAcesso = new ArrayList<>();
        tela = new TelaHorario();
    }
     
   //TODO
    //acertar a logica do laco
    public void inicia(ICargo cargo) {
    	if(horariosAcesso.isEmpty()){
    		cadastrarHorario();
    	}else{
    		int opcao = tela.mostraLista(horariosAcesso);
    		if(opcao == horariosAcesso.size()+1) {
    			cadastrarHorario();
    		}else{
    			cargo.AdicionarHorarioPermitido(horariosAcesso.get(opcao-1));
    		}
    	}

    }

    public void cadastrarHorario() {
    	String inicio = tela.perguntaInicio();
        Hora horarioInicio = new Hora(Integer.parseInt(inicio.substring(0,2)), Integer.parseInt(inicio.substring(3)));
        String fim = tela.perguntaFim();
        Hora horarioFim = new Hora(Integer.parseInt(fim.substring(0,2)), Integer.parseInt(fim.substring(3)));
        if(possuiHorario(horarioInicio, horarioFim)){
        	tela.mostrarAviso("Horário já existente");
        }else{
        	Horario novo = new Horario(horarioInicio, horarioFim);
        	horariosAcesso.add(novo);
        }
        
    }

    private boolean possuiHorario(Hora inicio, Hora fim) {
    	for(Horario i: horariosAcesso) {
    		if(i.getInicio().toString().equals(inicio.toString()) && i.getFim().toString().equals(fim.toString())){
    			return true;
    		}
    	}
    	return false;
    }
    
    
    
}
