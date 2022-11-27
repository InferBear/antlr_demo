import code.bear.MyVisitor;
import code.bear.gen.CalculatorLexer;
import code.bear.gen.CalculatorParser;
import org.antlr.v4.runtime.*;

import java.math.BigInteger;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        while (in.hasNext()) {
            String cal = in.next();
            CharStream charStream = CharStreams.fromString(cal);
            CalculatorLexer lexer = new CalculatorLexer(charStream);
            TokenStream tokens = new CommonTokenStream(lexer);
            CalculatorParser parser = new CalculatorParser(tokens);
            MyVisitor visitor = new MyVisitor();
            BigInteger result = visitor.visit(parser.calc());
            System.out.println(result);
        }

    }
}