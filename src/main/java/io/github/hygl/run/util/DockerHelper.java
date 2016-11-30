/**
 * 
 */
package io.github.hygl.run.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.spotify.docker.client.DockerClient;
import com.spotify.docker.client.DockerClient.ListContainersFilterParam;
import com.spotify.docker.client.DockerClient.ListImagesFilterParam;
import com.spotify.docker.client.exceptions.DockerException;
import com.spotify.docker.client.messages.Container;
import com.spotify.docker.client.messages.ContainerConfig;
import com.spotify.docker.client.messages.ContainerConfig.Builder;
import com.spotify.docker.client.messages.HostConfig;
import com.spotify.docker.client.messages.Image;
import com.spotify.docker.client.messages.PortBinding;

/**
 * @author philipp
 *
 */
public final class DockerHelper {

	public static void findOrPullImage(final DockerClient docker,String imageTag) throws DockerException, InterruptedException {
		List<Image> listImages = docker.listImages(ListImagesFilterParam.allImages());
		boolean foundImage = false;
		for(Image image : listImages){
			if(image.repoTags().contains(imageTag)){
				foundImage = true;
				continue;
			}
		}
		if (!foundImage) {
			docker.pull(imageTag);
		}
	}

	
	public static String startContainer(final DockerClient docker, String image, String[] ports) throws DockerException, InterruptedException {
		List<Container> listContainers = docker.listContainers(ListContainersFilterParam.allContainers());
		for (Container container: listContainers){
			if(container.image().equals(image) && container.state().equals("running")){
				return container.imageId();
			}
				
		}
		final Map<String, List<PortBinding>> portBindings = new HashMap<String, List<PortBinding>>();
		for (String port : ports) {
		    List<PortBinding> hostPorts = new ArrayList<PortBinding>();
		    hostPorts.add(PortBinding.of("0.0.0.0", port));
		    portBindings.put(port, hostPorts);
		}

		
		final HostConfig hostConfig = HostConfig.builder().portBindings(portBindings).build();
		
		final ContainerConfig containerConfig = ContainerConfig.builder()
			    .hostConfig(hostConfig)
			    .image(image).exposedPorts(ports)
			    .build();

		final String id = docker.createContainer(containerConfig).id();
		System.out.println(docker.inspectContainer(id));

		docker.startContainer(id);
		return id;
	}
	
	public static void StopContainer(final DockerClient docker, final String id) throws DockerException, InterruptedException{
		// Kill container
		docker.killContainer(id);

		// Remove container
		docker.removeContainer(id);
	}

}
