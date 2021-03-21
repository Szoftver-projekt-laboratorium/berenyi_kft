package berenyi_kft;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Skeleton {
	
	private static Map<Object, String> ids = new HashMap();
	
	

	 public static void main(String args[])
	  {
		 	Game game = new Game();
		 	
		 	Recipe aiRobot = new Recipe();
	    	Recipe gatePair = new Recipe();
	    	Recipe spaceBase = new Recipe();
	    	game.addRecipe(aiRobot);
	    	game.addRecipe(gatePair);
	    	game.addRecipe(spaceBase);
	    	
	    	Ice ice = new Ice();
	    	Iron iron = new Iron();
	    	Coal coal = new Coal();
	    	Uran uran = new Uran();
	    	spaceBase.addResource(ice);
	    	spaceBase.addResource(iron);
	    	spaceBase.addResource(coal);
		 	
		 	Settler s1 = new Settler();
		 	ids.put(s1, "settler1");
	    	game.addSettler(s1);
	    	Settler s2 = new Settler();
	    	game.addSettler(s2);
	    	
		 	Asteroid a1 = new Asteroid();
		 	ids.put(a1,"asteroid1");
		 	game.addAsteroid(a1);
	    	a1.setGame(game);
	    	a1.accept(s1);
	    	s1.setPlace(a1);
	    	a1.accept(s2);
	    	s2.setPlace(a1);
	    	
	    	
	    	Asteroid a2 = new Asteroid();
	    	ids.put(a2,"asteroid2");
	    	game.addAsteroid(a2);
	    	a2.setGame(game);
	    	
	    	Asteroid a3 = new Asteroid();
	    	ids.put(a3,"asteroid3");
	    	game.addAsteroid(a3);
	    	a3.setGame(game);
	    	
	    	//a1 a2 szomszed
	    	a1.accept(a2);
	    	a2.accept(a1);
	    	
	    	TeleportingGate tg1 = new TeleportingGate();
	    	tg1.asteroid = a1;
	    	
	    	TeleportingGate tg2 = new TeleportingGate();
	    	tg2.asteroid = a3;
	    	
	    	tg1.setPair(tg2);
	    	tg2.setPair(tg1);
		 
		 
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
		   			   	    	
		   			   	    	int dir = 0;
		   			   	    	s1.move(dir);
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
		   			   	    	 * expected layersize = 4
		   			   	    	 * print layersize
		   			   	    	 */
		   			   	    	/*---------------------------------------------------------
		   			   	    	a1.setRockLayerThickness(2);
		   			   	    	
		   			   	    	s1.setPlace(a1);
		   			   	    	s1.drill();
		   			   	    	System.out.println(a1.getRockLayerThickness());
		   			   	    	if(a1.getRockLayerThickness()==1) {
		   			   	    		System.out.println("Successful drilling");
		   			   	    	}
		   			   	    	*///------------------------------------------------------------
		   			   	    	break;
		   			   	    case "b":
		   			   	    	System.out.println("\t\tDrill radioactive asteroid's last layer selected:\n");
		   			   	    	/*
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
		   			   	    		*/
		   			   	    	break;
		   			   	    case "c":
		   			   	    	
		   			   	    	System.out.println("\t\tDrill icy asteroid's last layer selected:\n");
		   			   	    	/*
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
		   			   	    		*/
		   			   	    	break;
		   			   	    case "d":
		   			   	    	System.out.println("\t\tFail to drill selected:\n");
		   			   	    	/*
		   			   	    	Asteroid asteroid4=new Asteroid();
		   			   	    	asteroid4.setRockLayerThickness(0);
		   			   	    	
		   			   	    	Settler settler4=new Settler();
		   			   	    	
		   			   	    	settler4.setPlace(asteroid4);
		   			   	    	asteroid4.accept(settler4);
		   			   	    	
		   			   	    	settler4.drill();
		   			   	    	
		   			   	    	if(asteroid4.getRockLayerThickness()==0)
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
		   			   	    	break;
		   			   	    case "b":
		   			   	    	System.out.println("\t\tFail to build selected:\n");
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
		   			   	    	break;
		   			   	    case "b":
		   			   	    	System.out.println("\t\tFail to build TeleportingGatePair selected:\n");
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
