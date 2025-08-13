package com.example.waterpipefix;

public class EmpInfo {
    String employeeID;
    String password;
    int employee_lat;
    int employee_long;
    String employee_Address;

    public String getEmployee_Address() {
        return employee_Address;
    }

    public void setEmployee_Address(String employee_Address) {
        this.employee_Address = employee_Address;
    }

    public EmpInfo(String employeeID, String password) {
        this.employeeID = employeeID;
        this.password = password;

    }

    public EmpInfo() {
    }

    public int getEmployee_lat() {
        return employee_lat;
    }

    public void setEmployee_lat(int employee_lat) {
        this.employee_lat = employee_lat;
    }

    public int getEmployee_long() {
        return employee_long;
    }

    public void setEmployee_long(int employee_long) {
        this.employee_long = employee_long;
    }
}
