package pl.byte2byte.controller;

import java.util.Locale;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import pl.byte2byte.model.B2bForm;
import pl.byte2byte.service.B2bFormValidator;

@Controller
public class MainController {
  
  @Autowired
  private B2bFormValidator b2bFormValidator;
  
  @GetMapping("/")
  public String displayMainPage(Model model)  {
    
    model.addAttribute("b2bForm", new B2bForm());
   
    return "index";
    
  }
  
  @PostMapping("/")
  public String sendForm(
      @RequestParam("file") MultipartFile multipartFile, 
      @Valid B2bForm c, 
      BindingResult bindingResult, 
      HttpSession session, 
      Model model) {
    String directoryPath=session.getServletContext().getRealPath("/");  
    
    // Get the operating system file separator
    String fileName = multipartFile.getOriginalFilename();
    
    String path=session.getServletContext().getServerInfo().toString();  
    
    System.out.println(path);
    
    int b2bFormValidationResult = b2bFormValidator.checkB2bForm(b2bFormValidator, bindingResult, directoryPath, model);
        
    System.out.println(bindingResult.getAllErrors().toString());
    
   
    

      return "index";
  }

}
