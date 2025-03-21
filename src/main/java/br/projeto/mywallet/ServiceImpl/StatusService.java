package br.projeto.mywallet.ServiceImpl;

import br.projeto.mywallet.DTO.StatusDTO;
import br.projeto.mywallet.Mappers.StatusMapper;
import br.projeto.mywallet.Model.Status;
import br.projeto.mywallet.Service.IStatusService;
import br.projeto.mywallet.repository.IStatusRepository;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StatusService implements IStatusService {

    @Autowired
    private IStatusRepository statusRepository;
    
    @Autowired
    private StatusMapper statusMapper;
    
    @Override
    public StatusDTO criarStatus(StatusDTO statusDTO) {
        Status status = statusMapper.toEntity(statusDTO);
        return statusMapper.toDTO(statusRepository.save(status));
    }

    @Override
    public StatusDTO atualizarStatus(Long id, StatusDTO statusAtualizado) {
        StatusDTO statusDTO = buscarPorId(id);

        statusDTO.setNome(statusAtualizado.getNome());
        statusDTO.setTransacoes(statusAtualizado.getTransacoes());

        return statusMapper.toDTO(statusRepository.save(statusMapper.toEntity(statusDTO)));
    }

    @Override
    public void deletarStatus(Long id) {
        StatusDTO statusDTO = buscarPorId(id);
        statusRepository.delete(statusMapper.toEntity(statusDTO));
    }

    @Override
    public StatusDTO buscarPorId(Long id) {
        Optional<Status> status = statusRepository.findById(id);
        return status.map(statusMapper::toDTO)
                .orElseThrow(() -> new RuntimeException("Status com o ID " + id + " não encontrado."));
    }

    @Override
    public List<StatusDTO> listarTodos() {
        return statusRepository.findAll().stream()
                .map(statusMapper::toDTO)
                .toList();
    }
}
