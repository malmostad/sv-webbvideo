package se.malmo.webbvideo.portlet.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import se.malmo.webbvideo.portlet.model.WebvideoRequestForm;

import javax.portlet.*;
import java.io.IOException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import static se.malmo.webbvideo.portlet.constants.AppConstants.*;
import static se.malmo.webbvideo.portlet.constants.Tokens.*;
import se.malmo.webbvideo.portlet.exception.VideoCloudRequestException;

/**
 *
 * @author Henrik Rydstedt
 */
@Controller
@RequestMapping("CONFIG")
public class WebbvideoConfigController extends WebbvideoAbstractController {

    @RequestMapping
    public String doConfig(Model model, PortletPreferences prefs, RenderRequest request, RenderResponse response) {
        Map<String,String> categoryMap;
        
        try {
            categoryMap = getCategories(ACCESS_TOKEN_GENERAL);
        }
        catch(VideoCloudRequestException ex) {
            Logger.getLogger(WebbvideoRequestController.class.getName()).log(Level.SEVERE, null, ex);
            model.addAttribute("errorText", ex.getMessage());
            return "error";
        }
        
        model.addAttribute("categories", categoryMap);
        
        WebvideoRequestForm form = (WebvideoRequestForm)model.asMap().get("form");
        if (form == null){
            prefs.getMap();
            String chosenCategory = prefs.getValue(CATEGORY, "");
            form = new WebvideoRequestForm();
            form.setCategory(chosenCategory);
        }
        
        model.addAttribute("form", form);
        model.addAttribute("renderResponse", response);
        
        return "config";
    }
    
    @RequestMapping(params = "action=save")
    public void processAction(Model model, @ModelAttribute("form") WebvideoRequestForm form, PortletPreferences prefs, ActionRequest request, ActionResponse response) {        
        model.addAttribute("form", form);
        model.addAttribute("renderResponse", response);
        
        if(request.getParameter("_ok") != null) {
            try {
                prefs.setValue(CATEGORY, form.getCategory());
            } catch (ReadOnlyException ex) {
                Logger.getLogger(WebbvideoConfigController.class.getName()).log(Level.SEVERE, null, ex);
                model.addAttribute("errorText", ex.getMessage());
                response.setRenderParameter("action", "error");
            }
            try {
                prefs.store();
            } catch (IOException ex) {
                Logger.getLogger(WebbvideoConfigController.class.getName()).log(Level.SEVERE, null, ex);
                model.addAttribute("errorText", ex.getMessage());
                response.setRenderParameter("action", "error");
            } catch (ValidatorException ex) {
                Logger.getLogger(WebbvideoConfigController.class.getName()).log(Level.SEVERE, null, ex);
                model.addAttribute("errorText", ex.getMessage());
                response.setRenderParameter("action", "error");
            }
        }
        
        try {
            response.setPortletMode(PortletMode.VIEW);
        } catch (PortletModeException ex) {
            Logger.getLogger(WebbvideoConfigController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
