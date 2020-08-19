package task3;

import java.io.*;
import java.util.*;
import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;

public class Main {

	public static void main(String[] args) throws IOException {
		if (args.length != 3) {
			System.out.println("Ошибка: не найдены входные аргументы командной строки\nUsage: java -jar Task3.jar path_to_log first_date second_date");
			System.exit(0);
		}
		@SuppressWarnings("unused")
		FileScanner scanIni = new FileScanner(args[0]);	
		parseInit (args[1], args[2]);		
		try {
			PrintWriter csvWriter = new PrintWriter(new File("results.csv"));
			StringBuilder sb = new StringBuilder();
			
			sb.append("Количество попыток налить воду в бочку");
			sb.append(";");
			sb.append("Процент ошибок допущеный при наливании за указанный период");
			sb.append(";");
			sb.append("Объем налитой в бочку за указанный период");
			sb.append(";");
			sb.append("Объем не налитой в бочку за указанный период");
			sb.append(";");
			sb.append("Количество попыток отчерпать воду из бочки");
			sb.append(";");
			sb.append("Процент ошибок допущеный при отчерпывании за указанный период");
			sb.append(";");
			sb.append("Объем отчерпнутой из бочки за указанный период");
			sb.append(";");
			sb.append("Объем не отчерпнутой из бочки за указанный период");
			sb.append(";");
			sb.append("Объем воды в бочке в начале указанного периода");
			sb.append(";");
			sb.append("Объем воды в бочке в конце указанного периода"); 
			sb.append("\r\n");
			
			for (int i = 0; i < 10; i++) {
				sb.append(String.valueOf(Act.results[i]));
				if (i!=9) {
					sb.append(';');
				}
			}
			sb.append('\n');
			
			csvWriter.write(sb.toString());			
			csvWriter.close();
		} catch (Exception e) {
			
		}		
		System.exit(0);	
	}
	
	public static void parseInit(String first_date, String second_date) {					
		int water[] = {0, 0};
		for (int cou = 0; cou < 2;) {
			if (FileScanner.sc.hasNextInt()) {
				water[cou] = FileScanner.sc.nextInt();
				cou++;
			}
			else FileScanner.sc.next();
		}
		Barrel barrel = new Barrel(water[0], water[1]);
		parseLogMain(DateFormat(first_date), DateFormat(second_date), barrel);
		Act.results[9] = barrel.currentWater;
		if (Act.results[0] != 0) {
			Act.results[1] = Act.results[1]/Act.results[0];
		}
		else {
			Act.results[1] = 0;
		}
		if (Act.results[4] != 0) {
			Act.results[5] = Act.results[5]/Act.results[4];
		}
		else {
			Act.results[5] = 0;
		}

	}
	
	public static Date DateFormat (String date) {
		TemporalAccessor ta = DateTimeFormatter.ISO_INSTANT.parse(date);
		Instant i = Instant.from(ta);
		return Date.from(i);		
	}
	
	public static void parseLogMain(Date first_date, Date second_date, Barrel barrel) {
		while (!(FileScanner.EOF)) {
			Act action = workHeader(first_date, barrel);
			Act.results[8] = barrel.currentWater;
			actionHandler(action, second_date, barrel);
		}					
	}
	
	public static Act workHeader(Date first_date, Barrel barrel) {
		Act action = workLine();
		if (first_date.compareTo(action.ActDate) <= 0) {
			return action;
		}
		else {
			if (action.ActType.equals("scoop")) {
				barrel.scoop(action.ActModif);
			}
			else {
				barrel.top_up(action.ActModif);
			}
		}		
		return null;
	}
	
	public static Act workLine() {		
		String Line = "";
		int actModif = 0;
		while (FileScanner.sc.hasNext()) {
			Line = Line + FileScanner.sc.next();
			if (Line.contains(")")) {
				if (Line.contains("Z")) {
					break;
				}
				else {
					Line = "";
				}
			}
		}
		if (!(FileScanner.sc.hasNext())) {
			FileScanner.EOF = true;
		}
		Date actDate = DateFormat(Line.substring(0, Line.indexOf('Z')+1));			
		String actUsr = Line.substring(Line.indexOf('[')+1,Line.indexOf(']'));
		String actType = Line.substring(Line.indexOf("wanna")+5,Line.lastIndexOf('p')+1);
		System.out.println(actType);
		if (actType.equals("scoop")) {
			actModif = Integer.parseInt(Line.substring(Line.indexOf("scoop")+5,Line.indexOf('l')));
		}
		else {
			actModif = Integer.parseInt(Line.substring(Line.indexOf("up")+2,Line.indexOf('l')));
		}
		System.out.println(actModif + "\n");
		Act action = new Act(actUsr, actDate, actType, actModif);
		return action;
	}
	
	public static boolean actionHandler(Act action, Date second_date, Barrel barrel) {
		if (second_date.compareTo(action.ActDate) >= 0) {
			if (action.ActType.equals("scoop")) {
				Act.results[4] += 1;
				if (barrel.scoop(action.ActModif)) {
					Act.results[6] += action.ActModif;
				}
				else {
					Act.results[5] += 1;
					Act.results[7] += action.ActModif;
				}			
			}
			else {
				Act.results[0] += 1;
				if (barrel.top_up(action.ActModif)) {
					Act.results[2] += action.ActModif;
				}
				else {
					Act.results[1] += 1;
					Act.results[3] += 1;
				}
			}
			return true;
		}
		else {
			return false;
		}
	}

}
