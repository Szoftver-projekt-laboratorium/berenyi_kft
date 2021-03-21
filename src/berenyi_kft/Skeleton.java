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
		   	    	
		   	    	
		   	    	a1.checkSpaceBase();
		   	    	if(game.end_game_flag == true) {
		   	    		System.out.println("WinGame Test: Successful");
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
		   			   	switch (choice2){
		   			   	    case "a":
		   			   	    	System.out.println("\t\tMove to asteroid selected:\n");
		   			   	    	
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
		   			   	    		System.out.println("Move to asteroid Test: Successful");
		   			   	    	}else {
		   			   	    		System.out.println("Move to asteroid Test: Failed");
		   			   	    	}
		   			   	    	
		   			   	    	break;
		   			   	    case "b":
		   			   	    	System.out.println("\t\tMove through TeleportingGate selected:\n");
		   			   	    	/*
		   			   	    	 * 2 ast, 2 kapu
		   			   	    	 * 2 kapu egymás párja, aszteroidához vannak adva
		   			   	    	 * settler, egyik asteroidához
		   			   	    	 */
		   			   	    	
		   			   	    	a1.accept(tg1);
		   			   	    	dir = 1;
		   			   	    	s1.move(dir);
		   			   	    	if(s1.getPlace()==a3) {
	   			   	    		System.out.println("Move through TelePortingGate Test: Successful");
		   			   	    	}else {
	   			   	    		System.out.println("Move through TelePortingGate Test: Failed");
		   			   	    	}
		   			   	    	
		   			   	    	dir = 0;
		   			   	    	s1.move(dir);
		   			   	    	a1.remove(tg2);
		   			   	    	
		   			   	    	
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
		   			   	    	
		   			   	    	dir = 1;
		   			   	    	try {
		   			   	    		s1.move(dir);
		   			   	    	}catch(Exception e) {
		   			   	    		System.out.println("Fail to use TeleportingGate Test: Successful");
		   			   	    	}
		   			   	    	
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
		   			   	    	 * expected layersizeT = 4
		   			   	    	 * print layersize
		   			   	    	 */
		   			   	    	/*---------------------------------------------------------
		   			   	    	Asteroid asteroid1_3a=new Asteroid();
		   			   	    	asteroid1_3a.setRockLayerThickness(2);
		   			   	    	Settler settler1_3a=new Settler();
		   			   	    	settler1_3a.setPlace(asteroid1_3a);
		   			   	    	settler1_3a.drill();
		   			   	    	System.out.println(asteroid1_3a.getRockLayerThickness());
		   			   	    	if(asteroid1_3a.getRockLayerThickness()==1) {
		   			   	    		System.out.println("Successful drilling test");
		   			   	    	}
		   			   	    	*///------------------------------------------------------------
		   			   	    	break;
		   			   	    case "b":
		   			   	    	System.out.println("\t\tDrill radioactive asteroid's last layer selected:\n");
		   			   	    	/*
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
		   			   	    	
		   			   	    	asteroid1_3b.addNeighbor(asteroid2_3b);
		   			   	    	asteroid2_3b.addNeighbor(asteroid1_3b);
		   			   	    	
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
		   			   	    		System.out.println("Successful Radioactive drilling test");
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
		   			   	    		*/
		   			   	    	break;
		   			   	    case "d":
		   			   	    	System.out.println("\t\tFail to drill selected:\n");
		   			   	    	/*
		   			   	    	Asteroid asteroid1_3d=new Asteroid();
		   			   	    	asteroid1_3d.setRockLayerThickness(0);
		   			   	    	
		   			   	    	Settler settler1_3d=new Settler();
		   			   	    	
		   			   	    	settler1_3d.setPlace(asteroid1_3d);
		   			   	    	asteroid1_3d.accept(settler1_3d);
		   			   	    	
		   			   	    	settler1_3d.drill();
		   			   	    	
		   			   	    	if(asteroid1_3d.getRockLayerThickness()==0)
		   			   	    		System.out.println("Successful fail to drill test");
		   			   	    		*/
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
								Timer timer = new Timer();

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
