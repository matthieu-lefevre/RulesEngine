package com.mlefevre.engine.rule.model;

import java.util.ArrayList;
import java.util.List;

public class RuleResultExpression implements Comparable<RuleResultExpression> {

    protected String expression;

    protected Integer order = 1;

    protected boolean evaluate = true;

    protected List<String> variables = new ArrayList<String>();


    public RuleResultExpression() { }


    public String getExpression() {
        return expression;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public boolean isEvaluate() {
        return evaluate;
    }

    public void setEvaluate(boolean evaluate) {
        this.evaluate = evaluate;
    }

    public List<String> getVariables() {
        return variables;
    }

    public void setVariables(List<String> variables) {
        this.variables = variables;
    }


    @Override
    public int compareTo(RuleResultExpression o) {
        if(this.order == null || o.getOrder() == null) {
            return 0;
        }
        return this.order.compareTo(o.getOrder());
    }

}