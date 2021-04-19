package berenyi_kft;

import java.util.Scanner;

/**
 * Az uran nyersanyagot reprezentalja: radioaktiv, es van
 * stabilitasa/elettartama is
 * 
 * @author berenyi_kft
 */
public class Uranium extends RadioactiveResource {

	/**
	 * Az uran stabilitasa a robbanasig hatralevo expoziciok szama; expozicion a
	 * napkozeli aszteroidan torteno kifurast vagy a nyersanyag visszatolteset
	 * ertve.
	 */
	private int life = 3;

	// ----------------------------------------------------------

	/**
	 * Betolti az uran attributumait az sc Scanner aktualis poziciojatol.
	 * 
	 * @param sc A beolvasast vegzo Scanner
	 */
	public void load(Scanner sc) {
		String line = sc.nextLine();
		while (!line.equals("") & sc.hasNextLine()) {
			line = sc.nextLine();
			line = line.stripLeading();
			String[] tokens = line.split("\\s+");

			switch (tokens[0]) {
			case "life":
				life = Integer.parseInt(tokens[1]);
				break;

			default:
				break;
			}
		}
	}

	public String getDescription() {

		String str = "";

		String id = Proto.getId(this);
		str += "Uranium " + id + "\n";

		String lifeStr = Integer.toString(life);
		str += "\tlife " + lifeStr + "\n";

		return str;
	}

	/**
	 * Osszehasonlitja magat a parameterkent kapott nyersanyaggal a tipusa szerint,
	 * es visszater az egyezes logikai ertekevel.
	 * 
	 * @param r A masik nyersanyag, amelyikkel ezt a nyersanyagot osszehasonlitjuk
	 * @return Pontosan akkor true, ha r is Uranium tipusu nyersanyag
	 */
	@Override
	public boolean isCompatibleWith(Resource r) {
		/*
		 * Proto.println(Proto.getId(this) + ".isCompatibleWith("
		 * + Proto.getId(r) + ")");
		 */
		if (this.getClass().equals(r.getClass()))
			return true;
		return false;
	}

	/**
	 * Az uran egyseg elettartamat
	 */
	public void decLife() {
		Proto.println(Proto.getId(this) + ".decLife()");
		life--;
	}

	/**
	 * Az uran egyseg napkozeli megfurt aszteroidan exponalodik, ezzel az
	 * elettartama eggyel csokken. Ha minden elete elfogyott, berobban, felrobbantva
	 * ezzel az aszteroidajat is (explodedBy(RadioactiveResource rr)).
	 */
	@Override
	public void drilledOut(Asteroid a) {
		Proto.println(Proto.getId(this) + ".drilledOut(" + Proto.getId(a) + ")");
		Proto.incrTabs();
		decLife();
		if (life == 0) {
			System.out.println("Uranium is exploding!");
			a.explodedBy(this);
		}
		Proto.decrTabs();
	}

	/**
	 * Visszater az uran nyersanyagegyseg egy klonjaval.
	 * 
	 * @return A nyersanyag klonozott masolata
	 */
	@Override
	public Uranium clone() {
		Uranium urClone = (Uranium) super.clone();
		return urClone;
	}

	/**
	 * Az uran nyersanyagegyseg egy uj azonositoval eltarolodik a Proto osztaly
	 * allObjects nyilvantartasaban.
	 */
	@Override
	public void addToGame() {
		// Proto.println(Proto.getId(this) + ".addToGame()");
		Proto.getAllObjects().addUranium(this);
	}

	/**
	 * Az uran nyersanyagegyseg megsemmisul, eltunik a jatekbol.
	 */
	@Override
	public void removeFromGame() {
		// Proto.println(Proto.getId(this) + ".removeFromGame()");
		Proto.getAllObjects().removeUranium(this);
	}
}
