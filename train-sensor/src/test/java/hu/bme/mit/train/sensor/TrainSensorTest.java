package hu.bme.mit.train.sensor;

import hu.bme.mit.train.interfaces.TrainController;
import hu.bme.mit.train.interfaces.TrainSensor;
import hu.bme.mit.train.interfaces.TrainUser;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.mockito.Mockito.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

public class TrainSensorTest {

    TrainController trainController;
    TrainUser trainUser;
    TrainSensorImpl trainSensor;

    @Before
    public void before() {
        trainController = mock(TrainController.class);
        trainUser = mock(TrainUser.class);
        trainSensor = new TrainSensorImpl(trainController, trainUser);
    }

    @Test
    public void BelowAbsoluteMargin() {
        trainSensor.overrideSpeedLimit(-1);
        verify(trainUser, times(1)).setAlarmState(true);
    }

    @Test
    public void AboveAbsoluteMargin() {
        trainSensor.overrideSpeedLimit(501);
        verify(trainUser, times(1)).setAlarmState(true);
    }

    @Test
    public void CorrectSpeedMargin() {
        trainSensor.overrideSpeedLimit(1);
        verify(trainUser, times(1)).setAlarmState(false);
    }

    @Test
    public void SpeedLimitTest() {
        assertEquals(5, trainSensor.getSpeedLimit());
    }

    @Test
    public void BelowSafeSpeed() {
        when(trainController.getReferenceSpeed()).thenReturn(120);
        trainSensor.overrideSpeedLimit(50);
        verify(trainUser, times(1)).setAlarmState(true);
    }
}
