package com.tilldawn.Model.enums;

public enum SecurityQuestion {
    FatherName("Father's Name?"),
    FavoriteColor("Favorite color?"),
    Birthplace("Birthplace?");

    private final String question;

    SecurityQuestion(String question) {
        this.question = question;
    }

    public String getQuestion() {
        return question;
    }

    public static SecurityQuestion findByName(String question){
        for (SecurityQuestion securityQuestion : SecurityQuestion.values()){
            if (securityQuestion.getQuestion().equalsIgnoreCase(question)) return securityQuestion;
        }
        return null;
    }
}
