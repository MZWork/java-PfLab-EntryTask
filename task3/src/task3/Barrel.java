package task3;

public class Barrel {
	
	public int maxWater;
	public int currentWater;
		
	Barrel (int maxWater, int currentWater) {
		this.maxWater = maxWater;
		this.currentWater = currentWater;
	}
	
	public boolean top_up (int waterModif) {
		if (waterModif > (this.maxWater - this.currentWater)) {
			return false;
		}
		else {
			this.currentWater += waterModif;
			return true;
		}
	}
	
	public boolean scoop (int waterModif) {
		if (waterModif >  this.currentWater) {
			return false;
		}
		else {
			this.currentWater -= waterModif;
			return true;
		}
	}
}
