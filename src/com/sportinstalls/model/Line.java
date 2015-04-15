package com.sportinstalls.model;

import java.util.HashMap;
import java.util.Map;

public class Line
{
    private Map<String, Object> fields;

    public Line(int size)
    {
        fields = new HashMap<String, Object>(size);
    }

    public void addFieldValue(String columnLabel, Object fieldValue)
    {
        fields.put(columnLabel, fieldValue);
    }

    public Map<String, Object> getFields()
    {
        return fields;
    }
}
