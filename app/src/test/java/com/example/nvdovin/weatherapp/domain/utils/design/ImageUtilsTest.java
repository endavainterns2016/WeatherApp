package com.example.nvdovin.weatherapp.domain.utils.design;

import com.example.nvdovin.weatherapp.R;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Collection;

import static org.junit.Assert.assertEquals;


@RunWith(Parameterized.class)
public class ImageUtilsTest {

    private ImageUtils imageUtils;

    private String id;
    private Integer iconId;
    private Integer backgroundId;


    public ImageUtilsTest(String id, Integer iconId, Integer backgroundId) {
        this.id = id;
        this.iconId = iconId;
        this.backgroundId = backgroundId;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> weatherCodes() {

        Collection<Object[]> params = new ArrayList<>();
        params.add(new Object[]{"01d", R.string.weather_sunny, R.drawable.clear_sky_day});
        params.add(new Object[]{"02d", R.string.weather_cloudy, R.drawable.few_clouds_day});
        params.add(new Object[]{"03d", R.string.weather_cloudy, R.drawable.scattered_day});
        params.add(new Object[]{"04d", R.string.weather_cloudy, R.drawable.broken_clouds_day});
        params.add(new Object[]{"09d", R.string.weather_rainy, R.drawable.shower_rain_day});
        params.add(new Object[]{"10d", R.string.weather_rainy, R.drawable.rain_day});
        params.add(new Object[]{"11d", R.string.weather_thunder, R.drawable.thunderstorm_day});
        params.add(new Object[]{"13d", R.string.weather_snowy, R.drawable.snow_day});
        params.add(new Object[]{"50d", R.string.weather_foggy, R.drawable.mist_day});
        params.add(new Object[]{"01n", R.string.weather_clear_night, R.drawable.clear_sky_night});
        params.add(new Object[]{"02n", R.string.weather_cloudy, R.drawable.few_clouds_night});
        params.add(new Object[]{"03n", R.string.weather_cloudy, R.drawable.scattered_night});
        params.add(new Object[]{"04n", R.string.weather_cloudy, R.drawable.broken_clouds_night});
        params.add(new Object[]{"09n", R.string.weather_rainy, R.drawable.shower_rain_night});
        params.add(new Object[]{"10n", R.string.weather_rainy, R.drawable.rain_night});
        params.add(new Object[]{"11n", R.string.weather_thunder, R.drawable.thunderstorm_night});
        params.add(new Object[]{"13n", R.string.weather_snowy, R.drawable.snow_night});
        params.add(new Object[]{"50n", R.string.weather_foggy, R.drawable.mist_night});

        return params;
    }

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        imageUtils = new ImageUtils();
    }

    @Test
    public void testGetIconResById() {
        assertEquals(imageUtils.getIconResById(id), iconId);

    }

    @Test
    public void testGetBackgroundResById() throws Exception {
        assertEquals(imageUtils.getBackgroundResById(id), backgroundId);
    }

}