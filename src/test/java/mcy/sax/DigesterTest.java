package mcy.sax;

import org.apache.commons.digester3.Digester;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class DigesterTest {
    // ???get/set????????????School??????
    private School school;
    public School getSchool() {
        return school;
    }
    public void setSchool(School s) {
        this.school = s;
    }

    private void digester() throws IOException, SAXException {
        // ????????????InputSource???digester?????????
        File file = new File("E:\\WorkSpace\\MyWorkSpace\\idea\\commons-digester3-3.2-src\\src\\test\\java\\mcy\\sax\\School.xml");
        InputStream inputStream = new FileInputStream(file);
        InputSource inputSource = new InputSource(file.toURI().toURL().toString());
        inputSource.setByteStream(inputStream);

        // ??Digester??
        Digester digester = new Digester();
        // ?????DTD??XML??????
        digester.setValidating(true);
        // ??????????????????????????school??????
        digester.push(this);

        /*
         * ?????Digester??????
         * Digester??School?School/Grade?School/Grade/Class?????School.xml?School?Grade?Class??
         */

        // ?School????

        /*
         * Digester.addObjectCreate(String pattern, String className, String attributeName)
         * pattern, ?????
         * className, ???????????
         * attributeName, ??????className??, ?className?????????
         *
         * Digester???School??
         *
         * 1. ??School????className??????com.juconcurrent.learn.apache.digester.School???
         * 2. ??School???className?????????(className????)??
         */
        digester.addObjectCreate("School", School.class.getName(), "className");
        // ????????????????School???name??????School.java
        digester.addSetProperties("School");

        /*
         * Digester.addSetNext(String pattern, String methodName, String paramType)
         * pattern, ?????
         * methodName, ????????
         * paramType, ?????????????
         * Digester???School??????DigesterTest(School????)?setSchool??????School??
         */
        digester.addSetNext("School", "setSchool", School.class.getName());

        // ?School/Grade????
        digester.addObjectCreate("School/Grade", Grade.class.getName(), "className");
        digester.addSetProperties("School/Grade");

        // Grade?????School
        digester.addSetNext("School/Grade", "addGrade", Grade.class.getName());

        // ?School/Grade/Class????
        digester.addObjectCreate("School/Grade/Class", Class.class.getName(), "className");
        digester.addSetProperties("School/Grade/Class");
        digester.addSetNext("School/Grade/Class", "addClass", Class.class.getName());
        // ?????
        digester.parse(inputSource);
    }

    // ???School?????????
    private void print(School s) {
        if (s != null) {
            System.out.println(s.getName() + "?" + s.getGrades().length + "???");
            for (int i = 0; i < s.getGrades().length; i++) {
                if (s.getGrades()[i] != null) {
                    Grade g = s.getGrades()[i];
                    System.out.println(g.getName() + "?? ? " + g.getClasses().length + "???");
                    for (int j = 0; j < g.getClasses().length; j++) {
                        if (g.getClasses()[j] != null) {
                            Class c = g.getClasses()[j];
                            System.out.println(c.getName() + "??" + c.getNumber() + "?");
                        }
                    }
                }
            }
        }
    }

    // ??main()??
    public static void main(String[] args) throws IOException, SAXException {
        DigesterTest digesterTest = new DigesterTest();
        digesterTest.digester();
        digesterTest.print(digesterTest.school);
    }

}
