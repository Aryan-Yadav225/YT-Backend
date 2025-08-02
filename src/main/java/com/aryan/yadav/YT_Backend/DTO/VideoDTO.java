package com.aryan.yadav.YT_Backend.DTO;

import com.aryan.yadav.YT_Backend.Model.VideoStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VideoDTO {
    private String id;
    private String title;
    private String description;
    private Set<String> tags;
    private String videoUrl;
    private VideoStatus videoStatus;
    private String thumbnailUrl;

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

}