package ru.nsu.Sirotkin.task1.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.nsu.Sirotkin.task1.context.Context;
import ru.nsu.Sirotkin.task1.context.ContextImplementation;
import ru.nsu.Sirotkin.task1.exceptions.BaseException;
import ru.nsu.Sirotkin.task1.exceptions.OperationException;
import ru.nsu.Sirotkin.task1.factory.Operation;
import ru.nsu.Sirotkin.task1.factory.OperationsFactory;
import ru.nsu.Sirotkin.task1.operations.ParameterSolver;


public class Tests {
    @Test
    void contextStackTest() {
        Context context = new ContextImplementation();
        context.pushToStack(1.2);
        Assertions.assertEquals(1.2, context.getFromStack());
        Assertions.assertNull(context.getFromStack());

        context.pushToStack(1.3);
        context.pushToStack(1.0);
        Assertions.assertEquals(1.0, context.getFromStack());

        context.pushToStack(1.0);
        Assertions.assertEquals(1.0, context.peekFromStack());
        Assertions.assertEquals(1.0, context.getFromStack());
    }

    @Test
    void contextDefineTest(){
        Context context = new ContextImplementation();
        context.setDefine("1", 1.0);
        Assertions.assertEquals(1.0, context.getDefine("1"));
        Assertions.assertNull(context.getDefine("2"));
        context.setDefine("1", 2.0);
        Assertions.assertEquals(2.0, context.getDefine("1"));
    }

    @Test
    void factoryTest() throws BaseException {
        OperationsFactory factory = new OperationsFactory();
        Operation operation = factory.getOperation("+");
        Assertions.assertEquals("operation Plus", operation.name());
        operation = factory.getOperation("-");
        Assertions.assertEquals("operation Minus", operation.name());
        operation = factory.getOperation("*");
        Assertions.assertEquals("operation Multiply", operation.name());
        operation = factory.getOperation("/");
        Assertions.assertEquals("operation Division", operation.name());
        operation = factory.getOperation("SQRT");
        Assertions.assertEquals("operation SQRT", operation.name());
        operation = factory.getOperation("POP");
        Assertions.assertEquals("operation Pop", operation.name());
        operation = factory.getOperation("PUSH");
        Assertions.assertEquals("operation Push", operation.name());
        operation = factory.getOperation("DEFINE");
        Assertions.assertEquals("operation Define", operation.name());
        operation = factory.getOperation("PRINT");
        Assertions.assertEquals("operation Print", operation.name());
        Context context = new ContextImplementation();
        context.pushToStack(1.3);
        context.pushToStack(1.0);
        operation = factory.getOperation("+");
        operation.performOperation(context,new String[0]);
        String[] operands = operation.lastOperands();
        operation = factory.getOperation("+");
        Assertions.assertEquals(operands[0], operation.lastOperands()[0]);
        Assertions.assertEquals(operands[1], operation.lastOperands()[1]);
    }


