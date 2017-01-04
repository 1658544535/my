package com.tzmb2c.SAPService;

import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.UUID;

import javax.xml.namespace.QName;

import org.codehaus.xfire.XFireRuntimeException;
import org.codehaus.xfire.aegis.AegisBindingProvider;
import org.codehaus.xfire.annotations.AnnotationServiceFactory;
import org.codehaus.xfire.annotations.jsr181.Jsr181WebAnnotations;
import org.codehaus.xfire.client.XFireProxyFactory;
import org.codehaus.xfire.jaxb2.JaxbTypeRegistry;
import org.codehaus.xfire.service.Endpoint;
import org.codehaus.xfire.service.Service;
import org.codehaus.xfire.transport.TransportManager;
import org.w3c.dom.Document;

import com.tzmb2c.SAPService.Entity.SapEntity;
import com.tzmb2c.utils.SapSecurityUtil;
import com.tzmb2c.utils.XMLHandle;



public class SAPServiceClientTest {
  private static SAPServicePortType service = null;
  private static SAPServiceClientTest sc = null;
  private static XFireProxyFactory proxyFactory = new XFireProxyFactory();
  private HashMap endpoints = new HashMap();
  private Service service0;


  public SAPServiceClientTest(String url) {
    create0();
    Endpoint SAPServicePortTypeLocalEndpointEP =
        service0.addEndpoint(new QName("http://ws.sap.emn.com", "SAPServicePortTypeLocalEndpoint"),
            new QName("http://ws.sap.emn.com", "SAPServicePortTypeLocalBinding"),
            "xfire.local://SAPService");
    endpoints.put(new QName("http://ws.sap.emn.com", "SAPServicePortTypeLocalEndpoint"),
        SAPServicePortTypeLocalEndpointEP);
    Endpoint SAPServiceHttpPortEP =
        service0.addEndpoint(new QName("http://ws.sap.emn.com", "SAPServiceHttpPort"), new QName(
            "http://ws.sap.emn.com", "SAPServiceHttpBinding"),
            "http://120.197.89.51/sap_web2/services/SAPService");
    endpoints.put(new QName("http://ws.sap.emn.com", "SAPServiceHttpPort"), SAPServiceHttpPortEP);
    service = getSAPServiceHttpPort(url);
  }

  public Object getEndpoint(Endpoint endpoint) {

    try {
      return proxyFactory.create(endpoint.getBinding(), endpoint.getUrl());
    } catch (MalformedURLException e) {
      throw new XFireRuntimeException("Invalid URL", e);
    }
  }

  public Object getEndpoint(QName name) {
    Endpoint endpoint = (Endpoint) endpoints.get(name);
    if (endpoint == null) {
      throw new IllegalStateException("No such endpoint!");
    }
    return getEndpoint(endpoint);
  }

  public Collection getEndpoints() {
    return endpoints.values();
  }

  private void create0() {
    TransportManager tm =
        org.codehaus.xfire.XFireFactory.newInstance().getXFire().getTransportManager();
    HashMap props = new HashMap();
    props.put("annotations.allow.interface", true);
    AnnotationServiceFactory asf =
        new AnnotationServiceFactory(new Jsr181WebAnnotations(), tm, new AegisBindingProvider(
            new JaxbTypeRegistry()));
    asf.setBindingCreationEnabled(false);
    service0 = asf.create(SAPServicePortType.class, props);
    {
      asf.createSoap11Binding(service0,
          new QName("http://ws.sap.emn.com", "SAPServiceHttpBinding"),
          "http://schemas.xmlsoap.org/soap/http");
    }
    {
      asf.createSoap11Binding(service0, new QName("http://ws.sap.emn.com",
          "SAPServicePortTypeLocalBinding"), "urn:xfire:transport:local");
    }
  }

  public SAPServicePortType getSAPServicePortTypeLocalEndpoint() {
    return (SAPServicePortType) this.getEndpoint(new QName("http://ws.sap.emn.com",
        "SAPServicePortTypeLocalEndpoint"));
  }

