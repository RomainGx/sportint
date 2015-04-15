package com.sportinstalls.model.columns.formatters;

import com.sportinstalls.model.columns.Column;

import org.apache.commons.lang3.text.WordUtils;

public class CaseFormatter implements Column.PostFormatCallback<String>
{
    public static final int CASE_UPPER_CAMEL = 0;
    public static final int CASE_LOWER_CAMEL = 1;
    public static final int CASE_UPPER = 2;
    public static final int CASE_LOWER = 3;

    private int caseFormat;


    public CaseFormatter(int caseFormat)
    {
        this.caseFormat = caseFormat;
    }

    public CaseFormatter()
    {
        this(CASE_UPPER_CAMEL);
    }

    public void setCaseFormat(int caseFormat)
    {
        this.caseFormat = caseFormat;
    }

    @Override
    public String onPostFormat(String data)
    {
        switch (caseFormat)
        {
            case CASE_UPPER_CAMEL:
            default:
                return WordUtils.capitalizeFully(data);

            case CASE_LOWER_CAMEL:
                return null;

            case CASE_UPPER:
                return data.toUpperCase();

            case CASE_LOWER:
                return data.toLowerCase();
        }
    }
}
