package org.magnum.dataup;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.magnum.dataup.model.Video;
import org.magnum.dataup.model.VideoStatus;
import org.magnum.dataup.model.VideoStatus.VideoState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class VideoController {

	@Autowired
	private VideoService videoService;

	@RequestMapping(value = "/video", method = RequestMethod.GET)
	public @ResponseBody Collection<Video> getVideos() {
		return videoService.getVideos();
	}

	@RequestMapping(method = RequestMethod.POST, value = "/video")
	public @ResponseBody Video save(@RequestBody Video video) {
		videoService.save(video);
		video.setDataUrl(getDataUrl(video.getId()));
		return video;
	}

	@RequestMapping(method = RequestMethod.POST, value = "/video/{id}/data")
	public @ResponseBody ResponseEntity<VideoStatus> addData(@PathVariable("id") Long id,
			@RequestParam("data") MultipartFile file) throws IOException {

		try {
			InputStream data = file.getInputStream();
			videoService.addData(id, data);
			return new ResponseEntity<>(new VideoStatus(VideoState.READY), HttpStatus.OK);
		} catch (Exception exception) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping(method = RequestMethod.GET, value = "/video/{id}/data")
	public @ResponseBody ResponseEntity<Void> getData(@PathVariable("id") Long id, HttpServletResponse httpServletResponse) {
		try {
			videoService.getData(id, httpServletResponse.getOutputStream());
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (IOException exception) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	private String getDataUrl(long videoId) {
		return getUrlBaseForLocalServer() + "/video/" + videoId + "/data";
	}

	private String getUrlBaseForLocalServer() {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getRequest();
		return "http://" + request.getServerName()
				+ ((request.getServerPort() != 80) ? ":" + request.getServerPort() : "");
	}

}
