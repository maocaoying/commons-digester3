package mcy.user;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException, SAXException, ParserConfigurationException {
        List<User> list = getUsers();
        for (User u:list) {
            System.out.println("---->"+u.getName());
        }

    }

    public static List<User> getUsers() throws ParserConfigurationException, SAXException, IOException {
        // ????????????
        InputStream is = new FileInputStream("/Users/Kydu1024/IdeaProjects/commons-digester3/src/test/java/mcy/user/users.xml");
        XmlParseHandler handler = new XmlParseHandler();
        // 1. ??SAX????
        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        // 2. ???????sax???
        SAXParser newSAXParser = saxParserFactory.newSAXParser();
        // 3. ??????handler???
        newSAXParser.parse(is, handler);
        is.close();
        return handler.getUsers();
    }
    }
