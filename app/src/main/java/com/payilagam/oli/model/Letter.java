package com.payilagam.oli.model;

public class Letter {
     private String letter;

    public Letter(String letter, int letterIndex, String letterGroup) {
        this.letter = letter;
        this.letterIndex = letterIndex;
        this.letterGroup = letterGroup;
    }

    public String getLetter() {
        return letter;
    }

    public void setLetter(String letter) {
        this.letter = letter;
    }

    public int getLetterIndex() {
        return letterIndex;
    }

    public void setLetterIndex(int letterIndex) {
        this.letterIndex = letterIndex;
    }

    public String getLetterGroup() {
        return letterGroup;
    }

    public void setLetterGroup(String letterGroup) {
        this.letterGroup = letterGroup;
    }

    private int letterIndex;
    private String letterGroup;


}
