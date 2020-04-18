package com.yhzn.common.util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

import org.apache.commons.codec.binary.Base64;

import sun.misc.BASE64Encoder;
   
public class DESUtil {  
   
    Key key ;  
    private String keyStr;
   
    public DESUtil() {  
     
    }  
   
    public String getKeyStr() {
		return keyStr;
	}

	public void setKeyStr(String keyStr) {
		this.keyStr = keyStr;
	}

	public DESUtil(String str) {  
      this.keyStr = str;
    }  
   
    public Key getKey() {  
       return key ;  
    }  
   
    public void setKey(Key key) {  
       this . key = key;  
    }  
   
    /** 
      * 根据参数生成 KEY 
      */  
    public void setKey(String strKey) {  
       
    }  
   
    /** 
      * 加密 String 明文输入 ,String 密文输出 
      */  
    public String encryptStr(String strMing) {  
       byte [] byteMi = null ;  
       byte [] byteMing = null ;  
       String strMi = "" ;  
       BASE64Encoder base64en = new BASE64Encoder();  
       try {  
           byteMi = this .encryptByte(strMing.getBytes("UTF8"));  
           strMi = base64en.encode(byteMi);  
       } catch (Exception e) {  
           throw new RuntimeException(  
                  "Error initializing SqlMap class. Cause: " + e);  
       } finally {  
           base64en = null ;  
           byteMing = null ;  
           byteMi = null ;  
       }  
       return strMi;  
    }  
   
    /** 
      * 解密 以 String 密文输入 ,String 明文输出 
      * 
      * @param strMi 
      * @return 
     * @throws UnsupportedEncodingException 
     * @throws InvalidKeyException 
      */  
    public String decryptStr(String strEncrypted) throws Exception {  
       //已加密的Base64字串  
     
       //先將Base64字串轉碼為byte[]  
       Base64 objBase64 = new Base64();  
       byte[] bysDecoded = objBase64.decode(strEncrypted.getBytes());  
     
       //建立解密所需的Key. 因為加密時的key是用ASCII轉換, 所以這邊也用ASCII做  
       DESKeySpec objDesKeySpec = new DESKeySpec(keyStr.getBytes("ASCII"));  
       SecretKeyFactory objKeyFactory = SecretKeyFactory.getInstance("DES");  
       SecretKey objSecretKey = objKeyFactory.generateSecret(objDesKeySpec);  
     
       //設定一個DES/ECB/NoPadding的Cipher  
       //ECB對應到.Net的CipherMode.ECB  
       //NoPadding對應到.Net的PaddingMode.None  
       Cipher objCipher = Cipher.getInstance("DES/ECB/NoPadding");  
       //設定為解密模式, 並設定解密的key  
       objCipher.init(Cipher.DECRYPT_MODE, objSecretKey);  
     
       //輸出解密後的字串. 因為加密是用UTF-8將字串轉為byte[], 所以這邊要用UTF-8轉回去  
       //注意後面會多一些空字元. 因為加密前有因為資料長度的關係補上一些空的bytes  
       //這邊用String的trim()將這些空字元刪掉  objCipher.
      String strDecrypted = new String(objCipher.doFinal(bysDecoded), "utf-8").trim();  
       System.out.println("[" + strDecrypted + "]");  
       return strDecrypted;
    }  
   
    /** 
      * 加密以 byte[] 明文输入 ,byte[] 密文输出 
      * 
      * @param byteS 
      * @return 
      */  
    private byte [] encryptByte( byte [] byteS) {  
       byte [] byteFina = null ;  
       Cipher cipher;  
       try {  
           cipher = Cipher.getInstance ("DES");  
           DESKeySpec objDesKeySpec = new DESKeySpec(keyStr.getBytes("ASCII"));  
           SecretKeyFactory objKeyFactory = SecretKeyFactory.getInstance("DES");
           SecretKey objSecretKey = objKeyFactory.generateSecret(objDesKeySpec);  
           cipher.init(Cipher.ENCRYPT_MODE,objSecretKey);  
           byteFina = cipher.doFinal(byteS);  
       } catch (Exception e) {  
           throw new RuntimeException("Error initializing SqlMap class. Cause: " + e);  
       } finally {  
           cipher = null ;  
       }  
       return byteFina;  
    }  
   
    /** 
      * 解密以 byte[] 密文输入 , 以 byte[] 明文输出 
      * 
      * @param byteD 
      * @return 
      */  
    private byte [] decryptByte( byte [] byteD) {  
       Cipher cipher;  
       byte [] byteFina = null ;  
       try {  
           cipher = Cipher.getInstance ("DES/ECB/NoPadding" );  
           DESKeySpec objDesKeySpec = new DESKeySpec(keyStr.getBytes("ASCII"));  
           SecretKeyFactory objKeyFactory = SecretKeyFactory.getInstance("DES");  
           SecretKey objSecretKey = objKeyFactory.generateSecret(objDesKeySpec);  
           cipher.init(Cipher. DECRYPT_MODE , objSecretKey );  
           byteFina = cipher.doFinal(byteD);  
       } catch (Exception e) {  
           throw new RuntimeException(  
                  "Error initializing SqlMap class. Cause: " + e);  
       } finally {  
           cipher = null ;  
       }  
       return byteFina;  
    }  
   
