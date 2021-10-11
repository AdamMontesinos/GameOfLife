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

public class Tablero {

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
		
		int rows = 100;
		int cols = 100;
		int contador=0;
		
		frame = new JFrame();
		frame.setBounds(100, 100, 900, 900);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panelBoton = new JPanel();
		panelBoton.setBackground(Color.DARK_GRAY);
		panelBoton.setBounds(0, 811, 900, 52);
		frame.getContentPane().add(panelBoton);
		
		JPanel panelJuego = new JPanel();
		panelJuego.setBounds(0, 0, 900, 900);
		frame.getContentPane().add(panelJuego);
		panelJuego.setLayout(new GridLayout(rows, cols));
		panelBoton.setLayout(null);
		
		JButton btnPlay = new JButton("Play");
		btnPlay.setBackground(Color.YELLOW);
		btnPlay.setBounds(136, 15, 64, 25);
		panelBoton.add(btnPlay);
		
		JButton btnClear = new JButton("Clear");
		btnClear.setBackground(Color.YELLOW);
		btnClear.setBounds(414, 15, 71, 25);
		panelBoton.add(btnClear);
		
		JButton btnRandom = new JButton("Random");
		btnRandom.setBackground(Color.YELLOW);
		btnRandom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnRandom.setBounds(690, 15, 91, 25);
		panelBoton.add(btnRandom);
		
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
}
