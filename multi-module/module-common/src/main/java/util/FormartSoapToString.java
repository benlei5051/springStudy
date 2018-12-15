package util;

import javax.xml.soap.SOAPMessage;
import javax.xml.soap.SOAPPart;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.StringWriter;

public class FormartSoapToString {
    /**
     * 把soap对象格式化为字符串
     * @param soapMessage
     * @return
     */
    public static String formartToString(SOAPMessage soapMessage) {
        String str = "";
        try {
            SOAPPart soapPart = soapMessage.getSOAPPart();
            Transformer trans = TransformerFactory.newInstance().newTransformer();
            StringWriter sw = new StringWriter();
            trans.transform(new DOMSource(soapPart.getEnvelope()), new StreamResult(sw));
            sw.flush();
            sw.close();
            str = sw.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return str;
    }
}
