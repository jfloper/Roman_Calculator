package roman_calculator;

public class RomanNumeral {

	public static final String ONE_THOUSAND = "M";
	public static final String FIVE_HUNDRED = "D";
	public static final String ONE_HUNDRED = "C";
	public static final String FIFTY = "L";
	public static final String TEN = "X";
	public static final String FIVE = "V";
	public static final String ONE = "I";
	public static final String NONE = "-";
	public static final String SUBTRACTIVE = "S";

	public Integer convertSymbolToNumber(String romanNumeral) {

		if (ONE.equalsIgnoreCase(romanNumeral)) {
			return 1;
		} else if (FIVE.equalsIgnoreCase(romanNumeral)) {
			return 5;
		} else if (TEN.equalsIgnoreCase(romanNumeral)) {
			return 10;
		} else if (FIFTY.equalsIgnoreCase(romanNumeral)) {
			return 50;
		} else if (ONE_HUNDRED.equalsIgnoreCase(romanNumeral)) {
			return 100;
		} else if (FIVE_HUNDRED.equalsIgnoreCase(romanNumeral)) {
			return 500;
		} else if (ONE_THOUSAND.equalsIgnoreCase(romanNumeral)) {
			return 1000;
		} else
			return null;
	}

	public String convertToLetter(int value) {
		if (value <= 0) {
			return NONE;
		} else if (value / 1000 >= 1) {
			return ONE_THOUSAND;
		} else if (value / 500 >= 1 && value < 900) {
			return FIVE_HUNDRED;
		} else if (value / 100 >= 1 && value < 400) {
			return ONE_HUNDRED;
		} else if (value / 50 >= 1 && value < 90) {
			return FIFTY;
		} else if (value / 10 >= 1 && value < 40) {
			return TEN;
		} else if (value / 5 >= 1 && value < 9) {
			return FIVE;
		} else if (value / 1 >= 1 && value < 4) {
			return ONE;
		} else {
			return SUBTRACTIVE;
		}

	}

	public int covertToNumeral(String input) {
		int index = 0;
		int sum = 0;
		while (true) {

			Integer currentLetter = 0;
			try {
				currentLetter = this.convertSymbolToNumber(input.substring(index, index + 1));
			} catch (StringIndexOutOfBoundsException e) {
				currentLetter = 0;
			}
			Integer nextLetter = 0;
			try {
				nextLetter = this.convertSymbolToNumber(input.substring(index + 1, index + 2));
			} catch (StringIndexOutOfBoundsException e) {
				nextLetter = 0;
			}

			if (currentLetter >= nextLetter) {
				sum += currentLetter;
			} else if (currentLetter < nextLetter) {
				sum += (nextLetter - currentLetter);
				index++;
			}

			index++;
			if (index == input.length()) {
				break;
			}
		}
		return sum;
	}

	public String convertToRomanNumeral(int input) {
		int tempInput = input;

		String resultRomanNumeral = "";

		while (true) {
			String letter = this.convertToLetter(tempInput);
			if (RomanNumeral.SUBTRACTIVE.equalsIgnoreCase(letter)) {
				Double subValue = tempInput * 0.25;
				letter = this.convertToLetter(subValue.intValue());
				subValue = tempInput * 1.25;
				if (letter != null && !letter.equalsIgnoreCase(RomanNumeral.NONE)) {
					resultRomanNumeral += letter;
					tempInput += this.convertSymbolToNumber(letter);
				}
				letter = this.convertToLetter(subValue.intValue());
				if (letter != null && !letter.equalsIgnoreCase(RomanNumeral.NONE)) {
					resultRomanNumeral += letter;
					tempInput -= this.convertSymbolToNumber(letter);
				}

			} else if (letter != null && !letter.equalsIgnoreCase(RomanNumeral.NONE)) {
				resultRomanNumeral += letter;
				tempInput -= this.convertSymbolToNumber(letter);
			}

			if (tempInput == 0) {
				break;
			}
		}

		return resultRomanNumeral;
	}

}
