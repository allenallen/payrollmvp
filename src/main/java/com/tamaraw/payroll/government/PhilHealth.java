package com.tamaraw.payroll.government;

public class PhilHealth {
    private static final Double premiumRate = 0.03;

    public static Double computeContribution(Double salary) {
        if (salary <= 10000.00) {
            return 300.00;
        } else if (salary > 10000.00 && salary < 60000.00) {
            return salary * premiumRate;
        } else {
            return 1800.00;
        }
    }
}
