package com.dqk.controller;

import com.dqk.Service.CommentService;
import com.dqk.domain.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CommentController {
    @Autowired
    private CommentService commentService;

    @GetMapping("/get/{id}")
    public Comment findById(@PathVariable("id") int comment_id){
        Comment comment = commentService.findById(comment_id);
        return comment;
    }

    @GetMapping("/update/{id}/{author}")
    public Comment updateComment(@PathVariable("id") int comment_id,@PathVariable("author") String author){
        Comment comment = commentService.findById(comment_id);
        comment.setAuthor(author);
//        comment.setId(comment_id);
        Comment updateComments = commentService.updateComment(comment);
        return updateComments;
    }

    @GetMapping("/delete/{id}")
    public void deleteComment(@PathVariable("id") int comment_id){
        commentService.deleteComment(comment_id);
    }
}
