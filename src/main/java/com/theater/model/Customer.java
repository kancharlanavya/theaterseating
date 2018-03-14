package com.theater.model;

import java.io.Serializable;

/**
 * Model object that represents a theater Customer.
 */
public class Customer implements Serializable {

    public String name;
    public Integer rowAssigned;
    public Integer sectionAssigned;
    public String comments;
    public int seatingNeeded;

    public Customer(String name, int seatingNeeded) {
        this.name = name;
        this.seatingNeeded = seatingNeeded;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getRowAssigned() {
        return rowAssigned;
    }

    public void setRowAssigned(Integer rowAssigned) {
        this.rowAssigned = rowAssigned;
    }

    public Integer getSectionAssigned() {
        return sectionAssigned;
    }

    public void setSectionAssigned(Integer sectionAssigned) {
        this.sectionAssigned = sectionAssigned;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public int getSeatingNeeded() {
        return seatingNeeded;
    }

    public void setSeatingNeeded(int seatingNeeded) {
        this.seatingNeeded = seatingNeeded;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Customer customer = (Customer) o;

        if (seatingNeeded != customer.seatingNeeded) return false;
        if (!name.equals(customer.name)) return false;
        if (rowAssigned != null ? !rowAssigned.equals(customer.rowAssigned) : customer.rowAssigned != null)
            return false;
        if (sectionAssigned != null ? !sectionAssigned.equals(customer.sectionAssigned) : customer.sectionAssigned != null)
            return false;
        return comments != null ? comments.equals(customer.comments) : customer.comments == null;
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + (rowAssigned != null ? rowAssigned.hashCode() : 0);
        result = 31 * result + (sectionAssigned != null ? sectionAssigned.hashCode() : 0);
        result = 31 * result + (comments != null ? comments.hashCode() : 0);
        result = 31 * result + seatingNeeded;
        return result;
    }
}
