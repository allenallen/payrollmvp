package com.tamaraw.payroll.government;

public class SSSContribution {
    private Double er;
    private Double ee;
    private Double contributionEr;

    public SSSContribution(Double contributionEr, Double er, Double ee) {
        this.er = er;
        this.ee = ee;
        this.contributionEr = contributionEr;
    }

    public Double getEr() {
        return er;
    }

    public void setEr(Double er) {
        this.er = er;
    }

    public Double getEe() {
        return ee;
    }

    public void setEe(Double ee) {
        this.ee = ee;
    }

    public Double getContributionEr() {
        return contributionEr;
    }

    public void setContributionEr(Double contributionEr) {
        this.contributionEr = contributionEr;
    }
}
