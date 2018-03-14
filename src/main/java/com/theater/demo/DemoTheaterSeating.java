package com.theater.demo;

import com.theater.model.SeatingAndCustomerRequest;
import com.theater.process.ProcessPreSalesRequestsImpl;
import com.theater.process.ProcessPreSalesRequests;
import com.theater.seating.AssignCustomerSeating;
import com.theater.seating.ExtractSeatingAndCustomerOrder;
import com.theater.seating.PrintTheaterSeating;

import java.io.IOException;

/**
 * Class to execute to see the desired output from this Exercise.
 */
public class DemoTheaterSeating {


    public static void main(String[] args) throws IOException {

    	ProcessPreSalesRequests processPreSaleRequests = new ProcessPreSalesRequestsImpl();
        SeatingAndCustomerRequest seatingAndCustomerRequest = processPreSaleRequests.extractInfo("theater_layout.txt");

        ExtractSeatingAndCustomerOrder extractSeatingAndCustomerOrder = new ExtractSeatingAndCustomerOrder();
        extractSeatingAndCustomerOrder.extractSeatingAndCustomerOrder(seatingAndCustomerRequest);

        new AssignCustomerSeating().processSeating(seatingAndCustomerRequest, extractSeatingAndCustomerOrder.getHighestSeatSection(), extractSeatingAndCustomerOrder.getTotalTheaterSeats());

        new PrintTheaterSeating().printSeatingAssignments(seatingAndCustomerRequest.getCustomerMap());


    }
}
