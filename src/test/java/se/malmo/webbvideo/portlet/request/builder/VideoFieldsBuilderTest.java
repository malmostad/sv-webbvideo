package se.malmo.webbvideo.portlet.request.builder;

import lombok.NoArgsConstructor;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Henrik Rydstedt
 */
@NoArgsConstructor
public class VideoFieldsBuilderTest {
    /**
     * Test of enableField method, of class VideoFieldsBuilder.
     */
    @Test
    public void testEnableField() {
        String startQuery = "&video_fields=";
        
        VideoFieldsBuilder instance = new VideoFieldsBuilder();
        String expResult = startQuery+"ID,NAME,SHORTDESCRIPTION,LONGDESCRIPTION,"
                + "CREATIONDATE,PUBLISHEDDATE,LASTMODIFIEDDATE,VERSION,LINKURL,"
                + "LINKTEXT,TAGS,CUSTOMFIELDS,CUEPOINTS,VIDEOSTILLURL,VIDEOSTILL,"
                + "THUMBNAILURL,THUMBNAIL,LOGOOVERLAY,REFERENCEID,LENGTH,ECONOMICS,"
                + "PLAYSTOTAL,PLAYSTRAILINGWEEK,FLVURL,RENDITIONS,IOSRENDITIONS,"
                + "HDSRENDITIONS,HDSMANIFESTURL,WVMRENDITIONS,SMOOTHRENDITIONS,"
                + "SMOOTHMANIFESTURL,HLSURL,FLVFULLLENGTH,VIDEOFULLLENGTH,"
                + "DIGITALMASTER,ACCOUNTID,ITEMSTATE,STARTDATE,ENDDATE,GEOFILTERED,"
                + "GEOFILTEREDCOUNTRIES,GEOFILTEREXCLUDE";
        String result = instance.build();
        assertEquals(expResult,result);
        
        instance = new VideoFieldsBuilder();
        expResult = startQuery+"ID";
        instance.enableField(VideoFieldsBuilder.Field.ID);
        result = instance.build();
        assertEquals(expResult, result);
        
        instance = new VideoFieldsBuilder();
        expResult = startQuery+"ID,NAME";
        instance.enableField(VideoFieldsBuilder.Field.ID)
                .enableField(VideoFieldsBuilder.Field.NAME)
                .enableField(VideoFieldsBuilder.Field.ID);
        result = instance.build();
        assertEquals(expResult,result);
    }
}
