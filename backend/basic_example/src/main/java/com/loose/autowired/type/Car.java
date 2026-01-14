package com.loose.autowired.type;

public class Car {
    private Specification specification;

    public void setSpecification(Specification specification) {
        this.specification = specification;
    }

    public void displayDetails() {
        System.out.println("Car Detail: " + specification.toString());
    }
}
