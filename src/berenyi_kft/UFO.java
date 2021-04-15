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
		proto.println(proto.getId(this)+".die()");
		proto.incrTabs();
		super.die();
		timer.removeSteppable(this);
		proto.decrTabs();
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
		proto.println(proto.getId(this)+".mine()");
		 proto.incrTabs();
		place.minedByUFO();
		proto.decrTabs();
	}
}
