package com.bob.learn.springframework.beans.factory.xml;

import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.XmlUtil;
import com.bob.learn.springframework.beans.BeansException;
import com.bob.learn.springframework.beans.PropertyValue;
import com.bob.learn.springframework.beans.factory.config.BeanDefinition;
import com.bob.learn.springframework.beans.factory.config.BeanReference;
import com.bob.learn.springframework.beans.factory.support.AbstractBeanDefinitionReader;
import com.bob.learn.springframework.beans.factory.support.BeanDefinitionRegistry;
import com.bob.learn.springframework.core.io.Resource;
import com.bob.learn.springframework.core.io.ResourceLoader;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.io.IOException;
import java.io.InputStream;

/**
 * xmlBean定义读取器
 *
 * @author Bob
 * @date 2022/8/18 9:36
 */
public class XmlBeanDefinitionReader extends AbstractBeanDefinitionReader {

    public XmlBeanDefinitionReader(BeanDefinitionRegistry registry) {
        super(registry);
    }

    public XmlBeanDefinitionReader(BeanDefinitionRegistry registry, ResourceLoader resourceLoader) {
        super(registry, resourceLoader);
    }

    @Override
    public void loadBeanDefinitions(Resource resource) throws BeansException {
        try {
            try (InputStream inputStream = resource.getInputStream()) {
                doLoadBeanDefinitions(inputStream);
            }
        } catch (IOException | ClassNotFoundException e) {
            throw new BeansException("IOException parsing xml document from " + resource, e);
        }
    }

    @Override
    public void loadBeanDefinitions(Resource... resources) throws BeansException {
        for (Resource resource : resources) {
            loadBeanDefinitions(resource);
        }
    }

    @Override
    public void loadBeanDefinitions(String location) throws BeansException {
        ResourceLoader resourceLoader = getResourceLoader();
        Resource resource = resourceLoader.getResource(location);
        loadBeanDefinitions(resource);
    }

    /**
     * 真正做输入流加载
     *
     * @param inputStream 输入流
     */
    protected void doLoadBeanDefinitions(InputStream inputStream) throws ClassNotFoundException {
        Document doc = XmlUtil.readXML(inputStream);
        Element root = doc.getDocumentElement();
        NodeList childNodes = root.getChildNodes();
        for (int i = 0; i < childNodes.getLength(); i++) {
            //判断元素、对象
            if (!(childNodes.item(i) instanceof Element) || !"bean".equals(childNodes.item(i).getNodeName())) {
                continue;
            }
            // 解析标签:bean
            Element bean = (Element) childNodes.item(i);
            String id = bean.getAttribute("id");
            String name = bean.getAttribute("name");
            String className = bean.getAttribute("class");

            //获取class
            Class<?> clazz = Class.forName(className);
            // 优先级 id > name
            String beanName = StrUtil.isNotEmpty(id) ? id : name;
            if (StrUtil.isEmpty(beanName)) {
                //类名首字母小写
                beanName = StrUtil.lowerFirst(clazz.getSimpleName());
            }
            //定义bean
            BeanDefinition beanDefinition = new BeanDefinition(clazz);
            NodeList beanChildNodes = bean.getChildNodes();
            //读取属性填充
            for (int j = 0; j < beanChildNodes.getLength(); j++) {
                if (!(beanChildNodes.item(j) instanceof Element) || !"property".equals(beanChildNodes.item(j).getNodeName())) {
                    continue;
                }
                // 解析标签:property
                Element property = (Element) beanChildNodes.item(j);
                String attrName = property.getAttribute("name");
                String attrValue = property.getAttribute("value");
                String attrRef = property.getAttribute("ref");
                // 获取属性值：引入对象、值对象
                Object value = StrUtil.isNotEmpty(attrRef) ? new BeanReference(attrRef) : attrValue;
                // 创建属性信息
                PropertyValue propertyValue = new PropertyValue(attrName, value);
                beanDefinition.getPropertyValues().addPropertyValue(propertyValue);
            }
            if (getRegistry().containsBeanDefinition(beanName)) {
                throw new BeansException("Duplicate beanName[" + beanName + "] is not allowed");
            }
            // 注册 BeanDefinition
            getRegistry().registerBeanDefinition(beanName, beanDefinition);
        }
    }
}
