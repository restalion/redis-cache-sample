package org.acme.spring.cache;

import java.time.LocalDate;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import redis.clients.jedis.Jedis;
import javax.enterprise.inject.Instance;


@ApplicationScoped
public class WeatherForecastService {

    @Inject
    Instance<Jedis> provider;

    public String getDailyForecast(LocalDate date, String city) {

        Jedis jedis = provider.get();
        String cacheResult = jedis.get(city + date.toString());
        if (cacheResult == null) {

            try {
                Thread.sleep(2000L); 
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            
            String result = date.getDayOfWeek() + " will be " + getDailyResult(date.getDayOfMonth() % 4) + " in " + city;

            jedis.set(city + date.toString(), result);

            return result;

        } else {
            return cacheResult;
        }
    }

    private String getDailyResult(int dayOfMonthModuloFour) {
        switch (dayOfMonthModuloFour) {
            case 0:
                return "sunny";
            case 1:
                return "cloudy";
            case 2:
                return "chilly";
            case 3:
                return "rainy";
            default:
                throw new IllegalArgumentException();
        }
    }
}