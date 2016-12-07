package com.example.nvdovin.weatherapp.domain.utils.temperature;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class TemperatureConverterTest {


    @Test
    public void fromId() throws Exception {
        assertEquals(TemperatureConverter.CELSIUS, TemperatureConverter.fromId(0));
        assertEquals(TemperatureConverter.FAHRENHEIT, TemperatureConverter.fromId(1));
        assertEquals(TemperatureConverter.KELVIN, TemperatureConverter.fromId(2));
        assertEquals(null, TemperatureConverter.fromId(3));
    }

    @Test
    public void getConvertorId() throws Exception {
        assertEquals(0, TemperatureConverter.CELSIUS.getConvertorId());
        assertEquals(1, TemperatureConverter.FAHRENHEIT.getConvertorId());
        assertEquals(2, TemperatureConverter.KELVIN.getConvertorId());
    }

}