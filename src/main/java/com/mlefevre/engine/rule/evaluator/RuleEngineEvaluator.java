package com.mlefevre.engine.rule.evaluator;

import com.mlefevre.engine.rule.RuleEngineInputData;
import com.mlefevre.engine.rule.exception.EvaluationException;
import com.mlefevre.engine.rule.exception.ParseException;
import com.mlefevre.engine.rule.model.RuleCondition;
import com.mlefevre.engine.rule.model.RuleResult;
import com.mlefevre.engine.rule.model.RuleResultExpression;

/**
 * Define a rule engine evaluator contract
 *
 * @author Matthieu
 */
public interface RuleEngineEvaluator {

    /**
     * Evaluate the condition to true or false.
     * <br/>
     * if no condition given, it is evaluated to true
     *
     * @param condition (RuleCondition)
     * @param data
     * @return boolean
     * @throws EvaluationException
     */
    boolean evalCondition(RuleCondition condition, RuleEngineInputData data) throws EvaluationException;

    /**
     * Evaluate the rule according to all expressions
     *
     * @param result (RuleResult)
     * @param data
     * @return the rule evaluation
     * @throws EvaluationException
     */
    String evalRule(RuleResult result, RuleEngineInputData data) throws EvaluationException, ParseException;


    Object evalRuleExpression(RuleResultExpression expression, RuleEngineInputData data) throws EvaluationException;

    String parseRuleExpression(RuleResultExpression expression, RuleEngineInputData data) throws EvaluationException, ParseException;

}