package com.tzmb2c.web.dao;

import java.sql.SQLException;

import com.tzmb2c.web.pojo.UserCertificatesPhotoPojo;

public interface UserCertificatesPhotoDao {

  public void insertUserCertificatesPhoto(UserCertificatesPhotoPojo userCertificatesPhotoPojo)
      throws SQLException;

  public UserCertificatesPhotoPojo findUserCertificatesPhotoByUid(Long id) throws SQLException;

  public void delUserCertificatesPhotoById(Long id) throws SQLException;

  public void checkUserCertificatesPhoto(Long id) throws SQLException;

  public void uncheckUserCertificatesPhoto(Long id) throws SQLException;

  public void updateUserCertificatesPhoto(UserCertificatesPhotoPojo userCertificatesPhotoPojo)
      throws SQLException;


}
