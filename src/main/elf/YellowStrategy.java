package main.elf;

import main.*;

import java.util.ArrayList;

public class YellowStrategy implements ElfStrategy {
    public void applyChanges(Copil child) {
        Copil cobai = new Copil();
        cobai.sortGiftList(child.getAllAvailableGiftsList());
        // child.setAllGiftsList(
        String prefCategory;
        prefCategory = child.getGiftsPreferences().get(0);

        int gasit = 0;
        for (int i = 0; i < child.getAllAvailableGiftsList().size() && gasit == 0; i++) { // lista e sortata crescator dupa pret, deci primul cadou care corespunde categoriei
                                                    // este si cel mai ieftin
            if (child.getAllAvailableGiftsList().get(i).getCategory().equals(prefCategory)) {
                if (child.getAllAvailableGiftsList().get(i).getQuantity() > 0) {
                    //if (child.getAssignedBudget() >= child.getAllAvailableGiftsList().get(i).getPrice()) {
                    child.receivedGifts.add(child.getAllAvailableGiftsList().get(i));
                    child.getAllAvailableGiftsList().get(i).setQuantity(child.getAllAvailableGiftsList().get(i).getQuantity() - 1);
                    gasit = 1;
                    //}
                } else {
                    gasit = 1; // tehnic nu s-a gasit cadou, dar elful galben ofera cadou doar daca exista stoc pentru
                               // cadoul cel mai ieftin din cate exista
                }
            }
        }

    }

}
