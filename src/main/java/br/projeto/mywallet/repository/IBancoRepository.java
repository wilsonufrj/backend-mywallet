/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package br.projeto.mywallet.repository;

import br.projeto.mywallet.Model.Banco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author wilsonramos
 */
@Repository
public interface IBancoRepository extends JpaRepository<Banco, Long>{
    
}
