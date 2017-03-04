package com.yu.demo.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class FileUtil {
	
	static private String DEFAULT_RESULT_FOLDER_NAME = "result";
	static private String RESULTFOLDER_PROP = "chk.result.folder";
	static private String EXPECT_PROP = "chk.expect"; 
	static private String REAL_PROP = "chk.real";
	static private String RECORD_PROP = "chk.record";
	static private PropertyPlaceholderConfigurer config;
	
//	static {
//		@SuppressWarnings("resource")
//		ApplicationContext ctx = new ClassPathXmlApplicationContext("spring-config.xml");
//		config = (PropertyPlaceholderConfigurer) ctx.getBean("propertyConfigurer");
//	}
	
	
	static private String EXPECT_FOLDER_NAME = SpringPropertyResourceReader.getProperty(EXPECT_PROP);
	static private String REAL_FOLDER_NAME = SpringPropertyResourceReader.getProperty(REAL_PROP); //"real";
	static private String RECORD_FOLDER_NAME = SpringPropertyResourceReader.getProperty(RECORD_PROP); //"_RECORD_";
	static private String ENCODE = "utf-8";
	
	// 读取指定文本文件
	public static String read(File f) {  
        StringBuilder str = new StringBuilder();  
        BufferedReader in = null;  
        try {   
        	in = new BufferedReader(new InputStreamReader
        			(new FileInputStream(f), ENCODE));
            String s;  
            try {  
                while ((s = in.readLine()) != null)  
                    str.append(s.trim());  // + '\n'
            } finally {  
                in.close();  
            }  
        } catch (IOException e) {   
            e.printStackTrace();  
        }  
        return str.toString();  
    } 
	
	// 写入指定的文本文件，append为true表示追加，false表示重头开始写，  
    //text是要写入的文本字符串，text为null时直接返回  
    public static void write(File f, boolean append, String text) {  
        if (text == null)  
            return;  
        try {  
        	BufferedWriter out = 
        			new BufferedWriter( new OutputStreamWriter
        					(new FileOutputStream(f), ENCODE));
            try {  
                out.write(text);  
            } finally {  
                out.close();  
            }  
        } catch (IOException e) {   
            e.printStackTrace();  
        }  
    }  
	
	private static String convertApi(String api) {
		return api.replaceAll("/", " ").trim().replaceAll(" ", "-");
	}
	
	private static File makeFile(File directory, String eor, String api, String casename) {
		File path = null;
		String apiFolder = convertApi(api);
		if(directory != null) {
			path = new File(new File(directory, eor), apiFolder);
		} else {
			path = new File(new File(new File("result"), eor), apiFolder);
		}
		
		if(!path.exists() && !path.isDirectory()) {
			path.mkdirs();
		}
		File f = new File(path, casename);
		return f;
	}
	
	/**
	 * 
	 * @param eor - expect or real
	 * @param api - api name
	 * @param casename
	 * @return
	 */
	private static File eorFile(String eor, String api, String casename) {
		String parent = "";
		if(SpringPropertyResourceReader.containsProperty(RESULTFOLDER_PROP) && 
			(SpringPropertyResourceReader.getProperty(RESULTFOLDER_PROP).trim().length()>0)) {
			parent = SpringPropertyResourceReader.getProperty(RESULTFOLDER_PROP).trim();
		} else {
			parent = DEFAULT_RESULT_FOLDER_NAME;
		}
//		System.out.println(parent + ":" + eor + ":" + api + ":" + casename);
		return makeFile(new File(parent), eor, api, casename); //"D:/temp/result"
	}
	
	public static File expectFile(String api, String casename) {
		return eorFile(EXPECT_FOLDER_NAME, api, casename);
	}
	
	public static File realFile(String api, String casename) {
		return eorFile(REAL_FOLDER_NAME, api, casename);
	}
	
	public static File recordFile(String api, String casename) {
		return eorFile(RECORD_FOLDER_NAME, api, casename);
	}
	
	public static void writeExpect(String api, String casename, String json) {
		write(expectFile(api, casename), false, json);
	}
	
	public static String readExpect(String api, String casename) {
		return read(expectFile(api, casename));
	}
	
	public static void writeReal(String api, String casename, String json) {
		write(realFile(api, casename), false, json);
	}
	
	public static String readReal(String api, String casename) {
		return read(realFile(api, casename));
	}
	
	public static void writeRecord(String api, String casename, String json) {
		write(recordFile(api, casename), false, json);
	}
	
	public static String readRecord(String api, String casename) {
		return read(recordFile(api, casename));
	}
	
	private static void writeDemo() {
		String api = "/prices/get";
		
		//File f = makeFile(new File("D:/temp/result"), "expect", api, "casename");
		File f1 = expectFile(api, "casename");
		String s1 = "{'avBar':[{'barAge':-1442278496,'barDate':'2016-12-22 14:12:11','barName':'sss_0.7553178'},{'barAge':-46028450,'barDate':'2016-12-22 14:12:11','barName':'sss_0.9416827'}],'avString':['aaa','bbb','ccc'],'avboolean':[true,false,true,true],'avint':[1,2,3,4],'bar':{'barAge':2051276180,'barDate':'2016-12-22 14:12:11','barName':'sss_0.8763819'},'dddd':'2016-12-22 14:12:11','listBar':[{'barAge':385060738,'barDate':'2016-12-22 14:12:11','barName':'sss_0.14227659'},{'barAge':698120777,'barDate':'2016-12-22 14:12:11','barName':'sss_0.8042798'},{'barAge':171325217,'barDate':'2016-12-22 14:12:11','barName':'sss_0.63253206'}],'listString':['listString1','listString2','listString3'],'map':{'x':'s11111x','y':'s22222y','z':'s33333z'},'vDate':'2016-12-22 14:12:11','vString':'vStringhehhehe','v_Date':'2016-12-22 14:12:11','vboolean':false,'vbyte':64,'vchar':'x','vdouble':22.203,'vfloat':12.1,'vint':65535,'vlong':9999999,'vshort':128}";
		write(f1, false, s1);
		
		File f2 = expectFile(api, "casename2");
		String s2 = "[{'p':'中文','m':'859.00','id':'J_954086','op':'459.00'}]";
		write(f2, false, s2);
	}
	
	private static void readDemo() {
		String api = "/prices/get";
		
		File f1 = expectFile(api, "casename");
		String s1 = read(f1);
		System.out.println(s1);
		
		File f2 = expectFile(api, "casename2");
		String s2 = read(f2);
		System.out.println(s2);
	}

	public static void main(String[] args) {
		writeDemo();
//		readDemo();
	}

}
