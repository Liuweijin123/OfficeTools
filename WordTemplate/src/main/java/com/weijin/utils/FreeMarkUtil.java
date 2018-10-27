package com.weijin.utils;


import java.io.StringWriter;

import freemarker.cache.StringTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;

/**
 * freemark模板渲染辅助类
 * @author liuweijin
 *
 */
public class FreeMarkUtil {

	public static String renderTamplate(String tample, Object model) throws Exception {

		StringWriter writer = new StringWriter();

		Configuration configuration = new Configuration(Configuration.getVersion());
		StringTemplateLoader stringLoader = new StringTemplateLoader();
		stringLoader.putTemplate("temp", tample);
		configuration.setTemplateLoader(stringLoader);

		Template temp = configuration.getTemplate("temp", "utf-8");
		temp.process(model, writer);

		return writer.toString();
	}

}
