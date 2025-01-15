package fr.uga.m1miage.pc.application.mapper;

import fr.uga.m1miage.pc.domain.model.JeuEntity;
import fr.uga.m1miage.pc.application.dto.responses.JeuConnexionResponseDTO;
import fr.uga.m1miage.pc.application.dto.responses.JeuCreationResponseDTO;
import fr.uga.m1miage.pc.application.dto.responses.JeuDTO;
import fr.uga.m1miage.pc.domain.model.JoueurEntity;
import fr.uga.m1miage.pc.application.dto.responses.JoueurDTO;
import fr.uga.m1miage.pc.domain.model.PartieEntity;
import fr.uga.m1miage.pc.domain.model.PartieJoueurEntity;
import fr.uga.m1miage.pc.application.dto.responses.PartieDTO;
import fr.uga.m1miage.pc.application.dto.responses.PartieDetailsDTO;
import fr.uga.m1miage.pc.application.dto.responses.PartieJoueurDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface GlobalMapper {
    GlobalMapper INSTANCE = Mappers.getMapper(GlobalMapper.class);

    JeuCreationResponseDTO mapJeuEntityToJeuCreationResponseDTO(JeuEntity jeu) ;
    
    JeuConnexionResponseDTO mapJeuEntityToJeuConnexionResponseDTO(JeuEntity jeu) ;

    JeuDTO mapJeuEntityToJeuDto(JeuEntity entity);

    @Mapping(source = "nomJoueur", target = "nom")
    JoueurDTO mapJoueurEntityToJoueurDTO(JoueurEntity joueur);

    @Mapping(target = "partiesJoueurs", source = "partiesJoueur")
    PartieDetailsDTO mapPartieEntityToPartieDetailsDTO(PartieEntity partie);

    PartieJoueurDTO mapPartieJoueurEntityToPartieJoueurDTO(PartieJoueurEntity partieJoueurEntity);

    PartieDTO mapPartieEntityToPartieDTO(PartieEntity partie);

}
