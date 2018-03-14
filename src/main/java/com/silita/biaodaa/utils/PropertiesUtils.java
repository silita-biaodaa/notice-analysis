package com.silita.biaodaa.utils;/**
 * Created by zhangxiahui on 15/6/3.
 */

import org.apache.commons.logging.LogConfigurationException;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.Properties;

/**
 * @author zhangxiahui
 * @version 1.0
 * @date 2015/06/03 下午4:24
 */
public class PropertiesUtils {
    private static Logger logger = Logger.getLogger(PropertiesUtils.class);

    public static final String[] PROPERTIES = new String[] { "config/analysis.properties"};

    private static Properties properties = new Properties();

    static {
        try {
            for (String str : PROPERTIES) {
                properties.load(getResourceAsStream(str));
            }
        } catch (IOException e) {
            logger.error("配置文件加载失败！", e);
        }
    }

    private PropertiesUtils() {
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }

    public static String getProperty(String key, String defaultValue) {
        return properties.getProperty(key, defaultValue);
    }

    private static ClassLoader getContextClassLoader() {
        ClassLoader classLoader = null;

        if (classLoader == null) {
            try {
                Method method = Thread.class.getMethod("getContextClassLoader", (Class[]) null);
                try {
                    classLoader = (ClassLoader) method.invoke(Thread.currentThread(), (Class[]) null);
                } catch (IllegalAccessException e) {
                    ; // ignore
                } catch (InvocationTargetException e) {

                    if (e.getTargetException() instanceof SecurityException) {
                        ; // ignore
                    } else {
                        throw new LogConfigurationException("Unexpected InvocationTargetException",
                                e.getTargetException());
                    }
                }
            } catch (NoSuchMethodException e) {
                // Assume we are running on JDK 1.1
                ; // ignore
            }
        }

        if (classLoader == null) {
            classLoader = PropertiesUtils.class.getClassLoader();
        }

        // Return the selected class loader
        return classLoader;
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    private static InputStream getResourceAsStream(final String name) {
        return (InputStream) AccessController.doPrivileged(new PrivilegedAction() {
            public Object run() {
                ClassLoader threadCL = getContextClassLoader();

                if (threadCL != null) {
                    return threadCL.getResourceAsStream(name);
                } else {
                    return ClassLoader.getSystemResourceAsStream(name);
                }
            }
        });
    }

    public static void main(String[] args) {
        System.out.println(PropertiesUtils.getProperty("tracker_server"));
        System.out.println(PropertiesUtils.getProperty("zkConnectString"));
        System.out.println(PropertiesUtils.getProperty("rootPath"));
        System.out.println(PropertiesUtils.getProperty("userName"));
        System.out.println(PropertiesUtils.getProperty("password"));
        System.out.println(PropertiesUtils.getProperty("zkSessionTimeout"));
        System.out.println(PropertiesUtils.getProperty("isCheckParentPath"));
        System.out.println(PropertiesUtils.getProperty("workDir"));
    }
}
