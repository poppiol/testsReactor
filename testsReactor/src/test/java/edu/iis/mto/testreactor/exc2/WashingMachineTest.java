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






}
