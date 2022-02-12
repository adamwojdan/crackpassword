package pl.adamwojdan.crackpassword;

import java.util.ArrayList;
import java.util.Arrays;

public class PartOfPassword {
    private final ArrayList<String> arrayList;
    private LetterType[] capitalize;
    private boolean uppercase;
    private boolean lowercase;
    private boolean reverse;
    private boolean empty;

    public static class Builder {
        private final ArrayList<String> arrayList;
        private LetterType[] capitalTypes;
        private boolean uppercase;
        private boolean lowercase;
        private boolean reverse;
        private boolean empty;

        public Builder(String... strings) {
            this.arrayList = new ArrayList<>();
            this.arrayList.addAll(Arrays.asList(strings));
        }

        public Builder addCapital(LetterType... enums) {
            capitalTypes = enums;
            return this;
        }

        public Builder addUppercase() {
            uppercase = true;
            return this;
        }

        public Builder addLowercase() {
            lowercase = true;
            return this;
        }

        public Builder addReverse() {
            reverse = true;
            return this;
        }

        public Builder addEmpty() {
            empty = true;
            return this;
        }

        public PartOfPassword build() {
                addExtraItemsToArray();
            return new PartOfPassword(this);
        }

        private void addExtraItemsToArray() {
            ArrayList<String> newArrayList = new ArrayList<>();
            setCapitals(newArrayList);
            setLowercase(newArrayList);
            setUppercase(newArrayList);
            setReverse(newArrayList);
            setEmpty();
            arrayList.addAll(newArrayList);
        }

        private void setCapitals(ArrayList<String> newStrings) {
            if(capitalTypes != null) {
                addCapitalFirst(newStrings);
                addCapitalLast(newStrings);
                addCapitalAlternately(newStrings);
                addCapitalAlternatelyInvert(newStrings);
            }
        }

        private void addCapitalFirst(ArrayList<String> newStrings) {
            if (LetterType.FIRST.contains(capitalTypes)) {
                for (String s : arrayList) {
                    String str = s.substring(0, 1).toUpperCase() + s.substring(1);
                    newStrings.add(str);
                }
            }
        }

        private void addCapitalLast(ArrayList<String> newStrings) {
            if (LetterType.LAST.contains(capitalTypes)) {
                for (String s : arrayList) {
                    String str = s.substring(0, s.length() - 1) + s.substring(s.length() - 1).toUpperCase();
                    newStrings.add(str);
                }
            }
        }


        private void addCapitalAlternately(ArrayList<String> newStrings) {
            if (LetterType.ALTERNATELY.contains(capitalTypes)) {
                for (String s : arrayList) {
                    char[] arr = s.toLowerCase().toCharArray();
                    boolean invert = true;
                    for (int j = 0; j < arr.length; j++) {
                        if (invert && Character.isLetter(arr[j])) {
                            arr[j] = Character.toUpperCase(arr[j]);
                            invert = false;
                        } else if (!invert && Character.isLetter(arr[j])) {
                            invert = true;
                        }
                    }
                    newStrings.add(String.valueOf(arr));
                }
            }
        }

        private void addCapitalAlternatelyInvert(ArrayList<String> newStrings) {
            if (LetterType.ALTERNATELY_INVERT.contains(capitalTypes)) {
                for (String s : arrayList) {
                    char[] arr = s.toLowerCase().toCharArray();
                    boolean invert = false;
                    for (int j = 0; j < arr.length; j++) {
                        if (invert && Character.isLetter(arr[j])) {
                            arr[j] = Character.toUpperCase(arr[j]);
                            invert = false;
                        } else if (!invert && Character.isLetter(arr[j])) {
                            invert = true;
                        }
                    }
                    newStrings.add(String.valueOf(arr));
                }
            }
        }

        private void setUppercase(ArrayList<String> newStrings) {
            if (uppercase) {
                for (String s : arrayList) {
                    if (!s.toUpperCase().equals(s)) {
                        newStrings.add(s.toUpperCase());
                    }
                }
            }
        }

        private void setLowercase(ArrayList<String> newStrings) {
            if (lowercase) {
                for (String s : arrayList) {
                    if (!s.toLowerCase().equals(s)) {
                        newStrings.add(s.toLowerCase());
                    }
                }
            }
        }

        private void setReverse(ArrayList<String> newStrings) {
            if (reverse) {
                for (String s : arrayList) {
                    StringBuilder stringBuilder = new StringBuilder(s);
                    newStrings.add(stringBuilder.reverse().toString());
                }
            }
        }

        private void setEmpty() {
            if (empty) {
                arrayList.add("");
            }
        }
    }

    public PartOfPassword(Builder builder) {
        arrayList = builder.arrayList;
    }

    private String[] getStringArray() {
        String[] s = new String[arrayList.toArray().length];
        for (int i = 0; i < arrayList.toArray().length; i++) {
            s[i] = arrayList.toArray()[i].toString();
        }
        return s;
    }

    public String getString(int id) {
        String[] strings = getStringArray();
        return strings[id];
    }

    public int getLength() {
        return arrayList.size();
    }
}