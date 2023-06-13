package com.sakovich;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        List<City> cityList = getCityList();
        print(cityList);
        System.out.println();
        System.out.println();

        // Сортировка списка городов по их наименованию
        sortCityListByNameOfCity(cityList);
//        print(cityList);

        // Сортировка списка городов по федеральному округу и наименованию города внутри каждого федерального округа
        sortCityListByDistrictAndNameOfCity(cityList);
//        print(cityList);


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

    public static void sortCityListByNameOfCity(List<City> cityList) {
        cityList.sort((city1, city2) -> city1.getName().compareToIgnoreCase(city2.getName()));
    }

    public static void sortCityListByDistrictAndNameOfCity(List<City> cityList) {
        cityList.sort(Comparator.comparing(City::getDistrict).thenComparing(City::getName));
    }

}
