package berenyi_kft_test;

import java.util.Scanner;

import berenyi_kft.Proto;

/**
 * Tesztelest vegzo segedosztalyok a prototipus programhoz
 * @author berenyi_kft
 */
public class Tester {
	private static final int testCount = 38;
	
	// Atirtam String paramterure, de majd meglatjuk, a File jobb-e.
	public static boolean compare(String fName1, String fName2) {
		// Bármely két fájl egyelore ugyanolyan...
		return true;
	}
	
	/**
	 * Vegrehajtja a testNum sorszamu tesztet.
	 * Ha a bemeneti (.in) es elvart kimeneti (.out) fajlok nem allnak
	 * rendelkezesre a futtatott program mappajaban, kivetel keletkezik.
	 * A teszteset eredmenye egy .result kiterjesztesu kimeneti fajlba irodik,
	 * amelyet a compare() metodusban hasonlit ossze az elvart kimenettel.
	 * @param testNum
	 */
	public static void testOne(int testNum) {
		String testName = "test_" + Integer.toString(testNum);
		String inputName = testName + ".in";
		String resultName = testName + ".result";
		String outputName = testName + ".out";
		
		try {
			// (A statikus Proto osztalyra es annak statikus metodusaira hivatkozik,
			// plusz a Tester.compare()-re).
			Proto.setRandom(true);
			Proto.enableLogging(true);
			Proto.load(inputName);
			// Proto.start();
			// <Tesztspecifikus parancs>
			// Proto.stop();
			Proto.save(resultName);
			Tester.compare(resultName, outputName);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Sorban vegrehajtja az osszes tesztesetet az elsotol
	 * a Tester.testCount valtozoval jelolt utolsoig.
	 */
	public static void testAll() {
		for (int i = 0; i < Tester.testCount; i++) {
			testOne(i);
		}
	}
	
	/**
	 * A tesztelest vegzo programresz belepesi pontja.
	 * A standard bemeneten test <test_num>, test, illetve exit parancsokra
	 * figyel, soronkent feldolgozva azokat.
	 * @param args A proto program altal atadott parancssori argumentumok
	 */
	public static void testerMain(String[] args) {
		Scanner sc = new Scanner(System.in);
		boolean exit = false;
		String line = sc.nextLine();
		while (line != null & !exit) { // nextLine() utan kell null check?
			String[] tokens = line.split("\\s");
			String cmd = tokens[0];
			
			switch (cmd) {
				case "test":
					if (tokens.length == 1) {
						testAll();
					} else {
						testOne(Integer.parseInt(tokens[1]));
					}
					break;
				
				case "exit":
					exit = true;
					//System.exit(0);
					break;
				
				default:
					System.out.println("Invalid operation, please type in a new command.");
					break;
			}
		}
		sc.close();
	}
}
