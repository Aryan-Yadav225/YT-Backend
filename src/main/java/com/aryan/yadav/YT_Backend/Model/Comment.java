package com.aryan.yadav.YT_Backend.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Comment {

    @Id
    private String id;
    private String text;
    private String authorId;
    private Integer likeCount;
    private Integer disLikeCount;
}