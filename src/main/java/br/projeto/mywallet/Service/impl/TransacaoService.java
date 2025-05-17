package br.projeto.mywallet.Service.impl;

import br.projeto.mywallet.DTO.BancoDTO;
import br.projeto.mywallet.DTO.ResponsavelDTO;
import br.projeto.mywallet.DTO.TransacaoDTO;
import br.projeto.mywallet.Model.*;
import br.projeto.mywallet.Service.ITransacaoService;
import br.projeto.mywallet.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransacaoService implements ITransacaoService {

    @Autowired
    private ITransacaoRepository transacaoRepository;

    @Autowired
    private IBancoRepository bancoRepository;


    @Autowired
    private IResponsavelRepository responsavelRepository;

    @Autowired
    private IMesRepository mesRepository;


    @Override
    public TransacaoDTO criarTransacao(TransacaoDTO transacaoDTO, Long idMes) throws Exception {

        Transacao transacao = new Transacao();

        Banco banco = bancoRepository.findById(transacao.getBanco().getId())
                .orElseThrow(() -> new Exception("Banco nao encontrado"));

        Responsavel responsavel = responsavelRepository.findById(transacao.getResponsavel().getId())
                .orElseThrow(() -> new Exception("Responsavel nao encontrado"));

        Mes mes = mesRepository.findById(idMes)
                .orElseThrow(() -> new Exception("Mes nao encontrado"));

        transacao.setData(transacaoDTO.getData());
        transacao.setDescricao(transacaoDTO.getDescricao());
        transacao.setValor(transacaoDTO.getValor());
        transacao.setQuantasVezes(transacaoDTO.getQuantasVezes());
        transacao.setReceita(transacaoDTO.getReceita());
        transacao.setBanco(banco);
        transacao.setFormaPagamento(transacaoDTO.getFormaPagamento());
        transacao.setStatus(transacaoDTO.getStatus());
        transacao.setResponsavel(responsavel);
        transacao.setMes(mes);
        transacao.setTipoTransacao(transacaoDTO.getTipoTransacao());

        return toDto(transacaoRepository.save(transacao));
    }

    @Override
    public TransacaoDTO atualizarTransacao(Long id, TransacaoDTO transacaoAtualizada) {

        try {
            Transacao transacao = transacaoRepository.findById(transacaoAtualizada.getId())
                    .orElseThrow(() -> new Exception("Transacao não encontrada"));

            Banco banco = bancoRepository.findById(transacaoAtualizada.getBanco().getId())
                    .orElseThrow(() -> new Exception("Banco nao encontrado"));

            Responsavel responsavel = responsavelRepository.findById(transacaoAtualizada.getResponsavel().getId())
                    .orElseThrow(() -> new Exception("Responsavel nao encontrado"));

            transacao.setData(transacaoAtualizada.getData());
            transacao.setDescricao(transacaoAtualizada.getDescricao());
            transacao.setValor(transacaoAtualizada.getValor());
            transacao.setQuantasVezes(transacaoAtualizada.getQuantasVezes());
            transacao.setStatus(transacaoAtualizada.getStatus());
            transacao.setBanco(banco);
            transacao.setFormaPagamento(transacaoAtualizada.getFormaPagamento());
            transacao.setResponsavel(responsavel);
            transacao.setTipoTransacao(transacaoAtualizada.getTipoTransacao());

            return toDto(transacaoRepository.save(transacao));

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
                .map(TransacaoService::toDto)
                .orElseThrow(() -> new RuntimeException("Transação com ID " + id + " não encontrada."));
    }

    @Override
    public List<TransacaoDTO> listarTodas() {
        return transacaoRepository.findAll().stream()
                .map(TransacaoService::toDto)
                .toList();
    }

    public static TransacaoDTO toDto(Transacao transacao){
        BancoDTO bancoDTO = BancoService.toDto(transacao.getBanco());
        ResponsavelDTO responsavelDTO = ResponsavelService.toDto(transacao.getResponsavel());
        return new TransacaoDTO(
                transacao.getId(),
                transacao.getData(),
                transacao.getDescricao(),
                transacao.getValor(),
                transacao.getQuantasVezes(),
                bancoDTO,
                transacao.getFormaPagamento(),
                transacao.getStatus(),
                responsavelDTO,
                transacao.getTipoTransacao(),
                transacao.getReceita()
        );
    }
}
