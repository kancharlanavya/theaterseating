package com.theater.seating;

import com.theater.model.Customer;
import com.theater.model.SeatingAndCustomerRequest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;


public class ExtractSeatingCustomerInfoTest {

    ExtractSeatingAndCustomerOrder testingObject;
    SeatingAndCustomerRequest seatingAndCustomerRequest;
    Customer customer;
    TreeMap<Integer, Customer> customerMap = new TreeMap<Integer, Customer>();

    @Before
    public void init() {
        testingObject = new ExtractSeatingAndCustomerOrder();
        seatingAndCustomerRequest = new SeatingAndCustomerRequest();
        seatingAndCustomerRequest.setTotalLines(5);
        seatingAndCustomerRequest.setEmptyLineNumber(3);
    }

    @Test
    public void extractSeatingAndCustomerOrder_HappyPath() {

        customer = new Customer("Test77", 3);
        Customer customer1 = new Customer("Test88", 4);
        customerMap.put(1, customer);
        customerMap.put(2, customer1);
        seatingAndCustomerRequest.setCustomerMap(customerMap);

        List<StringBuilder> lineReaderText = new ArrayList<>();
        lineReaderText.add(new StringBuilder("3 3"));
        lineReaderText.add(new StringBuilder("4 3"));
        lineReaderText.add(new StringBuilder(" "));
        lineReaderText.add(new StringBuilder("Test77 3"));
        lineReaderText.add(new StringBuilder("Test88 4"));
        seatingAndCustomerRequest.setLineReaderText(lineReaderText);
        testingObject.extractSeatingAndCustomerOrder(seatingAndCustomerRequest);
        Assert.assertEquals(testingObject.getHighestSeatSection(), 4);
        Assert.assertEquals(testingObject.getTotalTheaterSeats(), 13);

    }

    @Test
    public void extractSeatingAndCustomerOrder_NoDataPassed_NoExceptions() {

        testingObject.extractSeatingAndCustomerOrder(seatingAndCustomerRequest);
        Assert.assertEquals(testingObject.getHighestSeatSection(), 0);
        Assert.assertEquals(testingObject.getTotalTheaterSeats(), 0);

    }
}
