package vn.iotstar.controller;
import jakarta.validation.Valid; import lombok.RequiredArgsConstructor; import org.springframework.data.domain.PageRequest; import org.springframework.stereotype.Controller; import org.springframework.ui.Model; import org.springframework.validation.BindingResult; import org.springframework.web.bind.annotation.*; import vn.iotstar.dto.CategoryDTO; import vn.iotstar.entity.Category; import vn.iotstar.repository.CategoryRepository;
@Controller @RequiredArgsConstructor public class CategoryController {
 private final CategoryRepository cats;
 @GetMapping("/categories") String list(@RequestParam(defaultValue="") String q,@RequestParam(defaultValue="0") int page,Model m){m.addAttribute("categories",cats.findByNameContainingIgnoreCase(q,PageRequest.of(Math.max(0,page),10)));m.addAttribute("form",new CategoryDTO());m.addAttribute("q",q);return "categories/list";}
 @PostMapping("/categories") String save(@Valid @ModelAttribute("form") CategoryDTO dto,BindingResult br,Model m){if(br.hasErrors()){m.addAttribute("categories",cats.findAll());return "categories/list";}cats.save(new Category(dto.getName().trim()));return "redirect:/categories";}
 @PostMapping("/categories/{id}/delete") String delete(@PathVariable Long id){cats.deleteById(id);return "redirect:/categories";}
}
