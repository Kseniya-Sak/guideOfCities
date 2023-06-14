package com.sakovich;

import com.sakovich.model.City;
import java.util.List;
import static com.sakovich.util.Cities.*;

public class Main {
    public static void main(String[] args) {
        // 1 Получить список городов из файла
        List<City> cityList = getCityList();
//        print(cityList);


        // 2 Сортировка списка городов по их наименованию
        sortCityListByNameOfCity(cityList);
//        print(cityList);

        // 2 Сортировка списка городов по федеральному округу и наименованию города внутри каждого федерального округа
        sortCityListByDistrictAndNameOfCity(cityList);
//        print(cityList);

        // 3 Поиск города с наибольшим количеством жителей через Stream
        findCityWithMaxPopulationUsingStream(cityList);

        // 3 Поиск города с наибольшим количеством жителей через массив по условию задачи
        findCityWithMaxPopulationUsingArray(cityList);

        // 4 Поиск количества городов в разрезе регионов
        findCountCitiesOfRegion(cityList);
    }
}
