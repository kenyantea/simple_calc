import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Calc {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String expression = br.readLine();
        try {
            double result = evaluate(expression);
            System.out.println("Результат: " + result);
        } catch (Exception e) {
            System.out.println("Ошибка вычисления: " + e.getLocalizedMessage());
        }
    }

    public static double evaluate(String expression) throws Exception {
        if (!isValidExpression(expression)) {
            throw new Exception("Некорректный ввод");
        }

        String[] tokens = tokenize(expression);
        Stack<Double> values = new Stack<>();
        Stack<Character> operators = new Stack<>();

        for (String token : tokens) {
            if (token.isEmpty()) continue;

            if (isNumber(token)) {
                values.push(Double.parseDouble(token));
            } else if (token.charAt(0) == '(') {
                operators.push('(');
            } else if (token.charAt(0) == ')') {
                while (operators.peek() != '(') {
                    applyOperator(values, operators);
                }
                operators.pop();
            } else if (isOperator(token.charAt(0))) {
                while (!operators.isEmpty() && precedence(operators.peek()) >= precedence(token.charAt(0))) {
                    applyOperator(values, operators);
                }
                operators.push(token.charAt(0));
            }
        }

        while (!operators.isEmpty()) {
            applyOperator(values, operators);
        }

        return values.pop();
    }

    private static void applyOperator(Stack<Double> values, Stack<Character> operators) throws Exception {
        char op = operators.pop();
        double b = values.pop();
        double a = values.pop();
        values.push(applyOperation(op, a, b));
    }

    private static String[] tokenize(String expression) {
        Pattern pattern = Pattern.compile("-?\\d+(\\.\\d+)?|[-+*/()]");
        Matcher matcher = pattern.matcher(expression);
        List<String> tokens = new ArrayList<>();

        while (matcher.find()) {
            String token = matcher.group();
            // обработка унарного минуса
            if (token.equals("-") && (tokens.isEmpty() || isOperatorOrOpenBracket(tokens.get(tokens.size() - 1)))) {
                if (matcher.find()) {
                    token += matcher.group();
                } else {
                    throw new IllegalArgumentException("Некорректный унарный минус");
                }
            }
            tokens.add(token);
        }

        return tokens.toArray(new String[0]);
    }

    private static boolean isOperatorOrOpenBracket(String token) {
        return token.equals("+") || token.equals("-") || token.equals("*") || token.equals("/") || token.equals("(");
    }

    private static boolean isValidExpression(String expression) {
        int openBrackets = 0;
        for (char c : expression.toCharArray()) {
            if (c == '(') openBrackets++;
            else if (c == ')') openBrackets--;
            if (openBrackets < 0) return false;
        }
        return openBrackets == 0;
    }

    private static boolean isNumber(String str) {
        return str.matches("-?\\d+(\\.\\d+)?");
    }

    private static boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/';
    }

    private static int precedence(char operator) {
        switch (operator) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
            default:
                return 0;
        }
    }

    private static double applyOperation(char operator, double a, double b) throws Exception {
        switch (operator) {
            case '+':
                return a + b;
            case '-':
                return a - b;
            case '*':
                return a * b;
            case '/':
                if (b == 0) throw new Exception("Деление на ноль");
                else return a / b;
            default:
                throw new IllegalArgumentException("Неизвестный оператор: " + operator);
        }
    }
}