package com.mlefevre.engine.rule.model;

public class Rule implements Comparable<Rule> {

    protected Integer priority = 1;

    protected RuleCondition condition;

    protected RuleResult result;


    public Rule() { }


    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public RuleCondition getCondition() {
        return condition;
    }

    public void setCondition(RuleCondition condition) {
        this.condition = condition;
    }

    public RuleResult getResult() {
        return result;
    }

    public void setResult(RuleResult result) {
        this.result = result;
    }


    @Override
    public int compareTo(Rule o) {
        if(this.priority == null || o.getPriority() == null) {
            return 0;
        }
        return this.priority.compareTo(o.getPriority());
    }

}
