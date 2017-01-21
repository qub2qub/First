package by.den.service;

import by.den.model.User;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class UserController {

    //produces() element serves only to restrict the mapping for your request handlers. It does nothing else.
    @RequestMapping(value = "/test"/*, produces = MediaType.APPLICATION_JSON*/)
    public @ResponseBody User test() {
        return new User("Ivan", "Rybin", 33, "ivan_riba@mail.ru");
    }

 /*
    @RequestMapping(value = "/test", produces= MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody User testJson() {
        return new User("Ivan", "JSON", 33, "ivan_riba@mail.ru");
    }

    @RequestMapping(value = "/test", produces= MediaType.APPLICATION_XML_VALUE)
    public @ResponseBody User testXml() {
        return new User("Ivan", "XML", 33, "ivan_riba@mail.ru");
    }
*/
}
