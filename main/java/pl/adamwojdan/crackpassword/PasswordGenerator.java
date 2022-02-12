package pl.adamwojdan.crackpassword;

public class PasswordGenerator {

    private final PartOfPassword[] partOfPasswords;

    private StringBuilder[] passwords;
    private int allCombinations;
    private int[] combinationsPerPart;

    PasswordGenerator(PartOfPassword... partOfPasswords) {
        this.partOfPasswords = partOfPasswords;
        combinationsPerPart = new int[partOfPasswords.length];
        init();
    }

    private void init() {
        allCombinations = countAllCombinations();
        combinationsPerPart = countCombinationsPerPart();
        buildPasswords();
    }

    public int countAllCombinations() {
        int combinations = 1;
        for (PartOfPassword partOfPassword : partOfPasswords) {
            combinations *= partOfPassword.getLength();
        }
        return combinations;
    }

    private int[] countCombinationsPerPart() {
        int[] combinationsPart = new int[partOfPasswords.length];
        for (int i = 0; i < partOfPasswords.length; i++) {
            if (i == 0) {
                combinationsPart[i] = allCombinations / partOfPasswords[i].getLength();
            } else {
                combinationsPart[i] = combinationsPart[i - 1] / partOfPasswords[i].getLength();
            }
        }
        return combinationsPart;
    }

    private void buildPasswords() {
        initPasswords();
        for (int i = 0; i < partOfPasswords.length; i++) {
            addPartToPasswords(i);
        }
    }

    private void initPasswords() {
        passwords = new StringBuilder[allCombinations];

        for (int i = 0; i < allCombinations; i++) {
            passwords[i] = new StringBuilder();
        }
    }

    public void addPartToPasswords(int partNumber) {
        int stringNumber = 0;
        for (int i = 0; i < allCombinations; i++) {
            if (i != 0 && i % combinationsPerPart[partNumber] == 0) {
                if (stringNumber+1 < partOfPasswords[partNumber].getLength()) {
                    stringNumber++;
                } else {
                    stringNumber = 0;
                }
                passwords[i].append(partOfPasswords[partNumber].getString(stringNumber));
            } else {
                passwords[i].append(partOfPasswords[partNumber].getString(stringNumber));
            }
        }
    }

    public void printCombinations() {
        for (int i = 0; i < allCombinations; i++) {
            System.out.println(passwords[i]);
        }
    }

    public void printCombinations(boolean showNumbers) {
        for (int i = 0; i < allCombinations; i++) {
            if (showNumbers) {
                System.out.print((i + 1) + ". ");
            }
            System.out.println(passwords[i]);
        }
    }

}
