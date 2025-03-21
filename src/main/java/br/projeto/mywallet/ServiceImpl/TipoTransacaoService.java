package br.projeto.mywallet.ServiceImpl;

import br.projeto.mywallet.DTO.TipoTransacaoDTO;
import br.projeto.mywallet.Mappers.TipoTransacaoMapper;
import br.projeto.mywallet.Model.TipoTransacao;
import br.projeto.mywallet.Service.ITipoTransacaoService;
import br.projeto.mywallet.repository.ITipoTransacaoRepository;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TipoTransacaoService implements ITipoTransacaoService {

    @Autowired
    private ITipoTransacaoRepository tipoTransacaoRepository;

    @Autowired
    private TipoTransacaoMapper tipoTransacaoMapper = TipoTransacaoMapper.INSTANCE;

    @Override
    public TipoTransacaoDTO criarTipoTransacao(TipoTransacaoDTO tipoTransacaoDTO) {

        TipoTransacao tipoTransacao = tipoTransacaoMapper.toEntity(tipoTransacaoDTO);

        return tipoTransacaoMapper
                .toDTO(tipoTransacaoRepository.save(tipoTransacao));
    }

    @Override
    public TipoTransacaoDTO atualizarTipoTransacao(Long id, TipoTransacaoDTO tipoTransacaoAtualizado) {
        TipoTransacaoDTO tipoDTO = buscarPorId(id);

        tipoDTO.setNome(tipoTransacaoAtualizado.getNome());
        tipoDTO.setTransacoes(tipoTransacaoAtualizado.getTransacoes());

        return tipoTransacaoMapper
                .toDTO(tipoTransacaoRepository.save(tipoTransacaoMapper.toEntity(tipoDTO)));
    }

    @Override
    public void deletarTipoTransacao(Long id) {

        TipoTransacaoDTO tipoDTO = buscarPorId(id);
        tipoTransacaoRepository.delete(tipoTransacaoMapper.toEntity(tipoDTO));
    }

    @Override
    public TipoTransacaoDTO buscarPorId(Long id) {

        Optional<TipoTransacao> tipoTransacao = tipoTransacaoRepository.findById(id);

        return tipoTransacao.map(tipoTransacaoMapper::toDTO)
                .orElseThrow(() -> new RuntimeException("Tipo de Transação com o ID " + id + " não encontrado."));
    }

    @Override
    public List<TipoTransacaoDTO> listarTodos() {

        return tipoTransacaoRepository.findAll().stream()
                .map(tipoTransacaoMapper::toDTO)
                .toList();
    }
}
