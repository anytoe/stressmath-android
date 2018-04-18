package mathchallenge.anytoe.com.mathchallenge.model.challenge;

import java.util.Random;

/**
 * Created by anytoe on 02/10/2015.
 */
public class MathGenerator {

    private Random ran;
    private String[] operators;

    public MathGenerator(int seed) {
        ran = new Random(seed);
        operators = new String[2];
        operators[0] = "+";
        operators[1] = "-";
    }

    public MathProblem nextMathProblem() {

        int firstOperand = ran.nextInt(20) + 1;
        int secondOperand = ran.nextInt(20) + 1;
        int operatorIndex = ran.nextInt(2);

        String formattedProblem = formatProblem(firstOperand, secondOperand, operators[operatorIndex]);
        int solution = solveProblem(firstOperand, secondOperand, operators[operatorIndex]);


        MathProblem p1 = new MathProblem(formattedProblem, solution + "");
        AddAnswers(p1, 3);

        return p1;
    }

    private void AddAnswers(MathProblem mathProblem, int answers) {

        for (int counter = 1; counter <= answers; counter++) {
            String wrongAnswer;
            do {
                wrongAnswer = (ran.nextInt(81) - 40) + "";
            }
            while (mathProblem.hasAnswer(wrongAnswer));
            mathProblem.addAnswer(wrongAnswer);
        }
    }

    private int solveProblem(int firstOperand, int secondOperand, String operator) {

        if (operator == "+")
            return firstOperand + secondOperand;
        else if (operator == "-")
            return firstOperand - secondOperand;

        throw new RuntimeException("Unknown Operator");
    }

    private String formatProblem(int firstOperand, int secondOperand, String operator) {
        StringBuffer string = new StringBuffer();
        string.append(firstOperand);
        string.append(" ");
        string.append(operator);
        string.append(" ");
        string.append(secondOperand);

        return string.toString();
    }

}
