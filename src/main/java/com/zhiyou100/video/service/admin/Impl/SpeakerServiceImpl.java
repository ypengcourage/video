package com.zhiyou100.video.service.admin.Impl;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhiyou100.video.mapper.SpeakerMapper;
import com.zhiyou100.video.model.Speaker;
import com.zhiyou100.video.model.SpeakerExample;
import com.zhiyou100.video.model.speakerVO;
import com.zhiyou100.video.service.admin.SpeakerService;
import com.zhiyou100.video.util.Page;
@Service
public class SpeakerServiceImpl implements SpeakerService {
	@Autowired
	SpeakerMapper sm;
	
	@Override
	public Page<Speaker> loadPage(String speakerName, String speakerJob, Integer page) {
		Page<Speaker> ppp = new Page<>();
		speakerVO vo = new speakerVO();
		vo.setPage((page-1)*5);
		vo.setSpeakerJob(speakerJob);
		vo.setSpeakerName(speakerName);
		SpeakerExample example = new SpeakerExample();
		example.createCriteria().andSpeakerNameLike("%"+speakerName+"%").andSpeakerJobLike("%"+speakerJob+"%");
		ppp.setSize(5);
		ppp.setPage(page);
		ppp.setTotal(sm.countByExample(example));
		ppp.setRows(sm.findAllSpeakerByNameAndJob(vo));
		return ppp;
	}

	@Override
	public void addpeaker(Speaker sk) {
		sk.setInsertTime(new Timestamp(System.currentTimeMillis()));
		sm.insert(sk);
	}

	@Override
	public Speaker findSpeakerById(Integer id) {
		return sm.selectByPrimaryKey(id);
	}

	@Override
	public void updateSpeaker(Speaker sk) {
		sk.setUpdateTime(new Timestamp(System.currentTimeMillis()));
		sm.updateByPrimaryKeySelective(sk);
	}

	@Override
	public void deleteSpeaker(Integer id) {
		sm.deleteByPrimaryKey(id);
	}

	@Override
	public List<Speaker> findAllSpeaker() {
		return sm.selectByExample(null);
	}
}
