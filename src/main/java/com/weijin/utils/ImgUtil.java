package com.weijin.utils;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import org.apache.commons.codec.binary.Base64;

public class ImgUtil {
	
	/*public static void main(String [] arr)
	{
		
		System.out.println(getBase64ImageStr("D:\\MyTempData\\TestData\\test.png"));
	}*/

	/**
	 * 该方法用来将指定的文件转换成base64编码
	 * 
	 * @param path:图片路径
	 **/
	public static String getBase64ImageStr(String path) {
		// 1、校验是否为空
		if (path == null || path.trim().length() <= 0) {
			return "";
		}

		// 2、校验文件是否为目录或者是否存在
		File picFile = new File(path);
		if (picFile.isDirectory() || (!picFile.exists()))
			return "";

		// 3、校验是否为图片
		try {
			BufferedImage image = ImageIO.read(picFile);
			if (image == null) {
				return "";
			}
		} catch (IOException ex) {
			ex.printStackTrace();
			return "";
		}

		// 4、转换成base64编码
		String imageStr = "";
		try {
			byte[] data = null;
			@SuppressWarnings("resource")
			InputStream in = new FileInputStream(path);
			data = new byte[in.available()];
			in.read(data);
			imageStr = Base64.encodeBase64String(data);
		} catch (Exception e) {
			imageStr = "";
			e.printStackTrace();
		}

		return imageStr;
	}
	
	public static byte[] readFile(String path) throws IOException
	{
		byte [] bs=null;
		try(FileInputStream  in=new FileInputStream(path))
		{
			bs=new byte[in.available()];
			in.read(bs);
		}
		return bs;
	}

}
