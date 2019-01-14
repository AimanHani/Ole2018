package model;


public class Ask {
    private int askId;
    private String question;
    private String answer;
    private String category;
    private String username;
    
    public Ask(int askId, String question, String answer, String category, String username){
        this.askId = askId;
        this.question = question;
        this.answer = answer;
        this.category = category;
        this.username = username;
    }

    public int getAskId() {
        return askId;
    }

    public void setAskId(int askId) {
        this.askId = askId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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
}
