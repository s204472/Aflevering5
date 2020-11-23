/*
 * This class creates a complex number, with methods retrieving the number and doing mathematical operations. 
 */
public class Complex {
	private double re;
	private double im;
	
	
	public Complex() {
		re = 0;
		im = 0;
	}
	
	public Complex(double re, double im) {
		this.re = re;
		this.im = im;	
	}
	
	public Complex(Complex z) {
		re = z.getRe();
		im = z.getIm();
	}
	
	public double getRe() {
		return re;
	}
	
	public double getIm() {
		return im;
	}
	
	public double abs() {
		return Math.sqrt(re*re + im*im);
	}
	
	public Complex plus(Complex other) {
		return new Complex(re + other.getRe(), im + other.getIm());
	}
	
	public Complex times(Complex other) {
		return new Complex(re * other.getRe() - im * other.getIm(), im * other.getRe() + re * other.getIm());
	}
	
	public String toString() {
		String s = "";
		if(im == 0 && re == 0) {
			return "0";
		}
		s += re != 0 ? "" + re : "";
		if (im != 0) {
			s += im > 0 ? "+" : ""; 
			s += im + "i";
		} 
		return s;
	}
}
