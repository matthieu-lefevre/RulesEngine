package com.mlefevre.engine.rule.evaluator;


import com.mlefevre.engine.rule.RuleEngineInputData;
import com.mlefevre.engine.rule.dataset.data.RuleDataYamlSerializer;
import com.mlefevre.engine.rule.dataset.expressions.Expressions;
import com.mlefevre.engine.rule.dataset.expressions.ExpressionsYamlSerializer;
import com.mlefevre.engine.rule.exception.EvaluationException;
import com.mlefevre.engine.rule.exception.ParseException;
import com.mlefevre.engine.rule.model.RuleResultExpression;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.fail;

public class JexlRuleExpressionParsingTest {

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
    public void parseRuleExpressionTest_Success() {
        RuleResultExpression expression = this.expressions.getExpression("expression7");
        assertNotNull(expression);

        try {
            String result = this.evaluator.parseRuleExpression(expression, data);
            assertEquals("10 expressions", result);

        } catch(ParseException e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test(expected = ParseException.class)
    public void parseRuleExpressionTest_NoExpressionToParse() throws ParseException {
        this.evaluator.parseRuleExpression(null, data);
    }

    @Test
    public void parseRuleExpressionTest_NoVariablesToParse() {
        RuleResultExpression expression = this.expressions.getExpression("expression8");
        assertNotNull(expression);

        try {
            String result = this.evaluator.parseRuleExpression(expression, data);
            assertEquals("expression", result);

        } catch(ParseException e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    public void parseRuleExpressionTest_SeveralParsedVariables() {
        RuleResultExpression expression = this.expressions.getExpression("expression9");
        assertNotNull(expression);

        try {
            String result = this.evaluator.parseRuleExpression(expression, data);
            assertEquals("first 10 then 9", result);

        } catch(ParseException e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test(expected = ParseException.class)
    public void parseRuleExpressionTest_NoMatchingVariable() throws ParseException {
        RuleResultExpression expression = this.expressions.getExpression("expression10");
        assertNotNull(expression);
        this.evaluator.parseRuleExpression(expression, data);
    }

}
