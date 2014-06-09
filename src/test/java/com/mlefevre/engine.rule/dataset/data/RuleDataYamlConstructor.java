package com.mlefevre.engine.rule.dataset.data;

import java.util.HashMap;

import org.yaml.snakeyaml.constructor.Constructor;
import org.yaml.snakeyaml.error.YAMLException;
import org.yaml.snakeyaml.nodes.Node;

import com.mlefevre.engine.rule.RuleEngineInputData;

public class RuleDataYamlConstructor extends Constructor {

    private HashMap<String, Class<?>> classMap = new HashMap<String, Class<?>>();

    public RuleDataYamlConstructor() {
        super(RuleEngineInputData.class);

        classMap.put(RuleEngineInputData.class.getName(), RuleEngineInputData.class);
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
