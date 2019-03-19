package controllers;

import com.jasiczek.fitnessCenter.App;

import models.CourseModel;
import models.Worker;
import views.AdminWindow;
/**
 * Admin's panel controller class
 *
 */
public class AdminPanelController
{
	private CourseModel[][] courseModels = new CourseModel[7][7];
	private Worker loggedWorker;
	
	private AdminWindow adminView;
	
	private App app;
	
	public AdminPanelController(App app, Worker loggedWorker)
	{
		this.app = app;
		this.loggedWorker = loggedWorker;
		adminView = new AdminWindow(this);
	}
	
	public void update()
	{
		adminView.updateVisuals();
	}
	
	public void backToMainMenu()
	{
		app.switchWindow(App.LOGIN,null);
	}
	
	public void closeWindows()
	{
		adminView.closeWindows();
	}
	
	/**
	 * Method that enables admin to add courses
	 * @param course
	 */
	public void addCourse(CourseModel course)
	{

		int i= course.getCourseHour();
		int j = course.getCourseDay();
		
		courseModels[i][j] = course;
		
		
		
		update();
	}
	
	public void deleteCourse(int day, int hour)
	{
		courseModels[hour][day] = null;
	}
	
	public Worker getLoggedWorker()
	{
		return loggedWorker;
	}
	
	/**
	 * 
	 * @return courseModels
	 */
	public CourseModel[][] getCourses()
	{
		return courseModels;
	}
}
