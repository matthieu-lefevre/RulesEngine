package com.mlefevre.engine.rule.dataset.expressions;


import com.mlefevre.engine.rule.dataset.YamlSerializationException;
import com.mlefevre.engine.rule.dataset.YamlSerializer;

import java.io.File;

public class ExpressionsYamlSerializer extends YamlSerializer<Expressions> {

    private Expressions expressions;

    public ExpressionsYamlSerializer() {
        try {
            ExpressionsYamlConstructor constructor = new ExpressionsYamlConstructor();
            this.expressions = this.readYamlFile(constructor);

        } catch (YamlSerializationException e) {
        }
    }


    public Expressions getExpressions() {
        return this.expressions;
    }


    @Override
    protected File getFile() {
        return new File("src/test/resources/dataset/expressions.yaml");
    }
}
