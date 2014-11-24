package se.malmo.webbvideo.portlet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import se.malmo.webbvideo.portlet.model.*;
import se.malmo.webbvideo.portlet.request.builder.FindAllPlaylistsBuilder;
import se.malmo.webbvideo.portlet.request.builder.FindPlaylistByIdBuilder;
import se.malmo.webbvideo.portlet.request.builder.PlaylistFieldsBuilder;
import se.malmo.webbvideo.portlet.request.builder.VideoFieldsBuilder;

import java.util.*;
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
    
    protected Map<String, String> getCategories(String token) {
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
        }
        return categoryMap;
    }

    protected List<Videos> getVideosFromPlaylistId(String id, String token, int numberOfVideos) {
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
