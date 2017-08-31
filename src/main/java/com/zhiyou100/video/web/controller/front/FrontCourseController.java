package com.zhiyou100.video.web.controller.front;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zhiyou100.video.model.Course;
import com.zhiyou100.video.model.Subject;
import com.zhiyou100.video.service.admin.CourseService;
import com.zhiyou100.video.service.admin.VideoService;

@Controller
public class FrontCourseController {
	@Autowired
	CourseService cs;
	@Autowired
	VideoService vs;
	@RequestMapping("/front/course/index.do")
	public String courseInfo(int subjectId,Model md){
		Subject sb = cs.findSubjectById(subjectId);
		List<Course> li = cs.findCourseBysubjectId(subjectId);
		for (Course course : li) {
			course.setVideoList(vs.findVideoByCourseId(course.getId()));
		}
		md.addAttribute("courses",li);
		md.addAttribute("subjectId",subjectId);
		md.addAttribute("subject",sb);
		return "/front/course/index";
	}
}
