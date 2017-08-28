package com.zhiyou100.video.service;

import java.util.List;

import com.zhiyou100.video.model.Speaker;
import com.zhiyou100.video.util.Page;

public interface SpeakerService {

	Page<Speaker> loadPage(String speakerName, String speakerJob, Integer page);

	void addpeaker(Speaker sk);

	Speaker findSpeakerById(Integer id);

	void updateSpeaker(Speaker sk);

	void deleteSpeaker(Integer id);

	List<Speaker> findAllSpeaker();

}
