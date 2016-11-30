package io.github.hygl;

import java.util.Collections;
import java.util.List;

import org.junit.Test;

import com.spotify.docker.client.DefaultDockerClient;
import com.spotify.docker.client.DockerClient;
import com.spotify.docker.client.DockerClient.ListImagesFilterParam;
import com.spotify.docker.client.LogStream;
import com.spotify.docker.client.exceptions.DockerCertificateException;
import com.spotify.docker.client.exceptions.DockerException;
import com.spotify.docker.client.messages.ContainerConfig;
import com.spotify.docker.client.messages.ExecCreation;
import com.spotify.docker.client.messages.Image;

import io.github.hygl.run.util.DockerHelper;

public class DockerTest {

	private static final String BUSYBOX_LATEST = "busybox:latest";

	@Test
	public void testDocker() throws DockerException, InterruptedException, DockerCertificateException {
		final DockerClient docker = DefaultDockerClient.fromEnv().build();
		DockerHelper.findOrPullImage(docker,BUSYBOX_LATEST);
		final String[] ports = {};
		final String id = DockerHelper.startContainer(docker,BUSYBOX_LATEST,ports);
//
//		final String[] command = { "bash", "-c", "ls" };
//		final ExecCreation execId = docker.execCreate(id, command, DockerClient.ExecCreateParam.attachStdout(),
//				DockerClient.ExecCreateParam.attachStderr());
//		final LogStream output = docker.execStart(execId.id());
//		final String execOutput = output.readFully();

		// Kill container
		docker.killContainer(id);

		// Remove container
		docker.removeContainer(id);
		docker.close();
	}

	

}
