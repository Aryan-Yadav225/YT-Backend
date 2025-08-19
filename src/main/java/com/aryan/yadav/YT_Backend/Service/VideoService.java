package com.aryan.yadav.YT_Backend.Service;

import com.aryan.yadav.YT_Backend.DTO.CommentDTO;
import com.aryan.yadav.YT_Backend.DTO.UploadVideoResponse;
import com.aryan.yadav.YT_Backend.DTO.VideoDTO;
import com.aryan.yadav.YT_Backend.Model.Comment;
import com.aryan.yadav.YT_Backend.Model.Video;
import com.aryan.yadav.YT_Backend.Repository.VideoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class VideoService {

    private final S3Service s3Service;
    private final VideoRepository videoRepository;
    private final UserService userService;

    public VideoService(S3Service s3Service, VideoRepository videoRepository, UserService userService) {
        this.s3Service = s3Service;
        this.videoRepository = videoRepository;
        this.userService = userService;
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

    public VideoDTO getVideoDetails(String videoId) {
        var savedVideo = this.getVideoById(videoId);

        increaseVideoCount(savedVideo);
        userService.addVideoToHistory(videoId);

        return mapVideoToVideoDTO(savedVideo);
    }

    private void increaseVideoCount(Video savedVideo) {
        savedVideo.incrementViewCount();
        videoRepository.save(savedVideo);
    }

    public  VideoDTO likeVideo(String videoId) {
        //find the video by id
        var savedVideo = this.getVideoById(videoId);

        if(userService.ifLikedVideo(videoId)) {
            //If the video is already liked by the user, then decrement the like count
            savedVideo.getLikes().decrementAndGet();
            userService.removeFromLikeVideos(videoId);
        } else if(userService.ifDislikedVideo(videoId)) {
            //If user already disliked the video, then increment the like count and decrement the dislike count
            savedVideo.getDisLikes().decrementAndGet();
            savedVideo.getLikes().incrementAndGet();
            userService.addToLikeVideos(videoId);
            userService.removeFromDislikedVideos(videoId);
        }else{
            // If neither liked nor disliked, add like
            savedVideo.getLikes().incrementAndGet();
            userService.addToLikeVideos(videoId);
        }
        videoRepository.save(savedVideo);
        return mapVideoToVideoDTO(savedVideo);
    }

    public VideoDTO dislikeVideo(String videoId) {
        //find the video by id
        var savedVideo = this.getVideoById(videoId);
        if(userService.ifDislikedVideo(videoId)) {
            //If the video is already disliked by the user, then decrement the dislike count
            savedVideo.getDisLikes().decrementAndGet();
            userService.removeFromDislikedVideos(videoId);
        } else if(userService.ifLikedVideo(videoId)) {
            //If user already liked the video, then increment the dislike count and decrement the like count
            savedVideo.getLikes().decrementAndGet();
            savedVideo.getDisLikes().incrementAndGet();
            userService.addToDislikedVideos(videoId);
            userService.removeFromLikeVideos(videoId);
        }else{
            // If neither liked nor disliked, add dislike
            savedVideo.getDisLikes().incrementAndGet();
            userService.addToDislikedVideos(videoId);
        }
        videoRepository.save(savedVideo);
        return mapVideoToVideoDTO(savedVideo);
    }

    private VideoDTO mapVideoToVideoDTO(Video videoById) {
        VideoDTO videoDto = new VideoDTO();
        videoDto.setId(videoById.getId());
        videoDto.setTitle(videoById.getTitle());
        videoDto.setDescription(videoById.getDescription());
        videoDto.setTags(videoById.getTags());
        videoDto.setVideoUrl(videoById.getVideoUrl());
        videoDto.setThumbnailUrl(videoById.getThumbnailUrl());
        videoDto.setVideoStatus(videoById.getVideoStatus());
        videoDto.setLikeCount(videoById.getLikes().get());
        videoDto.setDislikeCount(videoById.getDisLikes().get());
        videoDto.setViewCount(videoById.getViewCount());
        return videoDto;
    }

    public void addComment(String videoId, CommentDTO commentDTO) {
        Video video = this.getVideoById(videoId);
        Comment comment = new Comment();
        comment.setText(commentDTO.getCommentText());
        comment.setAuthorId(commentDTO.getAuthorId());
        video.addComment(comment);

        videoRepository.save(video);
    }

    public List<CommentDTO> getAllComments(String videoId) {
        Video video = this.getVideoById(videoId);
        List<Comment> comments = video.getCommentList();

        return comments.stream().map(comment -> mapToCommentDTO(comment)).toList();
    }

    private CommentDTO mapToCommentDTO(Comment comment) {
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setText(comment.getText());
        commentDTO.setAuthorId(comment.getAuthorId());
        return commentDTO;
    }

    public List<VideoDTO> getAllVideos() {
        return videoRepository.findAll().stream().map(video -> mapVideoToVideoDTO(video)).toList();
    }
}
