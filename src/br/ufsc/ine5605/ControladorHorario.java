package br.ufsc.ine5605;

import java.util.ArrayList;


public class ControladorHorario {
    private ArrayList<Horario> horariosAcesso;
    private TelaHorario tela;
    private static ControladorHorario instancia;
     

    private ControladorHorario() {
        horariosAcesso = new ArrayList<>();
        tela = new TelaHorario();
    }
    public static ControladorHorario getInstance(){
    	if(instancia == null){
    		instancia = new ControladorHorario();
    	}
    	return instancia;
    }
     
   //TODO
    //acertar a logica do laco
    public void inicia() {
    	ArrayList<Horario> horariosCargo = new ArrayList<>();
    	if(horariosAcesso.isEmpty()){
    		horariosCargo.add(cadastrarHorario());
    	} else {
    		int opcao = tela.mostraLista(horariosAcesso);
    		if(opcao == horariosAcesso.size()+1) {
    			horariosCargo.add(cadastrarHorario());
    		} else {
    			horariosCargo.add(horariosAcesso.get(opcao-1));
    		}
    	}
    	int opcao;
    	do{
    		opcao = tela.mostraOpcoes();
    		if(opcao != 2){
    			opcao = tela.mostraLista(horariosAcesso);
        		if(opcao == horariosAcesso.size()+1) {
        			horariosCargo.add(cadastrarHorario());
        		} else {
        			horariosCargo.add(horariosAcesso.get(opcao-1));
        		}
    		} else {
    			break;
    		}
    	}while(true);

    }
    //TODO
    //string no controlador
    private Horario cadastrarHorario() throws Exception {
    	String inicio = tela.perguntaInicio();
        Hora horarioInicio = new Hora(Integer.parseInt(inicio.substring(0,2)), Integer.parseInt(inicio.substring(3)));
        String fim = tela.perguntaFim();
        Hora horarioFim = new Hora(Integer.parseInt(fim.substring(0,2)), Integer.parseInt(fim.substring(3)));
        if(possuiHorario(horarioInicio, horarioFim)){
        	throw new Exception("Horário já existente.");
        }else{
        	Horario novo = new Horario(horarioInicio, horarioFim);
        	horariosAcesso.add(novo);
        	return novo;
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
