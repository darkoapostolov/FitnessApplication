package com.example.fitnessapp.web.controller;//package com.example.fitnessapp.web.controller;
//
//import com.example.fitnessapp.model.Exercise;
//import com.example.fitnessapp.model.ExerciseSchedule;
//import com.example.fitnessapp.service.ExerciseScheduleService;
//import com.example.fitnessapp.service.ExerciseService;
//import mk.ukim.finki.wp.eshop.model.Category;
//import mk.ukim.finki.wp.eshop.model.Manufacturer;
//import mk.ukim.finki.wp.eshop.model.Product;
//import mk.ukim.finki.wp.eshop.service.CategoryService;
//import mk.ukim.finki.wp.eshop.service.ManufacturerService;
//import mk.ukim.finki.wp.eshop.service.ProductService;
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@Controller
//@RequestMapping("/schedules")
//public class ProductListController {
//
//
//    private final CategoryService categoryService;
//    private final ManufacturerService manufacturerService;
//    private final ExerciseService exerciseService;
//    private final ExerciseScheduleService exerciseScheduleService;
//
//    public ProductListController(ProductService productService,
//                                 CategoryService categoryService,
//                                 ManufacturerService manufacturerService) {
//        this.productService = productService;
//        this.categoryService = categoryService;
//        this.manufacturerService = manufacturerService;
//    }
//
//    @GetMapping
//    public String getSchedulePage(@RequestParam(required = false) String error, Model model) {
//        if (error != null && !error.isEmpty()) {
//            model.addAttribute("hasError", true);
//            model.addAttribute("error", error);
//        }
//        List<ExerciseSchedule> schedules = this.exerciseScheduleService.listAll();
//        model.addAttribute("schedules", schedules);
//        model.addAttribute("bodyContent", "schedules");
//        return "master-template";
//    }
//
//    @DeleteMapping("/delete/{id}")
//    public String deleteProduct(@PathVariable Long id) {
//        this.exerciseScheduleService.delete(id);
//        return "redirect:/products";
//    }
//
//    @GetMapping("/edit-form/{id}")
//    public String editProductPage(@PathVariable Long id, Model model) {
//        if (this.productService.findById(id).isPresent()) {
//            Product product = this.productService.findById(id).get();
//            List<Manufacturer> manufacturers = this.manufacturerService.findAll();
//            List<Category> categories = this.categoryService.listCategories();
//            model.addAttribute("manufacturers", manufacturers);
//            model.addAttribute("categories", categories);
//            model.addAttribute("product", product);
//            model.addAttribute("bodyContent", "add-product");
//            return "master-template";
//        }
//        return "redirect:/products?error=ProductNotFound";
//    }
//
//    @GetMapping("/add-form")
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
//    public String addProductPage(Model model) {
//        List<Manufacturer> manufacturers = this.manufacturerService.findAll();
//        List<Category> categories = this.categoryService.listCategories();
//        model.addAttribute("manufacturers", manufacturers);
//        model.addAttribute("categories", categories);
//        model.addAttribute("bodyContent", "add-product");
//        return "master-template";
//    }
//
//    @PostMapping("/add")
//    public String saveProduct(
//            @RequestParam(required = false) Long id,
//            @RequestParam String name,
//            @RequestParam Double price,
//            @RequestParam Integer quantity,
//            @RequestParam Long category,
//            @RequestParam Long manufacturer) {
//        if (id != null) {
//            this.productService.edit(id, name, price, quantity, category, manufacturer);
//        } else {
//            this.productService.save(name, price, quantity, category, manufacturer);
//        }
//        return "redirect:/products";
//    }
//}
