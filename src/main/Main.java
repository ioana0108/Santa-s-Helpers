package main;

import checker.Checker;
import com.fasterxml.jackson.databind.ObjectMapper;

import main.elf.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Class used to run the code
 */
public final class Main {

    private Main() {
        ///constructor for checkstyle
    }
    /**
     * This method is used to call the checker which calculates the score
     * @param args
     *          the arguments used to call the main method
     */
    public static void main(final String[] args) throws IOException {

        // Diverse declarari:
        ObjectMapper objectMapper = new ObjectMapper();
        String currFile = new String();
        Input input = new Input();
        Copil cobai = new Copil();
        RoundKids roundKids = new RoundKids();
        AnnualKids annualKids = new AnnualKids();
        SantaSingleton santa = SantaSingleton.Singleton();
        santa.childrenList = new ArrayList<Copil>();
        ArrayList<Cadou> giftList = new ArrayList<Cadou>();
        double budgetUnit = 0;


        // PARCURG FISIERELE SI EXTRAG INPUT-URILE:
        for (int fileIndex = 1; fileIndex <= 30; fileIndex++) {

            // La inceputul fiecarei runde toate listele trebuie sa fie resetate:

            cobai.clearLists(santa.childrenList, giftList, annualKids.children, roundKids.annualChildren);

            currFile = String.valueOf(fileIndex);

            input = objectMapper.readValue(new File("tests/test" + currFile + ".json"), Input.class);


            // FAZA 0:

            // Pun in lista mosului toti copiii primiti din input-ul curent, mai putin copiii deja adulti:
            cobai.getInitialChildren(santa.childrenList, input);

            // Pun in lista giftList cadourile primite din input-ul curent:
            giftList.addAll(input.getInitialData().getSantaGiftsList());

            cobai.setAverageScore(santa.childrenList);

            cobai.setUpdatedAverageScore(santa.childrenList);

            budgetUnit = input.getSantaBudget() / cobai.calculateSumAverageScores(santa.childrenList);

            cobai.assignBudget(santa.childrenList, budgetUnit);

            Elf elf = new Elf("elf", new BlackStrategy()); // nu conteaza cu ce l-am initializat

            elf.applyBlackOrPinkChanges(santa.childrenList);

            cobai.assignGifts("id", santa.childrenList, input.getInitialData().getSantaGiftsList());

            // Adaug copiii prelucrati in lista annual_kids.children0 ca sa-i pot afisa frumos in fisier:
            AnnualKids annualKids0 = new AnnualKids();
            annualKids0.children = new ArrayList<>();

            annualKids0.getAnnualChildren(santa.childrenList, annualKids0.children);

            roundKids.annualChildren.add(annualKids0);


            // SIMULAREA PE CEI NUMBEROFYEARS ANI:

            for (int an = 1; an <= input.getNumberOfYears(); an++) {

                annualKids = new AnnualKids(); // o resetare

                cobai.incrementAge(santa.childrenList);

                santa.childrenList.removeIf((Copil copil) -> copil.getAge() > 18);

                AnnualChanges annualChange = new AnnualChanges();

                // bag copiii noi:
                annualChange.addNewChildren(santa.childrenList, input, an);

                // Updatam copiii:
                annualChange.updateChildren(santa.childrenList, input, an);

                // Adaug noile cadouri:
                annualChange.addNewGifts(giftList, input, an);

                // Am calculat averageScore-ul pentru fiecare copil:
                cobai.setAverageScore(santa.childrenList);

                // Am adaugat bonusul de cumintenie pentru fiecare copil:
                cobai.setUpdatedAverageScore(santa.childrenList);

                budgetUnit = input.getAnnualChanges().get(an - 1).getNewSantaBudget() / cobai.calculateSumAverageScores(santa.childrenList);

                // Am calculat bugetul pentru fiecare copil:
                cobai.assignBudget(santa.childrenList, budgetUnit);

                // Am aplicat modificarile pentru copiii care au atribuiti elfii Black sau Pink:
                elf.applyBlackOrPinkChanges(santa.childrenList);

                ArrayList<City> cities = new ArrayList<City>();
                cities = cobai.getCities(santa.childrenList);
                cobai.setNiceScoreForCities(santa.childrenList, cities);
                cobai.setNiceScoreCityforChildren(santa.childrenList, cities);

                // Am atribuit copiilor cadourile in functie de strategia de impartire a cadourilor:
                cobai.assignGifts(input.getAnnualChanges().get(an - 1).getStrategy(), santa.childrenList, giftList);

                cobai.sortListById(santa.childrenList);

                // Am aplicat modificarile oferite de elful Yellow:
                elf.applyYellowChanges(santa.childrenList, giftList);

                cobai.sortListById(santa.childrenList);

                annualKids.getAnnualChildren(santa.childrenList, annualKids.children);

                roundKids.annualChildren.add(annualKids);

            }

            objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File("output/out_" + currFile + ".json"), roundKids);

        }

        Checker.calculateScore();

        System.out.println("afiseaza ceva domne");

    }

}



