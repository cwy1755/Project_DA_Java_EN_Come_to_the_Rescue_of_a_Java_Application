package com.hemebiotech.analytics;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

/**
 * class process the symptoms
 */

public class ProcessOut {

    private ISymptomReader reader;
    private FileWriter writer;

    /**
     * Create Object with input file and output file
     * @param reader: Input file
     * @param writer: Output file
     */
    ProcessOut(ISymptomReader reader, FileWriter writer) {
        this.reader = reader;
        this.writer = writer;
    }

    /**
     * read, count/sort , write result
     */
    public void countSymptom() {

        List<String> listSymptoms = reader.GetSymptoms();
        TreeMap<String, Integer> sortCountSymptoms = countFromList(listSymptoms);
        writeResult (sortCountSymptoms);
    }

    /**
     * Count symptoms from a list of symptoms, then put the result in a TreeMap <symptom,count>
     * @param listSymptoms: List<symptoms>
     * @return TreeMap <Symptoms, count>
     */
    private TreeMap<String, Integer> countFromList(List<String> listSymptoms) {
        TreeMap<String, Integer> sortCountSymptoms = new TreeMap<>();
        if (listSymptoms.size() != 0) {
            for (int i = 1; i < listSymptoms.size(); i++) {
                if (!sortCountSymptoms.containsKey(listSymptoms.get(i))) {
                    sortCountSymptoms.put(listSymptoms.get(i), 1);
                } else {
                    Integer valSymptom = sortCountSymptoms.get(listSymptoms.get(i));
                    valSymptom++;
                    sortCountSymptoms.replace(listSymptoms.get(i), valSymptom);
                }
            }

        }
        return sortCountSymptoms;
    }

    /**
     * write Map of <symptom,count> into file writer
     * @param sortCountSymptoms: Map<symptoms,count>
     */
    private void writeResult (Map<String, Integer> sortCountSymptoms ){
        BufferedWriter bufWriter = null;
        try {
            bufWriter = new BufferedWriter(writer);
            for (Map.Entry mapEntry : sortCountSymptoms.entrySet()) {
                bufWriter.write(mapEntry.getKey().toString() + " : " + mapEntry.getValue());
                bufWriter.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        } finally {
            try {
                if (bufWriter != null) {
                    bufWriter.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
                System.exit(2);
            }
        }

    }

}
