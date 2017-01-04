package com.tzmb2c.web.service.impl;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.web.dao.UserCertificatesPhotoDao;
import com.tzmb2c.web.pojo.UserCertificatesPhotoPojo;
import com.tzmb2c.web.service.UserCertificatesPhotoService;

public class UserCertificatesPhotoServiceImpl implements UserCertificatesPhotoService {

  @Autowired
  private UserCertificatesPhotoDao userCertificatesPhotoDao;

  @Override
  public void insertUserCertificatesPhoto(UserCertificatesPhotoPojo userCertificatesPhotoPojo)
      throws SQLException {
    // TODO Auto-generated method stub
    userCertificatesPhotoDao.insertUserCertificatesPhoto(userCertificatesPhotoPojo);
  }

  @Override
  public UserCertificatesPhotoPojo findUserCertificatesPhotoByUid(Long id) throws SQLException {
    // TODO Auto-generated method stub
    return userCertificatesPhotoDao.findUserCertificatesPhotoByUid(id);
  }

  @Override
  public void delUserCertificatesPhotoById(Long id) throws SQLException {
    // TODO Auto-generated method stub
    userCertificatesPhotoDao.delUserCertificatesPhotoById(id);
  }

  @Override
  public void checkUserCertificatesPhoto(Long id) throws SQLException {
    // TODO Auto-generated method stub
    userCertificatesPhotoDao.checkUserCertificatesPhoto(id);
  }

  @Override
  public void uncheckUserCertificatesPhoto(Long id) throws SQLException {
    // TODO Auto-generated method stub
    userCertificatesPhotoDao.uncheckUserCertificatesPhoto(id);
  }

  @Override
  public void updateUserCertificatesPhoto(UserCertificatesPhotoPojo userCertificatesPhotoPojo)
      throws SQLException {
    // TODO Auto-generated method stub
    userCertificatesPhotoDao.updateUserCertificatesPhoto(userCertificatesPhotoPojo);
  }

}
