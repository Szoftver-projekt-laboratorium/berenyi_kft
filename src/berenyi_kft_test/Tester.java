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
import berenyi_kft.TeleportingGate;
import berenyi_kft.Timer;

/**
 * Tesztelest vegzo segedosztaly a prototipus programhoz
 * 
 * @author berenyi_kft
 */
public class Tester {
	// 1-tol 38-ig terjednek a tesztesetek sorszamai
	private static final int testCount = 38;

	// A tesztkonyvtar eleresi utja
	private static String path = "C:\\Users\\cloud\\berenyi_kft\\src\\test_data\\";

	/**
	 * Soronkent osszehasonlitja a ket szovegfajl tartalmat. Ha a ket fajl
	 * karakterre megegyezik, akkor ezt kiirja a kepernyore es igazzal ter vissza.
	 * Egyebkent az elso eltero sorpart kiirja egymas ala a kepernyore, es hamissal
	 * ter vissza.
	 * 
	 * @param fpath1 Az elso fajl eleresi utja szovegesen
	 * @param fpath2 A masodik fajl eleresi utja szovegesen
	 * @return <code>true</code>, ha a ket fajl tartalma azonos, kulonben
	 *         <code>false</code>
	 * @throws IOException Akkor dobodik, ha barmelyik fajl megnyitasa sikertelen
	 *                     volt.
	 */
	public static boolean compareFiles(String fpath1, String fpath2) throws IOException {
		int lineNum = 0;
		Scanner input1 = new Scanner(new File(fpath1));
		Scanner input2 = new Scanner(new File(fpath2));

		String line1, line2;
		while (input1.hasNextLine() && input2.hasNextLine()) {
			lineNum++;
			line1 = input1.nextLine();
			line2 = input2.nextLine();

			if (!line1.equals(line2)) {
				System.out.println("Differences found in line "
						+ lineNum + " :\n" + line1 + '\n' + line2);
				return false;
			}
		}
		System.out.println("The compared files' contents are the same.\n\n"
				+ "\t\t***The test is successful.***");
		return true;
	}

	/**
	 * Metodus a tesztspecifikus muvelet elvegzesehez.
	 * 
	 * @param controller A jatekot iranyito vezerlo
	 */
	private static void executeTestSpecificCommand(Controller controller, int testNum) {
		// Minden jatekos inditotta muveletet eles jatekban a Controller kezdemenyez.
		Player actPlayer = null;
		if (controller != null)
			actPlayer = controller.getActPlayer();
		else {
			actPlayer = (Player) Proto.getObject("p1"); // az elso szamu jatekos
		}

		if (testNum == 1) {
			if (actPlayer != null) {
				Object[] params = { "pass" };
				actPlayer.actOnSettler(PlayerCommand.PASS, params);
			}
		} else if (testNum == 2 | (testNum >= 4 & testNum <= 7)) {
			if (actPlayer != null) {
				Object[] params = { "move", "0" };
				actPlayer.actOnSettler(PlayerCommand.MOVE, params);
			}
		} else if (testNum == 3 || testNum >= 17 && testNum <= 20) {
			if (actPlayer != null) {
				Object[] params = { "mine" };
				actPlayer.actOnSettler(PlayerCommand.MINE, params);
			}
		} else if (testNum >= 8 && testNum <= 11 || testNum == 36) {
			if (actPlayer != null) {
				Object[] params = { "drill" };
				actPlayer.actOnSettler(PlayerCommand.DRILL, params);
			}
		} else if (testNum == 13 || testNum == 15) {
			if (actPlayer != null) {
				Object[] params = { "restore", (Resource) Proto.getObject("ur1") };
				actPlayer.actOnSettler(PlayerCommand.RESTORE, params);
			}
		} else if (testNum == 12 || testNum == 14) {
			if (actPlayer != null) {
				Object[] params = { "restore", (Resource) Proto.getObject("co1") };
				actPlayer.actOnSettler(PlayerCommand.RESTORE, params);
			}
		} else if (testNum == 16) {
			if (actPlayer != null) {
				Object[] params = { "restore", (Resource) Proto.getObject("ic1") };
				actPlayer.actOnSettler(PlayerCommand.RESTORE, params);
			}
		} else if (testNum == 21 || testNum == 22) {
			if (actPlayer != null) {
				Object[] params = { "create_robot" };
				actPlayer.actOnSettler(PlayerCommand.CREATE_ROBOT, params);
			}
		} else if (testNum >= 23 && testNum <= 25) {
			if (actPlayer != null) {
				Object[] params = { "create_gate_pair" };
				actPlayer.actOnSettler(PlayerCommand.CREATE_GATE_PAIR, params);
			}
		} else if (testNum == 26) {
			if (actPlayer != null) {
				Object[] params = { "release_gate", (TeleportingGate) Proto.getObject("tg1") };
				actPlayer.actOnSettler(PlayerCommand.RELEASE_GATE, params);
			}
		} else if (testNum == 27) {
			if (actPlayer != null) {
				Object[] params = { "release_gate", (TeleportingGate) Proto.getObject("tg2") };
				actPlayer.actOnSettler(PlayerCommand.RELEASE_GATE, params);
			}
		} else if (testNum >= 28 & testNum <= 35 || testNum == 37 || testNum == 38) {
			/* A start parancs inditja a tesztet, egy ISteppable lep magatol. */
			if (controller != null) {
				Timer timer = controller.getGame().getTimer();
				timer.tick();
			} else {
				Timer timer = (Timer) Proto.getObject("timer");
				timer.tick();
			}
		}

		if (controller != null)
			controller.nextPlayer();
	}

