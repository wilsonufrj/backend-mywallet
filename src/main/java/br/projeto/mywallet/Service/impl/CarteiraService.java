package br.projeto.mywallet.Service.impl;

import br.projeto.mywallet.DTO.CarteiraDTO;
import br.projeto.mywallet.DTO.MesDTO;
import br.projeto.mywallet.DTO.UsuarioInfo;
import br.projeto.mywallet.Model.Carteira;
import br.projeto.mywallet.Model.Mes;
import br.projeto.mywallet.Model.Usuario;
import br.projeto.mywallet.Service.ICarteiraService;
import br.projeto.mywallet.repository.ICarteiraRepository;
import br.projeto.mywallet.repository.IUsuarioRepository;

import java.time.LocalDate;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service
public class CarteiraService implements ICarteiraService {

    @Autowired
    private ICarteiraRepository carteiraRepository;

    @Autowired
    private IUsuarioRepository usuarioRepository;

    @Override
    public CarteiraDTO criarCarteira(Long usuarioId, CarteiraDTO carteiraDTO) throws Exception {

        Usuario usuarioLogged = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new Exception("Usuario não encontrado"));

        Carteira carteira = new Carteira();

        Set<Usuario> usuarios = new HashSet<>();
        usuarios.add(usuarioLogged);

        carteiraDTO.getUsuarios()
                .stream()
                .forEach(usuario -> {
                    Usuario auxUsuario;
                    try {
                        auxUsuario = usuarioRepository.findById(usuario.getId())
                                .orElseThrow(() -> new Exception("Usuario não encontrado"));

                        usuarios.add(auxUsuario);

                    } catch (Exception ex) {
                        Logger.getLogger(CarteiraService.class.getName()).log(Level.SEVERE, null, ex.getMessage());
                    }
                });

        carteira.setUsuarios(usuarios);

        LocalDate dataAtual = LocalDate.now();
        carteira.setMeses(List.of(new Mes(dataAtual.getMonth().name(), dataAtual.getYear(), 0,carteira, new ArrayList<>())));
        carteira.setNome(carteiraDTO.getNome());

        return toDto(carteiraRepository.save(carteira));
    }

    @Override
    public void deletarCarteira(Long id) throws Exception {
        Carteira carteira = carteiraRepository.findById(id)
                .orElseThrow(() -> new Exception("Usuario não encontrado"));

        Set<Usuario> usuarios = new HashSet<>(carteira.getUsuarios());

        usuarios.forEach(usuario -> usuario.removerCarteira(carteira));

        usuarioRepository.saveAll(usuarios);

        carteiraRepository.deleteById(id);
    }

    @Override
    public List<CarteiraDTO> buscaCarteiraPorIDUsuario(Long idUsuario) {
        List<Carteira> carteiras = carteiraRepository.findAll();

        return carteiras.stream()
                .filter(carteira -> carteira.getUsuarios().stream().anyMatch((Usuario usuario) -> usuario.getId().equals(idUsuario)))
                .map(CarteiraService::toDto)
                .toList();
    }

    @Override
    public CarteiraDTO buscaCarteira(Long id) throws Exception{
        return carteiraRepository.findById(id)
                .map(CarteiraService::toDto)
                .orElseThrow(()-> new Exception("Carteira não existe"));

    }

    public static CarteiraDTO toDto(Carteira carteira){

        Set<UsuarioInfo> usuariosInfo = carteira.getUsuarios()
                .stream()
                .map(UsuarioService::toInfo)
                .collect(Collectors.toSet());

        List<MesDTO> meses = carteira.getMeses().stream()
                .map(MesService::toDto)
                .toList();

        return new CarteiraDTO(
                carteira.getId(),
                carteira.getNome(),
                usuariosInfo,
                meses
        );
    }
}
