package vlillealert.jeremsamson.com.vlillealert;

import android.util.Xml;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import vlillealert.jeremsamson.com.vlillealert.Beans.Station;

/**
 * Created by jerem on 11/04/16.
 */
public class Stationparser {
    private static final String ns = null;

    public List parse(InputStream in) throws XmlPullParserException, IOException {
        try {
            XmlPullParser parser = Xml.newPullParser();
            parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
            parser.setInput(in, null);
            parser.nextTag();
            return readFeed(parser);
        } finally {
            in.close();
        }
    }

    private List readFeed(XmlPullParser parser) throws XmlPullParserException, IOException {
        List stations = new ArrayList();

        parser.require(XmlPullParser.START_TAG, ns, "station");
        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }
            String name = parser.getName();

            if (name.equals("adress")) {
                stations.add(readStation(parser));
            } else {
                skip(parser);
            }
        }
        return stations;
    }

    private Station readStation(XmlPullParser parser) throws XmlPullParserException, IOException {
        parser.require(XmlPullParser.START_TAG, ns, "station");
        Station station = new Station();

        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }
            String name = parser.getName();
            if (name.equals("adress")) {
                station.setAdress(readString(parser, "adress"));
            } else if (name.equals("status")) {
                station.setStatus(readInteger(parser, "status"));
            } else if (name.equals("bikes")) {
                station.setBikes(readInteger(parser, "bikes"));
            } else if (name.equals("attachs")) {
                station.setAttachs(readInteger(parser, "attachs"));
            } else if (name.equals("paiement")) {
                station.setPaiement(readInteger(parser, "paiement"));
            } else if (name.equals("lastupd")) {
                station.setLastupd(readString(parser, "lastupd"));
            } else {
                skip(parser);
            }
        }
        return station;
    }

    private String readString(XmlPullParser parser, String key) throws IOException, XmlPullParserException {
        parser.require(XmlPullParser.START_TAG, ns, key);
        String title = readText(parser);
        parser.require(XmlPullParser.END_TAG, ns, key);
        return title;
    }

    private int readInteger(XmlPullParser parser, String key) throws IOException, XmlPullParserException {
        parser.require(XmlPullParser.START_TAG, ns, key);
        String title = readText(parser);
        parser.require(XmlPullParser.END_TAG, ns, key);
        return Integer.parseInt(title);
    }

    // For the tags title and summary, extracts their text values.
    private String readText(XmlPullParser parser) throws IOException, XmlPullParserException {
        String result = "";
        if (parser.next() == XmlPullParser.TEXT) {
            result = parser.getText();
            parser.nextTag();
        }
        return result;
    }

    private void skip(XmlPullParser parser) throws XmlPullParserException, IOException {
        if (parser.getEventType() != XmlPullParser.START_TAG) {
            throw new IllegalStateException();
        }
        int depth = 1;
        while (depth != 0) {
            switch (parser.next()) {
                case XmlPullParser.END_TAG:
                    depth--;
                    break;
                case XmlPullParser.START_TAG:
                    depth++;
                    break;
            }
        }
    }


}