	/**
	 * Vegrehajtja a testNum sorszamu tesztet. Ha a bemeneti (.in) es elvart
	 * kimeneti (.out) fajlok nem allnak rendelkezesre a futtatott program
	 * mappajaban, kivetel keletkezik. A teszteset eredmenye egy .result
	 * kiterjesztesu kimeneti fajlba irodik, amelyet a compare() metodusban hasonlit
	 * ossze az elvart kimenettel.
	 * 
	 * @param testNum
	 */
	public static boolean testOne(int testNum) {
		if (testNum < 1 | testNum > testCount) {
			System.out.println("Please give a test number between 1 and " + testCount + ".");
			return false;
		}

		String testName = "test_" + Integer.toString(testNum);
		String inputName = Tester.path + "test_inputs\\" + testName + ".in";
		String resultName = Tester.path + "test_results\\" + testName + ".result";
		String outputName = Tester.path + "test_outputs\\" + testName + ".out";

		boolean isSuccessful = false;

		try {
			System.out.println("\t// ----- berenyi_kft's test "
					+ testNum + " started. ----- //");
			Proto.setRandom(false);
			Proto.enableLogging(true);
			Proto.load(inputName);
			System.out.println("Configuration loaded from " + inputName + ".");

			Controller controller = Proto.getAllObjects().getController();
			if (controller != null)
				controller.setState(State.RUNNING);

			if (Proto.isLogging())
				System.out.println("\n\t// ----- Called methods logging started: --- //");
			executeTestSpecificCommand(controller, testNum);
			if (Proto.isLogging())
				System.out.println("\t// ------- Method logging ends here. ------- //\n");

			if (controller != null)
				controller.setState(State.PAUSED);

			Proto.save(resultName);
			System.out.println("Configuration saved to " + resultName + ".");
			System.out.println();
			System.out.println("Comparing result (" + resultName + ")\n"
							+ "to expected output (" + outputName + "):\n");

			isSuccessful = Tester.compareFiles(resultName, outputName);
			System.out.println("\t// ----- berenyi_kft's test "
					+ testNum + " ended. ------ //");
			System.out.println();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return isSuccessful;
	}

	/**
	 * Sorban vegrehajtja az osszes tesztesetet az elsotol a Tester.testCount
	 * valtozoval jelolt utolsoig.
	 */
	public static void testAll() {
		int counter = 0;
		for (int i = 1; i <= Tester.testCount; i++) {
			if (testOne(i)) {
				counter++;
			} else {
				System.out.println("Test number " + i + " failed");
			}
			System.out.println("\n\n");
		}
		System.out.println("Number of successful tests: " + counter);
		System.out.println("Number of failed tests: " + (testCount - counter));
	}

	/**
	 * A tesztelest vegzo programresz belepesi pontja. A standard bemeneten test
	 * <test_num>, test, illetve exit parancsokra figyel, soronkent feldolgozva
	 * azokat.
	 * 
	 * @param args A proto program altal atadott parancssori argumentumok
	 * @throws IOException
	 */
	public static void testerMain(String[] args) throws IOException {
		System.out.println("Here you can test our prototype program by predefined test cases.\n"
				+ "---------------------------------------------------------------------------" + "--------\n"
				+ "List of the applicable test control commands:\n"
				+ "\ttest <test_num>\t\tExecutes test number <test_num> and prints the result.\n"
				+ "\ttest\t\t\tRuns all the test from number 1 to " + testCount + ".\n"
				+ "\texit\t\t\tExits from this test program.\n");

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
				// System.exit(0);
				break;

			default:
				System.out.println("Invalid operation, please " + "type in a new command.");
				break;
			}
		}
		sc.close();
	}
}
