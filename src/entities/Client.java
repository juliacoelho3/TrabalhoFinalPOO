package entities;

import java.util.Date;

public class Client extends Person {

	private int id;
	private String cpf;
	private Date birthDate;

	public Client(String name, String address, String phoneNumber) {
		super(name, address, phoneNumber);
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
	
	}
