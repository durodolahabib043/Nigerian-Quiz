package com.lambtoncollege.entity;

public class Question {
	
    private String question;
    private String choiceA;
    private String choiceB;
    private String choiceC;
    private String choiceD;
    private int answer;
    
    public Question() {
        super();
    }
    
    @Override
    public String toString() {
        return "Question [question=" + question
        				+ ", choiceA=" + choiceA
        				+ ", choiceB=" + choiceB
        				+ ", choiceC=" + choiceC
        				+ ", choiceD=" + choiceD
        				+ ", answer=" + answer + "]";
    }

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getChoiceA() {
		return choiceA;
	}

	public void setChoiceA(String choiceA) {
		this.choiceA = choiceA;
	}

	public String getChoiceB() {
		return choiceB;
	}

	public void setChoiceB(String choiceB) {
		this.choiceB = choiceB;
	}

	public String getChoiceC() {
		return choiceC;
	}

	public void setChoiceC(String choiceC) {
		this.choiceC = choiceC;
	}

	public String getChoiceD() {
		return choiceD;
	}

	public void setChoiceD(String choiceD) {
		this.choiceD = choiceD;
	}

	public int getAnswer() {
		return answer;
	}

	public void setAnswer(int answer) {
		this.answer = answer;
	}

}
