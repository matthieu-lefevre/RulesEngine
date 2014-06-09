package com.mlefevre.engine.rule.model;

import java.util.ArrayList;
import java.util.List;

public class RuleResult {

    protected List<RuleResultExpression> expressions = new ArrayList<RuleResultExpression>();


    public RuleResult() { }


    public List<RuleResultExpression> getExpressions() {
        return expressions;
    }

    public void setExpressions(List<RuleResultExpression> expressions) {
        this.expressions = expressions;
    }

}
