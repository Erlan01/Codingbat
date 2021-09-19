package uz.pdp.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.entity.Task;

@Repository
public interface TaskRepo extends JpaRepository<Task,Long> {
}
