package se.malmo.webbvideo.portlet.model;

import lombok.Data;

/**
 *
 * @author Henrik Rydstedt
 */
@Data
public class Videos {
    private String id;
    private String name;
    private String shortDescription;
    private String thumbnailURL;
    private CustomFields customFields;
    private boolean useExternalVideoLink = false;
}
