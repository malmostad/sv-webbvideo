package se.malmo.webbvideo.portlet.request.builder;

import lombok.EqualsAndHashCode;
import static se.malmo.webbvideo.portlet.constants.AppConstants.*;

/**
 *
 * @author Henrik Rydstedt
 */
@EqualsAndHashCode
public class FindAllPlaylistsBuilder {
    private String token = "";
    
    public FindAllPlaylistsBuilder(String token) {
        this.token = token;
    }
    
    /**
     * Builds an URL to the VideoCloud API with query string parameters from
     * PlaylistFieldsBuilder pfb and VideoFieldsbuilder vfb. 
     * @param pfb
     * @param vfb
     * @return String representing the corresponding URL for getting all playlists from VideoCloud. 
     */
    public String build(PlaylistFieldsBuilder pfb, VideoFieldsBuilder vfb) {
        return BRIGHTCOVE_API_QUERY+"command=find_all_playlists&page_size="+PAGE_SIZE+
                "&media_delivery=http&get_item_count=true&token="+token+pfb.build()+vfb.build();
    }
    public String build(PlaylistFieldsBuilder pfb) {
        return BRIGHTCOVE_API_QUERY+"command=find_all_playlists&page_size="+PAGE_SIZE+
                "&media_delivery=http&get_item_count=true&token="+token+pfb.build();
    }
}

