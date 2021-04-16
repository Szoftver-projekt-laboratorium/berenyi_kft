package berenyi_kft;

/**
 * A jatekosok altal teheto lepesek felsorolt tipusa
 * @author berenyi_kft
 */
public enum PlayerCommand {
	PASS,
	MOVE,
	DRILL,
	MINE,
	RESTORE,
	CREATE_ROBOT,
	CREATE_GATE_PAIR,
	RELEASE_GATE,
	INVALID;
	
	/**
	 * Szovegesen megadott parancshoz hozzarendeli az azt reprezentalo enumot.
	 * @param s A muvelet szoveges formaban
	 * @return A muveletet azonosito felsorolt tipusu valtozo
	 */
	public static PlayerCommand fromString(String s) {
		switch (s) {
			case "pass": return PASS;
			case "move": return MOVE;
			case "drill": return DRILL;
			case "mine": return MINE;
			case "restore": return RESTORE;
			case "create_robot": return CREATE_ROBOT;
			case "create_gate_pair": return CREATE_GATE_PAIR;
			case "release_gate": return RELEASE_GATE;
			default: return INVALID;
		}
	}
}
