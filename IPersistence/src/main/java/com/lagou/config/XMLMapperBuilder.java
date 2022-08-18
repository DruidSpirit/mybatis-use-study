package com.lagou.config;

import com.lagou.mapping.SqlCommandType;
import com.lagou.pojo.Configuration;
import com.lagou.pojo.MappedStatement;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.util.List;

public class XMLMapperBuilder {

    private Configuration configuration;

    public XMLMapperBuilder(Configuration configuration) {
        this.configuration =configuration;
    }

    public void parse(InputStream inputStream) throws DocumentException {

        Document document = new SAXReader().read(inputStream);
        Element rootElement = document.getRootElement();
        //  解析select标签
        this.parseBySqlTypeTag( rootElement, SqlCommandType.SELECT);
        //  解析insert标签
        this.parseBySqlTypeTag( rootElement, SqlCommandType.INSERT);
        //  解析update标签
        this.parseBySqlTypeTag( rootElement, SqlCommandType.UPDATE);
        //  解析delete标签
        this.parseBySqlTypeTag( rootElement, SqlCommandType.DELETE);

    }

    private void parseBySqlTypeTag ( Element rootElement, SqlCommandType sqlCommandType ) {

        String namespace = rootElement.attributeValue("namespace");
        List<Element> list;
        switch (sqlCommandType){
            case SELECT:
                list = rootElement.selectNodes("//select");
                break;
            case UPDATE:
                list = rootElement.selectNodes("//insert");
                break;
            case INSERT:
                list = rootElement.selectNodes("//update");
                break;
            case DELETE:
                list = rootElement.selectNodes("//delete");
                break;
            default:
                return;
        }

        for (Element element : list) {
            String id = element.attributeValue("id");
            String resultType = element.attributeValue("resultType");
            String paramterType = element.attributeValue("paramterType");
            String sqlText = element.getTextTrim();
            MappedStatement mappedStatement = new MappedStatement();
            mappedStatement.setId(id);
            mappedStatement.setResultType(resultType);
            mappedStatement.setParamterType(paramterType);
            mappedStatement.setSql(sqlText);
            mappedStatement.setSqlCommandType(sqlCommandType);

            String key = namespace+"."+id;
            configuration.getMappedStatementMap().put(key,mappedStatement);

        }

    }


}
