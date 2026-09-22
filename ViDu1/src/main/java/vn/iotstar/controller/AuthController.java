package vn.iotstar.controller;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import vn.iotstar.dto.*; import vn.iotstar.service.AuthService;
@Controller @RequiredArgsConstructor
public class AuthController {
    private final AuthService auth;
    @GetMapping("/login") String login(){return "auth/login";}
    @GetMapping("/register") String register(Model m){m.addAttribute("form",new RegisterDTO());return "auth/register";}
    @PostMapping("/register") String register(@Valid @ModelAttribute("form") RegisterDTO dto,BindingResult br,Model m){if(br.hasErrors())return "auth/register";try{auth.register(dto);return "redirect:/verify-otp?email="+dto.getEmail();}catch(IllegalArgumentException e){m.addAttribute("error",e.getMessage());return "auth/register";}}
    @GetMapping("/verify-otp") String verify(@RequestParam String email,Model m){m.addAttribute("email",email);return "auth/verify-otp";}
    @PostMapping("/verify-otp") String verify(@RequestParam String email,@RequestParam String code,Model m){try{auth.verifyRegister(email,code);return "redirect:/login?verified=true";}catch(IllegalArgumentException e){m.addAttribute("email",email);m.addAttribute("error",e.getMessage());return "auth/verify-otp";}}
    @PostMapping("/resend-otp") String resend(@RequestParam String email,Model m){try{auth.resendRegister(email);return "redirect:/verify-otp?email="+email;}catch(IllegalArgumentException e){m.addAttribute("error",e.getMessage());m.addAttribute("email",email);return "auth/verify-otp";}}
    @GetMapping("/forgot-password") String forgot(){return "auth/forgot-password";}
    @PostMapping("/forgot-password") String forgot(@RequestParam String email){auth.requestReset(email);return "redirect:/reset-password?email="+email;}
    @GetMapping("/reset-password") String reset(@RequestParam String email,Model m){m.addAttribute("email",email);m.addAttribute("form",new ResetPasswordDTO());return "auth/reset-password";}
    @PostMapping("/reset-password") String reset(@RequestParam String email,@RequestParam String code,@Valid @ModelAttribute("form") ResetPasswordDTO dto,BindingResult br,Model m){if(br.hasErrors()){m.addAttribute("email",email);return "auth/reset-password";}try{auth.resetPassword(email,code,dto);return "redirect:/login?reset=true";}catch(IllegalArgumentException e){m.addAttribute("email",email);m.addAttribute("error",e.getMessage());return "auth/reset-password";}}
    @GetMapping("/access-denied") String denied(){return "error/403";}
}
