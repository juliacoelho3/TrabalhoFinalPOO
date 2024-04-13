package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import db.ConnectionFactory;
import entities.Product;

public class ProductDao {
	
	private Connection connection;
	
	public ProductDao() {
		connection = new ConnectionFactory().getConnection();
	}

	public void insert(Product product) {
		String sql = "INSERT INTO produto "
				+ "(descricao, vlcusto, vlvenda, categoria) "
				+ "VALUES "
				+ "(?, ?, ?, ?)";
		
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, product.getDescription());
			ps.setDouble(2, product.getCostValue());
			ps.setDouble(3, product.getSaleValue());
			ps.setString(4, product.getCategory());
			
			ps.execute();
			ps.close();
			connection.close();
		}
		
		catch (SQLException e) {
			System.out.println("Erro ao inserir o registro de produto.");
			e.printStackTrace();
		}
		
	}

	public void update(Product product) {
		// TODO Auto-generated method stub
		
	}

	public void delete(Integer id) {
		// TODO Auto-generated method stub
		
	}

	public Product findById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Product> findAll() {
		// TODO Auto-generated method stub
		return null;
	}


}
