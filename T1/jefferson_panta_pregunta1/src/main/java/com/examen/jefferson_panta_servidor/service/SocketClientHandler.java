package com.examen.jefferson_panta_servidor.service;

import com.examen.jefferson_panta_servidor.model.Alumno;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class SocketClientHandler implements Runnable {
    private final Socket client;
    public SocketClientHandler(Socket client) {
        this.client = client;
    }
    @Override
    public void run() {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
             PrintWriter out = new PrintWriter(client.getOutputStream(), true)) {

            ObjectMapper objectMapper = new ObjectMapper();
            Alumno alumno = objectMapper.readValue(in.readLine(), Alumno.class);

            String resultado;
            if (alumno.getNota() >= 13) {
                resultado = "Estimado " +alumno.getAlumno() +", con la nota obtenida de "+alumno.getNota() +", usted aha aprobado, felicitaciones.\n";
            } else {
                resultado = "Estimado " +alumno.getAlumno() +", con la nota obtenida de "+alumno.getNota() +", usted a desaprobado.\n";
            }

            out.println(resultado);
            System.out.println("Mensaje recibido: " + alumno.getNota());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                client.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
