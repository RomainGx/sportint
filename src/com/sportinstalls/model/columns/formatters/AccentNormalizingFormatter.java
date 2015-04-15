package com.sportinstalls.model.columns.formatters;

import com.sportinstalls.model.columns.Column;

import org.apache.commons.lang3.StringUtils;

public class AccentNormalizingFormatter implements Column.PostFormatCallback<String>
{
    @Override
    public String onPostFormat(String data)
    {
        return StringUtils.stripAccents(data);
    }
}
