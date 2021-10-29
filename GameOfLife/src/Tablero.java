import java.awt.EventQueue;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JSpinner;
import javax.swing.Timer;
import javax.swing.JLabel;

public class Tablero implements ActionListener{

	private JFrame frame;
	private final int rows =10; 
	private int cols =10;
	private int contador=0;
	private int[][] game;
	boolean cellsMap[][];
	JButton cells[][];
	Timer timer = null;
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
		
		Game juego = new Game();
		
		//inicializamos antes para evitar crear multiples instancias del timer
		inicializar();

		
		frame = new JFrame();
		frame.setBounds(100, 100, 896, 635);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panelJuego = new JPanel();
		panelJuego.setBounds(0, 0, 870, 527);
		frame.getContentPane().add(panelJuego);
		
		
		//Paneles de Juego y Menu
		JPanel panelBoton = new JPanel();
		panelBoton.setBounds(0, 538, 870, 58);
		panelBoton.setBackground(Color.DARK_GRAY);
		frame.getContentPane().add(panelBoton);
		panelBoton.setLayout(null);
		
		//Botones y cosas
		JButton btnPlay = new JButton("Play");
		btnPlay.setBackground(Color.YELLOW);
		btnPlay.setBounds(30, 15, 64, 25);
		panelBoton.add(btnPlay);
		btnPlay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				juego(panelJuego);
				timer.start();
			}
		});
		
		JButton btnClear = new JButton("Clear");
		btnClear.setBackground(Color.YELLOW);
		btnClear.setBounds(130, 15, 71, 25);
		panelBoton.add(btnClear);
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				juego(panelJuego);
				timer.stop();
				ClearCellsMap(cellsMap);
				timer.start();
			}
		});
		
		
		JButton btnRandom = new JButton("Random");
		btnRandom.setBackground(Color.YELLOW);
		btnRandom.setBounds(230, 15, 91, 25);
		panelBoton.add(btnRandom);
		btnRandom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				juego(panelJuego);
				//iniciamos la instacia del timer para que pueda rellenas las celdas de la matriz
				timer.start();
				cellsMap = RandomAutofill(cellsMap);
				RandomAutofill(cellsMap);
			}
		});
		
		JButton btnStop = new JButton("Stop");
		btnStop.setBackground(Color.YELLOW);
		btnStop.setBounds(353, 15, 91, 25);
		panelBoton.add(btnStop);
		btnStop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				juego(panelJuego);
				timer.stop();
			}
		});
		
		//spinners
		
		JSpinner spinner = new JSpinner();
		spinner.setBounds(578, 15, 35, 25);
		panelBoton.add(spinner);
		
		
		JSpinner spinner_1 = new JSpinner();
		spinner_1.setBounds(690, 15, 35, 25);
		panelBoton.add(spinner_1);
		
		
		JLabel lblNewLabel = new JLabel("Width :");
		lblNewLabel.setForeground(Color.YELLOW);
		lblNewLabel.setBounds(496, 20, 70, 15);
		panelBoton.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Height :");
		lblNewLabel_1.setForeground(Color.YELLOW);
		lblNewLabel_1.setBounds(633, 20, 70, 15);
		panelBoton.add(lblNewLabel_1);
		
		
		//contador de celulas vivas
		JLabel lblCounter = new JLabel("Counter :");
		lblCounter.setForeground(Color.YELLOW);
		lblCounter.setBounds(769, 20, 91, 15);
		panelBoton.add(lblCounter);
		
		//juego.consolePrintBoard(rows,cols,game,contador);
		

		//Print en Menu
		lblCounter.setText(String.valueOf("Counter : " + contador));
		
		
	}
	
	public void juego(JPanel panelJuego) {
		panelJuego.removeAll();
		panelJuego.setLayout(new GridLayout(rows, cols));
		
		//juego
		cellsMap = new boolean [rows][cols];
		
		//pintar de manera manual para el ejemplo de 3x3
		//cellsMap[0][1] = true;
		//cellsMap[1][1] = true;
		//cellsMap[2][1] = true;
		
		//celulas
		cells = new JButton[rows][cols];
		
		
		for(int i=0;i<rows;i++) {
			for(int j=0;j<cols;j++) {
				//tablero de botones temporal
				JButton temp = new JButton();
				if(cellsMap[i][j]) {
					temp.setBackground(Color.RED);
				}else {
					temp.setBackground(Color.BLUE);
				}
				panelJuego.add(temp);
				cells[i][j] = temp;
			}
		}
		panelJuego.setVisible(true);
		frame.setVisible(true);
	}
	
	public void inicializar(){
		
		//tiempo de movimientos por segundo (0,5 seg)
		timer = new Timer(500, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				//matriz Temporal
				boolean[][] temp = new boolean[rows][cols];
				
				for(int i=0;i<rows;i++) {
					for(int j=0;j<cols;j++) {
						int count = countNeignours(i,j);
						
						//reglas del juego
						if(cellsMap[i][j]) {
							//celula viva, puede continuar con vida? (0-1)
							if(count<2) {
								//muere, porque no hay sufientes celulas vivas alrededor
								temp[i][j]=false;
							}
							//celula viva, puede continuar con vida? (2-3)
							if(count == 3 || count == 2) {
								//continua con vida, hay sufientes celulas vivas alrededor
								temp[i][j]=true;
							}
							//celula viva, puede continuar con vida? (+3)
							if(count>3) {
								//muere por sobrepoblacion
								temp[i][j]=false;
							}
						}else {
							//celula muerta, puede nacer? (3)
							if(count == 3) {
								//la celula nace, porque hay sufientes celulas vivas alrededor
								temp[i][j]=true;
							}
						}
					}
				}
				//cambiar el estado del tablero principal por el tablero temporal
				cellsMap = temp;
				
				//pintar las celulas de la matriz principal 
				for(int i=0;i<rows;i++) {
					for(int j=0;j<cols;j++) {
						if(cellsMap[i][j]) {
							cells[i][j].setBackground(Color.RED);
						}else {
							cells[i][j].setBackground(Color.BLUE);
						}
					}
				}
			}
			
		});
		timer.stop();
	}
	//metodo que cuenta el numero de celulas vecinas vivas
	public int countNeignours(int x, int y) {
		int count = 0;
		
		for(int i=x-1;i<=x+1;i++) {
			for(int j=y-1;j<=y+1;j++) {
				try {
					if(cellsMap[i][j]) {
						count++;
					}
				}catch(Exception e) {}
			}
		}
		if(cellsMap[x][y]) {
			count--;
		}
		return count;
	}

	@Override
	public void actionPerformed(ActionEvent e) {}

	//metodo que rellena el tablero de manera aleatoria
	public boolean[][] RandomAutofill(boolean[][]cellsMap) {
		Random rnd = new Random();
		for(int i=0;i<rows;i++) {
			for(int j=0;j<cols;j++) {
				cellsMap[i][j] = rnd.nextInt(100)<30;
			}
		}
		return cellsMap;
	}
	
	public boolean[][] ClearCellsMap(boolean[][]cellsMap){
		for(int i=0;i<rows;i++) {
			for(int j=0;j<cols;j++) {
				cellsMap[i][j] = false;
			}
		}
		return cellsMap;
	}
}