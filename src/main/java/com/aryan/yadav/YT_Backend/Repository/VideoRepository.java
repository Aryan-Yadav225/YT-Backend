package com.aryan.yadav.YT_Backend.Repository;

import com.aryan.yadav.YT_Backend.Model.Video;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface VideoRepository extends MongoRepository<Video, String> {
}
