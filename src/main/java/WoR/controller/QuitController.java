package WoR.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class QuitController {

    // This file is here for easier closing of WebApp in OS X where closing the app is sometimes impossible

    @RequestMapping("/quit")
    public void quit() {
        System.exit(1);
    }
}
