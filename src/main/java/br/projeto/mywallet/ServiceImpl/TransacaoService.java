package br.projeto.mywallet.ServiceImpl;

import br.projeto.mywallet.DTO.TransacaoDTO;
import br.projeto.mywallet.Mappers.TransacaoMapper;
import br.projeto.mywallet.Model.*;
import br.projeto.mywallet.Service.ITransacaoService;
import br.projeto.mywallet.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TransacaoService implements ITransacaoService {

    @Autowired
    private ITransacaoRepository transacaoRepository;

    @Autowired
    private IBancoRepository bancoRepository;

    @Autowired
    private IFormaPagamentoRepository formaPagamentoRepository;

    @Autowired
    private IResponsavelRepository responsavelRepository;

    @Autowired
    private IMesRepository mesRepository;

    @Autowired
    private ITipoTransacaoRepository tipoTransacaoRepository;

    @Autowired
    private TransacaoMapper transacaoMapper = TransacaoMapper.INSTANCE;

    @Override
    public TransacaoDTO criarTransacao(TransacaoDTO transacaoDTO, Long idMes) throws Exception {

        Transacao transacao = transacaoMapper.toEntity(transacaoDTO);

        Banco banco = bancoRepository.findById(transacao.getBanco().getId())
                .orElseThrow(() -> new Exception("Banco nao encontrado"));

        FormaPagamento formaPagamento = formaPagamentoRepository.findById(transacao.getFormaPagamento().getId())
                .orElseThrow(() -> new Exception("Forma de pagamento nao encontrada"));

        Responsavel responsavel = responsavelRepository.findById(transacao.getResponsavel().getId())
                .orElseThrow(() -> new Exception("Responsavel nao encontrado"));

        Mes mes = mesRepository.findById(idMes)
                .orElseThrow(() -> new Exception("Mes nao encontrado"));

        TipoTransacao tipoTransacao = tipoTransacaoRepository.findById(transacao.getTipoTransacao().getId())
                .orElseThrow(() -> new Exception("Tipo Transacao não encontrado"));

        transacao.setBanco(banco);
        transacao.setFormaPagamento(formaPagamento);
        transacao.setResponsavel(responsavel);
        transacao.setMes(mes);
        transacao.setTipoTransacao(tipoTransacao);
        transacao.setReceita(transacaoDTO.getReceita());

        return transacaoMapper
                .toDTO(transacaoRepository.save(transacao));
    }

    @Override
    public TransacaoDTO atualizarTransacao(Long id, TransacaoDTO transacaoAtualizada) {

        try {
            Transacao transacao = transacaoRepository.findById(transacaoAtualizada.getId())
                    .orElseThrow(() -> new Exception("Transacao não encontrada"));

            Banco banco = bancoRepository.findById(transacaoAtualizada.getBanco().getId())
                    .orElseThrow(() -> new Exception("Banco nao encontrado"));

            FormaPagamento formaPagamento = formaPagamentoRepository.findById(transacaoAtualizada.getFormaPagamento().getId())
                    .orElseThrow(() -> new Exception("Forma de pagamento nao encontrada"));

            Responsavel responsavel = responsavelRepository.findById(transacaoAtualizada.getResponsavel().getId())
                    .orElseThrow(() -> new Exception("Responsavel nao encontrado"));

            TipoTransacao tipoTransacao = tipoTransacaoRepository.findById(transacaoAtualizada.getTipoTransacao().getId())
                            .orElseThrow(()-> new Exception("Tipo de transacao nao encontrado"));

            transacao.setData(transacaoAtualizada.getData());
            transacao.setDescricao(transacaoAtualizada.getDescricao());
            transacao.setValor(transacaoAtualizada.getValor());
            transacao.setQuantasVezes(transacaoAtualizada.getQuantasVezes());
            transacao.setStatus(transacaoAtualizada.getStatus());
            transacao.setBanco(banco);
            transacao.setFormaPagamento(formaPagamento);
            transacao.setResponsavel(responsavel);
            transacao.setTipoTransacao(tipoTransacao);

            return transacaoMapper
                    .toDTO(transacaoRepository.save(transacao));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }

    @Override
    public void deletarTransacao(Long id) {
        transacaoRepository.deleteById(id);
    }

    @Override
    public TransacaoDTO buscarPorId(Long id) {
        return transacaoRepository.findById(id)
                .map(transacaoMapper::toDTO)
                .orElseThrow(() -> new RuntimeException("Transação com ID " + id + " não encontrada."));
    }

    @Override
    public List<TransacaoDTO> listarTodas() {
        return transacaoRepository.findAll().stream()
                .map(transacaoMapper::toDTO)
                .toList();
    }
}
