package com.myblog.service.impl;

import com.myblog.entity.Comment;
import com.myblog.entity.Post;
import com.myblog.exception.ResourceNotFoundException;
import com.myblog.payload.CommentDto;
import com.myblog.repository.CommentRepository;
import com.myblog.repository.PostRepository;
import com.myblog.service.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {


    private ModelMapper modelMapper;

    public CommentServiceImpl(PostRepository postRepository, CommentRepository commentRepository, ModelMapper modelMapper) {
        this.postRepository = postRepository;
        this.commentRepository = commentRepository;
        this.modelMapper= modelMapper;
    }

    private PostRepository postRepository;




    private CommentRepository commentRepository;
    @Override
    public CommentDto createcomment(CommentDto commentDto, Long postId) {

        Post post = postRepository.findById(postId).orElseThrow(
                () -> new ResourceNotFoundException("post not found with id" + postId));

        Comment comment =new Comment();
        comment.setEmail(commentDto.getEmail());
        comment.setText(commentDto.getText());
        comment.setPost(post);
        Comment scomment = commentRepository.save(comment);


        CommentDto dto =new CommentDto();
        dto.setId(scomment.getId());
        dto.setEmail(scomment.getEmail());
        dto.setText(scomment.getText());

        return dto;
    }

    @Override
    public void deleteComment(long id){
        commentRepository.deleteById(id);
    }



    @Override
    public CommentDto updateComment(long id, CommentDto commentDto, long postId) {

        Post post = postRepository.findById(postId).orElseThrow(
                () -> new ResourceNotFoundException("post not found with id" + postId));

        Comment comment = commentRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("comment not found with id" + id));
        Comment c = mapToEntity(commentDto);
        c.setId(comment.getId());
        c.setPost(post);
        Comment savedComment = commentRepository.save(c);
        return mapToDto(savedComment);
    }


    CommentDto mapToDto(Comment comment){

       CommentDto dto= modelMapper.map(comment , CommentDto.class);
       return dto;
    }
    Comment mapToEntity(CommentDto commentDto) {

        Comment comment = modelMapper.map(commentDto, Comment.class);
        return comment;
    }
}
