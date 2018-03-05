package hello.db.repositories;

import hello.db.datasets.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long>{
    User findTopByUsername(String username);

    User findTopByEmail(String email);
}
