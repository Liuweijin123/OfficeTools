package com.weijin.utils;

import java.util.ArrayList;
import java.util.List;

public class StringUtil {

	public static List<String> splitToSection(String content, String preMark, String sufMark) {
		List<String> list = new ArrayList<String>();
		char[] cArr = stringToChars(content), pArr = stringToChars(preMark), sArr = stringToChars(sufMark);
		char[] mArr = pArr;
		StringBuilder sb = new StringBuilder();
		for1: for (int i = 0; i < cArr.length; i++) {
			for (int j = 0; j < mArr.length; j++) {
				if (cArr[i + j] != mArr[j])
					break;
				if (j == mArr.length - 1) {
					if (mArr == pArr) {
						mArr = sArr;
						if (sb.length() > 0) {
							list.add(sb.toString());
							sb = new StringBuilder();
						}
						sb.append(preMark);
					} else {
						mArr = pArr;
						sb.append(sufMark);
						list.add(sb.toString());
						sb = new StringBuilder();
					}
					i += j;
					continue for1;
				}
			}
			sb.append((char) cArr[i]);
		}
		if (sb.length() > 0)
			list.add(sb.toString());
		return list;
	}
	
	private static char[] stringToChars(String str) {
		char[] cArr = new char[str.length()];
		str.getChars(0, str.length(), cArr, 0);
		return cArr;
	}
}
