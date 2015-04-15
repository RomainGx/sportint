package com.sportinstalls.model.columns;

import com.sportinstalls.utils.TextUtils;

public abstract class Column<T>
{
    protected int idx;
    protected String csvLabel;
    protected String columnLabel;
    private PostFormatCallback<T> postFormatCallback;

    public Column(int idx, String csvLabel, String columnLabel)
    {
        this(idx, csvLabel, columnLabel, null);
    }

    public Column(int idx, String csvLabel, String columnLabel, PostFormatCallback<T> postFormatCallback)
    {
        this.idx = idx;
        this.csvLabel = csvLabel;
        this.columnLabel = columnLabel;
        this.postFormatCallback = postFormatCallback;
    }

    protected String preFormat(String csvData)
    {
        return TextUtils.trimDoubleQuotes(csvData);
    }

    public T format(String csvData) throws ColumnFormatterException
    {
        csvData = preFormat(csvData);
        T data = doFormat(csvData);

        if (postFormatCallback != null)
            data = postFormatCallback.onPostFormat(data);

        return data;
    }

    public abstract T doFormat(String csvData) throws ColumnFormatterException;

    public int getIdx()
    {
        return idx;
    }

    public String getCsvLabel()
    {
        return csvLabel;
    }

    public String getColumnLabel()
    {
        return columnLabel;
    }


    public interface PostFormatCallback<T>
    {
        public T onPostFormat(T data);
    }
}