package com.sportinstalls.model.columns.formatters;

import com.sportinstalls.model.columns.Column;

import java.util.ArrayList;
import java.util.List;

public class CompoundFormatter<T> implements Column.PostFormatCallback<T>
{
    private List<Column.PostFormatCallback<T>> formatters;


    public CompoundFormatter(List<Column.PostFormatCallback<T>> formatters)
    {
        this.formatters = formatters;
    }

    public CompoundFormatter()
    {
        this.formatters = new ArrayList<Column.PostFormatCallback<T>>();
    }

    public void addFormatter(Column.PostFormatCallback<T> formatter)
    {
        formatters.add(formatter);
    }

    @Override
    public T onPostFormat(T data)
    {
        for (Column.PostFormatCallback<T> formatter : formatters)
            data = formatter.onPostFormat(data);

        return data;
    }
}
