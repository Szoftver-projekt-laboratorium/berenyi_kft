package berenyi_kft;

/**
 * Az uran nyersanyagot reprezentalja
 * @author berenyi_kft
 */
public class Uranium extends RadioactiveResource {
	
	private int life=3;
	
	public String getDescription() { 
		
		String str="";
		
		String id=Proto.getId(this);
		str+="Uranium "+id+"\n";
		
		String lifeId=Proto.getId(life);
		str+="\tlives "+lifeId+"\n";
		
		return str;	
	}
	
	/**
	 * Osszehasonlitja magat a parameterkent kapott nyersanyaggal a tipusa szerint,
	 * es visszater az egyezes logikai ertekevel.
	 * @param r A masik nyersanyag, amelyikkel ezt a nyersanyagot osszehasonlitjuk
	 * @return Pontosan akkor true, ha r is Uranium tipusu nyersanyag
	 */
	@Override
	public boolean isCompatibleWith(Resource r) {
		System.out.println("Uranium's isCompatibleWith(r: Resource) has been called");
		if(this.getClass().equals(r.getClass()))
			return true;
		return false;
	}
	
	public void decLife() {
		life--;
	}
	
	@Override
	public void drilledOut(Asteroid a) {
		decLife();
		if(life==0)
			a.explodedBy(this);
	}
}
