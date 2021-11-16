
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.JSpinner;
import javax.swing.Timer;
import javax.swing.JLabel;

public class Tablero extends JFrame implements ActionListener{

	private JFrame frame;
	private JLabel lblCounter;
	private int rows; 
	private int cols;
	private int contador;
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
		
		//inicializamos antes para evitar crear multiples instancias del timer
		inicializar();
		

		
		frame = new JFrame();
		frame.setBounds(100, 100, 805, 634);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panelJuego = new JPanel();
		panelJuego.setBounds(0, 0, 789, 502);
		frame.getContentPane().add(panelJuego);
		
		
		
		//Paneles de Juego y Menu
		JPanel panelBoton = new JPanel();
		panelBoton.setBounds(0, 502, 789, 93);
		panelBoton.setBackground(Color.DARK_GRAY);
		frame.getContentPane().add(panelBoton);
		panelBoton.setLayout(null);
		
		
		//spinners para escoger el numero de celulas
		JSpinner spinnerWidth = new JSpinner();
		spinnerWidth.setBounds(50, 15, 35, 25);
		panelBoton.add(spinnerWidth);
		//valor por defecto para el tama�o
		spinnerWidth.setValue(3);
		
		JSpinner spinnerHeight = new JSpinner();
		spinnerHeight.setBounds(138, 15, 35, 25);
		panelBoton.add(spinnerHeight);
		//valor por defecto para el tamaño
		spinnerHeight.setValue(3);
		
		//Label de los spinners
		JLabel lblWidth = new JLabel("Width :");
		lblWidth.setForeground(Color.YELLOW);
		lblWidth.setBounds(10, 20, 58, 15);
		panelBoton.add(lblWidth);
		
		JLabel lblHeight = new JLabel("Height :");
		lblHeight.setForeground(Color.YELLOW);
		lblHeight.setBounds(95, 20, 70, 15);
		panelBoton.add(lblHeight);
		
		
		
		
		//Botones y cosas
		
		//btnCreateUniverse creara el tablero, no lo iniciara
		JButton btnCreateUniverse = new JButton("Create Universe");
		btnCreateUniverse.setBackground(Color.YELLOW);
		btnCreateUniverse.setBounds(10, 51, 163, 25);
		panelBoton.add(btnCreateUniverse);
		btnCreateUniverse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				panelJuego.removeAll();
				panelJuego.updateUI();
				rows = (Integer) spinnerWidth.getValue();
				cols = (Integer) spinnerHeight.getValue();
				juego(panelJuego, rows, cols);
			}
		});
		
		
		JButton btnPlay = new JButton("Play");
		btnPlay.setBackground(Color.YELLOW);
		btnPlay.setBounds(220, 37, 64, 25);
		panelBoton.add(btnPlay);
		btnPlay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				timer.start();
			}
		});
		
		JButton btnClear = new JButton("Clear");
		btnClear.setBackground(Color.YELLOW);
		btnClear.setBounds(314, 37, 71, 25);
		panelBoton.add(btnClear);
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				timer.stop();
				ClearCellsMap(cellsMap);
				timer.start();
			}
		});
		
		
		JButton btnRandom = new JButton("Random");
		btnRandom.setBackground(Color.YELLOW);
		btnRandom.setBounds(411, 37, 91, 25);
		panelBoton.add(btnRandom);
		btnRandom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//iniciamos la instacia del timer para que pueda rellenas las celdas de la matriz
				timer.start();
				cellsMap = RandomAutofill(cellsMap);
				RandomAutofill(cellsMap);
			}
		});
		
		JButton btnStop = new JButton("Stop");
		btnStop.setBackground(Color.YELLOW);
		btnStop.setBounds(526, 37, 79, 25);
		panelBoton.add(btnStop);
		btnStop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				timer.stop();
			}
		});
		
		//Print en Menu
		//contador de celulas vivas
		lblCounter = new JLabel("Counter :");
		lblCounter.setForeground(Color.YELLOW);
		lblCounter.setBounds(681, 42, 91, 15);
		panelBoton.add(lblCounter);
		lblCounter.setText("Counter :"+String.valueOf(contador));
		
		//juego.consolePrintBoard(rows,cols,game,contador);		
		
	}
	
	public void juego(JPanel panelJuego, int rows, int cols) {
		//panelJuego.removeAll();
		panelJuego.setLayout(new GridLayout(rows, cols));
		
		//juego
		cellsMap = new boolean [rows][cols];
		//cellsMap = ClearCellsMap(cellsMap);
		
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
				temp.setName(""+i+j);
				
				if(cellsMap[i][j]) {
					temp.setBackground(Color.RED);
					
				}else {
					temp.setBackground(Color.BLUE);
				}
				panelJuego.add(temp);
				cells[i][j] = temp;
				cells[i][j].addActionListener(this);
			}
		}
		
		panelJuego.setVisible(true);
		frame.setVisible(true);
	}
	
	public void inicializar( ){
		
		//tiempo de movimientos por segundo (0,5 seg)
		timer = new Timer(300, new ActionListener() {
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
				contador=0;
				//pintar las celulas de la matriz principal 
				for(int i=0;i<rows;i++) {
					for(int j=0;j<cols;j++) {
						if(cellsMap[i][j]) {
							cells[i][j].setBackground(Color.RED);
							contador++;
							
						}else {
							cells[i][j].setBackground(Color.BLUE);
							
						}
					}
				}				
				lblCounter.setText("Counter :"+String.valueOf(contador));
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
	
	//PINTAR LAS CELDAS CON EL RATÓN
	@Override
	public void actionPerformed (ActionEvent e) {
		// Invoked when the mouse button has been clicked (pressed and released) on a component
		
		JButton boton = (JButton)e.getSource();
		String xy= boton.getName();
		
		int i=Integer.parseInt(xy.charAt(0)+"");			
		int j=Integer.parseInt(xy.charAt(1)+"");			
			
		if(cellsMap[i][j]) {
			cellsMap[i][j]=false;
			boton.setBackground(Color.blue);
		}else{
			cellsMap[i][j]=true;
			boton.setBackground(Color.red);
		}
	}
}
