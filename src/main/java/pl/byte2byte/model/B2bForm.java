package pl.byte2byte.model;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * This class contains a form fields model.
 * @author Jakub Kalinowski
 */

public class B2bForm {
  
  // 1. Fields.

  // Set conditions for the findPhrase field.
  @Size(min=1, max=32, message="wrongLength")
  @Pattern(regexp="^[0-9,]*$", message="wrongSign")
  private String findPhrase = "";
  
  // Set condition for the replacePhrase field.
  @Size(max=32, message="wrongLength")
  @Pattern(regexp="^[0-9,]*$", message="wrongSign")
  private String replacePhrase = "";
  
  private String localDirectoryPath = "";
  private String filesExtension = "";
  
  // 2. Constructors.
  
  public B2bForm() {
    
  }
  
  public B2bForm(String findPhrase, String replacePhrase, String localDirectoryPath, String filesExtension) {
    this.findPhrase = findPhrase;
    this.replacePhrase = replacePhrase;
    this.localDirectoryPath = localDirectoryPath;
    this.filesExtension = filesExtension;
  }

  // 3. Getters and setters.
  
  public String getFindPhrase() {
    return findPhrase;
  }

  public void setFindPhrase(String findPhrase) {
    this.findPhrase = findPhrase;
  }

  public String getReplacePhrase() {
    return replacePhrase;
  }

  public void setReplacePhrase(String replacePhrase) {
    this.replacePhrase = replacePhrase;
  }

  public String getLocalDirectoryPath() {
    return localDirectoryPath;
  }

  public void setLocalDirectoryPath(String localDirectoryPath) {
    this.localDirectoryPath = localDirectoryPath;
  }

  public String getFilesExtension() {
    return filesExtension;
  }

  public void setFilesExtension(String filesExtension) {
    this.filesExtension = filesExtension;
  }
}