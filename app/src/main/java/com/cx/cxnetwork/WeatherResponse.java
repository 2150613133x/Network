package com.cx.cxnetwork;

import java.util.List;

/**
 * Created by ly-chenxiao on 25/10/2021
 * Email: chenxiao@szlanyou.com
 * Description:
 *
 * @author: chenxiao
 */
public class WeatherResponse {


    private DataBean data;
    private String respTime;
    private String statusMessage;
    private String statusCode;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getRespTime() {
        return respTime;
    }

    public void setRespTime(String respTime) {
        this.respTime = respTime;
    }

    public String getStatusMessage() {
        return statusMessage;
    }

    public void setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public static class DataBean {
        private AlarmBean alarm;
        private List<?> alarmList;
        private List<ForecastBean> forecast;
        private WeatherAirQualityBean weatherAirQuality;
        private LiveBean live;

        public AlarmBean getAlarm() {
            return alarm;
        }

        public void setAlarm(AlarmBean alarm) {
            this.alarm = alarm;
        }

        public List<?> getAlarmList() {
            return alarmList;
        }

        public void setAlarmList(List<?> alarmList) {
            this.alarmList = alarmList;
        }

        public List<ForecastBean> getForecast() {
            return forecast;
        }

        public void setForecast(List<ForecastBean> forecast) {
            this.forecast = forecast;
        }

        public WeatherAirQualityBean getWeatherAirQuality() {
            return weatherAirQuality;
        }

        public void setWeatherAirQuality(WeatherAirQualityBean weatherAirQuality) {
            this.weatherAirQuality = weatherAirQuality;
        }

        public LiveBean getLive() {
            return live;
        }

        public void setLive(LiveBean live) {
            this.live = live;
        }

        public static class AlarmBean {
            private String admin_code;
            private String alarm_level_en;
            private String alarm_type;
            private String alarm_content;
            private String publish_time;
            private String alarm_level;
            private String alarm_level_code;
            private String alarm_type_en;

            public String getAdmin_code() {
                return admin_code;
            }

            public void setAdmin_code(String admin_code) {
                this.admin_code = admin_code;
            }

            public String getAlarm_level_en() {
                return alarm_level_en;
            }

            public void setAlarm_level_en(String alarm_level_en) {
                this.alarm_level_en = alarm_level_en;
            }

            public String getAlarm_type() {
                return alarm_type;
            }

            public void setAlarm_type(String alarm_type) {
                this.alarm_type = alarm_type;
            }

            public String getAlarm_content() {
                return alarm_content;
            }

            public void setAlarm_content(String alarm_content) {
                this.alarm_content = alarm_content;
            }

            public String getPublish_time() {
                return publish_time;
            }

            public void setPublish_time(String publish_time) {
                this.publish_time = publish_time;
            }

            public String getAlarm_level() {
                return alarm_level;
            }

            public void setAlarm_level(String alarm_level) {
                this.alarm_level = alarm_level;
            }

            public String getAlarm_level_code() {
                return alarm_level_code;
            }

            public void setAlarm_level_code(String alarm_level_code) {
                this.alarm_level_code = alarm_level_code;
            }

            public String getAlarm_type_en() {
                return alarm_type_en;
            }

            public void setAlarm_type_en(String alarm_type_en) {
                this.alarm_type_en = alarm_type_en;
            }
        }

        public static class WeatherAirQualityBean {
            private String aqi_value;
            private String adcode;
            private String level;
            private String o38h;
            private String o31h;
            private String pm10;
            private String maincause;
            private String co;
            private String no2;
            private String condition;
            private String adname;
            private String pm25;
            private String so2;
            private String aqi;
            private String pm25_grade;

            public String getAqi_value() {
                return aqi_value;
            }

            public void setAqi_value(String aqi_value) {
                this.aqi_value = aqi_value;
            }

            public String getAdcode() {
                return adcode;
            }

            public void setAdcode(String adcode) {
                this.adcode = adcode;
            }

            public String getLevel() {
                return level;
            }

            public void setLevel(String level) {
                this.level = level;
            }

            public String getO38h() {
                return o38h;
            }

            public void setO38h(String o38h) {
                this.o38h = o38h;
            }

            public String getO31h() {
                return o31h;
            }

            public void setO31h(String o31h) {
                this.o31h = o31h;
            }

            public String getPm10() {
                return pm10;
            }

            public void setPm10(String pm10) {
                this.pm10 = pm10;
            }

            public String getMaincause() {
                return maincause;
            }

            public void setMaincause(String maincause) {
                this.maincause = maincause;
            }

            public String getCo() {
                return co;
            }

            public void setCo(String co) {
                this.co = co;
            }

            public String getNo2() {
                return no2;
            }

            public void setNo2(String no2) {
                this.no2 = no2;
            }

            public String getCondition() {
                return condition;
            }

            public void setCondition(String condition) {
                this.condition = condition;
            }

            public String getAdname() {
                return adname;
            }

            public void setAdname(String adname) {
                this.adname = adname;
            }

            public String getPm25() {
                return pm25;
            }

            public void setPm25(String pm25) {
                this.pm25 = pm25;
            }

            public String getSo2() {
                return so2;
            }

            public void setSo2(String so2) {
                this.so2 = so2;
            }

            public String getAqi() {
                return aqi;
            }

            public void setAqi(String aqi) {
                this.aqi = aqi;
            }

            public String getPm25_grade() {
                return pm25_grade;
            }

            public void setPm25_grade(String pm25_grade) {
                this.pm25_grade = pm25_grade;
            }
        }

        public static class LiveBean {
            private String sun_set;
            private String visibility;
            private String adcode;
            private String real_feel;
            private String wind_dirangle;
            private String uvi;
            private String wind_direction;
            private String pressure;
            private String tips;
            private String wind_power;
            private String weather_EN;
            private String update_time;
            private String weather_CN;
            private String adname;
            private String temperature;
            private String weather;
            private String humidity;
            private String sun_rise;
            private String wind_speed;

            public String getSun_set() {
                return sun_set;
            }

            public void setSun_set(String sun_set) {
                this.sun_set = sun_set;
            }

            public String getVisibility() {
                return visibility;
            }

            public void setVisibility(String visibility) {
                this.visibility = visibility;
            }

            public String getAdcode() {
                return adcode;
            }

            public void setAdcode(String adcode) {
                this.adcode = adcode;
            }

            public String getReal_feel() {
                return real_feel;
            }

            public void setReal_feel(String real_feel) {
                this.real_feel = real_feel;
            }

            public String getWind_dirangle() {
                return wind_dirangle;
            }

            public void setWind_dirangle(String wind_dirangle) {
                this.wind_dirangle = wind_dirangle;
            }

            public String getUvi() {
                return uvi;
            }

            public void setUvi(String uvi) {
                this.uvi = uvi;
            }

            public String getWind_direction() {
                return wind_direction;
            }

            public void setWind_direction(String wind_direction) {
                this.wind_direction = wind_direction;
            }

            public String getPressure() {
                return pressure;
            }

            public void setPressure(String pressure) {
                this.pressure = pressure;
            }

            public String getTips() {
                return tips;
            }

            public void setTips(String tips) {
                this.tips = tips;
            }

            public String getWind_power() {
                return wind_power;
            }

            public void setWind_power(String wind_power) {
                this.wind_power = wind_power;
            }

            public String getWeather_EN() {
                return weather_EN;
            }

            public void setWeather_EN(String weather_EN) {
                this.weather_EN = weather_EN;
            }

            public String getUpdate_time() {
                return update_time;
            }

            public void setUpdate_time(String update_time) {
                this.update_time = update_time;
            }

            public String getWeather_CN() {
                return weather_CN;
            }

            public void setWeather_CN(String weather_CN) {
                this.weather_CN = weather_CN;
            }

            public String getAdname() {
                return adname;
            }

            public void setAdname(String adname) {
                this.adname = adname;
            }

            public String getTemperature() {
                return temperature;
            }

            public void setTemperature(String temperature) {
                this.temperature = temperature;
            }

            public String getWeather() {
                return weather;
            }

            public void setWeather(String weather) {
                this.weather = weather;
            }

            public String getHumidity() {
                return humidity;
            }

            public void setHumidity(String humidity) {
                this.humidity = humidity;
            }

            public String getSun_rise() {
                return sun_rise;
            }

            public void setSun_rise(String sun_rise) {
                this.sun_rise = sun_rise;
            }

            public String getWind_speed() {
                return wind_speed;
            }

            public void setWind_speed(String wind_speed) {
                this.wind_speed = wind_speed;
            }
        }

        public static class ForecastBean {
            private String moonset;
            private String night_wind_direct;
            private String sunrise;
            private String day_weatherCN;
            private String description;
            private String day_temp;
            private String day_wind_direct;
            private String moonrise;
            private String night_weather;
            private String wind_speed_day;
            private String day_weather;
            private String day_weatherEN;
            private String day_wind_power;
            private String predict_date;
            private String moonphase;
            private String night_weatherEN;
            private String night_wind_power;
            private String sunset;
            private String humidity;
            private String night_weatherCN;
            private String wind_speed_night;
            private String night_temp;

            public String getMoonset() {
                return moonset;
            }

            public void setMoonset(String moonset) {
                this.moonset = moonset;
            }

            public String getNight_wind_direct() {
                return night_wind_direct;
            }

            public void setNight_wind_direct(String night_wind_direct) {
                this.night_wind_direct = night_wind_direct;
            }

            public String getSunrise() {
                return sunrise;
            }

            public void setSunrise(String sunrise) {
                this.sunrise = sunrise;
            }

            public String getDay_weatherCN() {
                return day_weatherCN;
            }

            public void setDay_weatherCN(String day_weatherCN) {
                this.day_weatherCN = day_weatherCN;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public String getDay_temp() {
                return day_temp;
            }

            public void setDay_temp(String day_temp) {
                this.day_temp = day_temp;
            }

            public String getDay_wind_direct() {
                return day_wind_direct;
            }

            public void setDay_wind_direct(String day_wind_direct) {
                this.day_wind_direct = day_wind_direct;
            }

            public String getMoonrise() {
                return moonrise;
            }

            public void setMoonrise(String moonrise) {
                this.moonrise = moonrise;
            }

            public String getNight_weather() {
                return night_weather;
            }

            public void setNight_weather(String night_weather) {
                this.night_weather = night_weather;
            }

            public String getWind_speed_day() {
                return wind_speed_day;
            }

            public void setWind_speed_day(String wind_speed_day) {
                this.wind_speed_day = wind_speed_day;
            }

            public String getDay_weather() {
                return day_weather;
            }

            public void setDay_weather(String day_weather) {
                this.day_weather = day_weather;
            }

            public String getDay_weatherEN() {
                return day_weatherEN;
            }

            public void setDay_weatherEN(String day_weatherEN) {
                this.day_weatherEN = day_weatherEN;
            }

            public String getDay_wind_power() {
                return day_wind_power;
            }

            public void setDay_wind_power(String day_wind_power) {
                this.day_wind_power = day_wind_power;
            }

            public String getPredict_date() {
                return predict_date;
            }

            public void setPredict_date(String predict_date) {
                this.predict_date = predict_date;
            }

            public String getMoonphase() {
                return moonphase;
            }

            public void setMoonphase(String moonphase) {
                this.moonphase = moonphase;
            }

            public String getNight_weatherEN() {
                return night_weatherEN;
            }

            public void setNight_weatherEN(String night_weatherEN) {
                this.night_weatherEN = night_weatherEN;
            }

            public String getNight_wind_power() {
                return night_wind_power;
            }

            public void setNight_wind_power(String night_wind_power) {
                this.night_wind_power = night_wind_power;
            }

            public String getSunset() {
                return sunset;
            }

            public void setSunset(String sunset) {
                this.sunset = sunset;
            }

            public String getHumidity() {
                return humidity;
            }

            public void setHumidity(String humidity) {
                this.humidity = humidity;
            }

            public String getNight_weatherCN() {
                return night_weatherCN;
            }

            public void setNight_weatherCN(String night_weatherCN) {
                this.night_weatherCN = night_weatherCN;
            }

            public String getWind_speed_night() {
                return wind_speed_night;
            }

            public void setWind_speed_night(String wind_speed_night) {
                this.wind_speed_night = wind_speed_night;
            }

            public String getNight_temp() {
                return night_temp;
            }

            public void setNight_temp(String night_temp) {
                this.night_temp = night_temp;
            }
        }
    }
}
