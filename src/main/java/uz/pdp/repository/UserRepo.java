package uz.pdp.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.entity.User;

@Repository
public interface UserRepo extends JpaRepository<User,Long> {
}
