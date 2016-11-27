package io.github.hygl.execution;

import io.github.hygl.registry.Job;

import java.time.LocalTime;
import java.util.List;

/**
 * Created by philipp on 16.11.16.
 */
public class JobExecution {

    private Job job;
    private Long executionId;
    private List<String> params;
    private String result;
    private LocalTime start;
    private LocalTime end;
}
