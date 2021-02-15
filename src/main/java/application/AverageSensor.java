/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author alex.bujenita
 */
public class AverageSensor implements Sensor {

    private final ArrayList<Sensor> sensors;
    private final ArrayList<Integer> readings;

    public AverageSensor() {
        this.sensors = new ArrayList<>();
        this.readings = new ArrayList<>();

    }

    public void addSensor(Sensor toAdd) {
        this.sensors.add(toAdd);
    }

    public List<Integer> readings() {
        return new ArrayList<>(this.readings);
    }

    @Override
    public boolean isOn() {
        return this.sensors.stream().allMatch(s -> s.isOn());
    }

    @Override
    public void setOn() {
        this.sensors.stream().forEach(s -> s.setOn());
    }

    @Override
    public void setOff() {
        this.sensors.stream().forEach(s -> s.setOff());
    }

    @Override
    public int read() {
        if (!this.isOn() || this.sensors.isEmpty()) {
            throw new IllegalStateException();
        }

        int reading = (int) this.sensors.stream().mapToInt(s -> s.read()).average().getAsDouble();
        this.readings.add(reading);
        return reading;
    }

}
