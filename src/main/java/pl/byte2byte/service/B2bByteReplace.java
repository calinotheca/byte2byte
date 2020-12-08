package pl.byte2byte.service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.stereotype.Service;

@Service
public class B2bByteReplace {

  public void replaceByte(String findPhrase, String replacePhrase, String localDirectoryPath, String filesExtension) throws IOException {

    Collection files = FileUtils.listFiles(new File(localDirectoryPath), null, true);
    if (filesExtension==null)
      filesExtension="";

    for (Iterator iterator = files.iterator(); iterator.hasNext();) {

      File file = (File) iterator.next();
      byte[] fileByteArray = Files.readAllBytes(Paths.get(file.getAbsolutePath()));
      
      String[] findStringArrayStrings = findPhrase.split(",");
      byte[] findStringByteArray = new byte[findStringArrayStrings.length];
      for (int i = 0; i < findStringArrayStrings.length; i++) {
       findStringByteArray[i] = Byte.parseByte(findStringArrayStrings[i]);
      }
      
      
      String[] replaceToByteArrayStrings = replacePhrase.split(",");
      byte[] replaceToByteArray = new byte[replaceToByteArrayStrings.length];
      for (int i = 0; i < replaceToByteArrayStrings.length; i++) {
        replaceToByteArray[i] = Byte.parseByte(replaceToByteArrayStrings[i]);
      }
      System.out.println(filesExtension);
     findAndReplacePhrase(fileByteArray, findStringByteArray, replaceToByteArray, file.getAbsolutePath(), filesExtension, 0);
    }
  }

  public void findAndReplacePhrase(byte[] fileByteArray, byte[] findStringByteArray, byte[] replaceToByteArray,
      String directoryPath, String filesExtension, int offset) throws IOException {

    int isByteExist = 0;
    int fileToWriteFlag = 0;
    for (int i = offset; i < fileByteArray.length; i++) {

      if (fileByteArray[i] == findStringByteArray[isByteExist])
        isByteExist++;
      else
        isByteExist = 0;

      if (findStringByteArray.length == isByteExist) {
        fileToWriteFlag = 1;
        int startOccuredBytes = i - findStringByteArray.length + 1;
        int bytesToChange = findStringByteArray.length;
        byte[] part1, part2, part3, both;
        

        part1 = Arrays.copyOfRange(fileByteArray, 0, startOccuredBytes);
        part2 = replaceToByteArray;
        part3 = Arrays.copyOfRange(fileByteArray, startOccuredBytes + bytesToChange, fileByteArray.length);
        both = ArrayUtils.addAll(part1, part2);
        fileByteArray = ArrayUtils.addAll(both, part3);
        offset = i - findStringByteArray.length + 1;
        isByteExist = 0;
      }
    }

    File changedfile = new File(directoryPath);
    String changedFileType = Files.probeContentType(changedfile.toPath());
    String changedFileExtension = changedfile.getName().substring(changedfile.getName().indexOf(".")+1).toLowerCase();
    
    if (changedFileType == null && fileToWriteFlag==1) {
      if (filesExtension.equals(changedFileExtension) || filesExtension=="")  {
        BufferedWriter writer = new BufferedWriter(new FileWriter(directoryPath));
        writer.write(new String(fileByteArray));
        writer.close();
        fileToWriteFlag=0;
      }
    } else if (changedFileType.startsWith("text") && fileToWriteFlag==1) {
      if (filesExtension.equals(changedFileExtension) || filesExtension=="")  {
        BufferedWriter writer = new BufferedWriter(new FileWriter(directoryPath));
        writer.write(new String(fileByteArray));
        writer.close();
        fileToWriteFlag=0;
      }
    } else if (fileToWriteFlag==1) {
      if (filesExtension.equals(changedFileExtension) || filesExtension=="")  {
        BufferedWriter writer = new BufferedWriter(new FileWriter(directoryPath));
        writer.write(new String(fileByteArray));
        writer.close();
        fileToWriteFlag=0;
      }
    }
  }
}
