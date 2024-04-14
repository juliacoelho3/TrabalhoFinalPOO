package aplication;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import entities.Client;
import entities.Order;
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
		
		// Cliente fixo mockado, ajustar posteriormente
		Client client = new Client("Fixo", "Fixo", "Fixo", 1, "Fixo", new Date());
		
		Order order = new Order(null, issueDate, deliveryDate, totalValue, observation, client);
		OrderDao dao = new OrderDao();
		dao.insert(order);
		
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
	

}
