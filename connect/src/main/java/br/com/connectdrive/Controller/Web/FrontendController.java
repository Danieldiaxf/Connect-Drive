package br.com.connectdrive.Controller.Web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FrontendController {

    @GetMapping({"/", "/alunos", "/alunos/**"})
    public String forward() {
        return "forward:/index.html";
    }
}
