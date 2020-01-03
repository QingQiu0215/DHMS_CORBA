package OperationsApp;


/**
* OperationsApp/OperationsOperations.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from Operations.idl
* Tuesday, November 5, 2019 6:29:05 o'clock PM EST
*/

public interface OperationsOperations 
{
  void writeTxtClient (String clientID, String task, String resultStr);
  void writeTxtServerMTL (String clientID, String patientID, String appointmentType, String appointmentID, String task, String resultStr);
  void writeTxtServerQUE (String clientID, String patientID, String appointmentType, String appointmentID, String task, String resultStr);
  void writeTxtServerSHE (String clientID, String patientID, String appointmentType, String appointmentID, String task, String resultStr);
  String bookAppointment (String clientID, String patientID, String appointmentID, String appointmentType);
  String getAppointmentSchedule (String patientID);
  String cancelAppointment (String clientID, String patientID, String appointmentID, String appointmentType);
  String swapAppointment (String clientID, String patientID, String oldAppointmentID, String oldAppointmentType, String newAppointmentID, String newAppointmentType);
  String addAppointment (String appointmentID, String appointmentType, String capacity, String appointmentWeekStr);
  boolean checkAppointmentExisted (String appointmentID, String appointmentType);
  String removeAppointment (String appointmentID, String appointmentType);
  String listAppointmentAvailability (String appointmentType);
  void shutdown ();
} // interface OperationsOperations