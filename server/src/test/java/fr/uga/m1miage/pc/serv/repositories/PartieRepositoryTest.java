package fr.uga.m1miage.pc.serv.repositories;



import fr.uga.m1miage.pc.restApi.enums.StatutJeuEnum;
import fr.uga.m1miage.pc.restApi.enums.StatutPartieEnum;
import fr.uga.m1miage.pc.serv.models.JeuEntity;
import fr.uga.m1miage.pc.serv.models.PartieEntity;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.TestConfiguration;


@ExtendWith(MockitoExtension.class)
@TestConfiguration
class PartieRepositoryTest {

    @Mock
    private PartieRepository partieRepository;


    @Test
    void findByJeuIdAndStatut() {


        JeuEntity jeu = JeuEntity
                .builder()
                .statut(StatutJeuEnum.EN_COURS)
                .build();
        PartieEntity partie = PartieEntity
                .builder()
                .statut(StatutPartieEnum.EN_COURS)
                .jeu(jeu)
                .build();
        StatutPartieEnum statutPartieEnum = StatutPartieEnum.EN_COURS;

        Mockito.when(partieRepository.findByJeuIdAndStatut(jeu.getId(), statutPartieEnum)).thenReturn(partie);

        PartieEntity result = partieRepository.findByJeuIdAndStatut(jeu.getId(), statutPartieEnum);


        Assertions.assertThat(result).isNotNull();
        Assertions.assertThat(result.getStatut()).isEqualTo(StatutPartieEnum.EN_COURS);
        Assertions.assertThat(result.getJeu()).isEqualTo(jeu);
    }

}
