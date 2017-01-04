package com.tzmb2c.utils;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.util.Vector;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * <p>
 * Description: xml解析处理工具类
 * </p>
 */
public final class XMLHandle {
  /**
   * <p>
   * description: 构造函数
   * </p>
   */
  public XMLHandle() {}

  /**
   * 通过一个字符串创建一个dom树结构
   * 
   * @param xml
   * @return
   * @throws Exception
   */
  public Document createXMLFromString(String xml) throws Exception {
    Document document = null;
    DocumentBuilderFactory DBF = DocumentBuilderFactory.newInstance();
    DocumentBuilder DB = DBF.newDocumentBuilder();
    document = DB.parse(new ByteArrayInputStream(xml.getBytes("UTF-8")));
    return document;
  }

  /**
   * 通过一个字节数组创建一个dom树结构
   * 
   * @param bytes
   * @return
   * @throws Exception
   */
  public Document createXMLFromBytes(byte[] bytes) throws Exception {
    Document document = null;
    DocumentBuilderFactory DBF = DocumentBuilderFactory.newInstance();
    DocumentBuilder DB = DBF.newDocumentBuilder();
    document = DB.parse(new ByteArrayInputStream(bytes));
    return document;
  }

  /**
   * 通过文件创建一个dom树结构
   * 
   * @param file
   * @return
   * @throws Exception
   */
  public Document createXMLFromFile(File file) throws Exception {
    Document document = null;
    DocumentBuilderFactory DBF = DocumentBuilderFactory.newInstance();
    DocumentBuilder DB = DBF.newDocumentBuilder();
    document = DB.parse(file);
    return document;
  }

  /**
   * 创建一个dom树结构
   * 
   * @return
   */
  public Document createXML() {
    Document document = null;
    try {
      DocumentBuilderFactory DBF = DocumentBuilderFactory.newInstance();
      DocumentBuilder DB = DBF.newDocumentBuilder();
      document = DB.newDocument();
    } catch (Exception e) {
      System.out.println("get xml :".concat(String.valueOf(String.valueOf(e.toString()))));
      Document document1 = null;
      return document1;
    }
    return document;
  }

  /**
   * 在一个元素中增加一子元素
   * 
   * @param document
   * @param pE 父元素
   * @param name 子元素名称
   * @param value 子元素的值
   * @return
   */
  public boolean addElementToElement(Document document, Element pE, String name, String value) {
    try {
      Element tempElement = document.createElement(name);
      tempElement.appendChild(document.createTextNode(value));
      pE.appendChild(tempElement);
    } catch (Exception e) {
      boolean flag = false;
      return flag;
    }
    return true;
  }

  /**
   * 在一元素中增加一内容子元素,即对应元素的值
   * 
   * @param document
   * @param pE 一元素
   * @param value 元素的值
   * @return
   */
  public boolean addTextToElement(Document document, Element pE, String value) {
    try {
      pE.appendChild(document.createTextNode(value));
    } catch (Exception e) {
      boolean flag = false;
      return flag;
    }
    return true;
  }

  /**
   * 通过名字获取一node结点，即在整个xml文档中第一个匹配此名字的结点
   * 
   * @param document
   * @param sName
   * @return
   */
  public Node getFirstNodeByName(Document document, String sName) {
    try {
      NodeList assetNodes = document.getElementsByTagName(sName);
      if (assetNodes == null) {
        Node node = null;
        return node;
      } else {
        Node node1 = assetNodes.item(0);
        return node1;
      }
    } catch (Exception e) {
      Node node2 = null;
      return node2;
    }
  }

  /**
   * 通过一个名字，查找xml文档所有与这个名字匹配的结点集合
   * 
   * @param document
   * @param sName
   * @return
   */
  public Vector getNodeListByName(Document document, String sName) {
    Vector allR = new Vector();
    try {
      NodeList assetNodes = document.getElementsByTagName(sName);
      if (assetNodes == null) {
        return null;
      }

      for (int i = 0; i < assetNodes.getLength(); i++) {
        Node cur = assetNodes.item(i);
        allR.addElement(cur);
      }

      Vector vector1 = allR;
      return vector1;
    } catch (Exception e) {
      Vector vector2 = null;
      return vector2;
    }
  }

