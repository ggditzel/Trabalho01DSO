package br.ufsc.ine5605;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class TelaTentativaAcesso extends Tela {

	public void mostraNegacao(MotivoNegacaoAcesso motivo) {
		switch(motivo){
		case ACESSO_BLOQUEADO:
			System.out.println("Acesso bloqueado.");
			break;
		case HORARIO_NAO_PERMITIDO:
			System.out.println("Horario não permitido.");
			break;
		case MATRICULA_INEXISTENTE:
			System.out.println("Matricula nao existe.");
			break;
		case NAO_POSSUI_ACESSO:
			System.out.println("Nao possui acesso.");
			break;
		}
		
	}

	public int mostraOpcoes() {
		System.out.println("1- Usar horario e data local");
		System.out.println("2- Definir horaio e data (para testes)");
		System.out.println("0- Voltar");
		return super.leInteiroPositivoAte(2);
	}

	public String perguntaHora() {
		String hora = "";
		boolean respostaOK = false;
		System.out.println("Digite o horario: ");
		do{
			hora = leitor.nextLine();
			if(hora.matches("([01]?[0-9]|2[0-3]):[0-5][0-9]") ) {
				respostaOK = true;
				if(hora.charAt(1) == ':') {
					hora = "0"+ hora;
				}
			} else {
				System.out.println("Formato de hora invalido.\nDigite novamente");
				respostaOK = false; // redundante
			}
		}while(!respostaOK);
		
		return hora;
	}

	public int perguntaMatricula() {
		System.out.println("Digite sua matricula:"); 
		return leInteiroPositivo();
	}

	public String perguntaData() {
		String data = "";
		boolean respostaOK = false;
		System.out.println("Digite a data do acesso (dd/mm/aaaa): ");
		do{
			data = leitor.nextLine();
			if(dataEhValida(data) ) {
				respostaOK = true;
				if(data.charAt(1) == '/') {
					data = "0"+ data;
				}
				if(data.charAt(4) == '/') {
					data = data.substring(0, 3) + "0" + data.substring(3);
				}
				if(data.length() != 10) {
					respostaOK = false;
					System.out.println("Formato de data invalido.\nDigite novamente");
				}
			} else {
				System.out.println("Formato de data invalido.\nDigite novamente");
				respostaOK = false; // redundante
			}
		}while(!respostaOK);
		
		return data;
	}
	
	private boolean dataEhValida(String text) {
		   
	    SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");

	    df.setLenient(false);
	    try {
	        df.parse(text);
	        return true;
	    } catch (ParseException ex) {
	        return false;
	    }
	}

	public void confirmaAcesso() {
		System.out.println("Acesso permitido.");
		
	}
	
}
