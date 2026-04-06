package src;

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
	private Set<String>letrasDigitadas = new HashSet<>();
	private Integer sorteioAtual, acertos=0, penalidade=0;
	private String palavraCerta, dica, resultado="a iniciar",palavra="",acertou="não";
	private ArrayList <String> resultados = new ArrayList<>();
	private ArrayList <String> nomePenalidade = new ArrayList<>(List.of("sem penalidades","perdeu primeira perna","perdeu segunda perna","perdeu primeiro braço", "perdeu segundo braço", "perdeu tronco", "perdeu a cabeça"));
	
	public String mostrarPalavra(){
		return palavraCerta;
	}

    public JogoDaForca(){
        	InputStream stream = this.getClass().getResourceAsStream("./dados/palavras.csv");
			System.out.println(getClass().getResource(""));
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
    
    public void iniciar() throws Exception{
    	if (this.resultado.equals("em andamento")) {
    		throw new Exception("Encerre a partida primeiro");
    	}
		resultado = "em andamento";
		palavra = "";
		penalidade = 0;
		acertos = 0;
		letrasCertas.clear();
		letrasDigitadas.clear();
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
    	letra = letra.toUpperCase();
		if (letra.length() > 1 || letra.length() < 1) 
			throw new Exception("Digite apenas uma letra válida!");
		
		if (letrasDigitadas.contains(letra)) {
			throw new Exception("Você já digitou essa letra");
		}
		char letraChar = letra.charAt(0);
		
		if (letraChar < 'A' || letraChar > 'Z' ) {
			throw new Exception("Digite uma letra válida");
		}
		
		letrasDigitadas.add(letra);


		ArrayList<Integer> numeros = new ArrayList<>();
		StringBuilder posicoesMutaveis = new StringBuilder(palavra);

    	if (palavraCerta.contains(letra)) {
    		acertou = "Você acertou!";
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
			acertou = "Você errou!";
			penalidade++;
		}

		if(palavra.equals(palavraCerta)){
			resultados.add(palavraCerta + " - Ganhou");
			resultado="Você Venceu!!!";
		}

		if(penalidade >= 6){
			resultados.add(palavraCerta + " - Perdeu");
			resultado = "Você Perdeu!!!";
		}

    	return numeros;
    }
    
	public Integer getAcertos(){
		return acertos;
	}
	
	public String getAcertou() {
		return acertou;
	}

	public String getResultado(){
		return resultado;
	}
	
	public String getResultados() {
		String a = "";
		for (int i=0; i<resultados.size(); i++) {
			a = a + resultados.get(i) + "\n";
		}
		return a;
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
