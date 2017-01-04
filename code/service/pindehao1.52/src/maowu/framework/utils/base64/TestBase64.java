package maowu.framework.utils.base64;

import maowu.framework.utils.datetime.DateTimeUtil;

import com.tzmb2c.utils.StringUtil;

public class TestBase64 {

  public static String getReq0000000001Base64() {
    String str =
        "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" + "<mw>" + "<pub>"
            + "<reqCode>0000000001</reqCode>" + "<reqSender>测试发送方</reqSender>" + "<reqTime>"
            + DateTimeUtil.getDateTime()
            + "</reqTime>"
            + "</pub>"
            + "<busi><!-- trunk 1次 -->"
            + "<trunk>"
            + "<req0000000001trunk>"
            + "<ofId>"
            + StringUtil.getPrimarykey()
            + "</ofId>"
            + "<tradeDate>20130909090909</tradeDate>"
            + "<payDate>20130909090909</payDate>"
            + "<conDate>20130909090909</conDate>"
            + "<tradePla>01</tradePla>"
            + "<payType>01</payType>"
            + "<trRecMoney>100000</trRecMoney>"
            + "<trRealMoney>100000</trRealMoney>"
            + "<recAddress>收货地址收货地址</recAddress>"
            + "<tranMode>01</tranMode>"
            + "<isPost>01</isPost>"
            + "<postMoney>123</postMoney>"
            + "<logiComp>物流公司</logiComp>"
            + "<wayBill>2123213123</wayBill>"
            + "<dgTime>20130909090909</dgTime>"
            + "<rebType>01</rebType>"
            + "<rebRate>30</rebRate>"
            + "<rebMoney>21</rebMoney>"
            + "<tradeContent>交易备注内容</tradeContent>"
            + "<buyerName>买方姓名</buyerName>"
            + "<buyerSex>01</buyerSex>"
            + "<buyerBirthdate>20091212</buyerBirthdate>"
            + "<buyerTel>12345643</buyerTel>"
            + "<buyerPhone>123456545</buyerPhone>"
            + "<buyerEmail>caoshujie@mailworld.org</buyerEmail>"
            + "<buyerLevel>2</buyerLevel>"
            + "<buyerLeMa>买家留言</buyerLeMa>"
            + "<sellerName>商家10</sellerName>"
            + "<sellerNi>卖家昵称</sellerNi>"
            + "<sellerTel>213231</sellerTel>"
            + "<sellerPhone>231231</sellerPhone>"
            + "<sellerEmail>caoshujie@mailworld.org</sellerEmail>"
            + "<sellerCity>卖家城市</sellerCity>"
            + "<equiId>1232313</equiId>"
            + "<custom1>预留</custom1>"
            + "<custom2>预留</custom2>"
            + "<custom3>预留</custom3>"
            + "</req0000000001trunk>"
            + "</trunk>"
            + "<list>"
            + "<!-- branch 1次或多次 -->"
            + "<branch>"
            + "<req0000000001branch>"
            + "<prodName>产品名称</prodName>"
            + "<prodType>产品类型</prodType>"
            + "<prodSrcPrice>1232</prodSrcPrice>"
            + "<prodTrPrice>123213</prodTrPrice>"
            + "<prodNum>2</prodNum>"
            + "<prodRebType>21</prodRebType>"
            + "<prodRebRate>12</prodRebRate>"
            + "<prodRebMoney>12</prodRebMoney>"
            + "<prodIsPost>00</prodIsPost>"
            + "<prodPostMoney>12</prodPostMoney>"
            + "<custom1>预留</custom1>"
            + "<custom2>预留</custom2>"
            + "<custom3>预留</custom3>"
            + "</req0000000001branch>"
            + "</branch>"
            + "<branch>"
            + "<req0000000001branch>"
            + "<prodName>产品名称</prodName>"
            + "<prodType>产品类型</prodType>"
            + "<prodSrcPrice>1232</prodSrcPrice>"
            + "<prodTrPrice>123213</prodTrPrice>"
            + "<prodNum>2</prodNum>"
            + "<prodRebType>21</prodRebType>"
            + "<prodRebRate>12</prodRebRate>"
            + "<prodRebMoney>12</prodRebMoney>"
            + "<prodIsPost>00</prodIsPost>"
            + "<prodPostMoney>12</prodPostMoney>"
            + "<custom1>预留</custom1>"
            + "<custom2>预留</custom2>"
            + "<custom3>预留</custom3>"
            + "</req0000000001branch>"
            + "</branch>" + "</list>" + "</busi>" + "</mw>";
    String base64Str = Base64Util.getBASE64(str);
    System.out.println("0000000001接口base64编码结果：" + base64Str);
    return base64Str;
  }

  public static String getReq0000000002Base64() {
    String str =
        "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" + "<mw>" + "<pub>"
            + "<reqCode>0000000002</reqCode>" + "<reqSender>测试发送方</reqSender>" + "<reqTime>"
            + DateTimeUtil.getDateTime() + "</reqTime>" + "</pub>" + "<busi><!-- trunk 1次 -->"
            + "<trunk>" + "<req0000000002trunk>" + "<shopId>0003</shopId>" + "<votes>200</votes>"
            + "<votesDate>20130808080801</votesDate>" + "<votesState>00</votesState>"
            + "<custom1></custom1>" + "<custom2></custom2>" + "<custom3></custom3>"
            + "</req0000000002trunk>" + "</trunk>" + "</busi>" + "</mw>";
    String base64Str = Base64Util.getBASE64(str);
    System.out.println("0000000002接口base64编码结果：" + base64Str);
    return base64Str;
  }

  public static void main(String[] args) {
    getReq0000000001Base64();
    getReq0000000002Base64();
  }

}
