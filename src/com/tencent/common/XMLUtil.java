package com.tencent.common;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

public class XMLUtil {


  /**
   * 将xml转换为Map
   * 
   * @param xml
   * @return
   * @throws Exception
   */
  public static Map<String, Object> xml2Map(String xml) throws Exception {
    return xmlDoc2Map(DocumentHelper.parseText(xml));
  }

  /**
   * 将xml文件转成Map
   * 
   * @param xmlDoc
   * @return
   */
  public static Map<String, Object> xmlDoc2Map(Document xmlDoc) {
    Map<String, Object> map = new HashMap<String, Object>();
    if (xmlDoc == null) {
      return map;
    }
    Element root = xmlDoc.getRootElement();
    for (Iterator iterator = root.elementIterator(); iterator.hasNext();) {
      Element e = (Element) iterator.next();
      List list = e.elements();
      if (list.size() > 0) {
        map.put(e.getName(), Dom2Map(e, map));
      } else {
        map.put(e.getName(), e.getText());
      }
    }
    return map;
  }

  private static Map Dom2Map(Element e, Map map) {
    List list = e.elements();
    if (list.size() > 0) {
      for (int i = 0; i < list.size(); i++) {
        Element iter = (Element) list.get(i);
        List mapList = new ArrayList();
        if (iter.elements().size() > 0) {
          Map m = Dom2Map(iter, map);
          if (map.get(iter.getName()) != null) {
            Object obj = map.get(iter.getName());
            if (!obj.getClass().getName().equals("java.util.ArrayList")) {
              mapList = new ArrayList();
              mapList.add(obj);
              mapList.add(m);
            }
            if (obj.getClass().getName().equals("java.util.ArrayList")) {
              mapList = (List) obj;
              mapList.add(m);
            }
            map.put(iter.getName(), mapList);
          } else {
            map.putAll(m);
          }
        } else {
          if (map.get(iter.getName()) != null) {
            Object obj = map.get(iter.getName());
            if (!obj.getClass().getName().equals("java.util.ArrayList")) {
              mapList = new ArrayList();
              mapList.add(obj);
              mapList.add(iter.getText());
            }
            if (obj.getClass().getName().equals("java.util.ArrayList")) {
              mapList = (List) obj;
              mapList.add(iter.getText());
            }
            map.put(iter.getName(), mapList);
          } else {
            map.put(iter.getName(), iter.getText());
          }
        }
      }
    } else {
      map.put(e.getName(), e.getText());
    }
    return map;
  }

  public static void main(String[] args) {
    String str =
        "<xml><appid><![CDATA[wx88fa4c9662539a8f]]></appid><bank_type><![CDATA[CCB_CREDIT]]></bank_type><cash_fee><![CDATA[1]]></cash_fee><fee_type><![CDATA[CNY]]></fee_type><is_subscribe><![CDATA[Y]]></is_subscribe><mch_id><![CDATA[1263513101]]></mch_id><nonce_str><![CDATA[1002477130]]></nonce_str><openid><![CDATA[o-HREuJzRr3moMvv990VdfnQ8x4k]]></openid><out_trade_no><![CDATA[20150819093510217]]></out_trade_no><result_code><![CDATA[SUCCESS]]></result_code><return_code><![CDATA[SUCCESS]]></return_code><sign><![CDATA[1269E03E43F2B8C388A414EDAE185CEE]]></sign><time_end><![CDATA[20150324100405]]></time_end><total_fee>1</total_fee><trade_type><![CDATA[APP]]></trade_type><transaction_id><![CDATA[1009530574201503240036299496]]></transaction_id></xml>";
    try {
      Map<String, Object> map = XMLUtil.xml2Map(str);
      System.out.println(map);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

}
