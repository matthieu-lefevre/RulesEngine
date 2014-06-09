package com.mlefevre.engine.rule.dataset.expressions;

import com.mlefevre.engine.rule.model.RuleResultExpression;
import org.yaml.snakeyaml.constructor.Constructor;
import org.yaml.snakeyaml.error.YAMLException;
import org.yaml.snakeyaml.nodes.Node;

import java.util.HashMap;

public class ExpressionsYamlConstructor extends Constructor {

    private HashMap<String, Class<?>> classMap = new HashMap<String, Class<?>>();

    public ExpressionsYamlConstructor() {
        super(Expressions.class);

        classMap.put(Expressions.class.getName(), Expressions.class);
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
