package com.tamaraw.payroll.utils;

public class IDHolder {

    private final static IDHolder INSTANCE = new IDHolder();

    private Long id;

    public static IDHolder getInstance() {
        return INSTANCE;
    }

    public static void setId(Long id) {
        INSTANCE.id = id;
    }

    public Long getId() {
        return id;
    }

    private IDHolder() {}
}
