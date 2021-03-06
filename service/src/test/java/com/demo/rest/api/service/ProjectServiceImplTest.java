package com.demo.rest.api.service;

import com.demo.rest.api.dao.ProjectDao;
import com.demo.rest.api.entity.Project;
import com.demo.rest.api.exception.CustomEntityNotFoundException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

class ProjectServiceImplTest {

    private static final int ONCE = 1;
    private static final long TEST_PROJECT_ID = 1L;
    private static Project TEST_PROJECT;
    private static List<Project> TEST_PROJECTS;

    @Mock
    private ProjectDao projectDao;

    @InjectMocks
    private ProjectServiceImpl projectService;

    @BeforeAll
    public static void setupProjects() {
        TEST_PROJECT = new Project();
        TEST_PROJECT.setId(TEST_PROJECT_ID);
        TEST_PROJECTS = new ArrayList<>();
        TEST_PROJECTS.add(TEST_PROJECT);
    }

    @BeforeEach
    public void setup() {
        initMocks(this);
    }

    @Test
    public void shouldReturnProjects() {
        when(projectDao.findAll()).thenReturn(TEST_PROJECTS);
        List<Project> projects = projectService.getProjects();
        assertNotNull(projects);
        assertEquals(TEST_PROJECTS.size(), projects.size());
        verify(projectDao, times(ONCE)).findAll();
    }

    @Test
    public void projectsIsEmpty_shouldThrowCustomEntityNotFoundException() {
        when(projectDao.findAll())
                .thenReturn(Collections.emptyList())
                .thenThrow(CustomEntityNotFoundException.class);

        assertThrows(CustomEntityNotFoundException.class, () -> projectService.getProjects());
    }

    @Test
    public void projectsIsNull_shouldThrowCustomEntityNotFoundException() {
        when(projectDao.findAll())
                .thenReturn(null)
                .thenThrow(CustomEntityNotFoundException.class);

        assertThrows(CustomEntityNotFoundException.class, () -> projectService.getProjects());
    }

    @Test
    public void shouldReturnProject() {
        when(projectDao.findById(TEST_PROJECT_ID)).thenReturn(Optional.of(TEST_PROJECT));
        Optional<Project> project = projectService.getProject(TEST_PROJECT_ID);
        assertNotNull(project);
        verify(projectDao, times(ONCE)).findById(TEST_PROJECT_ID);
    }

    @Test
    public void projectIsEmpty_shouldThrowCustomEntityNotFoundException_forGetProject() {
        when(projectDao.findById(TEST_PROJECT_ID))
                .thenReturn(Optional.empty())
                .thenThrow(CustomEntityNotFoundException.class);

        assertThrows(CustomEntityNotFoundException.class,
                () -> projectService.getProject(TEST_PROJECT_ID));
    }

    @Test
    public void shouldSaveProject() {
        doNothing().when(projectDao).save(TEST_PROJECT);
        projectService.saveProject(TEST_PROJECT);
        verify(projectDao, times(ONCE)).save(TEST_PROJECT);
    }

    @Test
    public void shouldDeleteProject() {
        shouldReturnProject();
        doNothing().when(projectDao).deleteById(TEST_PROJECT_ID);
        projectService.deleteProject(TEST_PROJECT_ID);
        verify(projectDao, times(ONCE)).deleteById(TEST_PROJECT_ID);
    }

    @Test
    public void projectIsEmpty_shouldThrowCustomEntityNotFoundException_forDeleteProject() {
        when(projectDao.findById(TEST_PROJECT_ID))
                .thenReturn(Optional.empty())
                .thenThrow(CustomEntityNotFoundException.class);

        assertThrows(CustomEntityNotFoundException.class,
                () -> projectService.deleteProject(TEST_PROJECT_ID));
    }
}