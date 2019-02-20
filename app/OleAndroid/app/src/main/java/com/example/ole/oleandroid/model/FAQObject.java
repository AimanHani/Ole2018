package com.example.ole.oleandroid.model;

public class FAQObject {
    int faqId;
    String answer;
    String question;
    String category;

    public FAQObject(int faqId, String answer, String question, String category) {
        this.faqId = faqId;
        this.answer = answer;
        this.question = question;
        this.category = category;
    }


    public int getFaqId() {
        return faqId;
    }

    public void setFaqId(int faqId) {
        this.faqId = faqId;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
