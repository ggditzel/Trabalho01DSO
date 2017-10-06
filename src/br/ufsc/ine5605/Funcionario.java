package br.ufsc.ine5605;

import java.util.Date;

public class Funcionario {
	private int matricula;
	private String nome;
	private Cargo cargo;
	private String telefone;
	private String dataNascimento;
	private String salario;
	private int numeroAcessosNegados;
	
	
	public Funcionario(int matricula, String nome, Cargo cargo, String telefone, String dataNascimento, String salario) {
		this.matricula = matricula;
		this.nome = nome;
		this.cargo = cargo;
		this.telefone = telefone;
		this.dataNascimento = dataNascimento;
		this.salario = salario;
		this.numeroAcessosNegados = 0;
	}
	public int getMatricula() {
		return matricula;
	}
	public void setMatricula(int matricula) {
		this.matricula = matricula;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Cargo getCargo() {
		return cargo;
	}
	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public String getDataNascimento() {
		return dataNascimento;
	}
	public void setDataNascimento(String dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	public String getSalario() {
		return salario;
	}
	public void setSalario(String salario) {
		this.salario = salario;
	}
	public int getNumeroAcessosNegados() {
		return numeroAcessosNegados;
	}
	public void setNumeroAcessosNegados(int numeroAcessosNegados) {
		this.numeroAcessosNegados = numeroAcessosNegados;
	}

}
