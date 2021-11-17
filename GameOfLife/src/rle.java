import java.io.BufferedReader;
import java.io.IOException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;

import java.io.File;
import java.util.Scanner;

public class rle {
	
	String path;
	
	public rle(String path) {
		this.path = path;
	}

	
	public boolean [][] getPattern() throws FileNotFoundException, IOException{
		File fichero = new File(path);
		
		try (BufferedReader entrada = new BufferedReader(new FileReader(fichero))) {
	        String line;
	        boolean[][] game;
	        
	        while ((line = entrada.readLine()) != null) {

			char firstLetter = line.charAt(0);
			if(firstLetter != '#'){
				if(firstLetter == 'x'){
					String[] parts = line.split(",");
						int rows = Integer.parseInt(parts[0].split("= ")[1]);
						int cols = Integer.parseInt(parts[0].split("= ")[1]);
						
						System.out.println("x= " + rows + " y= " + cols);
						
						line = entrada.readLine();
						
						game = new boolean[rows][cols];
			
						String[] part = line.split("\\$");
	
						int num = 0;
						
						
						for(int x=0; x< part.length;x++) {
							for(int i=0; i<part[x].length();i++) {
								int character = (int)part[x].charAt(i);
								if(character >= 48 && character <= 57) {
									//Cambias el char a numero real
									num = Character.getNumericValue(character);
									
								}else if(character == 98) {
									//Si el numero no es 0, imprimes el numero de veces que sea el numero un 0 (2b = 00)
									if(num!=0) {
										for(int r=0; r<num; r++) {
											game[x][r] = false;
										}
										num = 0;
									}else {
										game[x][i] = false;
									}
									
								}else if(character == 111) {
									//Si el numero no es 0, imprimes el numero de veces que sea el numero un 1 (2o = 11)
									if(num!=0) {
										for(int r=0; r<num; r++) {											
											game[x][r] = true;
										}
										num = 0;
									}else {
										game[x][i] = true;
									}
								}
	
							}
						
						}
						
					

						
						
						String out = "";
						
						for(int i=0; i <rows; i++) {
							for(int j = 0; j<cols; j++) {
								out = out + game[i][j];
							}
							out = out + "\n";
						}
						
						System.out.println(out);
	    
						return game;
						
						
					}

				}
			}
	    }
		return null;
		
	}
	

		
		
}
	

