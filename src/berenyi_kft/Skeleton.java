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
		   			   	    	
		   			   	    	//letrehozunk mindent ami kell
		   			   	    	Settler s1_8a=new Settler();
		   			   	    	Asteroid a1_8a= new Asteroid();
		   			   	    	Asteroid a2_8a= new Asteroid();
		   			   	    	TeleportingGate tg1_8a= new TeleportingGate();
		   			   	    	TeleportingGate tg2_8a= new TeleportingGate();
		   			   	    	//kapukat paroztatunk
		   			   	    	tg1_8a.setPair(tg2_8a);
		   			   	    	tg2_8a.setPair(tg1_8a);
		   			   	    	//egyik aszteroidahoz bekotjuk az egyik kaput es a kapuhoz az aszteroidat
		   			   	    	a1_8a.accept(tg1_8a);
		   			   	    	tg1_8a.setAsteroid(a1_8a);
		   			   	    	//a masik kapu a telepesnel van, es a telepes a masik aszteroidan
		   			   	    	s1_8a.gatesCreated.add(tg2_8a);
		   			   	    	a2_8a.accept(s1_8a);
		   			   	    	s1_8a.setPlace(a2_8a);
		   			   	    	//eddig tartott az init resz
		   			   	    	
		   			   	    	s1_8a.releaseGate();
		   			   	    	
		   			   	    	
		   			   	    	
		   			   	    	if(a1_8a==a2_8a.getNeighbor(0) && a2_8a==a1_8a.getNeighbor(0))
		   			   	    		System.out.println("Successful 'TeleportingGate placing' test");
		   			   	    	
		   			   	    	break;
		   			   	    case "b":
		   			   	    	System.out.println("\t\tFail to place TeleportingGate selected:\n");
		   			   	    	
		   			   	    	Settler s1_8b =new Settler();
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
		   			   	    	//letrehoznk mindent ami kell
		   			   	    	Asteroid a1_9b= new Asteroid();
		   			   	    	Settler s1_9b= new Settler();
		   			   	    	AIRobot r_9b= new AIRobot();
		   			   	    	Game g_9b= new Game();
		   			   	    	Sun sun_9b= new Sun();
		   			   	    	//az aszteroidának beállítgatjuk a dolgokat
		   			   	    	a1_9b.accept(r_9b);
		   			   	    	a1_9b.accept(s1_9b);
		   			   	    	a1_9b.setSun(sun_9b);
		   			   	    	a1_9b.setGame(g_9b);
		   			   	    	//a settlernek is beállítjuk
		   			   	    	s1_9b.setPlace(a1_9b);
		   			   	    	s1_9b.setGame(g_9b);
		   			   	    	//az airoboton is beallitjuk az aszteriodajat
		   			   	    	r_9b.setPlace(a1_9b);
		   			   	    	//a gamehez hozzaadjuk a dolgokat
		   			   	    	g_9b.addAsteroid(a1_9b);
		   			   	    	g_9b.addSettler(s1_9b);
		   			   	    	g_9b.setSun(sun_9b);
		   			   	    	//sunhoz hozzaadjuk az aszteroidat
		   			   	    	sun_9b.addNeighbor(a1_9b);
		   			   	    	//aszteroida ne legyen kibányászott
		   			   	    	a1_9b.setRockLayerThickness(5);
		   			   	    	//eddig tartott az init
		   			   	    	
		   			   	    	sun_9b.step();
		   			   	    	
		   			   	    	if(g_9b.getSizeOfSettlersAlive()==0)
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
