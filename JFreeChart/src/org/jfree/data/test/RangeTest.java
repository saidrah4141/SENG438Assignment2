package org.jfree.data.test;

import static org.junit.Assert.*; 
import org.jfree.data.Range; 
import org.junit.*;

public class RangeTest {
    private Range exampleRange;
    @BeforeClass public static void setUpBeforeClass() throws Exception {
    }


    @Before
    public void setUp() throws Exception { exampleRange = new Range(-1, 1);
    }


    @Test
    public void centralValueShouldBeZero() {
        assertEquals("The central value of -1 and 1 should be 0",
        0, exampleRange.getCentralValue(), .000000001d);
    }
    
    
    @Test
    public void testCombineValidRanges() {
        Range range1 = new Range(3.0, 5.0);
        Range range2 = new Range(2.0, 7.0);

        Range combined = Range.combine(range1, range2);
        
        assertEquals("The lower range should be 2", 2.0, combined.getLowerBound(), 1e-9);
        assertEquals("The upper range should be 7", 7.0, combined.getUpperBound(), 1e-9);
    }
    
    @Test
    public void testCombineOneNull() {
    	
    }
    
    @Test
    public void testCombineTwoNull() {
    	
    }
    
    
    @Test
    public void testConstrain() {
    	Range range1 = new Range(5.0,10.0);
    	assertEquals("The value should just return iteself", 6.0, range1.constrain(6.0),1e-9);
    	
    }
    
    @Test
    public void testConstrainLessThanLower() {
    	Range range1 = new Range(5.0,10.0);
    	assertEquals("The value less than the lower bound should return the lower bound",5.0,range1.constrain(1.0),1e-9);
    }

    @Test
    public void testConstrainGreaterThanUpper() {
    	Range range1 = new Range(5.0,10.0);
    	assertEquals("The value greater than the upper bound should return the upper bound",10.0,range1.constrain(11.0),1e-9);
    }

    @Test
    public void testContainsWithinRange() {
    	Range range1=new Range(5.0,10.0);
    	assertTrue("Should return true because value is within range", range1.contains(6.0));
    }
    @Test
    public void testContainsOutsideRange() {
    	Range range1=new Range(5.0,10.0);
    	assertFalse("Should return false because value is outside range", range1.contains(11.0));
    }
    
    @Test
    public void testEqualsEquivalentRanges() {
    	Range range1 = new Range(1.0,3.0);
    	Range range2 = new Range(1.0,3.0);
    	
    	assertTrue("The ranges are equals and should return true", range1.equals(range2));
    	
    }
    
    @Test
    public void testNonEquivalentRanges() {
    	Range range1 = new Range(1.0,3.0);
    	Range range2 = new Range(2.0,3.0);
    	
    	assertFalse("The ranges are not equal and should return false", range1.equals(range2));
    	
    }
    
    @Test
    public void testExpand() {
        Range range1 = new Range(2.0, 6.0);
       
        Range expandedRange = Range.expand(range1, 0.25, 0.5);

        assertEquals("Lower bound should be 1", 1.0, expandedRange.getLowerBound(), .000000001d);
        assertEquals("Upper bound should be 8", 8.0, expandedRange.getUpperBound(), .000000001d);
    }
    
    @Test
    public void testExpandToInclude() {
    	Range range1 = new Range(2.0, 6.0);
        
        Range expandedRange = Range.expandToInclude(range1, 8.0);
        assertTrue("The range does not containg the included value",expandedRange.contains(8.0));
        
    }
    
    @Test
    public void testGetLength() {
    	Range range1=new Range(1.0,5.0);
    	
    	assertEquals("The length of the range should be 4", 4, range1.getLength(),.000000001d);
    }
    
    @Test
    public void testGetLowerBound() {
    	Range range1=new Range(1.0,5.0);
    	
    	assertEquals("The lowerbound should be 1.0",1,range1.getLowerBound(),.000000001d);
    
    }
    
    
    @Test
    public void testGetUpperBound() {
    	Range range1=new Range(1.0,5.0);
    	
    	assertEquals("The upperbound should be 5.0",5,range1.getUpperBound(),.000000001d);
    
    }
    
    @Test
    public void testHashCodeSameRange() {
    	 Range range1 = new Range(2.0, 6.0);
         Range range2 = new Range(2.0, 6.0);
 
         
         assertEquals("Hash codes should match",range1.hashCode(),range2.hashCode(),.000000001d);

    }
    
    
    @Test
    public void testHashCodeDifferentRange() {

        Range range1 = new Range(2.0, 6.0);
        Range range2 = new Range(1.0, 5.0);
        
        
        assertFalse("Hash codes should not match", range1.hashCode()==range2.hashCode());
    }
    
    
    
    @Test
    public void testIntersects() {
    	Range range1 = new Range(1.0,5.0);
    	assertTrue("Should return true since it intersects",range1.intersects(2, 3));
    }
    
    @Test
    public void testNotIntersects() {
    	Range range1 = new Range(1.0,5.0);
    	assertFalse("Should return false since it does not intersects",range1.intersects(6, 8));
    }
    
    @Test
    public void testShift() {
    	Range range1 = new Range(1.0,5.0);
    	Range shiftedRange = Range.shift(range1, 2);
    	
    	
    	assertEquals("The lowerbound should be 3.0",3,shiftedRange.getLowerBound(),.000000001d);
    	assertEquals("The upperbound should be 7.0",7,shiftedRange.getUpperBound(),.000000001d);
        
    }
    
    @Test
    public void testShiftWithZeroCrossing() {
    	Range range1 = new Range(1.0,5.0);
    	Range shiftedRange = Range.shift(range1, -2,true);
    	
    	
    	assertEquals("The lowerbound should be -1.0",-1,shiftedRange.getLowerBound(),.000000001d);
    	assertEquals("The upperbound should be 3.0",3,shiftedRange.getUpperBound(),.000000001d);
        
    }
    
    @Test
    public void testShiftWithoutZeroCrossing() {
    	Range range1 = new Range(1.0,5.0);
    	Range shiftedRange = Range.shift(range1, -2,false);
    	
    	
    	assertEquals("The lowerbound should be 0.0",0,shiftedRange.getLowerBound(),.000000001d);
    	assertEquals("The upperbound should be 3.0",3,shiftedRange.getUpperBound(),.000000001d);
        
   }
    
   @Test
   public void testToString() {
	   Range range1 = new Range(1.0,5.0);
	   assertEquals("not equal","Range[1.0,5.0]", range1.toString());
   }
    	
    
    
    
    
    
    @After
    public void tearDown() throws Exception {
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }
}
