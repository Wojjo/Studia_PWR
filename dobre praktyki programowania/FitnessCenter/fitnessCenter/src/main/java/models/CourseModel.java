package models;

import javax.swing.border.EmptyBorder;

public class CourseModel
{
	/** course name*/
	private String courseName;

	private String courseType;
	private String courseLeader;
	private int coursePlace;

	private int courseDay;
	/** course time*/
	private int courseHour;

	private int  admissionFee;
	private int  membersAmount;
	private int currentMembersAmount;

	
	/**
	 * Contructor
	 */
	public CourseModel()
	{
		
	}
	
	/**
	 * courseName getter
	 * @return courseName
	 */
	public String getCourseName()
	{
		return courseName;
	}
	
	/**
	 * courseName setter
	 * @param courseName
	 */
	public void setCourseName(String courseName)
	{
		this.courseName = courseName;
	}
	public String getCourseType()
	{
		return courseType;
	}
	public void setCourseType(String courseType)

	{
		this.courseType = courseType;
	}
	
	/**
	 * courseDay getter
	 * @return courseDay
	 */
	public int getCourseDay()
	{
		return courseDay;
	}
	
	/**
	 * courseDay setter
	 * @param courseDay
	 */
	public void setCourseDay(int courseDay)
	{
		this.courseDay = courseDay;
	}
	
	/**
	 * courseHour getter
	 * @return courseHour
	 */
	public int getCourseHour()
	{
		return courseHour;
	}
	
	/**
	 * courseHour setter
	 * @param courseHour
	 */
	public void setCourseHour(int courseHour)
	{
		this.courseHour = courseHour;
	}

	public int getAdmissionFee()
	{
		return admissionFee;
	}

	public void setAdmissionFee(int admissionFee)
	{
		this.admissionFee = admissionFee;
	}

	public int getMembersAmount()
	{
		return membersAmount;
	}

	public void setMembersAmount(int membersAmount)

	{
		this.membersAmount = membersAmount;
	}

	public String getCourseLeader()
	{
		return courseLeader;
	}

	public void setCourseLeader(String courseLeader)
	{
		this.courseLeader = courseLeader;
	}

	public int getCoursePlace()
	{
		return coursePlace;
	}

	public void setCoursePlace(int coursePlace)
	{
		this.coursePlace = coursePlace;
	}

	public int getCurrentMembersAmount()
	{
		return currentMembersAmount;
	}

	public void setCurrentMembersAmount(int currentMembersAmount)
	{
		this.currentMembersAmount = currentMembersAmount;
	}
}
