package ynov_m1.post.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ynov_m1.post.model.CommentsDAO;

@Repository
public interface CommentsRepository extends JpaRepository<CommentsDAO, Long> {

    List<CommentsDAO> findAllByPostId(Long postId);

}
