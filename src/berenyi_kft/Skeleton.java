package berenyi_kft;

import java.util.Scanner;

public class Skeleton {
	
	
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
		   			   	    	
		   			   	    	break;
		   			   	    case "b":
		   			   	    	System.out.println("\t\tMove through TeleportingGate selected:\n");
		   			   	    	break;
		   			   	    case "c":
		   			   	    	System.out.println("\t\tFail to use TeleportingGate selected:\n");
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
		   			   	    	break;
		   			   	    case "b":
		   			   	    	System.out.println("\t\tDrill radioactive asteroid�s last layer selected:\n");
		   			   	    	break;
		   			   	    case "c":
		   			   	    	System.out.println("\t\tDrill icy asteroid�s last layer selected:\n");
		   			   	    	break;
		   			   	    case "d":
		   			   	    	System.out.println("\t\tFail to drill selected:\n");
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
