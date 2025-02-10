package src.lld.designpatterns.builder;

public class Main {
    public static void main(String[] args) {
        Builder builder = new DesktopComputerBuilder();
        Computer computer = builder.addCpu("Intel")
                .addMemory("256SSD")
                .addRamChip("16GB")
                .build();
        System.out.println(computer);
    }
}