    @Test
    void parameterSolverTest() throws BaseException {
        Context context = new ContextImplementation();
        context.pushToStack(1.3);
        context.pushToStack(1.0);
        context.setDefine("A", 20.0);
        context.setDefine("B", 30.0);

        Double[] operands1 = new Double[1];
        context.pushToStack(1.0);
        ParameterSolver.solveParameters(context, new String[0], operands1);
        Assertions.assertEquals(1.0, operands1[0]);

        ParameterSolver.solveParameters(context, new String[]{"A"}, operands1);
        Assertions.assertEquals(20.0, operands1[0]);

        ParameterSolver.solveParameters(context, new String[]{"123"}, operands1);
        Assertions.assertEquals(123.0, operands1[0]);

        Double[] operands = new Double[2];

        ParameterSolver.solveParameters(context, new String[0], operands);
        Assertions.assertEquals(1.0, operands[0]);
        Assertions.assertEquals(1.3, operands[1]);

        context.pushToStack(1.0);
        ParameterSolver.solveParameters(context, new String[]{"123"}, operands);
        Assertions.assertEquals(123.0, operands[0]);
        Assertions.assertEquals(1.0, operands[1]);

        ParameterSolver.solveParameters(context, new String[]{"123", "12"}, operands);
        Assertions.assertEquals(123.0, operands[0]);
        Assertions.assertEquals(12.0, operands[1]);

        ParameterSolver.solveParameters(context, new String[]{"A", "B"}, operands);
        Assertions.assertEquals(20.0, operands[0]);
        Assertions.assertEquals(30.0, operands[1]);

        ParameterSolver.solveParameters(context, new String[]{"A", "123"}, operands);
        Assertions.assertEquals(20.0, operands[0]);
        Assertions.assertEquals(123.0, operands[1]);

        ParameterSolver.solveParameters(context, new String[]{"123", "B"}, operands);
        Assertions.assertEquals(123.0, operands[0]);
        Assertions.assertEquals(30.0, operands[1]);

        context.pushToStack(1.3);
        ParameterSolver.solveParameters(context, new String[]{"B"}, operands);
        Assertions.assertEquals(30.0, operands[0]);
        Assertions.assertEquals(1.3, operands[1]);

        Exception e = Assertions.assertThrowsExactly(OperationException.class, () -> ParameterSolver.solveParameters(context, new String[]{"vbi"}, operands1));
        Assertions.assertEquals("no such define: vbi", e.getMessage());

        Exception e1 = Assertions.assertThrowsExactly(OperationException.class, () -> ParameterSolver.solveParameters(context, new String[]{"123" , "vbi"}, operands));
        Assertions.assertEquals("no such define: vbi", e1.getMessage());

        Exception e3 = Assertions.assertThrowsExactly(OperationException.class, () -> ParameterSolver.solveParameters(context, new String[0], operands1));
        Assertions.assertEquals("stack is empty during parsing argument1", e3.getMessage());

        Exception e4 = Assertions.assertThrowsExactly(OperationException.class, () -> ParameterSolver.solveParameters(context, new String[]{"123"}, operands));
        Assertions.assertEquals("stack is empty during parsing argument2", e4.getMessage());

        Exception e5 = Assertions.assertThrowsExactly(OperationException.class, () -> ParameterSolver.solveParameters(context, new String[]{"123", "234", "12345"}, operands));
        Assertions.assertEquals("invalid number of arguments", e5.getMessage());
    }

    
    @Test
    void operationPlusTest() throws BaseException {
        OperationsFactory factory = new OperationsFactory();
        Operation operation = factory.getOperation("+");
        Context context = new ContextImplementation();
        context.pushToStack(1.3);
        context.pushToStack(1.0);
        operation.performOperation(context, new String[0]);
        Assertions.assertEquals(1.0 + 1.3, context.peekFromStack());
        String[] operand1 = new String[1];
        operand1[0] = "1.0";
        operation.performOperation(context, operand1);
        Assertions.assertEquals(1.0 + 1.3 + 1.0, context.peekFromStack());
        String[] operand2 = new String[2];
        operand2[0] = "1.0";
        operand2[1] = "100.0";
        operation.performOperation(context, operand2);
        Assertions.assertEquals(1.0 + 100.0, context.peekFromStack());
    }


    @Test
    void operationMinusTest() throws BaseException {
        OperationsFactory factory = new OperationsFactory();
        Operation operation = factory.getOperation("-");
        Context context = new ContextImplementation();
        context.pushToStack(1.3);
        context.pushToStack(1.0);
        operation.performOperation(context, new String[0]);
        Assertions.assertEquals(1.0 - 1.3, context.peekFromStack());
        String[] operand1 = new String[1];
        operand1[0] = "1.0";
        operation.performOperation(context, operand1);
        Assertions.assertEquals(1.0 - (1.0 - 1.3), context.peekFromStack());
        String[] operand2 = new String[2];
        operand2[0] = "1.0";
        operand2[1] = "100.0";
        operation.performOperation(context, operand2);
        Assertions.assertEquals(1.0 - 100.0, context.peekFromStack());
    }


    @Test
    void operationDivisionTest()throws BaseException {
        OperationsFactory factory = new OperationsFactory();
        Operation operation = factory.getOperation("/");
        Context context = new ContextImplementation();
        context.pushToStack(1.3);
        context.pushToStack(1.0);
        operation.performOperation(context, new String[0]);
        Assertions.assertEquals(1.0 / 1.3, context.peekFromStack());
        String[] operand1 = new String[1];
        operand1[0] = "1.0";
        operation.performOperation(context, operand1);
        Assertions.assertEquals(1.0 / (1.0 / 1.3), context.peekFromStack());
        String[] operand2 = new String[2];
        operand2[0] = "1.0";
        operand2[1] = "100.0";
        operation.performOperation(context, operand2);
        Assertions.assertEquals(1.0 / 100.0, context.peekFromStack());
    }


