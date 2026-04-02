package src;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class JogoDaForca {
	private ArrayList<String> bancoPalavras, resultados = new ArrayList<>();
	private ArrayList<Integer> ocorrencias = new ArrayList<>();
	private Integer sorteioAtual, acertos, codidoPenalidade;
	private String palavra, dica, nomePenalidade, resultado;
	
	
	public String getDica() {
		return dica;
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
    
    public void iniciar() {
    	Random aleatorio = new Random();
    	sorteioAtual = aleatorio.nextInt(0, bancoPalavras.size());
    	
    	String[] palavraDica = bancoPalavras.get(sorteioAtual).split(",");
    
    	palavra = palavraDica[0];
    	dica = palavraDica[1];
    }
    
    public ArrayList<Integer> getOcorrencias(String letra){
    	if (letra.contains(palavra)) {
    		String[] caracteres = palavra.split("");
    		for (int i=0; i<=caracteres.length; i++) {
    			if (letra.equals(caracteres[i])) ocorrencias.add(i);
    		}
    	} 
    	return ocorrencias;
    }
    
    public static void main(String[] args) {
    	JogoDaForca x = new JogoDaForca();
    	
    }
}
