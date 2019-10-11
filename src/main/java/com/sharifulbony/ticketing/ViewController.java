package com.sharifulbony.ticketing;


import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping(value = "/ticketing-system")
@Api(value = "Ticketing System", description = "Operations related to Ticketing System")
public class ViewController {


    @RequestMapping(value = "/get-all", method = RequestMethod.GET)
    public ModelAndView getAll(){
        return new ModelAndView("welcome");
    }


}
