package br.ufsc.ine5605;

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
	
}
