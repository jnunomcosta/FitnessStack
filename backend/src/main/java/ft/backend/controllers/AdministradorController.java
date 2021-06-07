package ft.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ft.backend.beans.gestao_administradores;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/api/admin")
public class AdministradorController {

    @Autowired
    gestao_administradores ga;

    @GetMapping(value = "/getAdminInfo")
    public ResponseEntity<String> getAdminInfo(@RequestHeader String token){

        //retirar o username do token

        return null;
    }
}
