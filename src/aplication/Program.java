package aplication;

import java.text.ParseException;
import java.util.Scanner;

public class Program {

	public static void main(String[] args) throws ParseException {
		
		Scanner sc = new Scanner(System.in);
		
		int opcao = 0;
		int opcao2 = 0;
		
		while(opcao != 7) {
			switch (opcao) {
			case 0:
				System.out.print(Menu.menuInicial());
				opcao = sc.nextInt();
				break;
			case 1:
				System.out.println("Não implementado");
				break;
			case 2:
				Menu.novoPedido();
				break;
			case 7:
				System.out.println("Saindo do sistema.");
			default:
				System.out.println("Valor inválido.");
				
			}
		}		
		
		sc.close();
	}

}
