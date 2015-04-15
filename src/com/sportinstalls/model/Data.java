package com.sportinstalls.model;

import java.util.LinkedList;
import java.util.List;

public class Data
{
    private List<Line> lines;
    private String[] idKeys;

    public Data(String[] idKeys)
    {
        lines = new LinkedList<Line>();
        this.idKeys = idKeys;
    }

    public void addLine(Line line)
    {
        lines.add(line);
    }

    public List<Line> getLines()
    {
        return lines;
    }

    public String[] getIdKeys()
    {
        return idKeys;
    }
}
