package pl.adamwojdan.crackpassword;

public enum LetterType {
    FIRST,
    LAST,
    ALTERNATELY,
    ALTERNATELY_INVERT,
    RANDOM;

    public boolean contains(LetterType... enums) {
        for (LetterType anEnum : enums) {
            if (anEnum == this) {
                return true;
            }
        }
        return false;
    }
}