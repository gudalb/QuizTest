package se.nackademin;

public class Question {
    String question;
    String[] questionArray;
    String correctIndex;

    public Question (String question, String ans1, String ans2, String ans3, String ans4, String correctIndex) {
        this.question = question;
        this.questionArray = new String[]{ans1,ans2,ans3,ans4};
        this.correctIndex = correctIndex;
    }

    public String getQuestionText () {
        return question + "\n" +
                "0 " + questionArray[0] + "\n" +
                "1 " + questionArray[1] + "\n" +
                "2 " + questionArray[2] + "\n" +
                "3 " + questionArray[3] + "\n";
    }

    public String getCorrectIndex () {
        return correctIndex;
    }
}
