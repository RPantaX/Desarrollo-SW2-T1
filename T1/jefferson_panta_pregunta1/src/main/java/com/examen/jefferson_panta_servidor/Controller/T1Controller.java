package com.examen.jefferson_panta_servidor.Controller;

import com.examen.jefferson_panta_servidor.model.Alumno;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

@RestController
@RequestMapping("/api/v1/t1")
public class T1Controller {
    @PostMapping("/pregunta1")
    public ResponseEntity<String> pregunta1(@RequestBody Alumno alumno){
        String host = "localhost";
        int port = 5566;
        try(Socket socket = new Socket(host, port)){
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            ObjectMapper objectMapper = new ObjectMapper();
            String alumnoJson = objectMapper.writeValueAsString(alumno);
            out.println(alumnoJson);
            String response = in.readLine();
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Error al conectar al servidor");
        }
    }
}
