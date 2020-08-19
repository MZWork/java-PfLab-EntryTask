package task2;

import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) {
		boolean loopUsage;
		Scanner x = null, scanStr = null;
		String a = "";
		String scanned = "";
		double t1=0, t2=0, xSph=0, ySph=0, zSph=0, radius=0, x1=0, y1=0, z1=0, x2=0, y2=0, z2=0, discr=0, coefA=0, coefB=0, coefC=0;
		Scanner sc = new Scanner(System.in);
		System.out.println("Введите путь к файлу: ");
		scanned = sc.next();
		do {
			try {
				x = new Scanner(new File(scanned));
				loopUsage = false;
			} catch (Exception e) {
				System.out.println("Файл не найден, введите корректный путь: ");
				scanned = sc.next();
				loopUsage = true;
			}
		} while (loopUsage);
		sc.close();
		while(x.hasNext()) {			
			a = a + x.next();			
		}
		System.out.println(a);
		a = a.replace('{', ' ');
		a = a.replace('}', ' ');
		a = a.replace('[', ' ');
		a = a.replace(']', ' ');
		a = a.replace(',', ' ');
		a = a.replace(':', ' ');
		System.out.println(a);
		scanStr = new Scanner(a);
		scanStr.useLocale(Locale.US);
		scanStr.findInLine("center"); //start parsing
		if (scanStr.hasNextDouble()) {
			xSph = scanStr.nextDouble();
		}
		if (scanStr.hasNextDouble()) {
			ySph = scanStr.nextDouble();
		}
		if (scanStr.hasNextDouble()) {
			zSph = scanStr.nextDouble();
		}
		scanStr.findInLine("radius");
		if (scanStr.hasNextDouble()) {
			radius = scanStr.nextDouble();
		}
		scanStr.findInLine("line");
		if (scanStr.hasNextDouble()) {
			x1 = scanStr.nextDouble();
		}
		if (scanStr.hasNextDouble()) {
			y1 = scanStr.nextDouble();
		}
		if (scanStr.hasNextDouble()) {
			z1 = scanStr.nextDouble();
		}
		if (scanStr.hasNextDouble()) {
			x2 = scanStr.nextDouble();
		}
		if (scanStr.hasNextDouble()) {
			y2 = scanStr.nextDouble();
		}
		if (scanStr.hasNextDouble()) { //stop parsing
			z2 = scanStr.nextDouble();
		}
		scanStr.close();
		x.close();
		coefA = Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2) + Math.pow(z2 - z1, 2);
		coefB = 2 * ((x2 - x1) * (x1 - xSph) + (y2 - y1) * (y1 - ySph) + (z2 - z1) * (z1 - zSph));
		coefC = Math.pow(x1 - xSph,2) +  Math.pow(y1 - ySph,2) +  Math.pow(z1 - zSph,2) - Math.pow(radius, 2);
		discr = Math.pow(coefB, 2) - (4*coefA*coefC);

        if (discr < 0) 		// No intersection points
        {
            System.out.println("\nКоллизий не найдено");
            return;     
        }
        else if(discr == 0)  // Single intersection point
        {
          if (coefA > 0)
            {
                t1 = -coefB/(2*coefA);
                double[] intersecPoint = findXPoint(x1, y1, z1, x2, y2, z2, t1);
                System.out.println("Одна точка пересечения:\n" + intersecPoint[0] + "\n"+ intersecPoint[1] + "\n" + intersecPoint[2] + "\n");
            }
            else{
                System.out.println("Не линия\n");
            }
        }

        else // Two intersection points
        {       
            t1 = (-coefB - Math.sqrt(discr))/(2*coefA); 
            t2 = (-coefB + Math.sqrt(discr))/(2*coefA); 
         
            double[] intersecPoint1 = findXPoint(x1, y1, z1, x2, y2, z2, t1);
            double[] intersecPoint2 = findXPoint(x1, y1, z1, x2, y2, z2, t2);

            System.out.println("Первая точка пересечения:\n " + intersecPoint1[0] + "\n" + intersecPoint1[1] + "\n" + intersecPoint1[2] + "\n");
            System.out.println("Вторая точка пересечения:\n " + intersecPoint2[0] + "\n" + intersecPoint2[1] + "\n" + intersecPoint2[2] + "\n");
        }

        System.exit(0);     
    }


	public static double[] findXPoint(double x1, double y1, double z1, double x2, double y2, double z2, double t) {
		double[] x = {0,0,0};
	
	    x[0] = x1 + t * (x2 - x1);
	    x[1] = y1 + t * (y2 - y1);      
	    x[2] = z1 + t * (z2 - z1);
		    
	
	    return x;   
	}

}

	

