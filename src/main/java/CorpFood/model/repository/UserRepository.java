package CorpFood.model.repository;

import CorpFood.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {

    User findOneByLogin(String login);
}
