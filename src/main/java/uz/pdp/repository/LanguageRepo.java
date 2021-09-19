package uz.pdp.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.entity.Language;

@Repository
public interface LanguageRepo  extends JpaRepository<Language,Long> {

    boolean existsByName(String name);
}
