package br.ufsc.ine5605;

import java.util.ArrayList;
import java.util.Scanner;


public class TelaHorario {
    private Scanner leitor;

    
    public TelaHorario(){
        leitor = new Scanner(System.in);
    }

    public void mostrarAviso(String aviso){
        System.out.println(aviso);
    }

    public String perguntaInicio() {
        System.out.println("Insira o início do horário (hh:mm): ");
        return leitor.nextLine();
    }
    public String perguntaFim() {
        System.out.println("Insira o fim do horário (hh:mm): ");
        return leitor.nextLine();
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
