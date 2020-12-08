package pl.byte2byte.service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.stereotype.Service;

/**
 * This class contains service for replacing byte strings. 
 * @author Jakub Kalinowski
 */

@Service
public class B2bByteReplace {

  // 1. Methods.
  
  /**
   * This method lists files and runs findAndReplacePhrase method. 
   * @param findPhrase
   * @param replacePhrase
   * @param localDirectoryPath
   * @param filesExtension
   * @return
   * @throws IOException
   */
  
  public int replaceByte(String findPhrase, String replacePhrase, String localDirectoryPath, String filesExtension)
      throws IOException {

    int fileChangedAmount = 0;
    
    // Add all files from directory and subdirectories into the Collection object.
    Collection files = FileUtils.listFiles(new File(localDirectoryPath), null, true);
    
    if (filesExtension == null || filesExtension == "")
      filesExtension = "";
    
    // Loop files and run findAndReplacePhrase if a byte phrase occured.
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
      
      // Replace a pharse for matching files and count results.
      fileChangedAmount += findAndReplacePhrase(fileByteArray, findStringByteArray, replaceToByteArray,
          file.getAbsolutePath(), filesExtension, 0);
    }
    return fileChangedAmount;
  }

  
  /**
   * The method replace phrase in files and save it.
   * @param fileByteArray
   * @param findStringByteArray
   * @param replaceToByteArray
   * @param directoryPath
   * @param filesExtension
   * @param offset
   * @return
   * @throws IOException
   */
  
  public int findAndReplacePhrase(byte[] fileByteArray, byte[] findStringByteArray, byte[] replaceToByteArray,
      String directoryPath, String filesExtension, int offset) throws IOException {

    int isByteExist = 0;
    int fileToWriteFlag = 0;
    
    // Loop bytes in the file.
    for (int i = offset; i < fileByteArray.length; i++) {

      if (fileByteArray[i] == findStringByteArray[isByteExist])
        isByteExist++;
      else
        isByteExist = 0;

      // Replace phrase if match.
      if (findStringByteArray.length == isByteExist) {
        fileToWriteFlag = 1;
        int startOccuredBytes = i - findStringByteArray.length + 1;
        int bytesToChange = findStringByteArray.length;
        byte[] part1, part2, part3, both;

        // Merge a byte array for a file.
        part1 = Arrays.copyOfRange(fileByteArray, 0, startOccuredBytes);
        part2 = replaceToByteArray;
        part3 = Arrays.copyOfRange(fileByteArray, startOccuredBytes + bytesToChange, fileByteArray.length);
        both = ArrayUtils.addAll(part1, part2);
        fileByteArray = ArrayUtils.addAll(both, part3);
        offset = i - findStringByteArray.length + 1;
        isByteExist = 0;
      }
    }

    // Write file section.
    
    File changedfile = new File(directoryPath);
    String changedFileType = Files.probeContentType(changedfile.toPath());
    String changedFileExtension = changedfile.getName().substring(changedfile.getName().indexOf(".") + 1).toLowerCase();
    int fileChanged = 0;

    if ((filesExtension.equals(changedFileExtension) || filesExtension.isBlank()) && fileToWriteFlag == 1) {
      BufferedWriter writer = new BufferedWriter(new FileWriter(directoryPath));
      writer.write(new String(fileByteArray));
      writer.close();
      fileToWriteFlag = 0;
      fileChanged = 1;
    }
    return fileChanged;
  }
}