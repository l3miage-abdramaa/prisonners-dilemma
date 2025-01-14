package fr.uga.m1miage.pc.serv.mappers;



import fr.uga.m1miage.pc.serv.models.JeuEntity;
import fr.uga.m1miage.pc.serv.models.JoueurEntity;
import fr.uga.m1miage.pc.serv.models.PartieEntity;
import fr.uga.m1miage.pc.serv.models.PartieJoueurEntity;
import fr.uga.m1miage.pc.restApi.response.*;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface GlobalMapper {

    GlobalMapper INSTANCE = Mappers.getMapper(GlobalMapper.class);

    JeuCreationResponseDTO mapJeuEntityToJeuCreationResponseDTO(JeuEntity jeu) ;
    
    JeuConnexionResponseDTO mapJeuEntityToJeuConnexionResponseDTO(JeuEntity jeu) ;

    JeuDTO mapJeuEntityToJeuDto(JeuEntity entity);

    JoueurDTO mapJoueurEntityToJoueurDTO(JoueurEntity joueur);


    PartieDetailsDTO mapPartieEntityToPartieDetailsDTO(PartieEntity partie);

    PartieJoueurDTO mapPartieJoueurEntityToPartieJoueurDTO(PartieJoueurEntity partieJoueurEntity);



}

