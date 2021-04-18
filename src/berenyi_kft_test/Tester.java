package berenyi_kft_test;

import java.io.File;
import java.io.IOException;
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
	// 1-tol 38-ig terjednek a tesztesetek sorszamai
	private static final int testCount = 38;
	
	// A tesztkonyvtar eleresi utja
	private static String path = "src\\test_data\\";
	
	/**
	 * Soronkent osszehasonlitja a ket szovegfajl tartalmat.
	 * Ha a ket fajl karakterre megegyezik, akkor ezt kiirja a kepernyore
	 * es igazzal ter vissza. Egyebkent az elso eltero sorpart kiirja
	 * egymas ala a kepernyore, es hamissal ter vissza.
	 * @param fpath1 Az elso fajl eleresi utja szovegesen
	 * @param fpath2 A masodik fajl eleresi utja szovegesen
	 * @return <code>true</code>, ha a ket fajl tartalma azonos,
	 * 		   kulonben <code>false</code>
	 * @throws IOException Akkor dobodik, ha barmelyik fajl
	 * 		   megnyitasa sikertelen volt.
	 */
	public static boolean compareFiles(String fpath1, String fpath2)
														throws IOException {
		int lineNum = 0;
		Scanner input1 = new Scanner(new File(fpath1));
		Scanner input2 = new Scanner(new File(fpath2));
		
		String line1, line2;
		while(input1.hasNextLine() && input2.hasNextLine()) {
			lineNum++;
			line1 = input1.nextLine();   
			line2 = input2.nextLine();  

		    if (!line1.equals(line2)) {
		        System.out.println("Differences found in line "
		        			+ lineNum + " :\n" + line1 + '\n' + line2);
		        return false;
		    }
		}
		System.out.println("The compared files' contents are the same.");
		return true;
	}
	
	/**
	 * Metodus a tesztspecifikus muvelet elvegzesehez.
	 * @param controller A jatekot iranyito vezerlo
	 */
	private static void executeTestSpecificCommand(Controller controller, int testNum) {
		// Minden jatekos inditotta muveletet eles jatekban a Controller kezdemenyez.
		Player actPlayer = null;
		if (controller != null)
			actPlayer = controller.getActPlayer();
		else {
			actPlayer = (Player) Proto.getObject("p1");
		}
		
		if (testNum == 1) {
			if(actPlayer!=null) {
				Object[] params = { "pass" };
				actPlayer.actOnSettler(PlayerCommand.PASS, params);
			}
		} else if (testNum == 2 | (testNum >= 4 & testNum<=7)) {
			if(actPlayer!=null) {
				Object[] params = { "move", "0" };
				actPlayer.actOnSettler(PlayerCommand.MOVE, params);
			}
		} else if (testNum==3||testNum==17) {
			if(actPlayer!=null) {
				Object[] params = { "mine" };
				actPlayer.actOnSettler(PlayerCommand.MINE, params);
			}
		} else if(testNum >= 8 && testNum <= 11) {
			if(actPlayer!=null) {
				Object[] params = { "drill" };
				actPlayer.actOnSettler(PlayerCommand.DRILL, params);
			}
		} else if (testNum == 13) {
			if(actPlayer!=null) {
				Object[] params = { "restore", (Resource) Proto.getObject("ur1") };
				actPlayer.actOnSettler(PlayerCommand.RESTORE, params);
			}
		} else if (testNum >= 29 & testNum <= 35) {
			/* A start parancs inditja a tesztet, egy ISteppable lep magatol. */
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
		String outputName = Tester.path + "test_outputs\\" + testName + ".out";
		
		try {
			System.out.println("// -----  berenyi_kft's test "
								+ testNum + " started. ----- //");
			Proto.setRandom(true);
			Proto.enableLogging(true);
			Proto.load(inputName);
			System.out.println("Configuration loaded from " + inputName + ".");
			
			Controller controller = Proto.getAllObjects().getController();
			if (controller != null)
				controller.setState(State.RUNNING);
			
			if (Proto.isLogging())
				System.out.println("// ----- Called methods log started: ----- //");
			executeTestSpecificCommand(controller, testNum);
			if (Proto.isLogging())
				System.out.println("// ----- Method log ends here. ----- //");
			
			if (controller != null)
				controller.setState(State.PAUSED);
			
			Proto.save(resultName);
			System.out.println("Configuration saved to " + resultName + ".");
			System.out.println();
			System.out.println("Comparing result (" + resultName
					+ ")\nto expected output (" + outputName +"):");
			Tester.compareFiles(resultName, outputName);
			System.out.println("// ----- berenyi_kft's test number "
									+ testNum + " ended.  ----- //");
			System.out.println();
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
		for (int i = 1; i <= Tester.testCount; i++) {
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
