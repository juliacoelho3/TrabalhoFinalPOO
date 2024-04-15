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
		String sql = "INSERT INTO geral.pedidositens "
				+ "(vlunitario, qtproduto, vldesconto, idpedido, idproduto) "
				+ "VALUES "
				+ "(?, ?, ?, ?, ?)";
		
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setDouble(1, orderItem.getUnitValue());
			ps.setInt(2, orderItem.getQuantity());
			ps.setDouble(3, orderItem.getDescountValue());
			ps.setInt(4, orderItem.getOrder().getId());
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
	
	public void update(OrderItem orderItem) {
		String sql = "UPDATE geral.pedidositens "
				+ "SET vlunitario = ?, "
				+ "qtproduto = ?, "
				+ "vldesconto = ?, "
				+ "idpedido = ?, "
				+ "idproduto = ?";
		
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setDouble(1, orderItem.getUnitValue());
			ps.setInt(2, orderItem.getQuantity());
			ps.setDouble(3, orderItem.getDescountValue());
			ps.setInt(4, orderItem.getOrder().getId());
			ps.setInt(5, orderItem.getProduct().getId());
			
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
		String sql = "DELETE FROM geral.pedidositens "
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
				+ "PedidosItens.idpedidoitem, "
				+ "PedidosItens.vlunitario, "
				+ "PedidosItens.qtproduto, "
				+ "PedidosItens.vldesconto, "
				+ "Produto.idproduto, "
				+ "Produto.descricao, "
				+ "Produto.vlcusto, "
				+ "Produto.vlvenda, "
				+ "Produto.categoria "
				+ "FROM geral.pedidositens "
				+ "INNER JOIN geral.Pedido ON PedidosItens.idpedido = Pedido.idpedido "
				+ "INNER JOIN geral.Produto ON PedidosItens.idproduto = Produto.idproduto "
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
				
				
				OrderItem orderItem = new OrderItem(rs.getInt("PedidosItens.idpedidoitem"),
						rs.getDouble("PedidosItens.vlunitario"),
						rs.getInt("PedidosItens.qtproduto"),
						rs.getDouble("PedidosItens.vldesconto"),
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
	
}
