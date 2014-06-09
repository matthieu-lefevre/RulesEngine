package com.mlefevre.engine.rule;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.mlefevre.engine.rule.dataset.rules.Rules;
import com.mlefevre.engine.rule.dataset.rules.RulesYamlSerializer;
import com.mlefevre.engine.rule.dataset.data.RuleDataYamlSerializer;
import com.mlefevre.engine.rule.evaluator.JexlEvaluator;
import com.mlefevre.engine.rule.exception.RuleEngineException;
import com.mlefevre.engine.rule.model.Rule;

public class RuleEngineHandlerTest {

    private RuleEngineHandler handler;

    private Rules rules;
    private RuleEngineInputData data;

    @Before
    public void setUp() {
        JexlEvaluator jexl = new JexlEvaluator();
        handler = new RuleEngineHandler(jexl);

        RulesYamlSerializer rulesSerializer = new RulesYamlSerializer();
        rules = rulesSerializer.getRules();

        RuleDataYamlSerializer dataSerializer = new RuleDataYamlSerializer();
        data = dataSerializer.getRuleData();
    }


    // compute a lonely rule
    @Test
    public void computeTest_OneRule() {
        Rule rule = rules.getRule("rule1");
        assertNotNull(rule);

        try {
            Object value = handler.compute(rule, data);
            assertEquals(100, value);

        } catch (RuleEngineException e) {
            e.printStackTrace();
            fail();
        }
    }

    // compute value of two rules with the same priority
    // the first rule to satisfy the condition is computed
    @Test
    public void computeTest_TwoRulesSamePriority() {
        List<Rule> rulesList = rules.getRules(Arrays.asList("rule2", "rule11"));
        assertFalse(rulesList.isEmpty());

        try {
            Object value = handler.compute(rulesList, data);
            assertEquals(2, value);

        } catch(RuleEngineException e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    public void computeTest_TwoRulesSamePriority2() {
        List<Rule> rulesList = rules.getRules(Arrays.asList("rule11", "rule2"));
        assertFalse(rulesList.isEmpty());

        try {
            Object value = handler.compute(rulesList, data);
            assertEquals(3, value);

        } catch(RuleEngineException e) {
            e.printStackTrace();
            fail();
        }
    }

    // compute value of two rules with different priorities
    @Test
    public void computeTest_TwoRulesWithDifferentPriorities() {
        List<Rule> rulesList = rules.getRules(Arrays.asList("rule1", "rule2"));
        assertFalse(rulesList.isEmpty());

        try {
            Object value = handler.compute(rulesList, data);
            assertEquals(2, value);

        } catch(RuleEngineException e) {
            e.printStackTrace();
            fail();
        }
    }

    // compute a lonely rule with no condition
    @Test
    public void computeTest_NoCondition() {
        Rule rule = rules.getRule("rule3");
        assertNotNull(rule);

        try {
            Object value = handler.compute(rule, data);
            assertNotNull(value);
            assertEquals(5, value);

        } catch(RuleEngineException e) {
            e.printStackTrace();
            fail();
        }
    }

    // compute a lonely rule with wrong condition
    @Test
    public void computeTest_WrongCondition() {
        Rule rule = rules.getRule("rule4");
        assertNotNull(rule);

        try {
            Object value = handler.compute(rule, data);
            assertNull(value);

        } catch(RuleEngineException e) {
            e.printStackTrace();
            fail();
        }
    }

    // compute a lonely rule with several ordered expressions
    @Test
    public void computeTest_SeveralOrderedExpressions() {
        Rule rule = rules.getRule("rule5");
        assertNotNull(rule);

        try {
            Object value = handler.compute(rule, data);
            assertNotNull(value);
            assertEquals("test100", value);

        } catch(RuleEngineException e) {
            e.printStackTrace();
            fail();
        }
    }

    // compute a lonely rule without ordered expressions
    @Test
    public void computeTest_SeveralNotOrderedExpressions() {
        Rule rule = rules.getRule("rule6");
        assertNotNull(rule);

        try {
            Object value = handler.compute(rule, data);
            assertNotNull(value);
            assertEquals("100test", value);

        } catch(RuleEngineException e) {
            e.printStackTrace();
            fail();
        }
    }

    // compute a lonely rule with parsing result expression rather than evaluating it
    @Test
    public void computeTest_ParseResultExpression() {
        Rule rule = rules.getRule("rule7");
        assertNotNull(rule);

        try {
            Object value = handler.compute(rule, data);
            assertNotNull(value);
            assertEquals("1 petit test", value);

        } catch(RuleEngineException e) {
            e.printStackTrace();
            fail();
        }
    }

    // compute a lonely rule with no variables and no evaluation
    @Test
    public void computeTest_NoVariablesAndNoEvaluation() {
        Rule rule = rules.getRule("rule8");
        assertNotNull(rule);

        try {
            Object value = handler.compute(rule, data);
            assertNotNull(value);
            assertEquals("expression", value);

        } catch(RuleEngineException e) {
            e.printStackTrace();
            fail();
        }
    }

    // compute a lonely rule with no variables and evaluation
    @Test(expected = RuleEngineException.class)
    public void computeTest_NoVariablesAndEvaluation() throws RuleEngineException {
        Rule rule = rules.getRule("rule9");
        assertNotNull(rule);

        handler.compute(rule, data);
    }

    // compute a lonely rule with a wrong variable
    @Test(expected = RuleEngineException.class)
    public void computeTest_WrongVariable() throws RuleEngineException {
        Rule rule = rules.getRule("rule10");
        assertNotNull(rule);

        handler.compute(rule, data);
    }

}
