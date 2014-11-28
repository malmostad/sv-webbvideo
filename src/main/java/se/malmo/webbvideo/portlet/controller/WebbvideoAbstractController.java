package se.malmo.webbvideo.portlet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import se.malmo.webbvideo.portlet.model.*;
import se.malmo.webbvideo.portlet.request.builder.FindAllPlaylistsBuilder;
import se.malmo.webbvideo.portlet.request.builder.FindPlaylistByIdBuilder;
import se.malmo.webbvideo.portlet.request.builder.PlaylistFieldsBuilder;
import se.malmo.webbvideo.portlet.request.builder.VideoFieldsBuilder;

import java.util.*;
import se.malmo.webbvideo.portlet.exception.VideoCloudRequestException;
import se.malmo.webbvideo.portlet.request.VideoCloudCategoriesRequestService;
import se.malmo.webbvideo.portlet.request.VideoCloudCategoryVideoRequestService;

/**
 *
 * @author Henrik Rydstedt
 */
@Component
public abstract class WebbvideoAbstractController {
    @Autowired VideoCloudCategoriesRequestService categoriesService;
    @Autowired VideoCloudCategoryVideoRequestService videoService;
    
    /**
     * Retrieves id and name on all playlists from VideoCloud's API with the specified token.
     * @param token
     * @return  Map with id as key and name as value, 
     *          if no categories could be retrieved null is returned.
     * @throws se.malmo.webbvideo.portlet.exception.VideoCloudRequestException
     */
    protected Map<String, String> getCategories(String token) throws VideoCloudRequestException {
        FindAllPlaylistsBuilder queryBuilder = new FindAllPlaylistsBuilder(token);
        PlaylistFieldsBuilder pfb = new PlaylistFieldsBuilder();
        pfb.enableField(PlaylistFieldsBuilder.Field.ID).
            enableField(PlaylistFieldsBuilder.Field.NAME);
        
        Map<String,String> categoryMap = new LinkedHashMap<String, String>();
        
        List<Items> items = categoriesService.doRequest(queryBuilder,pfb);

        if(items != null) {
            for(Items item : items) {
                categoryMap.put(item.getId(), item.getName());
            }
            return categoryMap;
        }
        return null;
    }
    
    /**
     * Retrieves a playlist's videos from VideoCloud's API with corresponding id and token. 
     * The amount of videos returned is set by numberOfVideos.
     * @param id
     * @param token
     * @param numberOfVideos
     * @return List of Videos
     * @throws se.malmo.webbvideo.portlet.exception.VideoCloudRequestException
     */
    protected List<Videos> getVideosFromPlaylistId(String id, String token, int numberOfVideos) throws VideoCloudRequestException {
        FindPlaylistByIdBuilder queryBuilder = new FindPlaylistByIdBuilder(id, token);
        PlaylistFieldsBuilder pfb = new PlaylistFieldsBuilder();
        pfb.enableField(PlaylistFieldsBuilder.Field.ID)
            .enableField(PlaylistFieldsBuilder.Field.NAME)
            .enableField(PlaylistFieldsBuilder.Field.VIDEOS);
        
        VideoFieldsBuilder vfb = new VideoFieldsBuilder();
        vfb.enableField(VideoFieldsBuilder.Field.ID)
            .enableField(VideoFieldsBuilder.Field.NAME)
            .enableField(VideoFieldsBuilder.Field.THUMBNAILURL)
            .enableField(VideoFieldsBuilder.Field.CUSTOMFIELDS);

        ItemsWithVideos items = videoService.doRequest(queryBuilder,pfb,vfb);

        List<Videos> videoList = new ArrayList<Videos>();
        List<Videos> tmpList = items.getVideos();
        int i = 0;
        Iterator<Videos> iterator = tmpList.iterator();

        while(iterator.hasNext() && i < numberOfVideos) {
            Videos current = iterator.next();

            CustomFields cf = current.getCustomFields();
            boolean showVideo = true;
            if(cf != null) {

                if(cf.getTargetgroup() != null) {
                    String target = cf.getTargetgroup();
                    //Do not show video if the targetgroup is komin.
                    showVideo = !target.toLowerCase().contains("komin");
                }
            }

            if(showVideo) {
                videoList.add(tmpList.get(i));
                i++;
            }
        }
        return videoList;
    }
}
