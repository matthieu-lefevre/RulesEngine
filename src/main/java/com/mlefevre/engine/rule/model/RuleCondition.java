package com.mlefevre.engine.rule.model;

import java.util.ArrayList;
import java.util.List;

public class RuleCondition {

    protected List<String> variables = new ArrayList<String>();

    protected String clause;


    public RuleCondition() { }


    public List<String> getVariables() {
        return variables;
    }

    public void setVariables(List<String> variables) {
        this.variables = variables;
    }

    public String getClause() {
        return clause;
    }

    public void setClause(String clause) {
        this.clause = clause;
    }

}
