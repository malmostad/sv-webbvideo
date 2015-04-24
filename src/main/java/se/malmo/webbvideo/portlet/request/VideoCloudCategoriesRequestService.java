package se.malmo.webbvideo.portlet.request;

import com.googlecode.ehcache.annotations.Cacheable;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import se.malmo.webbvideo.portlet.controller.WebbvideoAbstractController;
import se.malmo.webbvideo.portlet.exception.VideoCloudRequestException;
import se.malmo.webbvideo.portlet.model.CategoryItems;
import se.malmo.webbvideo.portlet.model.Items;
import se.malmo.webbvideo.portlet.request.builder.FindAllPlaylistsBuilder;
import se.malmo.webbvideo.portlet.request.builder.PlaylistFieldsBuilder;

/**
 *
 * @author Henrik Rydstedt
 */
@Component
public class VideoCloudCategoriesRequestService {
    @Autowired
    RestTemplate restTemplate;
    
    @Cacheable( cacheName = "categories", cacheNull = false )
    public List<Items> doRequest(FindAllPlaylistsBuilder builder, PlaylistFieldsBuilder pfBuilder) throws VideoCloudRequestException {
        Logger.getLogger(WebbvideoAbstractController.class.getName()).log(Level.INFO,
                ":"+"REQUEST_SERVICE");
        
        CategoryItems entity;
        
        try {
            entity = restTemplate.getForObject(builder.build(pfBuilder), CategoryItems.class);
        }
        catch(RestClientException ex) {
            Logger.getLogger(VideoCloudCategoriesRequestService.class.getName()).log(Level.SEVERE, null, ex);
            throw new VideoCloudRequestException("Could not retrieve categories. ");
        }
        
        List<Items> items = entity.getItems();
        
        if(items != null) {
            Collections.sort(items, Items.COMPARE_BY_NAME);
        }
        return items;
    }
}