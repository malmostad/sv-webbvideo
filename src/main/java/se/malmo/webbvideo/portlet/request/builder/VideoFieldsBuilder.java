package se.malmo.webbvideo.portlet.request.builder;

import java.util.ArrayList;
import java.util.List;
import lombok.EqualsAndHashCode;

/**
 *
 * @author Henrik Rydstedt
 */
@EqualsAndHashCode
public class VideoFieldsBuilder {
    private List<Field> enabledFields;
    private String query = "";
    
    public enum Field {
        ID,
        NAME,
        SHORTDESCRIPTION,
        LONGDESCRIPTION,
        CREATIONDATE,
        PUBLISHEDDATE,
        LASTMODIFIEDDATE,
        VERSION,
        LINKURL,
        LINKTEXT,
        TAGS,
        CUSTOMFIELDS,
        CUEPOINTS,
        VIDEOSTILLURL,
        VIDEOSTILL,
        THUMBNAILURL,
        THUMBNAIL,
        LOGOOVERLAY,
        REFERENCEID,
        LENGTH,
        ECONOMICS,
        PLAYSTOTAL,
        PLAYSTRAILINGWEEK,
        FLVURL,
        RENDITIONS,
        IOSRENDITIONS,
        HDSRENDITIONS,
        HDSMANIFESTURL,
        WVMRENDITIONS,
        SMOOTHRENDITIONS,
        SMOOTHMANIFESTURL,
        HLSURL,
        FLVFULLLENGTH,
        VIDEOFULLLENGTH,
        DIGITALMASTER,
        ACCOUNTID,
        ITEMSTATE,
        STARTDATE,
        ENDDATE,
        GEOFILTERED,
        GEOFILTEREDCOUNTRIES,
        GEOFILTEREXCLUDE
    }
    
    public VideoFieldsBuilder() {
        enabledFields = new ArrayList<Field>();
    }
    
    public VideoFieldsBuilder enableField(Field f) {
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
     * Builds the video field query parameter to the VideoCloud API with corresponding attributes enabled. 
     * Default is all attributes enabled. 
     * @return String representing the corresponding video field parameters. 
     */
    public String build() {
        if(enabledFields.isEmpty()) {
            for(Field f : Field.values()) {
                addToQuery(f.toString());
            }
        }
        return "&video_fields="+query;
    }
}
