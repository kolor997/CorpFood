package CorpFood.model.repository;

import CorpFood.model.entity.UserResponse;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserResponseRepository extends JpaRepository<UserResponse,Long> {
}
