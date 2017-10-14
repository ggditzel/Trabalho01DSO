package br.ufsc.ine5605;

public class DadosCadastroFuncionario {
		public int matricula;
		public String nome;
		private Cargo cargo;
		private String telefone;
		private String dataNascimento;
		private int salario;
		private int numeroAcessosNegados;
		
		
		public DadosCadastroFuncionario(int matricula, String nome, Cargo cargo, String telefone, String dataNascimento, int salario) {
			this.matricula = matricula;
			this.nome = nome;
			this.cargo = cargo;
			this.telefone = telefone;
			this.dataNascimento = dataNascimento;
			this.salario = salario;
			this.numeroAcessosNegados = 0;
		}
}
