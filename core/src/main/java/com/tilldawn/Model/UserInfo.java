package com.tilldawn.Model;

import com.tilldawn.Model.enums.Avatar;
import com.tilldawn.Model.enums.SecurityQuestion;

public class UserInfo {
    private String name;
    private String password;
    private SecurityQuestion securityQuestion;
    private String securityAnswer;
    private int age;
    private int score;
    private int kill;
    private Avatar avatar;
    private int timeAlive;

    public UserInfo(String name, String password, SecurityQuestion securityQuestion, String securityAnswer, int age, int score, int kill, int timeAlive, Avatar avatar) {
        this.name = name;
        this.password = password;
        this.securityQuestion = securityQuestion;
        this.securityAnswer = securityAnswer;
        this.age = age;
        this.score = score;
        this.kill = kill;
        this.timeAlive = timeAlive;
        this.avatar = avatar;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public SecurityQuestion getSecurityQuestion() {
        return securityQuestion;
    }

    public void setSecurityQuestion(SecurityQuestion securityQuestion) {
        this.securityQuestion = securityQuestion;
    }

    public String getSecurityAnswer() {
        return securityAnswer;
    }

    public void setSecurityAnswer(String securityAnswer) {
        this.securityAnswer = securityAnswer;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getKill() {
        return kill;
    }

    public void setKill(int kill) {
        this.kill = kill;
    }

    public int getTimeAlive() {
        return timeAlive;
    }

    public void setTimeAlive(int timeAlive) {
        this.timeAlive = timeAlive;
    }

    public Avatar getAvatar() {
        return avatar;
    }

    public void setAvatar(Avatar avatar) {
        this.avatar = avatar;
    }
}
