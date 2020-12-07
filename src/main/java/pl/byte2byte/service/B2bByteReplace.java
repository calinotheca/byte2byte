package pl.byte2byte.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.Iterator;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;

@Service
public class B2bByteReplace {

  public int replaceByte(String findPhrase, String replacePhrase, String localDirectoryPath) throws IOException  {
    
    File root = new File(localDirectoryPath);
    Collection files = FileUtils.listFiles(root, null, true);

      for (Iterator iterator = files.iterator(); iterator.hasNext();) {
        File file = (File) iterator.next();

        byte[] fileByteArray = Files.readAllBytes(Paths.get(file.getAbsolutePath()));
        byte[] findStringByteArray = findPhrase.getBytes();
        byte[] replaceToByteArray = replacePhrase.getBytes();

        this.findAndReplace(fileByteArray, findStringByteArray, replaceToByteArray, file.getAbsolutePath(), 0);
        System.out.println(file.getAbsolutePath());
      }
    return 1;
  }
  
  
  
  
  
  public <bytes> void findAndReplace(byte[] fileByteArray, byte[] findStringByteArray, byte[] replaceToByteArray, String directoryPath, int offset) throws IOException {
    
    System.out.println("lala");
  }

  
  
  
  
}
