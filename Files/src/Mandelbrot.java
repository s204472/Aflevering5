import java.util.*;
import java.util.List;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
public class Mandelbrot {
	public static final int MAX = 255;
	public static final int SIZE = 1000;
	public static final double SCALE = 0.00001;
	
	public static void main(String[] args) throws FileNotFoundException {
		double x0 = getInfo("Input real part of center number: ");
		double y0 = getInfo("Input immaginary part of center number: ");
		double s = getInfo("Input side length: ");
		
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
		
		Color[] color = new Color[MAX];
		Random rand = new Random();
		
		//This part sets color array
		if (getInput()) {
			File f = getFile();
			color = readFile(f);
		} else {
			for (int i = 0; i < MAX; i++) {
				color[i] = new Color(rand.nextInt(256), rand.nextInt(256), rand.nextInt(256));				
			}		
		}
		
		//This part draws the fractals
		for (int i = SIZE - 1; i >= 0; i--){
			for (int j = 0; j < SIZE; j++){
				int value = iterate(grid[j][i]);
				StdDraw.setPenColor(color[value-1]);
				StdDraw.filledCircle(grid[j][i].getRe(), grid[j][i].getIm(), SCALE);
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
	
	/*
	 * This method prompts the user for a double value
	 */
	public static double getInfo(String message) {
        Scanner input = new Scanner(System.in);
        try {
        	System.out.print(message);
            return input.nextDouble();
        } catch (InputMismatchException e) {
            return getInfo(message);
        }
    }
	
	/*
	 * This method reads a given file containing a color information
	 * */
	public static Color[] readFile(File f) throws FileNotFoundException {
		Scanner scanner = new Scanner(f);
		List<String> lines = new ArrayList<String>();
		Color[] color = new Color[MAX + 1];
		
		while (scanner.hasNextLine()) {
		    lines.add(scanner.nextLine());
		}
		
		int size = lines.size();
		if(size != MAX + 1) {
			System.out.println("File doesn't contain " + (MAX + 1) + " lines");
			return readFile(getFile());
		}
		

		try {
			for (int i = 0; i < size; i++) {
				Scanner line = new Scanner(lines.get(i));
				System.out.println(i);
				color[i] = new Color(line.nextInt(), line.nextInt(), line.nextInt());
			}
		} catch (Exception e) {
			 System.out.println("Error in entered file");
			 return readFile(getFile());
		}
		return color;
	}
	
	/* 
     * This method prompts the user for a filepath. 
     * A file-object is returned, if the file could be located.
     * */
	public static File getFile() {
        Scanner input = new Scanner(System.in);
        String fp;
        try {
            System.out.print("Enter filepath: ");
            fp = input.nextLine();
            File f = new File(fp);
            
            if(!f.isDirectory() && f.canRead()) {
            	return f;
            } else {
            	System.out.println("Unable to locate file, try again");
            	return getFile();
            }
        } catch (InputMismatchException e) {
            System.out.println("Unable to locate file, try again");
            return getFile();
        }
    }
	
	/* 
     * This method prompts the user for a Yes or No answer. 
     * The method returns either false or true depending on the input.
     * */
	public static boolean getInput() {
        Scanner input = new Scanner(System.in);
        String s;
        try {
            System.out.print("Do you want to use a color file? (Y or N): ");
            s = input.next();
            if (s.toUpperCase().equals("Y")) {
            	return true;
            } else if (s.toUpperCase().equals("N")) {
            	return false;
            } else {
            	System.out.println("Please enter Y or N");
                return getInput();
            }
        } catch (InputMismatchException e) {
            System.out.println("Please enter Y or N");
            return getInput();
        }
    }	
}
