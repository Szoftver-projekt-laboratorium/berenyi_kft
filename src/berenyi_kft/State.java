package berenyi_kft;

/**
 * Felsorolt tipus a jatek allapotainak jelolesere.
 * @author berenyi_kft
 */
public enum State {
	INIT,
	RUNNING,
	PAUSED,
	WON,
	LOST,
	EXITED;
	
	/**
	 * Visszater az s sztring altal jelolt allapottal
	 * @param s Az allapot neve szovegesen
	 * @return Az allapot a felsorolt tipus szerint
	 */
	public static State fromString(String s) {
		switch (s) {
			case "init": return INIT;
			case "running": return RUNNING;
			case "paused": return PAUSED;
			case "won": return WON;
			case "lost": return LOST;
			default: return EXITED;
		}
	}
	
	/**
	 * Megadja a state allapotba lepes jelenteset, uzenetet a felhasznalonak.
	 * @param state Az allapot, amelybe torteno lepes uzenetet kerdezzuk
	 * @return A state allapotba lepeskor kiirando uzenet/naplobejegyzes
	 */
	public static String toString(State state) {
		switch (state) {
			case INIT: return "init";
			case RUNNING: return "running";
			case PAUSED: return "paused";
			case WON: return "won";
			case LOST: return "lost";
			default: return "exited";
		}
	}
}
