package com.weijin.utils;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class ZipUtil {

	public static Map<String, ZipEntryData> readZipFile(String zipPath) throws Exception {
		Map<String, ZipEntryData> zipDatas = new HashMap<String, ZipEntryData>();
		@SuppressWarnings("resource")
		ZipFile zf = new ZipFile(zipPath);
		InputStream in = new BufferedInputStream(new FileInputStream(zipPath));
		@SuppressWarnings("resource")
		ZipInputStream zin = new ZipInputStream(in);

		ZipEntry ze;
		while ((ze = zin.getNextEntry()) != null) {
			try (InputStream ins = zf.getInputStream(ze)) {
				byte[] bs = new byte[(int) ze.getSize()];
				ins.read(bs);
				ZipEntryData zed = new ZipEntryData();
				zed.setBites(bs);
				zipDatas.put(ze.getName(), zed);
			}
		}
		zin.closeEntry();
		return zipDatas;
	}

	public static void writeZipFile(String fileName, Map<String, ZipEntryData> data) throws IOException {
		File file = new File(Paths.get(fileName).toString());
		try (ZipOutputStream out = new ZipOutputStream(new FileOutputStream(file))) {
			for (Entry<String, ZipEntryData> item : data.entrySet()) {
				out.putNextEntry(new ZipEntry(item.getKey()));
				out.write(item.getValue().getBites());
			}
		}
	}


	public static class ZipEntryData {
		private byte[] bites;

		public byte[] getBites() {
			return bites;
		}

		public void setBites(byte[] bites) {
			this.bites = bites;
		}

		public String toString() {
			return new String(this.bites);
		}
	}

}
