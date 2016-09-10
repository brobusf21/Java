
import java.util.Scanner;


public class MatrixInverter {
	public static void main(String[] args) {

		int n; // Matrix size
		Scanner sc1 = new Scanner(System.in); // Obtaining the matrix size 
		System.out.println("Welcome. Please enter the size for an nxn matrix: ");
		n = sc1.nextInt();
		System.out.println("Matrix size will be: " + n + "x" + n);
		
		double[][] matrix = new double[(int) n][(int) n];
		System.out.println("Enter the elements of the first matrix: ");
		
		// Obtains the elements of the matrix 
		for (int a = 0; a < n; a++)
			for (int b = 0; b < n; b++)
				matrix[a][b] = sc1.nextDouble();
		
		// What if I could create another matrix ===> One that needs to be printed 
		double[][] invertedMatrix = new double[(int) n][(int) n];
		for (int c = 0; c < n; c++) {
			for (int d = 0; d<n; d++) {
				if (c == d) {
					invertedMatrix[c][d] = 1;
				} else {
					invertedMatrix[c][d] = 0;
				}
			}
		}
		
		// Elimination 
        double multiplier;
        for (int p = 0; p < n; p++) {
        	for (int q = 1; q < n; q++) {
        		if (matrix[q][p] != 0) {
        			multiplier = (matrix[q][p] / matrix[p][p]);
        			System.out.println("lm = " + multiplier);
        			for (int r = p; r < n; r++) {
        				matrix[q][r] -= multiplier * matrix[p][r];
        				invertedMatrix[q][r] -= multiplier * invertedMatrix[p][r];
        			}
        		} else {
        			q -=1;
        			p +=1;
        		}
        		if ((p == n-1) && (q == n-2)) {
        			// Prints the matrix 
        	        for (int a = 0 ; a < n ; a++ )
        	        {
        	           for (int b = 0 ; b < n ; b++ ) {
        	              System.out.print(invertedMatrix[a][b]+"\t");
        	           }

        	           System.out.print("\n");
        	        }
        			// BACK SUBSTITUTION
        	        for (int j = n-1; j > -1; j--) {
        	        	for (int k = n-1; k > 0; k--) {
        	        		multiplier = (matrix[k-1][k] / matrix[k][k]);
        	        		System.out.println("back sub lm = " + multiplier);
        	        		for (int s = j; s > -1; s--) {
        	        			matrix[k-1][s] -= multiplier * matrix[k][s];
        	        			invertedMatrix[k-1][s] -= multiplier * invertedMatrix[k][s];
        	        			System.out.println("This is J: " + j + "\n");
        	        			System.out.println("This is K: " + k + "\n");
        	        			System.out.println("This is s: " + s); 
        	        			//invertedMatrix[k-1][s] -= multiplier * invertedMatrix[k][s];
        	        		}
        	        		System.out.println("DONE");
        	        		q = n;
        	        		//break outerloop;
        	        	}
        	        }
        		}
        	
        	}
        }
        // Back substituion
        for (int j = n-1; j > -1; j--) {
        	for (int k = n-1; k > 0; k--) {
        		multiplier = (matrix[k-1][k] / matrix[k][k]);
        		System.out.println("back sub lm = " + multiplier + " " + k);
        		for (int s = j; s > -1; s--) {
        			System.out.println("here");
        			matrix[k-1][s] -= multiplier * matrix[k][s];
        			invertedMatrix[k-1][s] -= multiplier * invertedMatrix[k][s];
        		}
        	}
        }
        
        // Divide 
        for (int e = 0; e < n; e++) {
        	for (int f = 0; f < n; f++) {
        		if (e == f) {
        			matrix[e][f] /= matrix[e][f];
        			invertedMatrix[e][f] /= invertedMatrix[e][f];
        		}
        	}
        }
     
		// Prints the matrix 
        for (int a = 0 ; a < n ; a++ )
        {
           for (int b = 0 ; b < n ; b++ ) {
              System.out.print(invertedMatrix[a][b]+"\t");
           }

           System.out.print("\n");
        }
	}
}
