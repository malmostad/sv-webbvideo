package se.malmo.webbvideo.portlet.request.builder;

import lombok.NoArgsConstructor;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Henrik Rydstedt
 */
@NoArgsConstructor
public class PlaylistFieldsBuilderTest {
    /**
     * Test of enableField method, of class PlaylistFieldsBuilder.
     */
    @Test
    public void testEnableField() {
        String startQuery = "&playlist_fields=";
        
        PlaylistFieldsBuilder instance = new PlaylistFieldsBuilder();
        String expResult = startQuery+"ID,NAME,SHORTDESCRIPTION,PLAYLISTTYPE,VIDEOS,VIDEOIDS,ACCOUNTID,REFERENCEID,FILTERTAGS,THUMBNAILURL";
        String result = instance.build();
        assertEquals(expResult,result);
        
        instance = new PlaylistFieldsBuilder();
        expResult = startQuery+"ID";
        instance.enableField(PlaylistFieldsBuilder.Field.ID);
        result = instance.build();
        assertEquals(expResult, result);
        
        instance = new PlaylistFieldsBuilder();
        expResult = startQuery+"ID,NAME";
        instance.enableField(PlaylistFieldsBuilder.Field.ID)
                .enableField(PlaylistFieldsBuilder.Field.NAME)
                .enableField(PlaylistFieldsBuilder.Field.ID);
        result = instance.build();
        assertEquals(expResult,result);
    }    
}
