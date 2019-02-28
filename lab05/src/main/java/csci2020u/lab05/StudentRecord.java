package csci2020.lab05;

public class StudentRecord {

	private String SID;
	private double assignmentMark, midtermMark, examMark, finalMark;
	private char grade;

	public StudentRecord(String SID, double assignmentMark, double midtermMark, double examMark) {
		this.SID = SID;
		this.assignmentMark = assignmentMark;
		this.midtermMark = midtermMark;
		this.examMark = examMark;
		this.finalMark = calculateFinal();
		this.grade = calculateGrade();
	}

	public String getSID() {
		return SID;
	}

	public void setSID(String sid)
	{
		this.SID = sid;
	}

	public double getAssignmentMark() {
		return assignmentMark;
	}

	public void setAssignmentMark(double mark)
	{
		this.assignmentMark = mark;
	}

	public double getMidtermMark() {
		return midtermMark;
	}

	public void setMidtermMark(double mark)
	{
		this.midtermMark = mark;
	}

	public double getExamMark() {
		return examMark;
	}

	public void setExamMark(double mark)
	{
		this.examMark = mark;
	}

	public double getFinalMark() {
		return finalMark;
	}

	public void setFinalMark(double mark)
	{
		this.finalMark = mark;
	}

	public char getGrade() {
		return grade;
	}

	public void setGrade(char grade)
	{
		this.grade = grade;
	}

	private double calculateFinal() {
		return assignmentMark*0.2 + midtermMark*0.3 + examMark*0.5;
	}

	private char calculateGrade() {
		if(finalMark >= 80) return 'A';
		else if(finalMark >= 70) return 'B';
		else if(finalMark >= 60) return 'C';
		else if(finalMark >= 50) return 'D';
		else return 'F';
	}

}