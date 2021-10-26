public class TestPatterns {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String pattern = "bob$2bo$3o!";
		
		/*
		 * 010
		 * 110
		 * 111
		 */
		
		int[][] game = new int[3][3];
		
		String[] parts = pattern.split("\\$");
		
		int num = 0;
		
		System.out.println(parts[0]);
		for(int x=0; x<3;x++) {
			for(int i=0; i<parts[x].length();i++) {
				int character = (int)parts[x].charAt(i);
				if(character >= 48 && character <= 57) {
					//Cambias el char a numero real
					num = Character.getNumericValue(character);
					
				}else if(character == 98) {
					//Si el numero no es 0, imprimes el numero de veces que sea el numero un 0 (2b = 00)
					if(num!=0) {
						for(int r=0; r<num; r++) {
							System.out.println("0");
						}
						num = 0;
					}else {
						System.out.println("0");
					}
					
				}else if(character == 111) {
					//Si el numero no es 0, imprimes el numero de veces que sea el numero un 1 (2o = 11)
					if(num!=0) {
						for(int r=0; r<num; r++) {
							System.out.println("1");
						}
						num = 0;
					}else {
						System.out.println("1");
					}
				}
	
			}
		
		}
		
	}

}
