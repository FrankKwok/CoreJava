package com.github.frankkwok.corejava.v2ch01.collecting;

import com.github.frankkwok.corejava.util.ResourceUtils;

import java.io.IOException;
import java.util.*;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;

/**
 * @author Frank Kwok on 2017/4/19.
 */
public class DownstreamCollectors {
    public static void main(String[] args) throws IOException {
        Stream<Locale> locales = Stream.of(Locale.getAvailableLocales());
        Map<String, Set<Locale>> countryToLocaleSet = locales.collect(groupingBy(
                Locale::getCountry, toSet()));
        System.out.println("countryToLocaleSet: " + countryToLocaleSet);

        locales = Stream.of(Locale.getAvailableLocales());
        Map<String, Long> countryToLocaleCounts = locales.collect(groupingBy(
                Locale::getCountry, counting()));
        System.out.println("countryToLocaleCounts: " + countryToLocaleCounts);

        Stream<City> cities = readCities("cities.txt");
        Map<String, Integer> stateToCityPopulation = cities.collect(groupingBy(
                City::getState, summingInt(City::getPopulation)));
        System.out.println("stateToCityPopulation: " + stateToCityPopulation);

        cities = readCities("cities.txt");
        Map<String, Optional<String>> stateToLongestCityName = cities
                .collect(groupingBy(
                        City::getState,
                        mapping(City::getName,
                                maxBy(Comparator.comparing(String::length)))));

        System.out.println("stateToLongestCityName: " + stateToLongestCityName);

        locales = Stream.of(Locale.getAvailableLocales());
        Map<String, Set<String>> countryToLanguages = locales.collect(groupingBy(
                Locale::getDisplayCountry,
                mapping(Locale::getDisplayLanguage, toSet())));
        System.out.println("countryToLanguages: " + countryToLanguages);

        cities = readCities("cities.txt");
        Map<String, IntSummaryStatistics> stateToCityPopulationSummary = cities
                .collect(groupingBy(City::getState,
                        summarizingInt(City::getPopulation)));
        System.out.println(stateToCityPopulationSummary.get("NY"));

        cities = readCities("cities.txt");
        Map<String, String> stateToCityNames = cities.collect(groupingBy(
                City::getState,
                reducing("", City::getName, (s, t) -> s.length() == 0 ? t : s
                        + ", " + t)));
        System.out.println("stateToCityNames: " + stateToCityNames);

        cities = readCities("cities.txt");
        stateToCityNames = cities.collect(groupingBy(City::getState,
                mapping(City::getName, joining(", "))));
        System.out.println("stateToCityNames: " + stateToCityNames);
    }

    private static Stream<City> readCities(String fileName) throws IOException {
        return ResourceUtils.lines(fileName)
                .map(s -> s.split(","))
                .map(strings -> new City(strings[0], strings[1], Integer.parseInt(strings[2])));
    }

    private static class City {
        private String name;
        private String state;
        private int population;

        City(String name, String state, int population) {
            this.name = name;
            this.state = state;
            this.population = population;
        }

        String getName() {
            return name;
        }

        String getState() {
            return state;
        }

        int getPopulation() {
            return population;
        }
    }
}
