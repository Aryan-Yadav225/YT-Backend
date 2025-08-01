package com.aryan.yadav.YT_Backend.Service;

import com.aryan.yadav.YT_Backend.DTO.UploadVideoResponse;
import com.aryan.yadav.YT_Backend.DTO.VideoDTO;
import com.aryan.yadav.YT_Backend.Model.Video;
import com.aryan.yadav.YT_Backend.Repository.VideoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class VideoService {

    private final S3Service s3Service;
    private final VideoRepository videoRepository;

    public VideoService(S3Service s3Service, VideoRepository videoRepository) {
        this.s3Service = s3Service;
        this.videoRepository = videoRepository;
    }

    public UploadVideoResponse uploadVideo(MultipartFile multipartFile) {
        // upload to s3
        String videoUrl = s3Service.uploadFile(multipartFile);

        // save to db
        var video = new Video();
        video.setVideoUrl(videoUrl);
        var savedVideo = videoRepository.save(video);
        return new UploadVideoResponse(savedVideo.getId(), videoUrl);
    }

    public VideoDTO editVideo(VideoDTO videoDTO) {
        //find the video by id
        var savedVideo =  this.getVideoById(videoDTO.getId());
        //map the videoDTO fields to video
        savedVideo.setTitle(videoDTO.getTitle());
        savedVideo.setDescription(videoDTO.getDescription());
        savedVideo.setTags(videoDTO.getTags());
        savedVideo.setThumbnailUrl(videoDTO.getThumbnailUrl());
        savedVideo.setVideoStatus(videoDTO.getVideoStatus());
        //save the video to db
        videoRepository.save(savedVideo);
        return videoDTO;
    }

    public String uploadThumbnail(MultipartFile file, String videoId) {
        //find the video by id
        var savedVideo =  this.getVideoById(videoId);
        //upload the MultipartFile to s3
        String thumbnailUrl = s3Service.uploadFile(file);
        //update the thumbnailUrl in db
        savedVideo.setThumbnailUrl(thumbnailUrl);
        videoRepository.save(savedVideo);
        return thumbnailUrl;
    }

    private Video getVideoById(String videoId) {
        return videoRepository.findById(videoId)
                .orElseThrow(() -> new IllegalArgumentException("Cannot find video by id - " + videoId));
    }
}
