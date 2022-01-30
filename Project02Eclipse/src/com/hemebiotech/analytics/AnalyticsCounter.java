package com.hemebiotech.analytics;

import java.io.FileWriter;

public class AnalyticsCounter {
	/**
	 * count symptoms
	 * @param args: $1: input symptoms file (format : symptom)
	 *         $2: output result file (format: symptom : number)
	 */
	public static void main(String args[]) throws Exception {

		// validate input parameter
		if (args.length == 2) {
			System.out.println("Info: input symptoms file: " + args[0]);
			System.out.println("Info: output result file: " + args[1]);
		} else {
			System.out.println("Error: Verify the input parameter.");
			System.exit(1);
		}


		ISymptomReader reader = new ReadSymptomDataFromFile(args[0]);
		FileWriter writer = new FileWriter(args[1]);
		ProcessOut result = new ProcessOut(reader, writer);
		result.countSymptom();

		// output status
		System.out.println("Info: Job finish");

	}
}

