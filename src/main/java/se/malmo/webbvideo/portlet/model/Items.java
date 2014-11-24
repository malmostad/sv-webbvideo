package se.malmo.webbvideo.portlet.model;

import java.util.Comparator;
import lombok.Data;

/**
 *
 * @author Henrik Rydstedt
 */
@Data
public class Items {
    private String id;
    private String name;
    
    public static Comparator<Items> COMPARE_BY_NAME = new Comparator<Items>() {
        @Override
        public int compare(Items item1, Items item2) {
            return item1.getName().compareTo(item2.getName()); 
        }
    };
}
