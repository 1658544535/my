package com.tzmb2c.web.dao.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.web.dao.ManufacturerDao;
import com.tzmb2c.web.mapper.ManufacturerMapper;
import com.tzmb2c.web.pojo.ManufacturerPojo;

public class ManufacturerDaoImpl implements ManufacturerDao {

  @Autowired
  private ManufacturerMapper manufacturerMapper;

  @Override
  public void insertManufacturer(ManufacturerPojo manufacturerPojo) throws SQLException {
    try {
      manufacturerMapper.insertManufacturer(manufacturerPojo);
    } catch (Exception e) {
      e.printStackTrace();
    }

  }

  @Override
  public List<ManufacturerPojo> getManufacturerAll() throws SQLException {
    return manufacturerMapper.getManufacturerAll();
  }

  @Override
  public void updateManufacturer(ManufacturerPojo manufacturerPojo) throws SQLException {

    manufacturerMapper.updateManufacturer(manufacturerPojo);
  }

  @Override
  public ManufacturerPojo getfindByIdManufacturer(Long id) throws SQLException {
    return manufacturerMapper.getfindByIdManufacturer(id);

  }

  @Override
  public int manufacturerAllCount(Map<String, Object> map) {
    // TODO Auto-generated method stub
    return manufacturerMapper.manufacturerAllCount(map);
  }

  @Override
  public List<ManufacturerPojo> manufacturerAllList(Map<String, Object> map) {
    // TODO Auto-generated method stub
    return manufacturerMapper.manufacturerAllList(map);
  }

  @Override
  public void checkManufacturer(Long id) throws SQLException {

    manufacturerMapper.checkManufacturer(id);
  }

  @Override
  public void uncheckManufacturer(Long id) throws SQLException {

    manufacturerMapper.uncheckManufacturer(id);
  }

  @Override
  public ManufacturerPojo findManufacturerById(Long id) throws SQLException {
    return manufacturerMapper.getfindByIdManufacturer(id);
  }

  @Override
  public ManufacturerPojo findManufacturerByUserId(Long id) throws SQLException {
    return manufacturerMapper.getfindByUserIdManufacturer(id);
  }

  @Override
  public void delManufacturer(Long id) throws SQLException {
    manufacturerMapper.delManufacturer(id);

  }

  @Override
  public void deleteManufacturer(Long id) throws SQLException {
    manufacturerMapper.deleteManufacturer(id);

  }

  @Override
  public void updateManufacturerWeb(ManufacturerPojo manufacturerPojo) throws SQLException {
    // TODO Auto-generated method stub
    manufacturerMapper.updateManufacturerWeb(manufacturerPojo);
  }
}
