package src.lld.designpatterns.builder;

public class DesktopComputerBuilder implements Builder {

    Computer computer;

    DesktopComputerBuilder(){
        computer = new Computer();
    }

    @Override
    public Builder addCpu(String cpu) {
        System.out.println("Added cpu : "+cpu);
        computer.setCpu(cpu);
        return this;
    }

    @Override
    public Builder addRamChip(String ram) {
        System.out.println("Added RAM : "+ram);
        computer.setRam(ram);
        return this;
    }

    @Override
    public Builder addMemory(String memory) {
        System.out.println("Added Memory : "+memory);
        computer.setMemory(memory);
        return this;
    }

    @Override
    public Computer build() {
        return computer;
    }
}
