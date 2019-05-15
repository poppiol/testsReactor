package edu.iis.mto.testreactor.exc2;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;

public class WashingMachineTest {

    private DirtDetector dirtDetector;
    private Engine engine;
    private WaterPump waterPump;
    private LaundryBatch laundryBatch;
    private ProgramConfiguration programConfiguration;
    private WashingMachine washingMachine;
    private LaundryStatus status;
    private Percentage percentage;

    @Before
    public void init() {
        dirtDetector = mock(DirtDetector.class);
        engine = mock(Engine.class);
        waterPump = mock(WaterPump.class);
        programConfiguration = programConfiguration.builder().withProgram(Program.MEDIUM).withSpin(true).build();

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

        washingMachine = new WashingMachine(dirtDetector, engine, waterPump);
        status = washingMachine.start(laundryBatch, programConfiguration);

        assertThat("should return Result.Failure", status.getResult(), is(Result.FAILURE));
    }

    @Test
    public void WashingMachineTestStartMethodWhenMaterialIsCOTTONAndWeightKgIsMoreThanMaxWeightKg() {
        laundryBatch = laundryBatch.builder().withType(Material.COTTON).withWeightKg(9).build();

        washingMachine = new WashingMachine(dirtDetector, engine, waterPump);
        status = washingMachine.start(laundryBatch, programConfiguration);

        assertThat("should return Result.Failure", status.getResult(), is(Result.FAILURE));
    }

    @Test
    public void WashingMachineTestStartMethodWhenMaterialIsWOOLAndWeightKgIsLessThanMaxWeightKg() {
        laundryBatch = laundryBatch.builder().withType(Material.WOOL).withWeightKg(2).build();

        washingMachine = new WashingMachine(dirtDetector, engine, waterPump);
        status = washingMachine.start(laundryBatch, programConfiguration);

        assertThat("should return Result.Succes", status.getResult(), is(Result.SUCCESS));
    }

    @Test
    public void WashingMachineTestStartMethodWhenMaterialIsCOTTONAndWeightKgIsLessThanMaxWeightKg() {
        laundryBatch = laundryBatch.builder().withType(Material.COTTON).withWeightKg(5).build();

        washingMachine = new WashingMachine(dirtDetector, engine, waterPump);
        status = washingMachine.start(laundryBatch, programConfiguration);

        assertThat("should return Result.Succes", status.getResult(), is(Result.SUCCESS));
    }

    @Test(expected = WeightKgIsNegativeException.class)
    public void WashingMachineTestStartMethodWhenWeightKgIsNegative() {
        laundryBatch = laundryBatch.builder().withType(Material.COTTON).withWeightKg(-5).build();
    }

    @Test
    public void WashingMachineTestStartMethodShouldReturnCallLaundryBatchTwoTimes() {
        laundryBatch = mock(LaundryBatch.class);

        washingMachine = new WashingMachine(dirtDetector, engine, waterPump);
        status = washingMachine.start(laundryBatch, programConfiguration);

        verify(laundryBatch, times(2)).getType();
    }


}
