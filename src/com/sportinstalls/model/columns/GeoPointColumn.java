package com.sportinstalls.model.columns;

import com.mongodb.DBObject;
import com.mongodb.util.JSON;
import com.sportinstalls.utils.TextUtils;

public class GeoPointColumn extends Column<DBObject>
{
    private GeoPointExtractor extractor;


    public GeoPointColumn(int idx, String csvLabel, String columnLabel, GeoPointExtractor extractor)
    {
        super(idx, csvLabel, columnLabel);
        this.extractor = extractor;
    }

    public DBObject doFormat(String csvData) throws ColumnFormatterException
    {
        if (TextUtils.isEmpty(csvData))
            return null;

        try
        {
            GeoPointExtractor.GeoPoint geoPoint = extractor.extract(csvData);

            if (geoPoint != null)
            {
                StringBuilder geoShape = new StringBuilder()
                    .append("{")
                        .append("\"type\": \"Point\", ")
                        .append("\"coordinates\": [")
                            .append(geoPoint.latitude).append(", ")
                            .append(geoPoint.longitude)
                        .append("]")
                    .append("}");

                return (DBObject) JSON.parse(geoShape.toString());
            }

            return null;
        }
        catch (Exception e)
        {
            throw new ColumnFormatterException(e);
        }
    }

    public interface GeoPointExtractor
    {
        public GeoPoint extract(String data) throws Exception;

        public class GeoPoint
        {
            private String latitude;
            private String longitude;

            public GeoPoint(String latitude, String longitude)
            {
                this.latitude = latitude;
                this.longitude = longitude;
            }
        }
    }
}