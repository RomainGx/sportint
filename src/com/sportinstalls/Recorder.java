package com.sportinstalls;

import com.sportinstalls.model.Line;

public interface Recorder
{
    public void record(Line line, String[] idKeys);
    public void close();
}
