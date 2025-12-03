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

    public void exibirInformacoesMotorista(Motorista motorista) {
        System.out.println("\n=== INFORMAÇÕES DO MOTORISTA ===");
        System.out.println("Nome: " + motorista.getNome());
        System.out.println("Status: " + motorista.getStatus());
        System.out.println("Veículo: " + motorista.getVeiculo().getMarca() + " " +
                motorista.getVeiculo().getModelo());
        System.out.println("Placa: " + motorista.getVeiculo().getPlaca());
        System.out.println("Localização: [" + motorista.getLocalizacao().getX() +
                ", " + motorista.getLocalizacao().getY() + "]");
        System.out.println("===============================\n");
    }
}