  public SAPServicePortType getSAPServicePortTypeLocalEndpoint(String url) {
    SAPServicePortType var = getSAPServicePortTypeLocalEndpoint();
    org.codehaus.xfire.client.Client.getInstance(var).setUrl(url);
    return var;
  }

  public SAPServicePortType getSAPServiceHttpPort() {
    return (SAPServicePortType) this.getEndpoint(new QName("http://ws.sap.emn.com",
        "SAPServiceHttpPort"));
  }

  public SAPServicePortType getSAPServiceHttpPort(String url) {
    SAPServicePortType var = getSAPServiceHttpPort();
    org.codehaus.xfire.client.Client.getInstance(var).setUrl(url);
    return var;
  }

  public static SAPServiceClientTest getInstance() {
    String url = "http://120.197.89.51/sap_web2/services/SAPService";
    if (sc == null) {
      sc = new SAPServiceClientTest(url);
    }
    return sc;
  }


  /**
   * 用户鉴权接口
   * 
   * @param service
   */
  public static String userAuthen(SapEntity se) {


    String svccont =
        "<?xml version='1.0' encoding='UTF-8'?>" + "<BODY>" + "<EID>20571006253</EID>"
            + "<USERID>admin3</USERID>" + "<PASSWORD>d7izn7kg</PASSWORD>"
            + "<PLUGINTYPE>05</PLUGINTYPE>" + "</BODY>";
    System.out.println("用户发送userAuthen的报文体(svccont)：" + svccont);
    svccont = SapSecurityUtil.encrypt(svccont);// 加密报文体
    System.out.println("svccont=====================" + svccont);
    String rqxml =
        "<?xml version='1.0' encoding='UTF-8'?>" + "<PLUGINREQUEST>" + "<BIZCODE>SAP101</BIZCODE>"
            + "<BIZNAME>UserAuthen</BIZNAME>" + "<TRANSID>SAP201501280000001</TRANSID>"
            + "<TIMESTAMP>" + getTimeStamp() + "</TIMESTAMP>" + "<VERSION>1.0</VERSION>"
            + "<SVCCONT>" + svccont + "</SVCCONT>" + "</PLUGINREQUEST>";

    String rpxml = service.service(rqxml); // 发送的给企信通的接口
    // 打印返回报文
    String rpsvccont = writeRpXml(rpxml);

    System.out.println("rpsvccont========" + rpsvccont);
    // 保存token到数据库中
    // saveToken(rpsvccont);
    // <TOKEN>d4ac6319f7084f8ea659e53abf61e5ff</TOKEN>

    int strat = rpsvccont.lastIndexOf("<TOKEN>");
    int legth = "<TOKEN>".length();
    strat = strat + legth;
    int end = rpsvccont.indexOf("</TOKEN>");
    String token = rpsvccont.substring(strat, end);

    return token;
  }



  /**
   * 心跳接口
   * 
   * @param service
   * @param eid
   * @param userid
   */
  public void userPulse(SapEntity se) {
    String token = SAPServiceClientTest.userAuthen(se);
    // String token = "d4ac6319f7084f8ea659e53abf61e5ff";
    String svccont =
        "<?xml version='1.0' encoding='UTF-8'?>" + "<BODY>" + "<TOKEN>" + token + "</TOKEN>"
            + "<PLUGINTYPE>05</PLUGINTYPE>" + "</BODY>";
    System.out.println("心跳的svccont===========" + svccont);
    svccont = SapSecurityUtil.encrypt(svccont); // 加密报文体
    String rqxml =
        "<?xml version='1.0' encoding='UTF-8'?>" + "<PLUGINREQUEST>" + "<BIZCODE>SAP121</BIZCODE>"
            + "<BIZNAME>UserPulse</BIZNAME>" + "<TOKEN>" + token + "</TOKEN>"
            + "<PLUGINTYPE>01</PLUGINTYPE>" + "<TRANSID>SAP2012080912345670000001</TRANSID>"
            + "<TIMESTAMP>" + getTimeStamp() + "</TIMESTAMP>" + "<VERSION>1.0</VERSION>"
            + "<SVCCONT>" + svccont + "</SVCCONT>" + "</PLUGINREQUEST>";

    String rpxml = service.service(rqxml); // 发送的给企信通的接口

    System.out.println("rpxml++++++++++++++" + rpxml);

    // 打印返回报文
    writeRpXml(rpxml);
  }

