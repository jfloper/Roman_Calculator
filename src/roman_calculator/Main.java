package roman_calculator;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


//Roman Calculator
public class Main {
	private static boolean isTest = false;

	public static void main(String[] args) {

		// String input = "IVXLCDM ivxlcdm"; // example
		// String input = "LXXXIII"; // 83
		// String input = "LXXXIX"; // 89
		String inputPath = "../roman_calculator/input-roman-addictive-sample.txt";

		if (args != null) {
			for (int j = 0; j < args.length; j++) {
				if (args[j] != null) {
					if (args[j].contains("debug")) {
						isTest = true;
					}
					if (args[j].contains("path")) {
						inputPath = args[j].substring(args[j].indexOf("=") + 1);
					}
				}
			}
		}

		List<String> roman100Sample = new ArrayList<String>();

		if (isTest == true) {
			roman100Sample = reader(inputPath);
		} else {
			roman100Sample = reader(inputPath);
		}

		RomanNumeral romanNumeral = new RomanNumeral();
		for (int i = 0; i < roman100Sample.size(); i++) {
			String inputQuestion = roman100Sample.get(i);
//			System.out.print("item : " + (i + 1) + " : " + inputQuestion + " \t");// TODO
																					// COMMENT
			String[] inputs = inputQuestion.split("\\+");
			int verifyValue = 0;

			int sum = 0;
			for (int index = 0; index < inputs.length; index++) {
				String input = inputs[index];
				if (isTest == true && input.contains("-")) {
					String[] tempInput = input.split("\\-");
					input = tempInput[0];
					verifyValue = Integer.parseInt(tempInput[1]);
				}

				sum += romanNumeral.covertToNumeral(input);
			}
			// System.out.print(" summation : " + sum + " ");// TODO COMMENT
			// if(verifyValue == sum){
			// System.out.print("["+verifyValue+"] "+" verify summation :
			// Success.");// TODO COMMENT
			// }else{
			// System.err.print(" verify summation : \tFail.");// TODO COMMENT
			// }

			String result = romanNumeral.convertToRomanNumeral(sum);
//			System.out.println(" result : " + result);// TODO COMMENT

			 System.out.println(result);
		}

	}

	public static List<String> reader(String filePath) {
		List<String> items = new ArrayList<String>();
		try {

			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filePath)));
			String line;
			while ((line = br.readLine()) != null) {
				items.add(line);
			}
			br.close();

		} catch (IOException e) {
			System.out.println("ERROR: unable to read file " + filePath);
			e.printStackTrace();
		}
		return items;
	}

}
