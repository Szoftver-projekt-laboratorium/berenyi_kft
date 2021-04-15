package berenyi_kft;

import java.util.Random;

public class UFO extends Character implements ISteppable {
	
	
	public String getDescription() { 
		
		String str="";
		
		String id=Proto.getId(this);
		str+="UFO "+id+"\n";
		
		String timerId=Proto.getId(timer);
		str+="\ttimer "+timerId+"\n";
		
		return str;	
	}

	@Override
	public void die() {
		super.die();
		timer.removeSteppable(this);
	}
	
	public void step() {
		if(place.getRockLayerThickness()==0 && place.getResource()!=null) {
			this.mine();
		}
		else {
			Random r=new Random();
			this.move(r.nextInt());
		}
	}
	
	public void mine() {
		place.minedByUFO();
	}
}
