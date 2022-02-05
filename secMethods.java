            package lock;
            import javax.crypto.Cipher;
            import javax.crypto.spec.SecretKeySpec;
            import javax.swing.*;
            import java.io.File;
            import java.io.FileInputStream;
            import java.io.FileOutputStream;
            import java.nio.charset.StandardCharsets;
            import java.security.Key;
            
            public class SecMethods {
                public void lockFile(String ALGORITHM  , String TRANSFORMTION, String key, File filein, File fileout){
                    actionType(ALGORITHM,TRANSFORMTION,Cipher.ENCRYPT_MODE,key,filein,fileout);
                }
                public void unlockFile(String ALGORITHM, String TRANSFORMTION, String key, File in, File out){
                    actionType(ALGORITHM,TRANSFORMTION,Cipher.DECRYPT_MODE,key,in,out);
                }
                public void actionType(String ALGORITHM, String TRANSFORMTION,
                                       int cMode, String key, File filein , File fileout){
            
                    try {
                        Key secretkey=new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8),ALGORITHM);
                        Cipher cipher=Cipher.getInstance(TRANSFORMTION);
                        cipher.init(cMode,secretkey);
                        FileInputStream inputStream=new FileInputStream(filein);
                        byte[]bytes=new byte[(int) filein.length()];
                        inputStream.read(bytes);
                        byte []acted= cipher.doFinal(bytes);
                        FileOutputStream outputStream=new FileOutputStream(fileout);
                        outputStream.write(acted);
                        inputStream.close();
                        outputStream.close();
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null,e.getMessage());
                        return;
                    }
                }
            }
