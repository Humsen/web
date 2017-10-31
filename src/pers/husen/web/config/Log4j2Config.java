package pers.husen.web.config;

import java.io.File;
import java.io.FileOutputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.w3c.dom.Document;
import org.w3c.dom.Node;

import pers.husen.web.common.constants.CommonConstants;
import pers.husen.web.common.helper.StackTrace2Str;
import pers.husen.web.listener.WebInitConfigListener;

/**
 * @author 何明胜
 *
 *         2017年9月23日
 */
public class Log4j2Config {
	public void startLog4j2Config() {
		// 获取完整路径
		String xmlFilePath = DeployPathConfig.LOG4J2_CONFIG_PATH;
		// 获取日志输出位置
		String logsParentDir = DeployPathConfig.LOG4J2_OUT_PATH;

		/**
		 * 根据user.dir判断当前是本地开发还是远程部署 在centos为用户目录，以'/'开头 在windows为当前web工程部署的地方，一般以大写字母开头
		 * 通过修改log4j2.xml的日志文件存放位置的属性，来修改日志保存的位置
		 */
		String currUserDir = System.getProperty("user.dir");
		if (currUserDir.charAt(0) == CommonConstants.LINUX_ROOT_PATH) {
			this.remoteLogsPathConfig(xmlFilePath, logsParentDir);
		} else if (currUserDir.charAt(0) == CommonConstants.WINDOWS_SYSTEM_DISK_LETTER) {
			// 我一般部署在C盘tomcat下，但是日志显示在F盘工程目录下，方便开发
			this.localLogsPathConfig(xmlFilePath, logsParentDir);
		} else {
			Logger logger = LogManager.getLogger(WebInitConfigListener.class.getName());
			logger.warn("识别 user.dir 出错，使用log4j2默认配置 : ${web:rootDir}/logs \n");
		}

		/**
		 * 配置读取的目录 先配保存的，再配读取的 日志在读取配置好生效，然后产生日志能保存
		 */
		if (currUserDir.charAt(0) == CommonConstants.LINUX_ROOT_PATH) {
			// 服务器为部署目录
			this.readLog4jPath(xmlFilePath);
		} else {
			// 本地配置读取log4j2.xml的路径为当前工程根目录
			this.readLog4jPath(xmlFilePath);
		}

		Logger logger = LogManager.getLogger(WebInitConfigListener.class.getName());
		logger.info("成功配置log4j2日志输出路径 -> " + logsParentDir);
	}

	/**
	 * 配置服务器日志文件存放位置
	 * 
	 * @param xmlFilePath
	 * @param logsParentDir
	 */
	public void remoteLogsPathConfig(String xmlFilePath, String logsParentDir) {
		modifyXmlFile(xmlFilePath, logsParentDir);
	}

	/**
	 * 配置本地开发日志文件存放位置
	 * 
	 * @param xmlFilePath
	 * @param logsParentDir
	 */
	public void localLogsPathConfig(String xmlFilePath, String logsParentDir) {
		modifyXmlFile(xmlFilePath, logsParentDir);
	}

	/**
	 * 修改log4j.xml中日志文件目录变量
	 * 
	 * @param xmlFilePath
	 *            配置文件的路径
	 * @param logsParentDir
	 *            注入日志的路径在xml中提供引入
	 */
	public void modifyXmlFile(String xmlFilePath, String logsParentDir) {
		try {
			File file = new File(xmlFilePath);
			// 1.得到DOM解析器的工厂实例
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			// 2.从DOM工厂里获取DOM解析器
			DocumentBuilder db = dbf.newDocumentBuilder();
			// 3.解析XML文档，得到document，即DOM树
			Document doc = db.parse(file);
			doc.getDocumentElement().normalize();

			// 直接获取该节点
			Node node = doc.getElementsByTagName("Property").item(0);
			// 获取当前节点值
			// String currentValue = node.getTextContent();
			// System.out.println("当前值：" + currentValue);
			node.setTextContent(logsParentDir);
			// String currentValue1 = node.getTextContent();
			// System.out.println("修改后：" + currentValue1);

			// 保存读取的XML为DOM树
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource domSource = new DOMSource(doc);
			StreamResult result = new StreamResult(new FileOutputStream(xmlFilePath));
			// 把DOM树转换为xml文件
			transformer.transform(domSource, result);
		} catch (Exception e) {
			Logger logger = LogManager.getLogger(Log4j2Config.class.getName());
			logger.error(StackTrace2Str.exceptionStackTrace2Str(e));
		}
	}

	/**
	 * 配置读取log4j2.xml的路径
	 * 
	 * @param xmlFilePath
	 * 				log4j2.xml的路径
	 * 
	 */
	public void readLog4jPath(String xmlFilePath) {
		try {
			/**
			 * 如果加了log4j-web.jar， 默认加载会比listeren还早 这时候设置系统属性已经失效
			 * 这时采用context.setConfigLocation()
			 */
			// 方法1：设置系统属性设置
			System.setProperty("log4j.configurationFile", xmlFilePath);
			// System.setProperty("org.apache.logging.log4j.simplelog.StatusLogger.level","trace");

			// 方法2：设置配置文件路径
			/*
			 * LoggerContext context = (LoggerContext) LogManager.getContext(); File file =
			 * new File(xmlFilePath); context.setConfigLocation(file.toURI());
			 * System.out.println("默认:" +context.getConfigLocation());
			 */

			Logger logger = LogManager.getLogger(Log4j2Config.class.getName());
			logger.info("成功配置读取log4j2.xml的路径 -> " + xmlFilePath);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}