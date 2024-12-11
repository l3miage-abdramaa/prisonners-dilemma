package fr.uga.m1miage.pc.jeu.repositories;


import fr.uga.m1miage.pc.jeu.enums.StatutJeuEnum;
import fr.uga.m1miage.pc.jeu.models.JeuEntity;
import fr.uga.m1miage.pc.jeu.enums.StatutPartieEnum;
import fr.uga.m1miage.pc.jeu.models.PartieEntity;
import fr.uga.m1miage.pc.jeu.repository.PartieRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.TestConfiguration;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

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

        when(partieRepository.findByJeuIdAndStatut(jeu.getId(), statutPartieEnum)).thenReturn(partie);

        PartieEntity result = partieRepository.findByJeuIdAndStatut(jeu.getId(), statutPartieEnum);


        assertThat(result).isNotNull();
        assertThat(result.getStatut()).isEqualTo(StatutPartieEnum.EN_COURS);
        assertThat(result.getJeu()).isEqualTo(jeu);
    }

}
