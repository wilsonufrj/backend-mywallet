package br.projeto.mywallet.Service;

import br.projeto.mywallet.DTO.CarteiraDTO;
import br.projeto.mywallet.Model.Carteira;

import java.util.List;

public interface ICarteiraService {

    CarteiraDTO criarCarteira(Long usuarioId, CarteiraDTO carteira) throws Exception;

    List<CarteiraDTO> buscaCarteiraPorIDUsuario(Long idUsuario);

    CarteiraDTO buscaCarteira(Long id) throws Exception;

    void deletarCarteira(Long id) throws Exception;
}
