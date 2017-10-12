package br.ufsc.ine5605;

public class TelaFuncionario {
	package br.ufsc.ine5605;

	public class TelaFuncionario extends Tela {

		public void IncluirFuncionario() {
			int matricula = -1;
			String nome;
			String cargo;
			String telefone;
			String dataNascimento;
			int salario;
			int numeroAcessosNegados = 0;
			
			System.out.println("==== Digite os dados solicitados ====");
			
			
			System.out.println("Digite o nome do Funcionario");
			nome = leitor.nextLine();
			
			if (!nome.matches("^[A-Z][a-z]+([ ][A-Z][a-z]+)*$")) {
				System.out.println("Digite apenas o primeiro e último nome do funcionario(Apenas letras, sem acentuação gráfica)")
				nome = leitor.nextLine();
			}
			
			System.out.println("selecione um cargo na lista ou digite '0' para cadastrar novo");
			
			ControladorCargo.getInstance().getListaCargos().get(index)
			ControladorCargo.getInstance();
			TelaCargo.listarCargos(getListaCargos());
			cargo = leitor.nextLine();
			
			if (!cargo.matches([0-1])
			
			
			--Leitor.nextLine com .matches [0-num de cargos {1}]
			--Criar cargo caso 0
			--selecionar da lista e vincular
			
			
			System.out.println("Digite o telefone do funcionario(Apenas numeros)");
			telefone = leitor.nextLine();
			if(!telefone.matches("^[0-9]${1,}")) {
				System.out.println("Digite o telefone do funcionario(APENAS NUMEROS)")
				telefone = leitor.nextLine();
			}
			
			System.out.println("digite a data de nascimento do funcionario(No formato ddMMaaaa");
			dataNascimento = leitor.nextLine();
			if (!dataNascimento.matches("^[0-9]${8}")){
				System.out.println("digite a data de nascimento do funcionario(No formato ddMMaaaa");
				dataNascimento = leitor.nextLine();
			}
			System.out.println("digite o salario do Funcionario");
			salario = super.leInteiroPositivo();

}
