package controller;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;


public class KillController {
	public KillController(){
		super();
	}
	
	private String os() {
		return System.getProperty("os.name");
	}
	
	public void listaProcessos() {
		String[] comando;
		
		if (os().contains("Windows")) {
			comando = "TASKLIST /FO TABLE".split(" ");
		}
		else {
			comando = "ps -ef".split(" ");
		}
		
		try {
			Process p = Runtime.getRuntime().exec(comando);
			
			InputStream stream = p.getInputStream();
			InputStreamReader leitor = new InputStreamReader(stream);
			BufferedReader ler = new BufferedReader(leitor);
			String linha = ler.readLine();
			
			while (linha != null) {
				System.out.println(linha.trim());
				linha = ler.readLine();
			}
			ler.close();
			leitor.close();
			stream.close();
			
		}catch(Exception e) {
			System.err.println(e.getMessage());
		}	
	}
	
	public void mataNome(String nome) {
		try {
			int pid = Integer.parseInt(nome);
			mataPid(pid);
		} catch(Exception e) {
			StringBuffer buffer = new StringBuffer();
			if (os().contains("Windows")) {
				buffer.append("TASKKILL /IM ");
			}
			else {
				buffer.append("pkill -f ");
			}
			buffer.append(nome);
			String[] comando = (buffer.toString()).split(" ");
			
			try {
				Runtime.getRuntime().exec(comando);
				
			} catch(Exception e2) {
				System.err.println(e2.getMessage());
			}
		}
	}
	
	public void mataPid(int pid) {
		StringBuffer buffer = new StringBuffer();
		
		if (os().contains("Windows")) {
			buffer.append("TASKKILL /PID ");
		}
		else {
			buffer.append("kill -9 ");
		}
		buffer.append(pid);
		String[] comando = (buffer.toString()).split(" ");
		
		try {
			Runtime.getRuntime().exec(comando);
		} catch(Exception e) {
			System.err.println(e.getMessage());
		}
	}	
}
