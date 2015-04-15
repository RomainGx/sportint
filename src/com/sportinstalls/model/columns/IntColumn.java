package com.sportinstalls.model.columns;

import com.sportinstalls.utils.TextUtils;

public class IntColumn extends Column<Integer>
{
    public IntColumn(int idx, String csvLabel, String columnLabel)
    {
        super(idx, csvLabel, columnLabel);
    }

    public Integer doFormat(String csvData) throws ColumnFormatterException
    {
        try
        {
            if (TextUtils.isEmpty(csvData))
                return null;

            return Integer.valueOf(csvData);
        }
        catch (NumberFormatException e)
        {
            throw new ColumnFormatterException(e);
        }
    }
}
