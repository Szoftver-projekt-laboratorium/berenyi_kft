package berenyi_kft;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Skeleton {
	
	private static Map<Object, String> ids = new HashMap<Object, String>();
	
	

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
		   	    	
		   	    	/*Game game = new Game();
		   	    	Asteroid a1 = new Asteroid();
		   	    	Settler s1 = new Settler();
		   	    	Settler s2 = new Settler();
		   	    	Recipe spaceBase = new Recipe();
		   	    	game.addRecipe(new Recipe());
		   	    	game.addRecipe(new Recipe());
		   	    	game.addRecipe(spaceBase);
		   	    	
		   	    	Ice ice = new Ice();
		   	    	Iron iron = new Iron();
		   	    	Coal coal = new Coal();
		   	    	spaceBase.addResource(ice);
		   	    	spaceBase.addResource(iron);
		   	    	spaceBase.addResource(coal);
		   	    	a1.accept(s1);
		   	    	a1.accept(s2);
		   	    	a1.setGame(game);
		   	    	
		   	    	a1.checkSpaceBase();*/
		   	    	
		   	    	
		   	    	
		   	    	
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
		   			   	switch (choice2){
		   			   	    case "a":
		   			   	    	System.out.println("\t\tMove to asteroid selected:\n");
		   			   	    	/*
		   			   	    	Settler s1 = new Settler();
		   			   	    	ids.put(s1, "settler1");
		   			   	    	
		   			   	    	Asteroid a1 = new Asteroid();
		   			   	    	ids.put(a1,"asteroid1");
		   			   	    	
		   			   	    	Asteroid a2 = new Asteroid();
		   			   	    	ids.put(a2,"asteroid2");
		   			   	    	
		   			   	    	a1.addNeighbor(a2);
		   			   	    	a2.addNeighbor(a1);
		   			   	    	
		   			   	    	a1.accept(s1);
		   			   	    	s1.setPlace(a1);
		   			   	    	int dir = 0;
		   			   	    	s1.move(dir);
			   			   	    	//ids.get(s1.getPlace())
		   			   	    	if(s1.getPlace()==a2) {
		   			   	    		System.out.println("Successful moving!\n");
		   			   	    	}
		   			   	    	*/
		   			   	    	break;
		   			   	    case "b":
		   			   	    	System.out.println("\t\tMove through TeleportingGate selected:\n");
		   			   	    	/*
		   			   	    	 * 2 ast, 2 kapu
		   			   	    	 * 2 kapu egymás párja, aszteroidához vannak adva
		   			   	    	 * settler, egyik asteroidához
		   			   	    	 */
		   			   	    	
		   			   	    	break;
		   			   	    case "c":
		   			   	    	System.out.println("\t\tFail to use TeleportingGate selected:\n");
		   			   	    	
		   			   	    	/*
		   			   	    	 * 2 ast, 2 kapu
		   			   	    	 * 2 kapu egymás párja, aszteroidához vannak adva
		   			   	    	 * settler, egyik asteroidához
		   			   	    	 * egyik kapu még a settlernél van (azért nem tud mozogni, mert a zsebében van a pár)
		   			   	    	 * error: (nincs lerakva a párja)
		   			   	    	 */
		   			   	    	
		   			   	    	break;
		   			   	    default :
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
		   			   	switch (choice2){
		   			   	    case "a":
		   			   	    	System.out.println("\t\tDrill one layer selected:\n");
		   			   	    	/*
		   			   	    	 * Asteroid
		   			   	    	 * set layer
		   			   	    	 * settler
		   			   	    	 * drill hívás
		   			   	    	 * expected layersize = 4
		   			   	    	 * print layersize
		   			   	    	 */
		   			   	    	
		   			   	    	Asteroid a1=new Asteroid();
		   			   	    	a1.setRockLayerThickness(2);
		   			   	    	Settler s1=new Settler();
		   			   	    	s1.setPlace(a1);
		   			   	    	s1.drill();
		   			   	    	System.out.println(a1.getRockLayerThickness());
		   			   	    	if(a1.getRockLayerThickness()==1) {
		   			   	    		System.out.println("Successful drilling");
		   			   	    	}
		   			   	    	
		   			   	    	break;
		   			   	    case "b":
		   			   	    	System.out.println("\t\tDrill radioactive asteroid's last layer selected:\n");
		   			   	    	
		   			   	    	Game game=new Game();
		   			   	    	Asteroid asteroid1=new Asteroid();
		   			   	    	Asteroid asteroid2=new Asteroid();
		   			   	    	
		   			   	    	Sun sun=new Sun();
		   			   	    	sun.addNeighbor(asteroid1);
		   			   	    	
		   			   	    	asteroid1.addResource(new Uran());
		   			   	    	asteroid1.setRockLayerThickness(1);
		   			   	    	
		   			   	    	asteroid1.setSun(sun);
		   			   	    	asteroid2.setSun(sun);
		   			   	    	
		   			   	    	asteroid1.setGame(game);
		   			   	    	asteroid2.setGame(game);
		   			   	    	
		   			   	    	asteroid1.addNeighbor(asteroid2);
		   			   	    	asteroid2.addNeighbor(asteroid1);
		   			   	    	
		   			   	    	game.addAsteroid(asteroid1);
		   			   	    	game.addAsteroid(asteroid2);
		   			   	    	game.setSun(sun);
		   			   	    	
		   			   	    	AIRobot robot=new AIRobot();
		   			   	    	robot.setPlace(asteroid1);
		   			   	    
		   			   	    	
		   			   	    	Settler settler1=new Settler();
		   			   	    	Settler settler2=new Settler();
		   			   	    	
		   			   	    	asteroid1.accept(robot);
		   			   	    	asteroid1.accept(settler1);
		   			   	    	asteroid2.accept(settler2);
		   			   	    	
		   			   	    	game.addSettler(settler1);
		   			   	    	game.addSettler(settler2);
		   			   	    	
		   			   	    	settler1.setGame(game);
		   			   	    	settler2.setGame(game);
		   			   	    	
		   			   	    	settler1.setPlace(asteroid1);
		   			   	    	settler2.setPlace(asteroid2);
		   			   	    	System.out.println(asteroid1.getSizeOfCharacters());
		   			   	    	settler1.drill();
		   			   	    	
		   			   	    	if(robot.getPlace()==asteroid2&&game.getSizeOfSettlersAlive()==1&&
		   			   	    			game.getSizeOfAsteroids()==1)
		   			   	    		System.out.println("Successful radioactive drilling test");
		   			   	    	break;
		   			   	    	
		   			   	    case "c":
		   			   	    	System.out.println("\t\tDrill icy asteroid's last layer selected:\n");
		   			   	    	
		   			   	    	Settler settler3=new Settler();
		   			   	    	Asteroid asteroid3=new Asteroid();
		   			   	    	
		   			   	    	Game g=new Game();
		   			   	    	g.addSettler(settler3);
		   			   	    	g.addAsteroid(asteroid3);
		   			   	    	
		   			   	    	settler3.setPlace(asteroid3);
		   			   	    	settler3.setGame(g);
		   			   	    	
		   			   	    	asteroid3.setGame(g);
		   			   	    	asteroid3.accept(settler3);
		   			   	    	
		   			   	    	Sun s=new Sun();
		   			   	    	g.setSun(s);
		   			   	    	asteroid3.setSun(s);
		   			   	    	s.addNeighbor(asteroid3);
		   			   	    	
		   			   	    	asteroid3.addResource(new Ice());
		   			   	    	
		   			   	    	settler3.drill();
		   			   	    	
		   			   	    	if(asteroid3.getResource()==null)
		   			   	    		System.out.println("successful icy drilling test");
		   			   	    	break;
		   			   	    	
		   			   	    case "d":
		   			   	    	System.out.println("\t\tFail to drill selected:\n");
		   			   	    	
		   			   	    	Asteroid asteroid4=new Asteroid();
		   			   	    	asteroid4.setRockLayerThickness(0);
		   			   	    	
		   			   	    	Settler settler4=new Settler();
		   			   	    	
		   			   	    	settler4.setPlace(asteroid4);
		   			   	    	asteroid4.accept(settler4);
		   			   	    	
		   			   	    	settler4.drill();
		   			   	    	
		   			   	    	if(asteroid4.getRockLayerThickness()==0)
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
		   			   	    	break;
		   			   	    case "b":
		   			   	    	System.out.println("\t\tFail to restore selected:\n");
		   			   	    	break;
		   			   	    case "c":
		   			   	    	System.out.println("\t\tRestore Ice when Sun is close selected:\n");
		   			   	    	break;
		   			   	    case "d":
		   			   	    	System.out.println("\t\tRestore Uran when Sun is close selected:\n");
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
		   			   	    	break;
		   			   	    case "b":
		   			   	    	System.out.println("\t\tFail to mine selected:\n");
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
		   			   	    	if (a_6a.getCharacters().size() == 2 &
		   			   	    			s_6a.getCollectedResources().size() == 0 &
		   			   	    			timer_6a.getSizeOfSteppables() == 1)
		   			   	    		System.out.println("Successfully created new AIRobot");
		   			   	    	break;
		   			   	    	
		   			   	    case "b":
		   			   	    	System.out.println("\t\tFail to build selected:\n");
		   			   	    	
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
								if (a_6b.getCharacters().size() == 1 &
		   			   	    			s_6b.getCollectedResources().size() == 2)
		   			   	    		System.out.println("Successful fail to create of AIRobot");
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
								if (s_7a.getSizeOfGatesCreated() == 2 &
		   			   	    			s_7a.getCollectedResources().size() == 0)
		   			   	    		System.out.println("Successful creation of TeleportingGate pair");
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
		   			   	    	s_7b.accept(new TeleportingGate());
								s_7b.accept(new Iron());

								s_7b.createGatePair();
								if (s_7b.getSizeOfGatesCreated() == 1 &
		   			   	    			s_7b.getCollectedResources().size() == 1)
		   			   	    		System.out.println("Successful fail to create TeleportingGate pair");
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
		   			   	    	break;
		   			   	    case "b":
		   			   	    	System.out.println("\t\tFail to place TeleportingGate selected:\n");
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
		   			   	    	break;
		   			   	    case "b":
		   			   	    	System.out.println("\t\tAsteroid is not mined selected:\n");
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
