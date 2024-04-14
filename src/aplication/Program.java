package aplication;

import java.text.ParseException;
import java.util.Scanner;

public class Program {

	public static void main(String[] args) throws ParseException {
		
		Scanner sc = new Scanner(System.in);
		
		int opcao = 0;
		int opcao2 = 0;
		
		switch (opcao) {
		case 0:
			System.out.println(Menu.menuInicial());
			opcao = sc.nextInt();
			break;
		case 1:
			System.out.println("Não implementado");
			break;
		case 2:
			Menu.novoPedido();
		default:
			System.out.println("Valor inválido.");
			
		}
		
		
		
		sc.close();
	}

}
