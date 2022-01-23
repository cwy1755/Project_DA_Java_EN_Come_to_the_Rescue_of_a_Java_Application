package com.hemebiotech.analytics;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

/**
 * class Process the symptoms
 */

public class ProcessOut {

    ReadSymptomDataFromFile reader;
    FileWriter writer;

    ProcessOut(ISymptomReader reader, FileWriter writer) {
        this.reader = (ReadSymptomDataFromFile) reader;
        this.writer = writer;
    }

    /**
     * @Function: ProcessOut
     * @Description: read, count, sort , write result
     * @param: no input param
     * @output: write results in the file
     */
    public void ProcessOut() {

        // Read File
        List<String> listSymptoms = null;
        listSymptoms = reader.GetSymptoms();

        // Cumul symptoms
        TreeMap<String, Integer> sortCountSymptoms = new TreeMap<String, Integer>();
        if (listSymptoms.size() != 0) {
            for (int i = 1; i < listSymptoms.size(); i++) {
                if (sortCountSymptoms.containsKey(listSymptoms.get(i)) == false) {
                    sortCountSymptoms.put(listSymptoms.get(i), 1);
                } else {
                    Integer valSymptom = sortCountSymptoms.get(listSymptoms.get(i));
                    valSymptom++;
                    sortCountSymptoms.replace(listSymptoms.get(i), valSymptom);
                }
            }
        }

        // Write result
        BufferedWriter bufWriter = null;
        try {
            bufWriter = new BufferedWriter(writer);
            for (Map.Entry mapEntry : sortCountSymptoms.entrySet()) {
                bufWriter.write(mapEntry.getKey().toString() + " : " + mapEntry.getValue());
                bufWriter.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(2);
        } finally {
            try {
                if (bufWriter != null) {
                    bufWriter.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
                System.exit(3);
            }
        }
    }
}
