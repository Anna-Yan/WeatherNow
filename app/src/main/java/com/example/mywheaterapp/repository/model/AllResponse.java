package com.example.mywheaterapp.repository.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.jetbrains.annotations.Nullable;

import java.util.List;

public class AllResponse {

        @SerializedName("coord")
        @Expose
        private Coord coord;
        @SerializedName("weather")
        @Expose
        private List<Weather> weather = null;
        @SerializedName("base")
        @Expose
        private String base;
        @SerializedName("main")
        @Expose
        private Main main;
        @SerializedName("visibility")
        @Expose
        private Integer visibility;
        @SerializedName("wind")
        @Expose
        private Wind wind;
        @SerializedName("clouds")
        @Expose
        private Clouds clouds;
        @SerializedName("dt")
        @Expose
        private Integer dt;
        @SerializedName("sys")
        @Expose
        private Sys sys;
        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("cod")
        @Expose
        private Integer cod;

    public AllResponse(@Nullable Double temp, @Nullable Double tempMin, @Nullable Double tempMax, @Nullable Integer humidity, @Nullable Integer pressure, @Nullable String description) {

    }

    public Coord getCoord() {
            return coord;
        }

        public void setCoord(Coord coord) {
            this.coord = coord;
        }

        public List<Weather> getWeather() {
            return weather;
        }

        public void setWeather(List<Weather> weather) {
            this.weather = weather;
        }

        public String getBase() {
            return base;
        }

        public void setBase(String base) {
            this.base = base;
        }

        public Main getMain() {
            return main;
        }

        public void setMain(Main main) {
            this.main = main;
        }

        public Integer getVisibility() {
            return visibility;
        }

        public void setVisibility(Integer visibility) {
            this.visibility = visibility;
        }

        public Wind getWind() {
            return wind;
        }

        public void setWind(Wind wind) {
            this.wind = wind;
        }

        public Clouds getClouds() {
            return clouds;
        }

        public void setClouds(Clouds clouds) {
            this.clouds = clouds;
        }

        public Integer getDt() {
            return dt;
        }

        public void setDt(Integer dt) {
            this.dt = dt;
        }

        public Sys getSys() {
            return sys;
        }

        public void setSys(Sys sys) {
            this.sys = sys;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getCod() {
            return cod;
        }

        public void setCod(Integer cod) {
            this.cod = cod;
        }


        @Override
        public String toString() {
            return "Example{" +
                    "coord=" + coord +
                    ", weather=" + weather +
                    ", base='" + base + '\'' +
                    ", main=" + main +
                    ", visibility=" + visibility +
                    ", wind=" + wind +
                    ", clouds=" + clouds +
                    ", dt=" + dt +
                    ", sys=" + sys +
                    ", id=" + id +
                    ", name='" + name + '\'' +
                    ", cod=" + cod +
                    '}';
        }
    }


     class Clouds {

        @SerializedName("all")
        @Expose
        private Integer all;

        public Integer getAll() {
            return all;
        }

        public void setAll(Integer all) {
            this.all = all;
        }


        @Override
        public String toString() {
            return "Clouds{" +
                    "all=" + all +
                    '}';
        }
    }


    class Coord {

        @SerializedName("lon")
        @Expose
        private Double lon;
        @SerializedName("lat")
        @Expose
        private Double lat;

        public Double getLon() {
            return lon;
        }

        public void setLon(Double lon) {
            this.lon = lon;
        }

        public Double getLat() {
            return lat;
        }

        public void setLat(Double lat) {
            this.lat = lat;
        }


        @Override
        public String toString() {
            return "Coord{" +
                    "lon=" + lon +
                    ", lat=" + lat +
                    '}';
        }
    }


class Sys {

        @SerializedName("type")
        @Expose
        private Integer type;
        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("message")
        @Expose
        private Double message;
        @SerializedName("country")
        @Expose
        private String country;
        @SerializedName("sunrise")
        @Expose
        private Integer sunrise;
        @SerializedName("sunset")
        @Expose
        private Integer sunset;

        public Integer getType() {
            return type;
        }

        public void setType(Integer type) {
            this.type = type;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public Double getMessage() {
            return message;
        }

        public void setMessage(Double message) {
            this.message = message;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public Integer getSunrise() {
            return sunrise;
        }

        public void setSunrise(Integer sunrise) {
            this.sunrise = sunrise;
        }

        public Integer getSunset() {
            return sunset;
        }

        public void setSunset(Integer sunset) {
            this.sunset = sunset;
        }


        @Override
        public String toString() {
            return "Sys{" +
                    "type=" + type +
                    ", id=" + id +
                    ", message=" + message +
                    ", country='" + country + '\'' +
                    ", sunrise=" + sunrise +
                    ", sunset=" + sunset +
                    '}';
        }
    }


class Wind {

        @SerializedName("speed")
        @Expose
        private Double speed;

        public Double getSpeed() {
            return speed;
        }

        public void setSpeed(Double speed) {
            this.speed = speed;
        }


        @Override
        public String toString() {
            return "Wind{" +
                    "speed=" + speed +
                    '}';
        }
    }



