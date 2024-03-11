package com.myblog.entity;



    import jakarta.persistence.*;
    import lombok.AllArgsConstructor;
    import lombok.Data;
    import lombok.NoArgsConstructor;


@Entity
    @Table(name="comments")
    @Data
@AllArgsConstructor
@NoArgsConstructor
    public class Comment {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private String text;

        private String email;

        @ManyToOne
        @JoinColumn(name = "post_id")
        private Post post;

        // Constructors, getters, and setters
    }


