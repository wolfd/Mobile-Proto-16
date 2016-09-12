package io.wolfd.mobileproto.lesson2.animals;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Farm {
    private final List<Animal> animalList;

    public Farm() {
        this.animalList = new ArrayList<>();
    }

    public void addAnimal(Animal a) {
        this.animalList.add(a);
    }

    public ArrayList<Animal> getHeaviestAnimals() {
        ArrayList<Animal> sorted = new ArrayList<>();
        sorted.addAll(animalList);

        Collections.sort(sorted, new Comparator<Animal>() {
            @Override
            public int compare(Animal a, Animal b) {
                if (a.getWeight() < b.getWeight()) {
                    return 1;
                } else if (a.getWeight() > b.getWeight()) {
                    return -1;
                } else {
                    return 0;
                }
            }
        });

        return sorted;
    }

    public Animal getAnimal(int i) {
        return animalList.get(i);
    }

    public int averageLegs() {
        int totalLegs = 0;

        for (Animal a : animalList) {
            totalLegs += a.getLegs();
        }

        if (animalList.size() > 0) {
            return totalLegs / animalList.size();
        } else {
            return 0;
        }
    }

    public void printCatNames() {
        for (Animal a : animalList) {
            if (a instanceof Cat) {
                System.out.println(a.getName());
            }
        }
    }
}
