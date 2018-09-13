package ChM.lab1;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/** ���� GaussianElimination **/
public class Gaussian
{
    public void solve(double[][] A, double[] B)
    {
        int N = B.length;
        for (int k = 0; k < N; k++) 
        {
            /** ������ ��������� ����� **/
            int max = k;
            for (int i = k + 1; i < N; i++) 
                if (Math.abs(A[i][k]) > Math.abs(A[max][k])) 
                    max = i;
 
            /** ������ ����� � ������� � **/    
            double[] temp = A[k]; 
            A[k] = A[max]; 
            A[max] = temp;
 
            /** ���� ��������� ������� � ������� �������� **/
            double t = B[k]; 
            B[k] = B[max]; 
            B[max] = t;
 
            /** ��������� �� A � B **/
            for (int i = k + 1; i < N; i++) 
            {
                double factor = A[i][k] / A[k][k];
                B[i] -= factor * B[k];
                for (int j = k; j < N; j++) 
                    A[i][j] -= factor * A[k][j];
            }
        }
 
        /** ����� ������� ����� ����� **/
//        printRowEchelonForm(A, B);
 
        /** �������� ����� **/
        double[] solution = new double[N];
        for (int i = N - 1; i >= 0; i--) 
        {
            double sum = 0.0;
            for (int j = i + 1; j < N; j++) 
                sum += A[i][j] * solution[j];
            solution[i] = (B[i] - sum) / A[i][i];
        }        
        /** �������� ������ **/
        printSolution(solution);
    }
    /** ������� ��� ������ � �����  ���� ����� **/
    public void printRowEchelonForm(double[][] A, double[] B)
    {
        int N = B.length;
        System.out.println("\nRow Echelon form : ");
        for (int i = 0; i < N; i++)
           {
               for (int j = 0; j < N; j++)
                   System.out.printf("%.3f ", A[i][j]);
               System.out.printf("| %.3f\n", B[i]);
           }
           System.out.println();
    }
    /** ��������� ������ **/
    public void printSolution(double[] sol)
    {
    	try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("src/ChM/lab1/result3.txt"));

            for (int i = 0; i < sol.length; i++) {
                     bw.write(sol[i] + ",");
                
                bw.newLine();
            }
            bw.flush();
            bw.close();
        } catch (IOException e) {}    
    }    
    /**������� ������� 
     * @throws FileNotFoundException **/
    public static void main (String[] args) throws FileNotFoundException 
    {
        /** ��������� ����� ����� **/
        Gaussian ge = new Gaussian();
 
        int N = 3;
 
        double[] B = new double[N];
        double[][] A = new double[N][N];
 
        Scanner input = new Scanner(new File("src/ChM/lab1/array.txt"));
    	for(int i = 0; i < N; ++i)
    	{
    	    for(int j = 0; j < N; ++j)
    	    {
    	        if(input.hasNextDouble())
    	        {
    	            A[i][j] = input.nextDouble();
    	        }
    	    }
    	}
    	input.close();
    	
    	Scanner input2 = new Scanner(new File("src/ChM/lab1/equal.txt"));
        for (int i = 0; i < N; i++)
            B[i] = input2.nextDouble();
        input2.close();
        
        	ge.solve(A,B);
        
        System.out.println(matrixDeterminant(A));
    }
    

	public static double matrixDeterminant (double[][] matrix) {
		double temp[][];
		double result = 0;

		if (matrix.length == 1) {
			result = matrix[0][0];
			return(result);
		}

		if (matrix.length == 2) {
			result = ((matrix[0][0] * matrix[1][1]) - (matrix[0][1] * matrix[1][0]));
			return (result);
		}

		for (int i = 0; i < matrix[0].length; i++) {
			temp = new double[matrix.length - 1][matrix[0].length - 1];

			for (int j = 1; j < matrix.length; j++) {
				for (int k = 0; k < matrix[0].length; k++) {
					if (k < i) {
						temp[j - 1][k] = matrix[j][k];
					} else if (k > i) {
						temp[j - 1][k - 1] = matrix[j][k];
					}
				}
			}

			result += matrix[0][i] * Math.pow (-1, (double) i) * matrixDeterminant (temp);
		}
		return (result);
	}
}
