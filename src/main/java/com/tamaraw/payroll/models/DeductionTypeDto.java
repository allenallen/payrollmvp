package com.tamaraw.payroll.models;

public class DeductionTypeDto {

    private Long id;
    private String type;

    public DeductionTypeDto(Long id, String type) {
        this.id = id;
        this.type = type;
    }

    public DeductionTypeDto(DeductionType deductionType) {
        this.id = deductionType.getId().getValue();
        this.type = deductionType.getType().getValue();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
