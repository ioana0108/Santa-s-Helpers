package main;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.Collections;

public class Copil {
    private int id;
    private String lastName;
    private String firstName;
    private String city;
    private int age;
    @JsonIgnore
    private double niceScore;
    @JsonIgnore
    private double niceScoreCity;
    private ArrayList<String> giftsPreferences = new ArrayList<String>();
    @JsonIgnore
    private double niceScoreBonus;
    @JsonIgnore
    private String elf;
    private double averageScore;
    public ArrayList<Double> niceScoreHistory = new ArrayList<Double>();
    private double assignedBudget;
    public ArrayList<Cadou> receivedGifts = new ArrayList<Cadou>();
    @JsonIgnore
    private ArrayList<Cadou> allAvailableGiftsList = new ArrayList<Cadou>();


    // Getters and Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }


    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }


    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }


    public ArrayList<String> getGiftsPreferences() {
        return giftsPreferences;
    }

    public void setGiftsPreferences(ArrayList<String> giftsPreferences) {
        this.giftsPreferences = giftsPreferences;
    }


    public double getAverageScore() {
        return averageScore;
    }

    public void setAverageScore(double averageScore) {
        this.averageScore = averageScore;
    }

    @JsonIgnore
    public double getNiceScore() {
        return niceScore;
    }
    @JsonProperty
    public void setNiceScore(double niceScore) {
        this.niceScore = niceScore;
    }

    @JsonIgnore
    public double getNiceScoreCity() {
        return niceScoreCity;
    }
    @JsonProperty
    public void setNiceScoreCity(double niceScoreCity) {
        this.niceScoreCity = niceScoreCity;
    }

    @JsonIgnore
    public double getNiceScoreBonus() {
        return niceScoreBonus;
    }
    @JsonProperty
    public void setNiceScoreBonus(double niceScoreBonus) {
        this.niceScoreBonus = niceScoreBonus;
    }

    @JsonIgnore
    public String getElf() {
        return elf;
    }
    @JsonProperty
    public void setElf(String elf) {
        this.elf = elf;
    }

    public ArrayList<Double> getNiceScoreHistory() {
        return niceScoreHistory;
    }

    public void setNiceScoreHistory(ArrayList<Double> niceScoreHistory) {
        this.niceScoreHistory = niceScoreHistory;
    }


    public double getAssignedBudget() {
        return assignedBudget;
    }

    public void setAssignedBudget(double assignedBudget) {
        this.assignedBudget = assignedBudget;
    }

    @JsonIgnore
    public ArrayList<Cadou> getAllAvailableGiftsList() {
        return allAvailableGiftsList;
    }
    @JsonProperty
    public void setAllAvailableGiftsList(ArrayList<Cadou> allAvailableGiftsList) {
        this.allAvailableGiftsList = allAvailableGiftsList;
    }


    // Methods

    public void getInitialChildren(ArrayList<Copil> children, Input input) {
        for (var child : input.getInitialData().getChildren()) {
            if (child.getAge() <= 18) {
                child.niceScoreHistory.add(child.getNiceScore());
                children.add(child);
            }
        }
    }

    public void incrementAge(ArrayList<Copil> children) {
        for (var child : children) {
            child.setAge(child.getAge() + 1);
            child.receivedGifts.clear();
        }
    }

    // calculeaza averageScore pentru un singur copil
    public double calculateAverageScore(ArrayList<Copil> children) {
        if (this.getAge() < 5) {
            return 10;
        } else if (this.getAge() >= 5 && this.getAge() < 12) {
            double sum = 0;
            for (var i : this.getNiceScoreHistory()) {
                sum += i;
            }
            return (sum / this.getNiceScoreHistory().size());
        } else if (this.getAge() >= 12 && this.getAge() <= 18) {
            double sumProducts = 0, sumPos = 0;
            int poz = 0;
            for (var i : this.getNiceScoreHistory()) {
                poz++;
                sumProducts += i * poz;
                sumPos += poz;
            }

            return sumProducts / sumPos;
        }

        return 0;
    }

    // Atribuie averageScore pentru o intreaga lista
    public void setAverageScore(ArrayList<Copil> children) {
        for (var copil : children) {
            copil.setAverageScore(copil.calculateAverageScore(children));
        }
    }

    // Atribuie averageScore cu tot cu bonus pentru o intreaga lista
    public void setUpdatedAverageScore(ArrayList<Copil> children) {
        double score = 0.0;
        for (Copil c : children) {
            score = c.getAverageScore() + c.getAverageScore() * c.getNiceScoreBonus() / 100;
            if (score > 10) {
                score = 10;
            }
            c.setAverageScore(score);
        }
    }

    // Functie de calculare a sumei de averageScores a unei liste de obiecte copil
    public double calculateSumAverageScores(ArrayList<Copil> children) {
        double sum = 0;
        for (var copil : children) { // parcurg lista mosului si atribui fiecarui copil un averageScore
            sum += copil.getAverageScore();
        }
        return sum;
    }

    public void assignBudget(ArrayList<Copil> children, double budget) {
        for (var child : children) { // parcurg lista si aloc fiecarui copil cate un buget, acum e posibil, caci am budgetUnit
            child.setAssignedBudget(child.getAverageScore() * budget);
        }
    }

    public void removeCityDuplicates(ArrayList<City> initialList, ArrayList<City> modifiedList) {
        int ok;
        for (City c1 : initialList) {
            ok = 1;
            for (City c2 : modifiedList) {
                if (c1.getName().equals(c2.getName())) {
                    ok = 0;
                }
            }
            if (ok == 1) {
                modifiedList.add(c1);
            }
        }
    }

    public ArrayList<City> getCities(ArrayList<Copil> children) {

        ArrayList<City> citiesWithDuplicates = new ArrayList<City>();
        ArrayList<City> citiesWithOutDuplicates = new ArrayList<City>();

        for (Copil child : children) {
            City city = new City();
            city.setName(child.getCity());
            citiesWithDuplicates.add(city);
        }
        removeCityDuplicates(citiesWithDuplicates, citiesWithOutDuplicates);

        return citiesWithOutDuplicates;
    }

    public void setNiceScoreForCities(ArrayList<Copil> children, ArrayList<City> cities) {
        for (City city : cities) {
            double sum = 0;
            int nrChildren = 0;
            for (Copil child : children) {
                if (city.getName().equals(child.getCity())) {
                    sum += child.getAverageScore();
                    nrChildren++;
                }
            }
            city.setNiceScoreCity(sum / nrChildren);
        }
    }

    public void setNiceScoreCityforChildren(ArrayList<Copil> children, ArrayList<City> cities) {
        for (Copil child : children) {
            for (City city : cities) {
                if (child.getCity().equals(city.getName())) {
                    child.setNiceScoreCity(city.getNiceScoreCity());
                }
            }
        }
    }

    // functie care ordoneaza o lista de copii dupa id:
    public void sortListById(ArrayList<Copil> children) {
        for (int i = 0; i < children.size(); i++) {
            for (int j = i + 1; j < children.size(); j++) {
                if (children.get(i).getId() > children.get(j).getId()) {
                    Collections.swap(children, i, j);
                }
            }
        }
    }

    // functie care ordoneaza o lista de copii dupa averageScore:
    public void sortListByAverageScore(ArrayList<Copil> children) {
        for (int i = 0; i < children.size(); i++) {
            for (int j = i + 1; j < children.size(); j++) {
                if (children.get(i).getAverageScore() < children.get(j).getAverageScore()) {
                    Collections.swap(children, i, j);
                } else if (children.get(i).getAverageScore() == children.get(j).getAverageScore()) {
                    if (children.get(i).getId() > children.get(j).getId()) {
                        Collections.swap(children, i, j);
                    }
                }
            }
        }
    }

    // functie care ordoneaza o lista de copii dupa niceScoreCity:
    public void sortListByNiceScoreCity(ArrayList<Copil> children) {
        Copil cobai = new Copil();
        cobai.sortListById(children);
        for (int i = 0; i < children.size(); i++) {
            for (int j = i + 1; j < children.size(); j++) {
                if (children.get(i).getNiceScoreCity() < children.get(j).getNiceScoreCity()) {
                    Collections.swap(children, i, j);
                } else if (children.get(i).getNiceScoreCity() == children.get(j).getNiceScoreCity()) {
                    if (children.get(i).getCity().compareTo(children.get(j).getCity()) > 0) {
                        Collections.swap(children, i, j);
                    } else if(children.get(i).getId() > children.get(j).getId()) {
                        Collections.swap(children, i, j);
                    }
                }
            }
        }

    }

    public void assignGifts(String assignStrategy, ArrayList<Copil> listaCopii, ArrayList<Cadou> listaCadouri) {

        if (assignStrategy.equals("id")) {
            sortListById(listaCopii);
        } else if (assignStrategy.equals("niceScore")) {
            sortListByAverageScore(listaCopii);
        } else if (assignStrategy.equals("niceScoreCity")) {
            sortListByNiceScoreCity(listaCopii);
        }

        for (var copil : listaCopii) {
            double budget = copil.getAssignedBudget();
            ArrayList<String> categories = new ArrayList<>(); // retin categoriile din care copilul deja a primit cadouri

            for (var pref : copil.getGiftsPreferences()) {
                ArrayList<Cadou> codouri_din_aceeasi_categ = new ArrayList<Cadou>();
                double pret_minim = 999999;

                for (var gift : listaCadouri) {
                    if (gift.getCategory().equals(pref) && gift.getQuantity() > 0) {
                        if (!categories.contains(gift.getCategory())) {
                            if (gift.getPrice() <= budget) {
                                codouri_din_aceeasi_categ.add(gift);
                            }
                        }

                    }
                }

                // Luam din lista cadouri_din_aceeasi_categ cadoul cel mai ieftin
                for (var cadou : codouri_din_aceeasi_categ) {
                    if (cadou.getPrice() < pret_minim) {
                        pret_minim = cadou.getPrice();
                    }
                }
                int adaugat = 0;
                for (var cadou : codouri_din_aceeasi_categ) {
                    if (cadou.getPrice() == pret_minim && adaugat == 0) {
                        copil.receivedGifts.add(cadou);
                        cadou.setQuantity(cadou.getQuantity() - 1);
                        adaugat = 1;
                        categories.add(cadou.getCategory());
                        budget -= cadou.getPrice();
                    }
                }
            }
        }

    }

    public void sortGiftList(ArrayList<Cadou> giftList) {
        for (int i = 0; i < giftList.size(); i++) {
            for (int j = i + 1; j < giftList.size(); j++) {
                if (giftList.get(i).getPrice() > giftList.get(j).getPrice()) {
                    Collections.swap(giftList, i, j);
                }
            }
        }
    }

    public void removeDuplicates(ArrayList<String> initialList, ArrayList<String> modifiedList) {
        for (var pref : initialList) {
            if (!modifiedList.contains(pref)) {
                modifiedList.add(pref);
            }
        }
    }

    public Copil makeChildCopy(Copil copil) {
        Copil copy = new Copil();
        copy.setId(copil.getId());
        copy.setLastName(copil.getLastName());
        copy.setFirstName(copil.getFirstName());
        copy.setCity(copil.getCity());
        copy.setAge(copil.getAge());
        copy.setAverageScore(copil.getAverageScore());
        copy.setNiceScore(copil.getNiceScore());

        copy.getNiceScoreHistory().addAll(copil.getNiceScoreHistory());
        copy.getGiftsPreferences().addAll(copil.getGiftsPreferences());

        copy.setAssignedBudget(copil.getAssignedBudget());

        for (var cadou : copil.receivedGifts) {
            copy.receivedGifts.add(cadou);
        }

        return copy;
    }

    public void clearLists(ArrayList<Copil> list1, ArrayList<Cadou> list2, ArrayList<Copil> list3, ArrayList<AnnualKids> list4) {
        list1.clear();
        list2.clear();
        list3.clear();
        list4.clear();
    }

}
