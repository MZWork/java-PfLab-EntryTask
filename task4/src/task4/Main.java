package task4;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("¬ведите первую строку: ");
		String scanned1 = sc.next();
		System.out.println("¬ведите вторую строку: ");
		String scanned2 = sc.next();
		sc.close();
		scanned2 = scanned2.replace(".", "\\.");
		scanned2 = scanned2.replace("*", ".*");
		Pattern p2 = Pattern.compile(scanned2);
		Matcher match = p2.matcher(scanned1);
		if (match.matches()) {
			System.out.println("OK");
		}
		else {
			System.out.println("KO");
		}
		System.exit(0);
	}

}
