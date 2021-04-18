package berenyi_kft;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * A Napot reprezentalo osztaly
 * @author berenyi_kft
 */
public class Sun implements ISteppable {

	/**
	 * A kovetkezo napvihar bekovetkezeseig hatralevo ido
	 */
	private long timeToSunStorm;
	
	/**
	 * A Nappal kozvetlenul szomszedos aszteroidak listaja
	 */
	private ArrayList<Asteroid> neighboringAsteroids = new ArrayList<Asteroid>();
	
	/**
	 * A jatekot reprezentalo osztaly
	 */
	// TODO: Elvileg nem szukseges, mindenhonnan ki kellene vennunk.
	private Game game = null;
	
	//-------------------------------------------------------------
	
	/**
	 * Betolti a Nap attributumait az sc Scanner aktualis poziciojatol.
	 * @param sc A beolvasast vegzo Scanner
	 */
	public void load(Scanner sc) {
		String line = sc.nextLine();
		while (!line.equals("") & sc.hasNextLine()) {
			line = sc.nextLine();
			line = line.stripLeading();
			String[] tokens = line.split("\\s+");
			
			switch (tokens[0]) {
				case "timeToSunStorm":
					timeToSunStorm = Integer.parseInt(tokens[1]);
					break;
					
				case "neighboringAsteroids":
					for (int i = 1; i < tokens.length; i++) {
						Asteroid a = (Asteroid)Proto.getObject(tokens[i]);
						if (a != null)
							neighboringAsteroids.add(a);
					}
					break;
				
				default:
					break;
			}
		}
	}
	
	public String getDescription() { 
		
		String str="";
		
		String id=Proto.getId(this);
		str+="Sun "+id+"\n";
		
		String timeId=Long.toString(timeToSunStorm);
		str+="\ttimeToSunStorm "+timeId+"\n";
		
		if(!neighboringAsteroids.isEmpty()) {   
			str+="\tneighboringAsteroids";
			for(Asteroid a : neighboringAsteroids) {
				String asteroidId=Proto.getId(a);
				str+=" "+asteroidId;
			}
			str+="\n";
		}
		else
			str+="\tneighboringAsteroids null\n";
		
		return str;	
	}
	
	/**
	 * Beallitja a jatek osztalyt.
	 * @param game A jatekot reprezentalo osztaly
	 */
	public void setGame(Game game) {
		this.game = game;
	}

	/**
	 * Ha a timeToSunStorm attributum pozitiv, akkor eggyel csokkenti 
	 * (varakozik). Ha pedig 0, akkor napvihart indit a sunStorm() fuggvenyt
	 * hivva, majd a timeToSunStorm-nak beallit egy veletlen pozitiv egesz erteket.
	 */
	@Override
	public void step() {
		System.out.println("Sun's step() has been called");
		// A teszt kedveert legyen minden egyes step soran napvihar.
		if(timeToSunStorm>0)
			timeToSunStorm--;
		else {
			sunStorm();
			Random r=new Random();
			timeToSunStorm=r.nextInt(300);
		}
	}
	
	/**
	 * Lekerdezi a Game-tol az aszteroidak kollekciojat, majd egyesevel
	 * meghivja mindegyik aszteroida destroySurface() fuggvenyet.
	 */
	public void sunStorm() {
		//System.out.println("Sun's sunStorm() has been called");
		
		Proto.println(Proto.getId(this)+".sunStorm()");
		Proto.incrTabs();
		
		ArrayList<Asteroid> list=new ArrayList<Asteroid>();
		for(Asteroid a1 : neighboringAsteroids) {
			for(Asteroid a2 : a1.getNeighbors()) {
				if(!list.contains(a2))
					list.add(a2);
			}
			if(!list.contains(a1))
				list.add(a1);
		}
		
		for(Asteroid a : list) {
			a.destroySurface();
		}
		
		Proto.decrTabs();
	}
	
	/**
	 * Megallapitja, hogy az a aszteroida napkozeli-e, vagyis hogy
	 * legfeljebb masodszomszedos-e a Nappal.
	 * @return Pontosan akkor true, ha az aszteroida napkozelben van
	 */
	public boolean isCloseToSun(Asteroid a) {
		System.out.println("Sun's isCloseToSun(a: Asteroid) has been called");
		
		if(neighboringAsteroids.contains(a))
			return true;
		
		for(Asteroid a2 : a.getNeighbors())
			if(neighboringAsteroids.contains(a2))
				return true;
	
		return false;
	}
	
	/**
	 * Beallitja a kovetkezo napvihar idopontjat.
	 * @param time Az ido a legkozelebbi napviharig
	 */
	public void setTimeToSunStorm(long time){
		System.out.println("Sun's settimeToStorm(time: long) has been called");
		timeToSunStorm = time;
	}
	
	/**
	 * Uj aszteroidat ad a Nappal szomszedos aszteroidak kollekciojahoz.
	 * @param a A Nappal szomszedos uj aszteroida
	 */
	public void addNeighbor(Asteroid a) {
		System.out.println("Sun's addNeighbor(a: Asteroid) has been called");
		this.neighboringAsteroids.add(a);
	}
}
