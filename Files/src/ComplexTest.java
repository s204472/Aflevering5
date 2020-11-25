
public class ComplexTest {

	public static void main(String[] args) {
		// Initializing two complex numers.
		Complex z1 = new Complex(1,2);
		Complex z2 = new Complex(4,5);
		
		System.out.println(z1); // Expected output 1.0+2.0i
		System.out.println(z2); // Expected output 4.0+5.0i
		
		// Calculates (1.0+2.0i) + (4.0+5.0i) - Expected output is 5.0+7.0i
		System.out.println(z1.plus(z2)); 
		
		// Calculates (1.0+2.0i) x (4.0+5.0i) - Expected output is -6.0+13.0i
		System.out.println(z1.times(z2)); 
		
		// Copies a complex number
		Complex z3 = new Complex(3,4);
		
		// Calculates absolute value of Complex number - Expected output is 5.0
		System.out.println(z3.abs()); 
	}

}
