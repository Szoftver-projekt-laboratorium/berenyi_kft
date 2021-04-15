package berenyi_kft;

public class Player {
	
	enum Command{
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
	
	
	public String getDescription() { 
		
		String str="";
		
		String id=Proto.getId(this);
		str+="Player "+id+"\n";
		
		String nameId=Proto.getId(name);
		str+="\tname "+nameId+"\n";
		
		String settlerId=Proto.getId(settler);
		str+="\tsettler "+settlerId+"\n";
		
		if(isAlive)
			str+="isAlive true\n";
		else
			str+="isAlive false\n";
		
		return str;	
	}
	
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
	
	public void actOnSettler(Command cmd, Object[] params) {
		//TODO switch-case a commandoknak
	}
	
}
