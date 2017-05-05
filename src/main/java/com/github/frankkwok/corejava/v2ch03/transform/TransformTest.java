package com.github.frankkwok.corejava.v2ch03.transform;

import com.github.frankkwok.corejava.util.ResourceUtils;
import org.xml.sax.InputSource;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.sax.SAXSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author Frank Kwok on 2017/4/27.
 */
public class TransformTest {
    private static final String FILE_NAME = "employee_transform.dat";

    public static void main(String[] args) throws IOException, TransformerException {
        String filename;
        if (args.length > 0) {
            filename = args[0];
        } else {
            filename = "makehtml.xsl";
        }

        try (InputStream styleIn = ResourceUtils.newInputStream(filename)) {
            StreamSource styleSource = new StreamSource(styleIn);

            Transformer t = TransformerFactory.newInstance().newTransformer(styleSource);
            t.setOutputProperty(OutputKeys.INDENT, "yes");
            t.setOutputProperty(OutputKeys.METHOD, "xml");
            t.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");

            try (InputStream docIn = ResourceUtils.newInputStream(FILE_NAME)) {
                t.transform(new SAXSource(new EmployeeReader(), new InputSource(docIn)),
                        new StreamResult(System.out));
            }
        }
    }
}
