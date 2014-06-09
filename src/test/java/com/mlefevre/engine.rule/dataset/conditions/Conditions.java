package com.mlefevre.engine.rule.dataset.conditions;


import com.mlefevre.engine.rule.model.RuleCondition;

import java.util.HashMap;
import java.util.Map;

public class Conditions {

    Map<String, RuleCondition> conditions = new HashMap<String, RuleCondition>();


    public Conditions() { }



    public RuleCondition getCondition(String name) {
        RuleCondition condition = null;
        if(this.conditions.containsKey(name)) {
            condition = this.conditions.get(name);
        }
        return condition;
    }

    public Map<String, RuleCondition> getConditions() {
        return conditions;
    }

    public void setConditions(Map<String, RuleCondition> conditions) {
        this.conditions = conditions;
    }
}
