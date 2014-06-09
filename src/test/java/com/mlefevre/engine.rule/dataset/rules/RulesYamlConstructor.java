package com.mlefevre.engine.rule.dataset.rules;

import java.util.HashMap;

import org.yaml.snakeyaml.constructor.Constructor;
import org.yaml.snakeyaml.error.YAMLException;
import org.yaml.snakeyaml.nodes.Node;

import com.mlefevre.engine.rule.model.Rule;
import com.mlefevre.engine.rule.model.RuleCondition;
import com.mlefevre.engine.rule.model.RuleResult;
import com.mlefevre.engine.rule.model.RuleResultExpression;

public class RulesYamlConstructor extends Constructor {

    private HashMap<String, Class<?>> classMap = new HashMap<String, Class<?>>();

    public RulesYamlConstructor() {
        super(Rules.class);

        classMap.put(Rules.class.getName(), Rules.class);
        classMap.put(Rule.class.getName(), Rule.class);
        classMap.put(RuleCondition.class.getName(), RuleCondition.class);
        classMap.put(RuleResult.class.getName(), RuleResult.class);
        classMap.put(RuleResultExpression.class.getName(), RuleResultExpression.class);
    }

    protected Class<?> getClassForNode(Node node) {
        String name = node.getTag().getClassName();
        Class<?> cl = classMap.get(name);
        if(cl == null) {
            throw new YAMLException("Class not found: " + name);
        }
        return cl;
    }

}
