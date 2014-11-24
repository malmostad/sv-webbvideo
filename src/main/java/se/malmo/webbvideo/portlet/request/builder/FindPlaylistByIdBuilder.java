package se.malmo.webbvideo.portlet.request.builder;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import static se.malmo.webbvideo.portlet.constants.AppConstants.*;

/**
 *
 * @author Henrik Rydstedt
 */
@EqualsAndHashCode
public class FindPlaylistByIdBuilder {
    private String id = "";
    private String token = "";
    
    public FindPlaylistByIdBuilder(String id, String token) {
        this.id = id;
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
        return BRIGHTCOVE_API_QUERY+"command=find_playlist_by_id&playlist_id="+
                id+"&media_delivery=http&get_item_count=true&token="+
                token+pfb.build()+vfb.build();
    }
    
    /**
     * Builds an URL to the VideoCloud API with query string parameters from
     * PlaylistFieldsBuilder pfb. 
     * @param pfb
     * @return String representing the corresponding URL for getting all playlists from VideoCloud. 
     */
    public String build(PlaylistFieldsBuilder pfb) {
        return BRIGHTCOVE_API_QUERY+"command=find_playlist_by_id&playlist_id="+
                id+"&media_delivery=http&get_item_count=true&token="+
                token+pfb.build();
    }
}