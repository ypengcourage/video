package com.zhiyou100.video.web.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zhiyou100.video.model.Course;
import com.zhiyou100.video.model.Subject;
import com.zhiyou100.video.service.admin.CourseService;
import com.zhiyou100.video.util.Page;

@Controller
@RequestMapping("/admin/course")
public class CourseController {
	@Autowired
	CourseService cs;
	
	@RequestMapping("/courseList.action")
	public ModelAndView courseList(@RequestParam(value="page",required=true,defaultValue="1") Integer page){
		ModelAndView mav = new ModelAndView();
		Page<Course> ppp = cs.loadPage(page);
		mav.addObject("page" ,ppp);
		mav.setViewName("/admin/course/courseList");
		return mav;
	}
	@RequestMapping(value="/addCourse.action",method=RequestMethod.GET)
	public ModelAndView addCourse1(){
		ModelAndView mav = new ModelAndView();
		List<Subject> list = cs.findAllSubject();
		mav.addObject("subject",list);
		mav.setViewName("/admin/course/addCourse");
		return mav;
	}
	@RequestMapping(value="/addCourse.action",method=RequestMethod.POST)
	public String addCourse2(Course cc){
		cs.addCourse(cc);
		return "redirect:/admin/course/courseList.action";
	}
	@RequestMapping(value="/updateCourse.action",method=RequestMethod.GET)
	public ModelAndView updateCourse1(int id){
		ModelAndView mav = new ModelAndView();
		List<Subject> list = cs.findAllSubject();
		Course cc = cs.findCourseById(id);
		mav.addObject("course",cc);
		mav.addObject("subject",list);
		mav.setViewName("/admin/course/updateCourse");
		return mav;
	}
	@RequestMapping(value="/updateCourse.action",method=RequestMethod.POST)
	public String updateCourse2(Course cc){
		cs.updateCourse(cc);
		return "redirect:/admin/course/courseList.action";
	}
	@RequestMapping("/deleteCourse.action")
	@ResponseBody
	public String deleteCourse(int id){
		cs.deleteCourse(id);
		return "success";
	}
}
