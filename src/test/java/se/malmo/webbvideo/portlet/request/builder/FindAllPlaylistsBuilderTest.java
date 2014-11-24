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
public class FindAllPlaylistsBuilderTest {
    private final String startQuery=BRIGHTCOVE_API_QUERY+"command=find_all_playlists&page_size="+
                PAGE_SIZE+"&media_delivery=http&get_item_count=true&token=TOKEN";
    /**
     * Test of build method, of class FindAllPlaylistsBuilder.
     */
    @Test
    public void testBuild_PlaylistFieldsBuilder_VideoFieldsBuilder() {
        FindAllPlaylistsBuilder builder = new FindAllPlaylistsBuilder("TOKEN");
        
        PlaylistFieldsBuilder pfBuilder = new PlaylistFieldsBuilder();
        pfBuilder.enableField(PlaylistFieldsBuilder.Field.ID);
        
        VideoFieldsBuilder vfBuilder = new VideoFieldsBuilder();
        vfBuilder.enableField(VideoFieldsBuilder.Field.ID);
        
        String expResult = startQuery+pfBuilder.build()+vfBuilder.build();
        String result = builder.build(pfBuilder, vfBuilder);
        
        assertEquals(expResult, result);
    }

    /**
     * Test of build method, of class FindAllPlaylistsBuilder.
     */
    @Test
    public void testBuild_PlaylistFieldsBuilder() {
        FindAllPlaylistsBuilder builder = new FindAllPlaylistsBuilder("TOKEN");
        
        PlaylistFieldsBuilder pfBuilder = new PlaylistFieldsBuilder();
        pfBuilder.enableField(PlaylistFieldsBuilder.Field.ID);
        
        String expResult = startQuery+pfBuilder.build();
        String result = builder.build(pfBuilder);
        
        assertEquals(expResult, result);
    }
}
