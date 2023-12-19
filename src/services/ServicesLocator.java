package services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import definitions.LogicDefinitions;
import io.github.cdimascio.dotenv.Dotenv;

public final class ServicesLocator {
	private ServicesLocator() {}
	private static AcademicSubjectServices academicSubjectServices;
	private static AcademicYearServices academicYearServices;
	private static AuthServices authServices;
	private static CauseOfDismissalServices causeOfDismissalServices;
	private static EvaluationServices evaluationServices;
	private static GroupServices groupServices;
	private static ReportServices reportServices;
	private static RoleServices roleServices;
	private static StudentServices studentServices;
	private static TypeOfEvaluationServices typeOfEvaluationServices;
	private static UserServices userServices;
	private static TSServices teachingSecretaryServices;
	
	public static TSServices getTeachingSecretaryServices() {
		if(teachingSecretaryServices==null)
			teachingSecretaryServices = new TSServices();
		return teachingSecretaryServices;
	}
	
	public static AcademicSubjectServices getAcademicSubjectServices() {
		if(academicSubjectServices==null)
			academicSubjectServices = new AcademicSubjectServices();
		return academicSubjectServices;
	}
	
	public static AcademicYearServices getAcademicYearServices() {
		if(academicYearServices==null)
			academicYearServices = new AcademicYearServices();
		return academicYearServices;
	}
	
	public static AuthServices getAuthServices() {
		if(authServices==null)
			authServices = new AuthServices();
		return authServices;
	}
	
	public static CauseOfDismissalServices getCauseOfDismissalServices() {
		if(causeOfDismissalServices==null)
			causeOfDismissalServices = new CauseOfDismissalServices();
		return causeOfDismissalServices;
	}
	
	public static EvaluationServices getEvaluationServices() {
		if(evaluationServices==null)
			evaluationServices = new EvaluationServices();
		return evaluationServices;
	}
	
	public static GroupServices getGroupServices() {
		if(groupServices==null)
			groupServices = new GroupServices();
		return groupServices;
	}
	
	public static ReportServices getReportServices() {
		if(reportServices==null)
			reportServices = new ReportServices();
		return reportServices;
	}
	
	public static RoleServices getRoleServices() {
		if(roleServices==null)
			roleServices = new RoleServices();
		return roleServices;
	}
	
	public static StudentServices getStudentServices() {
		if(studentServices==null)
			studentServices = new StudentServices();
		return studentServices;
	}
	
	public static TypeOfEvaluationServices getTypeOfEvaluationServices() {
		if(typeOfEvaluationServices==null)
			typeOfEvaluationServices = new TypeOfEvaluationServices();
		return typeOfEvaluationServices;
	}
	
	public static UserServices getUserServices() {
		if(userServices==null)
			userServices = new UserServices();
		return userServices;
	}
	
	public static Connection getConnection() throws ClassNotFoundException, SQLException {
		Dotenv dotenv = Dotenv.configure().directory("/utils/bd_data").filename(LogicDefinitions.ENV_TO_LOAD).load();
//		Dotenv dotenv = Dotenv.configure().directory("").filename(LogicDefinitions.ENV_LOAD).load();
		Class.forName("org.postgresql.Driver");
		String url = "jdbc:postgresql://" + dotenv.get("HOST") + ":" + dotenv.get("PORT") + "/" + dotenv.get("DATABASE");
		
		return DriverManager.getConnection(url, dotenv.get("USER"), dotenv.get("PASSWORD"));
	}
}
