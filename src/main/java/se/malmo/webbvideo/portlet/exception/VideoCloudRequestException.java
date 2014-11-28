package se.malmo.webbvideo.portlet.exception;

import lombok.NoArgsConstructor;

/**
 *
 * @author Henrik Rydstedt
 */
@NoArgsConstructor
public class VideoCloudRequestException extends Exception{
    public VideoCloudRequestException(String msg) {
        super(msg);
    }
}
