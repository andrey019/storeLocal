package andrey019.model.dao;

import javax.persistence.Entity;

@Entity
public class Motherboard extends Product {

    private String cpuSocket;

    private int maxRAM;

    public String getCpuSocket() {
        return cpuSocket;
    }

    public void setCpuSocket(String cpuSocket) {
        this.cpuSocket = cpuSocket;
    }

    public int getMaxRAM() {
        return maxRAM;
    }

    public void setMaxRAM(int maxRAM) {
        this.maxRAM = maxRAM;
    }

    @Override
    public String toString() {
        return "id: " + super.getId() + ", code: " + super.getCode() +
                ", cpuSocket: " + cpuSocket + ", maxRAM: " + maxRAM;
    }
}
