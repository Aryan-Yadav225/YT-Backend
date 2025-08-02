package com.aryan.yadav.YT_Backend.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UploadVideoResponse {

    private String videoId;
    private String videoUrl;

    public UploadVideoResponse(String videoId, String videoUrl) {
        this.videoId = videoId;
        this.videoUrl = videoUrl;
    }
}