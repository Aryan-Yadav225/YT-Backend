package com.aryan.yadav.YT_Backend.DTO;

import com.aryan.yadav.YT_Backend.Model.VideoStatus;
import java.util.Set;

public class VideoDTO {
    private String id;
    private String title;
    private String description;
    private Set<String> tags;
    private String videoUrl;
    private VideoStatus videoStatus;
    private String thumbnailUrl;
    private Integer likeCount;
    private Integer dislikeCount;
    private Integer viewCount;

    // No-args constructor
    public VideoDTO() {
    }

    // All-args constructor
    public VideoDTO(String id, String title, String description, Set<String> tags, String videoUrl,
                    VideoStatus videoStatus, String thumbnailUrl, Integer likeCount,
                    Integer dislikeCount, Integer viewCount) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.tags = tags;
        this.videoUrl = videoUrl;
        this.videoStatus = videoStatus;
        this.thumbnailUrl = thumbnailUrl;
        this.likeCount = likeCount;
        this.dislikeCount = dislikeCount;
        this.viewCount = viewCount;
    }

    // --- Getters ---
    public String getId() {
        return id;
    }
    public String getTitle() {
        return title;
    }
    public String getDescription() {
        return description;
    }
    public Set<String> getTags() {
        return tags;
    }
    public String getVideoUrl() {
        return videoUrl;
    }
    public VideoStatus getVideoStatus() {
        return videoStatus;
    }
    public String getThumbnailUrl() {
        return thumbnailUrl;
    }
    public Integer getLikeCount() {
        return likeCount;
    }
    public Integer getDislikeCount() {
        return dislikeCount;
    }
    public Integer getViewCount() {
        return viewCount;
    }

    // --- Setters ---
    public void setId(String id) {
        this.id = id;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setTags(Set<String> tags) {
        this.tags = tags;
    }
    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }
    public void setVideoStatus(VideoStatus videoStatus) {
        this.videoStatus = videoStatus;
    }
    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }
    public void setLikeCount(Integer likeCount) {
        this.likeCount = likeCount;
    }
    public void setDislikeCount(Integer dislikeCount) {
        this.dislikeCount = dislikeCount;
    }
    public void setViewCount(Integer viewCount) {
        this.viewCount = viewCount;
    }
}