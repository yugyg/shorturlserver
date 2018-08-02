package com.yugyg.shorturlserver;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.mybatis.generator.api.GeneratedJavaFile;
import org.mybatis.generator.api.GeneratedXmlFile;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.exception.InvalidConfigurationException;
import org.mybatis.generator.exception.XMLParserException;
import org.mybatis.generator.internal.DefaultShellCallback;



/**
 * 用于MyBatis Generator来生成DAO类 
 * @author Administrator
 * 
 *
 */
public class RunGenerator 
{
  public static void main(String[] args) 
  {
	    List<String> warnings = new ArrayList<String>();
		boolean overwrite = true;
		InputStream configInput = RunGenerator.class.getClassLoader().getSystemResourceAsStream("generatorConfig.xml");
		System.out.println("==============开始读取generatorConfig.xml配置文件==============");
		
		ConfigurationParser cp = new ConfigurationParser(warnings);
		try {
			Configuration	config = cp.parseConfiguration(configInput);
			DefaultShellCallback callback = new DefaultShellCallback(overwrite);
			MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
			long begin = System.currentTimeMillis();
			myBatisGenerator.generate(null);
			long end = System.currentTimeMillis();
			System.out.println("耗费时间:(毫秒):"+(end-begin));
			List<GeneratedJavaFile> javaFiles = myBatisGenerator.getGeneratedJavaFiles();
			System.out.println("生成java类文件数量:"+javaFiles.size());
			List<GeneratedXmlFile> xmlFiles = myBatisGenerator.getGeneratedXmlFiles();
			System.out.println("生成sqlmap映射文件数量:"+xmlFiles.size());
			System.out.println("==============生成sqlmap文件成功==============");
			for (String s:warnings) {				
				System.out.println("警告:"+s);
			}
		} catch (IOException e) {
			System.out.println("==============读取文件失败==============");
			e.printStackTrace();
		} catch (XMLParserException e) {
			System.out.println("==============读取文件失败==============");
			e.printStackTrace();
		} catch (InvalidConfigurationException e) {
			System.out.println(""+e);
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println(""+e);
			e.printStackTrace();
		} catch (InterruptedException e) {
			System.out.println(""+e);
			e.printStackTrace();
		}
  } 
}
