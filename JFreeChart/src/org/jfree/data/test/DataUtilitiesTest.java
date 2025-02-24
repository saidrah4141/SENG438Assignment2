package org.jfree.data.test;

import static org.junit.Assert.*;

import org.jfree.data.DataUtilities;
import org.jfree.data.Values2D;
import org.jfree.data.KeyedValues;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Before;
import org.junit.Test;

public class DataUtilitiesTest extends DataUtilities {
	
	 private Mockery mockingContext;
     private Values2D values;
     private KeyedValues kValues;
     private KeyedValues resultValue;
	 
	 @Before
	 public void setUp() throws Exception {
		 mockingContext = new Mockery();
	     values = mockingContext.mock(Values2D.class);
	     kValues = mockingContext.mock(KeyedValues.class);
	 }
	 
	 @Test
	 public void calculateColumnTotalForTwoValues() {

	     mockingContext.checking(new Expectations() {
	         {
	             one(values).getRowCount();
	             will(returnValue(2));
	             one(values).getValue(0, 0);
	             will(returnValue(7.5));
	             one(values).getValue(1, 0);
	             will(returnValue(2.5));
	         }
	     });
	     double result = DataUtilities.calculateColumnTotal(values, 0);
	     // verify
	     assertEquals(10.0, result, .000000001d);
	     // tear-down: NONE in this test method
	 }
	 
	 @Test
	 public void calculateRowTotalForTwoValues() {
	     mockingContext.checking(new Expectations() {
	         {
	             one(values).getColumnCount();
	             will(returnValue(2));
	             one(values).getValue(1, 0);
	             will(returnValue(8.5));
	             one(values).getValue(1, 1);
	             will(returnValue(1.5));
	         }
	     });
	     double result2 = DataUtilities.calculateRowTotal(values, 1);
	     // verify
	     assertEquals(10.0, result2, .000000001d);    
	     // tear-down: NONE in this test method
	 }
	 
	 @Test
	 public void getCumulativePercentagesForThreeValues() {
		 mockingContext.checking(new Expectations() {
			 {
				 one(kValues).getItemCount();
				 will(returnValue(3));
				 one(kValues).getValue(0);
				 will(returnValue(5.0));
				 one(kValues).getValue(1);
				 will(returnValue(9.0));
				 one(kValues).getValue(2);
				 will(returnValue(2.0));
			 }	 
		 });
		 
		 resultValue = DataUtilities.getCumulativePercentages(kValues);
		 
		 
		 assertEquals(0.3125, resultValue.getValue(0).doubleValue(), .000000001d);
		 assertEquals(0.875, resultValue.getValue(1).doubleValue(), .000000001d);
	     assertEquals(1.0, resultValue.getValue(2).doubleValue(), .000000001d);
	 }
	 
	 @Test
	 public void createNumberArrayWithThreeValues() {
		 double[] array = {10,20,30};
		 
		 Number[] resultArray = DataUtilities.createNumberArray(array);
		 
		 assertEquals(array[0], resultArray[0]);
		 assertEquals(array[1], resultArray[1]);
		 assertEquals(array[2], resultArray[2]); 
	 }
	 
	 @Test
	 public void createNumberArray2DWithTwoArrays() {
		 double[][] array2D = {{10,20}, {11,22}};
		 
		 Number[][] resultArray2D = DataUtilities.createNumberArray2D(array2D);
		 
		 assertEquals(array2D[0][0], resultArray2D[0][0]);
		 assertEquals(array2D[0][1], resultArray2D[0][1]);
		 assertEquals(array2D[1][0], resultArray2D[1][0]);
		 assertEquals(array2D[1][1], resultArray2D[1][1]);
	 }

}
