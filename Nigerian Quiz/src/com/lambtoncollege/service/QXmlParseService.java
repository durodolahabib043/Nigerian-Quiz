package com.lambtoncollege.service;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import com.lambtoncollege.entity.Question;

public class QXmlParseService {

    public List<Question> getQuestionsByParseXml(InputStream is) throws Exception {
    	
        List<Question> list = null;
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(is);

        Element root = document.getDocumentElement();
        NodeList nodeList = root.getElementsByTagName("question");
        if (nodeList == null || nodeList.getLength() == 0) {
            return null;
        }

        list = new ArrayList<Question>();

        for (int i = 0; i < nodeList.getLength(); i++) {
            Element element = (Element) nodeList.item(i);
            Question p = new Question();
            NodeList questionNodeList = element.getChildNodes();
            Node node = null;
            for (int j = 0; j < questionNodeList.getLength(); j++) {
                node = questionNodeList.item(j);
                if (node.getNodeName().equals("q")) {
                	p.setQuestion(node.getTextContent());
                } else if (node.getNodeName().equals("choice")) {
                	NodeList choiceItems = node.getChildNodes();
                	Node subNode = null;
                	for (int k = 0; k < choiceItems.getLength(); k++) {
                		subNode = choiceItems.item(k);
                		if (subNode.getNodeName().equals("item_a")) {
                			p.setChoiceA(subNode.getTextContent());
                		} else if (subNode.getNodeName().equals("item_b")) {
                			p.setChoiceB(subNode.getTextContent());
                		} else if (subNode.getNodeName().equals("item_c")) {
                			p.setChoiceC(subNode.getTextContent());
                		} else if (subNode.getNodeName().equals("item_d")) {
                			p.setChoiceD(subNode.getTextContent());
                		} else {
                			continue;
                		}
                	}
                } else if (node.getNodeName().equals("a")) {
                	p.setAnswer(Integer.parseInt(node.getTextContent()));
                } else {
                	continue;
                }
            }

            list.add(p);
        }
        
        return list;
    }

}