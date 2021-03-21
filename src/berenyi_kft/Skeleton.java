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
		   	    	
		   	    	Game game = new Game();
		   	    	Asteroid a1 = new Asteroid();
		   	    	Settler s1 = new Settler();
		   	    	Settler s2 = new Settler();
		   	    	Recipe spaceBase = new Recipe();
		   	    	game.addReceipt(new Recipe());
		   	    	game.addReceipt(new Recipe());
		   	    	game.addReceipt(spaceBase);
		   	    	
		   	    	Ice ice = new Ice();
		   	    	Iron iron = new Iron();
		   	    	Coal coal = new Coal();
		   	    	spaceBase.addResource(ice);
		   	    	spaceBase.addResource(iron);
		   	    	spaceBase.addResource(coal);
		   	    	a1.accept(s1);
		   	    	a1.accept(s2);
		   	    	a1.setGame(game);
		   	    	
		   	    	a1.checkSpaceBase();
		   	    	
		   	    	
		   	    	
		   	    	
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
		   			   	    	
/**/		   			   	    Asteroid a11=new Asteroid();
		   			   	    	a11.setRockLayerThickness(2);
/**/		   			   	    Settler s11=new Settler();
		   			   	    	s11.setPlace(a11);
		   			   	    	s11.drill();
		   			   	    	System.out.println(a11.getRockLayerThickness());
		   			   	    	if(a11.getRockLayerThickness()==1) {
		   			   	    		System.out.println("Successful drilling");
		   			   	    	}
		   			   	    	break;
		   			   	    case "b":
		   			   	    	System.out.println("\t\tDrill radioactive asteroid's last layer selected:\n");
		   			   	    	
/**/	   			   	    	Game game1=new Game();
		   			   	    	Asteroid asteroid1=new Asteroid();
		   			   	    	Asteroid asteroid2=new Asteroid();
		   			   	    	
		   			   	    	Sun sun=new Sun();
		   			   	    	sun.addNeighbor(asteroid1);
		   			   	    	
		   			   	    	asteroid1.addResource(new Uran());
		   			   	    	asteroid1.setRockLayerThickness(1);
		   			   	    	
		   			   	    	asteroid1.setSun(sun);
		   			   	    	asteroid2.setSun(sun);
		   			   	    	
		   			   	    	asteroid1.setGame(game1);
		   			   	    	asteroid2.setGame(game1);
		   			   	    	
		   			   	    	asteroid1.addNeighbor(asteroid2);
		   			   	    	asteroid2.addNeighbor(asteroid1);
		   			   	    	
		   			   	    	game1.addAsteroid(asteroid1);
		   			   	    	game1.addAsteroid(asteroid2);
		   			   	    	game1.setSun(sun);
		   			   	    	
		   			   	    	AIRobot robot=new AIRobot();
		   			   	    	robot.setPlace(asteroid1);
		   			   	    
		   			   	    	
		   			   	    	Settler settler1=new Settler();
		   			   	    	Settler settler2=new Settler();
		   			   	    	
		   			   	    	asteroid1.accept(robot);
		   			   	    	asteroid1.accept(settler1);
		   			   	    	asteroid2.accept(settler2);
		   			   	    	
		   			   	    	game1.addSettler(settler1);
		   			   	    	game1.addSettler(settler2);
		   			   	    	
		   			   	    	settler1.setGame(game1);
		   			   	    	settler2.setGame(game1);
		   			   	    	
		   			   	    	settler1.setPlace(asteroid1);
		   			   	    	settler2.setPlace(asteroid2);
		   			   	    	System.out.println(asteroid1.getSizeOfCharacters());
		   			   	    	settler1.drill();
		   			   	    	
		   			   	    	if(robot.getPlace()==asteroid2&&game1.getSizeOfSettlersAlive()==1&&
		   			   	    			game1.getSizeOfAsteroids()==1)
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
		   			   	    	//sun4.addNeighbor(a4);
	   			   	    	
	   			   	    		a4.setSun(sun4);
		   			   	    	s4.restore(r4);
		   			   	    	
		   			   	    	if(a4.getResource().equals(r4) ) {
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
		   			   	    	//sun4.addNeighbor(a4);
	 			   	    	
	 			   	    		a41.setSun(sun41);
	 			   	    		
		   			   	    	s41.restore(r41);
		   			   	    	
		   			   	    	if(s41.getCollectedResources().isEmpty() == false) {
		   			   	    		System.out.println("Successful Test!\nSettler failed to restore Resource, because it's not drilled\n");
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
		   			   	    	
		   			   	    	if(a411.getResource() == null) {
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
		   			   	    	
		   			   	    	if(game4111.getAsteroids().isEmpty()) {
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
		   			   	    	//ids.put(s1, "settler1");
		   			   	    	game5.addSettler(s5);
		   			   	    	Asteroid a5 = new Asteroid();
		   			   	    	game5.addAsteroid(a5);
		   			   	    	a5.setGame(game5);
		   			   	    	//ids.put(a1,"asteroid1");
		   			   	    	Resource r5 = new Coal();
		   			   	    	a5.addResource(r5);
		   			   	    	a5.accept(s5);
		   			   	    	s5.setPlace(a5);
		   			   	    			  		
		   			   	    	a5.setRockLayerThickness(0);
		   			   	    	Recipe spaceBase5 = new Recipe();
		   			   	    	spaceBase5.addResource(new Ice());
		   			   	    	spaceBase5.addResource(new Iron());
		   			   	    	spaceBase5.addResource(new Coal());
		   			   	    	
		   			   	    	game5.addReceipt(new Recipe());
		   			   	    	game5.addReceipt(new Recipe());
		   			   	    	game5.addReceipt(spaceBase5);
    		   			   	   
		   			   	    	s5.mine();
		   			   	    	
		   			   	    	
		   			   	    	if(s5.getCollectedResources().contains(r5) && a5.isMined()) {
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
		   			   	    	
		   			   	    	game5b.addReceipt(new Recipe());
		   			   	    	game5b.addReceipt(new Recipe());
		   			   	    	game5b.addReceipt(spaceBase5b);
			   			   	   
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
