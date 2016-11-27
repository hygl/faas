package io.github.hygl;

import org.junit.Test;

import com.spotify.docker.client.DefaultDockerClient;
import com.spotify.docker.client.DockerClient;
import com.spotify.docker.client.DockerException;
import com.spotify.docker.client.LogStream;
import com.spotify.docker.client.messages.ContainerConfig;

public class DockerTest {

	@Test
	public void testDocker() throws DockerException, InterruptedException {
		final DockerClient docker = new DefaultDockerClient("unix:///var/run/docker.sock");
		docker.pull("busybox");
		final ContainerConfig containerConfig = ContainerConfig.builder().image("hello-world").build();
		
		final String id = docker.createContainer(containerConfig).id();
		System.out.println(docker.inspectContainer(id));

		docker.startContainer(id);

		
		final String[] command = {"bash", "-c", "ls"};
		final String execId = docker.execCreate(
		    id, command, DockerClient.ExecCreateParam.attachStdout(),
		    DockerClient.ExecCreateParam.attachStderr());
		final LogStream output = docker.execStart(execId);
		final String execOutput = output.readFully();

		// Kill container
		docker.killContainer(id);

		// Remove container
		docker.removeContainer(id);
		docker.close();
	}
}
