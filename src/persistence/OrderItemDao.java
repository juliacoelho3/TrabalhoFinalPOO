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
		String sql = "INSERT INTO pedidoItens "
				+ "(idpedidoitem, vlunitario, qtproduto, vldesconto, idproduto) "
				+ "VALUES "
				+ "(?, ?, ?, ?, ?)";
		
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, orderItem.getId());
			ps.setDouble(2, orderItem.getUnitValue());
			ps.setInt(3, orderItem.getQuantity());
			ps.setDouble(4, orderItem.getDescountValue());
			ps.setInt(5, orderItem.getProduct().getId());
			
			ps.execute();
			ps.close();
			connection.close();
		}
		
		catch (SQLException e) {
			System.out.println("Erro ao inserir o registro do item do pedido.");
			e.printStackTrace();
		}
		
	}
}
