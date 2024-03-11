package com.myblog.service;

import com.myblog.payload.CommentDto;

public interface CommentService {
   CommentDto createcomment(CommentDto commentdto,
                            Long postId);

   void deleteComment(long id);

   CommentDto updateComment(long id, CommentDto commentdto, long postId);
}


