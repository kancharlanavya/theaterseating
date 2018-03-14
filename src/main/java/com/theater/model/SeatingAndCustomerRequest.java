package com.theater.model;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

/**
 * SeatingAndCustomerRequest is the object model of all the information
 * about the seating in the theater and Customer request fed into this Exercise in file format.
 */
public class SeatingAndCustomerRequest {

    TreeMap<Integer, Customer> customerMap = new TreeMap<Integer, Customer>();
    int emptyLineNumber = 0;
    Integer totalLines = new Integer(0);
    int[][] seatingMatrix = new int[6][6];
    List<StringBuilder> lineReaderText = new ArrayList<>();

    public TreeMap<Integer, Customer> getCustomerMap() {
        return customerMap;
    }

    public void setCustomerMap(TreeMap<Integer, Customer> customerMap) {
        this.customerMap = customerMap;
    }

    public int getEmptyLineNumber() {
        return emptyLineNumber;
    }

    public void setEmptyLineNumber(int emptyLineNumber) {
        this.emptyLineNumber = emptyLineNumber;
    }

    public Integer getTotalLines() {
        return totalLines;
    }

    public void setTotalLines(Integer totalLines) {
        this.totalLines = totalLines;
    }

    public int[][] getSeatingMatrix() {
        return seatingMatrix;
    }

    public void setSeatingMatrix(int[][] seatingMatrix) {
        this.seatingMatrix = seatingMatrix;
    }

    public List<StringBuilder> getLineReaderText() {
        return lineReaderText;
    }

    public void setLineReaderText(List<StringBuilder> lineReaderText) {
        this.lineReaderText = lineReaderText;
    }
}
