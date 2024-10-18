package com.kimikevin;


public class Dev {

    private Computer computer;

    public Dev() {
        System.out.println("Dev constructor");
    }

    public Computer getComputer() {
        return computer;
    }

    public void setComputer(Computer computer) {
        this.computer = computer;
    }

    public void build() {
        System.out.println("Working on an Awesome Project");
        computer.compile();
    }
}
