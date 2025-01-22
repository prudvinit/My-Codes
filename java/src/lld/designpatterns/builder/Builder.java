package src.lld.designpatterns.builder;

public interface Builder {

    Builder addCpu(String cpu);
    Builder addRamChip(String ram);
    Builder addMemory(String memory);
    Computer build();
}
