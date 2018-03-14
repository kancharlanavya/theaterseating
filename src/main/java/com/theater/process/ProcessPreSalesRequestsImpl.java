package com.theater.process;

import com.theater.demo.DemoTheaterSeating;
import com.theater.model.Customer;
import com.theater.model.SeatingAndCustomerRequest;
import com.theater.seating.ExtractSeatingAndCustomerOrder;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;


public class ProcessPreSalesRequestsImpl implements  ProcessPreSalesRequests {

    /**
     * Loads and parses the input file provided.
     * Adds the extracted info into the SeatingAndCustomerRequest object to be used further down the pipeline.
     *
     * @param fileName
     * @return
     */
	@Override
    public SeatingAndCustomerRequest extractInfo(String fileName) {
        InputStream resourceAsStream = null;
        TreeMap<Integer, Customer> customerMap = new TreeMap<Integer, Customer>();
        SeatingAndCustomerRequest seatingAndCustomerRequest = null;

        try {
            LineNumberReader in = new LineNumberReader(new InputStreamReader(DemoTheaterSeating.class.getClassLoader().getResourceAsStream(fileName)));
            int emptyLineNumber = 0;
            Integer totalLines = new Integer(0);

            List<StringBuilder> lineReaderText = new ArrayList<>();
            String line = null;


            while ((line = in.readLine()) != null) {
                lineReaderText.add(totalLines, new StringBuilder(line));
                if (line.trim().isEmpty()) {
                    emptyLineNumber = in.getLineNumber() - 1;
                }
                totalLines++;
            }

            seatingAndCustomerRequest = setSeatingAndCustomerRequest(customerMap, emptyLineNumber, totalLines, lineReaderText);


        } catch (IOException ioe) {
            ioe.printStackTrace();
            System.out.println("Sorry, error while reading input file");
        } finally {
            if (resourceAsStream != null) {
                try {
                    resourceAsStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    System.out.println("Sorry, error while closing input reader");

                }
            }
        }
        return seatingAndCustomerRequest;
    }

    private SeatingAndCustomerRequest setSeatingAndCustomerRequest(TreeMap<Integer, Customer> customerMap, int emptyLineNumber, Integer totalLines, List<StringBuilder> lineReaderText) {
        SeatingAndCustomerRequest seatingAndCustomerRequest;
        int[][] seatingMatrix = new int[6][6];
        seatingAndCustomerRequest = new SeatingAndCustomerRequest();
        seatingAndCustomerRequest.setEmptyLineNumber(emptyLineNumber);
        seatingAndCustomerRequest.setLineReaderText(lineReaderText);
        seatingAndCustomerRequest.setCustomerMap(customerMap);
        seatingAndCustomerRequest.setSeatingMatrix(seatingMatrix);
        seatingAndCustomerRequest.setTotalLines(totalLines);
        return seatingAndCustomerRequest;
    }

}
