package main.elf;

import main.*;


import java.util.ArrayList;

public class Elf {
    private String name;
    ElfStrategy elfStrategy;

    // Constructor
    public Elf(String name, ElfStrategy elfStrategy) {
        this.name = name;
        this.elfStrategy = elfStrategy;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ElfStrategy getElfStrategy() {
        return elfStrategy;
    }

    public void setElfStrategy(ElfStrategy elfStrategy) {
        this.elfStrategy = elfStrategy;
    }


    // nu stiu exact ce va face
    public void applyChanges(Copil copil) {
        this.elfStrategy.applyChanges(copil);
    }

    public void applyBlackOrPinkChanges(ArrayList<Copil> children) {
        Elf blackElf = new Elf("black", new BlackStrategy());
        Elf pinkElf = new Elf("pink", new PinkStrategy());
        for (var child : children) {
            if (child.getElf().equals("black")) {
                blackElf.applyChanges(child);
            } else if (child.getElf().equals("pink")) {
                pinkElf.applyChanges(child);
            }
        }
    }

    public void applyYellowChanges(ArrayList<Copil> children, ArrayList<Cadou> gifts) {
        for (var child : children) {
            if (child.getElf().equals("yellow")) { // daca copilul are atribuit elful galben
                child.getAllAvailableGiftsList().addAll(gifts);
                if (child.receivedGifts.size() == 0) { // daca copilul nu a primit niciun cadou se aplica strategia elfului yellow
                    Elf yellowElf = new Elf("yellow", new YellowStrategy());
                    yellowElf.applyChanges(child);
                    gifts.clear(); // daca adaug fara sa o golesc o sa ma trezesc cu o gramada de cadouri in plus LOGIC
                    gifts.addAll(child.getAllAvailableGiftsList());
                }
            }
        }
    }

}
