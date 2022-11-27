package code.bear;

import code.bear.gen.CalculatorBaseVisitor;
import code.bear.gen.CalculatorParser;

import java.math.BigInteger;

public class MyVisitor extends CalculatorBaseVisitor<BigInteger> {


    @Override
    public BigInteger visitInfixExpr(CalculatorParser.InfixExprContext ctx) {
        super.visitInfixExpr(ctx);
        var left = ctx.expr(0);
        var right = ctx.expr(1);
        var leftV = left.accept(this);
        var rightV = right.accept(this);
        if (ctx.op.getText().equals("+")) {
            return leftV.add(rightV);
        } else if (ctx.op.getText().equals("-")) {
            return leftV.subtract(rightV);
        } else if (ctx.op.getText().equals("*")) {
            return leftV.multiply(rightV);
        } else if (ctx.op.getText().equals("/")) {
            return leftV.divide(rightV);
        } else {
            throw new RuntimeException("no op");
        }
    }

    @Override
    public BigInteger visitParensExpr(CalculatorParser.ParensExprContext ctx) {
        super.visitParensExpr(ctx);
        return ctx.expr().accept(this);
    }

    @Override
    public BigInteger visitNumber(CalculatorParser.NumberContext ctx) {
        super.visitNumber(ctx);
        return new BigInteger(ctx.getText());
    }

    @Override
    public BigInteger visitPlusOrMinusExpr(CalculatorParser.PlusOrMinusExprContext ctx) {
        super.visitPlusOrMinusExpr(ctx);
        var val = ctx.expr().accept(this);
        if (ctx.MINUS() != null) {
            return BigInteger.ZERO.subtract(val);
        } else {
            return val;
        }
    }
}
