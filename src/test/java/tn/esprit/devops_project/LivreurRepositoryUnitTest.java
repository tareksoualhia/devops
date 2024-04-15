import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import tn.esprit.devops_project.entities.Livreur;
import tn.esprit.devops_project.repositories.LivreurRepository;

import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(SpringExtension.class)
public class LivreurRepositoryUnitTest {
    @Mock
    LivreurRepository LivreurRepository;

    @BeforeEach
    void setUp() {
        LivreurRepository.save(new Livreur(1, "Manar"));
        LivreurRepository.save(new Livreur(2, "Sfax"));
    }

    @AfterEach
    void destroy() {
        LivreurRepository.deleteAll();
    }

    @Test
    void testGetInvalidLivreur() {
        assertThrows(NoSuchElementException.class, () -> {
            LivreurRepository.findById(1).get();
        });
    }

    @Test
    void testDeleteLivreur() {
        Livreur saved = new Livreur(5, "ron");
        LivreurRepository.save(saved);
        LivreurRepository.delete(saved);

        assertThrows(NoSuchElementException.class, () -> {
            LivreurRepository.findById(5).get();
        });
    }

}
