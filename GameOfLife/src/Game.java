
public class Game {
	public Game() {
		
	}

	public void consolePrintBoard(int rows, int cols, int game [][], int contador,int width , int heigth) {
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
		System.out.println("Contador : " + contador);
		System.out.println("Width : " + width);
		System.out.println("Heigth : " + heigth);
	}
}
