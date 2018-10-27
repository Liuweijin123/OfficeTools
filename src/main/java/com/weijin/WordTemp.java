package com.weijin;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.weijin.utils.FreeMarkUtil;
import com.weijin.utils.ImgUtil;
import com.weijin.utils.StringUtil;
import com.weijin.utils.ZipUtil;
import com.weijin.utils.ZipUtil.ZipEntryData;

/**
 * word文档生成
 * 
 * @author liuweijin
 *
 */
public class WordTemp {
	private String mediaPath = "word/media";
	private String docPath = "word/document.xml";
	private String tempStr;
	private Map<String, ZipEntryData> datas;

	public String getTemplateString() {
		return this.tempStr;
	}

	public WordTemp(String wordPath) throws Exception {

		this.datas = ZipUtil.readZipFile(wordPath);
	}

	public void reanderAndSave(String savePath, WorkData data) throws Exception {
		ZipEntryData docZED = this.datas.get(docPath);
		String temp = getTamplate(docZED.toString());
		String content = FreeMarkUtil.renderTamplate(temp, data.fieldHolds);
		docZED.setBites(content.getBytes());
		int imgIndex = 1;
		for (Entry<String, ZipEntryData> item : datas.entrySet()) {
			if (item.getKey().startsWith(mediaPath)) {
				if (data.imgHolds.containsKey(imgIndex))
					item.getValue().setBites(ImgUtil.readFile(data.imgHolds.get(imgIndex)));
				imgIndex++;
			}
		}
		ZipUtil.writeZipFile(savePath, this.datas);
	}

	private String getTamplate(String docContent) {
		StringBuilder sb = new StringBuilder();
		for (String str : StringUtil.splitToSection(docContent, "{", "}")) {

			if (str.startsWith("{") && str.endsWith("}")) {
				str = initKeyhold(str);
				if (str.startsWith("{$")) {
					str = str.replace("{$", "${");
				} else if ((str.startsWith("{#") || str.startsWith("{/#")) && str.endsWith("}")) {
					str = str.replace("{#", "<#").replace("{/#", "</#").replaceAll("}", ">");
				}
			}
			sb.append(str);
		}
		return sb.toString();
	}

	private static String initKeyhold(String str) {
		List<String> list = StringUtil.splitToSection(str, "<", ">");
		StringBuilder sb = new StringBuilder();
		for (String s : list) {
			if (!s.startsWith("<"))
				sb.append(s);
		}
		return sb.toString();

	}

	public static class WorkData {
		private Map<String, Object> fieldHolds = new HashMap<String, Object>();
		private Map<Integer, String> imgHolds = new HashMap<Integer, String>();

		public WorkData setFiled(String name, Object data) {
			this.fieldHolds.put(name, data);
			return this;
		}

		public WorkData setImpPath(Integer holdIndex, String imgPath) {
			imgHolds.put(holdIndex, imgPath);
			return this;
		}
	}
}
