package com.mlefevre.engine.rule.dataset.data;

import java.io.File;

import com.mlefevre.engine.rule.RuleEngineInputData;
import com.mlefevre.engine.rule.dataset.YamlSerializationException;
import com.mlefevre.engine.rule.dataset.YamlSerializer;

public class RuleDataYamlSerializer extends YamlSerializer<RuleEngineInputData> {

    private RuleEngineInputData data;

    public RuleDataYamlSerializer() {
        try {
            RuleDataYamlConstructor constructor = new RuleDataYamlConstructor();
            this.data = this.readYamlFile(constructor);

        } catch (YamlSerializationException e) {}
    }


    public RuleEngineInputData getRuleData() {
        return this.data;
    }


    @Override
    protected File getFile() {
        return new File("src/test/resources/dataset/data.yaml");
    }

}
