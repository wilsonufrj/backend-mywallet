package br.projeto.mywallet.Mappers;

import br.projeto.mywallet.DTO.StatusDTO;
import br.projeto.mywallet.Model.Status;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-03-21T18:26:18-0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 19.0.2 (Private Build)"
)
@Component
public class StatusMapperImpl implements StatusMapper {

    @Override
    public StatusDTO toDTO(Status status) {
        if ( status == null ) {
            return null;
        }

        StatusDTO statusDTO = new StatusDTO();

        statusDTO.setId( status.getId() );
        statusDTO.setNome( status.getNome() );

        return statusDTO;
    }

    @Override
    public Status toEntity(StatusDTO statusDTO) {
        if ( statusDTO == null ) {
            return null;
        }

        Status status = new Status();

        status.setId( statusDTO.getId() );
        status.setNome( statusDTO.getNome() );

        return status;
    }
}
