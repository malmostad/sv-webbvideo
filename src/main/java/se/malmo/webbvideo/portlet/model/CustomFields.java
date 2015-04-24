package se.malmo.webbvideo.portlet.model;

import lombok.Data;
import static se.malmo.webbvideo.portlet.constants.AppConstants.JSON_NULL;

/**
 *
 * @author Henrik Rydstedt
 */
@Data
public class CustomFields {
    private String targetgroup = JSON_NULL;
    private String cblandingpage;
}
