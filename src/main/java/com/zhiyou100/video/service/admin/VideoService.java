package com.zhiyou100.video.service.admin;

import java.util.List;

import com.zhiyou100.video.model.Video;
import com.zhiyou100.video.model.speakerVO;
import com.zhiyou100.video.util.Page;

public interface VideoService {

	Page<Video> loadPage(Integer page, String videoTitle, int videoSpeaker, int videoCourse);

	void addVideo(Video vo);

	Video findVideoById(int id);

	void updateVideo(Video vv);

	void deleteVideo(int id);

	void deleteVideoByIds(List<Integer> asList);

	List<speakerVO> findstatavg();

	String listToArray(List<String> li1);

	String listToArray1(List<Double> li2);

	List<Video> findVideoByCourseId(Integer id);

	void addVideoPlayTimes(Integer videoId);

}
