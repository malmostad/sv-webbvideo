package se.malmo.webbvideo.portlet.request.builder;

import java.util.ArrayList;
import java.util.List;
import lombok.EqualsAndHashCode;

/**
 *
 * @author Henrik Rydstedt
 */
@EqualsAndHashCode
public class PlaylistFieldsBuilder {
    private List<Field> enabledFields;
    private String query = "";
    
    
    public enum Field {
        ID, 
        NAME, 
        SHORTDESCRIPTION, 
        PLAYLISTTYPE, 
        VIDEOS, 
        VIDEOIDS, 
        ACCOUNTID, 
        REFERENCEID, 
        FILTERTAGS, 
        THUMBNAILURL
    }
    public PlaylistFieldsBuilder() {
        enabledFields = new ArrayList<Field>();
    }
    
    public PlaylistFieldsBuilder enableField(Field f) {
        if(!enabledFields.contains(f)) {
            enabledFields.add(f);
            addToQuery(f.toString());
        }
        return this;
    }
    
    private void addToQuery(String attr) {
        if(query == "")
            query+=attr;
        else
            query+=","+attr;
    }
    
    /**
     * Builds the playlist field query parameter to the VideoCloud API with corresponding attributes enabled. 
     * Default is all attributes enabled. 
     * @return String representing the corresponding playlist field parameters. 
     */
    public String build() {
        if(enabledFields.isEmpty()) {
            for(Field f : Field.values()) {
                addToQuery(f.toString());
            }
        }
        return "&playlist_fields="+query;
    }
}
