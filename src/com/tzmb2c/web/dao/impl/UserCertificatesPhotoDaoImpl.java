package com.tzmb2c.web.dao.impl;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.web.dao.UserCertificatesPhotoDao;
import com.tzmb2c.web.mapper.UserCertificatesPhotoMapper;
import com.tzmb2c.web.pojo.UserCertificatesPhotoPojo;

public class UserCertificatesPhotoDaoImpl implements UserCertificatesPhotoDao {

  @Autowired
  private UserCertificatesPhotoMapper userCertificatesPhotoMapper;

  @Override
  public void insertUserCertificatesPhoto(UserCertificatesPhotoPojo userCertificatesPhotoPojo)
      throws SQLException {
    // TODO Auto-generated method stub
    userCertificatesPhotoMapper.insertUserCertificatesPhoto(userCertificatesPhotoPojo);
  }

  @Override
  public UserCertificatesPhotoPojo findUserCertificatesPhotoByUid(Long id) throws SQLException {
    // TODO Auto-generated method stub
    return userCertificatesPhotoMapper.findUserCertificatesPhotoByUid(id);
  }

  @Override
  public void delUserCertificatesPhotoById(Long id) throws SQLException {
    // TODO Auto-generated method stub
    userCertificatesPhotoMapper.delUserCertificatesPhotoById(id);
  }

  @Override
  public void checkUserCertificatesPhoto(Long id) throws SQLException {
    // TODO Auto-generated method stub
    userCertificatesPhotoMapper.checkUserCertificatesPhoto(id);
  }

  @Override
  public void uncheckUserCertificatesPhoto(Long id) throws SQLException {
    // TODO Auto-generated method stub
    userCertificatesPhotoMapper.uncheckUserCertificatesPhoto(id);
  }

  @Override
  public void updateUserCertificatesPhoto(UserCertificatesPhotoPojo userCertificatesPhotoPojo)
      throws SQLException {
    // TODO Auto-generated method stub
    userCertificatesPhotoMapper.updateUserCertificatesPhoto(userCertificatesPhotoPojo);
  }

}
