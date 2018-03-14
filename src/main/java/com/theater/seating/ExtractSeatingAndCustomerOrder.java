package com.theater.seating;

import com.theater.model.Customer;
import com.theater.model.SeatingAndCustomerRequest;

import java.util.List;
import java.util.TreeMap;


public class ExtractSeatingAndCustomerOrder {

    private int highestSeatSection;
    private int totalTheaterSeats;

    public void extractSeatingAndCustomerOrder(SeatingAndCustomerRequest seatingAndCustomerRequest) {
        for (int i = 0, j = 1, partyKey = 1; i < seatingAndCustomerRequest.getTotalLines(); i++, j++) {
            if (i > seatingAndCustomerRequest.getEmptyLineNumber()) {
                partyKey = extractCustomerOrder(seatingAndCustomerRequest.getCustomerMap(), seatingAndCustomerRequest.getLineReaderText(), i, partyKey);

            } else if (i < seatingAndCustomerRequest.getEmptyLineNumber()) {
                extractSeatingMatrix(seatingAndCustomerRequest.getLineReaderText(), seatingAndCustomerRequest.getSeatingMatrix(), i, j);
            }

        }

    }

    private void extractSeatingMatrix(List<StringBuilder> lineReaderText, int[][] seatingMatrix, int i, int j) {
        if (!lineReaderText.isEmpty()) {
            String[] sectionList = lineReaderText.get(i).toString().split(" ");

            for (int section = 0, k = 1; section < sectionList.length; section++, k++) {
                int sectionSeatsCount = Integer.parseInt(sectionList[section]);
                highestSeatSection = Math.max(highestSeatSection, sectionSeatsCount);
                totalTheaterSeats = totalTheaterSeats + sectionSeatsCount;
                seatingMatrix[j][k] = sectionSeatsCount;
            }
        }
    }

    private int extractCustomerOrder(TreeMap<Integer, Customer> customerMap, List<StringBuilder> lineReaderText, int i, int partyKey) {
        if (!lineReaderText.isEmpty()) {
            String[] custmomerList = lineReaderText.get(i).toString().split(" ");
            Customer seatingSection = new Customer(custmomerList[0], Integer.parseInt(custmomerList[1]));
            customerMap.put(partyKey++, seatingSection);
        }
        return partyKey;
    }

    public int getHighestSeatSection() {
        return highestSeatSection;
    }

    public int getTotalTheaterSeats() {
        return totalTheaterSeats;
    }
}
