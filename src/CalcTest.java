public class CalcTest {

    public static void main(String[] args) {
        testAddition();
        testSubtraction();
        testMultiplication();
        testDivision();
        testOrderOfOperations();
        testParentheses();
        testComplexExpression();
        testDivisionByZero();
        testInvalidExpression();
        testEmptyInput();
    }

    private static void assertEqual(double expected, double actual, double delta, String testName) {
        if (Math.abs(expected - actual) > delta) {
            System.out.println(testName + " failed: expected " + expected + ", actual: " + actual);
        } else {
            System.out.println(testName + " passed.");
        }
    }

    private static void testAddition() {
        try {
            assertEqual(5.0, Calc.evaluate("2 + 3"), 0.0001, "testAddition1");
            assertEqual(1.0, Calc.evaluate("-2 + 3"), 0.0001, "testAddition2");
            assertEqual(-1.0, Calc.evaluate("2 + (-3)"), 0.0001, "testAddition3");
            assertEqual(-5.0, Calc.evaluate("-2 + (-3)"), 0.0001, "testAddition4");
        } catch (Exception e) {
            System.out.println("testAddition failed with exception: " + e.getMessage());
        }
    }

    private static void testSubtraction() {
        try {
            assertEqual(2.0, Calc.evaluate("5 - 3"), 0.0001, "testSubtraction1");
            assertEqual(8.0, Calc.evaluate("5 - (-3)"), 0.0001, "testSubtraction2");
            assertEqual(-8.0, Calc.evaluate("-5 - 3"), 0.0001, "testSubtraction3");
        } catch (Exception e) {
            System.out.println("testSubtraction failed with exception: " + e.getMessage());
        }
    }

    private static void testMultiplication() {
        try {
            assertEqual(15.0, Calc.evaluate("5 * 3"), 0.0001, "testMultiplication1");
            assertEqual(-15.0, Calc.evaluate("-5 * 3"), 0.0001, "testMultiplication2");
            assertEqual(-15.0, Calc.evaluate("5 * (-3)"), 0.0001, "testMultiplication3");
            assertEqual(15.0, Calc.evaluate("-5 * (-3)"), 0.0001, "testMultiplication4");
            assertEqual(1.5, Calc.evaluate("5 * 0.3"), 0.0001, "testMultiplication5");
            assertEqual(-1.5, Calc.evaluate("5 * (-0.3)"), 0.0001, "testMultiplication6");
        } catch (Exception e) {
            System.out.println("testMultiplication failed with exception: " + e.getMessage());
        }
    }

    private static void testDivision() {
        try {
            assertEqual(2.0, Calc.evaluate("6 / 3"), 0.0001, "testDivision1");
            assertEqual(-2.0, Calc.evaluate("6 / (-3)"), 0.0001, "testDivision2");
            assertEqual(-2.0, Calc.evaluate("-6 / 3"), 0.0001, "testDivision3");
            assertEqual(2.0, Calc.evaluate("-6 / (-3)"), 0.0001, "testDivision4");
        } catch (Exception e) {
            System.out.println("testDivision failed with exception: " + e.getMessage());
        }
    }

    private static void testOrderOfOperations() {
        try {
            assertEqual(15.0, Calc.evaluate("2 + 3 * 5 - 4 / 2"), 0.0001, "testOrderOfOperations5");
            assertEqual(18.0, Calc.evaluate("3 + 5 * (2 + 1)"), 0.0001, "testOrderOfOperations6");
        } catch (Exception e) {
            System.out.println("testOrderOfOperations failed with exception: " + e.getMessage());
        }
    }

    private static void testParentheses() {
        try {
            assertEqual(9.0, Calc.evaluate("(2 + 3) * 2 - 1"), 0.0001, "testParentheses");
            assertEqual(3.0, Calc.evaluate("(4 - 2) * (1 + 1) - 1"), 0.0001, "testParentheses");
        } catch (Exception e) {
            System.out.println("testParentheses failed with exception: " + e.getMessage());
        }
    }

    private static void testComplexExpression() {
        try {
            assertEqual(11.0, Calc.evaluate("3 + (2 * 5) - (4 / 2)"), 0.0001, "testComplexExpression");
        } catch (Exception e) {
            System.out.println("testComplexExpression failed with exception: " + e.getMessage());
        }
    }

    private static void testDivisionByZero() {
        try {
            Calc.evaluate("5 / 0");
            System.out.println("testDivisionByZero failed: exception not thrown.");
        } catch (Exception e) {
            System.out.println("testDivisionByZero passed with expected exception: " + e.getMessage());
        }
    }

    private static void testInvalidExpression() {
        try {
            Calc.evaluate("2 + (3 * (4 - 1");
            System.out.println("testInvalidExpression failed: exception not thrown.");
        } catch (Exception e) {
            System.out.println("testInvalidExpression passed with expected exception: " + e.getMessage());
        }
    }

    private static void testEmptyInput() {
        try {
            Calc.evaluate("");
            System.out.println("testEmptyInput failed: exception not thrown.");
        } catch (Exception e) {
            System.out.println("testEmptyInput passed with expected exception: " + e.getMessage());
        }
    }
}
