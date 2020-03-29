package org.magnum.mobilecloud.video.service;

import java.util.Collection;

import org.magnum.mobilecloud.video.repository.Video;
import org.magnum.mobilecloud.video.repository.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VideoService {

	@Autowired
	private VideoRepository videoRepository;

	public Collection<Video> getVideos() {
		return (Collection<Video>) videoRepository.findAll();
	}

	public Video saveVideoMetadata(Video video) {
		return videoRepository.save(video);
	}

	public Video getVideoById(long id) {
		return videoRepository.findOne(id);
	}

	public Collection<Video> findByName(String name) {
		return videoRepository.findByName(name);
	}

	public Collection<Video> findByDurationLessThan(Long duration) {
		return videoRepository.findByDurationLessThan(duration);
	}

}
