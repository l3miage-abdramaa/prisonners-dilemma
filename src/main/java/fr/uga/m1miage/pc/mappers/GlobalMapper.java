package fr.uga.m1miage.pc.mappers;

import fr.uga.m1miage.pc.jeu.models.JeuEntity;
import fr.uga.m1miage.pc.jeu.response.JeuConnexionResponseDTO;
import fr.uga.m1miage.pc.jeu.response.JeuCreationResponseDTO;
import fr.uga.m1miage.pc.jeu.response.JeuDTO;
import fr.uga.m1miage.pc.jeu.models.JoueurEntity;
import fr.uga.m1miage.pc.jeu.response.JoueurDTO;
import fr.uga.m1miage.pc.jeu.models.PartieEntity;
import fr.uga.m1miage.pc.jeu.models.PartieJoueurEntity;
import fr.uga.m1miage.pc.jeu.response.PartieDTO;
import fr.uga.m1miage.pc.jeu.response.PartieDetailsDTO;
import fr.uga.m1miage.pc.jeu.response.PartieJoueurDTO;
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
