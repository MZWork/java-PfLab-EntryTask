package task1;

import java.io.IOException;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws IOException {	
		System.out.println("To convert a number in a non-decimal numeral system, type in 1: ");
		String baseSrcMain = null;
		String result = null;
		int nbDec = 0;
		Scanner sc = new Scanner(System.in);
		String scanned = sc.next();
		if (scanned.equals("1")) {
			System.out.println("Type in a number that represents a base of your numeral system: ");	
			baseSrcMain = sc.next();
		}
		else {
			System.out.println("Decimal numeral system chosen as a base.");
		}
		System.out.println("Type in a number that you want to convert: ");
		String nbMain = sc.next();
		System.out.println("Type in a number that represents a base of a numeral system, to which you want to convert: ");
		String baseMainStr = sc.next();
		if (scanned.equals("1")) {			
			result = itoBase (nbMain, baseSrcMain, baseMainStr);		
		}
		else {
			nbDec = StrConvToInt (nbMain, "that you want to convert: ");
			result = itoBase (nbDec, baseMainStr);
		}
		System.out.print("Result - " + result + " in a chosen numeral system");
		System.exit(0);	
	}
	
	public static String itoBase (int nb, String base) throws IOException {
		int baseDec = 2;
		baseDec = StrConvToInt (base, "that represents a base of a numeral system, to which you want to convert: ");
		return Integer.toString(nb, baseDec);
	}
	
	public static String itoBase (String nb, String baseSrc, String baseDst) {
		int baseSrcDec = 2;
		int baseDstDec = 2;
		baseSrcDec = StrConvToInt (baseSrc, "that represents a base of your numeral system: ");
		baseDstDec = StrConvToInt (baseDst, "that represents a base of a numeral system, to which you want to convert: ");
		return Integer.toString(Integer.parseInt(nb, baseSrcDec), baseDstDec);
	}
	
	public static void Usage (String strAdd) {
		System.out.println("Error - wrong argument\nUsage: type in any decimal number - 0, 1, 2, etc. " + strAdd);
	}
	
	public static int StrConvToInt (String Str, String UsageStr) {
		int ConvInt = 0;
		boolean flagUsageLoop;
		do {
			try {		
			ConvInt = Integer.parseInt(Str);
			flagUsageLoop = false;
		} catch(NumberFormatException e) {
			Main.Usage(UsageStr);
			Scanner sc = new Scanner(System.in);
			Str = sc.next();
			flagUsageLoop = true;
		} } while (flagUsageLoop);		
		return ConvInt;
	}

}
