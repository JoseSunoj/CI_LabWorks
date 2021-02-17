/**
 * 
 */
package ie.lyit.mvnApps;

import java.math.BigInteger;
import java.util.Scanner;

/**
 * @author Sunoj
 *
 */
public class App {

	/**
	 * Asks the user to enter IBAN number and checks the return value of
	 * isValidString() method by passing the input as argument. If it returns true,
	 * then it reassign the input value by transferring the first 4 characters to
	 * the end. By invoking isValidIBAN() by passing the return value of
	 * ibanToNumber() as argument which takes the reassigned value as argument.
	 * Prints the result as per the returned value.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		var sc = new Scanner(System.in);
		System.out.print("Please Enter IBAN:");
		var iban = sc.nextLine().strip().replaceAll("\\s+", "");
		var validator = new App();
		var isValid = false;
		if (validator.isValidString(iban)) {
			iban = iban.substring(4) + iban.substring(0, 4).toUpperCase();
			isValid = validator.isValidIBAN(validator.ibanToNumber(iban));
		}
		if (isValid) {
			System.out.print("Valid IBAN!");
		} else {
			System.out.print("Inavlid IBAN!");
		}
		sc.close();

	}

	/**
	 * A method which takes String value of IBAN. Checks whether it is valid or of
	 * right length.
	 * 
	 * @param String iban
	 * @return True or False
	 */
	protected boolean isValidString(String iban) {
		var valid = true;
		if (!iban.matches("[a-zA-Z0-9]+")) {
			valid = false;
		} else if (iban.length() < 15) {
			valid = false;
		} else if (iban.length() > 31) {
			valid = false;
		} else {
			valid = true;
		}
		return valid;
	}

	/**
	 * This method does the final validation of the user input.Checks whether the
	 * remainder of corresponding BigInteger value of the passed argument when
	 * divided by 97 is 1 and returns the result.
	 * 
	 * @param String iban
	 * @return true or false
	 */
	protected boolean isValidIBAN(String iban) {
		var ibanToBigNumber = new BigInteger(iban);
		return (ibanToBigNumber.mod(new BigInteger("97")).equals(new BigInteger("1")));
	}

	/**
	 * A method which takes String value of transformed IBAN after the initial
	 * check,checks each characters,stores the integer characters to a StringBuilder
	 * object.Convert its non integer characters to integers using the formula [10 +
	 * unicodeValue(character) - unicodeValue(A)] And stores the number to the
	 * StringBuilder object.
	 * 
	 * @param String iban
	 * @return a String value of the StringBuilder object.
	 */

	protected String ibanToNumber(String iban) {
		var tempIban = new StringBuilder();
		for (var i = 0; i < iban.length(); i++) {
			if (Character.isDigit(iban.charAt(i))) {
				tempIban.append(iban.charAt(i));
			} else {
				var temp = 10 + (int) (iban.charAt(i)) - (int) ('A');
				tempIban.append(temp);
			}
		}
		return tempIban.toString();

	}
}