  private String UUid() {
    return UUID.randomUUID().toString();
  }


  /**
   * 退出接口
   * 
   * @param service
   * @param eid
   * @param userid
   */
  public static void userQuit(SapEntity se) {
    String token = SAPServiceClientTest.userAuthen(se);
    String svccont =
        "<?xml version='1.0' encoding='UTF-8'?>" + "<BODY>" + "<TOKEN>" + token + "</TOKEN>"
            + "<PLUGINTYPE>01</PLUGINTYPE>" + "</BODY>";
    svccont = SapSecurityUtil.encrypt(svccont); // 加密报文体
    String rqxml =
        "<?xml version='1.0' encoding='UTF-8'?>" + "<PLUGINREQUEST>" + "<BIZCODE>SAP111</BIZCODE>"
            + "<BIZNAME>UserQuit</BIZNAME>" + "<TRANSID>SAP2012080912345670000001</TRANSID>"
            + "<TIMESTAMP>" + getTimeStamp() + "</TIMESTAMP>" + "<VERSION>1.0</VERSION>"
            + "<SVCCONT>" + svccont + "</SVCCONT>" + "</PLUGINREQUEST>";

    String rpxml = service.service(rqxml); // 发送的给企信通的接口

    // 打印返回报文
    writeRpXml(rpxml);
  }

  /**
   * 短信发送接口
   * 
   * @param service
   * @param eid
   * @param userid
   * @throws Exception
   */
  public void sendSms(SapEntity se, String phone, String content) throws Exception {

    String token = SAPServiceClientTest.userAuthen(se);
    String svccont =
        "<?xml version='1.0' encoding='UTF-8'?>" + "<BODY>" + "<TOKEN>" + token + "</TOKEN>"
            + "<PLUGINTYPE>05</PLUGINTYPE>" + "<FROMCODE>13829436777</FROMCODE>"
            + "<FROMTYPE>05</FROMTYPE>" + "<SMSTYPE>01</SMSTYPE>" + "<SMSLIST>" + "<SMS>"
            + "<NO>1</NO>" + "<CONTENT>" + content + "</CONTENT>" + "<SCOUNT>1</SCOUNT>"
            + "<USERPORT>0056</USERPORT>" + "<RECEIVERLIST>" + "<RECEIVER>" + "<MOBILE>" + phone
            + "</MOBILE>" + "</RECEIVER>" + "</RECEIVERLIST>" + "</SMS>" + "</SMSLIST>" + "</BODY>";
    System.out.println("发送短信的svccont==========" + svccont);
    svccont = SapSecurityUtil.encrypt(svccont); // 加密报文体
    String rqxml =
        "<?xml version='1.0' encoding='UTF-8'?>" + "<PLUGINREQUEST>" + "<BIZCODE>SAP201</BIZCODE>"
            + "<BIZNAME>SendSms</BIZNAME>" + "<TRANSID>SAP2015012812345670000001</TRANSID>"
            + "<TIMESTAMP>" + getTimeStamp() + "</TIMESTAMP>" + "<VERSION>1.0</VERSION>"
            + "<SVCCONT>" + svccont + "</SVCCONT>" + "</PLUGINREQUEST>";
    System.out.println("============" + rqxml);
    String rpxml = service.service(rqxml); // 发送的给企信通的接口

    // 打印返回报文
    writeRpXml(rpxml);
  }

