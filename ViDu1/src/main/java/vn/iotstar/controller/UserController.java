package vn.iotstar.controller;
import lombok.RequiredArgsConstructor; import org.springframework.data.domain.PageRequest; import org.springframework.stereotype.Controller; import org.springframework.ui.Model; import org.springframework.web.bind.annotation.*; import vn.iotstar.mapper.UserMapper; import vn.iotstar.repository.*;
@Controller @RequiredArgsConstructor public class UserController {
 private final UserRepository users; private final RoleRepository roles; private final UserMapper mapper;
 @GetMapping("/users") String list(@RequestParam(defaultValue="") String q,@RequestParam(defaultValue="0") int page,Model m){var p=users.findByEmailContainingIgnoreCaseOrFullNameContainingIgnoreCase(q,q,PageRequest.of(Math.max(0,page),10));m.addAttribute("users",p);m.addAttribute("q",q);return "users/list";}
 @PostMapping("/users/{id}/toggle") String toggle(@PathVariable Long id){var u=users.findById(id).orElseThrow();u.setEnabled(!u.isEnabled());users.save(u);return "redirect:/users";}
}
