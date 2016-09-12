package io.wolfd.mobileproto.lesson2.animals;

import java.util.Random;

public class Dog extends Animal {
    public Dog(String name, String color) {
        this.setName(name);
        this.setColor(color);

        this.setLegs(4);
        this.setSpecies("Dog");
        this.setWeight(new Random().nextDouble() * 25);
    }

    @Override
    public void grow() {
        this.setWeight(this.getWeight() * 1.5);
    }
}
