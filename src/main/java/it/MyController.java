package it;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MyController {
    @GetMapping("/")
    public String menu() {
        return "menu";
    }

    @GetMapping("/manager1")
    public String manager1() {
        return "manager1";
    }

    @GetMapping("/manager")
    public String manager() {
        return "manager";
    }

    @GetMapping("/worker")
    public String worker() {
        return "Worker";
    }

    @GetMapping("/worker1")
    public String worker1() {
        return "worker1";
    }

}
