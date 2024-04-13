package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import db.ConnectionFactory;
import entities.Client;

public class ClientDao {
	
	private Connection connection;
	
	public ClientDao() {
		connection = new ConnectionFactory().getConnection();
	}
	
	public Client findById(Integer id) {
		String sql = "SELECT * FROM cliente "
				+ "WHERE idcliente = ?";
		
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				Client client = new Client(rs.getString("name"),
						rs.getString("address"),
						rs.getString("phoneNumber"),
						rs.getInt("idcliente"),
						rs.getString("cpf"),
						rs.getDate("birthdate"));
			}
			
			rs.close();
			ps.close();
			connection.close();	
		}
		
		catch (SQLException e) {
			System.out.println("Erro ao buscar cliente! ");
			e.printStackTrace();
		}
		return null;
	}
	
	
	
	
}