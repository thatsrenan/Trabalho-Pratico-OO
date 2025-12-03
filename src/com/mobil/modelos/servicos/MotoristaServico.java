package com.mobil.modelos.servicos;

import com.mobil.modelos.pessoas.Motorista;
import java.util.ArrayList;

public class MotoristaServico {

    public ArrayList<Motorista> listarMotoristasDisponiveis(ArrayList<Motorista> motoristas) {
        ArrayList<Motorista> disponiveis = new ArrayList<>();
        for (Motorista motorista : motoristas) {
            if ("Disponível".equals(motorista.getStatus())) {
                disponiveis.add(motorista);
            }
        }
        return disponiveis;
    }

}
