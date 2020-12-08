package pl.byte2byte.service;

import java.io.File;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import pl.byte2byte.model.B2bForm;

/**
 * This class contains a service for validating the main page form after sending.
 * @author Jakub Kalinowski
 */

@Service
public class B2bFormValidator {
  
  public int checkB2bForm(B2bForm b2bForm, BindingResult bindingResult, Model model) {
    
    // Check a findPhrase.
    int errorCount = 0;    
    if (bindingResult.hasFieldErrors("findPhrase")) {
      if (bindingResult.getFieldError("findPhrase").getDefaultMessage().equals("wrongLength")) {
      model.addAttribute("findPhraseFieldError", 1);
      errorCount++;
      }
      if (bindingResult.getFieldError("findPhrase").getDefaultMessage().equals("wrongSign")) {
      model.addAttribute("findPhraseFieldErrorWrongSign", 1);
      errorCount++;
      }
    }

    // Check a replacePhrase.
    if (bindingResult.hasFieldErrors("replacePhrase")) {
      if (bindingResult.getFieldError("replacePhrase").getDefaultMessage().equals("wrongLength")) {
      model.addAttribute("replacePhraseFieldError", 1);
      errorCount++;
      }
      if (bindingResult.getFieldError("replacePhrase").getDefaultMessage().equals("wrongSign")) {
      model.addAttribute("replacePhraseFieldErrorWrongSign", 1);
      errorCount++;
      }
    }
    
    // Check if localDirectoryPath exist on local drive.
    File file = new File(b2bForm.getLocalDirectoryPath());
    if (!file.exists() && !file.isDirectory()) {
      model.addAttribute("directoryPathFieldError", 1);
      errorCount++;
    }
    
    // Return 0 if form is not valid and 1 if is valid.
    if (errorCount > 0)
      return 0;
    else
      return 1; 
  }
}