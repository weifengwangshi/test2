package com.basefrm.util;

import net.sourceforge.pinyin4j.PinyinHelper;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.sql.Blob;
import java.util.*;


public class Utils {

	public static final String[] getKeys(Map<String, String> map) {
		String keys[] = new String[map.size()];
		int i = 0;
		for (Iterator iter = map.entrySet().iterator(); iter.hasNext();) {
			Map.Entry entry = (Map.Entry) iter.next();
			String key = (String) entry.getKey();
			keys[i] = key;
			i++;

		}

		return keys;
	}

	public static boolean isNotEmpty(String s) {
		return !isEmpty(s);
	}

	public static boolean isNotEmpty(Collection<?> c) {
		return !isEmpty(c);
	}

	public static boolean isNotEmpty(Map<?, ?> map) {
		return !isEmpty(map);
	}

	public static boolean isEmpty(String s) {
		return s == null || s.trim().length() == 0 || s.equals("null");
	}

	public static boolean isEmpty(Collection<?> c) {
		return c == null || c.isEmpty();
	}

	public static boolean isEmpty(Map<?, ?> map) {
		return map == null || map.isEmpty();
	}

	public static <T> boolean contains(final T[] array, final T v) {
		for (final T e : array)
			if (e == v || v != null && v.equals(e))
				return true;
		return false;
	}
	public static String isContentSameKey(String[] s1,String[] s2){  
		Arrays.sort(s1);
		String sfz="";
		for(int i=0;i<s2.length;i++){
			if(Arrays.binarySearch(s1, s2[i])>=0)

				return sfz = s2[i];;
			}
			return sfz;
		}

	public static String isContentSameKey(List<String> list,String[] s2){
		String[] s1 = new String [list.size()];
		for (int i = 0; i < s1.length; i++) {
			s1[i] = list.get(i).split("-")[0];
		}
		Arrays.sort(s1);
		String sfz="";
		for(int i=0;i<s2.length;i++){
			if(Arrays.binarySearch(s1, s2[i])>=0)
				
				return sfz = s2[i];;  
			}  
			return sfz; 
		}

	public static boolean ObjectIsEmpty(Object obj) {
		if (obj == null) {
			return true;
		} else if (obj instanceof String && (obj.equals(""))) {
			return true;
		} else if (obj instanceof Boolean && !((Boolean) obj)){
			return true;
		} else if (obj instanceof Collection && ((Collection) obj).isEmpty()){
			return true;
		} else if (obj instanceof Map && ((Map) obj).isEmpty()) {
			return true;
		} else if (obj instanceof Object[] && ((Object[]) obj).length == 0) {
			return true;
		} else {
			return false;
		}
	}

	// 返回中文的首字母
	public static String getPinYinHeadChar(String str) {

		String convert = "";
		for (int j = 0; j < str.length(); j++) {
			char word = str.charAt(j);
			String[] pinyinArray = PinyinHelper.toHanyuPinyinStringArray(word);
			if (pinyinArray != null) {
				convert += pinyinArray[0].charAt(0);
			} else {
				convert += word;
			}
		}
		return convert;
	}

	public static String getRandNum() {
		String randNum = "";
		for(int i=0;i<6;i++){
			int randNo = (int) (Math.random()*9+1);
			randNum += randNo;
		}
		return randNum;
	}

	// 将utf-8转换为中文字符
	public static String decodeUTF8(String str) {
		String xmldoc = "";
		try {
			xmldoc = URLDecoder.decode(str, "utf-8");
		} catch (UnsupportedEncodingException ex) {
			xmldoc = ex.toString();
		}
		return xmldoc;
	}
	// 将中文字符转换为utf-8
	public static String encodeUTF8(String xmlDoc) {
		String str = "";
		try {
			str = URLEncoder.encode(xmlDoc, "utf-8");
		} catch (UnsupportedEncodingException ex) {
			str = ex.toString();
		}
		return str;
	}

	/*向String数组添加一个值*/
	public static String[] insertElement(String original[],String element, int index) {
		int length = original.length;
		String destination[] = new String[length+1];
		System.arraycopy(original, 0, destination, 0, index);
		destination[index] = element;
		System.arraycopy(original, index, destination, index + 1, length - index);
		return destination;
	}

	/**
	 * 获取字符串的长度，如果有中文，则每个中文字符计为2位
	 *
	 * @param value 指定的字符串
	 * @return 字符串的长度
	 */
	public static int getStrLength(String value) {
		int valueLength = 0;
		String chinese = "[\u0391-\uFFE5]";
		/* 获取字段值的长度，如果含中文字符，则每个中文字符长度为2，否则为1 */
		for (int i = 0; i < value.length(); i++) {
			/* 获取一个字符 */
			String temp = value.substring(i, i + 1);
			/* 判断是否为中文字符 */
			if (temp.matches(chinese)) {
				/* 中文字符长度为2 */
				valueLength += 2;
			} else {
				/* 其他字符长度为1 */
				valueLength += 1;
			}
		}
		return valueLength;
	}

	public static String blobToString(Blob blob) {
		InputStream is = null;
		byte[] b = null;
		try {
			is = blob.getBinaryStream();
			b = new byte[(int) blob.length()];
			is.read(b);
			return new String(b);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				is.close();
				is = null;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return new String(b);
	}

	public static void main(String[] args) {
		String uuid = UUID.randomUUID().toString().replaceAll("-","");
		System.out.println(uuid.length());
	}
}
