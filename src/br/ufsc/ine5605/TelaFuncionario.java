public class TelaFuncionario extends Tela {

	public DadosCadastroFuncionario IncluirFuncionario() {
			int matricula = -1;
			String nome;
			Cargo cargo;
			String telefone;
			String dataNascimento;
			int salario;
			
			System.out.println("==== Digite os dados solicitados ====");
			
			matricula = pedeMatricula();
			nome = pedeNome();
			cargo = pedeCargo();
			
			
			
			
			
			System.out.println("Digite o telefone do funcionario(Apenas numeros)");
			telefone = leitor.nextLine();
			if(!telefone.matches("^[0-9]${1,}")) {
				System.out.println("Digite o telefone do funcionario(APENAS NUMEROS)");
				telefone = leitor.nextLine();
			}
			
			System.out.println("digite a data de nascimento do funcionario(No formato ddMMaaaa");
			dataNascimento = leitor.nextLine();
			if (!dataNascimento.matches("^[0-9]${8}")){
				System.out.println("digite a data de nascimento do funcionario(No formato ddMMaaaa");
				dataNascimento = leitor.nextLine();
			}
			System.out.println("digite o salario do Funcionario");
			salario = leInteiroPositivo();
			
			return new DadosCadastroFuncionario(matricula, nome, cargo, telefone, dataNascimento,  salario);

}

	
	private Cargo pedeCargo() {
		boolean eae = false;
		Cargo cargotemp = null;
	 ControladorFuncionario.getInstance().printaListaCargoNomeCodigoHorario();
	 mostraMensagem("Digite o codigo de um dos cargos da lista ou 0 para incluir novo");
	 do{
		 int codcarg = leitor.nextInt();
		 if (codcarg == 0) {
			 ControladorCargo.getInstance().incluirCargo();
		 }
		 
		 if ((codcarg>0) && (codcarg<(ControladorCargo.getInstance().getListaCargos().size()))){
			 cargotemp = ControladorCargo.getInstance().findCargoByCodigo(codcarg);
			 eae = true;
		 }
		 else {
			 mostraMensagem("Digite um número entre 0 e"+ ControladorCargo.getInstance().getListaCargos().size());
		 }
	 }while (!eae);
	 return cargotemp;
	 
	 	
	}


	public void mostraMensagem(String mensagem) {
		System.out.println(mensagem);
	}
	public int pedeMatricula() {
		boolean eae = false;
		int matricula = 0;
		do {	
	mostraMensagem("Digite a matricula do Funcionario(APENAS NUMEROS)");
	 matricula = leInteiroPositivo();
		if(ControladorFuncionario.getInstance().findFuncionarioByMatricula(matricula) == null) {	
		eae = true;
		
	}	else { mostraMensagem("Tente outro número de Matricula, este ja está em uso");
		}
		} while (!eae);
		return matricula;
	
}
	public String pedeNome() {
		boolean eae = false;
		String nome = "";
		do {
		mostraMensagem("Digite o nome do Funcionario");
		String nomet = leitor.nextLine();	
		if (validaNome(nomet, RegrasValidacao.VALIDA_NOME_FUNCIONARIO.getRegraValidacao())) {
			nome = nomet;	
		}
		else {
			mostraMensagem(RegrasValidacao.VALIDA_NOME_FUNCIONARIO.getExplicacaoRegra());
		}
		}while (!eae);
		return nome;
	}

}