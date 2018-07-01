package com.Dev.PortScanMVC.controller;

import com.Dev.PortScanMVC.bootstrap.Bootstrap;
import com.Dev.PortScanMVC.service.PortService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Kevin Neag
 */
@Slf4j
@Controller
public class IndexController {

    private PortService portService;


    public IndexController(PortService portService) {
        this.portService = portService;
    }

    @RequestMapping({"","/","/index"})
    public String getIndex(Model model){
        if(true){
            log.debug("Still Scanning");
            return "home";
        }
        else {
            log.debug("Getting Index page");
            model.addAttribute("port", portService.getPorts());
            return "index";
        }
    }

}