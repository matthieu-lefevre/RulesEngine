package com.mlefevre.engine.rule.dataset.conditions;

import com.mlefevre.engine.rule.model.RuleCondition;
import org.yaml.snakeyaml.constructor.Constructor;
import org.yaml.snakeyaml.error.YAMLException;
import org.yaml.snakeyaml.nodes.Node;

import java.util.HashMap;

public class ConditionsYamlConstructor extends Constructor {

    private HashMap<String, Class<?>> classMap = new HashMap<String, Class<?>>();

    public ConditionsYamlConstructor() {
        super(Conditions.class);

        classMap.put(Conditions.class.getName(), Conditions.class);
        classMap.put(RuleCondition.class.getName(), RuleCondition.class);
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
