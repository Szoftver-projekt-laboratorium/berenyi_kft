package berenyi_kft_test;

import java.io.File;

/**
 * Tesztelest vegzo segedosztalyok a prototipus programhoz
 * @author berenyi_kft
 */
public class Tester {
	private static final int testCount = 38;
	
	public boolean compare(File f1, File f2) {
		// Bármely két fájl ugyanolyan...
		return true;
	}
	
	public static void testOne(int testNum) {
		
	}
	
	public static void testAll() {
		for (int i = 0; i < Tester.testCount; i++) {
			testOne(i);
		}
	}
	
	public static void testerMain(String[] args) {
		// test <test_num>, test vagy exit parancsot var és dolgoz fel
	}
}
