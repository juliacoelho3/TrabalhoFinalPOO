package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import db.ConnectionFactory;
import entities.OrderItem;

public class OrderItemDao {

	private Connection connection;
	
	public OrderItemDao() {
		connection = new ConnectionFactory().getConnection();
	}
	
	public void insert(OrderItem orderItem) {
		String sql = "INSERT INTO pedidoitens "
				+ "(idpedidoitem, vlunitario, qtproduto, vldesconto, idpedido, idproduto) "
				+ "VALUES "
				+ "(?, ?, ?, ?, ?)";
		
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, orderItem.getId());
			ps.setDouble(2, orderItem.getUnitValue());
			ps.setInt(3, orderItem.getQuantity());
			ps.setDouble(4, orderItem.getDescountValue());
			ps.setInt(5, orderItem.getOrder().getId());
			ps.setInt(6, orderItem.getProduct().getId());
			
			ps.execute();
			ps.close();
			connection.close();
		}
		
		catch (SQLException e) {
			System.out.println("Erro ao inserir o registro do item do pedido.");
			e.printStackTrace();
		}
	}
	
	public void update(OrderItem orderItem) {
		String sql = "UPDATE pedidoitens "
				+ "SET idpedidoitem = ?, "
				+ "vlunitario = ?, "
				+ "qtproduto = ?, "
				+ "vldesconto = ?, "
				+ "idpedido = ?, "
				+ "idproduto = ?";
		
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, orderItem.getId());
			ps.setDouble(2, orderItem.getUnitValue());
			ps.setInt(3, orderItem.getQuantity());
			ps.setDouble(4, orderItem.getDescountValue());
			ps.setInt(5, orderItem.getOrder().getId());
			ps.setInt(6, orderItem.getProduct().getId());
			
			ps.execute();
			ps.close();
			connection.close();
		}
		
		catch (SQLException e) {
			System.out.println("Erro ao atualizar o registro do item do pedido.");
			e.printStackTrace();
		}
	}
	
	public void delete(Integer id) {
		String sql = "DELETE FROM pedidoitens "
				+ "WHERE idpedidoitem = ?";
		
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, id);
			
			ps.execute();
			ps.close();
			connection.close();
		}
		
		catch (SQLException e) {
			System.out.println("Erro ao excluir o registro do item do pedido.");
			e.printStackTrace();
		}
	}
	
	
}
