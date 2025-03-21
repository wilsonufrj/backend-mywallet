package br.projeto.mywallet.Mappers;

import br.projeto.mywallet.DTO.ResponsavelDTO;
import br.projeto.mywallet.Model.Responsavel;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-03-21T18:26:17-0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 19.0.2 (Private Build)"
)
@Component
public class ResponsavelMapperImpl implements ResponsavelMapper {

    @Override
    public ResponsavelDTO toDTO(Responsavel responsavel) {
        if ( responsavel == null ) {
            return null;
        }

        ResponsavelDTO responsavelDTO = new ResponsavelDTO();

        responsavelDTO.setId( responsavel.getId() );
        responsavelDTO.setNome( responsavel.getNome() );

        return responsavelDTO;
    }

    @Override
    public Responsavel toEntity(ResponsavelDTO responsavelDTO) {
        if ( responsavelDTO == null ) {
            return null;
        }

        Responsavel responsavel = new Responsavel();

        responsavel.setId( responsavelDTO.getId() );
        responsavel.setNome( responsavelDTO.getNome() );

        return responsavel;
    }
}
