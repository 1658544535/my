package com.tzmb2c.web.action;

import maowu.framework.utils.web.SuperAction;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.web.service.UserVerifyService;

public class UserVerifyAction extends SuperAction {

  @Autowired
  private UserVerifyService userVerifyService;



}
