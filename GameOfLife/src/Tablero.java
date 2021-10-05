import java.awt.EventQueue;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;

import java.awt.GridLayout;
import java.awt.Color;

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
		
		int rows = 3;
		int cols = 3;
		
		frame = new JFrame();
		frame.setBounds(100, 100, 900, 900);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new GridLayout(rows,cols));
		
		//Creem els taulell imaginari
		int[][] game = new int[rows][cols];
		JPanel [][] tablero = new JPanel[rows][cols];
		for(int i=0; i<rows; i++) {
			for(int j=0; j<cols; j++) {
				game[i][j]= 0;
				
				//Inicialitzamos el tablero visual sin color
				tablero[i][j] = new JPanel();
				Border borde;
				borde = BorderFactory.createLineBorder(Color.black);
				tablero[i][j].setBorder(borde);
				frame.add(tablero[i][j]);
			}
		}
		
		//Pintamos los que queremos y los que no pues nada
		game[0][1] = 1;
		game[1][1] = 1;
		game[2][1] = 1;
		
		//Para imprimirlo por terminal
		String out = "";
		for(int i=0; i<rows; i++) {
			for(int j=0; j<cols; j++) {
				out = out + game[i][j] + " ";
			}
			out = out + "\n";
		}
		System.out.println(out);
		
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
