package com.sourcestudy.content.api;/**
 * @author Dantence
 * @description:
 * @date 2023/2/15
 */

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @projectName: SourceStudy
 * @package: com.sourcestudy.content.api
 * @className: FreemarkerController
 * @author: Dantence
 * @description: TODO
 * @date: 2023/2/15 19:35
 * @version: 1.0
 */
@Controller
public class FreemarkerController {
    @GetMapping("/test")
    public ModelAndView test() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("test");
        modelAndView.addObject("name", "yyw");
        return modelAndView;
    }



}
