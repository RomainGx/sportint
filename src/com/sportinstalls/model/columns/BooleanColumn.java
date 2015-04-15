package com.sportinstalls.model.columns;

import com.sportinstalls.utils.TextUtils;

public class BooleanColumn extends Column<Boolean>
{
    private String valueIfTrue;


    public BooleanColumn(int idx, String csvLabel, String columnLabel, String valueIfTrue)
    {
        super(idx, csvLabel, columnLabel);
        this.valueIfTrue = valueIfTrue;
    }

    public Boolean doFormat(String csvData) throws ColumnFormatterException
    {
        if (TextUtils.isEmpty(csvData))
            return null;

        return valueIfTrue.equals(csvData) || Boolean.valueOf(csvData);
    }
}