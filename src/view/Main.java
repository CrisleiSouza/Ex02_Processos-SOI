package view;
import controller.KillController;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		KillController killC = new KillController();
		Scanner scan = new Scanner(System.in);
		
		killC.listaProcessos();
		
		System.out.println("Insira o nome ou PID do processo que deseja matar: ");
		String processo = scan.next();
		
		killC.mataNome(processo);
		
		System.out.println("Sistema finalizado.");
		scan.close();
	}
}