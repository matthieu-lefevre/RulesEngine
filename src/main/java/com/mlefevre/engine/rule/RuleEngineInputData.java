package com.mlefevre.engine.rule;

import java.util.HashMap;
import java.util.Map;

/**
 * Define the data object that must be injected to compute the rule(s)
 *
 * @author Matthieu
 */
public class RuleEngineInputData {

    /**
     * List data elements
     * <br/>
     * indexed by name
     */
    protected Map<String, Object> data = new HashMap<String, Object>();


    /**
     * CONSTRUCTOR
     */
    public RuleEngineInputData() { }


    /**
     * GETTERS AND SETTERS
     */

    public Map<String, Object> getData() {
        return data;
    }

    public Object getElement(String name) {
        Object element = null;
        if(this.data.containsKey(name)) {
            element = this.data.get(name);
        }
        return element;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }

    public void addElement(String name, Object value) {
        this.data.put(name, value);
    }

}
