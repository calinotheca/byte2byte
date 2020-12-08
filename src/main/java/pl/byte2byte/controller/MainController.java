package pl.byte2byte.controller;

import java.io.IOException;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import pl.byte2byte.model.B2bForm;
import pl.byte2byte.service.B2bByteReplace;
import pl.byte2byte.service.B2bFormValidator;

/**
 * This class contains the main application controller.
 * @author Jakub Kalinowski
 *
 */

@Controller
public class MainController {

  // 1. Fields.
  
  @Autowired
  private B2bFormValidator b2bFormValidator; /** Inject a service for validating the form object. */
  
  @Autowired
  private B2bByteReplace b2bByteReplace; /** Inject a service for replacing bytes. */
  
  // 2. Methods.
  
  /**
   * The main page get controller.
   * @param fileChangedAmount
   * @param model
   * @return
   */
  
  @GetMapping("/")
  public String displayMainPage(@ModelAttribute("fileChangedAmount") String fileChangedAmount, Model model)  {
    
    // Add a form object into the page model.
    model.addAttribute("b2bForm", new B2bForm());
    
    // Add number of processed files.
    model.addAttribute("fileChangedAmount", fileChangedAmount);
   
    // Return the page template.
    return "index";
    
  }
  
  /**
   * The main page post controller.
   * @param b2bForm
   * @param bindingResult
   * @param redirectAttributes
   * @param model
   * @return
   * @throws IOException
   */
  
  @PostMapping("/")
  public String sendForm( 
      @Valid B2bForm b2bForm, 
      BindingResult bindingResult, 
      RedirectAttributes redirectAttributes, 
      Model model) throws IOException {
    
    // Check if form is valid.
    int b2bFormValidationResult = b2bFormValidator.checkB2bForm(b2bForm, bindingResult, model);
    int fileChangedAmount = 0;  
    
    // Start a replacing process if form is valid.
    if (b2bFormValidationResult==1) {
      
      fileChangedAmount = b2bByteReplace.replaceByte(b2bForm.getFindPhrase(), b2bForm.getReplacePhrase(), b2bForm.getLocalDirectoryPath(), b2bForm.getFilesExtension().toLowerCase());
      
      // Add an attribute with total amount of changed files.
      redirectAttributes.addFlashAttribute("fileChangedAmount", fileChangedAmount);
      
      // Redirect into the main page.
      return "redirect:/";
    }
 
    // Return the main page if a form is not valid.
    return "index";
  }
}