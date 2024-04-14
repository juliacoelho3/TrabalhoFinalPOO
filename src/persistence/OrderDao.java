package persistence;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import db.ConnectionFactory;
import entities.Client;
import entities.Order;




public class OrderDao {
	private Connection connection;

	public OrderDao() {
		connection = new ConnectionFactory().getConnection();
	}

	public void insert(Order order) {
		String sql = "INSERT INTO geral.pedido"
				+ "(dtemissao, dtentrega, valortotal, observacao, idcliente) "
				+ "VALUES "
				+ "(?, ?, ?, ?, ?)";
		
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setDate(1, new java.sql.Date(order.getIssueDate().getTime()));
			ps.setDate(2, new java.sql.Date(order.getDeliveryDate().getTime()));
			ps.setDouble(3, order.getTotalValue());
			ps.setString(4, order.getObservation());
			ps.setInt(5, order.getClient().getId());
			
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
		String sql = "UPDATE geral.pedido"
				+ "SET idpedido = ?, "
				+ "dtemissao = ?, "
				+ "dtentrega = ?, "
				+ "valortotal = ?, "
				+ "observacao = ?, ";
		
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, order.getId());
			ps.setDate(2, new java.sql.Date(order.getIssueDate().getTime()));
			ps.setDate(3, new java.sql.Date(order.getDeliveryDate().getTime()));
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
		String sql = "DELETE FROM geral.pedido "
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
		String sql = "SELECT * FROM geral.pedido "
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
	
	public List<Order> findByName(String name, int search) {
	    String sql;
	    if (search == 1) {
	        sql = "select * from geral.cliente where nome like '" + name + "%'";
	    } else if (search == 2) {
	        sql = "select * from geral.cliente where nome like '%" + name + "'";
	    } else {
	        sql = "select * from geral.cliente where nome like '%" + name + "%'";
	    }

	    List<Order> orders = new ArrayList<>();

	    try {
	        PreparedStatement stmt = connection.prepareStatement(sql);
	        ResultSet rs = stmt.executeQuery();
	        while (rs.next()) {
	            Order order = new Order(rs.getInt("idpedido"),
	                    rs.getDate("dtemissao"),
	                    rs.getDate("dtentrega"),
	                    rs.getDouble("valortotal"),
	                    rs.getString("observacao"));

	            orders.add(order);

	        }

	        rs.close();
	        stmt.close();
	        connection.close();

	    } catch (SQLException e) {
	        System.err.println("Erro ao buscar pedido!");
	        e.printStackTrace();
	    }
	    return orders;
	}
	
	public List<Order> findByDate(Date startDate, Date endDate) {
	    String sql = "SELECT * FROM geral.pedido WHERE geral.dtemissao BETWEEN ? AND ?";
	    
	    List<Order> orders = new ArrayList<>();

	    try {
	        PreparedStatement stmt = connection.prepareStatement(sql);
	        stmt.setDate(1, startDate);
	        stmt.setDate(2, endDate);
	        ResultSet rs = stmt.executeQuery();
	        
	        while (rs.next()) {
	            Order order = new Order(rs.getInt("idpedido"),
	                    rs.getDate("dtemissao"),
	                    rs.getDate("dtentrega"),
	                    rs.getDouble("valortotal"),
	                    rs.getString("observacao"));

	            orders.add(order);
	        }

	        rs.close();
	        stmt.close();
	        connection.close();
	        
	    } catch (SQLException e) {
	        System.err.println("Erro ao buscar pedidos!");
	        e.printStackTrace();
	    }
	    return orders;
	}
	
	public List<Order> findAll() {
		String sql = "SELECT * FROM geral.pedido";
		List<Order> orders = new ArrayList<>();
		
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				
				Client client = new Client(rs.getString("Client.idnome"), 
						rs.getString("address"), 
						rs.getString("phoneNumber"),
					    rs.getInt("idcliente"),
						rs.getString("cpf"), 
						rs.getDate("birthdate"));
	            
	            
			    Order order = new Order(rs.getInt("idpedido"),
		                    rs.getDate("dtemissao"),
		                    rs.getDate("dtentrega"),
		                    rs.getDouble("valortotal"),
		                    rs.getString("observacao"),
		                    client);
				orders.add(order);
			}
			rs.close();
			ps.close();
			connection.close();
		}
		catch (SQLException e) {
			System.out.println("Erro ao buscar os produtos! ");
			e.printStackTrace();
		}
		return orders;
	}

}