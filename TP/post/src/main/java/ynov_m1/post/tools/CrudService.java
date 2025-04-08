package ynov_m1.post.tools;

import java.util.List;

public interface CrudService<DAO, DTO, ID> {
    DTO save(DTO entity);
    DTO findById(ID id);
    List<DTO> findAll();
    DTO update(ID id,DTO entity);
    void delete(ID id);
    DTO convertDaoToDto(DAO entity);
    DAO convertDtoToDao(DTO entity);   
}
