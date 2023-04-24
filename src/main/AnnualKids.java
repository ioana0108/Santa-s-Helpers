package main;

import java.util.ArrayList;

public class AnnualKids {
    public ArrayList<Copil> children = new ArrayList<Copil>(); // lista asta contine copii dintr-un singur an


    // Getters and Setters

    public ArrayList<Copil> getChildren() {
        return children;
    }

    public void setChildren(ArrayList<Copil> children) {
        this.children = children;
    }

    @Override
    public String toString() {
        return "AnnualKids{" +
                "children=" + children +
                '}';
    }

    // Pune copii prelucrati pe parcursul unui an in lista finala din acel an, care va fi afisata in fisier
    public void getAnnualChildren(ArrayList<Copil> children, ArrayList<Copil> annualChildren) {
        Copil cobai = new Copil();
        for (var copil : children) {
            Copil copieCopil = new Copil();
            copieCopil = cobai.makeChildCopy(copil);

            if (copieCopil.getAge() <= 18) {
                annualChildren.add(copieCopil);
            }
        }
    }

}
