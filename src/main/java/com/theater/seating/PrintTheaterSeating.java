package com.theater.seating;

import com.theater.model.Customer;
import org.apache.commons.lang3.StringUtils;

import java.util.TreeMap;


public class PrintTheaterSeating {

    /**
     * Retrieves Customer Name and assignment info from the Map and Sysouts the final seating information.
     *
     * @param customerTreeMap
     */
    public void printSeatingAssignments(TreeMap<Integer, Customer> customerTreeMap) {
        customerTreeMap.forEach((key, customer) -> {
            StringBuilder printAssignment = new StringBuilder();
            printAssignment.append(customer.getName());
            if (StringUtils.isBlank(customer.getComments()) && customer.getRowAssigned() != null && customer.getSectionAssigned() != null) {
                printAssignment.append(" Row " + customer.getRowAssigned() + " Section " + customer.getSectionAssigned());

            } else if (StringUtils.isNotBlank(customer.getComments())) {
                printAssignment.append(customer.getComments());
            }
            System.out.println(printAssignment);

        });
    }
}
