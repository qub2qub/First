package by.den.service;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

@RestController
public class RestHiController {

    @RequestMapping("/hi")
    public String sayHi(WebRequest webRequest) {
//        webRequest.
        return "Hi There!!  from REST";
    }

}
