package mathchallenge.anytoe.com.mathchallenge.model.challenge;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

/**
 * Created by anytoe on 27/09/2015.
 */
public class MathProblem implements Serializable {

    private static final long serialVersionUID = -3128617500311696221L;

    private String problem;
    private String solution;

    private String givenAnswer;
    private boolean answered;
    private HashSet<String> allAnswers;

    public MathProblem(String problem, String solution) {
        this.problem = problem;
        this.solution = solution;
        this.allAnswers = new HashSet<>();
        allAnswers.add(solution);
    }

    public MathProblem addAnswer(String wrongAnswer) {
        allAnswers.add(wrongAnswer);
        return this;
    }

    public String getProblem() {
        return problem;
    }

    public String[] getRandomizedAnswers() {
        String[] randomizedAnswers = new String[allAnswers.size()];
        List<String> copiedList = new ArrayList<>(allAnswers);

        Random ran = new Random(problem.hashCode());

        int nextIndex = 0;
        while (copiedList.size() > 0) {
            int nextAnswerIndex = ran.nextInt(copiedList.size());
            String nextAnswer = copiedList.get(nextAnswerIndex);
            randomizedAnswers[nextIndex] = nextAnswer;
            copiedList.remove(nextAnswer);
            nextIndex++;
        }

        return randomizedAnswers;
    }

    public boolean hasAnswer(String answer) {
        return allAnswers.contains(answer);
    }

    public boolean answer(String answer) {
        givenAnswer = answer;
        answered = solution.equals(answer);

        return answered;
    }

}
