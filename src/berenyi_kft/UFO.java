package berenyi_kft;

import java.util.Random;

public class UFO extends Character implements ISteppable {
	
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
