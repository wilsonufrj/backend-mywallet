package br.projeto.mywallet.Mappers;

import br.projeto.mywallet.DTO.CarteiraDTO;
import br.projeto.mywallet.DTO.MesDTO;
import br.projeto.mywallet.Model.Carteira;
import br.projeto.mywallet.Model.Mes;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-03-21T18:26:18-0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 19.0.2 (Private Build)"
)
@Component
public class CarteiraMapperImpl implements CarteiraMapper {

    @Autowired
    private MesMapper mesMapper;

    @Override
    public CarteiraDTO toDTO(Carteira carteira) {
        if ( carteira == null ) {
            return null;
        }

        CarteiraDTO carteiraDTO = new CarteiraDTO();

        carteiraDTO.setId( carteira.getId() );
        carteiraDTO.setNome( carteira.getNome() );
        carteiraDTO.setMeses( mesListToMesDTOList( carteira.getMeses() ) );

        return carteiraDTO;
    }

    @Override
    public Carteira toEntity(CarteiraDTO carteiraDTO) {
        if ( carteiraDTO == null ) {
            return null;
        }

        Carteira carteira = new Carteira();

        carteira.setId( carteiraDTO.getId() );
        carteira.setNome( carteiraDTO.getNome() );
        carteira.setMeses( mesDTOListToMesList( carteiraDTO.getMeses() ) );

        return carteira;
    }

    protected List<MesDTO> mesListToMesDTOList(List<Mes> list) {
        if ( list == null ) {
            return null;
        }

        List<MesDTO> list1 = new ArrayList<MesDTO>( list.size() );
        for ( Mes mes : list ) {
            list1.add( mesMapper.toDTO( mes ) );
        }

        return list1;
    }

    protected List<Mes> mesDTOListToMesList(List<MesDTO> list) {
        if ( list == null ) {
            return null;
        }

        List<Mes> list1 = new ArrayList<Mes>( list.size() );
        for ( MesDTO mesDTO : list ) {
            list1.add( mesMapper.toEntity( mesDTO ) );
        }

        return list1;
    }
}
