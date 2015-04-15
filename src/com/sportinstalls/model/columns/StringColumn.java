package com.sportinstalls.model.columns;

public class StringColumn extends Column<String>
{
    public StringColumn(int idx, String csvLabel, String columnLabel)
    {
        super(idx, csvLabel, columnLabel);
    }

    public StringColumn(int idx, String csvLabel, String columnLabel, PostFormatCallback<String> formatter)
    {
        super(idx, csvLabel, columnLabel, formatter);
    }

    public String doFormat(String csvData) throws ColumnFormatterException
    {
        return csvData;
    }
}