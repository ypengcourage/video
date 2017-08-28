package com.zhiyou100.video.service.admin.Impl;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhiyou100.video.mapper.VideoMapper;
import com.zhiyou100.video.model.Video;
import com.zhiyou100.video.model.VideoExample;
import com.zhiyou100.video.model.speakerVO;
import com.zhiyou100.video.service.admin.VideoService;
import com.zhiyou100.video.util.Page;
@Service
public class VideoServiceImpl implements VideoService {
	@Autowired
	VideoMapper vm;
	@Override
	public Page<Video> loadPage(Integer page, String videoTitle, int videoSpeaker, int videoCourse) {
		Page<Video> ppp = new Page<>();
		ppp.setPage(page);
		ppp.setSize(5);
		speakerVO vo = new speakerVO();
		vo.setPage((page-1)*5);
		vo.setVideoCourse(videoCourse);
		vo.setVideoTitle(videoTitle);
		vo.setVideoSpeaker(videoSpeaker);
		ppp.setTotal(vm.countBySou(vo));
		ppp.setRows(vm.findVideoBySou(vo));
		return ppp;
	}
	@Override
	public void addVideo(Video vo) {
		vo.setInsertTime(new Timestamp(System.currentTimeMillis()));
		vm.insertSelective(vo);
	}
	@Override
	public Video findVideoById(int id) {
		
		return vm.selectByPrimaryKey(id);
	}
	@Override
	public void updateVideo(Video vv) {
		vv.setUpdateTime(new Timestamp(System.currentTimeMillis()));
		vm.updateByPrimaryKeySelective(vv);
		
	}
	@Override
	public void deleteVideo(int id) {
		vm.deleteByPrimaryKey(id);
	}
	@Override
	public void deleteVideoByIds(List<Integer> asList) {
		VideoExample example = new VideoExample();
		example.createCriteria().andIdIn(asList);
		vm.deleteByExample(example);
		
	}
	@Override
	public List<speakerVO> findstatavg() {		
		return vm.findstatavg();
	}
	@Override
	public String listToArray(List<String> li1) {
		StringBuilder sb = new StringBuilder();
		sb.append("[\"");
		for(int i = 0;i<li1.size();i++){
			sb.append(li1.get(i));
			if(i == li1.size()-1){
				break;
			}
			sb.append("\",\"");
		}
		sb.append("\"]");
		/*Object[] arr =  li1.toArray();
		String[] arr1 = (String[]) arr;*/
		return sb.toString();
	}
	@Override
	public String listToArray1(List<Double> li2) {
		Object[] arr =  li2.toArray();
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		for(int i = 0;i<li2.size();i++){
			sb.append(li2.get(i));
			if(i == li2.size()-1){
				break;
			}
			sb.append(",");
		}
		sb.append("]");
		//Double[] arr1 = (Double[]) arr;
		return Arrays.toString(arr);
	}

}



/*if(videoCourse == 0){
			if(videoSpeaker == 0){
				vo.setPage((page-1)*5);
				vo.setVideoTitle(videoTitle);
				ppp.setRows(vm.findVideoByPage(vo));
				example.createCriteria().andVideoTitleLike("%"+videoTitle+"%");
				ppp.setTotal(vm.countByExample(example));
			}else{
				vo.setPage((page-1)*5);
				vo.setVideoSpeaker(videoSpeaker);
				vo.setVideoTitle(videoTitle);
				ppp.setRows(vm.findVideoBySpeaker(vo));
				example.createCriteria().andVideoTitleLike("%"+videoTitle+"%").andSpeakerIdEqualTo(videoSpeaker);
				ppp.setTotal(vm.countByExample(example));
			}
		}else{
			if(videoSpeaker == 0){
				vo.setPage((page-1)*5);
				vo.setVideoCourse(videoCourse);
				vo.setVideoTitle(videoTitle);
				ppp.setRows(vm.findVideoByCourse(vo));
				example.createCriteria().andVideoTitleLike("%"+videoTitle+"%").andCourseIdEqualTo(videoCourse);
				ppp.setTotal(vm.countByExample(example));
			}else{
				vo.setPage((page-1)*5);
				vo.setVideoCourse(videoCourse);
				vo.setVideoTitle(videoTitle);
				vo.setVideoSpeaker(videoSpeaker);
				ppp.setRows(vm.findVideoByCourseAndSpeaker(vo));
				example.createCriteria().andVideoTitleLike("%"+videoTitle+"%").andCourseIdEqualTo(videoCourse).andSpeakerIdEqualTo(videoSpeaker);
				ppp.setTotal(vm.countByExample(example));
			}
		}*/