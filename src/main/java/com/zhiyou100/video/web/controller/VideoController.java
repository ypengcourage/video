package com.zhiyou100.video.web.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zhiyou100.video.model.Course;
import com.zhiyou100.video.model.Speaker;
import com.zhiyou100.video.model.Video;
import com.zhiyou100.video.model.speakerVO;
import com.zhiyou100.video.service.CourseService;
import com.zhiyou100.video.service.SpeakerService;
import com.zhiyou100.video.service.VideoService;
import com.zhiyou100.video.util.Page;

@Controller
@RequestMapping("/video")
public class VideoController {
	@Autowired
	VideoService vs;
	@Autowired
	CourseService cs;
	@Autowired
	SpeakerService ss;
	@RequestMapping("/videoList.action")
	public ModelAndView videoList(@RequestParam(value="page",required=true,defaultValue="1") Integer page,
			@RequestParam(value="videoTitle",required=true,defaultValue="") String videoTitle,
			@RequestParam(value="videoSpeaker",required=true,defaultValue="0") int videoSpeaker,
			@RequestParam(value="videoCourse",required=true,defaultValue="0") int videoCourse
			){
		Page<Video> ppp = vs.loadPage(page,videoTitle,videoSpeaker,videoCourse);
		ModelAndView mav = new ModelAndView();
		List<Course> listCourse = cs.findAllCourse();
		List<Speaker> listSpeaker = ss.findAllSpeaker();
		speakerVO vo = new speakerVO();
		vo.setPage((page-1)*5);
		vo.setVideoCourse(videoCourse);
		vo.setVideoTitle(videoTitle);
		vo.setVideoSpeaker(videoSpeaker);
		mav.addObject("speakervo",vo);
		mav.addObject("listCourse",listCourse);
		mav.addObject("listSpeaker",listSpeaker);
		mav.addObject("page",ppp);
		mav.setViewName("/video/videoList");
		return mav;
	}
	
	
	@RequestMapping(value="/addVideo.action",method=RequestMethod.GET)
	public ModelAndView addVideo1(){
		ModelAndView mav = new ModelAndView();
		List<Course> listCourse = cs.findAllCourse();
		List<Speaker> listSpeaker = ss.findAllSpeaker();
		mav.addObject("listCourse",listCourse);
		mav.addObject("listSpeaker",listSpeaker);
		mav.setViewName("/video/addVideo");
		return mav;
	}
	@RequestMapping(value="/addVideo.action",method=RequestMethod.POST)
	public String addVideo2(Video vo){
		vs.addVideo(vo);
		return "redirect:/video/videoList.action";
	}
	@RequestMapping(value="/updateVideo.action",method=RequestMethod.GET)
	public ModelAndView updateVideo1(int id){
		ModelAndView mav = new ModelAndView();
		Video vv = vs.findVideoById(id);
		mav.addObject("video",vv);
		List<Course> listCourse = cs.findAllCourse();
		List<Speaker> listSpeaker = ss.findAllSpeaker();
		mav.addObject("listCourse",listCourse);
		mav.addObject("listSpeaker",listSpeaker);
		mav.setViewName("/video/updateVideo");
		return mav;
	}
	@RequestMapping(value="/updateVideo.action",method=RequestMethod.POST)
	public String updateVideo2(Video vv){
		vs.updateVideo(vv);
		return "redirect:/video/videoList.action";
	}
	@RequestMapping("/deleteVideo.action")
	@ResponseBody
	public String deleteVideo(int id){
		vs.deleteVideo(id);
		return "success";
	}
	@RequestMapping("/deleteAllVideo.action")
	public String deleteVideoByIds(Integer [] xuanze){
		List<Integer> asList = Arrays.asList(xuanze);
		vs.deleteVideoByIds(asList);
		return "redirect:/video/videoList.action";
	}
	@RequestMapping("/statshow.action")
	public ModelAndView statshow(){
		ModelAndView mav = new ModelAndView();
		List<speakerVO> li = vs.findstatavg();
		List<String> li1 = new ArrayList<>();
		List<Double> li2 = new ArrayList<>();
		for (speakerVO vo : li) {
			if(vo.getCourseName() != null){
				li1.add(vo.getCourseName());
				li2.add(vo.getTimes());
			}
		}
		String str1 = vs.listToArray(li1);
		String str2 = vs.listToArray1(li2);
		mav.addObject("courseName",str1);
		mav.addObject("times",str2);
		mav.setViewName("/stat/stat");
		return mav;
	}	
}