  /**
   * 上行短信接口
   * 
   * @param service
   * @param eid
   * @param userid
   */
  public void syncUpSms(SapEntity se) {
    String token = SAPServiceClientTest.userAuthen(se);
    String svccont =
        "<?xml version='1.0' encoding='UTF-8'?>" + "<BODY>" + "<TOKEN>" + token + "</TOKEN>"
            + "<PLUGINTYPE>05</PLUGINTYPE>" + "</BODY>";
    svccont = SapSecurityUtil.encrypt(svccont); // 加密报文体
    String rqxml =
        "<?xml version='1.0' encoding='UTF-8'?>" + "<PLUGINREQUEST>" + "<BIZCODE>SAP301</BIZCODE>"
            + "<BIZNAME>SyncUpSms</BIZNAME>" + "<TRANSID>SAP2012080912345670000001</TRANSID>"
            + "<TIMESTAMP>" + getTimeStamp() + "</TIMESTAMP>" + "<VERSION>1.0</VERSION>"
            + "<SVCCONT>" + svccont + "</SVCCONT>" + "</PLUGINREQUEST>";

    String rpxml = service.service(rqxml); // 发送的给企信通的接口
    // 打印返回报文
    writeRpXml(rpxml);
  }

  /**
   * 获取状态报告接口
   * 
   * @param service
   * @param eid
   * @param userid
   */
  public void syncSmsStatus(SapEntity se) {
    String svccont =
        "<?xml version='1.0' encoding='UTF-8'?>" + "<BODY>"
            + "<TOKEN>01b973183a004636b777aea5b85929a5</TOKEN>" + "<PLUGINTYPE>05</PLUGINTYPE>"
            + "</BODY>";
    svccont = SapSecurityUtil.encrypt(svccont); // 加密报文体
    String rqxml =
        "<?xml version='1.0' encoding='UTF-8'?>" + "<PLUGINREQUEST>" + "<BIZCODE>SAP311</BIZCODE>"
            + "<BIZNAME>SyncSmsStatus</BIZNAME>" + "<TRANSID>SAP2012080912345670000001</TRANSID>"
            + "<TIMESTAMP>" + getTimeStamp() + "</TIMESTAMP>" + "<VERSION>1.0</VERSION>"
            + "<SVCCONT>" + svccont + "</SVCCONT>" + "</PLUGINREQUEST>";

    String rpxml = service.service(rqxml); // 发送的给企信通的接口
    // 打印返回报文
    writeRpXml(rpxml);
  }

  /**
   * 打印返回的报文
   */
  public static String writeRpXml(String rpxml) {
    String rpsvccont = "";
    String rpmsg = "";
    try {
      System.out.println("能力插件返回的报文(rpxml)：" + rpxml);
      XMLHandle xh = new XMLHandle();
      Document doc = xh.createXMLFromString(rpxml);
      rpsvccont = xh.getValueOfNode(xh.getFirstNodeByName(doc, "SVCCONT"));
      rpsvccont = SapSecurityUtil.decrypt(rpsvccont);
      System.out.println("能力插件返回的报文体(svccont)：" + rpsvccont);
      rpmsg = xh.getValueOfNode(xh.getFirstNodeByName(doc, "RESULTMSG"));
      if ("未登录或session过期".equals(rpmsg.trim())) {
        System.out.println("=====心跳维持的Token已过期，需要重新登录获取token=====");
        SapEntity se = new SapEntity();
        se.setBizCode("SAP101");
        se.setBizName("UserAuthen");
        getInstance();
        SAPServiceClientTest.userAuthen(se);
      }
    } catch (Exception e) {
      System.out.println("SAPServiceClient 【writeRpXml】：" + e.getMessage());
      e.printStackTrace();
    }
    return rpsvccont;
  }

  /**
   * 获取时间
   * 
   * @param args
   * @return
   * @throws Exception
   */
  public static String getTimeStamp() {
    SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmssSSSS");
    Date date = new Date();
    String datetime = df.format(date);
    return datetime;
  }



  public static void main(String[] args) throws Exception {

    String url = "http://120.197.89.51/sap_web2/services/SAPService";
    SAPServiceClientTest client = new SAPServiceClientTest(url);
    SapEntity se = new SapEntity();
    // 测试用户登录接口
    SAPServiceClientTest.userAuthen(se);
    // 测试心跳接口
    // client.userPulse(se);
    // 测试退出接口
    // client.userQuit(se);
    // 测试短信发送接口
    client.sendSms(se, "13068904483", "测试短信！test test");
    // 测试上行短信接口
    // client.syncUpSms(se);
    // 测试获取状态报告接口
    // client.syncSmsStatus(se);
    System.out.println("msm send completed");
    System.exit(0);

  }

}
