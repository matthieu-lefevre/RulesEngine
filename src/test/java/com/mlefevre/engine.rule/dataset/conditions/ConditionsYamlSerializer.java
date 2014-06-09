package com.mlefevre.engine.rule.dataset.conditions;

import com.mlefevre.engine.rule.dataset.YamlSerializationException;
import com.mlefevre.engine.rule.dataset.YamlSerializer;

import java.io.File;


public class ConditionsYamlSerializer extends YamlSerializer<Conditions> {

    private Conditions conditions;


    public ConditionsYamlSerializer() {
        try {
            ConditionsYamlConstructor constructor = new ConditionsYamlConstructor();
            this.conditions = this.readYamlFile(constructor);

        } catch(YamlSerializationException e) {

        }
    }

    public Conditions getConditions() {
        return this.conditions;
    }

    @Override
    protected File getFile() {
        return new File("src/test/resources/dataset/conditions.yaml");
    }
}
