package com.aryan.yadav.YT_Backend.DTO;

public class UploadVideoResponse {

    private String videoId;
    private String videoUrl;

    public UploadVideoResponse() {
    }

    public UploadVideoResponse(String videoId, String videoUrl) {
        this.videoId = videoId;
        this.videoUrl = videoUrl;
    }

    public String getVideoId() {
        return videoId;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    @Override
    public String toString() {
        return "UploadVideoResponse{" +
                "videoId='" + videoId + '\'' +
                ", videoUrl='" + videoUrl + '\'' +
                '}';
    }
}
