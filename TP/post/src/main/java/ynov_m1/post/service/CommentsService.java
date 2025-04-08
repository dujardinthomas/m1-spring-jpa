package ynov_m1.post.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ynov_m1.post.dto.CommentsDTO;
import ynov_m1.post.model.CommentsDAO;
import ynov_m1.post.repository.CommentsRepository;
import ynov_m1.post.tools.CrudService;

@Service
public class CommentsService implements CrudService<CommentsDAO, CommentsDTO, Long>{


    @Autowired
    private CommentsRepository commentsRepository;

    @Autowired
    private PostsService postsService;

    @Override
    public CommentsDTO save(CommentsDTO entity) {
        return convertDaoToDto(commentsRepository.save(convertDtoToDao(entity)));
    }

    @Override
    public CommentsDTO findById(Long id) {
        return convertDaoToDto(commentsRepository.findById(id).orElse(null));
    }

    @Override
    public List<CommentsDTO> findAll() {
        List<CommentsDAO> comments = commentsRepository.findAll();
        return comments.stream().map(this::convertDaoToDto).toList();
    }

    @Override
    public CommentsDTO update(Long id, CommentsDTO entity) {
        CommentsDAO commentsDAO = commentsRepository.findById(id).orElse(null);
        if (commentsDAO == null) return null;
        commentsDAO.setBody(entity.getBody());
        commentsDAO.setEmail(entity.getEmail());
        commentsDAO.setName(entity.getName());
        commentsDAO.setPost(postsService.convertDtoToDao(postsService.findById(entity.getPostId())));
        return convertDaoToDto(commentsRepository.save(commentsDAO));
    }

    @Override
    public void delete(Long id) {
        CommentsDAO commentsDAO = commentsRepository.findById(id).orElseThrow(() -> new RuntimeException("Comment not found"));
        commentsRepository.delete(commentsDAO);
    }

    @Override
    public CommentsDTO convertDaoToDto(CommentsDAO entity) {
        if (entity == null) return null;
        CommentsDTO commentsDTO = new CommentsDTO();
        commentsDTO.setId(entity.getId());
        commentsDTO.setPostId(entity.getPost().getId());
        commentsDTO.setBody(entity.getBody());
        commentsDTO.setEmail(entity.getEmail());
        commentsDTO.setName(entity.getName());  
        commentsDTO.setCreatedAt(LocalDate.now());
        return commentsDTO;
    }

    @Override
    public CommentsDAO convertDtoToDao(CommentsDTO entity) {
        if (entity == null) return null;
        CommentsDAO commentsDAO = new CommentsDAO();
        commentsDAO.setId(entity.getId());
        commentsDAO.setPost(postsService.convertDtoToDao(postsService.findById(entity.getPostId())));
        commentsDAO.setBody(entity.getBody());
        commentsDAO.setEmail(entity.getEmail());
        commentsDAO.setName(entity.getName());  
        return commentsDAO;
    }

    public List<CommentsDTO> findAllByPostId(Long postId) {
        List<CommentsDAO> comments = commentsRepository.findAllByPostId(postId);
        return comments.stream().map(this::convertDaoToDto).toList();
    }
    
}
