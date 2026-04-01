package src;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class JogoDaForca {
	private ArrayList<String> bancoPalavras = new ArrayList<>();
	
	public ArrayList<String> getBancoPalavras() {
		return bancoPalavras;
	}
    public JogoDaForca(){
        try{
            String linha;
            Scanner arquivo = new Scanner(new File("palavras.csv"));
            while (arquivo.hasNextLine()) {
                linha = arquivo.nextLine();
                this.bancoPalavras.add(linha);
            }
            arquivo.close();
        } catch (FileNotFoundException e){
            System.out.println("Cadê o arquivo?");
        }
    }
    
    
    public static void main(String[] args) {
    	JogoDaForca x = new JogoDaForca();
    	System.out.println(x.getBancoPalavras());
    }
}
