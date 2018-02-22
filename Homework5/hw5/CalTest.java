package hw5;

import org.junit.*;
import static org.junit.Assert.*;

/*
 * Repeat following for Leap Year, Non-Leap Year
 * 		Repeat for high number year, low number year
 * 			Days in same month
        	Days in Feb and March
        	Day in Feb and non March
        	Days in two consecutive non Feb month
        	Days in two non consecutive non Feb month
        	Same day/same month
 */
public class CalTest {
	
	//int cal (int month1, day1, month2, day2, year)
	
	@Test
	public void HighLeapYearSameMonth()
	{
		int retVal = Cal.cal(3, 23, 3, 28, 7600);
		assertTrue("High Number Leap Year with days in same month fail with " + retVal, retVal == 5);
	}	
	
	
	@Test
	public void HighLeapYearFebMarch()
	{
		int retVal = Cal.cal(2, 29, 3, 28, 7600);
		assertTrue("High Number Leap Year with days in Feb 3rd then March 28th fail with" + retVal, retVal == 28);
		
	}
	
	@Test
	public void HighLeapYearFebJune()
	{
		int retVal = Cal.cal(2, 3, 6, 28, 7600);
		assertTrue("High Number Leap Year with days in Feb 3rd then June 28th fail with " + retVal, retVal == 146);
		
	}
	
	@Test
	public void HighLeapYearAprilAugust()
	{
		int retVal = Cal.cal(4, 15, 8, 25, 7600);
		assertTrue("High Number Leap Year with days in April 15th and August 25th fail with " + retVal, retVal == 132);
		
	}
	
	@Test
	public void HighLeapYearNovemberDecember()
	{
		int retVal = Cal.cal(11, 23, 12, 28, 7600);
		assertTrue("High Number Leap Year with days in November 23rd and December 28th fail " + retVal, retVal == 35);
		
	}
	
	@Test
	public void HighLeapYearSameDay()
	{
		int retVal = Cal.cal(2, 29, 2, 29, 7600);
		assertTrue("High Number Leap Year with same day Feb 29th fail " + retVal, retVal == 0);
		
	}
//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~	
	@Test
	public void LowLeapYearSameMonth()
	{
		int retVal = Cal.cal(3, 23, 3, 28, 4);
		assertTrue("Low Number Leap Year with days in same month fail " + retVal, retVal == 5);
	}	
	
	
	@Test
	public void LowLeapYearFebMarch()
	{
		int retVal = Cal.cal(2, 3, 3, 28, 4);
		assertTrue("Low Number Leap Year with days in Feb 3rd then March 28th fail " + retVal, retVal == 54);
		
	}
	
	@Test
	public void LowLeapYearFebJune()
	{
		int retVal = Cal.cal(2, 3, 6, 28, 4);
		assertTrue("Low Number Leap Year with days in Feb 3rd then June 28th fail " + retVal, retVal == 146);
		
	}
	
	@Test
	public void LowLeapYearAprilAugust()
	{
		int retVal = Cal.cal(4, 15, 8, 25, 4);
		assertTrue("Low Number Leap Year with days in April 15th and August 25th fail " + retVal, retVal == 132);
		
	}
	
	@Test
	public void LowLeapYearNovemberDecember()
	{
		int retVal = Cal.cal(11, 23, 12, 28, 4);
		assertTrue("Low Number Leap Year with days in November 23rd and December 28th fail with " + retVal, retVal == 35);
		
	}
	
	@Test
	public void LowLeapYearSameDay()
	{
		int retVal = Cal.cal(2, 29, 2, 29, 4);
		assertTrue("Low Number Leap Year with same day Feb 29th fail with " + retVal, retVal == 0);
		
	}
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	@Test
	public void HighNonLeapYearSameMonth()
	{
		int retVal = Cal.cal(3, 23, 3, 28, 8001);
		assertTrue("High Number NonLeap Year with days in same month fail with " + retVal, retVal == 5);
	}	
	
	
	@Test
	public void HighNonLeapYearFebMarch()
	{
		int retVal = Cal.cal(2, 3, 3, 28, 8001);
		assertTrue("High Number NonLeap Year with days in Feb 3rd then March 28th fail with " + retVal, retVal == 53);
		
	}
	
	@Test
	public void HighNonLeapYearFebJune()
	{
		int retVal = Cal.cal(2, 3, 6, 28, 8001);
		assertTrue("High Number NonLeap Year with days in Feb 3rd then June 28th fail with " + retVal, retVal == 145);
		
	}
	
	@Test
	public void HighNonLeapYearAprilAugust()
	{
		int retVal = Cal.cal(4, 15, 8, 25, 8001);
		assertTrue("High Number NonLeap Year with days in April 15th and August 25th fail with " + retVal, retVal == 132);
		
	}
	
	@Test
	public void HighNonLeapYearNovemberDecember()
	{
		int retVal = Cal.cal(11, 23, 12, 28, 8001);
		assertTrue("High Number NonLeap Year with days in November 23rd and December 28th fail with " + retVal, retVal == 35);
		
	}
	
	@Test
	public void HighNonLeapYearSameDay()
	{
		int retVal = Cal.cal(2, 28, 2, 28, 8001);
		assertTrue("High Number NonLeap Year with same day Feb 28th fail with " + retVal, retVal == 0);
		
	}
//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~	
	@Test
	public void LowNonLeapYearSameMonth()
	{
		int retVal = Cal.cal(3, 23, 3, 28, 1);
		assertTrue("Low Number NonLeap Year with days in same month fail with " + retVal, retVal == 5);
	}	
	
	
	@Test
	public void LowNonLeapYearFebMarch()
	{
		int retVal = Cal.cal(2, 3, 3, 28, 1);
		assertTrue("Low Number NonLeap Year with days in Feb 3rd then March 28th fail with " + retVal, retVal == 53);
		
	}
	
	@Test
	public void LowNonLeapYearFebJune()
	{
		int retVal = Cal.cal(2, 3, 6, 28, 1);
		assertTrue("Low Number NonLeap Year with days in Feb 3rd then June 28th fail with " + retVal, retVal == 145);
		
	}
	
	@Test
	public void LowNonLeapYearAprilAugust()
	{
		int retVal = Cal.cal(4, 15, 8, 25, 1);
		assertTrue("Low Number NonLeap Year with days in April 15th and August 25th fail with " + retVal, retVal == 132);
		
	}
	
	@Test
	public void LowNonLeapYearNovemberDecember()
	{
		int retVal = Cal.cal(11, 23, 12, 28, 1);
		assertTrue("Low Number NonLeap Year with days in November 23rd and December 28th fail with " + retVal, retVal == 35);
		
	}
	
	@Test
	public void LowNonLeapYearSameDay()
	{
		int retVal = Cal.cal(2, 28, 2, 28, 1);
		assertTrue("Low Number NonLeap Year with same day Feb 28th fail with " + retVal, retVal == 0);
		
	}
}