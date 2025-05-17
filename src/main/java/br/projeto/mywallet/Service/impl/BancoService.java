package br.projeto.mywallet.Service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.projeto.mywallet.DTO.BancoDTO;
import br.projeto.mywallet.Model.Banco;
import br.projeto.mywallet.Service.IBancoService;
import br.projeto.mywallet.repository.IBancoRepository;

import java.util.ArrayList;
import java.util.List;



/**
 * @author wilsonramos
 */
@Service
public class BancoService implements IBancoService {

    @Autowired
    private IBancoRepository bancoRepository;


    @Override
    public BancoDTO criarBanco(BancoDTO bancoDto) {

            Banco banco = new Banco();
            banco.setNome(bancoDto.getNome());
            banco.setTransacoes(new ArrayList<>());

           return toDto(bancoRepository.save(banco));
    }

    @Override
    public BancoDTO buscarBancoPorId(Long id) {
        return bancoRepository.findById(id)
                .map(BancoService::toDto)
                .orElseThrow(() -> new RuntimeException("Banco não encontrado com ID: " + id));
    }

    @Override
    public List<BancoDTO> listarTodosBancos() {
        return bancoRepository.findAll().stream()
                .map(BancoService::toDto)
                .toList();
    }

    @Override
    public void deletarBanco(Long id) {
        bancoRepository.deleteById(id);
    }

    public static BancoDTO toDto(Banco banco) {
        return new BancoDTO(
                banco.getId(),
                banco.getNome());
    }
}
