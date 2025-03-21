package br.projeto.mywallet.Mappers;

import br.projeto.mywallet.DTO.BancoDTO;
import br.projeto.mywallet.Model.Banco;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-03-21T18:26:18-0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 19.0.2 (Private Build)"
)
@Component
public class BancoMapperImpl implements BancoMapper {

    @Override
    public BancoDTO toDTO(Banco banco) {
        if ( banco == null ) {
            return null;
        }

        BancoDTO bancoDTO = new BancoDTO();

        bancoDTO.setId( banco.getId() );
        bancoDTO.setNome( banco.getNome() );

        return bancoDTO;
    }

    @Override
    public Banco toEntity(BancoDTO banco) {
        if ( banco == null ) {
            return null;
        }

        Banco banco1 = new Banco();

        banco1.setId( banco.getId() );
        banco1.setNome( banco.getNome() );

        return banco1;
    }
}
