package src;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

public class TelaJogo {

	private JFrame frame;
	private JLabel labelDica;
	private JLabel labelLetra;
	private JTextField letraInput;
	private JButton buttonAdvinhar;
	private JButton buttonIniciar;
	private JLabel labelPalavra;
	private JLabel labelAcertos;
	private JLabel labelPenalidade;
	private JLabel labelResultado;
	private JLabel labelImagem;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaJogo window = new TelaJogo();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TelaJogo() {
		initialize();
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		frame = new JFrame();
		frame.setTitle("TelaJogo");
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		labelDica = new JLabel("Dica:");
		labelDica.setBounds(23, 54, 415, 17);
		frame.getContentPane().add(labelDica);
		
		labelLetra = new JLabel("Letra:");
		labelLetra.setBounds(23, 83, 60, 17);
		frame.getContentPane().add(labelLetra);
		
		letraInput = new JTextField();
		letraInput.setBounds(67, 81, 52, 21);
		frame.getContentPane().add(letraInput);
		letraInput.setColumns(10);
		
		
		
		JogoDaForca novoJogo = new JogoDaForca();
		buttonAdvinhar = new JButton("Adivinhar");
		buttonAdvinhar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					novoJogo.getOcorrencias(letraInput.getText());
					
					labelPalavra.setText("Palavra= "+ novoJogo.getPalavra());
					labelAcertos.setText("Acertos: "  + novoJogo.getAcertos());
					
					
					labelPenalidade.setText("Penalidade: " + novoJogo.getNomePenalidade());
					labelResultado.setText(novoJogo.getResultado());
					
					ImageIcon icon =
							new ImageIcon(this.getClass().getResource("imagens/"+novoJogo.getCodigoPenalidade()+".png"));
					labelImagem.setIcon(icon);
					if (novoJogo.terminou()) buttonAdvinhar.setEnabled(false);
					else buttonAdvinhar.setEnabled(true);
					
				} catch (Exception e1) {
					labelResultado.setText(e1.getMessage());
				}
			}
		});
		
		
		buttonAdvinhar.setEnabled(false);
		buttonAdvinhar.setBounds(141, 78, 105, 27);
		frame.getContentPane().add(buttonAdvinhar);
		
		buttonIniciar = new JButton("Iniciar");
		buttonIniciar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				novoJogo.iniciar();
				
				
				labelPalavra.setText("Palavra= "+ novoJogo.getPalavra());
				labelAcertos.setText("Acertos: "  + novoJogo.getAcertos());
				labelPenalidade.setText("Penalidade: " + novoJogo.getNomePenalidade());
				labelResultado.setText(novoJogo.getResultado());
				labelDica.setText("Dica: " + novoJogo.getDicas());
				ImageIcon icon =
						new ImageIcon(this.getClass().getResource("imagens/"+novoJogo.getCodigoPenalidade()+".png"));
				labelImagem.setIcon(icon);
				
				buttonAdvinhar.setEnabled(true);
				
				
				labelAcertos.setVisible(true);
				labelAcertos.setForeground(Color.GREEN);
				labelPenalidade.setVisible(true);
				labelPenalidade.setForeground(Color.RED);
				labelResultado.setVisible(true);
					
			}
		});
		buttonIniciar.setBounds(43, 12, 105, 27);
		frame.getContentPane().add(buttonIniciar);
		
		labelPalavra = new JLabel("Palavra= ");
		labelPalavra.setBounds(23, 112, 415, 17);
		frame.getContentPane().add(labelPalavra);
		
		labelAcertos = new JLabel("Acertos: "  + novoJogo.getAcertos());
		labelAcertos.setBounds(166, 12, 60, 17);
		frame.getContentPane().add(labelAcertos);
		labelAcertos.setVisible(false);
		
		labelPenalidade = new JLabel("Penalidade: " + novoJogo.getNomePenalidade());
		labelPenalidade.setBounds(238, 12, 266, 17);
		frame.getContentPane().add(labelPenalidade);
		labelPenalidade.setVisible(false);
		
		
		labelResultado = new JLabel(novoJogo.getResultado());
		labelResultado.setBounds(23, 197, 196, 17);
		frame.getContentPane().add(labelResultado);
		labelResultado.setVisible(false);
		
		labelImagem = new JLabel("");
		java.net.URL imgURL6 = getClass().getResource("imagens/6.png");
		labelImagem.setIcon(new ImageIcon(imgURL6));
		labelImagem.setBounds(288, 106, 150, 150);
		frame.getContentPane().add(labelImagem);
		
		
		
	}
}
