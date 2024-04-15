

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import tn.esprit.devops_project.controllers.LivreurController;
import tn.esprit.devops_project.controllers.dtos.LivreurDTO;
import tn.esprit.devops_project.entities.Livreur;
import tn.esprit.devops_project.services.LivreurServiceImpl;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@RunWith(MockitoJUnitRunner.class)
public class LivreurControllerTest {

    @Mock
    private LivreurServiceImpl LivreurService;

    @InjectMocks
    private LivreurController LivreurController;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        // Autres initialisations si nécessaires
    }


    @Test
    void testGetAllLivreurs() {
        // Mock data
        Livreur Livreur1 = new Livreur(1, "Livreur1");
        Livreur Livreur2 = new Livreur(2, "Livreur2");
        List<Livreur> LivreurList = Arrays.asList(Livreur1, Livreur2);

        // Mocking behavior
        when(LivreurService.retrieveAllLivreurs()).thenReturn(LivreurList);

        // Perform the test
        List<Livreur> result = LivreurController.getLivreurs();

        // Verify the interactions
        verify(LivreurService, times(1)).retrieveAllLivreurs();

        // Assertions
        assertEquals(2, result.size());
    }

    @Test
    void testGetLivreurById() {
        // Mock data
        Livreur Livreur = new Livreur(1, "Livreur1");

        // Mocking behavior
        when(LivreurService.retrieveLivreur(1)).thenReturn(Livreur);

        // Perform the test
        ResponseEntity<Livreur> responseEntity = LivreurController.retrieveLivreur(1);

        // Verify the interactions
        verify(LivreurService, times(1)).retrieveLivreur(1);

        // Assertions
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());  // Vérifiez le statut HTTP ici
        assertEquals(Livreur, responseEntity.getBody());
    }


    @Test
    void testAddLivreur() {
        // Mock data
        LivreurDTO LivreurDTO = new LivreurDTO();
        LivreurDTO.setNomLivreur("Livreur1");

        // Mocking behavior
        when(LivreurService.addLivreur(any(Livreur.class))).thenReturn(new Livreur());

        // Perform the test
        ResponseEntity<Livreur> responseEntity = LivreurController.addLivreur(LivreurDTO);

        // Verify the interactions
        verify(LivreurService, times(1)).addLivreur(any(Livreur.class));

        // Assertions
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
    }


    @Test
    void testUpdateLivreur() {
        // Mock data
        LivreurDTO LivreurDTO = new LivreurDTO();
        LivreurDTO.setNomLivreur("Livreur1");

        // Mocking behavior
        when(LivreurService.updateLivreur(any(Long.class), any(Livreur.class))).thenReturn(new Livreur());

        // Perform the test
        ResponseEntity<Livreur> responseEntity = LivreurController.updateLivreur(1L, LivreurDTO);

        // Verify the interactions
        verify(LivreurService, times(1)).updateLivreur(any(Long.class), any(Livreur.class));

        // Assertions
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
    }


    @Test
    void testDeleteLivreur() {
        // Mock data
        Livreur Livreur = new Livreur(1, "Livreur1");

        // Mocking behavior
        when(LivreurService.retrieveLivreur(1)).thenReturn(Livreur);

        // Perform the test
        ResponseEntity<Void> responseEntity = LivreurController.deleteLivreur(1);

        // Verify the interactions
        verify(LivreurService, times(1)).deleteLivreur(1);

        // Assertions
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }
}
