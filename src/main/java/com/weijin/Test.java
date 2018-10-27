package com.weijin;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.weijin.WordTemp.WorkData;
/**
 * 测试
 * @author liuweijin
 *
 */
public class Test {

	public static void main(String[] arr) throws Exception {
		String tempPath = "D:\\MyTempData\\TestData\\test.docx";
		WorkData data=new WorkData();
		//-------数据填充---------	
		for(int i=0;i<=22;i++)
		{
			data.setFiled("p"+(i+1), "testText_"+(i+1));
		}		
		List<String> ls=new ArrayList<String>();
		ls.add("ls1");
		ls.add("ls2");
		ls.add("ls3");
		ls.add("ls4");
		data.setFiled("ls", ls);
		
		TestModel obj=new TestModel();
		obj.setName("Object Test");
		TestItem item1=new TestItem();
		item1.setKey("item1");
		item1.setVal("itemValue1");
		obj.getVals().add(item1);
		
		TestItem item2=new TestItem();
		item2.setKey("item2");
		item2.setVal("itemValue2");
		obj.getVals().add(item2);
		data.setFiled("obj", obj);
		data.setImpPath(2, "D:\\MyTempData\\TestData\\test2.jpg");
		//-----------------------
		
		String outPath = "D:\\MyTempData\\TestData\\test7.doc";
		WordTemp wmg=new WordTemp(tempPath);
		wmg.reanderAndSave(outPath, data);
		
		System.out.println("生成文档成功");
	}
	
	
	@SuppressWarnings("serial")
	public static class TestModel implements Serializable 
	{
		private String name;
		private List<TestItem> vals=new ArrayList<TestItem>();
		
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public List<TestItem> getVals() {
			return vals;
		}
		public void setVals(List<TestItem> vals) {
			this.vals = vals;
		}
		
	}
	@SuppressWarnings("serial")
	public static class TestItem implements Serializable 
	{
		private String key;
		private String val;
		public String getKey() {
			return key;
		}
		public void setKey(String key) {
			this.key = key;
		}
		public String getVal() {
			return val;
		}
		public void setVal(String val) {
			this.val = val;
		}
		
	}
	
}
