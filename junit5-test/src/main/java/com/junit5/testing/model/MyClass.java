package com.junit5.testing.model;

public class MyClass {

    private int balance = 100;
    private boolean isChecking = true;

    public int deposit(int balance) {
        this.balance += balance;
        return this.balance;
    }

    public boolean getChecking() {
        return isChecking;
    }

    public void testException(boolean withCheck) {
        if (withCheck) {
            System.out.println("Unit JavaUnitTestExceptions.test");
        } else {
            throw new IllegalArgumentException();
        }
    }
}