package uz.pdp.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.domain.Result;

@Repository
public interface ResultRepo extends JpaRepository<Result,Long> {
}
