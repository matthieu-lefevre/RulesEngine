package com.mlefevre.engine.rule.evaluator;

import java.util.Collections;
import java.util.List;

import org.apache.commons.jexl2.Expression;
import org.apache.commons.jexl2.JexlContext;
import org.apache.commons.jexl2.JexlEngine;
import org.apache.commons.jexl2.JexlException;
import org.apache.commons.jexl2.MapContext;

import com.mlefevre.engine.rule.util.ObjectParser;
import com.mlefevre.engine.rule.RuleEngineInputData;
import com.mlefevre.engine.rule.exception.EvaluationException;
import com.mlefevre.engine.rule.exception.ParseException;
import com.mlefevre.engine.rule.model.RuleCondition;
import com.mlefevre.engine.rule.model.RuleResult;
import com.mlefevre.engine.rule.model.RuleResultExpression;

/**
 *
 */
public class JexlEvaluator implements RuleEngineEvaluator {

    protected JexlEngine jexl;


    public JexlEvaluator() {
        JexlEngine jexl = new JexlEngine();
        jexl.setCache(512);
        jexl.setLenient(false);
        jexl.setSilent(false);

        this.jexl = jexl;
    }


    @Override
    public boolean evalCondition(RuleCondition condition, RuleEngineInputData data) throws EvaluationException {
        if(condition == null) {
            return true;
        }

        boolean result = false;

        try {
            JexlContext context = createContext(condition.getVariables(), data);
            Expression expression = this.jexl.createExpression(condition.getClause());
            Object eval = expression.evaluate(context);

            if(!(eval instanceof Boolean)) {
                throw new EvaluationException("The clause " + condition.getClause() + " cannot be evaluated as a condition.");
            }
            result = (Boolean) eval;

        } catch(JexlException e) {
            throw new EvaluationException(e);
        } catch(NumberFormatException e) {
            throw new EvaluationException(e);
        }
        return result;
    }

    @Override
    public String evalRule(RuleResult result, RuleEngineInputData data) throws EvaluationException, ParseException {
        if(result == null) {
            throw new EvaluationException("No rule result given");
        }

        StringBuilder strBuilder = new StringBuilder();

        List<RuleResultExpression> expressions = result.getExpressions();
        Collections.sort(expressions);
        for(RuleResultExpression expression : expressions) {
            if(expression.isEvaluate()) {
                strBuilder.append(this.evalRuleExpression(expression, data));
            } else {
                strBuilder.append(this.parseRuleExpression(expression, data));
            }
        }
        return strBuilder.toString();
    }

    @Override
    public Object evalRuleExpression(RuleResultExpression resultExpression, RuleEngineInputData data) throws EvaluationException {
        if(resultExpression == null) {
            throw new EvaluationException("No expression given.");
        }

        Object result = resultExpression.getExpression();
        try {
            JexlContext context = createContext(resultExpression.getVariables(), data);
            Expression expression = this.jexl.createExpression(resultExpression.getExpression());
            result = expression.evaluate(context);

        } catch(JexlException e) {
            throw new EvaluationException(e);
        } catch(NullPointerException e) {
            throw new EvaluationException("Expression to evaluate must not be null.");
        }

        return result;
    }

    @Override
    public String parseRuleExpression(RuleResultExpression resultExpression, RuleEngineInputData data) throws ParseException {
        if(resultExpression == null) {
            throw new ParseException("No expression given.");
        }

        String expression = resultExpression.getExpression();
        List<String> variables = resultExpression.getVariables();
        if(variables != null && data != null) {
            for(String variable : variables) {
                try {
                    String value = ObjectParser.toString(data.getElement(variable));
                    if(value == null) {
                        continue;
                    }
                    expression = expression.replaceAll(variable, value);

                } catch(IllegalArgumentException e) {
                    throw new ParseException(e);
                }
            }
        }
        return expression;
    }

    protected static JexlContext createContext(List<String> variables, RuleEngineInputData data) {
        JexlContext ctx = new MapContext();

        if(variables != null && data != null) {
            for(String variableName : variables) {
                Object variableValue = data.getElement(variableName);
                if(variableValue != null) {
                    ctx.set(variableName, variableValue);
                }
            }
        }
        return ctx;
    }

}