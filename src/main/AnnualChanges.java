package main;

import java.util.ArrayList;

public class AnnualChanges {
    private double newSantaBudget;
    private ArrayList<Cadou> newGifts;
    private ArrayList<Copil> newChildren;
    private ArrayList<ChildUpdate> childrenUpdates;
    private String strategy;

    public double getNewSantaBudget() {
        return newSantaBudget;
    }

    public void setNewSantaBudget(double newSantaBudget) {
        this.newSantaBudget = newSantaBudget;
    }

    public ArrayList<Cadou> getNewGifts() {
        return newGifts;
    }

    public void setNewGifts(ArrayList<Cadou> newGifts) {
        this.newGifts = newGifts;
    }

    public ArrayList<Copil> getNewChildren() {
        return newChildren;
    }

    public void setNewChildren(ArrayList<Copil> newChildren) {
        this.newChildren = newChildren;
    }

    public ArrayList<ChildUpdate> getChildrenUpdates() {
        return childrenUpdates;
    }

    public void setChildrenUpdates(ArrayList<ChildUpdate> childrenUpdates) {
        this.childrenUpdates = childrenUpdates;
    }

    public String getStrategy() {
        return strategy;
    }

    public void setStrategy(String strategy) {
        this.strategy = strategy;
    }


    // Methods:

    public void addNewChildren(ArrayList<Copil> children, Input input, Integer year) {
        if (input.getAnnualChanges().get(year - 1).getNewChildren().size() > 0) {
            for (var child : input.getAnnualChanges().get(year - 1).getNewChildren()) {
                if(child.getAge() <= 18) {
                    child.getNiceScoreHistory().add(child.getNiceScore());
                    children.add(child);
                }
            }
        }
    }

    public void addNewGifts(ArrayList<Cadou> gifts, Input input, Integer year) {
        if (input.getAnnualChanges().get(year - 1).getNewGifts().size() > 0) {
            gifts.addAll(input.getAnnualChanges().get(year - 1).getNewGifts());
        }
    }

    public void updateChildren(ArrayList<Copil> children, Input input, Integer year) {
        if (input.getAnnualChanges().get(year - 1).getChildrenUpdates().size() > 0) {
            Copil cobai = new Copil();
            for (int i = 0; i < input.getAnnualChanges().get(year - 1).getChildrenUpdates().size(); i++) {
                for (var copil : children) {
                    if (copil.getId() == input.getAnnualChanges().get(year - 1).getChildrenUpdates().get(i).getId()) {
                        // daca am gasit copilul cu id-ul specificat, ii adaug in lista de scoruri, daca scorul primit != null
                        if (input.getAnnualChanges().get(year - 1).getChildrenUpdates().get(i).getNiceScore() != null) {
                            copil.niceScoreHistory.add(input.getAnnualChanges().get(year - 1).getChildrenUpdates().get(i).getNiceScore());
                        }

                        if (input.getAnnualChanges().get(year - 1).getChildrenUpdates().get(i).getGiftsPreferences().size() > 0) {
                            ArrayList<String> aux1 = new ArrayList<>();
                            ArrayList<String> aux2 = new ArrayList<>();

                            // pun in aux 1 lista de noi preferinte ale copilului
                            // salvez in aux2 lista aux1 \ {elementele duplicate}
                            // pun in aux2 lista de preferinte de dinainte de schimbari
                            // sterg preferintele copilului curent si ii atribui lista de preferinte retinuta de aux2
                            // aux2 = preferinte noi (fara duplicate) + preferinte vechi

                            aux1.addAll(input.getAnnualChanges().get(year - 1).getChildrenUpdates().get(i).getGiftsPreferences());
                            cobai.removeDuplicates(aux1, aux2);

                            int ok;
                            for (var pref : copil.getGiftsPreferences()) {
                                ok = 1;
                                for (var newpref : input.getAnnualChanges().get(year - 1).getChildrenUpdates().get(i).getGiftsPreferences()) {
                                    if (pref.equals(newpref)) {
                                        ok = 0;
                                    }
                                }
                                if (ok == 1) {
                                    aux2.add(pref);
                                }
                            }
                            copil.getGiftsPreferences().clear();

                            copil.getGiftsPreferences().addAll(aux2);
                        }
                        if (input.getAnnualChanges().get(year - 1).getChildrenUpdates().get(i).getElf() != null) {
                            copil.setElf(input.getAnnualChanges().get(year - 1).getChildrenUpdates().get(i).getElf());
                        }
                    }
                }
            }
        }
    }
}
