package pl.byte2byte.model;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class B2bForm {
  
  @Size(min=1, max=16)
  private String findPhrase = "";
  
  @Size(max=16)
  private String replacePhrase = "";
  
  private String localDirectoryPath = "";
  
  public B2bForm() {
    
  }
  
  public B2bForm(String findPhrase, String replacePhrase, String localDirectoryPath) {
    super();
    this.findPhrase = findPhrase;
    this.replacePhrase = replacePhrase;
    this.localDirectoryPath = localDirectoryPath;
  }

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
}
