package mcy.sax;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;

public class ParseTest {

    @Test
    public void testSAX() throws Throwable{
        SaxParseTest sax = new SaxParseTest();
//        InputStream input = this.getClass().getClassLoader().getResourceAsStream("E:\\WorkSpace\\MyWorkSpace\\idea\\commons-digester3-3.2-src\\src\\test\\java\\mcy\\sax\\books.xml");
        InputStream input = new FileInputStream("E:\\WorkSpace\\MyWorkSpace\\idea\\commons-digester3-3.2-src\\src\\test\\java\\mcy\\sax\\books.xml");
        List<Book> books = sax.getBooks(input);
        for(Book book : books){
            System.out.println(book.toString());
        }
    }
}
