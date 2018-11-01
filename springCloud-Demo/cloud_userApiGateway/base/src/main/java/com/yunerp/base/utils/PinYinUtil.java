package com.yunerp.base.utils;

import java.io.BufferedReader;  
import java.io.File;  
import java.io.FileInputStream;  
import java.io.FileNotFoundException;  
import java.io.IOException;  
import java.io.InputStreamReader;  
import java.io.UnsupportedEncodingException;  
import java.util.HashMap;  
import java.util.Map;  
  
import org.apache.log4j.Logger;

import com.alibaba.dubbo.common.utils.StringUtils;

import net.sourceforge.pinyin4j.PinyinHelper;  
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;  
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;  
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;  
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;  
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

/**
 * 
 * @author:dyw
 * @version:1.0
 * @date:2016年8月30日下午5:21:07
 * @description:<li>业务描述：汉字转拼音工具类</li>
 */
public class PinYinUtil {
	 private static final Logger logger = Logger.getLogger("devLog");  
     
     public static Map<String,String> dictionary = new HashMap<String,String>();  
   
     //加载多音字词典  
     static{  
           
         BufferedReader br = null;  
         try {  
             File file = new File("./config/duoyinzi_pinyin.txt");  
             br = new BufferedReader(new InputStreamReader(new FileInputStream(file),"UTF-8"));  
               
             String line = null;  
             while((line=br.readLine())!=null){  
                   
                 String[] arr = line.split("#");  
                   
                 if(StringUtils.isNotEmpty(arr[1])){  
                     String[] sems = arr[1].split(" ");  
                     for (String sem : sems) {  
                           
                         if(StringUtils.isNotEmpty(sem)){  
                             dictionary.put(sem , arr[0]);  
                         }  
                     }  
                 }  
             }  
               
         } catch (UnsupportedEncodingException e) {  
             e.printStackTrace();  
         } catch (FileNotFoundException e) {  
             e.printStackTrace();  
         } catch (IOException e) {  
             e.printStackTrace();  
         }finally{  
             if(br!=null){  
                 try {  
                     br.close();  
                 } catch (IOException e) {  
                     e.printStackTrace();  
                 }  
             }  
         }  
   
     }  
       
     //查找ASCII值
     public static String[] chineseToPinYin(char chineseCharacter) throws BadHanyuPinyinOutputFormatCombination{  
         HanyuPinyinOutputFormat outputFormat = new HanyuPinyinOutputFormat();  
         outputFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);  
         outputFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);  
         outputFormat.setVCharType(HanyuPinyinVCharType.WITH_V);  
           
         if(chineseCharacter>=32 && chineseCharacter<=125){    //ASCII >=33 ASCII<=125的直接返回 ,ASCII码表：http://www.asciitable.com/  
             return new String[]{String.valueOf(chineseCharacter)};  
         }  
           
         return PinyinHelper.toHanyuPinyinStringArray(chineseCharacter, outputFormat);  
     }  
       
     /** 
      * 获取汉字拼音的全拼 
      * @param chineseCharacter 
      * @return 
      * @throws BadHanyuPinyinOutputFormatCombination 
      */  
     public static String chineseToPinYinF(String chineseCharacter) throws BadHanyuPinyinOutputFormatCombination{  
         if(StringUtils.isEmpty(chineseCharacter)){  
             return null;  
         }  
           
         char[] chs = chineseCharacter.toCharArray();  
           
         StringBuilder result = new StringBuilder();  
           
         for(int i=0;i<chs.length;i++){  
             String[] arr = chineseToPinYin(chs[i]);  
             if(arr==null){  
                 result.append("");  
             }else if(arr.length==1){  
                 result.append(arr[0]);  
             }else if(arr[0].equals(arr[1])){  
                 result.append(arr[0]);  
             }else{  
                   
                 String prim = chineseCharacter.substring(i, i+1);  
 //              System.out.println("prim="+prim+"**i="+i);  
                   
                 String lst = null,rst = null;  
                   
                 if(i<=chineseCharacter.length()-2){  
                     rst = chineseCharacter.substring(i,i+2);  
                 }  
                 if(i>=1 && i+1<=chineseCharacter.length()){  
                     lst = chineseCharacter.substring(i-1,i+1);  
                 }  
                   
 //              System.out.println("lst="+lst+"**rst="+rst);  
                   
                 String answer = null;  
                 for (String py : arr) {  
                       
                     if(StringUtils.isEmpty(py)){  
                         continue;  
                     }  
                       
                     if((lst!=null && py.equals(dictionary.get(lst))) ||  
                             (rst!=null && py.equals(dictionary.get(rst)))){  
                         answer = py;  
 //                      System.out.println("get it,answer="+answer+",i="+i+"**break");  
                         break;  
                     }  
                       
                     if(py.equals(dictionary.get(prim))){  
                         answer = py;  
 //                      System.out.println("get it,answer="+answer+",i="+i+"**prim="+prim);  
                     }  
                 }  
                 if(answer!=null){  
                     result.append(answer);  
                 }else{  
                     logger.warn("no answer ch="+chs[i]);  
                 }  
             }  
         }  
           
         return result.toString().toLowerCase();  
     }
           
     /**  
      * 获取汉字串拼音首字母，英文字符不变  
      * @param chinese 汉字串  
      * @return 汉语拼音首字母  
      */   
     public static String getFirstSpell(String chinese) {   
             StringBuffer pybf = new StringBuffer();   
             char[] arr = chinese.toCharArray();   
             HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();   
             defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);   
             defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);   
             for (int i = 0; i < arr.length; i++) {   
                     if (arr[i] > 128) {   
                             try {   
                                     String[] temp = PinyinHelper.toHanyuPinyinStringArray(arr[i], defaultFormat);   
                                     if (temp != null) {   
                                             pybf.append(temp[0].charAt(0));   
                                     }   
                             } catch (BadHanyuPinyinOutputFormatCombination e) {   
                                     e.printStackTrace();   
                             }   
                     } else {   
                             pybf.append(arr[i]);   
                     }   
             }   
             return pybf.toString().replaceAll("\\W", "").trim();   
     }   
}
