package berenyi_kft;

public class Player {
	
	private enum Command{
		PASS,
		MOVE,
		DRILL,
		MINE,
		RESTORE,
		CREATE_ROBOT,
		CREATE_GATE_PAIR,
		RELEASE_GATE
	}
	
	private String name;
	
	private Settler settler;
	
	private boolean isAlive=true;
	
	public void setSettler(Settler s) {
		settler=s;
	}
	
	public Settler getSettler() {
		return settler;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isAlive() {
		return isAlive;
	}

	public void setAlive(boolean isAlive) {
		this.isAlive = isAlive;
	}
	
	public void actOnSettler(Command cmd) {
		//TODO switch-case a commandoknak
	}
	
}
