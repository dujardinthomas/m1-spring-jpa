package ynov_m1.post.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ynov_m1.post.model.PostsDAO;

@Repository
public interface PostsRepository extends JpaRepository<PostsDAO, Long> {

}
