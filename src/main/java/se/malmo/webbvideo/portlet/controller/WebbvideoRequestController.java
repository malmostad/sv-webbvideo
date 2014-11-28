package se.malmo.webbvideo.portlet.controller;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jcr.Node;
import static se.malmo.webbvideo.portlet.constants.AppConstants.*;
import static se.malmo.webbvideo.portlet.constants.Tokens.*;

import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import javax.portlet.PortletPreferences;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import se.malmo.webbvideo.portlet.exception.VideoCloudRequestException;

import se.malmo.webbvideo.portlet.model.Videos;
import senselogic.sitevision.api.Utils;
import senselogic.sitevision.api.context.PortletContextUtil;
import senselogic.sitevision.api.resource.ResourceLocatorUtil;

/**
 *
 * @author Henrik Rydstedt
 */
@Controller
@RequestMapping("VIEW")
@SessionAttributes("category")
public class WebbvideoRequestController extends WebbvideoAbstractController {
    
    @RequestMapping()
    public String showItems(Model model, RenderRequest request, RenderResponse response, PortletPreferences prefs) {
        Utils utils = (Utils)request.getAttribute("sitevision.utils");
        PortletContextUtil pcu = utils.getPortletContextUtil();
        ResourceLocatorUtil rlu = utils.getResourceLocatorUtil();
        Node currentPage = pcu.getCurrentPage();
        Node sitePage = rlu.getSitePage();

        String category =  prefs.getValue(CATEGORY, null);
        List<Videos> videoList = null;
        if(category != null) {
            /* STARTPAGE */
            if(currentPage == sitePage) {
                List<Videos> generalVideoList;
                try {
                    videoList = getVideosFromPlaylistId(category, ACCESS_TOKEN_GENERAL, NUMBER_OF_VIDEOS-1);
                    generalVideoList = getVideosFromPlaylistId(KF_PLAYLIST_ID, ACCESS_TOKEN_KF, 1);
                } catch (VideoCloudRequestException ex) {
                    Logger.getLogger(WebbvideoRequestController.class.getName()).log(Level.SEVERE, null, ex);
                    model.addAttribute("errorText", ex.getMessage());
                    return "error";
                }
                if(!generalVideoList.isEmpty())
                    videoList.add(generalVideoList.get(0));
            }
            /* OTHER PAGES */
            else {
                try {
                    videoList = getVideosFromPlaylistId(category, ACCESS_TOKEN_GENERAL, NUMBER_OF_VIDEOS);
                } catch (VideoCloudRequestException ex) {
                    Logger.getLogger(WebbvideoRequestController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            model.addAttribute("videoList", videoList);
            return "videolist";
        }
        
        model.addAttribute("errorText", "No category is chosen.");
        return "error";
    }
}
