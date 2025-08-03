package com.aryan.yadav.YT_Backend.Model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Document(value = "Video")
public class Video {

    @Id
    private String id;
    private String title;
    private String description;
    private String userId;
    private int likes;
    private int disLikes;
    private Set<String> tags = new HashSet<>();
    private String videoUrl;
    private VideoStatus videoStatus;
    private int viewCount;
    private String thumbnailUrl;
    private List<Comment> commentList = new ArrayList<>();

    // --- Constructors ---
    public Video() {
    }

    public Video(String id, String title, String description, String userId, int likes, int disLikes,
                 Set<String> tags, String videoUrl, VideoStatus videoStatus, int viewCount,
                 String thumbnailUrl, List<Comment> commentList) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.userId = userId;
        this.likes = likes;
        this.disLikes = disLikes;
        this.tags = tags == null ? new HashSet<>() : tags;
        this.videoUrl = videoUrl;
        this.videoStatus = videoStatus;
        this.viewCount = viewCount;
        this.thumbnailUrl = thumbnailUrl;
        this.commentList = commentList == null ? new ArrayList<>() : commentList;
    }

    // --- Getters and Setters ---
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }

    public int getLikes() { return likes; }
    public void setLikes(int likes) { this.likes = likes; }

    public int getDisLikes() { return disLikes; }
    public void setDisLikes(int disLikes) { this.disLikes = disLikes; }

    public Set<String> getTags() { return tags; }
    public void setTags(Set<String> tags) { this.tags = tags; }

    public String getVideoUrl() { return videoUrl; }
    public void setVideoUrl(String videoUrl) { this.videoUrl = videoUrl; }

    public VideoStatus getVideoStatus() { return videoStatus; }
    public void setVideoStatus(VideoStatus videoStatus) { this.videoStatus = videoStatus; }

    public int getViewCount() { return viewCount; }
    public void setViewCount(int viewCount) { this.viewCount = viewCount; }

    public String getThumbnailUrl() { return thumbnailUrl; }
    public void setThumbnailUrl(String thumbnailUrl) { this.thumbnailUrl = thumbnailUrl; }

    public List<Comment> getCommentList() { return commentList; }
    public void setCommentList(List<Comment> commentList) { this.commentList = commentList; }

    // --- Utility Methods ---

    public void incrementLikes() {
        this.likes++;
    }

    public void decrementLikes() {
        if (this.likes > 0) this.likes--;
    }

    public void incrementDisLikes() {
        this.disLikes++;
    }

    public void decrementDisLikes() {
        if (this.disLikes > 0) this.disLikes--;
    }

    public void incrementViewCount() {
        this.viewCount++;
    }

    public void addComment(Comment comment) {
        this.commentList.add(comment);
    }
}
