import java.util.InputMismatchException;
import java.util.Scanner;

public class Mandelbrot {
	public static final int MAX = 50;
	public static final int SIZE = 512;
	public static final double SCALE = 0.00001;
	
	public static void main(String[] args) {
		double x0 = getInput("Input re center");
		double y0 = getInput("Input im center");
		double s = getInput("Input size");
		System.out.println(x0 + ", " + y0 + ", " + s);
		
		Complex[][] grid = new Complex[SIZE][SIZE];
		for (int i = SIZE - 1; i >= 0; i--){
			for (int j = 0; j < SIZE; j++){
				grid[j][i] = new Complex(x0 - (s / 2) + ((s * j) / (SIZE - 1)) , y0 - (s / 2) + ((s * i) / (SIZE - 1)));
				
			}
		}
		
		
		
		
		StdDraw.setCanvasSize(800, 800);
		StdDraw.setXscale(grid[0][0].getRe() - SCALE, grid[SIZE - 1][0].getRe() + SCALE);
		StdDraw.setYscale(grid[0][0].getIm() - SCALE, grid[0][SIZE - 1].getIm() + SCALE);
		StdDraw.show(0);
		for (int i = SIZE - 1; i >= 0; i--){
			for (int j = 0; j < SIZE; j++){
				if(iterate(grid[j][i]) == MAX) {
					StdDraw.filledCircle(grid[j][i].getRe(), grid[j][i].getIm(), SCALE);	
				}
			}
		}
		StdDraw.show(0);
		
	}
	public static int iterate(Complex z0) {
		Complex z = new Complex(z0);
		for (int i = 0; i < MAX; i++) {
			if (z.abs() > 2.0) {
				return i;
			}
			z = z.times(z).plus(z0);
		}
		return MAX;
	}
	
	
	public static double getInput(String message) {
        Scanner input = new Scanner(System.in);
        double num = 0;
        try {
        	System.out.print(message);
            return input.nextDouble();
        } catch (InputMismatchException e) {
            return getInput(message);
        }
    }
	
	
}
