package se.malmo.webbvideo.portlet.request;

import com.googlecode.ehcache.annotations.Cacheable;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import se.malmo.webbvideo.portlet.controller.WebbvideoAbstractController;
import se.malmo.webbvideo.portlet.model.ItemsWithVideos;
import se.malmo.webbvideo.portlet.request.builder.FindPlaylistByIdBuilder;
import se.malmo.webbvideo.portlet.request.builder.PlaylistFieldsBuilder;
import se.malmo.webbvideo.portlet.request.builder.VideoFieldsBuilder;

/**
 *
 * @author Henrik Rydstedt
 */
@Component
public class VideoCloudCategoryVideoRequestService  {
    @Autowired
    RestTemplate restTemplate;
    
    @Cacheable( cacheName = "categoryVideos")
    public ItemsWithVideos doRequest(FindPlaylistByIdBuilder builder, PlaylistFieldsBuilder pfBuilder, VideoFieldsBuilder vfBuilder) {
        Logger.getLogger(WebbvideoAbstractController.class.getName()).log(Level.INFO, builder.build(pfBuilder));
        
        ItemsWithVideos entity = restTemplate.getForObject(builder.build(pfBuilder,vfBuilder), ItemsWithVideos.class);
        
        return entity;
    }
}
