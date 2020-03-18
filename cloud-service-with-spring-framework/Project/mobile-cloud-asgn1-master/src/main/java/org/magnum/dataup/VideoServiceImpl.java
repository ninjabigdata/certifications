package org.magnum.dataup;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import org.magnum.dataup.model.Video;
import org.springframework.stereotype.Service;

@Service
public class VideoServiceImpl implements VideoService {

	private VideoFileManager videoFileManager;
	private static final AtomicLong currentId = new AtomicLong(0L);

	private Map<Long, Video> videos = new HashMap<>();

	public VideoServiceImpl() throws IOException {
		videoFileManager = VideoFileManager.get();
	}

	@Override
	public Collection<Video> getVideos() {
		return videos.values();
	}

	@Override
	public Video save(Video entity) {
		checkAndSetId(entity);
		videos.put(entity.getId(), entity);
		return entity;
	}

	@Override
	public void addData(Long id, InputStream data) throws IOException {
		videoFileManager.saveVideoData(videos.get(id), data);
	}

	@Override
	public void getData(Long id, OutputStream data) throws IOException {
		if (videos.containsKey(id)) {
			videoFileManager.copyVideoData(videos.get(id), data);
		} else {
			throw new IOException();
		}
	}

	private void checkAndSetId(Video entity) {
		if (entity.getId() == 0) {
			entity.setId(currentId.incrementAndGet());
		}
	}

}
