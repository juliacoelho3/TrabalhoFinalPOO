package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.ConnectionFactory;
import entities.Order;
import entities.OrderItem;
import entities.Product;

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
	
	public List<OrderItem> findByOrder(Integer id) {
		String sql = "SELECT "
				+ "Pedido.idpedido, "
				+ "Pedido.dtemissao, "
				+ "Pedido.dtentrega, "
				+ "Pedido.valortotal, "
				+ "Pedido.observacao, "
				+ "Pedido.idcliente, "
				+ "PedidoItens.idpedidoitem, "
				+ "PedidoItens.vlunitario, "
				+ "PedidoItens.qtproduto, "
				+ "PedidoItens.vldesconto, "
				+ "Produto.idproduto, "
				+ "Produto.descricao, "
				+ "Produto.vlcusto, "
				+ "Produto.vlvenda, "
				+ "Produto.categoria "
				+ "FROM pedidoitens "
				+ "INNER JOIN Pedido ON PedidoItens.idpedido = Pedido.idpedido "
				+ "INNER JOIN Produto ON PedidoItens.idproduto = Produto.idproduto "
				+ "WHERE idpedido = ?";
		List<OrderItem> orderItens = new ArrayList<>();
		
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				
				Product product = new Product(rs.getInt("Produto.idproduto"),
						rs.getString("Produto.descricao"),
						rs.getDouble("Produto.vlcusto"),
						rs.getDouble("Produto.vlvenda"),
						rs.getString("Produto.categoria"));
				
				Order order = new Order(rs.getInt("Pedido.idpedido"),
						rs.getDate("Pedido.dtemissao"),
						rs.getDate("Pedido.dtentrega"),
						rs.getDouble("Pedido.valortotal"),
						rs.getString("Pedido.observacao"));
				
				
				OrderItem orderItem = new OrderItem(rs.getInt("PedidoItens.idpedidoitem"),
						rs.getDouble("PedidoItens.vlunitario"),
						rs.getInt("PedidoItens.qtproduto"),
						rs.getDouble("PedidoItens.vldesconto"),
						order,
						product);
				
				orderItens.add(orderItem);
			}
			
			rs.close();
			ps.close();
			connection.close();	
		}
		
		catch (SQLException e) {
			System.out.println("Erro ao buscar o produto! ");
			e.printStackTrace();
		}
		return orderItens; 
	}
	
	
	/*
	public List<OrderItem> findByOrder(Integer id) {
		String sql = "SELECT * FROM pedidoitens "
				+ "WHERE idpedido = ?";
		List<OrderItem> orderItens = new ArrayList<>();
		
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				OrderItem orderItem = new OrderItem(rs.getInt("idpedidoitem"),
						rs.getDouble("vlunitario"),
						rs.getInt("qtproduto"),
						rs.getDouble("vldesconto"),
						rs.getInt("idpedido"),
						rs.getInt("idproduto"));
				orderItens.add(orderItem);
			}
			
			rs.close();
			ps.close();
			connection.close();	
		}
		
		catch (SQLException e) {
			System.out.println("Erro ao buscar o produto! ");
			e.printStackTrace();
		}
		return null;
	}
	*/
	
}
