package se.malmo.webbvideo.portlet.request;

import com.googlecode.ehcache.annotations.Cacheable;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import se.malmo.webbvideo.portlet.controller.WebbvideoAbstractController;
import se.malmo.webbvideo.portlet.exception.VideoCloudRequestException;
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
    public ItemsWithVideos doRequest(FindPlaylistByIdBuilder builder, PlaylistFieldsBuilder pfBuilder, VideoFieldsBuilder vfBuilder) throws VideoCloudRequestException {
        Logger.getLogger(WebbvideoAbstractController.class.getName()).log(Level.INFO, builder.build(pfBuilder));
        
        ItemsWithVideos entity;
        
        try {
            entity = restTemplate.getForObject(builder.build(pfBuilder,vfBuilder), ItemsWithVideos.class);
        }
        catch(RestClientException ex) {
            Logger.getLogger(VideoCloudCategoryVideoRequestService.class.getName()).log(Level.SEVERE, null, ex);
            throw new VideoCloudRequestException("Could not retrieve the playlist.");
        }
        
        return entity;
    }
}
