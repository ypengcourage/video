package com.zhiyou100.video.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zhiyou100.video.model.Speaker;
import com.zhiyou100.video.service.SpeakerService;
import com.zhiyou100.video.util.Page;

@Controller
@RequestMapping("/speaker")
public class SpeakerController {
	@Autowired
	SpeakerService ss;
	@RequestMapping("/speakerList.action")
	public ModelAndView speakerList(@RequestParam(value="page",required=true,defaultValue="1") Integer page,
			@RequestParam(value="speakerName",required=true,defaultValue="") String speakerName,
			@RequestParam(value="speakerJob",required=true,defaultValue="") String speakerJob
			){
		Page<Speaker> ppp = ss.loadPage(speakerName,speakerJob,page);
		ModelAndView mav = new ModelAndView();
		//ss.findAllSpeaker()
		mav.addObject("page",ppp);
		mav.setViewName("/speaker/speakerList");
		return mav;
	}
	
	@RequestMapping(value="addSpeaker.action",method=RequestMethod.GET)
	public String addSpeaker() {
		return "/speaker/addSpeaker";
	}
	@RequestMapping(value="addSpeaker.action",method=RequestMethod.POST)
	public String addSpeaker2(Speaker sk) {
		ss.addpeaker(sk);
		return "redirect:/speaker/speakerList.action";
	}
	@RequestMapping(value="updateSpeaker.action",method=RequestMethod.GET)
	public ModelAndView updateSpeaker1(Integer id) {
		ModelAndView mav = new ModelAndView();
		Speaker sk = ss.findSpeakerById(id);
		mav.addObject("speaker",sk);
		mav.setViewName("/speaker/updateSpeaker");
		return mav;
	}
	@RequestMapping(value="updateSpeaker.action",method=RequestMethod.POST)
	public String updateSpeaker2(Speaker sk) {
		ss.updateSpeaker(sk);
		return "redirect:/speaker/speakerList.action";
	}
	@RequestMapping("deleteSpeaker.action")
	@ResponseBody
	public String deleteSpeaker(Integer id) {
		ss.deleteSpeaker(id);
		return "success";
	}
	
	
	
}
