package com.sportinstalls.model.columns;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTimeColumn extends Column<Long>
{
    private String dateFormat;
    private int stringLength;

    /**
     *
     * @param idx
     * @param csvLabel
     * @param dateFormat
     * @param stringLength Length to consider in csv string. Only the <code>stringLength</code> first
     * characters are used to format the string as a date.
     */
    public DateTimeColumn(int idx, String csvLabel, String columnLabel, String dateFormat, int stringLength)
    {
        super(idx, csvLabel, columnLabel);
        this.dateFormat = dateFormat;
        this.stringLength = stringLength;
    }

    public Long doFormat(String csvData) throws ColumnFormatterException
    {
        try
        {
            DateFormat format = new SimpleDateFormat(dateFormat);
            Date date = format.parse(csvData);

            return date.getTime();
        }
        catch(ParseException e)
        {
            throw new ColumnFormatterException(e);
        }
    }
}