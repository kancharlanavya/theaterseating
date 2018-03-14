package com.theater.seating;

import com.theater.model.Customer;
import com.theater.model.SeatingAndCustomerRequest;

import java.util.TreeMap;


public class AssignCustomerSeating {

    public final static String CANNOT_HANDLE_PARTY_MSG = " Sorry we can't handle your party.";
    public final static String SPLIT_PARTY_MSG = " Call to split party.";
    private int highestSeatSection;
    private int totalTheaterSeats;

    /**
     *
     * @param seatingAndCustomerRequest
     * @param highestSeatSection
     * @param totalTheaterSeats
     */
    public void processSeating(SeatingAndCustomerRequest seatingAndCustomerRequest, int highestSeatSection, int totalTheaterSeats) {

        this.highestSeatSection = highestSeatSection;
        this.totalTheaterSeats = totalTheaterSeats;
        assignSeatingToCustomers(seatingAndCustomerRequest.getCustomerMap(), seatingAndCustomerRequest.getSeatingMatrix());
        handleSplitOrders(seatingAndCustomerRequest.getCustomerMap());
        handleLargeOrders(seatingAndCustomerRequest.getCustomerMap());
    }


    /**
     * Handles any orders that are more than the number of seats in a given row
     * by adding a message to split the party.
     *
     * @param customerMap
     */
    private void handleSplitOrders(TreeMap<Integer, Customer> customerMap) {
        customerMap.forEach((key, customer) -> {
            if (customer.getSeatingNeeded() > highestSeatSection) {
                customer.setComments(SPLIT_PARTY_MSG);
            }

        });
    }

    /**
     * Comapares number of seats requested with total number of seats in theater
     * to number of seats requested by a Customer. If Customer requests more than available
     * appropriate message is added.
     * @param customerMap
     */
    private void handleLargeOrders(TreeMap<Integer, Customer> customerMap) {
        customerMap.forEach((key, customer) -> {
            if (customer.getSeatingNeeded() > totalTheaterSeats) {
                customer.setComments(CANNOT_HANDLE_PARTY_MSG);
            }

        });
    }

    /**
     * Assigns seating to customer based on thier request.
     *
     * @param customerMap
     * @param seatingMatrix
     */
    private void assignSeatingToCustomers(TreeMap<Integer, Customer> customerMap, int[][] seatingMatrix) {
        customerMap.forEach((key, customer) -> {
            customerLoop:
            for (int row = 1; row < 6; row++) {
                for (int section = 1; section < 6; section++) {
                    //If exact match and within 3 row
                    if ((seatingMatrix[row][section] == customer.getSeatingNeeded() && row < 4) && customer.getRowAssigned() == null) {
                        customer.setRowAssigned(row);
                        customer.setSectionAssigned(section);
                        break customerLoop;
                    }
                    if (seatingMatrix[row][section] == customer.getSeatingNeeded() && row >= 4 && customer.getRowAssigned() == null) {
                        //Check if higher number is available in front rows
                        for (int rowRepeater = 1; rowRepeater < 4; rowRepeater++) {
                            for (int sectionRepeater = 1; sectionRepeater < 6; sectionRepeater++) {
                                if ((seatingMatrix[rowRepeater][sectionRepeater] > customer.getSeatingNeeded()) && customer.getRowAssigned() == null) {
                                    customer.setRowAssigned(rowRepeater);
                                    customer.setSectionAssigned(sectionRepeater);
                                    seatingMatrix[rowRepeater][sectionRepeater] = seatingMatrix[rowRepeater][sectionRepeater] - customer.getSeatingNeeded();
                                    break customerLoop;
                                }
                            }
                            customer.setRowAssigned(row);
                            customer.setSectionAssigned(section);
                            break customerLoop;
                        }
                    }
                }
            }
        });
    }


}
