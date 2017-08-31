package com.zhiyou100.video.web.controller.front;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.zhiyou100.video.model.Course;
import com.zhiyou100.video.model.Speaker;
import com.zhiyou100.video.model.Subject;
import com.zhiyou100.video.model.Video;
import com.zhiyou100.video.service.admin.CourseService;
import com.zhiyou100.video.service.admin.SpeakerService;
import com.zhiyou100.video.service.admin.VideoService;

@Controller
public class FrontVideoController {
	@Autowired
	VideoService vs;
	@Autowired
	CourseService cs;
	@Autowired
	SpeakerService ss;
	@RequestMapping("/front/video/index.do")
	public String videoPlay(Integer videoId,Integer subjectId,Model md) {
		Subject sb = cs.findSubjectById(subjectId);
		
		
		md.addAttribute("videoId",videoId);
		md.addAttribute("subject",sb);
		md.addAttribute("subjectId",subjectId);
		return "/front/video/index";
		
	}
	
	
	@RequestMapping("/front/video/videoData.do")
	public String videoData(Integer videoId,Model md){
		Video video = vs.findVideoById(videoId);
		Speaker speaker = ss.findSpeakerById(video.getSpeakerId());
		Course course = cs.findCourseById(video.getCourseId());
		List<Video> list = vs.findVideoByCourseId(video.getCourseId());
		md.addAttribute("videoList",list);
		md.addAttribute("subjectId",course.getSubjectId());
		md.addAttribute("video",video);
		md.addAttribute("course",course);
		md.addAttribute("speaker",speaker);
		//System.out.println(speaker);
		return "/front/video/content";
	}///front/video/state.do?videoId
	
	@RequestMapping(value="/front/video/state.do",method=RequestMethod.GET)
	public void state(Integer videoId){
		vs.addVideoPlayTimes(videoId);
	}
	
}
