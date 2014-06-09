package com.mlefevre.engine.rule.evaluator;

import com.mlefevre.engine.rule.RuleEngineInputData;
import com.mlefevre.engine.rule.dataset.conditions.Conditions;
import com.mlefevre.engine.rule.dataset.conditions.ConditionsYamlSerializer;
import com.mlefevre.engine.rule.dataset.data.RuleDataYamlSerializer;
import com.mlefevre.engine.rule.dataset.expressions.Expressions;
import com.mlefevre.engine.rule.dataset.expressions.ExpressionsYamlSerializer;
import com.mlefevre.engine.rule.exception.EvaluationException;
import com.mlefevre.engine.rule.model.RuleCondition;
import com.mlefevre.engine.rule.model.RuleResultExpression;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.*;

public class JexlEvaluatorTest {

    private JexlEvaluator evaluator;

    private RuleEngineInputData data;

    @Before
    public void setUp() {
        this.evaluator = new JexlEvaluator();


        RuleDataYamlSerializer dataSerializer = new RuleDataYamlSerializer();
        this.data = dataSerializer.getRuleData();
    }





















}
