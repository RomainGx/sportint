package com.sportinstalls.utils;

public final class TextUtils
{
    public static boolean isEmpty(String str)
    {
        return str == null || str.trim().length() == 0;
    }

    public static String trimDoubleQuotes(String str)
    {
        if (str != null)
        {
            int length = str.length();

            if (length > 0)
            {
                if (str.charAt(0) == '"')
                {
                    if (str.charAt(length - 1) == '"')
                        return str.substring(1, length - 1);

                    return str.substring(1);
                }
            }

            return str;
        }

        return null;
    }
}
