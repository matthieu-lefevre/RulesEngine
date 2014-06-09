package com.mlefevre.engine.rule.dataset.expressions;

import com.mlefevre.engine.rule.model.RuleResultExpression;

import java.util.HashMap;
import java.util.Map;

public class Expressions {

    Map<String, RuleResultExpression> expressions = new HashMap<String, RuleResultExpression>();


    public Expressions() { }


    public RuleResultExpression getExpression(String name) {
        RuleResultExpression expression = null;
        if(this.expressions.containsKey(name)) {
            expression = this.expressions.get(name);
        }
        return expression;
    }

    public Map<String, RuleResultExpression> getExpressions() {
        return expressions;
    }

    public void setExpressions(Map<String, RuleResultExpression> expressions) {
        this.expressions = expressions;
    }
}
