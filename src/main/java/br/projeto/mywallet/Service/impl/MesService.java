package br.projeto.mywallet.Service.impl;

import br.projeto.mywallet.DTO.BalancoConjuntoDTO;
import br.projeto.mywallet.DTO.BalancoDTO;
import br.projeto.mywallet.DTO.MesDTO;
import br.projeto.mywallet.DTO.TransacaoDTO;
import br.projeto.mywallet.Model.Carteira;
import br.projeto.mywallet.Model.Mes;
import br.projeto.mywallet.Model.Responsavel;
import br.projeto.mywallet.Model.Transacao;
import br.projeto.mywallet.Service.IMesService;
import br.projeto.mywallet.enums.TipoStatus;
import br.projeto.mywallet.enums.TipoTransacao;
import br.projeto.mywallet.repository.ICarteiraRepository;
import br.projeto.mywallet.repository.IMesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Stream;

@Service
public class MesService implements IMesService {

    @Autowired
    private IMesRepository mesRepository;

    @Autowired
    private ICarteiraRepository carteiraRepository;

    @Override
    public MesDTO criarMes(MesDTO mesDTO) throws Exception {

        Mes mes = new Mes();

        Carteira carteira = carteiraRepository.findById(mesDTO.getCarteira().getId())
                .orElseThrow(() -> new Exception("Carteira não encontrada"));

        mes.setNome(mesDTO.getNome());
        mes.setAno(mesDTO.getAno());
        mes.setCarteira(carteira);
        mes.setPorcentagemInvestimento(0);
        mes.setTransacoes(new ArrayList<>());

        return toDto(mesRepository.save(mes));
    }

    @Override
    public void deletarMes(Long id) {
        mesRepository.deleteById(id);
    }

    @Override
    public MesDTO buscarPorId(Long id) {
        Optional<Mes> mes = mesRepository.findById(id);
        return mes.map(MesService::toDto)
                .orElseThrow(() -> new RuntimeException("Mês com o ID " + id + " não encontrado."));
    }

    @Override
    public List<MesDTO> listarTodos() {
        return mesRepository.findAll().stream()
                .map(MesService::toDto)
                .toList();
    }

    @Override
    public BalancoDTO balancoMes(Long id) throws Exception {
        Mes mes = mesRepository.findById(id)
                .orElseThrow(() -> new Exception("Erro ao encontrar o id"));

        List<Transacao> transacoes = mes.getTransacoes();
        BalancoDTO balanco = new BalancoDTO();

        balanco.setTotalGanhoMes(getGanhosMensais(transacoes));
        balanco.setTotalGastosMes(getGastosMensais(transacoes));
        balanco.setInvestimentoMes(getInvestimentos(transacoes));
        balanco.setSaldoAtual(getSaldoAtual(transacoes));
        balanco.setSaldoMesSeguinte(getGanhosMensais(transacoes) - getGastosCredito(transacoes));

        return balanco;
    }

    @Override
    public List<BalancoConjuntoDTO> balancoConjunto(Long idMes) {
        try {
            Mes mes = this.mesRepository.findById(idMes)
                    .orElseThrow(() -> new Exception("Ero ao encontrar o mês"));

            List<Responsavel> responsaveis = mes.getCarteira().getUsuarios()
                    .stream()
                    .map(usuario ->
                            usuario.getResponsaveis()
                                    .stream()
                                    .filter(auxUsuario -> auxUsuario.getNome().equals(usuario.getNome()))
                                    .findFirst()
                                    .orElse(null)

                    )
                    .filter(Objects::nonNull)
                    .toList();

            Map<Responsavel, List<Transacao>> balancoResponsavel = new HashMap<>();

            responsaveis
                    .forEach(responsavel -> {
                        List<Transacao> transacoes = mes.getTransacoes().stream()
                                .filter(transacao -> transacao.getResponsavel().getId().equals(responsavel.getId()))
                                .toList();
                        balancoResponsavel.put(responsavel, transacoes);
                    });

            Double ganhoTotalMensal = getGanhosMensais(mes.getTransacoes());
            Double gastosTotalMensal = getGastosMensais(mes.getTransacoes());

            return balancoResponsavel.entrySet()
                    .stream()
                    .map(entry -> {
                        Double somaGanho = getGanhosMensais(entry.getValue());

                        Double porcentagemDosCustos = ganhoTotalMensal.equals(0.00)
                        ?0
                        :somaGanho / ganhoTotalMensal;
                        Double gastoConjunto = gastosTotalMensal * porcentagemDosCustos;
                        Double investimentoConjunto = somaGanho * ((double) mes.getPorcentagemInvestimento() /100);
                        Double totalGasto = gastoConjunto + investimentoConjunto;
                        Double saldoFinal = somaGanho - totalGasto;

                        return new BalancoConjuntoDTO(
                                ResponsavelService.toDto(entry.getKey()),
                                porcentagemDosCustos,
                                gastoConjunto,
                                investimentoConjunto,
                                totalGasto,
                                saldoFinal);
                    })
                    .toList();


        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public MesDTO atualizaPorcentagemMes(Integer porcentagemMes, Long idMes) {
        try {
            Mes mes = this.mesRepository.findById(idMes)
                    .orElseThrow(() -> new Exception("Ero ao encontrar o mês"));

            mes.setPorcentagemInvestimento(porcentagemMes);

            return toDto(mesRepository.save(mes));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private Double getSaldoAtual(List<Transacao> transacoes) {

        return getGanhosMensais(transacoes)
                - getInvestimentos(transacoes)
                - getGastosPagosDebito(transacoes);
    }

    private Double getGastosPagosDebito(List<Transacao> transacoes) {
        return getGastos(transacoes)
                .filter(transacao -> transacao.getTipoTransacao().equals(TipoTransacao.DEBITO))
                .mapToDouble(Transacao::getValor)
                .sum();
    }

    private Double getGastosCredito(List<Transacao> transacoes) {
        return transacoes.stream()
                .filter(transacao -> transacao.getTipoTransacao().equals(TipoTransacao.CREDITO)
                        && transacao.getStatus().equals(TipoStatus.NAO_PAGO))
                .mapToDouble(Transacao::getValor)
                .sum();
    }

    private Double getInvestimentos(List<Transacao> transacoes) {
        return getGastos(transacoes)
                .filter(transacao -> transacao.getTipoTransacao().equals(TipoTransacao.INVESTIMENTO))
                .mapToDouble(Transacao::getValor)
                .sum();
    }

    private Double getGastosMensais(List<Transacao> transacoes) {
        return getGastos(transacoes)
                .mapToDouble(Transacao::getValor)
                .sum();
    }

    private Double getGanhosMensais(List<Transacao> transacoes) {
        return getGanhos(transacoes)
                .mapToDouble(Transacao::getValor)
                .sum();
    }

    private Stream<Transacao> getGastos(List<Transacao> transacoes) {
        return transacoes.stream()
                .filter(transacao -> !transacao.getReceita());
    }

    private Stream<Transacao> getGanhos(List<Transacao> transacoes) {
        return transacoes.stream()
                .filter(Transacao::getReceita);
    }

    public static MesDTO toDto(Mes mes) {

        List<TransacaoDTO> transacoes = mes.getTransacoes()
                .stream()
                .map(TransacaoService::toDto)
                .toList();

        return new MesDTO(
                mes.getId(),
                mes.getNome(),
                mes.getAno(),
                mes.getPorcentagemInvestimento(),
                transacoes
        );
    }
}
