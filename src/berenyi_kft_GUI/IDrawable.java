package berenyi_kft_GUI;

/**
 * Interfész a játék képernyőre rajzolható nézet-objektumainak
 * 
 * @author berenyi_kft
 */
public interface IDrawable {
	
	/**
	 * Frissíti a nézet-objektumot a modellbeli állapota alapján:
	 * beállítja a pozícióját és az ikonját.
	 */
	public void draw();

}
