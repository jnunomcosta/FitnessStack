package ft.backend.controllers;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ft.backend.entities.Utilizador;
import ft.backend.repositories.UtilizadorDAO;
import ft.backend.requests.PedidoRegister;
import ft.backend.responses.RespostaRegister;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/api/register")
public class RegisterController {
    
    @Autowired
    UtilizadorDAO uDao;

    @PostMapping(value = "/user")
    public ResponseEntity<RespostaRegister> register_utilizador(@RequestBody PedidoRegister pl){

        Utilizador u = new Utilizador();
        u.setEmail(pl.getEmail());u.setPassword(pl.getPassword());u.setNome(pl.getNome());
        u.setAltura(pl.getAltura());u.setGenero(pl.getGenero());u.setPeso(pl.getPeso());
        uDao.save(u);

        RespostaRegister ret = new RespostaRegister();
        ret.setSucess(true);

        return ResponseEntity.ok().body(ret);
    }

}
