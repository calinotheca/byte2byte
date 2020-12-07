package pl.byte2byte.service;

import java.io.File;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.multipart.MultipartFile;

import pl.byte2byte.model.B2bForm;

@Service
public class B2bFormValidator {
  
  public int checkB2bForm(B2bForm b2bForm, BindingResult bindingResult, Model model) {
    
    int errorCount = 0;
    
    if (bindingResult.hasFieldErrors("findPhrase")) {
      model.addAttribute("findPhraseFieldError", 1);
      errorCount++;
    }
    
    if (bindingResult.hasFieldErrors("replacePhrase")) {
      model.addAttribute("replacePhraseFieldError", 1);
      errorCount++;
    }
    
//    File file = new File(b2bForm.getLocalDirectoryPath());
//    if (!file.exists() && !file.isDirectory()) {
//      model.addAttribute("directoryPathFieldError", 1);
//      errorCount++;
//    }
//    
    if (errorCount > 0)
      return 0;
    else
      return 1;
    
  }

}
