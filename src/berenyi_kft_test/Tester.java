package berenyi_kft_test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import berenyi_kft.Controller;
import berenyi_kft.Player;
import berenyi_kft.Proto;
import berenyi_kft.State;

/**
 * Tesztelest vegzo segedosztaly a prototipus programhoz
 * @author berenyi_kft
 */
public class Tester {
	// A 0. az csak teszt tesztfajl sorszam, 1-tol 38-ig mennek majd
	// az igazi tesztek.
	private static final int testCount = 38;
	
	// Eleresi ut
	private static String path = "src\\test_data\\";
	
	
	static boolean compareTextFiles(String file1, String file2) throws IOException {
		BufferedReader r1 = new BufferedReader(new FileReader(file1));
		BufferedReader r2 = new BufferedReader(new FileReader(file2));
		int c1 = 0, c2 = 0;
		while (true) {
			c1 = r1.read();
			c2 = r2.read();
			if (c1 == -1 && c2 == -1)
				return true;
			else if (c1 == -1 || c2 == -1 || c1 != c2) {
				return false;
			}
		}
	}
	
	// Atirtam String paramterure, de majd meglatjuk, a File jobb-e.
	public static boolean compare(String fName1path, String fName2path)
														throws IOException {
		
		String file1 = fName1path;
		String file2 = fName2path;
		
		if (compareTextFiles(file1, file2)){
			System.out.println("Files' contents are the same.");
			return true;
		}
		else {
			// TODO: Kellene az is, hogy hol ternek el, a compareTextFiles
			// terjen vissza a hellyel/sorokkal valahogy.
			System.out.println("Files are different.");
			return false;
		} 
		
	}
	
	/**
	 * Metodus a tesztspecifikus muvelet elvegzesehez.
	 * @param controller A jatekot iranyito vezerlo
	 */
	private static void executeTestSpecificCommand(Controller controller) {
		Player actPlayer = controller.getActPlayer();
		
		// TODO: Sorszam szerinti if-else agak es metodushivasok az actPlayeren;
		// az idozitett inditas megtortenik a startban, azoknal itt nem kell semmi
		
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
		String testName ="test_" + Integer.toString(testNum);
		String inputName = Tester.path + "test_inputs\\" + testName + ".in";
		String resultName = Tester.path + "test_results\\" + testName + ".result";
		String outputName = Tester.path + "test_outs\\" + testName + ".out";
		
		try {
			// (A statikus Proto osztalyra es annak statikus metodusaira hivatkozik,
			// plusz a Tester.compare()-re)
			Proto.setRandom(true);
			Proto.enableLogging(true);
			Proto.load(inputName);
			// Proto.start();
			
			Proto.Objects allObjects = Proto.getAllObjects();
			Controller controller = allObjects.getController();
			controller.setState(State.RUNNING);
			
			executeTestSpecificCommand(controller);
			
			controller.setState(State.PAUSED);
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
	 * @throws IOException 
	 */
	public static void testerMain(String[] args) throws IOException {
		compare("src/berenyi_kft_test/txt_tarto/test1.txt","src/berenyi_kft_test/txt_tarto/test1.txt");
		Scanner sc = new Scanner(System.in);
		boolean exit = false;
		while (!exit & sc.hasNextLine()) {
			String line = sc.nextLine();
			String[] tokens = line.split("\\s+");
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
