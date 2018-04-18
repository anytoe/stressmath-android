package mathchallenge.anytoe.com.mathchallenge.model.challenge;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by anytoe on 27/09/2015.
 */
public class MathChallenge extends ArrayList<MathProblem> implements Serializable {

    private static final long serialVersionUID = -8522368046663484683L;

    private long startTimeMs;
    private long startTimeLastProblem;
    private MathGenerator generator;
    private int correctAnswerCount = 0;
    private int score = 0;
    private int timeNextProblemMs = 5000;
    private float timeDecreaseFactor = 0.95f;

    private boolean hasStarted = false;
    private boolean isTimeUp = false;

    //private IChallengeFinishedListener listener;

    public MathChallenge() {
        // nothing to do
        //generator = new MathGenerator(99);
        generator = new MathGenerator((int) (new Date().getTime()));
    }

//    public void setChallengeFinishedListener(IChallengeFinishedListener listener) {
//        this.listener = listener;
//    }

    public MathProblem GetNextMathProblem() {
        if (!hasStarted())
            startTimeMs = new Date().getTime();
        hasStarted = true;
        startTimeLastProblem = new Date().getTime();
        MathProblem currentProblem = generator.nextMathProblem();
        add(currentProblem);
        return currentProblem;
    }

    public MathProblem GetCurrentMathProblem() {
        return get(size() - 1);
    }

    public int getCurrentStartTimeMs() {
        return timeNextProblemMs;
    }

    public int getScore() {
        return score;
    }

    public boolean hasStarted() {
        return hasStarted;
    }

    public boolean hasFinished() {
        return isTimeUp;
    }

    public int getRemainingProblemTime() {
        if (!hasStarted)
            throw new RuntimeException("MathChallenge hasn't started yet. Check 'hasStarted()' first");

        return (int) (startTimeLastProblem + timeNextProblemMs - new Date().getTime());
    }

    public boolean answer(String answer, float answerFactor) {
        answerFactor = ((1 - answerFactor) * 0.9f) + answerFactor;
        boolean isCorrectAnswer = GetCurrentMathProblem().answer(answer);
        if (isCorrectAnswer) {
            score = calculateScore(score, ++correctAnswerCount, answerFactor);
            timeNextProblemMs = Math.round(timeNextProblemMs * timeDecreaseFactor * answerFactor);
        }
//        else if (listener != null) {
//            listener.notifyOnFinish();
//        }

        return isCorrectAnswer;
    }

    private int calculateScore(int currentScore, int answerCount, float answerFactor) {
        return Math.round(currentScore + (answerCount * answerFactor * 100));
    }

    public int getCorrectAnswerCount(){
        return correctAnswerCount;
    }

//    public void timesUp() {
//        listener.notifyOnFinish();
//    }

}
