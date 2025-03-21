package br.projeto.mywallet.Service;

import br.projeto.mywallet.DTO.CarteiraDTO;

import java.util.List;

public interface ICarteiraService {

    CarteiraDTO criarCarteira(Long usuarioId, CarteiraDTO carteira) throws Exception;

    List<CarteiraDTO> buscaCarteiraPorIDUsuario(Long idUsuario);

    void deletarCarteira(Long id) throws Exception;
}
