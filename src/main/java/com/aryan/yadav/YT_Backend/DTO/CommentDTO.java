package com.aryan.yadav.YT_Backend.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CommentDTO {
    private String commentText;
    private String authorId;

    public void setText(String text) {
        this.commentText = text;
    }
}