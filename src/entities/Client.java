package entities;

import java.util.Date;

public class Client extends Person {

	public Client(int idPerson, String nome, String endereco, String telefone) {
		super(idPerson, nome, endereco, telefone);
		// TODO Auto-generated constructor stub
	}

	private int idCliente;
	private String cpf;
	private Date dataNascimento;

	public int getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

}
