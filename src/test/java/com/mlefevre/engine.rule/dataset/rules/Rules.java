package com.mlefevre.engine.rule.dataset.rules;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mlefevre.engine.rule.model.Rule;

public class Rules {

    protected Map<String, Rule> rules = new HashMap<String, Rule>();


    public Rules() { }


    public Map<String, Rule> getRules() {
        return rules;
    }

    public void setRules(Map<String, Rule> rules) {
        this.rules = rules;
    }

    public void addRule(String name, Rule rule) {
        this.rules.put(name, rule);
    }

    public List<Rule> getRules(List<String> ruleNames) {
        List<Rule> rules = new ArrayList<Rule>();
        if(ruleNames != null) {
            for(String ruleName : ruleNames) {
                rules.add(this.getRule(ruleName));
            }
        }
        return rules;
    }

    public Rule getRule(String name) {
        Rule rule = null;

        if(this.rules.containsKey(name)) {
            rule = this.rules.get(name);
        }

        return rule;
    }

}
