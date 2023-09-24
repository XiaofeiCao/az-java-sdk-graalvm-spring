package com.example.demo;

import com.azure.core.management.AzureEnvironment;
import com.azure.core.management.profile.AzureProfile;
import com.azure.identity.DefaultAzureCredentialBuilder;
import com.azure.resourcemanager.AzureResourceManager;
import com.azure.resourcemanager.compute.models.VirtualMachine;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;

import java.util.function.Consumer;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@EventListener(ApplicationStartedEvent.class)
	public void listVMs() {
		AzureResourceManager azure = AzureResourceManager
				.authenticate(new DefaultAzureCredentialBuilder().build(), new AzureProfile(AzureEnvironment.AZURE))
				.withDefaultSubscription();
		azure.virtualMachines().list().forEach(new Consumer<VirtualMachine>() {
			@Override
			public void accept(VirtualMachine virtualMachine) {
			}
		});
	}
}
