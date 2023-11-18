package com.spring.springselenium.StepDefinitions;

import io.cucumber.java.Scenario;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.stereotype.Component;

@Component(value=BeanDefinition.SCOPE_PROTOTYPE)
public class ScenarioContext {

	protected Scenario scenario;

	public Scenario getScenario() {
		return scenario;
	}
	public void setScenario(Scenario scenario) {
		this.scenario = scenario;
	}

}
