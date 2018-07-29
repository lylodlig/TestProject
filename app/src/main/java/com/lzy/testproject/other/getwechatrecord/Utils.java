package com.lzy.testproject.other.getwechatrecord;

import android.content.Context;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;

import org.dom4j.io.SAXReader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

/**
 * Created by lzy on 2018/7/28.
 */
public class Utils {

	private static final String WX_SP_UIN_PATH = "/data/data/com.tencent.mm/shared_prefs/auth_info_key_prefs.xml";

	/**
	 * 获取手机IMEI
	 *
	 * @param context
	 * @return
	 */
	public static final String getIMEI(Context context) {
		try {
			//实例化TelephonyManager对象
			TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
			//获取IMEI号
			String imei = telephonyManager.getDeviceId();
			//在次做个验证，也不是什么时候都能获取到的啊
			if (imei == null) {
				imei = "";
			}
			return imei;
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}

	}

	/**
	 * 复制单个文件
	 *
	 * @param oldPath String 原文件路径 如：c:/fqf.txt
	 * @param newPath String 复制后路径 如：f:/fqf.txt
	 * @return boolean
	 */
	public static boolean copyFile(String oldPath, String newPath) {
		deleteFolderFile(newPath, true);
		Log.e("copyFile", "time_1:" + System.currentTimeMillis());
		InputStream inStream = null;
		FileOutputStream fs = null;
		try {
			int bytesum = 0;
			int byteread = 0;
			File oldfile = new File(oldPath);
			Boolean flag = oldfile.exists();
			Log.e("copyFile", "flag:" + flag);
			if (oldfile.exists()) { //文件存在时
				inStream = new FileInputStream(oldPath); //读入原文件
				fs = new FileOutputStream(newPath);
				byte[] buffer = new byte[2048];
				while ((byteread = inStream.read(buffer)) != -1) {
					bytesum += byteread; //字节数 文件大小
					fs.write(buffer, 0, byteread);
				}
				Log.e("copyFile", "time_2:" + System.currentTimeMillis());
			}
		} catch (Exception e) {
			System.out.println("复制单个文件操作出错");
			e.printStackTrace();
		} finally {
			try {
				if (inStream != null) {
					inStream.close();
				}
				if (fs != null) {
					fs.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return true;
	}

	/**
	 * 删除单个文件
	 *
	 * @param filepath
	 * @param deleteThisPath
	 */
	public static void deleteFolderFile(String filepath, boolean deleteThisPath) {
		if (!TextUtils.isEmpty(filepath)) {
			try {
				File file = new File(filepath);
				if (file.isDirectory()) {
					//处理目录
					File files[] = file.listFiles();
					for (int i = 0; i < file.length(); i++) {
						deleteFolderFile(files[i].getAbsolutePath(), true);
					}
				}
				if (deleteThisPath) {
					if (!file.isDirectory()) {
						//删除文件
						file.delete();
					} else {
						//删除目录
						if (file.listFiles().length == 0) {
							file.delete();
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static char HEX_DIGITS[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
			'A', 'B', 'C', 'D', 'E', 'F'};

	//将字符串转化为位
	public static String toHexString(byte[] b) {
		StringBuilder stringBuilder = new StringBuilder(b.length * 2);
		for (int i = 0; i < b.length; i++) {
			stringBuilder.append(HEX_DIGITS[(b[i] & 0xf0) >>> 4]);
			stringBuilder.append(HEX_DIGITS[b[i] & 0x0f]);
		}
		return stringBuilder.toString();
	}

	public static String md5(String string) {
		try {
			MessageDigest digest = java.security.MessageDigest.getInstance("MD5");
			digest.update(string.getBytes());
			byte messageDigest[] = digest.digest();
			return toHexString(messageDigest);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return "";
	}

	/**
	 * <?xml version='1.0' encoding='utf-8' standalone='yes' ?>
	 * <map>
	 * <int name="_auth_uin" value="669000982" />
	 * <boolean name="key_auth_info_prefs_created" value="true" />
	 * <int name="key_auth_update_version" value="637929273" />
	 * <string name="server_id">96021802000000005c46b7d0f95800</string>
	 * <string
	 * name="_auth_key
	 * ">0a14081012100f105f7790ad4774594ffbefa2e65463125f085b125b08ae011250c7e92b53fade0cfbe53cbf0e70ffcc24613cd20f1816beb7409af4654bae7d2f02140892f4707985dfba1689bf76773eea289c3de12c3b052a4f9d4ef2a520bc1438b353c026f50c427eb684235d592018fa87899c02</string>
	 * </map>
	 * 获取微信的uid
	 * 微信的uid存储在SharedPreferences里面
	 * 存储位置\data\data\com.tencent.mm\shared_prefs\auth_info_key_prefs.xml
	 */
	public static String parseXmlForUin() throws Exception {
		String mCurrWxUin = null;
		File file = new File(WX_SP_UIN_PATH);

		FileInputStream in = new FileInputStream(file);
		SAXReader saxReader = new SAXReader();
		org.dom4j.Document document = saxReader.read(in);
		org.dom4j.Element root = document.getRootElement();
		List<org.dom4j.Element> elements = root.elements();
		for (org.dom4j.Element element : elements) {
			if ("_auth_uin".equals(element.attributeValue("name"))) {
				mCurrWxUin = element.attributeValue("value");
			}
		}
		return mCurrWxUin;
	}
}
