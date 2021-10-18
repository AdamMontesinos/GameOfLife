import java.awt.EventQueue;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;

import org.w3c.dom.events.MouseEvent;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import javax.swing.JSpinner;
import javax.swing.JLabel;

public class Tablero {
	private final int rows = 3;
	private final int cols = 3;
	private int contador = 0;

	private JFrame frame;

	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Tablero window = new Tablero();
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
	public Tablero() {
		initialize();
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		/*int rows = 3;
		int cols = 3;
		int contador=0;*/
		
		frame = new JFrame();
		frame.setBounds(100, 100, 900, 900);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		
		//Paneles de Juego y Menu
		JPanel panelBoton = new JPanel();
		panelBoton.setBackground(Color.DARK_GRAY);
		panelBoton.setBounds(0, 811, 900, 52);
		frame.getContentPane().add(panelBoton);
		
		
		JPanel panelJuego = new JPanel();
		panelJuego.setBounds(0, 0, 900, 738);
		frame.getContentPane().add(panelJuego);
		panelJuego.setLayout(new GridLayout(rows, cols));
		
		//Botones y cosas
		JButton btnPlay = new JButton("Play");
		btnPlay.setBackground(Color.YELLOW);
		btnPlay.setBounds(30, 15, 64, 25);
		panelBoton.add(btnPlay);
		btnPlay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				inicializar(panelJuego, panelBoton);
				contador=0;
				
			}
		});
		
		JButton btnClear = new JButton("Clear");
		btnClear.setBackground(Color.YELLOW);
		btnClear.setBounds(130, 15, 71, 25);
		panelBoton.add(btnClear);
		
		JButton btnRandom = new JButton("Random");
		btnRandom.setBackground(Color.YELLOW);
		btnRandom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		
		btnRandom.setBounds(230, 15, 91, 25);
		panelBoton.add(btnRandom);
		
		JLabel lblNewLabel = new JLabel("Width :");
		lblNewLabel.setForeground(Color.YELLOW);
		lblNewLabel.setBounds(350, 20, 70, 15);
		panelBoton.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Height :");
		lblNewLabel_1.setForeground(Color.YELLOW);
		lblNewLabel_1.setBounds(450, 20, 70, 15);
		panelBoton.add(lblNewLabel_1);
		
		JSpinner spinner = new JSpinner();
		spinner.setBounds(405, 15, 35, 25);
		panelBoton.add(spinner);
		
		JSpinner spinner_1 = new JSpinner();
		spinner_1.setBounds(509, 15, 35, 25);
		panelBoton.add(spinner_1);
		
		JLabel lblCounter = new JLabel("Counter :");
		lblCounter.setForeground(Color.YELLOW);
		lblCounter.setBounds(633, 20, 1000, 15);
		panelBoton.add(lblCounter);
		
		
		
		
		
		//Print en Menu
		lblCounter.setText(String.valueOf("Counter : " + contador));

	}
	private void inicializar(JPanel panelJuego, JPanel panelBoton) {
		//Creem els taulell imaginari
				int[][] game = new int[rows][cols];
				JPanel [][] tablero = new JPanel[rows][cols];
				for(int i=0; i<rows; i++) {
					for(int j=0; j<cols; j++) {
					
						//Para Random
						int red = 1;
						int blue = 0;
						
						int random_int = (int)Math.floor(Math.random()*(red-blue+1)+blue);
						game[i][j]= random_int;
						
						//Inicialitzamos el tablero visual sin color
						tablero[i][j] = new JPanel();
						Border borde;
						borde = BorderFactory.createLineBorder(Color.black);
						tablero[i][j].setBorder(borde);
					
						
						panelJuego.add(tablero[i][j]);						
						
						
					}
					
				}
				this.frame.setVisible(true);


				
				imprimir_logico(game);
				imprimir_visual(game, tablero);
	}
	
	private void imprimir_visual(int[][] game, JPanel[][] tablero) {
		//Los 1 pintamos rojo, los demas azul
				for(int i=0;i<rows;i++){
					for(int j=0;j<cols;j++) {
						
						if(game[i][j]==1) {
							tablero[i][j].setBackground(Color.red); 
						}else {
							tablero[i][j].setBackground(Color.blue); 
						}
					}	
				}
	}
	
	private void imprimir_logico(int[][] game) {
		//Para imprimirlo por terminal
		String out = "";
		for(int i=0; i<rows; i++) {
			for(int j=0; j<cols; j++) {
				out = out + game[i][j] + " ";
				if(game[i][j]==1) {
					contador++;
				}
			}
			out = out + "\n";
		}
		System.out.println(out);
		System.out.println(contador);
	}
}