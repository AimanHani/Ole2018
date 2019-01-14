package model;


public class Faq {
    private int faqId;
    private String question;
    private String answer;
    private String category;

   
    
    public Faq(int faqId, String question, String answer, String category){
        this.faqId = faqId;
        this.question = question;
        this.answer = answer;
        this.category = category;
    }
    
    public int getFaqId() {
        return faqId;
    }

    public void setFaqId(int faqId) {
        this.faqId = faqId;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    } 
    
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
