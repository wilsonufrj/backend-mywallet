package br.projeto.mywallet.ServiceImpl;

import br.projeto.mywallet.DTO.BalancoDTO;
import br.projeto.mywallet.DTO.MesDTO;
import br.projeto.mywallet.Mappers.MesMapper;
import br.projeto.mywallet.Model.Carteira;
import br.projeto.mywallet.Model.Mes;
import br.projeto.mywallet.Model.Transacao;
import br.projeto.mywallet.Service.IMesService;
import br.projeto.mywallet.enums.TipoStatus;
import br.projeto.mywallet.repository.ICarteiraRepository;
import br.projeto.mywallet.repository.IMesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MesService implements IMesService {

    @Autowired
    private IMesRepository mesRepository;

    @Autowired
    private ICarteiraRepository carteiraRepository;

    @Autowired
    private final MesMapper mesMapper = MesMapper.INSTANCE;

    @Override
    public MesDTO criarMes(MesDTO mesDTO) throws Exception {

        Carteira carteira = carteiraRepository.findById(mesDTO.getCarteira().getId())
                .orElseThrow(() -> new Exception("Carteira não encontrada"));

        Mes mes = mesMapper.toEntity(mesDTO);
        mes.setCarteira(carteira);

        return mesMapper.toDTO(mesRepository.save(mes));
    }

    @Override
    public MesDTO atualizarMes(Long id, MesDTO mesAtualizado) {
        MesDTO mesDTO = buscarPorId(id);

        mesDTO.setNome(mesAtualizado.getNome());
        mesDTO.setAno(mesAtualizado.getAno());
        mesDTO.setTransacoes(mesAtualizado.getTransacoes());

        return mesMapper.toDTO(mesRepository.save(mesMapper.toEntity(mesDTO)));
    }

    @Override
    public void deletarMes(Long id) {
        mesRepository.deleteById(id);
    }

    @Override
    public MesDTO buscarPorId(Long id) {
        Optional<Mes> mes = mesRepository.findById(id);
        return mes.map(mesMapper::toDTO)
                .orElseThrow(() -> new RuntimeException("Mês com o ID " + id + " não encontrado."));
    }

    @Override
    public List<MesDTO> listarTodos() {
        return mesRepository.findAll().stream()
                .map(mesMapper::toDTO)
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
        //O saldo atual eh a soma dos ganhos do usuario logado menos as transacoes pagas por ele no débito, gastos no credito pago
        balanco.setSaldoAtual(getSaldoAtual(balanco,transacoes));
        balanco.setInvestimentoMes(getInvestimentos(transacoes));
        balanco.setGastosNaoPagosCredito(getGastosNaoPagosCredito(transacoes));
        balanco.setSaldoMesSeguinte(balanco.getTotalGanhoMes() - balanco.getGastosNaoPagosCredito());

        return balanco;
    }

    private Double getSaldoAtual(BalancoDTO balancoDTO,List<Transacao> transacoes){
        return balancoDTO.getTotalGanhoMes() -getGastosPagos(transacoes);
    }

    private Double getGastosPagos(List<Transacao> transacoes){
        return transacoes.stream()
                .filter(transacao -> transacao.getTipoTransacao().getNome().equals("Crédito")
                        && transacao.getStatus().equals(TipoStatus.PAGO))
                .mapToDouble(Transacao::getValor)
                .sum();
    }

    private Double getGastosNaoPagosCredito(List<Transacao> transacoes) {
        return transacoes.stream()
                .filter(transacao -> transacao.getTipoTransacao().getNome().equals("Crédito")
                        && transacao.getStatus().equals(TipoStatus.NAO_PAGO))
                .mapToDouble(Transacao::getValor)
                .sum();
    }

    private Double getInvestimentos(List<Transacao> transacoes) {
        return transacoes.stream()
                .filter(transacao -> transacao.getTipoTransacao().getNome().equals("Investimento"))
                .mapToDouble(Transacao::getValor)
                .sum();
    }

    private Double getGastosMensais(List<Transacao> transacoes) {
        return transacoes.stream()
                .filter(transacao -> !transacao.getReceita())
                .mapToDouble(Transacao::getValor)
                .sum();
    }

    private Double getGanhosMensais(List<Transacao> transacoes) {
        return transacoes.stream()
                .filter(Transacao::getReceita)
                .mapToDouble(Transacao::getValor)
                .sum();
    }

}
