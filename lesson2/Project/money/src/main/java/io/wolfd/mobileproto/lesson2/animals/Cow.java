package io.wolfd.mobileproto.lesson2.animals;

import java.util.Random;

public class Cow extends Animal {
    public Cow(String name, String color) {
        this.setName(name);
        this.setColor(color);

        this.setLegs(4);
        this.setSpecies("Cow");
        this.setWeight((new Random().nextDouble() * 100) + 100);
    }

    @Override
    public void grow() {
        this.setWeight(this.getWeight() * 5);
    }
}
