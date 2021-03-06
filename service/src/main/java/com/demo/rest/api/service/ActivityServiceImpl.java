package com.demo.rest.api.service;

import com.demo.rest.api.dao.ActivityDao;
import com.demo.rest.api.entity.Activity;
import com.demo.rest.api.exception.CustomEntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Optional;

@Service
public class ActivityServiceImpl implements ActivityService {

    private final ActivityDao activityDao;

    public ActivityServiceImpl(ActivityDao activityDao) {
        this.activityDao = activityDao;
    }

    @Override
    @Transactional
    public List<Activity> getActivities() {
        List<Activity> activities = activityDao.findAll();
        if (activities == null || activities.isEmpty())
            throw new CustomEntityNotFoundException("No Activities data found!");
        return activities;
    }

    @Override
    @Transactional
    public Optional<Activity> getActivity(Long id) {
        Optional<Activity> activity = activityDao.findById(id);
        if (activity.isEmpty())
            throw new CustomEntityNotFoundException("Activity with id=" + id + " not found!");
        return activity;
    }

    @Override
    @Transactional
    public void saveActivity(Activity activity) {
        activityDao.save(activity);
    }

    @Override
    @Transactional
    public void deleteActivity(Long id) {
        Optional<Activity> activity = activityDao.findById(id);
        if (activity.isEmpty())
            throw new CustomEntityNotFoundException("Activity with id=" + id + " not found!");
        activityDao.deleteById(id);
    }
}
