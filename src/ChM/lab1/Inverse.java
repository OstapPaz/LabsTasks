package ChM.lab1;
 import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
 

// Search of inverse matrix
// Пошук оберненої матриці
public class Inverse 
{
    public static void main(String argv[]) throws FileNotFoundException 
    {
    	// Задати кількість рядків та стовбців матриці
    	int rows = 3;
    	int columns = 3;
    	double[][] a = new double[rows][columns];
    	

    	// Зчитування матриці з файлу
    	Scanner input = new Scanner(new File("src/ChM/lab1/array.txt"));
    	for(int i = 0; i < rows; ++i)
    	{
    	    for(int j = 0; j < columns; ++j)
    	    {
    	        if(input.hasNextDouble())
    	        {
    	            a[i][j] = input.nextDouble();
    	        }
    	    }
    	}
    	input.close();
    	
        double d[][] = invert(a);
        
        writeToFile(d);

    }	
 
    public static double[][] invert(double a[][]) 
    {
        int n = a.length;
        double x[][] = new double[n][n];
        double b[][] = new double[n][n];
        int index[] = new int[n];
        for (int i=0; i<n; ++i) 
            b[i][i] = 1;
 
 // ������������ ������� 
        gaussian(a, index);
 
 // ���������� ������� b[i][j] � ����������� ��������
        for (int i=0; i<n-1; ++i)
            for (int j=i+1; j<n; ++j)
                for (int k=0; k<n; ++k)
                    b[index[j]][k]
                    	    -= a[index[j]][i]*b[index[i]][k];
 
 // ��������� �������� ����
        for (int i=0; i<n; ++i) 
        {
            x[n-1][i] = b[index[n-1]][i]/a[index[n-1]][n-1];
            for (int j=n-2; j>=0; --j) 
            {
                x[j][i] = b[index[j]][i];
                for (int k=j+1; k<n; ++k) 
                {
                    x[j][i] -= a[index[j]][k]*x[k][i];
                }
                x[j][i] /= a[index[j]][j];
            }
        }
        return x;
    }
 
// ����� ���������� �������� ������� ������
// ��� index[] ������ �������� ������� ������������.
 
    public static void gaussian(double a[][], int index[]) 
    {
        int n = index.length;
        double c[] = new double[n];
 
 // ��������� �������
        for (int i=0; i<n; ++i) 
            index[i] = i;
 
 // ����� ����������� ��� ������������, ���� �� ����� �����
        for (int i=0; i<n; ++i) 
        {
            double c1 = 0;
            for (int j=0; j<n; ++j) 
            {
                double c0 = Math.abs(a[i][j]);
                if (c0 > c1) c1 = c0;
            }
            c[i] = c1;
        }
 
 // ���� ����������� �������� ��� ������� �������
        int k = 0;
        for (int j=0; j<n-1; ++j) 
        {
            double pi1 = 0;
            for (int i=j; i<n; ++i) 
            {
                double pi0 = Math.abs(a[index[i]][j]);
                pi0 /= c[index[i]];
                if (pi0 > pi1) 
                {
                    pi1 = pi0;
                    k = i;
                }
            }
 
   // ���� ����� �������� �� ������� ��������
            int itmp = index[j];
            index[j] = index[k];
            index[k] = itmp;
            for (int i=j+1; i<n; ++i) 	
            {
                double pj = a[index[i]][j]/a[index[j]][j];

 
 // ����� �������� ������� ����� �������
                a[index[i]][j] = pj;
 
 // ���� ����� ��������
                for (int l=j+1; l<n; ++l)
                    a[index[i]][l] -= pj*a[index[j]][l];
            }
        }
    }
    
    public static void writeToFile(double[][] d) {
    	try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("src/ChM/lab1/result2.txt"));

            for (int i = 0; i < d.length; i++) {
                for (int j = 0; j < d[i].length; j++) {
                        bw.write(d[i][j] + ",");
                }
                bw.newLine();
            }
            bw.flush();
            bw.close();
        } catch (IOException e) {}
    }
    
}