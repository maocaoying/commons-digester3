package mcy.user;

import java.util.ArrayList;
import java.util.List;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class XmlParseHandler extends DefaultHandler {

    private List<User> users;
    private String currentTag; // ????????????
    private User user; // ?????user
    public List<User> getUsers() {
        return users;
    }

    /**
     * ?????????
     */
    @Override
    public void endDocument() throws SAXException {
        super.endDocument();
        System.out.println("endDocument-------endDocument()");
    }

    /**
     * ?????????
     * @param uri : ?????uri
     * @param localName : ?????
     * @param qName : ??????????
     */
    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        super.endElement(uri, localName, qName);
        System.out.println("endElement-------"+localName + "-----endElement()");
        if("user".equals(localName)){
            users.add(user);
            user = null;
        }
        currentTag = null;
    }

    /**
     * ????????
     */
    public void startDocument() throws SAXException {
        super.startDocument();
        System.out.println("");
        System.out.println("");
        System.out.println("startDocument startDocument()");
        users = new ArrayList<User>();
    }

    /**
     * ????????
     * @param uri : ?????uri
     * @param localName : ?????
     * @param qName : ??????????
     */
    public void startElement(String uri, String localName, String qName,
                             Attributes attributes) throws SAXException {
        super.startElement(uri, localName, qName, attributes);
        System.out.println("startElement------"+ localName + "-----startElement()");
        if ("user".equals(localName)) { // ?????
            for (int i = 0; i < attributes.getLength(); i++) {
                System.out.println("attributes attribute_name?" + attributes.getLocalName(i)
                        + "  attribute_value?" + attributes.getValue(i));
                user = new User();
                if("id".equals(attributes.getLocalName(i))){
                    user.setId(Long.parseLong(attributes.getValue(i)));
                }
            }
        }
        currentTag = localName; // ?????????
    }

    public void characters(char[] ch, int start, int length)
            throws SAXException {
        super.characters(ch, start, length);
        String value = new String(ch,start,length); // ???TextNode???String
        System.out.println("characters-------"+ value + "--------characters");
        if("name".equals(currentTag)){  // ?????name?????????????????????????????User???
            // ????name??
            user.setName(value);
        }else if("password".equals(currentTag)){  // ?????password?????????????????????????????User???
            // ????password??
            user.setPassword(value);
        }
    }




}
