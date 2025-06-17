package br.projeto.mywallet.Service.impl;

import br.projeto.mywallet.DTO.ResponsavelDTO;
import br.projeto.mywallet.DTO.TransacaoDTO;
import br.projeto.mywallet.DTO.UsuarioInfo;
import br.projeto.mywallet.Model.Responsavel;
import br.projeto.mywallet.Model.Transacao;
import br.projeto.mywallet.Model.Usuario;
import br.projeto.mywallet.Service.IResponsavelService;
import br.projeto.mywallet.repository.IResponsavelRepository;
import br.projeto.mywallet.repository.IUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ResponsavelService implements IResponsavelService {

    @Autowired
    private IResponsavelRepository responsavelRepository;

    @Autowired
    private IUsuarioRepository usuarioRepository;

    @Override
    public ResponsavelDTO criarResponsavel(ResponsavelDTO responsavelDTO) {
        Responsavel responsavel = new Responsavel();
        try {

            Usuario usuario = usuarioRepository.findById(responsavelDTO.getUsuarioInfo().getId())
                    .orElseThrow(() -> new Exception("Usuário não encontrado"));


            responsavel.setNome(responsavelDTO.getNome());
            responsavel.setTransacoes(new ArrayList<>());
            responsavel.setUsuario(usuario);
            return toDto(responsavelRepository.save(responsavel));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void deletarResponsavel(Long id) {
        responsavelRepository.deleteById(id);
    }

    @Override
    public ResponsavelDTO buscarPorId(Long id) {
        return responsavelRepository.findById(id)
                .map(ResponsavelService::toDto)
                .orElseThrow(() -> new RuntimeException("Responsável com o ID " + id + " não encontrado."));
    }

    @Override
    public List<ResponsavelDTO> listarTodos(Long id) {

        try{
            Usuario usuario = usuarioRepository.findById(id)
                    .orElseThrow(() -> new Exception("Usuario nao encontrado"));

            return responsavelRepository.findAll().stream()
                    .filter(responsavel -> responsavel.getUsuario().getId().equals(id))
                    .map(ResponsavelService::toDto)
                    .toList();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<ResponsavelDTO> listarTodos() {
        return responsavelRepository.findAll()
                .stream()
                .map(ResponsavelService::toDto)
                .toList();
    }

    public static ResponsavelDTO toDto(Responsavel responsavel) {

        UsuarioInfo usuarioInfo = UsuarioService.toInfo(responsavel.getUsuario());

        return new ResponsavelDTO(
                responsavel.getId(),
                responsavel.getNome(),
                usuarioInfo
        );
    }
}
