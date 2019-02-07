package repository;

import java.util.List;
import java.util.Optional;

import model.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
	public int insertUser(String username, String email, String password);
    public List<User> findByUsername(String username);
    public User findByEmail(String email);
}
