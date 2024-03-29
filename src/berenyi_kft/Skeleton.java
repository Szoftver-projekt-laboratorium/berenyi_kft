package berenyi_kft;

import java.util.Scanner;

/* A prototipus programban mar nincs szuksegunk erre az osztalyra. */
/**
 * Szkeleton osztaly a jatek analizis modelljenek tesztelesehez
 * @author berenyi_kft
 */
public class Skeleton {
	
	// private static Map<Object, String> ids = new HashMap<Object, String>();

	public static void main(String args[]) {
		System.out.println("\tWelcome in berenyi_kft's Skeleton tester!\n ");

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
				 * Letrehozunk egy jatek osztalyt, es egy aszteroidat, valamint ket telepest,
				 * melyeket hozzaadjuk az aszteroidahoz. Letrehozunk 3 nyeranyagot, es egy
				 * spaceBase recipe-t, amihez hozzaadjuk a nyersanyagokat. A telepesekhez is
				 * hozzaadunk nyersanyagokat, melyek elegsegesek az urbazis megepitesehez. A
				 * teszt alapja az aszteroidan hivott chackSpaceBase() fuggveny. A teszt
				 * sikeres, ha a fuggvenyhivas eredmenyekeppen meghivodik az endgame fuggveny,
				 * mely beallitja az 'endGameFlag'-et.
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
				// ids.put(s11, "settler11");
				game1.addSettler(s11);
				s11.accept(iron1);
				s11.accept(ice1);

				Settler s12 = new Settler();
				game1.addSettler(s12);
				s12.accept(coal1);

				Asteroid a11 = new Asteroid();
				// ids.put(a11, "asteroid11");
				game1.addAsteroid(a11);
				a11.setGame(game1);
				a11.accept(s11);
				s11.setPlace(a11);
				a11.accept(s12);
				s12.setPlace(a11);

				Asteroid a12 = new Asteroid();
				// ids.put(a12, "asteroid12");
				game1.addAsteroid(a12);
				a12.setGame(game1);

				Asteroid a13 = new Asteroid();
				// ids.put(a13, "asteroid13");
				game1.addAsteroid(a13);
				a13.setGame(game1);

				a11.accept(a12);
				a12.accept(a11);

				TeleportingGate tg11 = new TeleportingGate();
				tg11.setAsteroid(a11);

				TeleportingGate tg12 = new TeleportingGate();
				tg12.setAsteroid(a13);

				tg11.setPair(tg12);
				tg12.setPair(tg11);
				
				// System.out.println();
				a11.checkSpaceBase();

				if (game1.isEndGameFlag() == true) {
					System.out.println("\nWinGame Test: Successful");
				} else {
					System.out.println("WinGame Test: Failed");
				}

				break;
			case "2":
				System.out.println("Testing Move Spaceship...\n Please choose a test-case:\n");

				System.out.println("\ta: Move to asteroid\n");
				System.out.println("\tb: Move through TeleportingGate\n");
				System.out.println("\tc: Back to the main menu\n");

				System.out.print("\tMy choice:");
				choice2 = scan.nextLine();
				while (choice2.equals("c") == false) {

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
					// ids.put(s21, "settler21");
					game2.addSettler(s21);
					s21.accept(iron2);
					s21.accept(ice2);

					Settler s22 = new Settler();
					game2.addSettler(s22);
					s22.accept(coal2);

					Asteroid a21 = new Asteroid();
					// ids.put(a21, "asteroid2");
					game2.addAsteroid(a21);
					a21.setGame(game2);
					a21.accept(s21);
					s21.setPlace(a21);
					a21.accept(s22);
					s22.setPlace(a21);

					Asteroid a22 = new Asteroid();
					// ids.put(a22, "asteroid22");
					game2.addAsteroid(a22);
					a22.setGame(game2);

					Asteroid a23 = new Asteroid();
					// ids.put(a23, "asteroid3");
					game2.addAsteroid(a23);
					a23.setGame(game2);

					// a1 a2 szomszed
					a21.accept(a22);
					a22.accept(a21);

					TeleportingGate tg21 = new TeleportingGate();
					tg21.setAsteroid(a21);

					TeleportingGate tg22 = new TeleportingGate();
					tg22.setAsteroid(a23);

					tg21.setPair(tg22);
					tg22.setPair(tg21);

					switch (choice2) {
					case "a":
						System.out.println("\t\tMove to asteroid selected:\n");

						/**
						 * Letrehozunk ket aszteroidat, melyeket beallitjuk egymas szomszedjanak.
						 * Valamint letrehozunk egy telepest, akit raallitunk az elsokent letrehozott
						 * aszteroidara, majd meghivjuk a telepes move() fuggvenyet. A teszt sikeres, ha
						 * a telepes aszteroidajat lekerdezve a masodikkent letrehozott aszteroidat
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
						 * Letrehozunk ket aszteroidat, ket teleportkaput, amiket egymas parjanak
						 * beallitjuk, majd hozzaadjuk az egyes aszteroidakhoz, majd a letrehozott
						 * telepest raallitjuk az egyik aszteroidara. A teszt sikeres, ha a telepes
						 * move() fugvenyet meghivva az az adott iranyban atlep a teleportkapu altal
						 * szomszedos aszteroidara.
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
					default:
						System.out.println("\t\tInvalid choice!\n");
					}
					break;
				}
				break;

			case "3":
				System.out.println("Testing Drill Asteroid...\n Please choose a test-case:\n");

				System.out.println("\ta: Drill one layer\n");
				System.out.println("\tb: Drill radioactive asteroid's last layer\n");
				System.out.println("\tc: Drill icy asteroid's last layer\n");
				System.out.println("\td: Fail to drill\n");
				System.out.println("\te: Back to the main menu\n");

				System.out.print("\tMy choice:");
				choice2 = scan.nextLine();
				while (choice2.equals("e") == false) {
					switch (choice2) {
					case "a":
						System.out.println("\t\tDrill one layer selected:\n");

						// Letrehozunk egy aszteroidat es beallitjuk a reteg vastagsagat 2-re
						Asteroid asteroid1_3a = new Asteroid();
						asteroid1_3a.setRockLayerThickness(2);

						// Letrehozunk egy Settler-t es beallitjuk az aktualis aszteroidajanak
						// asteroid1_3a-t
						Settler settler1_3a = new Settler();
						settler1_3a.setPlace(asteroid1_3a);

						// Meghivjuk settler1_3a drill() metodusat
						settler1_3a.drill();

						// Ha az aszteroida retegenek vastagsaga egyre csokkent, akkor sikeres a teszt
						if (asteroid1_3a.getRockLayerThickness() == 1) {
							System.out.println("\nDrill one layer test: successful");
						}

						break;
					case "b":
						System.out.println("\t\tDrill radioactive asteroid's last layer selected:\n");

						// Letrehozzuk a game-t
						Game game_3b = new Game();

						// Letrehozunk ket aszteroidat
						Asteroid asteroid1_3b = new Asteroid();
						Asteroid asteroid2_3b = new Asteroid();

						// Letrehozunk egy Napot es beallitjuk szomszedjanak az asteroid1_3b-t
						Sun sun_3b = new Sun();
						sun_3b.addNeighbor(asteroid1_3b);

						// Hozzaadjuk a asteroid1_3b magjaba az urant es beallitjuk a reteg vastagsagat
						// 1-re
						asteroid1_3b.addResource(new Uranium());
						asteroid1_3b.setRockLayerThickness(1);

						// Beallitjuk az aszteroidak sun attributumait
						asteroid1_3b.setSun(sun_3b);
						asteroid2_3b.setSun(sun_3b);

						// Beallitjuk az aszteroidak game attributumait
						asteroid1_3b.setGame(game_3b);
						asteroid2_3b.setGame(game_3b);

						// Beallitjuk a ket aszteroidat egymassal szomszedosnak
						asteroid1_3b.accept(asteroid2_3b);
						asteroid2_3b.accept(asteroid1_3b);

						// Hozzaadjuk a game_3b-hez az aszteroidakat es beallitjuk a Napot
						game_3b.addAsteroid(asteroid1_3b);
						game_3b.addAsteroid(asteroid2_3b);
						game_3b.setSun(sun_3b);

						// Letrehozunk egy AIRobotot es beallitjuk aktualis aszteroidajanak az
						// asteroid1_3b-t
						AIRobot robot_3b = new AIRobot();
						robot_3b.setPlace(asteroid1_3b);

						// Letrehozunk 2 Settler-t
						Settler settler1_3b = new Settler();
						Settler settler2_3b = new Settler();

						// Hozzaadjuk az aszteroidakhoz a robotot es a Settlereket
						asteroid1_3b.accept(robot_3b);
						asteroid1_3b.accept(settler1_3b);
						asteroid2_3b.accept(settler2_3b);

						// Hozzadjuk a game_3b-hez a Settlereket
						game_3b.addSettler(settler1_3b);
						game_3b.addSettler(settler2_3b);

						// Beallitjuk a Settlerek game attributumait
						settler1_3b.setGame(game_3b);
						settler2_3b.setGame(game_3b);

						// Beallitjuk a Settlerek aktualis aszteroidait
						settler1_3b.setPlace(asteroid1_3b);
						settler2_3b.setPlace(asteroid2_3b);

						// Meghivjuk a settler1_3b drill() metodusat
						settler1_3b.drill();

						// Ha a robot aktualis aszteroidaja az asteroid2_3b es ha a
						// jatek nem tartalmazza az asteroid1_3b-t,
						// illetve ha a jatek nem tartalmazza a settler1_3b-t, akkor a teszt sikeres
						if (robot_3b.getPlace()==asteroid2_3b && !game_3b.getAsteroids().contains(asteroid1_3b)
								&& !game_3b.getSettlers().contains(settler1_3b))
							System.out.println("\nDrill radioactive asteroid's last layer test: successful");
						break;
					case "c":

						// Letrehozunk egy aszteroidat es egy Settler-t
						Settler settler1_3c = new Settler();
						Asteroid asteroid1_3c = new Asteroid();

						// Letrehozzuk a game_3c-t es hozzaadjuk a letrehozott aszteroidat es Settler-t
						Game game_3c = new Game();
						game_3c.addSettler(settler1_3c);
						game_3c.addAsteroid(asteroid1_3c);

						// Beallitjuk a Settler game attributumat es az aszteroidajat
						settler1_3c.setPlace(asteroid1_3c);
						settler1_3c.setGame(game_3c);

						// Beallitjuk az aszteroida game attributumat es hozzaadjuk a letrehozott
						// Settler-t,
						// valamint beallitjuk a reteg vastagsagat 1-re
						asteroid1_3c.setGame(game_3c);
						asteroid1_3c.accept(settler1_3c);
						asteroid1_3c.setRockLayerThickness(1);

						// Letrehozzuk a Napot, melyet beallitunk a jatek es az aszteroida szamara
						// es hozzaadjuk szomszedos aszteroidakent az asteroid1_3c-t
						Sun sun_3c = new Sun();
						game_3c.setSun(sun_3c);
						asteroid1_3c.setSun(sun_3c);
						sun_3c.addNeighbor(asteroid1_3c);

						// Az aszteroida magjaba jeget helyezunk
						asteroid1_3c.addResource(new Ice());

						// Meghivjuk a Settler drill() metodusat
						settler1_3c.drill();

						// Ha az aszteroida nem tartalmaz nyersanyagot, akkor sikeres a teszt
						if (asteroid1_3c.getResource() == null)
							System.out.println("\nDrill icy asteroid's last layer: successful");

						break;
					case "d":
						System.out.println("\t\tFail to drill selected:\n");

						// Letrehozunk egy aszteroidat es beallitjuk a reteg vastagsagat 1-re
						Asteroid asteroid1_3d = new Asteroid();
						asteroid1_3d.setRockLayerThickness(0);

						// Letrehozunk egy Settler-t
						Settler settler1_3d = new Settler();

						// Beallitjuk a settler1_3d aktualis aszteroidajat es hozzaadjuk a Settler-t az
						// aszteroidahoz
						settler1_3d.setPlace(asteroid1_3d);
						asteroid1_3d.accept(settler1_3d);

						// Meghivjuk a Settler drill() metodusat
						settler1_3d.drill();

						// Ha az aszteroida retegenek vastagsaga 0, akkor a teszt sikeres
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
				System.out.println("\td: Restore Uranium when Sun is close\n");
				System.out.println("\te: Back to the main menu\n");

				System.out.print("\tMy choice:");
				choice2 = scan.nextLine();
				while (choice2.equals("e") == false) {
					switch (choice2) {
					case "a":
						System.out.println("\t\tRestore Resource selected:\n");

						/*
						 * Letrehozunk egy Settler-t, amelyhez hozzaadunk egy szenet. Letrehozunk egy
						 * aszteroidat es a reteg vastagsagat beallitjuk 0-ra. Meghivjuk a Settler
						 * restore() fuggvenyet es ha az aszteroida magjaba bekerul a nyersanyag, akkor
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
						 * Letrehozunk egy egy Settler-t, amelyhez hozzaadunk egy szenet. Letrehozunk
						 * egy aszteroidat es a reteg vastagsagat beallitjuk 1-re. Meghivjuk a Settler
						 * restore() fuggvenyet es ha a Settler meg mindig tarolja a nyersanyagot, tehat
						 * nem sikerult az aszteroida magjaba helyezni a reteg vastagsaga miatt, akkor
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
						 * Letrehozunk egy Settler-t es egy aszteroidat, amely epp napkozelben van,
						 * retegvastagsaga 0 es ureges. Beallitjuk a Settler aktualis aszteroidajanak a
						 * letrehozott aszteroidat es hozzaadunk a Settler nyersanyagaihoz egy jeget.
						 * Meghivjuk a Settler restore(r: Resource) fuggvenyet. Ha az aszteroida
						 * tovabbra is ureges, tehat nem tarol nyersanyagot, mert a jeg vizze
						 * szublimalt, akkor sikeres a teszt.
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
						System.out.println("\t\tRestore Uranium when Sun is close selected:\n");

						/*
						 * Letrehozunk egy Settler-t es egy aszteroidat, amely epp napkozelben van,
						 * retegvastagsaga 0 es ureges. Beallitjuk a Settler aktualis aszteroidajanak a
						 * letrehozott aszteroidat es hozzaadunk a Settler-hez egy urant, amely
						 * radioaktiv. Meghivjuk a Settler restore(r: Resource) fuggvenyet. Ha az
						 * aszteroida felrobban es ezaltal torlodik a jatekbol, akkor sikeres a teszt.
						 */

						Game game4111 = new Game();

						Settler s4111 = new Settler();
						game4111.addSettler(s4111);
						s4111.setGame(game4111);
						Asteroid a4111 = new Asteroid();
						game4111.addAsteroid(a4111);
						a4111.setGame(game4111);
						Resource r4111 = new Uranium();

						a4111.accept(s4111);
						s4111.setPlace(a4111);
						a4111.setRockLayerThickness(0);
						Sun sun4111 = new Sun();

						a4111.setSun(sun4111);
						sun4111.addNeighbor(a4111);

						s4111.restore(r4111);

						if (game4111.getAsteroids().isEmpty()) {
							System.out.println("\nRestore Uranium when Sun is close test: successful");
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
						 * Letrehozunk egy Settler-t es egy aszteroidat, amelynek a retegvastagsaga 0, es
						 * eltarol egy szenet, tovabba letrehozzuk az urbazis receptjet. Meghivjuk a
						 * Settler mine() metodusat, ha a Settler ezutan eltarolja a az aszteroidaba
						 * helyezett szenet es az aszteroida magja ures, akkor sikeres a teszt.
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
						 * Letrehozunk egy Settler-t es egy aszteroidat, amelynek a retegvastagsaga 1,
						 * es eltarol egy szenet, tovabba letrehozzuk az urbazis receptjet.
						 * Meghivjuk a Settler mine() metodusat; ha az aszteroida ezek utan nincsen
						 * kibanyaszva, akkor sikeres a teszt.
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
						 * Letrehozzuk a robot elkeszitesehez szukseges receptet, amely nyersanyagokat
						 * tartalmaz es letrehozunk egy Settler-t, amelyhez hozzaadjuk az AIRobot
						 * megepitesehez szukseges nyersanyagokat. Meghivjuk a Settler createAIRobot()
						 * metodusat, ha ketten vannak a Settler aszteroidajan, tovabba ha a Settler
						 * altal eltarolt nyersanyagok elfogytak es a Timerhez hozzaadodott egy
						 * Steppable, akkor sikeres a teszt.
						 */
						Game game_6a = new Game();
						Timer timer_6a = new Timer(1000, 20);
						game_6a.setTimer(timer_6a);
						Recipe robotRecipe_6a = new Recipe();
						game_6a.addRecipe(robotRecipe_6a);
						robotRecipe_6a.addResource(new Coal());
						robotRecipe_6a.addResource(new Iron());
						robotRecipe_6a.addResource(new Uranium());
						Settler s_6a = new Settler();
						game_6a.addSettler(s_6a);
						s_6a.setGame(game_6a);
						s_6a.accept(new Coal());
						s_6a.accept(new Iron());
						s_6a.accept(new Uranium());
						Asteroid a_6a = new Asteroid();
						s_6a.setPlace(a_6a);
						a_6a.accept(s_6a);

						s_6a.createAIRobot();
						if (a_6a.getCharacters().size() == 2
								& s_6a.getCollectedResources().size() == 0
								& timer_6a.getSteppables().contains(
										(ISteppable)a_6a.getCharacters().get(1)))
							System.out.println("\nBuild AI Robot: successful");
						break;
					case "b":
						System.out.println("Fail to build robot selected:");

						/*
						 * Letrehozzuk a robot elkeszitesehez szukseges receptet, amely nyersanyagokat
						 * tartalmaz es letrehozunk egy Settler-t, amelyhez hozzaadunk tovabbi
						 * nyersanyagokat. Meghivjuk a Settler createAIRobot() metodusat, ha a Settler
						 * egyedul van az aszteroidan es tovabbra is eltarolja a nyersanyagokat, akkor
						 * sikeres a teszt.
						 */

						Game game_6b = new Game();
						Timer timer_6b = new Timer(1000, 20);
						game_6b.setTimer(timer_6b);
						Recipe robotRecipe_6b = new Recipe();
						game_6b.addRecipe(robotRecipe_6b);
						robotRecipe_6b.addResource(new Coal());
						robotRecipe_6b.addResource(new Iron());
						robotRecipe_6b.addResource(new Uranium());
						Settler s_6b = new Settler();
						game_6b.addSettler(s_6b);
						s_6b.setGame(game_6b);
						s_6b.accept(new Uranium());
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
						 * Letrehozzuk a teleportkapu-par elkeszitesehez szukseges receptet, amely
						 * nyersanyagokat tartalmaz es letrehozunk egy Settler-t, amelyhez hozzaadjuk a
						 * teleportkapuk megepitesehez szukseges nyersanyagokat. Meghivjuk a Settler
						 * createGatePair() metodusat, ha a Settlernel levo teleportkapuk szama 2 es a
						 * Settler nyersanyagai elfogytak, akkor sikeres a teszt.
						 */

						Game game_7a = new Game();
						Recipe gatePairRecipe_7a = new Recipe();
						game_7a.addRecipe(new Recipe());
						game_7a.addRecipe(gatePairRecipe_7a);
						gatePairRecipe_7a.addResource(new Iron());
						gatePairRecipe_7a.addResource(new Iron());
						gatePairRecipe_7a.addResource(new Ice());
						gatePairRecipe_7a.addResource(new Uranium());
						Settler s_7a = new Settler();
						game_7a.addSettler(s_7a);
						s_7a.setGame(game_7a);
						s_7a.accept(new Ice());
						s_7a.accept(new Iron());
						s_7a.accept(new Uranium());
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
						 * Letrehozzuk a teleportkapu-par elkeszitesehez szukseges receptet, amely
						 * nyersanyagokat tartalmaz es letrehozunk egy Settler-t, amelyhez hozzaadunk
						 * egy teleportkaput, illetve nyersanyagot. Meghivjuk a Settler createGatePair()
						 * metodusat, ha a Settlernel levo teleportkapuk szama tovabbra is 1 es a
						 * Settlernek megmaradt a nyersanyaga , akkor sikeres a teszt.
						 */

						Game game_7b = new Game();
						Recipe gatePairRecipe_7b = new Recipe();
						game_7b.addRecipe(new Recipe());
						game_7b.addRecipe(gatePairRecipe_7b);
						gatePairRecipe_7b.addResource(new Iron());
						gatePairRecipe_7b.addResource(new Iron());
						gatePairRecipe_7b.addResource(new Ice());
						gatePairRecipe_7b.addResource(new Uranium());
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
						 * Letrehozunk ket aszteroidat, egy Settler-t es ket teleportkaput, amelyek
						 * egymasnak a parjai. A ket teleportkapubol az egyiket elhelyezzuk az a1_8a
						 * aszteroidan, a masikat pedig a Settlernel, aki az a2_8a aszteroidan
						 * helyezkedik el. Meghivjuk a Settler releaseGate() metodusat, ha a ket
						 * aszteroida egymasnak a szomszedjai a teleportkapuk altal, akkor sikeres a
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
						 * Letrehozunk ket aszteroidat, egy Settler-t, amely a Settler aktualis
						 * aszteroidaja, majd pedig meghivjuk a Settler releaseGate() metodusat. Ha az
						 * aszteroidan levo teleportkapuk szama 0, akkor sikeres a teszt.
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
						 * Letrehozunk egy Settler-t, egy aszteroidat, amelynek a retegvastagsaga 0,
						 * tovabba egy AIRobotot es egy Napot. Meghivjuk a Sun step() metodusat, ezaltal
						 * megvalositva a napvihart. A teszt sikeres, ha a jatekban megtalalhato
						 * jatekosok szama nem 0 a napvihar utan.
						 */

						Asteroid a1_9a = new Asteroid();
						Settler s1_9a = new Settler();
						AIRobot r_9a = new AIRobot();
						Game g_9a = new Game();
						Sun sun_9a = new Sun();
						sun_9a.setGame(g_9a);
						// az aszteroidanak beallitgatjuk a dolgokat
						a1_9a.accept(r_9a);
						a1_9a.accept(s1_9a);
						a1_9a.setSun(sun_9a);
						a1_9a.setGame(g_9a);
						// a settlernek is beallitjuk
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
						// aszteroida legyen kibanyaszott
						a1_9a.setRockLayerThickness(0);
						// eddig tartott az init

						sun_9a.step();

						if (a1_9a.getCharacters() != null)
							System.out.println("\nSunstorm on mined asteroid test: successful");
						break;
					case "b":
						System.out.println("\t\tAsteroid is not mined selected:\n");

						/*
						 * Letrehozunk egy Settler-t, egy aszteroidat, amelynek a retegvastagsaga 5,
						 * tovabba egy AIRobotot es egy Napot. Meghivjuk a Sun step() metodusat, ezaltal
						 * megvalositva a napvihart. A teszt sikeres, ha a jatekban megtalalhato
						 * jatekosok szama 0 a napvihar utan.
						 */

						Asteroid a1_9b = new Asteroid();
						Settler s1_9b = new Settler();
						AIRobot r_9b = new AIRobot();
						Game g_9b = new Game();
						Sun sun_9b = new Sun();
						sun_9b.setGame(g_9b);

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
							System.out.println("\nSunstorm on not mined asteroid test: successful");
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
