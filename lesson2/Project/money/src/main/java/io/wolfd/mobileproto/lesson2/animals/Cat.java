package io.wolfd.mobileproto.lesson2.animals;

import java.util.Random;

public class Cat extends Animal {
    public Cat(String name, String color) {
        this.setName(name);
        this.setColor(color);

        this.setLegs(4);
        this.setSpecies("Cat");
        this.setWeight(new Random().nextDouble() * 25);
    }

    @Override
    public void grow() {
        this.setWeight(this.getWeight() * 3);
    }
}
