/**
 * 
 */
package ie.lyit.mvnApps;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

/**
 * The test class for App.java
 * @author Sunoj
 *
 */
class AppTest {

	/**
	 * Test method for {@link ie.lyit.mvnApps.App#main(java.lang.String[])}.
	 * 
	 * @param String  sampleInput - takes sample inputs values
	 * @param String sampleOutput -  takes sample expected output values
	 */
	@ParameterizedTest
	@CsvSource({ "SE35 5000 0000 0549 1000 0003, Please Enter IBAN:Valid IBAN!",
			"CH93@ 0076 2011 6238 5295 7, Please Enter IBAN:Inavlid IBAN!",
			"HU42 1177 3016 1111 1018 000 000, Please Enter IBAN:Inavlid IBAN!" })
	void testMain(String sampleInput, String sampleOutput) {
		final var outPut = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outPut));
		var input = new ByteArrayInputStream(sampleInput.getBytes());
		System.setIn(input);
		App.main(null);
		assertEquals(sampleOutput, outPut.toString());
		System.setIn(System.in);
		System.setOut(System.out);

	}

	/**
	 * Test method for
	 * {@link ie.lyit.mvnApps.App#intialValidator(java.lang.String)}.
	 * 
	 * @param String  input - takes some input values for the test
	 * @param boolean output - takes boolean values for the test as expected result
	 */
	@ParameterizedTest
	@CsvSource({ "DE89370400440532013000, true", "93@00762011623852957, false", "HU42117, false", "11773016AIB, false",
			"SE355000000005491000000398765346, false" })
	void testIsValidString(String input, boolean output) {
		assertEquals(output, new App().isValidString(input));
	}

	/**
	 * Test method for {@link ie.lyit.mvnApps.App#finalValidator(java.lang.String)}.
	 * 
	 * @param String  input - represents the input test values
	 * @param boolean result - represents the expected results
	 */
	@ParameterizedTest
	@CsvSource({ "50000000054910000003281435, true", "11102320000043519500213180, false",
			"221021290110000123452229211210282900128222984, true",
			"22102129011000123452229211210282900128222984, false" })
	void testIsValidIBAN(String input, boolean result) {
		assertEquals(result, new App().isValidIBAN(input));
	}

	/**
	 * Test method for {@link ie.lyit.mvnApps.App#IbanToNumber(java.lang.String)}
	 * 
	 * @param String input - input values for the test
	 * @param String result - expected result of the test
	 */
	@ParameterizedTest
	@CsvSource({ "50000000054910000003SE35, 50000000054910000003281435",
			"002001280000001200527600CY17, 002001280000001200527600123417",
			"114020040000300201355387PL27, 114020040000300201355387252127",
			"MALT01100012345MTLCAST001SMT84, 22102129011000123452229211210282900128222984" })
	void testIbanToNumber(String input, String result) {
		assertEquals(result, new App().ibanToNumber(input));
	}
}
