package vn.iotstar.controller;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller; import org.springframework.ui.Model; import org.springframework.web.bind.annotation.GetMapping;
import vn.iotstar.repository.*;
@Controller @RequiredArgsConstructor public class HomeController {
 private final UserRepository users; private final CategoryRepository cats; private final ProductRepository products;
 @GetMapping("/") String home(){return "home";}
 @GetMapping("/dashboard") String dashboard(Model m){m.addAttribute("userCount",users.count());m.addAttribute("categoryCount",cats.count());m.addAttribute("productCount",products.count());return "dashboard";}
}
