package org.magnum.dataup;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Collection;

import org.magnum.dataup.model.Video;

public interface VideoService {
	
	Collection<Video> getVideos();

	Video save(Video video);

	void addData(Long id, InputStream data) throws IOException;

	void getData(Long id, OutputStream data) throws IOException;

}
