package com.mlefevre.engine.rule.dataset.rules;

import java.io.File;

import com.mlefevre.engine.rule.dataset.YamlSerializationException;
import com.mlefevre.engine.rule.dataset.YamlSerializer;

public class RulesYamlSerializer extends YamlSerializer<Rules> {

    private Rules rules;

    public RulesYamlSerializer() {
        try {
            RulesYamlConstructor constructor = new RulesYamlConstructor();
            this.rules = this.readYamlFile(constructor);

        } catch (YamlSerializationException e) {}
    }


    public Rules getRules() {
        return this.rules;
    }


    @Override
    protected File getFile() {
        return new File("src/test/resources/dataset/rules.yaml");
    }

}
