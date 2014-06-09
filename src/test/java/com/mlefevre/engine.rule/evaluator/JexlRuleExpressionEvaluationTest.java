package com.mlefevre.engine.rule.evaluator;

import com.mlefevre.engine.rule.RuleEngineInputData;
import com.mlefevre.engine.rule.dataset.data.RuleDataYamlSerializer;
import com.mlefevre.engine.rule.dataset.expressions.Expressions;
import com.mlefevre.engine.rule.dataset.expressions.ExpressionsYamlSerializer;
import com.mlefevre.engine.rule.exception.EvaluationException;
import com.mlefevre.engine.rule.model.RuleResultExpression;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.fail;

public class JexlRuleExpressionEvaluationTest {

    private JexlEvaluator evaluator;

    private Expressions expressions;
    private RuleEngineInputData data;

    @Before
    public void setUp() {
        this.evaluator = new JexlEvaluator();

        ExpressionsYamlSerializer expressionsSerializer = new ExpressionsYamlSerializer();
        this.expressions = expressionsSerializer.getExpressions();

        RuleDataYamlSerializer dataSerializer = new RuleDataYamlSerializer();
        this.data = dataSerializer.getRuleData();
    }


    @Test
    public void evalRuleExpressionTest_StringSuccess() {
        RuleResultExpression expression = this.expressions.getExpression("expression1");
        assertNotNull(expression);

        try {
            Object result = this.evaluator.evalRuleExpression(expression, data);
            assertEquals("expression1", (String) result);

        } catch(EvaluationException e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    public void evalRuleExpressionTest_NumberSuccess() {
        RuleResultExpression expression = this.expressions.getExpression("expression2");
        assertNotNull(expression);

        try {
            Integer result = (Integer) this.evaluator.evalRuleExpression(expression, data);
            assertEquals(100, (int) result);

        } catch(EvaluationException e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    public void evalRuleExpressionTest_SeveralNumberVariablesSuccess() {
        RuleResultExpression expression = this.expressions.getExpression("expression3");
        assertNotNull(expression);

        try {
            Integer result = (Integer) this.evaluator.evalRuleExpression(expression, data);
            assertEquals(1, (int) result);

        } catch(EvaluationException e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test(expected = EvaluationException.class)
    public void evalRuleExpressionTest_NullRuleResultExpression() throws EvaluationException {
        this.evaluator.evalRuleExpression(null, data);
    }

    @Test(expected = EvaluationException.class)
    public void evalRuleExpressionTest_NullExpression() throws EvaluationException {
        RuleResultExpression expression = this.expressions.getExpression("expression4");
        assertNotNull(expression);
        this.evaluator.evalRuleExpression(expression, data);
    }

    @Test(expected = EvaluationException.class)
    public void evalRuleExpressionTest_WrongVariable() throws EvaluationException {
        RuleResultExpression expression = this.expressions.getExpression("expression5");
        assertNotNull(expression);
        this.evaluator.evalRuleExpression(expression, data);
    }

    @Test(expected = EvaluationException.class)
    public void evalRuleExpressionTest_NoVariableToEvaluate() throws EvaluationException {
        RuleResultExpression expression = this.expressions.getExpression("expression6");
        assertNotNull(expression);
        this.evaluator.evalRuleExpression(expression, data);
    }


}
