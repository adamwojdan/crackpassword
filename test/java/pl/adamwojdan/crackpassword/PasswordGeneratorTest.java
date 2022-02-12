package pl.adamwojdan.crackpassword;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PasswordGeneratorTest {

    @Test
    public void printCombinations_showNumbersNull_throwsNullPointerException() {

        //given
        Boolean isNull = null;
        PartOfPassword part1 = new PartOfPassword.Builder("p1s1", "p1s2").build();
        PartOfPassword part2 = new PartOfPassword.Builder("p2s1", "p2s2").build();
        PasswordGenerator passwordGenerator = new PasswordGenerator(part1, part2);

        //when

        //then
        Assertions.assertThrows(NullPointerException.class, () -> passwordGenerator.printCombinations(isNull));
    }

    @Test
    public void addPartToPasswords_partNumberOutOfBound_throwsIndexOutOfBoundsException() {

        //given
        int partNumber = 2;
        PartOfPassword part1 = new PartOfPassword.Builder("p1s1", "p1s2").build();
        PartOfPassword part2 = new PartOfPassword.Builder("p2s1", "p2s2").build();
        PasswordGenerator passwordGenerator = new PasswordGenerator(part1, part2);

        //when

        //then
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> passwordGenerator.addPartToPasswords(partNumber));
    }


}




