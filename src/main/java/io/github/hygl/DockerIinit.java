package io.github.hygl;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.spotify.docker.client.DefaultDockerClient;
import com.spotify.docker.client.DockerClient;

import io.github.hygl.run.util.DockerHelper;
@Component
public class DockerIinit implements CommandLineRunner {
	
	private final String REDIS="redis:3.2.5-alpine";
	@Override
	public void run(String... args) throws Exception {
		final DockerClient docker = DefaultDockerClient.fromEnv().build();
		
		DockerHelper.findOrPullImage(docker, REDIS);
		final String[] ports = {"6379"};
		String id = DockerHelper.startContainer(docker, REDIS,ports);
	}

}
