package com.tzmb2c.common;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.dispatcher.multipart.JakartaMultiPartRequest;

/**
 * 
 * 重写struts2的转换器
 * 
 * @author EricChen
 * 
 */
public class RequestParseWrapper extends JakartaMultiPartRequest {
  @Override
  public void parse(HttpServletRequest servletRequest, String saveDir) throws IOException {

  }
}
