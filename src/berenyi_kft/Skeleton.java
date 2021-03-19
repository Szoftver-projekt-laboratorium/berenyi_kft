package berenyi_kft;

import java.util.Scanner;

public class Skeleton {

	 public static void main(String args[])
	  {
	   	System.out.println("?dv?z?llek a berenyi_kft Skeleton tesztel? programj?ban!\n ");
	   	
	   	System.out.println("Tesztesetek: \n\n");
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
	   	String choice = scan.nextLine();
	   	String choice2;
	   	System.out.println("Bej?n: "+choice);
	   	while (choice.equals("10") == false) {
		   	switch (choice){
		   	    case "1":
		   	    	
		   	    	System.out.println("Win Game tesztesetet v?lasztottad.\n");
   
		   	    case "2":
		   	    	
		   	    	System.out.println("Move Spaceship tesztesetet v?lasztottad.\n"); 
		   	    	
		   	    	System.out.println("	a: Move to asteroid\n");
		   	    	System.out.println("	b: Move through TeleportingGate\n");
		   	    	System.out.println("	c: Fail to use TeleportingGate\n");
		   	    	System.out.println("	d: Vissza a f?men?be.\n");
		   	    	
		   	    	choice2 = scan.nextLine();
		   	    	while (choice2.equals("d") == false) {
		   			   	switch (choice2){
		   			   	    case "a":
		   			   	    	System.out.println("		Move to asteroid tesztel?se:\n");
		   			   	    	break;
		   			   	    case "b":
		   			   	    	System.out.println("		Move through TeleportingGate tesztel?se:\n");
		   			   	    	break;
		   			   	    case "c":
		   			   	    	System.out.println("		Fail to use TeleportingGate tesztel?se:\n");
		   			   	    	break;
		   			   	    default :
		   			   	    	System.out.println("Invalid teszteset!\n");
		   			   	}
		   			   	break;
		   	    	}
		   	        break;
		   	    case "3":
		   	    	System.out.println("Drill Asteroid tesztesetet v?lasztottad.\n");
		   	    	
		   	    	System.out.println("	a: Drill one layer\n");
		   	    	System.out.println("	b: Drill radioactive asteroid?s last layer\n");
		   	    	System.out.println("	c: Drill icy asteroid?s last layer\n");
		   	    	System.out.println("	d: Fail to drill\n");
		   	    	System.out.println("	e: Vissza a f?men?be.\n");
		   	    	
		   	    	choice2 = scan.nextLine();
		   	    	while (choice2.equals("e") == false) {
		   			   	switch (choice2){
		   			   	    case "a":
		   			   	    	System.out.println("		Drill one layer tesztel?se:\n");
		   			   	    	break;
		   			   	    case "b":
		   			   	    	System.out.println("		Drill radioactive asteroid?s last layer tesztel?se:\n");
		   			   	    	break;
		   			   	    case "c":
		   			   	    	System.out.println("		Drill icy asteroid?s last layer tesztel?se:\n");
		   			   	    	break;
		   			   	    case "d":
		   			   	    	System.out.println("		Fail to drill tesztel?se:\n");
		   			   	    	break;
		   			   	    default :
		   			   	    	System.out.println("Invalid teszteset!\n");
		   			   	}
		   			   	break;
		   	    	}
		   	        break;
		   		   	
		   	    case "4":
		   	    	System.out.println("Restore Resource tesztesetet v?lasztottad.\n");
		   		   	
		   	    	System.out.println("	a: Restore Resource\n");
		   	    	System.out.println("	b: Fail to restore\n");
		   	    	System.out.println("	c: Restore Ice when Sun is close\n");
		   	    	System.out.println("	d: Restore Uran when Sun is close\n");
		   	    	System.out.println("	e: Vissza a f?men?be.\n");
		   	    	
		   	    	choice2 = scan.nextLine();
		   	    	while (choice2.equals("e") == false) {
		   			   	switch (choice2){
		   			   	    case "a":
		   			   	    	System.out.println("		Restore Resource tesztel?se:\n");
		   			   	    	break;
		   			   	    case "b":
		   			   	    	System.out.println("		Fail to restore tesztel?se:\n");
		   			   	    	break;
		   			   	    case "c":
		   			   	    	System.out.println("		Restore Ice when Sun is close tesztel?se:\n");
		   			   	    	break;
		   			   	    case "d":
		   			   	    	System.out.println("		Restore Uran when Sun is close tesztel?se:\n");
		   			   	    	break;
		   			   	    default :
		   			   	    	System.out.println("Invalid teszteset!\n");
		   			   	}
		   			   	break;
		   	    	}
		   	        break;
		   	        
		   	    case "5":
		   	    	System.out.println("Mine Resource tesztesetet v?lasztottad.\n");
		   		   			   	    	
		   	    	System.out.println("	a: Mine Resource\n");
		   	    	System.out.println("	b: Fail to mine\n");
		   	    	System.out.println("	c: Vissza a f?men?be.\n");
		   	    	
		   	    	choice2 = scan.nextLine();
		   	    	while (choice2.equals("c") == false) {
		   			   	switch (choice2){
		   			   	    case "a":
		   			   	    	System.out.println("		Mine Resource tesztel?se:\n");
		   			   	    	break;
		   			   	    case "b":
		   			   	    	System.out.println("		Fail to mine tesztel?se:\n");
		   			   	    	break;

		   			   	    default :
		   			   	    	System.out.println("Invalid teszteset!\n");
		   			   	}
		   			   	break;
		   	    	}
		   	        break;
		   	        
		   	    case "6":
		   	    	System.out.println("Build AI Robot tesztesetet v?lasztottad.\n");
		   		   	
		   	    	System.out.println("	a: Build robot\n");
		   	    	System.out.println("	b: Fail to build robot\n");
		   	    	System.out.println("	c: Vissza a f?men?be.\n");
		   	    	
		   	    	choice2 = scan.nextLine();
		   	    	while (choice2.equals("c") == false) {
		   			   	switch (choice2){
		   			   	    case "a":
		   			   	    	System.out.println("		Build robot tesztel?se:\n");
		   			   	    	break;
		   			   	    case "b":
		   			   	    	System.out.println("		Fail to build tesztel?se:\n");
		   			   	    	break;

		   			   	    default :
		   			   	    	System.out.println("Invalid teszteset!\n");
		   			   	}
		   			   	break;
		   	    	}
		   	        break;
		   	    case "7":
		   	    	System.out.println("Build TeleportingGatePair tesztesetet v?lasztottad.\n");
		   		   	
		   	    	System.out.println("	a: Build TeleportingGatePair\n");
		   	    	System.out.println("	b: Fail to build TeleportingGatePair\n");
		   	    	System.out.println("	c: Vissza a f?men?be.\n");
		   	    	
		   	    	choice2 = scan.nextLine();
		   	    	while (choice2.equals("c") == false) {
		   			   	switch (choice2){
		   			   	    case "a":
		   			   	    	System.out.println("		Build TeleportingGatePair tesztel?se:\n");
		   			   	    	break;
		   			   	    case "b":
		   			   	    	System.out.println("		Fail to build TeleportingGatePair tesztel?se:\n");
		   			   	    	break;

		   			   	    default :
		   			   	    	System.out.println("Invalid teszteset!\n");
		   			   	}
		   			   	break;
		   	    	}
		   	        break;
		   	    case "8":
		   	    	System.out.println("Place TeleportingGate tesztesetet v?lasztottad.\n");
		   	    	
		   	    	System.out.println("	a: Place TeleportingGate\n");
		   	    	System.out.println("	b: Fail to place TeleportingGate\n");
		   	    	System.out.println("	c: Vissza a f?men?be.\n");
		   	    	
		   	    	choice2 = scan.nextLine();
		   	    	while (choice2.equals("c") == false) {
		   			   	switch (choice2){
		   			   	    case "a":
		   			   	    	System.out.println("		Place TeleportingGate tesztel?se:\n");
		   			   	    	break;
		   			   	    case "b":
		   			   	    	System.out.println("		Fail to place TeleportingGate tesztel?se:\n");
		   			   	    	break;

		   			   	    default :
		   			   	    	System.out.println("Invalid teszteset!\n");
		   			   	}
		   			   	break;
		   	    	}
		   	        break;
		   	    case "9":
		   	    	System.out.println("Call Sunstorm tesztesetet v?lasztottad.\n");
		   	    	
		   	    	System.out.println("	a: Asteroid is mined\n");
		   	    	System.out.println("	b: Asteroid is not mined\n");
		   	    	System.out.println("	c: Vissza a f?men?be.\n");
		   	    	
		   	    	choice2 = scan.nextLine();
		   	    	while (choice2.equals("c") == false) {
		   			   	switch (choice2){
		   			   	    case "a":
		   			   	    	System.out.println("		Asteroid is mined tesztel?se:\n");
		   			   	    	break;
		   			   	    case "b":
		   			   	    	System.out.println("		Asteroid is not mined tesztel?se:\n");
		   			   	    	break;

		   			   	    default :
		   			   	    	System.out.println("Invalid teszteset!\n");
		   			   	}
		   			   	break;
		   	    	}
		   	        break;

		   	    default :
		   	    	System.out.println("Invalid input!\n");
	
		   	}
		   	System.out.println("\n\nA tesztel?s lefutott, v?lassz k?vetkez?t (1-9) vagy l?pj ki (10).\n\n");
		   	choice = scan.nextLine();
	  }
   	  System.out.println("Kil?p?s...");
   	  scan.close();
   	  //System.exit(0);
}
}
