package pl.adamwojdan.crackpassword;

public class Main {
    public static void main(String[] args) {

        PartOfPassword prefix = new PartOfPassword.Builder("!", "$")
                .addEmpty()
                .build();
        PartOfPassword part1 = new PartOfPassword.Builder("haslo", "QWERTY")
                .addCapital(LetterType.ALTERNATELY, LetterType.ALTERNATELY_INVERT, LetterType.FIRST, LetterType.LAST)
                .addLowercase()
                .addUppercase()
                .build();
        PartOfPassword part2 = new PartOfPassword.Builder("2022", "1234")
                .addReverse()
                .build();

        PasswordGenerator passwords = new PasswordGenerator(prefix, part1, part2);
        passwords.printCombinations(true);
    }
}
