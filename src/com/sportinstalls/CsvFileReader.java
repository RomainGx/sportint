package com.sportinstalls;

import com.sportinstalls.model.Data;
import com.sportinstalls.model.DataReader;
import com.sportinstalls.model.Line;
import com.sportinstalls.model.columns.Column;
import com.sportinstalls.model.columns.ColumnFormatterException;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class CsvFileReader implements DataReader
{
    private String separator;
    private Column[] columns;
    private String[] idKeys;


    public CsvFileReader(String separator, Column[] columns, String[] idKeys)
    {
        this.separator = separator;
        this.columns = columns;
        this.idKeys = idKeys;
    }

    @Override
    public Data readFile(String fileName, String encoding) throws IOException, ColumnFormatterException
    {
        int                 i               = 0;
        Data                data            = new Data(idKeys);
        InputStream         stream          = new FileInputStream(fileName);
        InputStreamReader   streamReader    = new InputStreamReader(stream, encoding);
        BufferedReader      reader          = new BufferedReader(streamReader);
        String              csvLine;


        // Consume first line which contains headers
        reader.readLine();

        while ((csvLine = reader.readLine()) != null)
        {
            if (i++ % 100 == 0)
                System.out.print(i + " ");

            String[] parts = csvLine.split(separator);

            Line line = new Line(columns.length);
            data.addLine(line);

            for (Column column : columns)
                line.addFieldValue(column.getColumnLabel(), column.format(parts[column.getIdx()]));
        }

        reader.close();
        System.out.println();

        return data;
    }
}
