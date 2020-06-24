package com.cy.shop.common.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

import sun.misc.BASE64Decoder;
/**将图片字符串转换成图片写入磁盘*/
public class Base64ToImage {
	public Base64ToImage() {
	}
	
	public boolean base64ToImage(String base64, String imgFilePath) {
        // 对字节数组字符串进行Base64解码并生成图片
        if (base64 == null) { // 图像数据为空
            return false;
        }
        BASE64Decoder decoder = new BASE64Decoder();
        try {
            // Base64解码
            byte[] b = decoder.decodeBuffer(base64);
            for (int i = 0; i < b.length; ++i) {
                if (b[i] < 0) {// 调整异常数据
                    b[i] += 256;
                }
            }
            File file=new File(imgFilePath);
            file.createNewFile();
            System.out.println(file.isFile());
            OutputStream out = new FileOutputStream(file);
            out.write(b);
            out.flush();
            out.close();
            return true;
        } catch (Exception e) {
        	e.getStackTrace();
            return false;
        }
    }
}
