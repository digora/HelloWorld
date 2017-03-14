package hellow.mobapde.com.helloworld.GoogleMapParser;

import android.graphics.Color;

import java.util.HashMap;
import java.util.List;

/**
 * Created by patricktobias on 14/03/2017.
 */

public class PathWrapperSettings {

    public String url;
    public int pathColor; // hex 0xFF000000
    public int lineWidth;

    public List<List<HashMap<String, String>>> routes; // TO BE SET DURING ASYNCTASK

    public PathWrapperSettings (int color, int lineWidth) {
        url = null;
        pathColor = color;
        this.lineWidth = lineWidth;
    }

}
