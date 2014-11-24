package se.malmo.webbvideo.portlet.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;
import lombok.Data;

/**
 *
 * @author Henrik Rydstedt
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ItemsWithVideos {
    private String id;
    private String name;
    private List<Videos> videos;
}
