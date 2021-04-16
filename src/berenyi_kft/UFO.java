package berenyi_kft;

import java.util.Random;
import java.util.Scanner;

public class UFO extends Character implements ISteppable {
	
	
	public String getDescription() { 
		
		String str="";
		
		String id=Proto.getId(this);
		str+="UFO "+id+"\n";
		
		String timerId=Proto.getId(timer);
		str+="\ttimer "+timerId+"\n";
		
		return str;	
	}
	
	/*
	 * minta:
	 * UFO ufo1
	 * 	timer timer
	 *  place a1
	 */
	
	public void load(Scanner sc) {
		String line = sc.nextLine();
		while (!line.equals("") & sc.hasNextLine()) {
			line = sc.nextLine();
			line = line.stripLeading();
			String[] tokens = line.split("\\s+");

			switch (tokens[0]) {
				case "timer":
					timer = (Timer)Proto.getObject(tokens[1]);
					break;
					
					
				case "place":
					place = (Asteroid)Proto.getObject(tokens[1]);
					break;
					
					
				default:
					break;
			}
		}
	}
	
	/**
	 * Uj UFO jon letre, amely parameterkent atveszi az ot lepteto idozitot is.
	 * A konstruktor egybol hozzaadja az ufot az idozitojehez.
	 * @param timer A jatek idozitoje
	 */
	public UFO(Timer timer) {
		System.out.println("UFO's UFO(Timer timer) has been called");
		this.timer = timer;
		if (timer != null)
			timer.addSteppable(this);
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
