package hellow.mobapde.com.helloworld.Converter;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

import hellow.mobapde.com.helloworld.Beans.Stop;

/**
 * Created by patricktobias on 27/03/2017.
 */

public class MapToArrayListConverter {

    public static ArrayList<Stop> convertMapToStopArray (Map map) {
        ArrayList<Stop> stopsArray = new ArrayList<Stop>();

        Iterator it = map.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            System.out.println(pair.getKey() + " = " + pair.getValue());

            stopsArray.add((Stop) pair.getValue());
            it.remove(); // avoids a ConcurrentModificationException
        }

        return stopsArray;
    }

}
