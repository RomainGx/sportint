package com.sportinstalls.model;

import com.sportinstalls.model.columns.ColumnFormatterException;

import java.io.IOException;

public interface DataReader
{
    public Data readFile(String fileName, String encoding) throws IOException, ColumnFormatterException;
}