    @Test
    void operationMultiplyTest() throws BaseException {
        OperationsFactory factory = new OperationsFactory();
        Operation operation = factory.getOperation("*");
        Context context = new ContextImplementation();
        context.pushToStack(1.3);
        context.pushToStack(1.0);
        operation.performOperation(context, new String[0]);
        Assertions.assertEquals(1.3, context.peekFromStack());
        String[] operand1 = new String[1];
        operand1[0] = "1.0";
        operation.performOperation(context, operand1);
        Assertions.assertEquals((1.3), context.peekFromStack());
        String[] operand2 = new String[2];
        operand2[0] = "1.0";
        operand2[1] = "100.0";
        operation.performOperation(context, operand2);
        Assertions.assertEquals(100.0, context.peekFromStack());
    }


    @Test
    void operationSQRTTest() throws BaseException {
        OperationsFactory factory = new OperationsFactory();
        Operation operation = factory.getOperation("SQRT");
        Context context = new ContextImplementation();
        context.pushToStack(4.0);
        operation.performOperation(context, new String[0]);
        Assertions.assertEquals(2.0, context.peekFromStack());
    }


    @Test
    void operationDefineTest() throws BaseException {
        OperationsFactory factory = new OperationsFactory();
        Operation operation = factory.getOperation("DEFINE");
        Context context = new ContextImplementation();

        operation.performOperation(context, new String[]{"A", "123"});
        Assertions.assertEquals(123.0, context.getDefine("A"));

        Exception e = Assertions.assertThrowsExactly(OperationException.class, () -> operation.performOperation(context, new String[]{"12", "12"}));
        Assertions.assertEquals("invalid define name: 12", e.getMessage());

        e = Assertions.assertThrowsExactly(OperationException.class, () -> operation.performOperation(context, new String[]{"A", "ewrgh"}));
        Assertions.assertEquals("invalid define value: ewrgh", e.getMessage());

        e = Assertions.assertThrowsExactly(OperationException.class, () -> operation.performOperation(context, new String[]{"A", "1", "123"}));
        Assertions.assertEquals("invalid number of arguments", e.getMessage());
    }


    @Test
    void operationPopTest() throws BaseException {
        OperationsFactory factory = new OperationsFactory();
        Operation operation = factory.getOperation("POP");
        Context context = new ContextImplementation();
        context.pushToStack(1.0);
        context.pushToStack(2.0);

        operation.performOperation(context, new String[0]);
        Assertions.assertEquals(1.0, context.peekFromStack());

        Exception e = Assertions.assertThrowsExactly(OperationException.class, () -> operation.performOperation(context, new String[]{"12345t4bvef"}));
        Assertions.assertEquals("POP does not require operands", e.getMessage());

        context.getFromStack();
        e = Assertions.assertThrowsExactly(OperationException.class, () -> operation.performOperation(context, new String[0]));
        Assertions.assertEquals("stack is empty", e.getMessage());
    }


    @Test
    void operationPrintTest() throws BaseException{
        OperationsFactory factory = new OperationsFactory();
        Operation operation = factory.getOperation("PRINT");
        Context context = new ContextImplementation();
        context.pushToStack(2.0);

        operation.performOperation(context, new String[0]);
        String[] resultLineSplit = operation.lastResult().split(" ");
        Assertions.assertEquals("2.0", resultLineSplit[0]);

        Exception e = Assertions.assertThrowsExactly(OperationException.class, () -> operation.performOperation(context, new String[]{"12345t4bvef"}));
        Assertions.assertEquals("PRINT does not require operands", e.getMessage());

        context.getFromStack();
        e = Assertions.assertThrowsExactly(OperationException.class, () -> operation.performOperation(context, new String[0]));
        Assertions.assertEquals("empty stack", e.getMessage());
    }


    @Test
    void operationPushTest() throws BaseException {
        OperationsFactory factory = new OperationsFactory();
        Operation operation = factory.getOperation("PUSH");
        Context context = new ContextImplementation();

        operation.performOperation(context, new String[]{"12"});
        Assertions.assertEquals(12.0, context.peekFromStack());

       Exception e = Assertions.assertThrowsExactly(OperationException.class, () -> operation.performOperation(context, new String[0]));
       Assertions.assertEquals("invalid number of arguments", e.getMessage());
       }
}
