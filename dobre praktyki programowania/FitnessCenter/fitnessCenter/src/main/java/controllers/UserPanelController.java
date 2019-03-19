package controllers;

import views.UserWindow;
import com.jasiczek.fitnessCenter.App;

import models.CourseModel;

public class UserPanelController
{
	private CourseModel[][] courseModels = new CourseModel[7][7];
	private UserWindow userView;
	
	private App app;
	
	/**
	 * Constructor
	 * @param app
	 */
	public UserPanelController(App app)
	{
		this.app = app;
		userView = new UserWindow(this);
	}

	public void backToMainMenu()
	{
		app.switchWindow(App.LOGIN,null);
	}
	
	public void closeWindows()
	{
		userView.closeWindows();
	}
	
	public void chooseCourse(CourseModel course)
	{
		int i= course.getCourseHour();
		int j = course.getCourseDay();
		
		courseModels[i][j] = course;
		update();
	}
	public CourseModel[][] getCourses()
	{
		return courseModels;
	}
	
	public void addCourse(CourseModel course)
	{

		int i= course.getCourseHour();
		int j = course.getCourseDay();
		
		courseModels[i][j] = course;
		update();
	}
	
	public void update()
	{
		userView.updateVisuals();
	}
	
}
