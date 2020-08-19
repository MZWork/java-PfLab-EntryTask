package task3;

import java.util.Date;

public class Act {

	public String ActUsername;
	public Date ActDate;
	public String ActType;
	public int ActModif;
	public static int[] results = {0,0,0,0,0,0,0,0,0,0};
	
	Act (String ActUsername, Date ActDate, String ActType, int ActModif) {
		this.ActUsername = ActUsername;
		this.ActDate = ActDate;
		this.ActType = ActType;
		this.ActModif = ActModif;
	}
	
}
