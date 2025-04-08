package ynov_m1.post.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ynov_m1.post.dto.PostsDTO;
import ynov_m1.post.model.PostsDAO;
import ynov_m1.post.repository.PostsRepository;
import ynov_m1.post.tools.CrudService;

@Service
public class PostsService implements CrudService<PostsDAO, PostsDTO, Long> {

    @Autowired
    private PostsRepository postsRepository;

    @Override
    public PostsDTO save(PostsDTO entity) {
        return convertDaoToDto(postsRepository.save(convertDtoToDao(entity)));
    }

    @Override
    public PostsDTO findById(Long id) {
        PostsDAO postsDAO = postsRepository.findById(id).orElse(null);
        return convertDaoToDto(postsDAO);
    }

    @Override
    public List<PostsDTO> findAll() {
        List<PostsDAO> postsDAOs = postsRepository.findAll();
        return postsDAOs.stream().map(this::convertDaoToDto).toList();
    }

    @Override
    public PostsDTO update(Long id, PostsDTO entity) {
        PostsDAO postsDAO = postsRepository.findById(id).orElseThrow(() -> new RuntimeException("Post not found"));
        postsDAO.setTitle(entity.getTitle());
        postsDAO.setContent(entity.getContent());
        postsDAO.setDescription(entity.getDescription());
        return convertDaoToDto(postsRepository.save(postsDAO));
    }

    @Override
    public void delete(Long id) {
        postsRepository.deleteById(id);
    }

    @Override
    public PostsDTO convertDaoToDto(PostsDAO postsDAO) {
        if(postsDAO == null) {
            return null;
        }
        PostsDTO postsDTO = new PostsDTO();
        postsDTO.setId(postsDAO.getId());
        postsDTO.setTitle(postsDAO.getTitle());
        postsDTO.setContent(postsDAO.getContent());
        postsDTO.setDescription(postsDAO.getDescription());
        postsDTO.setCreatedAt(LocalDate.now());
        return postsDTO;
    }

    @Override
    public PostsDAO convertDtoToDao(PostsDTO entity) {
        if(entity == null) {
            return null;
        }
        PostsDAO postsDAO = new PostsDAO();
        postsDAO.setId(entity.getId());
        postsDAO.setTitle(entity.getTitle());
        postsDAO.setContent(entity.getContent());
        postsDAO.setDescription(entity.getDescription());
        return postsDAO;
    }

    
}
