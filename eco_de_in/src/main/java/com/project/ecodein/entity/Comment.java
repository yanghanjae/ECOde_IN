package com.project.ecodein.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import com.project.ecodein.dto.Admin;
import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Table(name = "comment")
@Entity
public class Comment {

    @Id
    @Column(name="id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="comment", columnDefinition = "TEXT", nullable = false)
    private String comment;

    @Column(name = "created_date")
    @CreatedDate
    private LocalDateTime createdDate;

    @Column(name = "modified_date")
    @LastModifiedDate
    private LocalDateTime modifiedDate;

    @ManyToOne
    @JoinColumn(name = "board_no", referencedColumnName = "board_no", nullable = false)
    private Board boardNo;

    @ManyToOne
    @JoinColumn(name = "admin_id", referencedColumnName = "admin_id", nullable = false)
    private Admin admin; // 작성자


    //댓글 수정
    public void update(String comment) {
        this.comment = comment;
    }

}
