package com.sportinstalls.model.columns;

import com.sportinstalls.utils.TextUtils;

public class DoubleColumn extends Column<Double>
{
    public DoubleColumn(int idx, String csvLabel, String columnLabel)
    {
        super(idx, csvLabel, columnLabel);
    }

    public Double doFormat(String csvData) throws ColumnFormatterException
    {
        try
        {
            if (TextUtils.isEmpty(csvData))
                return null;

            return Double.valueOf(csvData);
        }
        catch (NumberFormatException e)
        {
            throw new ColumnFormatterException(e);
        }
    }
}