    /** 
      * 文件 file 进行加密并保存目标文件 destFile 中 
      * 
      * @param file 
      *             要加密的文件 如 c:/test/srcFile.txt 
      * @param destFile 
      *             加密后存放的文件名 如 c:/ 加密后文件 .txt 
      */  
    public void encryptFile(String file, String destFile) throws Exception {  
       Cipher cipher = Cipher.getInstance ( "DES" );  
       // cipher.init(Cipher.ENCRYPT_MODE, getKey());  
       cipher.init(Cipher. ENCRYPT_MODE , this . key );  
       InputStream is = new FileInputStream(file);  
       OutputStream out = new FileOutputStream(destFile);  
       CipherInputStream cis = new CipherInputStream(is, cipher);  
       byte [] buffer = new byte [1024];  
       int r;  
       while ((r = cis.read(buffer)) > 0) {  
           out.write(buffer, 0, r);  
       }  
       cis.close();  
       is.close();  
       out.close();  
    }  
   
    /** 
      * 文件采用 DES 算法解密文件 
      * 
      * @param file 
      *             已加密的文件 如 c:/ 加密后文件 .txt * 
      * @param destFile 
      *             解密后存放的文件名 如 c:/ test/ 解密后文件 .txt 
      */  
    public void decryptFile(String file, String dest) throws Exception {  
       Cipher cipher = Cipher.getInstance ( "DES" );  
       cipher.init(Cipher. DECRYPT_MODE , this . key );  
       InputStream is = new FileInputStream(file);  
       OutputStream out = new FileOutputStream(dest);  
       CipherOutputStream cos = new CipherOutputStream(out, cipher);  
       byte [] buffer = new byte [1024];  
       int r;  
       while ((r = is.read(buffer)) >= 0) {  
           cos.write(buffer, 0, r);  
       }  
       cos.close();  
       out.close();  
       is.close();  
    }  
    
    /**解密 
     * @param content  待解密内容 
     * @param password 解密密钥 
     * @return 
     */  
    public static byte[] decrypt(byte[] content, String password) {  
            try {  
                     //KeyGenerator kgen = KeyGenerator.getInstance("AES");  
                    // kgen.init(128, new SecureRandom(password.getBytes()));  
                     DESKeySpec objDesKeySpec = new DESKeySpec("cq*&@$#2".getBytes("ASCII"));  
                     SecretKeyFactory objKeyFactory = SecretKeyFactory.getInstance("DES");  
                     SecretKey objSecretKey = objKeyFactory.generateSecret(objDesKeySpec);  
                     Cipher cipher = Cipher.getInstance("DES");// 创建密码器  
                     Cipher objCipher = Cipher.getInstance("DES/ECB/NoPadding");  
                     objCipher.init(Cipher.DECRYPT_MODE, objSecretKey);// 初始化  
                    byte[] result = cipher.doFinal(content);  
                    return result; // 加密  
            } catch (Exception e) {  
                    e.printStackTrace();  
            }
            return null;  
    }  
    
    public static byte[] parseHexStr2Byte(String hexStr) {  
        if (hexStr.length() < 1)  
                return null;  
        byte[] result = new byte[hexStr.length()/2];  
        for (int i = 0;i< hexStr.length()/2; i++) {  
                int high = Integer.parseInt(hexStr.substring(i*2, i*2+1), 16);  
                int low = Integer.parseInt(hexStr.substring(i*2+1, i*2+2), 16);  
                result[i] = (byte) (high * 16 + low);  
        }  
        return result;  
}  

  public static String  strNew(String str){
	  int len = str.length();
      System.out.println(len);
      if(len<48){
   	   for(int i=0;i<48-str.length();i++){
   		   str = '1'+str;
   		   
   	   }
      }else if(48<len && len<56){
   	   for(int i=0;i<56-len;i++){
   		   str = '1'+str;
   	   }
   	   
      }
      return str;
   }
    
    public static void main(String[] args) throws Exception {  
       DESUtil des = new DESUtil("cq*&@$#2");  
       System.out.println("-------------------------");
       // DES 加密文件  
       // des.encryptFile("G:/test.doc", "G:/ 加密 test.doc");  
       // DES 解密文件  
       // des.decryptFile("G:/ 加密 test.doc", "G:/ 解密 test.doc");  
       String str1 = "xz_cq-1409215143114-127001-622425198910023838".trim() ; 
       int len = str1.length();
       System.out.println(len+"*******************");
       str1=  DESUtil.strNew(str1);
       System.out.println(str1.length());
       // DES 加密字符串  
       String str2 = des.encryptStr(str1);  
      // byte[] decryptResult = des.decrypt(parseHexStr2Byte("9jVPi50FEn8LOK9LtIMExeK2F/AUWusvpefVci9GVRce+ULYqliHbbmKOnPT+OWv"),"cq*&@$#2");  
      //ystem.out.println("解密后：" + new String(decryptResult));  
       // DES 解密字符串  
       System.out.println(str2.length());
      String deStr = des.decryptStr(str2);  
       System. out .println( " 加密前： " + str1);  
       System. out .println( " 加密后： " + str2);  
       System. out .println( " 解密后： " + deStr);  
    }  
} 
