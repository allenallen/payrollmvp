package com.tamaraw.payroll.government;

public class Pagibig {
    private static final Double employeePercentage = 0.02;
    private static final Double employerPercentage = 0.02;

    public static Double computeTotalContribution(Double salary) {
        if (salary >= 5000) {
            return (5000 * employeePercentage) + (5000 * employerPercentage);
        } else {
            return (salary * employerPercentage) + (salary * employeePercentage);
        }
    }
}
