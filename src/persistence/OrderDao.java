package persistence;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import db.ConnectionFactory;
import entities.Order;



public class OrderDao {
	private Connection connection;

	public OrderDao() {
		connection = new ConnectionFactory().getConnection();
	}

	public void insert(Order order) {
		String sql = "INSERT INTO pedido"
				+ "(descricao, vlcusto, vlvenda, categoria, idcliente) "
				+ "VALUES "
				+ "(?, ?, ?, ?, ?)";
		
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, order.getId());
			ps.setDate(2, (Date) order.getIssueDate());
			ps.setDate(3, (Date) order.getDeliveryDate());
			ps.setDouble(4, order.getTotalValue());
			ps.setString(5, order.getObservation());
			ps.setInt(6, order.getClient().getId());
			
			ps.execute();
			ps.close();
			connection.close();
		}
		
		catch (SQLException e) {
			System.out.println("Erro ao inserir o registro do pedido.");
			e.printStackTrace();
		}
	}
	
	public void update(Order order) {
		String sql = "UPDATE pedido"
				+ "SET idpedido = ?, "
				+ "dtemissao = ?, "
				+ "dtentrega = ?, "
				+ "valortotal = ?, "
				+ "observacao = ?, ";
		
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, order.getId());
			ps.setDate(2, (Date) order.getIssueDate());
			ps.setDate(3, (Date) order.getDeliveryDate());
			ps.setDouble(4, order.getTotalValue());
			ps.setString(5, order.getObservation());
			ps.setInt(6, order.getClient().getId());
			
			ps.execute();
			ps.close();
			connection.close();
		}
		
		catch (SQLException e) {
			System.out.println("Erro ao atualizar o registro do pedido.");
			e.printStackTrace();
		}
	}
	
	public void delete(Integer id) {
		String sql = "DELETE FROM pedido "
				+ "WHERE idpedido = ?";
		
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, id);
			
			ps.execute();
			ps.close();
			connection.close();
		}
		
		catch (SQLException e) {
			System.out.println("Erro ao excluir o registro do pedido.");
			e.printStackTrace();
		}
	}
	public Order findById(Integer id) {
		String sql = "SELECT * FROM produto "
				+ "WHERE idproduto = ?";
		
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				Order order = new Order(rs.getInt("idpedido"),
						rs.getDate("dtemissao"),
						rs.getDate("dtentrega"),
						rs.getDouble("valortotal"),
						rs.getString("observacao"));
				return order;
			}
			
			rs.close();
			ps.close();
			connection.close();	
		}
		
		catch (SQLException e) {
			System.out.println("Erro ao buscar o pedido! ");
			e.printStackTrace();
		}
		return null;
	}
	
}
