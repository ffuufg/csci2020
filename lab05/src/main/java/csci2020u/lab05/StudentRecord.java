package csci2020.lab05;

public class StudentRecord {

	private String SID;
	private double assignmentMark, midtermMark, examMark;

	public StudentRecord(String SID, double assignmentMark, double midtermMark, double examMark) {
		this.SID = SID;
		this.assignmentMark = assignmentMark;
		this.midtermMark = midtermMark;
		this.examMark = examMark;
	}

	public String getSID() {
		return SID;
	}

	public double getAssignmentMark() {
		return assignmentMark;
	}

	public double getMidtermMark() {
		return midtermMark;
	}

	public double getExamMark() {
		return examMark;
	}

}