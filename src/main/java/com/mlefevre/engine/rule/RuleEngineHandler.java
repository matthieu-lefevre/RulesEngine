package com.mlefevre.engine.rule;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import com.mlefevre.engine.rule.evaluator.RuleEngineEvaluator;
import com.mlefevre.engine.rule.exception.RuleEngineException;
import com.mlefevre.engine.rule.model.Rule;
import com.mlefevre.engine.rule.util.ObjectParser;


/**
 * Entry point of the rule engine
 *
 * @author Matthieu
 */
public class RuleEngineHandler {

    /**
     * Evaluator dependency
     */
    private RuleEngineEvaluator evaluator;

    /**
     * Define if rules execution must be stopped
     */
    private boolean stopRuleExecution = false;


    /**
     * CONSTRUCTOR
     *
     * @param evaluator (injection of evaluator dependency)
     */
    public RuleEngineHandler(RuleEngineEvaluator evaluator) {
        this.evaluator = evaluator;
    }

    /**
     * Retrieve the value of a list of rules evaluation
     * <br/><br/>
     * Execute the rules in order of priority while stop execution flag is set to false
     *
     * @param rules (list of Rule)
     * @param data
     * @return NULL|the evaluated value
     * @throws RuleEngineException
     */
    public Object compute(List<Rule> rules, RuleEngineInputData data) throws RuleEngineException {
        Object result = null;

        Collections.sort(rules);
        Iterator<Rule> it = rules.iterator();
        while(!stopRuleExecution && it.hasNext()) {
            result = this.compute(it.next(), data);
        }
        return result;
    }

    /**
     * Retrieve the value of the rule evaluation
     * <br/><br/>
     * Compute the rule according to condition evaluation.
     * Set stop execution flag if the condition of the rule if evaluated to true
     *
     * @param rule (Rule)
     * @param data
     * @return NULL|the rule evaluation
     * @throws RuleEngineException
     */
    public Object compute(Rule rule, RuleEngineInputData data) throws RuleEngineException {
        Object result = null;

        if(this.evaluator.evalCondition(rule.getCondition(), data)) {
            this.stopRuleExecution = true;
            result = ObjectParser.parseString(this.evaluator.evalRule(rule.getResult(), data));
        }
        return result;
    }

}