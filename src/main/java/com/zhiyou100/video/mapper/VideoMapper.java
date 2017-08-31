package com.zhiyou100.video.mapper;

import com.zhiyou100.video.model.Video;
import com.zhiyou100.video.model.VideoExample;
import com.zhiyou100.video.model.speakerVO;

import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface VideoMapper {
    int countByExample(VideoExample example);

    int deleteByExample(VideoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Video record);

    int insertSelective(Video record);

    List<Video> selectByExample(VideoExample example);

    Video selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Video record, @Param("example") VideoExample example);

    int updateByExample(@Param("record") Video record, @Param("example") VideoExample example);

    int updateByPrimaryKeySelective(Video record);

    int updateByPrimaryKey(Video record);

	List<Video> findVideoBySpeaker(speakerVO vo);

	List<Video> findVideoByPage(speakerVO vo);

	List<Video> findVideoByCourse(speakerVO vo);

	List<Video> findVideoByCourseAndSpeaker(speakerVO vo);

	int countBySou(speakerVO vo);

	List<Video> findVideoBySou(speakerVO vo);

	List<speakerVO> findstatavg();

	List<Video> selectByCourseId(Integer id);

}