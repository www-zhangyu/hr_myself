package ytu.icoding.util;

import java.util.Properties;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * ͨ��ʵ��ApplicationContextAware�ӿڣ���spring��������ʱ��ȡspring��������<br>
 * 

 * 
 */
public class ApplicationContextInit implements ApplicationContextAware {
	private static Log log = LogFactory.getLog(ApplicationContextInit.class);

	/**
	 * ApplicationContext
	 */
	private static ApplicationContext springContext;

	/**
	 * ϵͳ������Ϣ
	 */
	//private static Properties props;

	/**
	 * ����������Ϣ
	 */
	private static Properties errorCodeProps;

	/*
	 * ע��ApplicationContext
	 */
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		// �ڼ���Springʱ�Զ����context
		springContext = applicationContext;
		//props = getProps();
		errorCodeProps=getErrorProps();
	}

	/**
	 * ͨ��beanId�õ�factory�е�beanʵ��
	 * 
	 * @param beanId
	 * @return Object
	 */
	public static Object getBean(String beanId) {
		Object obj = null;
		if (springContext != null)
			obj = springContext.getBean(beanId);
		return obj;
	}

	/**
	 * ���ϵͳ����������Զ���
	 * 
	 * @return ����������Զ���
	 */
	private static Properties getErrorProps() {
		Properties conf = null;
		try {
			if (springContext != null)
				conf = (Properties) getBean("errorCode");
		} catch (NoSuchBeanDefinitionException e) {
			//log.error("û���ҵ�errorCode�����֣�");
			System.out.println("û���ҵ�errorCode�����֣�");
		}
		return conf;
	}
	
	/**
	 * ���ϵͳ�������Զ���
	 * 
	 * @return ���Զ���
	 */
	/*private static Properties getProps() {
		Properties conf = null;
		try {
			if (springContext != null)
				conf = (Properties) getBean("config");
		} catch (NoSuchBeanDefinitionException e) {
			log.error("û���ҵ�config�����֣�");
		}
		return conf;
	}
*/
	/**
	 * �õ�ϵͳ��������
	 * 
	 * @param name
	 * @return �����ַ�ֵ
	 */
	/*public static String getConfig(String name) {
		if (props != null)
			return (String) props.getProperty(name);
		else
			return null;
	}*/
	
	/**
	 * ��ȡ�������Ӧ����ϸ��Ϣ
	 * @param errorCode ������
	 * @return			��ϸ��Ϣ
	 */
	public static String getErrorInfo(String errorCode) {
		if (errorCodeProps != null)
			return (String) errorCodeProps.getProperty(errorCode);
		else
			return null;
	}

	/**
	 * ��ȡApplicationContext
	 * 
	 * @return ApplicationContext
	 */
	public static ApplicationContext getApplicationContext() {
		return springContext;
	}

}
