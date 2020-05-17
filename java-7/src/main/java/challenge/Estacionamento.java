package challenge;

import java.util.LinkedList;

public class Estacionamento {

    final LinkedList<Carro> parking = new LinkedList<>();

    public void estacionar(Carro car) {
        car.getMotorista().validateDriver();

        int limit = 10;
        if (carrosEstacionados() == limit) {
            for (Carro p : parking) {
                if (!p.getMotorista().isSenior()) {
                    parking.removeFirstOccurrence(p);
                    break;
                }
            }
            if (carrosEstacionados() == limit)
                throw new EstacionamentoException("Não há vagas disponíveis");
        }
        parking.add(car);
    }

    public int carrosEstacionados() {
        return parking.size();
    }

    public boolean carroEstacionado(Carro car) {
        return parking.contains(car);
    }
}
