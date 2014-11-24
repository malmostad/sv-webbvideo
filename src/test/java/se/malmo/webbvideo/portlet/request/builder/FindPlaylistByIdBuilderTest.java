package se.malmo.webbvideo.portlet.request.builder;

import lombok.NoArgsConstructor;
import org.junit.Test;
import static org.junit.Assert.*;
import static se.malmo.webbvideo.portlet.constants.AppConstants.*;

/**
 *
 * @author Henrik Rydstedt
 */
@NoArgsConstructor
public class FindPlaylistByIdBuilderTest {
    private final String startQuery = BRIGHTCOVE_API_QUERY+"command=find_playlist_by_id"
            + "&playlist_id=1234567890&media_delivery=http&get_item_count=true&"
            + "token=TOKEN";
    /**
     * Test of build method, of class FindPlaylistByIdBuilder.
     */
    @Test
    public void testBuild_PlaylistFieldsBuilder_VideoFieldsBuilder() {
        FindPlaylistByIdBuilder builder = new FindPlaylistByIdBuilder("1234567890","TOKEN");
        
        PlaylistFieldsBuilder pfBuilder = new PlaylistFieldsBuilder();
        pfBuilder.enableField(PlaylistFieldsBuilder.Field.ID);
        
        VideoFieldsBuilder vfBuilder = new VideoFieldsBuilder();
        vfBuilder.enableField(VideoFieldsBuilder.Field.ID);
        
        String expResult = startQuery+pfBuilder.build()+vfBuilder.build();
        String result = builder.build(pfBuilder, vfBuilder);
        
        assertEquals(expResult, result);
    }

    /**
     * Test of build method, of class FindPlaylistByIdBuilder.
     */
    @Test
    public void testBuild_PlaylistFieldsBuilder() {
        FindPlaylistByIdBuilder builder = new FindPlaylistByIdBuilder("1234567890","TOKEN");
        
        PlaylistFieldsBuilder pfBuilder = new PlaylistFieldsBuilder();
        pfBuilder.enableField(PlaylistFieldsBuilder.Field.ID);
        
        String expResult = startQuery+pfBuilder.build();
        String result = builder.build(pfBuilder);
        
        assertEquals(expResult, result);
    }
}
