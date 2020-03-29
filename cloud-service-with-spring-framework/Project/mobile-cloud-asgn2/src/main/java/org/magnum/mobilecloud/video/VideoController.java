package org.magnum.mobilecloud.video;

import java.security.Principal;
import java.util.Collection;

import org.magnum.mobilecloud.video.repository.Video;
import org.magnum.mobilecloud.video.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
		videoService.saveVideoMetadata(video);
		return video;
	}

	@RequestMapping(method = RequestMethod.GET, value = "/video/{id}")
	public @ResponseBody ResponseEntity<Video> getVideo(@PathVariable("id") Long id) {
		Video video = videoService.getVideoById(id);

		if (video == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(video, HttpStatus.OK);
		}
	}

	@RequestMapping(method = RequestMethod.POST, value = "video/{id}/like")
	public @ResponseBody ResponseEntity<Void> likeVideo(@PathVariable("id") Long id, Principal p) {
		Video video = videoService.getVideoById(id);

		if (video == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			if (video.getLikedBy().contains(p.getName())) {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
			video.getLikedBy().add(p.getName());
			video.setLikes(video.getLikes() + 1);
			videoService.saveVideoMetadata(video);
			return new ResponseEntity<>(HttpStatus.OK);
		}
	}

	@RequestMapping(method = RequestMethod.POST, value = "video/{id}/unlike")
	public @ResponseBody ResponseEntity<Void> unlikeVideo(@PathVariable("id") Long id, Principal p) {
		Video video = videoService.getVideoById(id);

		if (video == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			if (video.getLikedBy().contains(p.getName())) {
				video.getLikedBy().remove(p.getName());
				video.setLikes(video.getLikes() - 1);
				videoService.saveVideoMetadata(video);
				return new ResponseEntity<>(HttpStatus.OK);
			}
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(method = RequestMethod.GET, value = "/video/search/findByName")
	public @ResponseBody Collection<Video> findByName(@RequestParam("title") String title) {
		return videoService.findByName(title);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/video/search/findByDurationLessThan")
	public @ResponseBody Collection<Video> findByDurationLessThan(@RequestParam("duration") Long duration) {
		return videoService.findByDurationLessThan(duration);
	}

}
