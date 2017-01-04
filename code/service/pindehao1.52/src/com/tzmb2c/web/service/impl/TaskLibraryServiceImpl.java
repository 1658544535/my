package com.tzmb2c.web.service.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.web.dao.TaskLibraryDao;
import com.tzmb2c.web.pojo.TaskPojo;
import com.tzmb2c.web.service.TaskLibraryService;

public class TaskLibraryServiceImpl implements TaskLibraryService {
  @Autowired
  private TaskLibraryDao taskLibraryDao;

  

	@Override
	public int findTaskLibraryCount(Map<String, Object> map) throws SQLException {
		return taskLibraryDao.findTaskLibraryCount(map);
	}

	@Override
	public List<TaskPojo> findTaskLibraryList(Map<String, Object> map) throws SQLException {
		return taskLibraryDao.findTaskLibraryList(map);
	}
	
	@Override
	public TaskPojo findTaskLibraryById(Long id) throws SQLException {
		return taskLibraryDao.findTaskLibraryById(id);
	}
	
	@Override
	public void insertTaskLibrary(TaskPojo taskPojo) throws SQLException {
		taskLibraryDao.insertTaskLibrary(taskPojo);
		
	}
	
	@Override
	public void delTaskLibrary(Long id) throws SQLException {
		taskLibraryDao.delTaskLibrary(id);
		
	}
	
	@Override
	public void updateTaskLibrary(TaskPojo taskPojo) throws SQLException {
		taskLibraryDao.updateTaskLibrary(taskPojo);
		
	}

  
}
