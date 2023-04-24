package main;

import java.util.ArrayList;

public class Input {
    private int numberOfYears;
    private double santaBudget;
    private InitialData initialData;
    private ArrayList<AnnualChanges> annualChanges;


    public int getNumberOfYears() {
        return numberOfYears;
    }

    public void setNumberOfYears(int numberOfYears) {
        this.numberOfYears = numberOfYears;
    }

    public double getSantaBudget() {
        return santaBudget;
    }

    public void setSantaBudget(double santaBudget) {
        this.santaBudget = santaBudget;
    }

    public InitialData getInitialData() {
        return initialData;
    }

    public void setInitialData(InitialData initialData) {
        this.initialData = initialData;
    }

    public ArrayList<AnnualChanges> getAnnualChanges() {
        return annualChanges;
    }

    public void setAnnualChanges(ArrayList<AnnualChanges> annualChanges) {
        this.annualChanges = annualChanges;
    }
}
