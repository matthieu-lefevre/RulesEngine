package com.mlefevre.engine.rule.dataset;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

public abstract class YamlSerializer<T> {

    protected abstract File getFile();

    @SuppressWarnings("unchecked")
    public <E extends Constructor> T readYamlFile(E yamlConstructor) throws YamlSerializationException {
        T data = null;
        InputStream stream = null;
        try {
            stream = new FileInputStream(this.getFile());

            Yaml yaml = new Yaml(yamlConstructor);
            data = (T) yaml.load(stream);

        } catch(Exception e) {
            throw new YamlSerializationException(e);
        } finally {
            if(stream != null) {
                try { stream.close(); } catch(IOException e) {}
            }
        }
        return data;
    }

}
