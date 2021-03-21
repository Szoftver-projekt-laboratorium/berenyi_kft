package berenyi_kft;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Skeleton {

	private static Map<Object, String> ids = new HashMap<Object, String>();

	public static void main(String args[]) {
		System.out.println("Welcome in berenyi_kft's Skeleton tester!\n ");

		System.out.println("Please choose from our test cases:\n");
		System.out.println("1: Win game");
		System.out.println("2: Move Spaceship");
		System.out.println("3: Drill Asteroid");
		System.out.println("4: Restore Resource");
		System.out.println("5: Mine Resource");
		System.out.println("6: Build AI Robot");
		System.out.println("7: Build TeleportingGatePair");
		System.out.println("8: Place TeleportingGate");
		System.out.println("9: Call Sunstorm");
		System.out.println("10: Quit");
		Scanner scan = new Scanner(System.in);
		System.out.print("My choice:");
		String choice = scan.nextLine();
		String choice2;
		while (choice.equals("10") == false) {
			switch (choice) {
			case "1":

				System.out.println("Testing Win Game.\n");

				/**
				 * Létrehozunk egy játék osztályt, és egy aszteroidát, valamint két telepest,
				 * melyeket hozzáadjuk az aszteroidához. Létrehozunk 3 nyeranyagot, és egy
				 * spaceBase recipe-t, amihez hozzáadjuk a nyersanyagokat. A telepesekhez is
				 * hozzáadunk nyersanyagokat, melyek elégségesek az űrbázis megépítéséhez. A
				 * teszt alapja az aszteroidán hívott chackSpaceBase() függvény. A teszt
				 * sikeres, ha a függvényhívás eredményeképpen meghívódik az endgame függvény,
				 * mely beállítja az 'end_game_flag'-t.
				 */

				Game game1 = new Game();

				Recipe aiRobot1 = new Recipe();
				Recipe gatePair1 = new Recipe();
				Recipe spaceBase1 = new Recipe();
				game1.addRecipe(aiRobot1);
				game1.addRecipe(gatePair1);
				game1.addRecipe(spaceBase1);

				Ice ice1 = new Ice();
				Iron iron1 = new Iron();
				Coal coal1 = new Coal();
				spaceBase1.addResource(ice1);
				spaceBase1.addResource(iron1);
				spaceBase1.addResource(coal1);

				Settler s11 = new Settler();
				ids.put(s11, "settler11");
				game1.addSettler(s11);
				s11.accept(iron1);
				s11.accept(ice1);

				Settler s12 = new Settler();
				game1.addSettler(s12);
				s12.accept(coal1);

				Asteroid a11 = new Asteroid();
				ids.put(a11, "asteroid11");
				game1.addAsteroid(a11);
				a11.setGame(game1);
				a11.accept(s11);
				s11.setPlace(a11);
				a11.accept(s12);
				s12.setPlace(a11);

				Asteroid a12 = new Asteroid();
				ids.put(a12, "asteroid12");
				game1.addAsteroid(a12);
				a12.setGame(game1);

				Asteroid a13 = new Asteroid();
				ids.put(a13, "asteroid13");
				game1.addAsteroid(a13);
				a13.setGame(game1);

				a11.accept(a12);
				a12.accept(a11);

				TeleportingGate tg11 = new TeleportingGate();
				tg11.asteroid = a11;

				TeleportingGate tg12 = new TeleportingGate();
				tg12.asteroid = a13;

				tg11.setPair(tg12);
				tg12.setPair(tg11);

				a11.checkSpaceBase();

				if (game1.end_game_flag == true) {
					System.out.println("\nWinGame Test: Successful");
				} else {
					System.out.println("WinGame Test: Failed");
				}

				break;
			case "2":
				System.out.println("Testing Move Spaceship...\n Please choose a test-case:\n");

				System.out.println("\ta: Move to asteroid\n");
				System.out.println("\tb: Move through TeleportingGate\n");
				System.out.println("\tc: Fail to use TeleportingGate\n");
				System.out.println("\td: Back to the main menu\n");

				System.out.print("\tMy choice:");
				choice2 = scan.nextLine();
				while (choice2.equals("d") == false) {

					Game game2 = new Game();

					Recipe aiRobot2 = new Recipe();
					Recipe gatePair2 = new Recipe();
					Recipe spaceBase2 = new Recipe();
					game2.addRecipe(aiRobot2);
					game2.addRecipe(gatePair2);
					game2.addRecipe(spaceBase2);

					Ice ice2 = new Ice();
					Iron iron2 = new Iron();
					Coal coal2 = new Coal();
					spaceBase2.addResource(ice2);
					spaceBase2.addResource(iron2);
					spaceBase2.addResource(coal2);

					Settler s21 = new Settler();
					ids.put(s21, "settler21");
					game2.addSettler(s21);
					s21.accept(iron2);
					s21.accept(ice2);

					Settler s22 = new Settler();
					game2.addSettler(s22);
					s22.accept(coal2);

					Asteroid a21 = new Asteroid();
					ids.put(a21, "asteroid2");
					game2.addAsteroid(a21);
					a21.setGame(game2);
					a21.accept(s21);
					s21.setPlace(a21);
					a21.accept(s22);
					s22.setPlace(a21);

					Asteroid a22 = new Asteroid();
					ids.put(a22, "asteroid22");
					game2.addAsteroid(a22);
					a22.setGame(game2);

					Asteroid a23 = new Asteroid();
					ids.put(a23, "asteroid3");
					game2.addAsteroid(a23);
					a23.setGame(game2);

					// a1 a2 szomszed
					a21.accept(a22);
					a22.accept(a21);

					TeleportingGate tg21 = new TeleportingGate();
					tg21.asteroid = a21;

					TeleportingGate tg22 = new TeleportingGate();
					tg22.asteroid = a23;

					tg21.setPair(tg22);
					tg22.setPair(tg21);

					switch (choice2) {
					case "a":
						System.out.println("\t\tMove to asteroid selected:\n");

						/**
						 * Létrehozunk két aszteroidát, melyeket beállítjuk egymás szomszédjának.
						 * Valamint létrehozunk egy telepest, akit ráállítunk az elsőként létrehozott
						 * aszteroidára, majd meghívjuk a telepes move() függvényét. A teszt sikeres, ha
						 * a telepes aszteroidáját lekérdezve a másodikként létrehozott aszteroidát
						 * kapjuk.
						 */

						int dir = 0;
						s21.move(dir);
						if (s21.getPlace() == a22) {
							s21.move(dir);
							System.out.println("\nMove to asteroid Test: Successful");

						} else {
							System.out.println("Move to asteroid Test: Failed");
						}

						break;
					case "b":
						System.out.println("\t\tMove through TeleportingGate selected:\n");

						/**
						 * Létrehozunk két aszteroidát, két teleportkaput, amiket egymás párjának
						 * beállítjuk, majd hozzáadjuk az egyes aszteroidákhoz, majd a létrehozott
						 * telepest ráállítjuk az egyik aszteroidára. A teszt sikeres, ha a telepes
						 * move() fügvényét meghívva az az adott irányban átlép a teleportkapu által
						 * szomszédos aszteroidára.
						 */

						a21.accept(tg21);
						dir = 1;
						s21.move(dir);
						boolean succes_test_flag = false;
						if (s21.getPlace() == a23) {
							succes_test_flag = true;
						}

						dir = 0;
						s21.move(dir);
						a21.remove(tg22);

						if (succes_test_flag) {
							System.out.println("\nMove through TelePortingGate Test: Successful");
						} else {
							System.out.println("Move through TelePortingGate Test: Failed");
						}

						break;
					case "c":
						System.out.println("\t\tFail to use TeleportingGate selected:\n");

						/**
						 * Létrehozunk két aszteroidát, és két teleportkaput, melyeket hozzáadjuk az
						 * aszteroidákhoz, majd eltávolítjuk az egyik teleportkaput az aszteroidájáról,
						 * akkor a telepes az adott irányban már nem tud lépni a teleportkapun keresztül
						 */

						dir = 1;
						try {
							s21.move(dir);
						} catch (Exception e) {
							System.out.println("\nFail to use TeleportingGate Test: Successful");
						}

						break;
					default:
						System.out.println("\t\tInvalid choice!\n");
					}
					break;
				}
				break;

			case "3":
				System.out.println("Testing Drill Asteroid...\n Please choose a test-case:\n");

				System.out.println("\ta: Drill one layer\n");
				System.out.println("\tb: Drill radioactive asteroid�s last layer\n");
				System.out.println("\tc: Drill icy asteroid�s last layer\n");
				System.out.println("\td: Fail to drill\n");
				System.out.println("\te: Back to the main menu\n");

				System.out.print("\tMy choice:");
				choice2 = scan.nextLine();
				while (choice2.equals("e") == false) {
					switch (choice2) {
					case "a":
						System.out.println("\t\tDrill one layer selected:\n");

						// Létrehozunk egy aszteroidát és beállítjuk a réteg vastagságát 2-re
						Asteroid asteroid1_3a = new Asteroid();
						asteroid1_3a.setRockLayerThickness(2);

						// Létrehozunk egy Settler-t és beállítjuk az aktuális aszteroidájának
						// asteroid1_3a-t
						Settler settler1_3a = new Settler();
						settler1_3a.setPlace(asteroid1_3a);

						// Meghívjuk settler1_3a drill() metódusát
						settler1_3a.drill();

						// Ha az aszteroida rétegének vastagsága egyre csökkent, akkor sikeres a teszt
						if (asteroid1_3a.getRockLayerThickness() == 1) {
							System.out.println("\nDrill one layer test: successful");
						}

						break;
					case "b":
						System.out.println("\t\tDrill radioactive asteroid's last layer selected:\n");

						// Létrehozzuk a game-t
						Game game_3b = new Game();

						// Létrehozunk két aszteroidát
						Asteroid asteroid1_3b = new Asteroid();
						Asteroid asteroid2_3b = new Asteroid();

						// Létrehozunk egy Napot és beállítjuk szomszédjának az asteroid1_3b-t
						Sun sun_3b = new Sun();
						sun_3b.addNeighbor(asteroid1_3b);

						// Hozzáadjuk a asteroid1_3b magjába az uránt és beállítjuk a réteg vastagságát
						// 1-re
						asteroid1_3b.addResource(new Uran());
						asteroid1_3b.setRockLayerThickness(1);

						// Beállítjuk az aszteroidák sun attribútumait
						asteroid1_3b.setSun(sun_3b);
						asteroid2_3b.setSun(sun_3b);

						// Beállítjuk az aszteroidák game attribútumait
						asteroid1_3b.setGame(game_3b);
						asteroid2_3b.setGame(game_3b);

						// Beállítjuk a két aszteroidát egymással szomszédosnak
						asteroid1_3b.accept(asteroid2_3b);
						asteroid2_3b.accept(asteroid1_3b);

						// Hozzáadjuk a game_3b-hez az aszteroidákat és beállítjuk a Napot
						game_3b.addAsteroid(asteroid1_3b);
						game_3b.addAsteroid(asteroid2_3b);
						game_3b.setSun(sun_3b);

						// Létrehozunk egy AIRobotot és beállítjuk aktuális aszteroidájának az
						// asteroid1_3b-t
						AIRobot robot_3b = new AIRobot();
						robot_3b.setPlace(asteroid1_3b);

						// Létrehozunk 2 Settler-t
						Settler settler1_3b = new Settler();
						Settler settler2_3b = new Settler();

						// Hozzáadjuk az aszteroidákhoz a robotot és a Settlereket
						asteroid1_3b.accept(robot_3b);
						asteroid1_3b.accept(settler1_3b);
						asteroid2_3b.accept(settler2_3b);

						// Hozzadjuk a game_3b-hez a Settlereket
						game_3b.addSettler(settler1_3b);
						game_3b.addSettler(settler2_3b);

						// Beállítjuk a Settlerek game attribútumait
						settler1_3b.setGame(game_3b);
						settler2_3b.setGame(game_3b);

						// Beállítjuk a Settlerek aktuális aszteroidáit
						settler1_3b.setPlace(asteroid1_3b);
						settler2_3b.setPlace(asteroid2_3b);

						// Meghívjuk a settler1_3b drill() metódusát
						settler1_3b.drill();

						// Ha a robot aktuális aszteroidája az asteroid2_3b és ha a
						// játék nem tartalmazza az asteroid1_3b-t,
						// illetve ha a játék nem tartalmazza a settler1_3b-t, akkor a teszt sikeres
						if (robot_3b.getPlace() == asteroid2_3b && !game_3b.getAsteroids().contains(asteroid1_3b)
								&& !game_3b.getSettlers().contains(settler1_3b))
							System.out.println("\nDrill radioactive asteroid's last layer test: successful");
						break;
					case "c":

						// Létrehozunk egy aszteroidát és egy Settler-t
						Settler settler1_3c = new Settler();
						Asteroid asteroid1_3c = new Asteroid();

						// Létrehozzuk a game_3c-t és hozzáadjuk a létrehozott aszteroidát és Settler-t
						Game game_3c = new Game();
						game_3c.addSettler(settler1_3c);
						game_3c.addAsteroid(asteroid1_3c);

						// Beállítjuk a Settler game attribútumát és az aszteroidáját
						settler1_3c.setPlace(asteroid1_3c);
						settler1_3c.setGame(game_3c);

						// Beállítjuk az aszteroida game attribútumát és hozzáadjuk a létrehozott
						// Settler-t,
						// valamint beállítjuk a réteg vastagságát 1-re
						asteroid1_3c.setGame(game_3c);
						asteroid1_3c.accept(settler1_3c);
						asteroid1_3c.setRockLayerThickness(1);

						// Létrehozzuk a Napot, melyet beállítunk a játék és az aszteroida számára
						// és hozzáadjuk szomszédos aszteroidaként az asteroid1_3c-t
						Sun sun_3c = new Sun();
						game_3c.setSun(sun_3c);
						asteroid1_3c.setSun(sun_3c);
						sun_3c.addNeighbor(asteroid1_3c);

						// Az aszteroida magjába jeget helyezünk
						asteroid1_3c.addResource(new Ice());

						// Meghívjuk a Settler drill() metódusát
						settler1_3c.drill();

						// Ha az aszteroida nem tartalmaz nyersanyagot, akkor sikeres a teszt
						if (asteroid1_3c.getResource() == null)
							System.out.println("\nDrill icy asteroid's last layer: successful");

						break;
					case "d":
						System.out.println("\t\tFail to drill selected:\n");

						// Létrehozunk egy aszteroidát és beállítjuk a réteg vastagságát 1-re
						Asteroid asteroid1_3d = new Asteroid();
						asteroid1_3d.setRockLayerThickness(0);

						// Létrehozunk egy Settler-t
						Settler settler1_3d = new Settler();

						// Beállítjuk a settler1_3d aktuális aszteroidáját és hozzáadjuk a Settler-t az
						// aszteroidához
						settler1_3d.setPlace(asteroid1_3d);
						asteroid1_3d.accept(settler1_3d);

						// Meghívjuk a Settler drill() metódusát
						settler1_3d.drill();

						// Ha az aszteroida rétegének vastagsága 0, akkor a teszt sikeres
						if (asteroid1_3d.getRockLayerThickness() == 0)
							System.out.println("\nFail to drill test: successful");

						break;
					default:
						System.out.println("\t\tInvalid choice!\n");
					}
					break;
				}
				break;

			case "4":
				System.out.println("Testing Restore Resource...\n Please choose a test-case:\n");

				System.out.println("\ta: Restore Resource\n");
				System.out.println("\tb: Fail to restore\n");
				System.out.println("\tc: Restore Ice when Sun is close\n");
				System.out.println("\td: Restore Uran when Sun is close\n");
				System.out.println("\te: Back to the main menu\n");

				System.out.print("\tMy choice:");
				choice2 = scan.nextLine();
				while (choice2.equals("e") == false) {
					switch (choice2) {
					case "a":
						System.out.println("\t\tRestore Resource selected:\n");

						/*
						 * Létrehozunk egy Settler-t, amelyhez hozzáadunk egy szenet. Létrehozunk egy
						 * aszteroidát és a réteg vastagságát beállítjuk 0-ra. Meghívjuk a Settler
						 * restore() függvényét és ha az aszteroida magjába bekerül a nyersanyag, akkor
						 * sikeres a teszt.
						 */
						Game game4 = new Game();

						Settler s4 = new Settler();
						game4.addSettler(s4);
						Asteroid a4 = new Asteroid();
						game4.addAsteroid(a4);
						a4.setGame(game4);
						Resource r4 = new Coal();
						s4.accept(r4);

						a4.accept(s4);
						s4.setPlace(a4);

						a4.setRockLayerThickness(0);

						Sun sun4 = new Sun();
						// sun4.addNeighbor(a4);

						a4.setSun(sun4);
						s4.restore(r4);

						if (a4.getResource().equals(r4)) {
							System.out.println("\nRestore resource test: successful!\n");
						}
						break;
					case "b":
						System.out.println("\t\tFail to restore selected:\n");

						/*
						 * Létrehozunk egy egy Settler-t, amelyhez hozzáadunk egy szenet. Létrehozunk
						 * egy aszteroidát és a réteg vastagságát beállítjuk 1-re. Meghívjuk a Settler
						 * restore() függvényét és ha a Settler még mindig tárolja a nyersanyagot, tehát
						 * nem sikerült az aszteroida magjába helyezni a réteg vastagsága miatt, akkor
						 * sikeres a teszt.
						 */
						Game game41 = new Game();

						Settler s41 = new Settler();
						game41.addSettler(s41);
						Asteroid a41 = new Asteroid();
						game41.addAsteroid(a41);
						a41.setGame(game41);
						Resource r41 = new Coal();
						s41.accept(r41);

						a41.accept(s41);
						s41.setPlace(a41);
						a41.setRockLayerThickness(1);
						Sun sun41 = new Sun();
						// sun4.addNeighbor(a4);

						a41.setSun(sun41);

						s41.restore(r41);

						if (s41.getCollectedResources().isEmpty() == false) {
							System.out.println("Fail to restore test: successful");
						}
						break;
					case "c":
						System.out.println("\t\tRestore Ice when Sun is close selected:\n");

						/*
						 * Létrehozunk egy Settler-t és egy aszteroidát, amely épp napközelben van,
						 * rétegvastagsága 0 és üreges. Beállítjuk a Settler aktuális aszteroidájának a
						 * létrehozott aszteroidát és hozzáadunk a Settler nyersanyagaihoz egy jeget.
						 * Meghívjuk a Settler restore(r: Resource) függvényét. Ha az aszteroida
						 * továbbra is üreges, tehát nem tárol nyersanyagot, mert a jég vízzé
						 * szublimált, akkor sikeres a teszt.
						 */

						Game game411 = new Game();

						Settler s411 = new Settler();
						game411.addSettler(s411);
						Asteroid a411 = new Asteroid();
						game411.addAsteroid(a411);
						a411.setGame(game411);
						Resource r411 = new Ice();

						a411.accept(s411);
						s411.setPlace(a411);
						a411.setRockLayerThickness(0);
						Sun sun411 = new Sun();

						a411.setSun(sun411);
						sun411.addNeighbor(a411);

						s411.restore(r411);

						if (a411.getResource() == null) {
							System.out.println("\nRestore Ice when Sun is close test: successful");
						}
						break;
					case "d":
						System.out.println("\t\tRestore Uran when Sun is close selected:\n");

						/*
						 * Létrehozunk egy Settler-t és egy aszteroidát, amely épp napközelben van,
						 * rétegvastagsága 0 és üreges. Beállítjuk a Settler aktuális aszteroidájának a
						 * létrehozott aszteroidát és hozzáadunk a Settler-hez egy uránt, amely
						 * radioaktív. Meghívjuk a Settler restore(r: Resource) függvényét. Ha az
						 * aszteroida felrobban és ezáltal törlődik a játékból, akkor sikeres a teszt.
						 */

						Game game4111 = new Game();

						Settler s4111 = new Settler();
						game4111.addSettler(s4111);
						s4111.setGame(game4111);
						Asteroid a4111 = new Asteroid();
						game4111.addAsteroid(a4111);
						a4111.setGame(game4111);
						Resource r4111 = new Uran();

						a4111.accept(s4111);
						s4111.setPlace(a4111);
						a4111.setRockLayerThickness(0);
						Sun sun4111 = new Sun();

						a4111.setSun(sun4111);
						sun4111.addNeighbor(a4111);

						s4111.restore(r4111);

						if (game4111.getAsteroids().isEmpty()) {
							System.out.println("\nRestore Uran when Sun is close test: successful");
						}
						break;
					default:
						System.out.println("\t\tInvalid choice!\n");
					}
					break;
				}
				break;

			case "5":
				System.out.println("Testing Mine Resource...\n Please choose a test-case:\n");

				System.out.println("\ta: Mine Resource\n");
				System.out.println("\tb: Fail to mine\n");
				System.out.println("\tc: Back to the main menu\n");

				System.out.print("\tMy choice:");
				choice2 = scan.nextLine();
				while (choice2.equals("c") == false) {
					switch (choice2) {
					case "a":
						System.out.println("\t\tMine Resource selected:\n");

						/*
						 * Létrehozunk egy Settler-t és egy aszteroidát, amelynek a rétegvastagsága 0, és
						 * eltárol egy szenet, továbbá létrehozzuk az űrbázis receptjét. Meghívjuk a
						 * Settler mine() metódusát, ha a Settler ezután eltárolja a az aszteroidába
						 * helyezett szenet és az aszteroida magja üres, akkor sikeres a teszt.
						 */

						Game game5 = new Game();

						Settler s5 = new Settler();
						// ids.put(s1, "settler1");
						game5.addSettler(s5);
						Asteroid a5 = new Asteroid();
						game5.addAsteroid(a5);
						a5.setGame(game5);
						// ids.put(a1,"asteroid1");
						Resource r5 = new Coal();
						a5.addResource(r5);
						a5.accept(s5);
						s5.setPlace(a5);

						a5.setRockLayerThickness(0);
						Recipe spaceBase5 = new Recipe();
						spaceBase5.addResource(new Ice());
						spaceBase5.addResource(new Iron());
						spaceBase5.addResource(new Coal());

						game5.addRecipe(new Recipe());
						game5.addRecipe(new Recipe());
						game5.addRecipe(spaceBase5);

						s5.mine();

						if (s5.getCollectedResources().contains(r5) && a5.isMined()) {
							System.out.println("\nMine resource test: successful");
						}
						break;
					case "b":
						System.out.println("\t\tFail to mine selected:\n");
						
						/*
						 * Létrehozunk egy Settler-t és egy aszteroidát, amelynek a rétegvastagsága 1,
						 * és eltárol egy szenet, továbbá létrehozzuk az űrbázis receptjét.
						 * Meghívjuk a Settler mine() metódusát; ha az aszteroida ezek után nincsen
						 * kibányászva, akkor sikeres a teszt.
						 */

						Game game5b = new Game();

						Settler s5b = new Settler();
						// ids.put(s1, "settler1");
						game5b.addSettler(s5b);
						Asteroid a5b = new Asteroid();
						game5b.addAsteroid(a5b);
						a5b.setGame(game5b);
						// ids.put(a1,"asteroid1");
						Resource r5b = new Coal();
						a5b.addResource(r5b);
						a5b.accept(s5b);
						s5b.setPlace(a5b);

						a5b.setRockLayerThickness(1);

						Recipe spaceBase5b = new Recipe();
						spaceBase5b.addResource(new Ice());
						spaceBase5b.addResource(new Iron());
						spaceBase5b.addResource(new Coal());

						game5b.addRecipe(new Recipe());
						game5b.addRecipe(new Recipe());
						game5b.addRecipe(spaceBase5b);

						s5b.mine();

						if (a5b.isMined() == false) {
							System.out.println("\nFail to mine test: successful");
						}
						break;

					default:
						System.out.println("\t\tInvalid choice!\n");
					}
					break;
				}
				break;

			case "6":
				System.out.println("Testing Build AI Robot...\n Please choose a test-case:\n");

				System.out.println("\ta: Build robot\n");
				System.out.println("\tb: Fail to build robot\n");
				System.out.println("\tc: Back to the main menu\n");

				System.out.print("\tMy choice:");
				choice2 = scan.nextLine();
				while (choice2.equals("c") == false) {
					switch (choice2) {
					case "a":
						System.out.println("\t\tBuild robot selected:\n");

						/*
						 * Létrehozzuk a robot elkészítéséhez szükséges receptet, amely nyersanyagokat
						 * tartalmaz és létrehozunk egy Settler-t, amelyhez hozzáadjuk az AIRobot
						 * megépítéséhez szükséges nyersanyagokat. Meghívjuk a Settler createAIRobot()
						 * metódusát, ha ketten vannak a Settler aszteroidáján, továbbá ha a Settler
						 * által eltárolt nyersanyagok elfogytak és a Timerhez hozzáadódott egy
						 * Steppable, akkor sikeres a teszt.
						 */
						Game game_6a = new Game();
						Timer timer_6a = new Timer(1000, 20);
						game_6a.setTimer(timer_6a);
						Recipe robotRecipe_6a = new Recipe();
						game_6a.addRecipe(robotRecipe_6a);
						robotRecipe_6a.addResource(new Coal());
						robotRecipe_6a.addResource(new Iron());
						robotRecipe_6a.addResource(new Uran());
						Settler s_6a = new Settler();
						game_6a.addSettler(s_6a);
						s_6a.setGame(game_6a);
						s_6a.accept(new Coal());
						s_6a.accept(new Iron());
						s_6a.accept(new Uran());
						Asteroid a_6a = new Asteroid();
						s_6a.setPlace(a_6a);
						a_6a.accept(s_6a);

						s_6a.createAIRobot();
						if (a_6a.getCharacters().size() == 2 & s_6a.getCollectedResources().size() == 0
								& timer_6a.getSteppables().contains(a_6a.getCharacters().get(1)))
							System.out.println("\nBuild AI Robot: successful");
						break;
					case "b":
						System.out.println("Fail to build robot selected:");

						/*
						 * Létrehozzuk a robot elkészítéséhez szükséges receptet, amely nyersanyagokat
						 * tartalmaz és létrehozunk egy Settler-t, amelyhez hozzáadunk további
						 * nyersanyagokat. Meghívjuk a Settler createAIRobot() metódusát, ha a Settler
						 * egyedül van az aszteroidán és továbbra is eltárolja a nyersanyagokat, akkor
						 * sikeres a teszt.
						 */

						Game game_6b = new Game();
						Timer timer_6b = new Timer(1000, 20);
						game_6b.setTimer(timer_6b);
						Recipe robotRecipe_6b = new Recipe();
						game_6b.addRecipe(robotRecipe_6b);
						robotRecipe_6b.addResource(new Coal());
						robotRecipe_6b.addResource(new Iron());
						robotRecipe_6b.addResource(new Uran());
						Settler s_6b = new Settler();
						game_6b.addSettler(s_6b);
						s_6b.setGame(game_6b);
						s_6b.accept(new Uran());
						s_6b.accept(new Ice());
						Asteroid a_6b = new Asteroid();
						s_6b.setPlace(a_6b);
						a_6b.accept(s_6b);

						s_6b.createAIRobot();
						if (a_6b.getCharacters().size() == 1 & s_6b.getCollectedResources().size() == 2)
							System.out.println("\nFail to build robot: successful");
						break;

					default:
						System.out.println("\t\tInvalid choice!\n");
					}
					break;
				}
				break;
			case "7":

				System.out.println("Testing Build TeleportingGate pair...\n Please choose a test-case:\n");

				System.out.println("	a: Build TeleportingGate pair\n");
				System.out.println("	b: Fail to build TeleportingGate pair\n");
				System.out.println("	c: Back to the main menu selected:\n");

				System.out.print("\tMy choice:");
				choice2 = scan.nextLine();
				while (choice2.equals("c") == false) {
					switch (choice2) {
					case "a":
						System.out.println("\t\tBuild TeleportingGate pair selected:\n");

						/*
						 * Létrehozzuk a teleportkapu-pár elkészítéséhez szükséges receptet, amely
						 * nyersanyagokat tartalmaz és létrehozunk egy Settler-t, amelyhez hozzáadjuk a
						 * teleportkapuk megépítéséhez szükséges nyersanyagokat. Meghívjuk a Settler
						 * createGatePair() metódusát, ha a Settlernél lévő teleportkapuk száma 2 és a
						 * Settler nyersanyagai elfogytak, akkor sikeres a teszt.
						 */

						Game game_7a = new Game();
						Recipe gatePairRecipe_7a = new Recipe();
						game_7a.addRecipe(new Recipe());
						game_7a.addRecipe(gatePairRecipe_7a);
						gatePairRecipe_7a.addResource(new Iron());
						gatePairRecipe_7a.addResource(new Iron());
						gatePairRecipe_7a.addResource(new Ice());
						gatePairRecipe_7a.addResource(new Uran());
						Settler s_7a = new Settler();
						game_7a.addSettler(s_7a);
						s_7a.setGame(game_7a);
						s_7a.accept(new Ice());
						s_7a.accept(new Iron());
						s_7a.accept(new Uran());
						s_7a.accept(new Iron());

						s_7a.createGatePair();
						if (s_7a.getGatesCreated().size() == 2
								& (s_7a.getGatesCreated().get(0).getPair() == s_7a.getGatesCreated().get(1))
								& s_7a.getCollectedResources().size() == 0)
							System.out.println("\nBuild TeleportingGate pair: successful");
						break;

					case "b":
						System.out.println("\t\tFail to build TeleportingGate pair selected:\n");

						/*
						 * Létrehozzuk a teleportkapu-pár elkészítéséhez szükséges receptet, amely
						 * nyersanyagokat tartalmaz és létrehozunk egy Settler-t, amelyhez hozzáadunk
						 * egy teleportkaput, illetve nyersanyagot. Meghívjuk a Settler createGatePair()
						 * metódusát, ha a Settlernél lévő teleportkapuk száma továbbra is 1 és a
						 * Settlernek megmaradt a nyersanyaga , akkor sikeres a teszt.
						 */

						Game game_7b = new Game();
						Recipe gatePairRecipe_7b = new Recipe();
						game_7b.addRecipe(new Recipe());
						game_7b.addRecipe(gatePairRecipe_7b);
						gatePairRecipe_7b.addResource(new Iron());
						gatePairRecipe_7b.addResource(new Iron());
						gatePairRecipe_7b.addResource(new Ice());
						gatePairRecipe_7b.addResource(new Uran());
						Settler s_7b = new Settler();
						game_7b.addSettler(s_7b);
						s_7b.setGame(game_7b);
						s_7b.accept(new TeleportingGate());
						s_7b.accept(new Iron());

						s_7b.createGatePair();
						if (s_7b.getGatesCreated().size() == 1 & s_7b.getCollectedResources().size() == 1)
							System.out.println("\nFail to build TeleportingGate pair: successful");
						break;

					default:
						System.out.println("\t\tInvalid choice!\n");
					}
					break;
				}
				break;
			case "8":
				System.out.println("Testing Place TeleportingGate...\n Please choose a test-case:\n");

				System.out.println("\ta: Place TeleportingGate\n");
				System.out.println("\tb: Fail to place TeleportingGate\n");
				System.out.println("\tc: Back to the main menu\n");

				System.out.print("\tMy choice:");
				choice2 = scan.nextLine();
				while (choice2.equals("c") == false) {
					switch (choice2) {
					case "a":
						System.out.println("\t\tPlace TeleportingGate selected:\n");

						/*
						 * Létrehozunk két aszteroidát, egy Settler-t és két teleportkaput, amelyek
						 * egymásnak a párjai. A két teleportkapuból az egyiket elhelyezzük az a1_8a
						 * aszteroidán, a másikat pedig a Settlernél, aki az a2_8a aszteroidán
						 * helyezkedik el. Meghívjuk a Settler releaseGate() metódusát, ha a két
						 * aszteroida egymásnak a szomszédjai a teleportkapuk által, akkor sikeres a
						 * teszt.
						 */

						Settler s1_8a = new Settler();
						Asteroid a1_8a = new Asteroid();
						Asteroid a2_8a = new Asteroid();
						TeleportingGate tg1_8a = new TeleportingGate();
						TeleportingGate tg2_8a = new TeleportingGate();

						tg1_8a.setPair(tg2_8a);
						tg2_8a.setPair(tg1_8a);

						a1_8a.accept(tg1_8a);
						tg1_8a.setAsteroid(a1_8a);

						s1_8a.accept(tg2_8a);
						a2_8a.accept(s1_8a);
						s1_8a.setPlace(a2_8a);

						s1_8a.releaseGate();

						if (a1_8a.getNeighbors().contains(a2_8a))
							System.out.println("\nPlace teleportingGate test: successful");
						break;
					case "b":

						/*
						 * Létrehozunk két aszteroidát, egy Settler-t, amely a Settler aktuális
						 * aszteroidája, majd pedig meghívjuk a Settler releaseGate() metódusát. Ha az
						 * aszteroidán lévő teleportkapuk száma 0, akkor sikeres a teszt.
						 */

						System.out.println("\t\tFail to place TeleportingGate selected:\n");

						Settler s1_8b = new Settler();
						Asteroid a1_8b = new Asteroid();
						a1_8b.accept(s1_8b);
						s1_8b.setPlace(a1_8b);

						s1_8b.releaseGate();

						if (a1_8b.getGates().size() == 0)
							System.out.println("\nFail to place teleportingGate test: successful");
						break;

					default:
						System.out.println("\t\tInvalid choice!\n");
					}
					break;
				}
				break;
			case "9":
				System.out.println("Testing Call Sunstorm...\n Please choose a test-case:\n");

				System.out.println("\ta: Asteroid is mined\n");
				System.out.println("\tb: Asteroid is not mined\n");
				System.out.println("\tc: Back to the main menu\n");

				System.out.print("\tMy choice:");
				choice2 = scan.nextLine();
				while (choice2.equals("c") == false) {
					switch (choice2) {
					case "a":
						System.out.println("\t\tAsteroid is mined selected:\n");

						/*
						 * Létrehozunk egy Settler-t, egy aszteroidát, amelynek a rétegvastagsága 0,
						 * továbbá egy AIRobotot és egy Napot. Meghívjuk a Sun step() metódusát, ezáltal
						 * megvalósítva a napvihart. A teszt sikeres, ha a játékban megtalálható
						 * játékosok száma nem 0 a napvihar után.
						 */

						Asteroid a1_9a = new Asteroid();
						Settler s1_9a = new Settler();
						AIRobot r_9a = new AIRobot();
						Game g_9a = new Game();
						Sun sun_9a = new Sun();
						// az aszteroidának beállítgatjuk a dolgokat
						a1_9a.accept(r_9a);
						a1_9a.accept(s1_9a);
						a1_9a.setSun(sun_9a);
						a1_9a.setGame(g_9a);
						// a settlernek is beállítjuk
						s1_9a.setPlace(a1_9a);
						s1_9a.setGame(g_9a);
						// az airoboton is beallitjuk az aszteriodajat
						r_9a.setPlace(a1_9a);
						// a gamehez hozzaadjuk a dolgokat
						g_9a.addAsteroid(a1_9a);
						g_9a.addSettler(s1_9a);
						g_9a.setSun(sun_9a);
						// sunhoz hozzaadjuk az aszteroidat
						sun_9a.addNeighbor(a1_9a);
						// aszteroida legyen kibányászott
						a1_9a.setRockLayerThickness(0);
						// eddig tartott az init

						sun_9a.step();

						if (a1_9a.getCharacters() != null)
							System.out.println("\nSunstorm on mined asteroid test: successful");
						break;
					case "b":
						System.out.println("\t\tAsteroid is not mined selected:\n");

						/*
						 * Létrehozunk egy Settler-t, egy aszteroidát, amelynek a rétegvastagsága 5,
						 * továbbá egy AIRobotot és egy Napot. Meghívjuk a Sun step() metódusát, ezáltal
						 * megvalósítva a napvihart. A teszt sikeres, ha a játékban megtalálható
						 * játékosok száma 0 a napvihar után.
						 */

						Asteroid a1_9b = new Asteroid();
						Settler s1_9b = new Settler();
						AIRobot r_9b = new AIRobot();
						Game g_9b = new Game();
						Sun sun_9b = new Sun();

						a1_9b.accept(r_9b);
						a1_9b.accept(s1_9b);
						a1_9b.setSun(sun_9b);
						a1_9b.setGame(g_9b);

						s1_9b.setPlace(a1_9b);
						s1_9b.setGame(g_9b);

						r_9b.setPlace(a1_9b);

						g_9b.addAsteroid(a1_9b);
						g_9b.addSettler(s1_9b);
						g_9b.setSun(sun_9b);

						sun_9b.addNeighbor(a1_9b);
						a1_9b.setRockLayerThickness(5);

						sun_9b.step();

						if (g_9b.getSizeOfSettlersAlive() == 0)
							System.out.println("\nsunstorm on not mined asteroid test: successful");
						break;

					default:
						System.out.println("\t\tInvalid choice!\n");
					}
					break;
				}
				break;

			default:
				System.out.println("Invalid input!\n");

			}
			System.out.println("\n\nTesting ended, please choose another test (1-9) or finish the testing (10).\n\n");
			System.out.println("Please choose from our test cases:\n");
			System.out.println("1: Win game");
			System.out.println("2: Move Spaceship");
			System.out.println("3: Drill Asteroid");
			System.out.println("4: Restore Resource");
			System.out.println("5: Mine Resource");
			System.out.println("6: Build AI Robot");
			System.out.println("7: Build TeleportingGatePair");
			System.out.println("8: Place TeleportingGate");
			System.out.println("9: Call Sunstorm");
			System.out.println("10: Quit");
			System.out.print("My choice:");
			choice = scan.nextLine();
		}
		System.out.println("Exit...");
		scan.close();
		// System.exit(0);
	}
}
