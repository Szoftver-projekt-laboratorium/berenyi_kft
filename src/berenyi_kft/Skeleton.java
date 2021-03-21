package berenyi_kft;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Skeleton {
	
	private static Map<Object, String> ids = new HashMap();
	
	

	 public static void main(String args[])
	  {
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
		   	switch (choice){
		   	    case "1":
		   	    	
		   	    	System.out.println("Testing Win Game.\n");
		   	    	
		   	    	/*
		   	    	 * új játék
		   	    	 * új asteroida
		   	    	 * 2 settler
		   	    	 * új recipe spacebase
		   	    	 * settlerekhez resourceok
		   	    	 * gamehez asteroid
		   	    	 * gamehez settlerek
		   	    	 * settlerek aszteroidához adása
		   	    	 * asteroid-check hívás - lekérdezi a receptet a gametől
		   	    	 * 
		   	    	 */
		   	    	
		   	    	
		   	    /*	a1.checkSpaceBase();
		   	    	if(game.end_game_flag == true) {
		   	    		System.out.println("WinGame Test: Successful");
		   	    	} else {
		   	    		System.out.println("WinGame Test: Failed");
		   	    	}
		   	    		   	    	
		   	    	*/
		   	    	break;
		   	    case "2":
		   	    	
		   	    	
		   			   	    	
		   			   	    	
		   			   	    	
		   	     
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
		   			   	switch (choice2){
		   			   	    case "a":
		   			   	    	System.out.println("\t\tDrill one layer selected:\n");
		   			   	    	
		   			   	    	Asteroid asteroid1_3a=new Asteroid();
		   			   	    	asteroid1_3a.setRockLayerThickness(2);
		   			   	    	Settler settler1_3a=new Settler();
		   			   	    	settler1_3a.setPlace(asteroid1_3a);
		   			   	    	settler1_3a.drill();
		   			   	    	System.out.println(asteroid1_3a.getRockLayerThickness());
		   			   	    	if(asteroid1_3a.getRockLayerThickness()==1) {
		   			   	    		System.out.println("Successful drilling test");
		   			   	    	}
		   			   	    	
		   			   	    	break;
		   			   	    case "b":
		   			   	    	System.out.println("\t\tDrill radioactive asteroid's last layer selected:\n");
		   			   	    	
		   			   	    	Game game_3b=new Game();
		   			   	    	Asteroid asteroid1_3b=new Asteroid();
		   			   	    	Asteroid asteroid2_3b=new Asteroid();
		   			   	    	
		   			   	    	Sun sun_3b=new Sun();
		   			   	    	sun_3b.addNeighbor(asteroid1_3b);
		   			   	    	
		   			   	    	asteroid1_3b.addResource(new Uran());
		   			   	    	asteroid1_3b.setRockLayerThickness(1);
		   			   	    	
		   			   	    	asteroid1_3b.setSun(sun_3b);
		   			   	    	asteroid2_3b.setSun(sun_3b);
		   			   	    	
		   			   	    	asteroid1_3b.setGame(game_3b);
		   			   	    	asteroid2_3b.setGame(game_3b);
		   			   	    	
		   			   	    	asteroid1_3b.accept(asteroid2_3b);
		   			   	    	asteroid2_3b.accept(asteroid1_3b);
		   			   	    	
		   			   	    	game_3b.addAsteroid(asteroid1_3b);
		   			   	    	game_3b.addAsteroid(asteroid2_3b);
		   			   	    	game_3b.setSun(sun_3b);
		   			   	    	
		   			   	    	AIRobot robot_3b=new AIRobot();
		   			   	    	robot_3b.setPlace(asteroid1_3b);
		   			   	    
		   			   	    	
		   			   	    	Settler settler1_3b=new Settler();
		   			   	    	Settler settler2_3b=new Settler();
		   			   	    	
		   			   	    	asteroid1_3b.accept(robot_3b);
		   			   	    	asteroid1_3b.accept(settler1_3b);
		   			   	    	asteroid2_3b.accept(settler2_3b);
		   			   	    	
		   			   	    	game_3b.addSettler(settler1_3b);
		   			   	    	game_3b.addSettler(settler2_3b);
		   			   	    	
		   			   	    	settler1_3b.setGame(game_3b);
		   			   	    	settler2_3b.setGame(game_3b);
		   			   	    	
		   			   	    	settler1_3b.setPlace(asteroid1_3b);
		   			   	    	settler2_3b.setPlace(asteroid2_3b);

		   			   	    	settler1_3b.drill();
		   			   	    	
		   			   	    	if(robot_3b.getPlace()==asteroid2_3b&&!game_3b.getAsteroids().contains(asteroid1_3b)&&
		   			   	    			!game_3b.getSettlers().contains(settler1_3b))
		   			   	    		System.out.println("Drill radioactive asteroid's last layer selected: success");
		   			   	    	break;
		   			   	    case "c":
		   			   	    	
		   			   	    	Settler settler1_3c=new Settler();
		   			   	    	Asteroid asteroid1_3c=new Asteroid();
		   			   	    	
		   			   	    	Game game_3c=new Game();
		   			   	    	game_3c.addSettler(settler1_3c);
		   			   	    	game_3c.addAsteroid(asteroid1_3c);
		   			   	    	
		   			   	    	settler1_3c.setPlace(asteroid1_3c);
		   			   	    	settler1_3c.setGame(game_3c);
		   			   	    	
		   			   	    	asteroid1_3c.setGame(game_3c);
		   			   	    	asteroid1_3c.accept(settler1_3c);
		   			   	    	asteroid1_3c.setRockLayerThickness(1);
		   			   	    	
		   			   	    	Sun sun_3c=new Sun();
		   			   	    	game_3c.setSun(sun_3c);
		   			   	    	asteroid1_3c.setSun(sun_3c);
		   			   	    	sun_3c.addNeighbor(asteroid1_3c);
		   			   	    	
		   			   	    	asteroid1_3c.addResource(new Ice());
		   			   	    	
		   			   	    	settler1_3c.drill();
		   			   	    	
		   			   	    	if(asteroid1_3c.getResource()==null)
		   			   	    		System.out.println("successful icy drilling test");
		   			   	    		
		   			   	    	break;
		   			   	    case "d":
		   			   	    	System.out.println("\t\tFail to drill selected:\n");
		   			   	    	
		   			   	    	Asteroid asteroid1_3d=new Asteroid();
		   			   	    	asteroid1_3d.setRockLayerThickness(0);
		   			   	    	
		   			   	    	Settler settler1_3d=new Settler();
		   			   	    	
		   			   	    	settler1_3d.setPlace(asteroid1_3d);
		   			   	    	asteroid1_3d.accept(settler1_3d);
		   			   	    	
		   			   	    	settler1_3d.drill();
		   			   	    	
		   			   	    	if(asteroid1_3d.getRockLayerThickness()==0)
		   			   	    		System.out.println("Successful fail to drill test");
		   			   	    		
		   			   	    	break;
		   			   	    default :
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
		   			   	switch (choice2){
		   			   	    case "a":
		   			   	    	System.out.println("\t\tRestore Resource selected:\n");
		   			   	    	
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
									System.out.println("Successful restore!\n");
								}
		   			   	    	break;
		   			   	    case "b":
		   			   	    	System.out.println("\t\tFail to restore selected:\n");
		   			   	    	
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
									System.out.println(
											"Successful Test!\nSettler failed to restore Resource, because it's not drilled\n");
								}
		   			   	    	break;
		   			   	    case "c":
		   			   	    	System.out.println("\t\tRestore Ice when Sun is close selected:\n");
		   			   	    	
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
									System.out.println("Successful Test!\nIce sublimated\n");
								}
		   			   	    	break;
		   			   	    case "d":
		   			   	    	System.out.println("\t\tRestore Uran when Sun is close selected:\n");
		   			   	    	
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
									System.out.println("Successful Test!\nAsteroid exploded!");
								}
		   			   	    	break;
		   			   	    default :
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
		   			   	switch (choice2){
		   			   	    case "a":
		   			   	    	System.out.println("\t\tMine Resource selected:\n");
		   			   	    	
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
									System.out.println("Successful mining!\nSettler mined and collected the resource!");
								}
		   			   	    	break;
		   			   	    case "b":
		   			   	    	System.out.println("\t\tFail to mine selected:\n");
		   			   	    	
		   			   	    	Game game5b = new Game();
		   			   	    	
		   			   	    	Settler s5b = new Settler();
		   			   	    	//ids.put(s1, "settler1");
		   			   	    	game5b.addSettler(s5b);
		   			   	    	Asteroid a5b = new Asteroid();
		   			   	    	game5b.addAsteroid(a5b);
		   			   	    	a5b.setGame(game5b);
		   			   	    	//ids.put(a1,"asteroid1");
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
		   			   	    	
				   			   	if(a5b.isMined() == false) {
			   			   	    		System.out.println("Successful Test!\n Settler could not mine!\n");
			   			   	    }
		   			   	    	break;

		   			   	    default :
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
		   			   	switch (choice2){
		   			   	    case "a":
		   			   	    	System.out.println("\t\tBuild robot selected:\n");
		   			   	    	
		   			   	    	/*
		   			   	    	 * Bővíteni kellett a recept létrehozásával
		   			   	    	 * és a Settler nyersanyagaival.
		   			   	    	 * A Timerre is kell referencia.
		   			   	    	 * Ha nincs elég nyersanyag, ott random
		   			   	    	 * hoztam létre néhányat.
		   			   	    	 */
		   			   	    	Game game_6a = new Game();
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
		   			   	    	//Timer timer_6a = new Timer();
		   			   	    	
		   			   	    	s_6a.createAIRobot();
		   			   	    	
		   			   	    	break;
		   			   	    case "b":
		   			   	    	System.out.println("\t\tFail to build selected:\n");
		   			   	    	
								Game game_6b = new Game();
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
								Timer timer = new Timer(0, 1000);

								s_6b.createAIRobot();
		   			   	    	
		   			   	    	break;

		   			   	    default :
		   			   	    	System.out.println("\t\tInvalid choice!\n");
		   			   	}
		   			   	break;
		   	    	}
		   	        break;
		   	    case "7":
		   	    	System.out.println("Testing Build TeleportingGatePair...\n Please choose a test-case:\n");
		   		   	
		   	    	System.out.println("	a: Build TeleportingGatePair\n");
		   	    	System.out.println("	b: Fail to build TeleportingGatePair\n");
		   	    	System.out.println("	c: Back to the main menu selected:\n");
		   	    	
		   	    	System.out.print("\tMy choice:");
		   	    	choice2 = scan.nextLine();
		   	    	while (choice2.equals("c") == false) {
		   			   	switch (choice2){
		   			   	    case "a":
		   			   	    	System.out.println("\t\tBuild TeleportingGatePair selected:\n");
		   			   	    	
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
		   			   	    	
		   			   	    	break;
		   			   	    case "b":
		   			   	    	System.out.println("\t\tFail to build TeleportingGatePair selected:\n");
		   			   	    	
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
								s_7b.accept(new Iron());
								s_7b.accept(new Uran());
								s_7b.accept(new Coal());

								s_7b.createGatePair();
		   			   	    	
		   			   	    	break;

		   			   	    default :
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
		   			   	switch (choice2){
		   			   	    case "a":
		   			   	    	System.out.println("\t\tPlace TeleportingGate selected:\n");
		   			   	    	
								// letrehozunk mindent ami kell
								Settler s1_8a = new Settler();
								Asteroid a1_8a = new Asteroid();
								Asteroid a2_8a = new Asteroid();
								TeleportingGate tg1_8a = new TeleportingGate();
								TeleportingGate tg2_8a = new TeleportingGate();
								// kapukat paroztatunk
								tg1_8a.setPair(tg2_8a);
								tg2_8a.setPair(tg1_8a);
								// egyik aszteroidahoz bekotjuk az egyik kaput es a kapuhoz az aszteroidat
								a1_8a.accept(tg1_8a);
								tg1_8a.setAsteroid(a1_8a);
								// a masik kapu a telepesnel van, es a telepes a masik aszteroidan
								s1_8a.gatesCreated.add(tg2_8a);
								a2_8a.accept(s1_8a);
								s1_8a.setPlace(a2_8a);
								// eddig tartott az init resz

								s1_8a.releaseGate();

								if (a1_8a == a2_8a.getNeighbor(0) && a2_8a == a1_8a.getNeighbor(0))
									System.out.println("Successful 'TeleportingGate placing' test");
		   			   	    	break;
		   			   	    case "b":
		   			   	    	System.out.println("\t\tFail to place TeleportingGate selected:\n");
		   			   	    	
								Settler s1_8b = new Settler();
								Asteroid a1_8b = new Asteroid();
								a1_8b.accept(s1_8b);
								s1_8b.setPlace(a1_8b);

								s1_8b.releaseGate();
		   			   	    	break;

		   			   	    default :
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
		   			   	switch (choice2){
		   			   	    case "a":
		   			   	    	System.out.println("\t\tAsteroid is mined selected:\n");
		   			   	    	
		   			   	    	//letrehoznk mindent ami kell
		   			   	    	Asteroid a1_9a= new Asteroid();
		   			   	    	Settler s1_9a= new Settler();
		   			   	    	AIRobot r_9a= new AIRobot();
		   			   	    	Game g_9a= new Game();
		   			   	    	Sun sun_9a= new Sun();
		   			   	    	//az aszteroidának beállítgatjuk a dolgokat
		   			   	    	a1_9a.accept(r_9a);
		   			   	    	a1_9a.accept(s1_9a);
		   			   	    	a1_9a.setSun(sun_9a);
		   			   	    	a1_9a.setGame(g_9a);
		   			   	    	//a settlernek is beállítjuk
		   			   	    	s1_9a.setPlace(a1_9a);
		   			   	    	s1_9a.setGame(g_9a);
		   			   	    	//az airoboton is beallitjuk az aszteriodajat
		   			   	    	r_9a.setPlace(a1_9a);
		   			   	    	//a gamehez hozzaadjuk a dolgokat
		   			   	    	g_9a.addAsteroid(a1_9a);
		   			   	    	g_9a.addSettler(s1_9a);
		   			   	    	g_9a.setSun(sun_9a);
		   			   	    	//sunhoz hozzaadjuk az aszteroidat
		   			   	    	sun_9a.addNeighbor(a1_9a);
		   			   	    	//aszteroida legyen kibányászott
		   			   	    	a1_9a.setRockLayerThickness(0);
		   			   	    	//eddig tartott az init
		   			   	    	
		   			   	    	sun_9a.step();
		   			   	    	
		   			   	    	if(a1_9a.getCharacters()!=null)
		   			   	    		System.out.println("Successful 'Sunstorm on mined Asteroid' test");
		   			   	    	break;
		   			   	    case "b":
		   			   	    	System.out.println("\t\tAsteroid is not mined selected:\n");
		   			   	    	
								// letrehoznk mindent ami kell
								Asteroid a1_9b = new Asteroid();
								Settler s1_9b = new Settler();
								AIRobot r_9b = new AIRobot();
								Game g_9b = new Game();
								Sun sun_9b = new Sun();
								// az aszteroidának beállítgatjuk a dolgokat
								a1_9b.accept(r_9b);
								a1_9b.accept(s1_9b);
								a1_9b.setSun(sun_9b);
								a1_9b.setGame(g_9b);
								// a settlernek is beállítjuk
								s1_9b.setPlace(a1_9b);
								s1_9b.setGame(g_9b);
								// az airoboton is beallitjuk az aszteriodajat
								r_9b.setPlace(a1_9b);
								// a gamehez hozzaadjuk a dolgokat
								g_9b.addAsteroid(a1_9b);
								g_9b.addSettler(s1_9b);
								g_9b.setSun(sun_9b);
								// sunhoz hozzaadjuk az aszteroidat
								sun_9b.addNeighbor(a1_9b);
								// aszteroida ne legyen kibányászott
								a1_9b.setRockLayerThickness(5);
								// eddig tartott az init

								sun_9b.step();

								if (g_9b.getSizeOfSettlersAlive() == 0)
									System.out.println("Successful 'Sunstorm on not mined Asteroid' test");
		   			   	    	break;

		   			   	    default :
		   			   	    	System.out.println("\t\tInvalid choice!\n");
		   			   	}
		   			   	break;
		   	    	}
		   	        break;

		   	    default :
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
   	  //System.exit(0);
}
}
