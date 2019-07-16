package com.neotys.neoload.model.readers.jmeter;

import com.neotys.neoload.model.listener.TestEventListener;
import com.neotys.neoload.model.v3.project.variable.CounterVariable;
import com.neotys.neoload.model.v3.project.variable.Variable;
import org.apache.jmeter.modifiers.CounterConfig;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.spy;

public class CounterConverterTest {

    @Before
    public void before()   {
        TestEventListener spy = spy(new TestEventListener());
        EventListenerUtils.setEventListener(spy);
    }

    @Test
    public void testApplyLocalAndCycle(){
        CounterConfig counterConfig = new CounterConfig();
        counterConfig.setIsPerUser(true);
        counterConfig.setResetOnThreadGroupIteration(true);
        counterConfig.setEnd("100");
        counterConfig.setIncrement("12");
        counterConfig.setStart("1");
        counterConfig.setVarName("Bellecour");

        List<Variable> result = new CounterConverter().apply(counterConfig,null);
        List<Variable> expected = new ArrayList<>();

        CounterVariable.Builder counterBuilder = CounterVariable.builder()
                .name(counterConfig.getVarName())
                .description(counterConfig.getComment())
                .increment(Integer.parseInt(counterConfig.getIncrementAsString()))
                .end(Integer.parseInt(counterConfig.getEndAsString()))
                .start(Integer.parseInt(counterConfig.getStartAsString()))
                .scope(Variable.Scope.LOCAL)
                .outOfValue(Variable.OutOfValue.CYCLE);
        expected.add(counterBuilder.build());
        assertEquals(result, expected);
    }

    @Test
    public void testApplyGlobalAndStop(){
    CounterConfig counterConfig = new CounterConfig();
        counterConfig.setIsPerUser(false);
        counterConfig.setResetOnThreadGroupIteration(false);
        counterConfig.setEnd("100");
        counterConfig.setIncrement("12");
        counterConfig.setStart("1");
        counterConfig.setVarName("Bellecour");

    List<Variable> result = new CounterConverter().apply(counterConfig,null);
    List<Variable> expected = new ArrayList<>();

    CounterVariable.Builder counterBuilder = CounterVariable.builder()
            .name(counterConfig.getVarName())
            .description(counterConfig.getComment())
            .increment(Integer.parseInt(counterConfig.getIncrementAsString()))
            .end(Integer.parseInt(counterConfig.getEndAsString()))
            .start(Integer.parseInt(counterConfig.getStartAsString()))
            .scope(Variable.Scope.GLOBAL)
            .outOfValue(Variable.OutOfValue.STOP);
    expected.add(counterBuilder.build());
    assertEquals(result, expected);
    }

}