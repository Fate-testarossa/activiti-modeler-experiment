package com.signavio.warehouse.business;

import java.io.IOException;
import java.io.StringWriter;

import javax.xml.bind.JAXBException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.json.JSONException;
import org.oryxeditor.server.diagram.basic.BasicDiagramBuilder;
import org.xml.sax.SAXException;

import com.signavio.platform.core.Platform;
import com.signavio.platform.core.PlatformProperties;
import com.signavio.platform.util.fsbackend.FileSystemUtil;

import de.hpi.bpmn2_0.exceptions.BpmnConverterException;
import de.hpi.bpmn2_0.transformation.Diagram2XmlConverter;

public class BPMN20XMLFileUtil {

    public static void storeBPMN20XMLFile(String path, String jsonRep) throws IOException, JSONException, BpmnConverterException, JAXBException, SAXException, ParserConfigurationException, TransformerException {
        PlatformProperties props = Platform.getInstance().getPlatformProperties();
        
        Json2XmlConverter converter = new Json2XmlConverter(jsonRep, Platform.getInstance().getFile("/WEB-INF/xsd/BPMN20.xsd").getAbsolutePath());
        
        StringWriter xml = converter.getXml();
        
        FileSystemUtil.deleteFileOrDirectory(path);
        FileSystemUtil.createFile(path, xml.toString());
    }
}
