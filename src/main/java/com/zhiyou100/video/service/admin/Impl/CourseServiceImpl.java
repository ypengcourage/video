package com.zhiyou100.video.service.admin.Impl;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhiyou100.video.mapper.CourseMapper;
import com.zhiyou100.video.mapper.SubjectMapper;
import com.zhiyou100.video.model.Course;
import com.zhiyou100.video.model.Subject;
import com.zhiyou100.video.service.admin.CourseService;
import com.zhiyou100.video.util.Page;
@Service
public class CourseServiceImpl implements CourseService {
	@Autowired
	CourseMapper cm;
	
	@Autowired
	SubjectMapper sbm;
	
	@Override
	public List<Course> findAllCourse() {
		// TODO Auto-generated method stub
		return cm.selectByExample(null);
	}

	@Override
	public Page<Course> loadPage(Integer page) {
		Page<Course> ppp = new Page<>();
		ppp.setPage(page);
		ppp.setSize(5);
		ppp.setRows(cm.findCourseBypage((page-1)*5));
		ppp.setTotal(cm.countCourse(page));
		return ppp;
	}

	@Override
	public List<Subject> findAllSubject() {
		return sbm.selectByExample(null);
	}

	@Override
	public void addCourse(Course cc) {
		cc.setInsertTime(new Timestamp(System.currentTimeMillis()));
		cm.insertSelective(cc);
		
	}

	@Override
	public Course findCourseById(int id) {
		return cm.selectByPrimaryKey(id);
	}

	@Override
	public void updateCourse(Course cc) {
		cc.setUpdateTime(new Timestamp(System.currentTimeMillis()));
		cm.updateByPrimaryKeySelective(cc);
		
	}
	
	@Override
	public void deleteCourse(int id) {
		cm.deleteByPrimaryKey(id);
		
	}



}
