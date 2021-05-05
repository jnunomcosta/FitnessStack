package ft.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ft.backend.beans.gestao_treinadores;
import ft.backend.beans.gestao_utilizadores;
import ft.backend.entities.Treinador;
import ft.backend.entities.Utilizador;
import ft.backend.requests.PedidoRegister;
import ft.backend.responses.RespostaRegister;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/api/register")
public class RegisterController {
    
    @Autowired
    gestao_utilizadores gestao_utilizadores;
    @Autowired
    gestao_treinadores gestao_treinadores;

    @PostMapping(value = "/user")
    public ResponseEntity<RespostaRegister> register_utilizador(@RequestBody PedidoRegister pl){

        Utilizador u = new Utilizador();
        u.setEmail(pl.getEmail());u.setPassword(pl.getPassword());u.setNome(pl.getNome());
        u.setAltura(pl.getAltura());u.setGenero(pl.getGenero());u.setPeso(pl.getPeso());
        u.setIdade(pl.getIdade());
        gestao_utilizadores.registerUser(u);

        RespostaRegister ret = new RespostaRegister();
        ret.setSucess(true);

        return ResponseEntity.ok().body(ret);
    }

    @PostMapping(value = "/treinador")
    public ResponseEntity<RespostaRegister> register_treinador(@RequestBody PedidoRegister pl){

        Treinador u = new Treinador();
        u.setEmail(pl.getEmail());u.setPassword(pl.getPassword());u.setNome(pl.getNome());
        u.setAltura(pl.getAltura());u.setGenero(pl.getGenero());u.setPeso(pl.getPeso());
        u.setIdade(pl.getIdade());
        gestao_treinadores.registerTreinador(u);

        RespostaRegister ret = new RespostaRegister();
        ret.setSucess(true);

        return ResponseEntity.ok().body(ret);
    }
    

}
