package guu;

import org.junit.Test;

import static org.junit.Assert.*;

public class ExecutorTest {

    @Test
    public void checkStart() {
        Context context = new Context();
        Executor executor = new Executor(context);

        //Проверка выполнения корректного кода
        String code = "sub main\n" +
                "set x 10\n" +
                "print x";
        executor.start(code);
        Integer expectedVarValue = 10;
        Integer actualVarValue = context.getVarValue("x");
        assertEquals("Значение переменной вычисляется не корректно", expectedVarValue, actualVarValue);

        actualVarValue = context.getVarValue("y");
        assertTrue("Значение неопределенной должно отсутствовать в списке переменных", actualVarValue == null);

        //Проверка выполнения не корректного кода
        code = "sub func\n" +
                "set x 10\n" +
                "print x";
        executor.start(code);
        boolean errFlag = context.getErrFlag();
        assertTrue("Интерпретатор пропускает ошибку отсутствия функции main", errFlag);

        code = "sub main\n" +
                "seet x 10\n" +
                "print x";
        executor.start(code);
        errFlag = context.getErrFlag();
        assertTrue("Интерпретатор пропускает ошибку неправильного написания команды", errFlag);

        code = "sub main\n" +
                "set x 10\n" +
                "print y";
        executor.start(code);
        errFlag = context.getErrFlag();
        assertTrue("Интерпретатор пропускает ошибку попытки вывести значение не определенной переменной", errFlag);

        code = "sub main\n" +
                "call main";
        executor.start(code);
        errFlag = context.getErrFlag();
        assertTrue("Интерпретатор пропускает ошибку переполнения стека вызовов процедур", errFlag);
    }
}