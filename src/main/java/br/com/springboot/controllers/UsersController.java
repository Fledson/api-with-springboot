package br.com.springboot.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.springboot.model.User;
import br.com.springboot.repository.UserRepository;
import br.com.springboot.services.exceptions.ObjectNotFoundExeption;

/**
 * Classe controladora
 * A anotação @RestController é para construções de API diferente da @Controller
 * que retorna uma visualização geral (em HTML + CSS + JavaScript)
 * a @RestController retorna apneas dados em JSON ou XML
 */
@RestController
@RequestMapping("/user")
public class UsersController {

  /**
   * Criando uma lista de usuarios para armazenar temporariamente minhas
   * informações
   */
  // private List<User> users = new ArrayList<>();

  @Autowired
  private UserRepository userRepository;

  /**
   * Metodo que busca um usuario por ID
   * 
   * @param id Recebe o id do usuario
   * @return Retorna o usuario se encontrar e null se não encontrar nada
   */
  @GetMapping("{id}")
  public User helloWord(@PathVariable("id") Long id) {
    /**
     * Função nova(Optional e Stream - Pesquisar mais sobre)
     * Aqui busco meu usuario usando um metodo do Stream, o filter que busca se
     * dentro da minha lista algum usuario tem o id que estou buscando
     * Se existir um usuario ele retorna o objeto e armazena no Optional findUser
     */
    // Optional<User> findUser = users.stream().filter(user -> user.getId() ==
    // id).findFirst();

    /**
     * Diferente da função anterior, essa procura no banco de dados e tras o retorno
     * para a variavel Optional findUser
     */
    Optional<User> findUser = this.userRepository.findById(id);

    /**
     * Usando aqui uma função do Optional para verifica se dentro da variavel existe
     * um usuario
     */
    if (findUser.isPresent()) {
      /**
       * Se existir, uso um get() para retornar meu objeto
       */
      return findUser.get();
    }

    /**
     * Se não existir, retorna nulo
     */

    return findUser.orElseThrow(() -> new ObjectNotFoundExeption("Nenhum usuario encotrado com o id: " + id));
  }

  /**
   * Metodo que cadastra um novo usuario
   * 
   * @param user recebe um usuario passado pelo body da api
   * @return retorna o usuario confirmando a operação
   */
  @PostMapping
  public User addUser(@RequestBody User user) {
    /**
     * Retornando o usuario e cadastrando no banco de dados
     */
    return this.userRepository.save(user);
  }

  /**
   * Metodo que retorna todos os usuario cadastrados
   * 
   * @return Retorna um ArrayList de usuarios
   */
  @GetMapping
  public List<User> allUsers() {
    return this.userRepository.findAll();
  }

  @GetMapping("/normal")
  public List<User> allNormalUsers() {
    return this.userRepository.findByUsernameNotContaining("#");
  }

  @GetMapping("/superuser")
  public List<User> allSuperUsers() {
    return this.userRepository.findByUsernameStartingWith("#");
  }

  @GetMapping("/findByName/{name}")
  public List<User> findbyname(@PathVariable("name") String name) {
    return this.userRepository.findByNameIgnoreCaseContaining(name);
  }
}