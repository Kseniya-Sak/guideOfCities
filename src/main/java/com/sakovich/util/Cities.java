package com.sakovich.util;

import com.sakovich.model.City;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public final class Cities {

    private Cities() {}

    // Получение списка городов из файла
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

    // Получение города из строки
    // В случае отсутствия даты основания или первого упоминания foundation будет равен пустой строке
    public static City parse(String infoOfCity) {
        String[] arrayOfCities = infoOfCity.split(";", 6);

        return new City(arrayOfCities[1], arrayOfCities[2], arrayOfCities[3],
                Integer.parseInt(arrayOfCities[4]), arrayOfCities[5]);
    }

    public static void print(List<City> cityList) {
        cityList.forEach(System.out::println);
    }

    public static void sortCityListByNameOfCity(List<City> cityList) {
        cityList.sort((city1, city2) -> city1.getName().compareToIgnoreCase(city2.getName()));
    }

    public static void sortCityListByDistrictAndNameOfCity(List<City> cityList) {
        cityList.sort(Comparator.comparing(City::getDistrict).thenComparing(City::getName));
    }

    public static void findCityWithMaxPopulationUsingStream(List<City> cityList) {
        cityList.stream()
                .max(Comparator.comparing(City::getPopulation))
                .ifPresentOrElse(city -> System.out.printf("%s=%,d\n", city.getName(), city.getPopulation()),
                        () -> System.out.println("Список городов пуст"));
    }

    public static void findCityWithMaxPopulationUsingArray(List<City> cityList) {
        City[] cityArray = cityList.toArray(new City[] {});
        int maxPopulation = cityArray[0].getPopulation();
        int indexOfCity = 0;

        for (int i = 0; i < cityArray.length; i++) {
            int population = cityArray[i].getPopulation();
            if (population > maxPopulation) {
                maxPopulation = population;
                indexOfCity = i;
            }
        }
        System.out.printf("[%d]=%,d\n", indexOfCity, maxPopulation);
    }

    public static void findCountCitiesOfRegion(List<City> cityList) {
        cityList.stream()
                .collect(Collectors.groupingBy(City::getRegion, Collectors.counting()))
                .forEach((key, value) -> System.out.printf("%s %s - %d\n",'\u2022', key, value));
    }
}