  /**
   * 通过一个名字和一个父结点获取其中对应的第一个和名字匹配的子结点
   * 
   * @param par 父结点
   * @param sName 要寻找的子结点的名字
   * @return
   */
  public Node getChildNodeByName(Node par, String sName) {
    try {
      NodeList assetNodes = par.getChildNodes();
      if (assetNodes == null) {
        Node node = null;
        return node;
      }

      for (int i = 0; i < assetNodes.getLength(); i++) {
        Node cur = assetNodes.item(i);
        if (cur.getNodeName().compareTo(sName) == 0) {
          Node node3 = cur;
          return node3;
        }
      }

      Node node1 = null;
      return node1;
    } catch (Exception e) {
      Node node2 = null;
      return node2;
    }
  }

  /**
   * 通过一父结点和一个名字，查找所有与这个名字匹配的子结点集合
   * 
   * @param par 父结点
   * @param sName 需要寻找的子结点的名字
   * @return
   */
  public Vector getChildNodeListByName(Node par, String sName) {
    Vector allR = new Vector();
    try {
      NodeList assetNodes = par.getChildNodes();
      if (assetNodes == null) {
        Vector vector = null;
        return vector;
      }

      for (int i = 0; i < assetNodes.getLength(); i++) {
        Node cur = assetNodes.item(i);
        if (cur.getNodeName().compareTo(sName) == 0) {
          allR.addElement(cur);
        }
      }

      Vector vector1 = allR;
      return vector1;
    } catch (Exception e) {
      Vector vector2 = null;
      return vector2;
    }
  }

  /*
   * // 递归寻找一结点下匹配一个名字的所有子结点 public boolean getChildNodeListByNameRecurse(Node par, String sName,
   * Vector allChilds) { boolean flag2; try { NodeList assetNodes = par.getChildNodes();
   * if(assetNodes == null) { boolean flag = true; return flag; } for(int i = 0; i <
   * assetNodes.getLength(); i++) { Node cur = assetNodes.item(i);
   * if(cur.getNodeName().compareTo(sName) == 0) allChilds.addElement(cur);
   * getChildNodeListByNameRecurse(cur, sName, allChilds); }
   * 
   * boolean flag1 = true; return flag1; } catch(Exception e) { flag2 = false; } return flag2; }
   */


  /**
   * 获取结点的值,内容
   * 
   * @param nd
   * @return
   */
  public String getValueOfNode(Node nd) {
    if (nd == null) {
      return "";
    }
    NodeList assetNodes = nd.getChildNodes();
    if (assetNodes == null || assetNodes.getLength() == 0) {
      return "";
    } else {
      return nd.getFirstChild().getNodeValue();
    }
  }

  /**
   * 获取某一结点某一属性的值
   * 
   * @param nd 结点
   * @param attributeName 属性名字
   * @return
   */
  public String getAttributeValueOfNode(Node nd, String attributeName) {
    if (nd == null) {
      return "";
    }
    Element elem = (Element) nd;
    String returnStr = elem.getAttribute(attributeName);
    if (returnStr != null) {
      return returnStr;
    } else {
      return "";
    }
  }

  /*
   * //把dom树结构转换为String类型数据 public String getString(Document document) { if(document == null) return
   * ""; String ret = ""; try { NodeList assetNodes = document.getChildNodes(); if(assetNodes ==
   * null) { String s = ""; return s; }
   * 
   * for(int i = 0; i < assetNodes.getLength(); i++) { Node assetNode = assetNodes.item(i); ret =
   * String.valueOf(ret) + String.valueOf(assetNode.toString()); }
   * 
   * } catch(Exception ex) { System.out.println(ex); } return ret; }
   */
}
