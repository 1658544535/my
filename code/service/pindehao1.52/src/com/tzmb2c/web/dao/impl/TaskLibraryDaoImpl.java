package com.tzmb2c.web.dao.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.web.dao.TaskLibraryDao;
import com.tzmb2c.web.mapper.TaskLibraryMapper;
import com.tzmb2c.web.pojo.TaskPojo;

public class TaskLibraryDaoImpl implements TaskLibraryDao {
  @Autowired
  private TaskLibraryMapper taskLibraryMapper;

  

	@Override
	public int findTaskLibraryCount(Map<String, Object> map) throws SQLException {
		return taskLibraryMapper.findTaskLibraryCount(map);
	}

	@Override
	public List<TaskPojo> findTaskLibraryList(Map<String, Object> map) throws SQLException {	
		return taskLibraryMapper.findTaskLibraryList(map);
	}
	

	@Override
	public TaskPojo findTaskLibraryById(Long id) throws SQLException {	
		return taskLibraryMapper.findTaskLibraryById(id);
	}
	

	@Override
	public void insertTaskLibrary(TaskPojo taskPojo) throws SQLException {
		taskLibraryMapper.insertTaskLibrary(taskPojo);	
	}
	
	
	@Override
	public void delTaskLibrary(Long id) throws SQLException {
		taskLibraryMapper.delTaskLibrary(id);
		
	}
	
	
	
	@Override
	public void updateTaskLibrary(TaskPojo taskPojo) throws SQLException {
		taskLibraryMapper.updateTaskLibrary(taskPojo);
		
	}

  
}
