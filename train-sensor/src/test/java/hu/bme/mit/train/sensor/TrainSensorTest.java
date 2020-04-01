package hu.bme.mit.train.sensor;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.*;

import hu.bme.mit.train.interfaces.TrainSensor;
import hu.bme.mit.train.interfaces.TrainUser;
import hu.bme.mit.train.interfaces.TrainController;

public class TrainSensorTest {

    TrainSensor sensor;
    TrainController controller;
    TrainUser user;

    @Before
    public void before() {
        user = mock(TrainUser.class);
        controller = mock(TrainController.class);
        sensor = new TrainSensorImpl(controller, user);
    }

    @Test
    public void ThisIsAnExampleTestStub() {
        Assert.assertTrue(true);
        // TODO Delete this and add test cases based on the issues
    }

    @Test
    public void AbsoluteMarginMinTest() {
        sensor.overrideSpeedLimit(-1);
        verify(user, times(1)).setAlarmState(true);
    }

    @Test
    public void AbsoluteMarginMaxTest() {
        sensor.overrideSpeedLimit(501);
        verify(user, times(1)).setAlarmState(true);
    }

    @Test
    public void AbsoluteMarginTest() {
        sensor.overrideSpeedLimit(250);
        verify(user, times(1)).setAlarmState(false);
    }

    @Test
    public void RelativeMarginTest() {
        when(controller.getReferenceSpeed()).thenReturn(150);
        sensor.overrideSpeedLimit(50);
        verify(user, times(1)).setAlarmState(true);
    }

    @Test
    public void SpeedLimit() {
        Assert.assertEquals(5, sensor.getSpeedLimit());
    }
}
