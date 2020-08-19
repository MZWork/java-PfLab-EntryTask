package task3;

import java.io.File;
import java.util.Locale;
import java.util.Scanner;

public class FileScanner {
	
	public static Scanner sc;
	public static boolean EOF;
	
	FileScanner(String str) { 
		EOF = false;		
		try {
			sc = new Scanner(new File(str));			
		} catch (Exception e) {
			System.out.println("Файл не найден, введите корректный путь:\n");
			System.exit(0);
		}
		sc.useLocale(Locale.US);
		if(sc.hasNext()) { 			 
		
		} else {
			System.out.println("Лог файл пустой");
			System.exit(0);
		}		
	}

}
