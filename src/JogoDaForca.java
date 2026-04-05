package src;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

import javax.swing.JOptionPane;

public class JogoDaForca {
	private ArrayList<String> bancoPalavras= new ArrayList<>();
	private Set<String>letrasCertas = new HashSet<>();
	private Integer sorteioAtual, acertos=0, penalidade=0;
	private String palavraCerta, dica, resultado="em andamento",palavra="";
	private ArrayList <String> resultados = new ArrayList<>();
	private ArrayList <String> nomePenalidade = new ArrayList<>(List.of("sem penalidades","perdeu primeira perna","perdeu segunda perna","perdeu primeiro braço", "perdeu segundo braço", "perdeu tronco", "perdeu a cabeça"));
	
	public String mostrarPalavra(){
		return palavraCerta;
	}

    public JogoDaForca(){
        	InputStream stream = this.getClass().getResourceAsStream("dados/palavras.csv");
        	if (stream == null) {
        		JOptionPane.showMessageDialog(null, "Arquivo de palavras inexistente!");
        			System.exit(0);
        		}
            String linha;
            Scanner arquivo = new Scanner(stream);
            while (arquivo.hasNextLine()) {
                linha = arquivo.nextLine();
                this.bancoPalavras.add(linha);
            }
            arquivo.close();
            }
    
    public void iniciar() {
		resultado = "em andamento";
		palavra = "";
		penalidade = 0;
		acertos = 0;
		letrasCertas.clear();
    	Random aleatorio = new Random();
    	sorteioAtual = aleatorio.nextInt(0, bancoPalavras.size());
    	
    	String[] palavraDica = bancoPalavras.get(sorteioAtual).split(",");
    
    	palavraCerta = palavraDica[0];
		for(int i=0; i<palavraCerta.length(); i++){
			palavra+= "*";
		}

    	dica = palavraDica[1];
    }
    
    public ArrayList<Integer> getOcorrencias(String letra) throws Exception{
		if (letra.length() > 1 || letra.length() < 1) 
			throw new Exception("Digite apenas uma letra válida!");

		letra = letra.toUpperCase();

		ArrayList<Integer> numeros = new ArrayList<>();
		StringBuilder posicoesMutaveis = new StringBuilder(palavra);

    	if (palavraCerta.contains(letra)) {
			letrasCertas.add(letra);
			String[] caracteres = palavraCerta.split("");
			
    		for (int i=0; i< caracteres.length; i++) {
				if (letra.equals(caracteres[i])){
					acertos++;
					posicoesMutaveis.setCharAt(i, letra.charAt(0));
					numeros.add(i+1);
					palavra = posicoesMutaveis.toString();
				}
    		}
    	} 
		else{
			penalidade++;
		}

		if(palavra.equals(palavraCerta)){
			resultados.add(palavraCerta + " - Ganhou");
			resultado="venceu";
		}

		if(penalidade >= 6){
			resultados.add(palavraCerta + " - Perdeu");
			resultado = "perdeu";
		}

    	return numeros;
    }
    
	public Integer getAcertos(){
		return acertos;
	}

	public String getResultado(){
		return resultado;
	}

	public String getDicas(){
		return dica;
	}

	public String getPalavra(){
		return palavra;
	}

	public Integer getCodigoPenalidade(){
		return penalidade;
	}

	public String getNomePenalidade(){
		return nomePenalidade.get(penalidade);
	}

	public boolean terminou(){
		return !(resultado.equals("em andamento"));
	}
    public static void main(String[] args) throws Exception {
		JogoDaForca x = new JogoDaForca();
		int control;
		Scanner sc = new Scanner(System.in);

		while (!x.terminou()){
			x.iniciar();
			System.out.println("Seu histórico de partidas é: ");
			System.out.println(x.resultados);
			System.out.println(x.palavraCerta);
			System.out.println("A dica é: " + x.dica);
			while (x.getResultado().equals("em andamento")){
				System.out.println("Adivinhe a palavra");
				System.out.println(x.palavra);
				String letra = sc.nextLine();
				System.out.println("Você acertou as letras nas posições: " + x.getOcorrencias(letra));
				System.out.println("As letras acertadas são: " + x.letrasCertas);
				System.out.println(x.getNomePenalidade());
				System.out.println("Quantidade de acertos: " + x.acertos);
				System.out.println(x.terminou());
			}

			System.out.println("Gostaria de continuar?");
			control = sc.nextInt();
			if (control == 0) x.resultado = "em andamento";
			sc.nextLine();

		}
		sc.close();
    }
}
