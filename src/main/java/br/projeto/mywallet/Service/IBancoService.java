package br.projeto.mywallet.Service;

import br.projeto.mywallet.DTO.BancoDTO;
import java.util.List;

public interface IBancoService {
    BancoDTO criarBanco(BancoDTO banco); // Criar
    
    BancoDTO buscarBancoPorId(Long id); // Ler por ID
    
    List<BancoDTO> listarTodosBancos(); // Ler todos

    void deletarBanco(Long id); // Deletar
}
