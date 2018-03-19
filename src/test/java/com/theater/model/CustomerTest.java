package com.theater.model;

import org.junit.Assert;
import org.junit.Test;

public class CustomerTest {

    @Test
    public void testCommentsNotEquals(){
        Customer customer1 = new Customer("Test77", 3);
        customer1.setComments(" This is a test");
        Customer customer2 = new Customer("Test77", 3);
        customer2.setComments(" This is not a test");

        Assert.assertFalse(customer1.equals(customer2));

    }

    @Test
    public void testCommentsEquals(){
        Customer customer1 = new Customer("Test77", 3);
        customer1.setComments(" This is a test");
        Customer customer2 = new Customer("Test77", 3);
        customer2.setComments(" This is a test");

        Assert.assertTrue(customer1.equals(customer2));

    }

    @Test
    public void testSeatingEquals(){
        Customer customer1 = new Customer("Test77", 3);
        Customer customer2 = new Customer("Test77", 3);

        Assert.assertTrue(customer1.equals(customer2));

    }

    @Test
    public void testSeatingNotEquals(){
        Customer customer1 = new Customer("Test77", 4);
        Customer customer2 = new Customer("Test77", 3);

        Assert.assertFalse(customer1.equals(customer2));

    }

    @Test
    public void testNameEquals(){
        Customer customer1 = new Customer("Test77", 3);
        Customer customer2 = new Customer("Test77", 3);

        Assert.assertTrue(customer1.equals(customer2));

    }

    @Test
    public void testNameNotEquals(){
        Customer customer1 = new Customer("Test77", 4);
        Customer customer2 = new Customer("Test88", 3);

        Assert.assertFalse(customer1.equals(customer2));

    }

    @Test
    public void testSectionEquals(){
        Customer customer1 = new Customer("Test77", 3);
        customer1.setSectionAssigned(10);
        Customer customer2 = new Customer("Test77", 3);
        customer2.setSectionAssigned(10);

        Assert.assertTrue(customer1.equals(customer2));

    }

    @Test
    public void testSectionNotEquals(){
        Customer customer1 = new Customer("Test77", 3);
        customer1.setSectionAssigned(10);
        Customer customer2 = new Customer("Test77", 3);
        customer2.setSectionAssigned(20);

        Assert.assertFalse(customer1.equals(customer2));

    }


    @Test
    public void testHashCode(){
        Customer customer = new Customer("Test77", 3);
        customer.setSectionAssigned(10);
        customer.setComments("This is a test");
        customer.setRowAssigned(30);

        Assert.assertNotNull(customer);
    }

}
