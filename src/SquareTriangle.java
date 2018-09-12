import java.util.Scanner;

// Find square of triangle by 3 points 
// Знайти площу трикутника за координатами трьох точок в просторі
public class SquareTriangle {

	public static double squareOfTriangle() {
		Scanner keyboard = new Scanner(System.in);
		System.out.println("Enter coordinates of point A");
		double Ax = keyboard.nextDouble();
		double Ay = keyboard.nextDouble();
		System.out.println("Enter coordinates of point B");
		double Bx = keyboard.nextDouble();
		double By = keyboard.nextDouble();
		System.out.println("Enter coordinates of point C");
		double Cx = keyboard.nextDouble();
		double Cy = keyboard.nextDouble();
		
// If you need to divide your square more times write times (Z), otherwise return area
		System.out.println("Enter times of dividing");
		double Z = keyboard.nextDouble();
		
		keyboard.close();
		
		double area = ((Ax * (By - Cy)) + (Bx * (Cy - Ay)) + (Cx * (Ay - By))) / 2;
		
		return area / (2 * Z);
	}

	public static void main(String[] args) {
		System.out.println(squareOfTriangle());

	}

}
