package io.github.biezhi.json.gson;

import java.lang.reflect.Type;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import io.github.biezhi.json.gson.model.Animal;
import io.github.biezhi.json.gson.model.Dog;

/**
 * 序列化、发序列化泛型类型
 *
 * @author biezhi
 * @date 2018/1/16
 */
public class SerializeGenericTypes {

    public static void main(String[] args) {
        Gson gson = new GsonBuilder().create();

        Animal<Dog> animal = new Animal<>();
        Dog         dog    = new Dog("单身狗");
        animal.setAnimal(dog);

        String json = gson.toJson(animal);
        System.out.println(json);

        Animal animal1 = gson.fromJson(json, Animal.class);
        System.out.println(animal1.getAnimal().getClass());

        Type   animalType = new TypeToken<Animal<Dog>>() {}.getType();
        Animal<Dog> animal2    = gson.fromJson(json, animalType);
        System.out.println(animal2.getAnimal().getClass());
    }
}
