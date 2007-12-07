package rlVizLib.utilities;

import rlVizLib.utilities.LogCompressor;
import java.util.Vector;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author mlee
 */
public class LogCompressorTest {

	public LogCompressorTest() {
	}

	@BeforeClass
	public static void setUpClass() throws Exception {
	}

	@AfterClass
	public static void tearDownClass() throws Exception {
	}

	@Before
	public void setUp() {
	}

	@After
	public void tearDown() {
	}


	@Test
	public void verifyinputEqualsOutput() {
		int A = 0;
		int B = 0;
		int amount = 0;
		int offset = 0;
		int packed = 0;
		int unpacked = 0;
		A = 5;
		B = 7;
		amount = 3;
		offset = 10;
		try {
			packed = LogCompressor.putSomeAintoB(A,B,amount,offset);
			unpacked = LogCompressor.getSomeAfromB(A,packed,amount,offset);
		} catch(Exception e) {
			System.err.println("Error: " + e);
			fail();
		}
		assertTrue(A == unpacked);
	}
	
	@Test
	public void verifyPackMultiple() {
		int A1_8bit = 37;
		int A2_4bit = 12;
		int A3_3bit = 5;
		int A1_out = 0;
		int A2_out = 0;
		int A3_out = 0;
		int A = 0;
		int B = 0;
		int amount = 0;
		int offset = 0;
		int packed = 0;
		int unpacked = 0;
		A = A1_8bit;
		B = 0;
		amount = 8;
		offset = 0;
		try {
			packed = LogCompressor.putSomeAintoB(A,B,amount,offset);
		} catch(Exception e) {
			System.err.println("Error: " + e);
			fail();
		}
		A = A2_4bit;
		amount = 4;
		offset = 8;
		try {
			packed = LogCompressor.putSomeAintoB(A,packed,amount,offset);
		} catch(Exception e) {
			System.err.println("Error: " + e);
			fail();
		}
		A = A3_3bit;
		amount = 3;
		offset = 12;
		try {
			packed = LogCompressor.putSomeAintoB(A,packed,amount,offset);
		} catch(Exception e) {
			System.err.println("Error: " + e);
			fail();
		}
		
		A = 0;
		amount = 8;
		offset = 0;
		try {
			A1_out = LogCompressor.getSomeAfromB(A,packed,amount,offset);
		} catch(Exception e) {
			System.err.println("Error: " + e);
			fail();
		}
		A = 0;
		amount = 4;
		offset = 8;
		try {
			A2_out = LogCompressor.getSomeAfromB(A,packed,amount,offset);
		} catch(Exception e) {
			System.err.println("Error: " + e);
			fail();
		}
		A = 0;
		amount = 3;
		offset = 12;
		try {
			A3_out = LogCompressor.getSomeAfromB(A,packed,amount,offset);
		} catch(Exception e) {
			System.err.println("Error: " + e);
			fail();
		}
		
		assertTrue(A1_8bit == A1_out);
		System.out.println("Not equal: " + A2_out);
		assertTrue(A2_4bit == A2_out);
		assertTrue(A3_3bit == A3_out);
	}
	
	@Test
	public void verifyOffset() {
		int A = 0;
		int B = 0;
		int amount = 0;
		int offset = 0;
		int packed = 0;
		int unpacked = 0;
		A = 5;
		B = 0;
		amount = 3;
		offset = 10;
		try {
			packed = LogCompressor.putSomeAintoB(A,B,amount,offset);
		} catch(Exception e) {
			System.err.println("Error: " + e);
			fail();
		}
		assertTrue(packed == (5 << 10));
	}
	
	@Test
	public void verifyAmount() {
		int A = 0;
		int B = 0;
		int amount = 0;
		int offset = 0;
		int packed = 0;
		int unpacked = 0;
		A = 13;
		B = 0;
		amount = 3;
		offset = 10;
		try {
			packed = LogCompressor.putSomeAintoB(A,B,amount,offset);
		} catch(Exception e) {
			System.err.println("Error: " + e);
			fail();
		}
		assertTrue(packed == (5 << 10));
	}
	
	@Test
	public void verifyAssertionFailureOnInvalidInput() {
		int A = 0;
		int B = 0;
		int amount = 0;
		int offset = 0;
		int packed = 0;
		int unpacked = 0;
		boolean exception;
		A = -5;
		B = 7;
		amount = 3;
		offset = 10;
		
		exception = false;
		try {
			packed = LogCompressor.putSomeAintoB(A,B,amount,offset);
		} catch (Exception e) {
			exception = true;
		} finally {
			assertTrue(exception);
		}
		exception = false;
		try {
			LogCompressor.getSomeAfromB(A,packed,amount,offset);
		} catch (Exception e) {
			exception = true;
		} finally {
			assertTrue(exception);
		}
		
		A = 5;
		B = -7;
		amount = 3;
		offset = 10;
		
		exception = false;
		try {
			packed = LogCompressor.putSomeAintoB(A,B,amount,offset);
		} catch (Exception e) {
			exception = true;
		} finally {
			assertTrue(exception);
		}
		packed = -7;
		exception = false;
		try {
			LogCompressor.getSomeAfromB(A,packed,amount,offset);
		} catch (Exception e) {
			exception = true;
		} finally {
			assertTrue(exception);
		}
		
		A = 5;
		B = 7;
		amount = -3;
		offset = 10;
		
		exception = false;
		try {
			packed = LogCompressor.putSomeAintoB(A,B,amount,offset);
		} catch (Exception e) {
			exception = true;
		} finally {
			assertTrue(exception);
		}
		exception = false;
		try {
			LogCompressor.getSomeAfromB(A,packed,amount,offset);
		} catch (Exception e) {
			exception = true;
		} finally {
			assertTrue(exception);
		}
		
		A = 5;
		B = 7;
		amount = 3;
		offset = -10;
		
		exception = false;
		try {
			packed = LogCompressor.putSomeAintoB(A,B,amount,offset);
		} catch (Exception e) {
			exception = true;
		} finally {
			assertTrue(exception);
		}
		exception = false;
		try {
			LogCompressor.getSomeAfromB(A,packed,amount,offset);
		} catch (Exception e) {
			exception = true;
		} finally {
			assertTrue(exception);
		}
		
		A = 5;
		B = 7;
		amount = 3;
		offset = 29;
		
		exception = false;
		try {
			packed = LogCompressor.putSomeAintoB(A,B,amount,offset);
		} catch (Exception e) {
			exception = true;
		} finally {
			assertTrue(exception);
		}
		exception = false;
		try {
			LogCompressor.getSomeAfromB(A,packed,amount,offset);
		} catch (Exception e) {
			exception = true;
		} finally {
			assertTrue(exception);
		}
	}

	

}