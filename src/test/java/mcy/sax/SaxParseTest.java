package mcy.sax;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;


public class SaxParseTest extends DefaultHandler{
    private List<Book> books = null;
    private Book book = null;
    private String preTag = null;

    public List<Book> getBooks(InputStream xmlStream) throws Exception{
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = factory.newSAXParser();
        SaxParseTest handler = new SaxParseTest();
        parser.parse(xmlStream, handler);
        return handler.getBooks();
    }

    public List<Book> getBooks(){
        return books;
    }

    @Override
    public void startDocument() throws SAXException {
        books = new ArrayList<Book>();
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if("book".equals(qName)){
            book = new Book();
            book.setId(Integer.parseInt(attributes.getValue(0)));
        }
        preTag = qName;//????????????preTag
    }

    @Override
    public void endElement(String uri, String localName, String qName)
            throws SAXException {
        if("book".equals(qName)){
            books.add(book);
            book = null;
        }
        preTag = null;

        /**
         * ???????????????????????3??????????????
         ???????preTag??null???startElement(....)???preTag????book?????????
         ???4????????characters(char[] ch, int start, int length)??????characters(....)?
         ???preTag!=null????if????????????????book??????????
         */
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        if(preTag!=null){
            String content = new String(ch,start,length);
            if("name".equals(preTag)){
                book.setName(content);
            }else if("price".equals(preTag)){
                book.setPrice(Float.parseFloat(content));
            }
        }
    }
}
