package com.theater.seating;

import com.theater.model.Customer;
import com.theater.model.SeatingAndCustomerRequest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;


public class AssignCustomerSeatingTest {
    public static final String TEST = "Test";
    public static final String TEST1 = "Test1";
    AssignCustomerSeating testObject;
    SeatingAndCustomerRequest seatingAndCustomerRequest;
    Customer customer;
    TreeMap<Integer, Customer> customerMap = new TreeMap<Integer, Customer>();



    @Before
    public void init(){

        testObject = new AssignCustomerSeating();
        seatingAndCustomerRequest = new SeatingAndCustomerRequest();
        seatingAndCustomerRequest.setTotalLines(5);
        seatingAndCustomerRequest.setEmptyLineNumber(3);
        int[][] seatingMatrix = new int[6][6];
        seatingMatrix[1][1] = 3;
        seatingMatrix[1][2] = 3;
        seatingMatrix[2][1] = 4;
        seatingMatrix[2][2] = 3;
        seatingAndCustomerRequest.setSeatingMatrix(seatingMatrix);

    }

    @Test
    public void testExtractSeatingAndCustomerOrder_HappyPath(){
        customer = new Customer(TEST, 3);
        Customer customer1 = new Customer(TEST1, 4);
        customerMap.put(1, customer);
        customerMap.put(2, customer1);
        seatingAndCustomerRequest.setCustomerMap(customerMap);

        List<StringBuilder> lineReaderText = new ArrayList<>();
        lineReaderText.add(new StringBuilder("3 3"));
        lineReaderText.add(new StringBuilder("4 3"));
        lineReaderText.add(new StringBuilder(" "));
        lineReaderText.add(new StringBuilder("Test 3"));
        lineReaderText.add(new StringBuilder("Test1 4"));
        seatingAndCustomerRequest.setLineReaderText(lineReaderText);


        testObject.processSeating(seatingAndCustomerRequest, 4, 13);
        seatingAndCustomerRequest.getCustomerMap().forEach((key, customer)-> {
            if(customer.getName().equals(TEST)) {
                Assert.assertTrue(customer.getRowAssigned() == 1);
                Assert.assertTrue(customer.getSectionAssigned() == 1);
            }
            if(customer.getName().equals(TEST1)) {
                Assert.assertTrue(customer.getRowAssigned() == 2);
                Assert.assertTrue(customer.getSectionAssigned() == 1);
            }

        });
    }

    @Test
    public void testextractSeatingAndCustomerOrder_LargeOrder(){

        customer = new Customer(TEST, 100);
        Customer customer1 = new Customer(TEST1, 4);
        customerMap.put(1, customer);
        customerMap.put(2, customer1);
        seatingAndCustomerRequest.setCustomerMap(customerMap);

        List<StringBuilder> lineReaderText = new ArrayList<>();
        lineReaderText.add(new StringBuilder("3 3"));
        lineReaderText.add(new StringBuilder("4 3"));
        lineReaderText.add(new StringBuilder(" "));
        lineReaderText.add(new StringBuilder("Test 100"));
        lineReaderText.add(new StringBuilder("Test1 4"));
        seatingAndCustomerRequest.setLineReaderText(lineReaderText);


        testObject.processSeating(seatingAndCustomerRequest, 4, 13);
        seatingAndCustomerRequest.getCustomerMap().forEach((key, customer)-> {
            if(customer.getName().equals(TEST)) {
                Assert.assertTrue(customer.getRowAssigned() == null);
                Assert.assertTrue(customer.getComments().equals(AssignCustomerSeating.CANNOT_HANDLE_PARTY_MSG));
            }
            if(customer.getName().equals(TEST1)) {
                Assert.assertTrue(customer.getRowAssigned() == 2);
                Assert.assertTrue(customer.getSectionAssigned() == 1);
            }

        });
    }

    @Test
    public void testextractSeatingAndCustomerOrder_SplitOrder(){

        customer = new Customer(TEST, 9);
        Customer customer1 = new Customer(TEST1, 4);
        customerMap.put(1, customer);
        customerMap.put(2, customer1);
        seatingAndCustomerRequest.setCustomerMap(customerMap);

        List<StringBuilder> lineReaderText = new ArrayList<>();
        lineReaderText.add(new StringBuilder("3 3"));
        lineReaderText.add(new StringBuilder("4 3"));
        lineReaderText.add(new StringBuilder(" "));
        lineReaderText.add(new StringBuilder("Test 100"));
        lineReaderText.add(new StringBuilder("Test1 4"));
        seatingAndCustomerRequest.setLineReaderText(lineReaderText);


        testObject.processSeating(seatingAndCustomerRequest, 4, 13);
        seatingAndCustomerRequest.getCustomerMap().forEach((key, customer)-> {
            if(customer.getName().equals(TEST)) {
                Assert.assertTrue(customer.getRowAssigned() == null);
                Assert.assertTrue(customer.getComments().equals(AssignCustomerSeating.SPLIT_PARTY_MSG));
            }
            if(customer.getName().equals(TEST1)) {
                Assert.assertTrue(customer.getRowAssigned() == 2);
                Assert.assertTrue(customer.getSectionAssigned() == 1);
            }

        });
    }

    @Test
    public void testextractSeatingAndCustomerOrder_CloseToFront(){

        customer = new Customer(TEST, 2);
        Customer customer1 = new Customer(TEST1, 4);
        customerMap.put(1, customer);
        customerMap.put(2, customer1);
        seatingAndCustomerRequest.setCustomerMap(customerMap);
        int[][] seatingMatrix = new int[6][6];
        seatingMatrix[1][1] = 3;
        seatingMatrix[1][2] = 3;
        seatingMatrix[2][1] = 4;
        seatingMatrix[2][2] = 3;
        seatingMatrix[3][1] = 4;
        seatingMatrix[3][2] = 2;
        seatingAndCustomerRequest.setSeatingMatrix(seatingMatrix);

        List<StringBuilder> lineReaderText = new ArrayList<>();
        lineReaderText.add(new StringBuilder("3 3"));
        lineReaderText.add(new StringBuilder("4 3"));
        lineReaderText.add(new StringBuilder("4 2"));
        lineReaderText.add(new StringBuilder(" "));
        lineReaderText.add(new StringBuilder("Test 2"));
        lineReaderText.add(new StringBuilder("Test1 4"));
        seatingAndCustomerRequest.setLineReaderText(lineReaderText);


        testObject.processSeating(seatingAndCustomerRequest, 4, 13);

        seatingAndCustomerRequest.getCustomerMap().forEach((key, customer)-> {
            if(customer.getName().equals(TEST)) {
                Assert.assertTrue(customer.getRowAssigned() == 3);
                Assert.assertTrue(customer.getSectionAssigned() == 2);
            }
            if(customer.getName().equals(TEST1)) {
                Assert.assertTrue(customer.getRowAssigned() == 2);
                Assert.assertTrue(customer.getSectionAssigned() == 1);
            }

        });
    }

}
