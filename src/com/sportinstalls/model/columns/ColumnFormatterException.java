package com.sportinstalls.model.columns;

public class ColumnFormatterException extends Exception
{
    public ColumnFormatterException(String message, Throwable t)
    {
        super(message, t);
    }

    public ColumnFormatterException(Throwable t)
    {
        super(t);
    }
}
