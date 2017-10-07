package br.ufsc.ine5605;

import java.util.ArrayList;



public class TelaHorario extends Tela{

    
    public TelaHorario(){
        
    }


    public String perguntaInicio() throws Exception {
        System.out.println("Insira o início do horário (hh:mm): ");
        String resposta = leitor.nextLine();
        if(!resposta.matches("([01]?[0-9]|2[0-3]):[0-5][0-9]") ) {
        	throw new Exception("Formato de hora inválido.");
        }
        return resposta;
    }
    public String perguntaFim() throws Exception {
        System.out.println("Insira o fim do horário (hh:mm): ");
        String resposta = leitor.nextLine();
        if(!resposta.matches("([01]?[0-9]|2[0-3]):[0-5][0-9]") ) {
        	throw new Exception("Formato de hora inválido.");
        }
        return resposta;
    }

	public int mostraOpcoes() {
			System.out.println("1- Adicionar horário de acesso \n"
							 + "2- Voltar");
		return leitor.nextInt();
	}

	public <T> int mostraLista(ArrayList<T> lista) {
		for(int i = 0; i < lista.size(); i++){
			System.out.println("" + i+1 + "- " + lista.get(i).toString());
		}
		System.out.println("" + lista.size()+1  + "- Adicionar horário");
		System.out.print("Sua opção: ");
		return leitor.nextInt();
	}
	
}
