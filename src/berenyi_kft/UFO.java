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
		
		String placeId=Proto.getId(place);
		str+="\tplace "+placeId+"\n";
		
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
		Proto.println(Proto.getId(this) + ".UFO(" + Proto.getId(timer) + ")");
		Proto.incrTabs();
		this.timer = timer;
		if (timer != null)
			timer.addSteppable(this);
		Proto.decrTabs();
	}
	
	/**
	 * Az UFO megsemmisul:
	 * meghivja a Timer removeSteppable(ISteppable s) metodusat.
	 */
	@Override
	public void die() {
		Proto.println(Proto.getId(this)+".die()");
		Proto.incrTabs();
		super.die();
		timer.removeSteppable(this);
		Proto.getAllObjects().removeUFO(this);
		Proto.decrTabs();
	}
	
	/**
	 * Az UFO lep. Ha az aszteroidaja meg van furva, es talal benne nyersanyagot,
	 * akkor kibanyassza; kulonben egy veletlenszeru szomszedos aszteroidara repul.
	 */
	public void step() {
		Proto.println(Proto.getId(this) + ".step()");
		Proto.incrTabs();
		if(place.getRockLayerThickness()==0 && place.getResource()!=null) {
			this.mine();
		}
		else {
			if(Proto.isRandom()) {
				Random r=new Random();
				this.move(r.nextInt());
			}
			else
				move(0);
		}
		Proto.decrTabs();
	}
	
	/**
	 * Az UFO banyaszik az aszteroidajan.
	 * Ha sikerult kibanyasznia a magban levo nyersanyagot, az eltunik.
	 */
	public void mine() {
		Proto.println(Proto.getId(this) + ".mine()");
		Proto.incrTabs();
		place.minedByUFO();
		Proto.decrTabs();
	}
}
