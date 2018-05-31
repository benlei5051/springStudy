package util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Base64;

public class Base64Util {

	private static Logger logger = LoggerFactory.getLogger(Base64Util.class);

	public static void main(String[] args) {
		String strImg = getNetImageStr("d:/mno/2.jpg");
		System.out.println(strImg);
	}



	// 图片转化成base64字符串
		public static String getNetImageStr(String address) {// 将图片文件转化为字节数组字符串，并对其进行Base64编码处理
			String imageStr = null;
			InputStream in = null;
			HttpURLConnection conn = null;
			try {
				URL url = new URL(address);
				//打开链接
				conn = (HttpURLConnection)url.openConnection();
				//设置请求方式为"GET"
				conn.setRequestMethod("GET");
				//超时响应时间为5秒
				conn.setConnectTimeout(5 * 1000);
				//通过输入流获取图片数据
				in = conn.getInputStream();
				byte[] data = new byte[in.available()];
				in.read(data);
				imageStr = Base64.getEncoder().encodeToString(data);
			} catch (IOException e) {
				logger.error("getImageStr exception:{}", e);
			} finally {
				if (in != null) {
					try {
						in.close();
						conn.disconnect();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
			// 对字节数组Base64编码
			return imageStr;// 返回Base64编码过的字节数组字符串
		}

	// 图片转化成base64字符串
	public static String getImageStr(String filepath) {// 将图片文件转化为字节数组字符串，并对其进行Base64编码处理
		String imageStr = null;
		InputStream in = null;
		try {
			in = new FileInputStream(filepath);
			byte[] data = new byte[in.available()];
			in.read(data);
			in.close();
			imageStr = Base64.getEncoder().encodeToString(data);
		} catch (IOException e) {
			logger.error("getImageStr exception:{}", e);
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		// 对字节数组Base64编码
		return imageStr;// 返回Base64编码过的字节数组字符串
	}

	// base64字符串转化成图片
	public static boolean generateImage(String imgStr, String destpath) { // 对字节数组字符串进行Base64解码并生成图片
		if (imgStr == null) // 图像数据为空
			return false;
		OutputStream out = null;
		try {
			// Base64解码
			byte[] b = Base64.getDecoder().decode(imgStr);
			for (int i = 0; i < b.length; ++i) {
				if (b[i] < 0) {// 调整异常数据
					b[i] += 256;
				}
			}
			// 生成jpeg图片
			out = new FileOutputStream(destpath);
			out.write(b);
			out.flush();
			return true;
		} catch (Exception e) {
			return false;
		} finally {
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
					logger.error("generateImage exception:{}", e);
				}
			}
		}
	}
}
