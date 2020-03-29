package org.magnum.mobilecloud.video.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface VideoRepository extends CrudRepository<Video, Long> {

	List<Video> findByName(String name);

	List<Video> findByDurationLessThan(Long duration);

}
