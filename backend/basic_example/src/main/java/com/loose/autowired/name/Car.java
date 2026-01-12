package com.loose.autowired.name;

public class Car {
    private Specification specification;

    public void setSpecification(Specification specification) {
        this.specification = specification;
    }

    public void displayDetails() {
        System.out.println("Car Detail: " + specification.toString());
    }
}
