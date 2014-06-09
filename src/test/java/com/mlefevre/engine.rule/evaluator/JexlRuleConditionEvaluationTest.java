package com.mlefevre.engine.rule.evaluator;

import com.mlefevre.engine.rule.RuleEngineInputData;
import com.mlefevre.engine.rule.dataset.conditions.Conditions;
import com.mlefevre.engine.rule.dataset.conditions.ConditionsYamlSerializer;
import com.mlefevre.engine.rule.dataset.data.RuleDataYamlSerializer;
import com.mlefevre.engine.rule.exception.EvaluationException;
import com.mlefevre.engine.rule.model.RuleCondition;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.*;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.fail;

public class JexlRuleConditionEvaluationTest {

    private JexlEvaluator evaluator;

    private Conditions conditions;
    private RuleEngineInputData data;

    @Before
    public void setUp() {
        this.evaluator = new JexlEvaluator();

        ConditionsYamlSerializer conditionsSerializer = new ConditionsYamlSerializer();
        this.conditions = conditionsSerializer.getConditions();

        RuleDataYamlSerializer dataSerializer = new RuleDataYamlSerializer();
        this.data = dataSerializer.getRuleData();
    }


    @Test
    public void evalCondition_Success() {
        RuleCondition condition = this.conditions.getCondition("condition1");
        assertNotNull(condition);

        try {
            boolean result = this.evaluator.evalCondition(condition, data);
            assertTrue(result);

        } catch (EvaluationException e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    public void evalCondition_FalseClause() {
        RuleCondition condition = this.conditions.getCondition("condition5");
        assertNotNull(condition);

        try {
            boolean result = this.evaluator.evalCondition(condition, data);
            assertFalse(result);

        } catch (EvaluationException e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test(expected = EvaluationException.class)
    public void evalCondition_NoVariables() throws EvaluationException {
        RuleCondition condition = this.conditions.getCondition("condition2");
        assertNotNull(condition);

        this.evaluator.evalCondition(condition, data);
    }

    @Test(expected = EvaluationException.class)
    public void evalCondition_WrongVariable() throws EvaluationException {
        RuleCondition condition = this.conditions.getCondition("condition3");
        assertNotNull(condition);

        this.evaluator.evalCondition(condition, data);
    }

    @Test(expected = EvaluationException.class)
    public void evalCondition_WrongClause() throws EvaluationException {
        RuleCondition condition = this.conditions.getCondition("condition4");
        assertNotNull(condition);

        this.evaluator.evalCondition(condition, data);
    }

    @Test(expected = EvaluationException.class)
    public void evalCondition_WrongDataTypeClause() throws EvaluationException {
        RuleCondition condition = this.conditions.getCondition("condition6");
        assertNotNull(condition);

        this.evaluator.evalCondition(condition, data);
    }

}
