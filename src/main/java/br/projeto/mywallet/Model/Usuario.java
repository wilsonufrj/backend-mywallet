    package br.projeto.mywallet.Model;

    import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
    import jakarta.persistence.*;

    import java.time.LocalDate;
    import java.util.List;
    import java.util.Set;

    /**
     *
     * @author wilsonramos
     */
    @Entity
    @Table(name = "usuario")
    public class Usuario {

        @Id
        @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "usuario_id_seq_generator")
        @SequenceGenerator(name = "usuario_id_seq_generator", sequenceName = "usuario_id_seq", allocationSize = 1)
        private Long id;

        @Column(name = "nome",
                nullable = false)
        private String nome;

        @Column(name = "data_nascimento")
        private LocalDate dataNascimento;

        @Column(name = "email",
                nullable = false)
        private String email;

        @Column(name = "senha",
                nullable = false)
        private String senha;

        @ManyToMany(mappedBy = "usuarios")
        @JsonIgnoreProperties("usuarios")
        private Set<Carteira> carteiras;

        @OneToMany(mappedBy = "usuario",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
        private List<Responsavel> responsaveis;

        public Usuario() {
        }

        public Usuario(
                Long id,
                String nome,
                LocalDate dataNascimento,
                String email,
                String senha,
                Set<Carteira> carteiras) {

            this.id = id;
            this.nome = nome;
            this.dataNascimento = dataNascimento;
            this.email = email;
            this.senha = senha;
            this.carteiras = carteiras;
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getNome() {
            return nome;
        }

        public void setNome(String nome) {
            this.nome = nome;
        }

        public String getSenha() {
            return senha;
        }

        public void setSenha(String senha) {
            this.senha = senha;
        }

        public LocalDate getDataNascimento() {
            return dataNascimento;
        }

        public void setDataNascimento(LocalDate dataNascimento) {
            this.dataNascimento = dataNascimento;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public Set<Carteira> getCarteiras() {
            return carteiras;
        }

        public void setCarteiras(Set<Carteira> carteiras) {
            this.carteiras = carteiras;
        }

        public List<Responsavel> getResponsaveis() {
            return responsaveis;
        }

        public void setResponsaveis(List<Responsavel> responsaveis) {
            this.responsaveis = responsaveis;
        }

        public void adicionarCarteira(Carteira carteira) {
            this.carteiras.add(carteira);
            carteira.getUsuarios().add(this);
        }

         public void removerCarteira(Carteira carteira) {
            this.carteiras.remove(carteira);
            carteira.getUsuarios().remove(this);
        }
    }
