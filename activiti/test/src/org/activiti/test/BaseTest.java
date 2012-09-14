package org.activiti.test;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.bind.JAXBException;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.json.JSONException;
import org.junit.Assert;
import org.junit.Test;
import org.oryxeditor.server.diagram.basic.BasicDiagram;
import org.oryxeditor.server.diagram.basic.BasicDiagramBuilder;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import de.hpi.bpmn2_0.exceptions.BpmnConverterException;
import de.hpi.bpmn2_0.transformation.Diagram2XmlConverter;

public class BaseTest {

    @Test
    public void test() throws IOException, BpmnConverterException, JAXBException, SAXException,
            ParserConfigurationException, TransformerException, JSONException, XPathExpressionException {
        String json = readJson("models/junit/base.signavio.xml");

        BasicDiagram diagram = BasicDiagramBuilder.parseJson(json);
        Diagram2XmlConverter converter = new Diagram2XmlConverter(diagram,
                "classpath:/META-INF/validation/xsd/BPMN20.xsd");

        String xml = converter.getXml().getBuffer().toString();

        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();

        Document doc;
        InputStream inpStr;
        inpStr = new ByteArrayInputStream(xml.getBytes("UTF-8"));
        doc = documentBuilderFactory.newDocumentBuilder().parse(inpStr);
        
        XPathFactory xpathFactory = XPathFactory.newInstance();
        
        String processName = (String) xpathFactory.newXPath().evaluate("/definitions/process/@name",
                doc, XPathConstants.STRING);
        
        Assert.assertEquals("ProcessName", processName);
    }

    private static String readJson(String inPath) throws IOException {
        StringBuffer fileData = new StringBuffer(1000);
        BufferedReader reader = new BufferedReader(new FileReader(inPath));
        char[] buf = new char[1024];
        int numRead = 0;
        while ((numRead = reader.read(buf)) != -1) {
            String readData = String.valueOf(buf, 0, numRead);
            fileData.append(readData);
            buf = new char[1024];
        }
        reader.close();

        // get json representation
        int regexpFlags = Pattern.MULTILINE | Pattern.UNIX_LINES | Pattern.UNICODE_CASE | Pattern.DOTALL;
        Matcher m = Pattern.compile(".*<json-representation><!\\[CDATA\\[(.*)]]></json-representation>.*", regexpFlags)
                .matcher(fileData.toString());
        m.matches();
        return m.group(1);
    }

}
