package com.sakovich;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        List<City> cityList = getCityList();

        print(cityList);
    }

    public static List<City> getCityList() {
        List<City> cityList = new ArrayList<>();
        Path path = Path.of("Задача ВС Java Сбер.csv");

        try (Scanner scanner = new Scanner(path)) {
            while (scanner.hasNextLine()) {
                cityList.add(parse(scanner.nextLine()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return cityList;
    }

    //в случае отсутствия даты основания или первого упоминания foundation будет равен пустой строке
    public static City parse(String infoOfCity) {
        String[] arrayOfCities = infoOfCity.split(";", 6);

        return new City(arrayOfCities[1], arrayOfCities[2], arrayOfCities[3],
                Integer.parseInt(arrayOfCities[4]), arrayOfCities[5]);
    }

    public static void print(List<City> cityList) {
        cityList.forEach(System.out::println);
    }
}
