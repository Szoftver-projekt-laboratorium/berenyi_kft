package berenyi_kft_test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

import berenyi_kft.Controller;
import berenyi_kft.Player;
import berenyi_kft.PlayerCommand;
import berenyi_kft.Proto;
import berenyi_kft.Resource;
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

	
	// Atirtam String paramterure, de majd meglatjuk, a File jobb-e.
	public static boolean compare(String fpath1, String fpath2)
														throws IOException {
	
		
		
		
		Scanner input1 = new Scanner(new File(fpath1));//read first file
		Scanner input2 = new Scanner(new File(fpath2));//read second file

		String line1, line2;
		
		while(input1.hasNextLine() && input2.hasNextLine()){
			line1 = input1.nextLine();   
			line2 = input2.nextLine(); 

		    if(!line1.equals(line2)){
		        System.out.println("Differences found: "+"\n"+line1+'\n'+line2);
		        return false;
		    }
		   
		}
		System.out.println("Files contents are equal");
		return true;
		/*
		String file1Content = Files.readString(Path.of(fpath1));
		String file2Content = Files.readString(Path.of(fpath2));
		
		if (file1Content.equals(file2Content)){
			System.out.println("Files' contents are the same.");
			return true;
		}
		else {
			// TODO: Kellene az is, hogy hol ternek el, a compareTextFiles
			// terjen vissza a hellyel/sorokkal valahogy.
			System.out.println("Files are different.");
			return false;
		} 
		*/
		
	}
	
	/**
	 * Metodus a tesztspecifikus muvelet elvegzesehez.
	 * @param controller A jatekot iranyito vezerlo
	 */
	private static void executeTestSpecificCommand(Controller controller, int testNum) {
		// TODO: Ez a par sor egyelore csak foltozgatas.
		// Minden jatekos inditotta tesztesethez kellene Controller is, hogy teljes legyen.
		Player actPlayer = null;
		if (controller != null)
			actPlayer = controller.getActPlayer();
		else {
			actPlayer = (Player) Proto.getObject("p1");
		}
		
		// TODO: Sorszam szerinti if-else agak es metodushivasok az actPlayeren.
		// (Az idozitett inditas megtortenik a startban, azoknal itt nem kell semmi.)
		if (testNum == 1) {
			Object[] params = { "pass" };
			actPlayer.actOnSettler(PlayerCommand.PASS, params);
		} else if (testNum == 2 | (testNum >= 4 & testNum <= 5)) {
			Object[] params = { "move", "0" };	// Vigyazz, szovegesen kell atadni a szamokat is!
			actPlayer.actOnSettler(PlayerCommand.MOVE, params);
		} else if (testNum == 3) {
			Object[] params = { "mine" };
			actPlayer.actOnSettler(PlayerCommand.MINE, params);
		} else if (testNum == 13) {
			Object[] params = { "restore", (Resource) Proto.getObject("ur1") };
			actPlayer.actOnSettler(PlayerCommand.RESTORE, params);
		} else if (testNum >= 29 & testNum <= 35) {
			/* (A start parancs inditja a tesztet, egy ISteppable lep magatol) */
		}
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
		if (testNum < 1 | testNum > testCount) {
			System.out.println("Please give a test number between 1 and "
															+ testCount + ".");
			return;
		}
		
		String testName ="test_" + Integer.toString(testNum);
		String inputName = Tester.path + "test_inputs\\" + testName + ".in";
		String resultName = Tester.path + "test_results\\" + testName + ".result";
		String outputName = Tester.path + "test_outs\\" + testName + ".out";
		
		try {
			Proto.setRandom(true);
			Proto.enableLogging(true);
			Proto.load(inputName);
			
			Proto.Objects allObjects = Proto.getAllObjects();
			Controller controller = allObjects.getController();
			if (controller != null) {
				controller.setState(State.RUNNING);
			}
			
			executeTestSpecificCommand(controller, testNum);
			
			if (controller != null) {
				controller.setState(State.PAUSED);
			}
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
		for (int i = 0; i <= Tester.testCount; i++) {
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
