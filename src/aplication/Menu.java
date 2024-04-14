package aplication;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import entities.Client;
import entities.Order;
import persistence.ClientDao;
import persistence.OrderDao;

public class Menu {
	
	static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
	static Scanner sc = new Scanner(System.in);
	
	public static String menuInicial() {
		return "###############################"
				+ "\n#        MENU INICIAL         #"
				+ "\n###############################"
				+ "\n"
				+ "\n1 - VISUALIZAR PEDIDOS"
				+ "\n2 - NOVO PEDIDO"
				+ "\n3 - EDITAR PEDIDO"
				+ "\n4 - EXCLUIR PEDIDO"
				+ "\n"
				+ "\nDIGITE O VALOR DESEJADO: ";
	}
	
	public static String subMenuTipoVisualizacaoPedido() {
		return "\n1 - VISUALIZAR PEDIDO COM INFORMAÇÕES DO CLIENTE E DOS PRODUTOS"
				+ "\n2 - VISUALIZAR PEDIDO APENAS COM AS INFORMAÇÕES DO CLIENTE";
	}
	
	public static String subMenuFiltrarPedido() {
		return "\n1 - LOCALIZAR PEDIDO POR CÓDIGO"
				+ "\n2 - LOCALIZAR PEDIDO POR DATA"
				+ "\n3 - EXIBIR TODOS OS PEDIDOS";
	}
	
	public static void novoPedido() throws ParseException {
		System.out.println("###############################"
				+ "\n#         NOVO PEDIDO         #"
				+ "\n###############################");
		System.out.print("Informe a data do pedido: ");
		Date issueDate = sdf.parse(sc.next());
		System.out.print("Informe a data de entrega do pedido: ");
		Date deliveryDate = sdf.parse(sc.next());
		System.out.print("Informe o valor total do pedido: ");
		Double totalValue = sc.nextDouble();
		System.out.println("Informe uma observação sobre o pedido:");
		sc.nextLine();
		String observation = sc.nextLine();
		
		System.out.println("Informe o código do cliente:");
		Integer idclient = sc.nextInt();
		ClientDao clientDao = new ClientDao();
		Client c = clientDao.findById(idclient);
		
		Client client = new Client(c.getName(),
				c.getAddress(),
				c.getPhoneNumber(),
				c.getId(),
				c.getCpf(),
				c.getBirthDate());
		
		Order order = new Order(null, issueDate, deliveryDate, totalValue, observation, client);
		OrderDao orderDao = new OrderDao();
		orderDao.insert(order);
		
		System.out.println("Pedido incluído com sucesso.");
	}
	
	public static void digiteParaContinuar() {
		System.out.print("\nPressione ENTER para continuar: ");
		sc.nextLine();
	}
	
	public static void imprimirPedidos() {
		System.out.println("###############################"
				+ "\n#       LISTA DE PEDIDOS      #"
				+ "\n###############################");
		OrderDao dao = new OrderDao();
		for (Order o : dao.findAll()) {
			System.out.println(o);
		}
	}
	
	public static void excluirPedido() {
		System.out.println("###############################"
				+ "\n#       EXCLUIR PEDIDO        #"
				+ "\n###############################");
		System.out.print("Digite o código do pedido: ");
		Integer id = sc.nextInt();
		
		OrderDao dao = new OrderDao();
		dao.delete(id);
	}
	
	public static void editarPedido() throws ParseException {
		System.out.println("###############################"
				+ "\n#        EDITAR PEDIDO        #"
				+ "\n###############################");
		System.out.print("Digite o código do pedido: ");
		Integer id = sc.nextInt();
		
		System.out.print("Informe a data do pedido: ");
		Date issueDate = sdf.parse(sc.next());
		System.out.print("Informe a data de entrega do pedido: ");
		Date deliveryDate = sdf.parse(sc.next());
		System.out.print("Informe o valor total do pedido: ");
		Double totalValue = sc.nextDouble();
		System.out.println("Informe uma observação sobre o pedido:");
		sc.nextLine();
		String observation = sc.nextLine();
		
		System.out.println("Informe o código do cliente:");
		Integer idclient = sc.nextInt();
		ClientDao clientDao = new ClientDao();
		Client c = clientDao.findById(idclient);
		
		Client client = new Client(c.getName(),
				c.getAddress(),
				c.getPhoneNumber(),
				c.getId(),
				c.getCpf(),
				c.getBirthDate());
		
		Order order = new Order(id, issueDate, deliveryDate, totalValue, observation, client);
		OrderDao orderDao = new OrderDao();
		orderDao.update(order);
		
		System.out.println("Pedido editado com sucesso.");
		
		System.out.println("Novas informações: " + orderDao.findById(id));
	}
	

}
