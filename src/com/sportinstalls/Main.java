package com.sportinstalls;

import com.sportinstalls.model.Data;
import com.sportinstalls.model.DataReader;
import com.sportinstalls.model.FileInformation;
import com.sportinstalls.model.Line;
import com.sportinstalls.model.columns.BooleanColumn;
import com.sportinstalls.model.columns.Column;
import com.sportinstalls.model.columns.ColumnFormatterException;
import com.sportinstalls.model.columns.GeoPointColumn;
import com.sportinstalls.model.columns.IntColumn;
import com.sportinstalls.model.columns.StringColumn;
import com.sportinstalls.model.columns.formatters.AccentNormalizingFormatter;
import com.sportinstalls.model.columns.formatters.CaseFormatter;
import com.sportinstalls.model.columns.formatters.CompoundFormatter;
import com.sportinstalls.model.columns.formatters.SpaceNormalizingFormatter;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.LinkedList;
import java.util.List;

public class Main
{
    private static final String[] ID_KEYS = new String[] {
        "install_number",
        "town_lowercase",
        "name_lowercase"
    };

    private List<FileInformation> fileInformationList;
    private MongoDbRecorder recorder;


    public static void main(String[] args) throws Exception
    {
        long start = System.currentTimeMillis();
        new Main().execute();
        System.out.println("\nExecuted in " + (Math.round((System.currentTimeMillis() - start) * 1000.0) / 1000000.0) + " seconds");
    }

    public Main() throws UnknownHostException
    {
        recorder = new MongoDbRecorder();
        fileInformationList = new LinkedList<FileInformation>();
    }

