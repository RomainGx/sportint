package com.sportinstalls.model;

import com.sportinstalls.model.columns.Column;

public class FileInformation
{
    private String fileName;
    private String encoding;
    private String csvSeparator;
    private Column[] columns;
    private String[] idKeys;


    public FileInformation(String fileName, String encoding, String csvSeparator, Column[] columns, String[] idKeys)
    {
        this.fileName = fileName;
        this.encoding = encoding;
        this.csvSeparator = csvSeparator;
        this.columns = columns;
        this.idKeys = idKeys;
    }

    public String getFileName()
    {
        return fileName;
    }

    public String getEncoding()
    {
        return encoding;
    }

    public String getCsvSeparator()
    {
        return csvSeparator;
    }

    public Column[] getColumns()
    {
        return columns;
    }

    public String[] getIdKeys()
    {
        return idKeys;
    }
}
