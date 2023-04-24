package main.elf;

import main.*;
import java.util.ArrayList;

public class PinkStrategy implements ElfStrategy {
    public void applyChanges(Copil copil) {
        Double budget = 0.0;
        budget = copil.getAssignedBudget();
        copil.setAssignedBudget(budget + (budget * 30) / 100);
    }
}
