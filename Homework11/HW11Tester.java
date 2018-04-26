
import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import hw5.Cal;

import static org.junit.Assert.*;
import java.util.*;


//int cal (int month1, day1, month2, day2, year)

public class HW11Tester {
	
	@Test 
	public void P1R1() {
		int retVal = Cal.cal(5, 20, 5, 20, 2013);
		assertTrue("P1R1 failed " + retVal, retVal == 0);
	}
	
	@Test 
	public void P1R2() {
		int retVal = Cal.cal(5, 20, 6, 20, 2013);
		assertTrue("P1R2 failed " + retVal, retVal == 31);
	}
	
	@Test 
	public void P2R3() {
		int retVal = Cal.cal(2, 20, 3, 20, 2013);
		assertTrue("P2R3 failed " + retVal, retVal == 28);
	}
	
	@Test 
	public void P2R5() {
		int retVal = Cal.cal(2, 20, 3, 20, 100);
		assertTrue("P2R5 failed " + retVal, retVal == 28);
	}
	
	@Test 
	public void P2R6() {
		int retVal = Cal.cal(2, 20, 3, 20, 400);
		assertTrue("P2R6 failed " + retVal, retVal == 29);
	}
	
	@Test 
	public void P2R7() {
		int retVal = Cal.cal(2, 20, 3, 20, 8);
		assertTrue("P2R7 failed " + retVal, retVal == 29);
	}
	
}
