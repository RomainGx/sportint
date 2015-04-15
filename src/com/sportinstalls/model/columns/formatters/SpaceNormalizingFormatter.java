package com.sportinstalls.model.columns.formatters;

import com.sportinstalls.model.columns.Column;

public class SpaceNormalizingFormatter implements Column.PostFormatCallback<String>
{
    @Override
    public String onPostFormat(String data)
    {
        return data.replaceAll("\\s{2,}", " ");
    }
}
