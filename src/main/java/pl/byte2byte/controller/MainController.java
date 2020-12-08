package pl.byte2byte.controller;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Locale;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import pl.byte2byte.model.B2bForm;
import pl.byte2byte.service.B2bByteReplace;
import pl.byte2byte.service.B2bFormValidator;

@Controller
public class MainController {
  
  @Autowired
  private B2bFormValidator b2bFormValidator;
  
  @Autowired
  private B2bByteReplace b2bByteReplace;
  
  @GetMapping("/")
  public String displayMainPage(@ModelAttribute("fileChangedAmount") String fileChangedAmount, Model model)  {
    
    System.out.println(fileChangedAmount);
    model.addAttribute("b2bForm", new B2bForm());
    model.addAttribute("fileChangedAmount", fileChangedAmount);
   
    return "index";
    
  }
  
  @PostMapping("/")
  public String sendForm( 
      @Valid B2bForm b2bForm, 
      BindingResult bindingResult, RedirectAttributes redirectAttributes, 
      Model model) throws IOException {
    
    int b2bFormValidationResult = b2bFormValidator.checkB2bForm(b2bForm, bindingResult, model);
    int fileChangedAmount = 0;  
    
    if (b2bFormValidationResult==1) {
      fileChangedAmount = b2bByteReplace.replaceByte(b2bForm.getFindPhrase(), b2bForm.getReplacePhrase(), b2bForm.getLocalDirectoryPath(), b2bForm.getFilesExtension().toLowerCase());
      System.out.println("lalalal");
      System.out.println(fileChangedAmount);
      redirectAttributes.addFlashAttribute("fileChangedAmount", fileChangedAmount);
      return "redirect:/";
    }
 
    return "index";
  }

}
