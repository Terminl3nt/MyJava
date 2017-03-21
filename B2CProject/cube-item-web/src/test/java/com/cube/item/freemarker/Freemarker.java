package com.cube.item.freemarker;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import freemarker.template.Configuration;
import freemarker.template.Template;

public class Freemarker {

	@Test
	public void freemarkerTest() throws Exception{
//		第一步：创建一个Configuration对象，直接new一个对象。构造方法的参数就是freemarker对于的版本号。
		Configuration configuration = new Configuration(Configuration.getVersion());
//		第二步：设置模板文件所在的路径。
		configuration.setDirectoryForTemplateLoading(new File("D:/ECube/cube-item-web/src/main/webapp/WEB-INF/ftl/"));
//		第三步：设置模板文件使用的字符集。一般就是utf-8.
		configuration.setDefaultEncoding("utf-8");
//		第四步：加载一个模板，创建一个模板对象。
		Template template = configuration.getTemplate("hello.ftl");
//		第五步：创建一个模板使用的数据集，可以是pojo也可以是map。一般是Map。
		Map dateSource = new HashMap();
//		dateSource.put("hello", "this is first Freemarker Object File Fuck you Everyone ");
//		Student stu = new Student("1", "zs", "3", "hk");
		List<Student> list = new ArrayList<>();
		list.add(new Student("1", "qq2", "323", "we"));
		list.add(new Student("2", "qq2", "343", "wae"));
		list.add(new Student("23", "qq3", "313", "wse"));
		list.add(new Student("14", "qq4", "313", "wde"));
		dateSource.put("stu", list);
//		第六步：创建一个Writer对象，一般创建一FileWriter对象，指定生成的文件名。
		Writer out = new FileWriter(new File("D:/就业班/宜立方/02.教案-AB-3.0/hello.txt"));
//		第七步：调用模板对象的process方法输出文件。
		template.process(dateSource, out);
//		第八步：关闭流。
		out.close();

	}
}
