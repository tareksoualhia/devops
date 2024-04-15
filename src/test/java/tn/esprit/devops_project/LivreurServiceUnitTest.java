
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import tn.esprit.devops_project.entities.Livreur;
import tn.esprit.devops_project.repositories.LivreurRepository;
import tn.esprit.devops_project.services.LivreurServiceImpl;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
public class LivreurServiceUnitTest {
    @Mock
    LivreurRepository LivreurRepository;
    @InjectMocks
    LivreurServiceImpl iLivreurService;

    @BeforeEach
    public void setup() {

    }

    @Test
    void testGetUniveristesList() {
        Livreur Livreur1 = new Livreur(9, "ben");
        Livreur Livreur2 = new Livreur(8, "kevin");
        when(LivreurRepository.findAll()).thenReturn(Arrays.asList(Livreur1, Livreur2));
        List<Livreur> LivreurList = iLivreurService.retrieveAllLivreurs();
        assertEquals(2, LivreurList.size());
        assertEquals("ben", LivreurList.get(0).getNomLivreur());
        assertEquals("kevin", LivreurList.get(1).getNomLivreur());
    }


    @Test
    void testGetUniveristeById() {
        Livreur Livreur = new Livreur(10, "george");
        when(LivreurRepository.findById(10)).thenReturn(Optional.of(Livreur));
        Livreur LivreurById = iLivreurService.retrieveLivreur(10);
        assertNotEquals(null, LivreurById);
        assertEquals("george", LivreurById.getNomLivreur());
    }

    @Test
    void testGetInvalidLivreurById() {
        when(LivreurRepository.findById(17)).thenThrow(new RuntimeException("Livreur Not Found with ID"));

        Exception exception = assertThrows(RuntimeException.class, () -> {
            iLivreurService.retrieveLivreur(17);
        });

        assertTrue(exception.getMessage().contains("Livreur Not Found with ID"));
    }


    @Test
    void testCreateLivreur() {
        Livreur Livreur = new Livreur(12, "john");
        iLivreurService.addLivreur(Livreur);
        verify(LivreurRepository, times(1)).save(Livreur);
        ArgumentCaptor<Livreur> LivreurArgumentCaptor = ArgumentCaptor.forClass(Livreur.class);
        verify(LivreurRepository).save(LivreurArgumentCaptor.capture());
        Livreur LivreurCreated = LivreurArgumentCaptor.getValue();
        assertNotNull(LivreurCreated.getIdLivreur());
        assertEquals("john", LivreurCreated.getNomLivreur());
    }

    @Test
    void testDeleteLivreur() {
        Livreur Livreur = new Livreur(13, "simen");
        when(LivreurRepository.findById(13)).thenReturn(Optional.of(Livreur));
        iLivreurService.deleteLivreur(Livreur.getIdLivreur());
        // Verify that the delete method is called with the correct argument
        verify(LivreurRepository, times(1)).delete(Livreur);
        // Optional: You can also use ArgumentCaptor to capture the deleted entity and make additional assertions
        ArgumentCaptor<Livreur> LivreurArgumentCaptor = ArgumentCaptor.forClass(Livreur.class);
        verify(LivreurRepository).delete(LivreurArgumentCaptor.capture());
        Livreur deletedLivreur = LivreurArgumentCaptor.getValue();
        assertNotNull(deletedLivreur);
        assertEquals(13, deletedLivreur.getIdLivreur());
    }

}