    private void initFileInfoList()
    {
        CompoundFormatter<String> displayFormatters = new CompoundFormatter<String>();
        displayFormatters.addFormatter(new SpaceNormalizingFormatter());
        displayFormatters.addFormatter(new CaseFormatter(CaseFormatter.CASE_UPPER_CAMEL));

        CompoundFormatter<String> compareFormatters = new CompoundFormatter<String>();
        compareFormatters.addFormatter(new SpaceNormalizingFormatter());
        compareFormatters.addFormatter(new AccentNormalizingFormatter());
        compareFormatters.addFormatter(new CaseFormatter(CaseFormatter.CASE_LOWER));

        fileInformationList.clear();

        fileInformationList.add(new FileInformation(
            "install_france.csv",
            "ISO-8859-1",
            ";",
            new Column[] {
                new StringColumn(3, "ComLib", "town", displayFormatters),
                new StringColumn(3, "ComLib", "town_lowercase", compareFormatters),
                new StringColumn(4, "InsNumeroInstall", "install_number"),
                new StringColumn(5, "InsNom", "name", displayFormatters),
                new StringColumn(5, "InsNom", "name_lowercase", compareFormatters),
                new StringColumn(6, "InsNoVoie", "street_number"),
                new StringColumn(7, "InsLibelleVoie", "street"),
                new StringColumn(8, "InsLieuDit", "place_called"),
                new StringColumn(9, "InsCodePostal", "zip_code"),
                new BooleanColumn(13, "InsAccessibiliteAucun", "no_accessibility", "-1"),
                new BooleanColumn(14, "InsAccessibiliteHandiMoteur", "accessibility_motor_disability", "-1"),
                new BooleanColumn(15, "InsAccessibiliteHandiSens", "accessibility_sense_disability", "-1"),
                new IntColumn(16, "InsNbPlaceParking", "num_parking_space"),
                new IntColumn(17, "InsNbPlaceParkingHandi", "num_parking_space_handicapped"),
                new BooleanColumn(20, "InsTransportMetro", "has_transport_subway", "-1"),
                new BooleanColumn(21, "InsTransportBus", "has_transport_bus", "-1"),
                new BooleanColumn(22, "InsTransportTram", "has_transport_tram", "-1"),
                new BooleanColumn(23, "InsTransportTrain", "has_transport_train", "-1"),
                new BooleanColumn(24, "InsTransportBateau", "has_transport_boat", "-1"),
                new BooleanColumn(25, "InsTransportAutre", "has_other_transport", "-1"),
                //new DateTimeColumn(26, "InsDateMaj", "last_update", "YYYY-MM-dd HH:mm:ss", 18)
            },
            ID_KEYS
        ));

        fileInformationList.add(new FileInformation(
            "install_pays_loire.csv",
            "ISO-8859-1",
            "\",\"",
            new Column[] {
                new StringColumn(2, "Nom de la commune", "town", displayFormatters),
                new StringColumn(2, "Nom de la commune", "town_lowercase", compareFormatters),
                new StringColumn(1, "Numéro de l'installation", "install_number"),
                new StringColumn(0, "Nom usuel de l'installation", "name", displayFormatters),
                new StringColumn(0, "Nom usuel de l'installation", "name_lowercase", compareFormatters),
                new StringColumn(6, "Numero de la voie", "street_number"),
                new StringColumn(7, "Nom de la voie", "street", displayFormatters),
                new StringColumn(5, "Nom du lieu dit", "place_called", displayFormatters),
                new StringColumn(4, "Code postal", "zip_code"),
                new BooleanColumn(11, "Aucun aménagement d'accessibilité", "no_accessibility", "Oui"),
                new BooleanColumn(12, "Accessibilité handicapés à mobilité réduite", "accessibility_motor_disability", "Oui"),
                new BooleanColumn(13, "Accessibilité handicapés sensoriels", "accessibility_sense_disability", "Oui"),
                new IntColumn(17, "Nombre total de place de parking", "num_parking_space"),
                new IntColumn(18, "Nombre total de place de parking handicapés", "num_parking_space_handicapped"),
                new BooleanColumn(20, "Desserte métro", "has_transport_subway", "Oui"),
                new BooleanColumn(21, "Desserte bus", "has_transport_bus", "Oui"),
                new BooleanColumn(22, "Desserte Tram", "has_transport_tram", "Oui"),
                new BooleanColumn(23, "Desserte train", "has_transport_train", "Oui"),
                new BooleanColumn(24, "Desserte bateau", "has_transport_boat", "Oui"),
                new BooleanColumn(25, "Desserte autre", "has_other_transport", "Oui"),
                new GeoPointColumn(8, "location", "geo_loc", new GeoPointColumn.GeoPointExtractor() {
                    @Override
                    public GeoPoint extract(String data) throws Exception {
                        String[] parts = data.substring(1, data.length() - 1).split(" , ");
                        return new GeoPoint(parts[1], parts[0]);
                    }
                })
            },
            ID_KEYS
        ));

        fileInformationList.add(new FileInformation(
            "install_idf.csv",
            "UTF-8",
            ";",
            new Column[] {
                new StringColumn(1, "ins_com", "town", displayFormatters),
                new StringColumn(1, "ins_com", "town_lowercase", compareFormatters),
                new StringColumn(6, "ins_id", "install_number"),
                new StringColumn(0, "ins_nom", "name", displayFormatters),
                new StringColumn(0, "ins_nom", "name_lowercase", compareFormatters),
                new GeoPointColumn(4, "geo_point_2d", "geo_loc", new GeoPointColumn.GeoPointExtractor() {
                    @Override
                    public GeoPoint extract(String data) throws Exception {
                        String[] parts = data.split(", ");
                        return new GeoPoint(parts[1], parts[0]);
                    }
                })
            },
            ID_KEYS
        ));
    }

    public void execute()
    {
        try
        {
            initFileInfoList();

            for (FileInformation fileInfo : fileInformationList)
            {
                System.out.println("Processing file " + fileInfo.getFileName());
                updateDataFromFile(fileInfo);
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        catch (ColumnFormatterException e)
        {
            e.printStackTrace();
        }
        finally
        {
            recorder.close();
        }
    }

    private void updateDataFromFile(FileInformation fileInfo) throws IOException, ColumnFormatterException
    {
        int i=0;
        DataReader fileReader = new CsvFileReader(fileInfo.getCsvSeparator(), fileInfo.getColumns(), fileInfo.getIdKeys());
        System.out.println("Reading file " + fileInfo.getFileName());
        Data data = fileReader.readFile(fileInfo.getFileName(), fileInfo.getEncoding());

        System.out.println("Recording data from file " + fileInfo.getFileName());

        recorder.prepareBulkOperation();

        for (Line line : data.getLines())
        {
            if (i++ % 100 == 0)
                System.out.print(i + " ");
            recorder.record(line, data.getIdKeys());
        }

        System.out.println();
        recorder.executeBulkOperation();
    }
}
