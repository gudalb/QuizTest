package se.nackademin;

import java.util.ArrayList;
import java.util.List;

public class QuestionDB {
    public List<Question> questionList = new ArrayList<>();
    public final Question q1 = new Question("fråga 1", "fel", "fel", "rätt", "fel","2");
    public final Question q2 = new Question("fråga 2", "rätt", "fel", "fel", "fel","0");
    public final Question q3 = new Question("fråga 3", "fel", "rätt", "fel", "fel","1");
    public final Question q4 = new Question("fråga 4", "fel", "fel", "fel", "rätt","3");

    public QuestionDB () {
        questionList.add(q1);
        questionList.add(q2);
        questionList.add(q3);
        questionList.add(q4);
    }
}
