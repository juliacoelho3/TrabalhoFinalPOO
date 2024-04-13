package entities;

public abstract class Person {
	private int idPerson;
	protected String nome;
	protected String endereco;
	protected String telefone;
	
	
	public Person(int idPerson, String nome, String endereco, String telefone) {
		super();
		this.idPerson = idPerson;
		this.nome = nome;
		this.endereco = endereco;
		this.telefone = telefone;
	}


	public int getIdPerson() {
		return idPerson;
	}


	public void setIdPerson(int idPerson) {
		this.idPerson = idPerson;
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public String getEndereco() {
		return endereco;
	}


	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}


	public String getTelefone() {
		return telefone;
	}


	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	
	

}
