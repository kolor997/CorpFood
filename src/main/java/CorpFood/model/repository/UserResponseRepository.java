package CorpFood.model.repository;

import CorpFood.model.entity.UserResponse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserResponseRepository extends JpaRepository<UserResponse,Long> {
    List<UserResponse> findUserResponsesByUser_Id(Long id);
}
