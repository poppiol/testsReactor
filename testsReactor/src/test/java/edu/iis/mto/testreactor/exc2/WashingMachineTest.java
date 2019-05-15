package edu.iis.mto.testreactor.exc2;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class WashingMachineTest {

    DirtDetector dirtDetector;
    Engine engine;
    WaterPump waterPump;
    LaundryBatch laundryBatch;
    ProgramConfiguration programConfiguration;
    WashingMachine washingMachine;
    LaundryStatus status;

    @Before
    public void init() {
        dirtDetector = mock(DirtDetector.class);
        engine = mock(Engine.class);
        waterPump = mock(WaterPump.class);
    }

    @Test
    public void itCompiles() {
        assertThat(true, Matchers.equalTo(true));
    }

    @Test(expected = NullPointerException.class)
    public void WashingMachineTestConstructorWhereAllArgumentsAreNull() {
        new WashingMachine(null, null, null);
    }

    @Test(expected = NullPointerException.class)
    public void WashingMachineTestConstructorWhereDirtDetectorIsNull() {
        new WashingMachine(null, engine, waterPump);
    }

    @Test(expected = NullPointerException.class)
    public void WashingMachineTestConstructorWhereEngineIsNull() {
        new WashingMachine(dirtDetector, null, waterPump);
    }

    @Test(expected = NullPointerException.class)
    public void WashingMachineTestConstructorWhereWaterPumpIsNull() {
        new WashingMachine(dirtDetector, engine, null);
    }

    @Test
    public void WashingMachineTestStartMethodWhenMaterialIsWOOLAndWeightKgIsMoreThanMaxWeightKg() {
        laundryBatch = laundryBatch.builder().withType(Material.WOOL).withWeightKg(5).build();
        programConfiguration = programConfiguration.builder().withProgram(Program.MEDIUM).withSpin(true).build();

        washingMachine = new WashingMachine(dirtDetector, engine, waterPump);
        status = washingMachine.start(laundryBatch, programConfiguration);

        assertThat("should return Result.Failure", status.getResult(), is(Result.FAILURE));
    }

    @Test
    public void WashingMachineTestStartMethodWhenMaterialIsCOTTONAndWeightKgIsMoreThanMaxWeightKg() {
        laundryBatch = laundryBatch.builder().withType(Material.COTTON).withWeightKg(9).build();
        programConfiguration = programConfiguration.builder().withProgram(Program.MEDIUM).withSpin(true).build();

        washingMachine = new WashingMachine(dirtDetector, engine, waterPump);
        status = washingMachine.start(laundryBatch, programConfiguration);

        assertThat("should return Result.Failure", status.getResult(), is(Result.FAILURE));
    }

    @Test
    public void WashingMachineTestStartMethodWhenMaterialIsWOOLAndWeightKgIsLessThanMaxWeightKg() {
        laundryBatch = laundryBatch.builder().withType(Material.WOOL).withWeightKg(2).build();
        programConfiguration = programConfiguration.builder().withProgram(Program.MEDIUM).withSpin(true).build();

        washingMachine = new WashingMachine(dirtDetector, engine, waterPump);
        status = washingMachine.start(laundryBatch, programConfiguration);

        assertThat("should return Result.Succes", status.getResult(), is(Result.SUCCESS));
    }

    @Test
    public void WashingMachineTestStartMethodWhenMaterialIsCOTTONAndWeightKgIsLessThanMaxWeightKg() {
        laundryBatch = laundryBatch.builder().withType(Material.COTTON).withWeightKg(5).build();
        programConfiguration = programConfiguration.builder().withProgram(Program.MEDIUM).withSpin(true).build();

        washingMachine = new WashingMachine(dirtDetector, engine, waterPump);
        status = washingMachine.start(laundryBatch, programConfiguration);

        assertThat("should return Result.Succes", status.getResult(), is(Result.SUCCESS));
    }
}
