package view.tm;

import dto.RoomReservationDTO;

import java.time.LocalDate;

public class RoomReservationTM extends RoomReservationDTO {
    private String student_id;
    private String name;
    private LocalDate date;
    private double keyMoney;

    public RoomReservationTM() {
    }

    public RoomReservationTM(String student_id, String name, LocalDate date, double keyMoney) {
        this.student_id = student_id;
        this.name = name;
        this.date = date;
        this.keyMoney = keyMoney;
    }

    public String getStudent_id() {
        return student_id;
    }

    public void setStudent_id(String student_id) {
        this.student_id = student_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public double getKeyMoney() {
        return keyMoney;
    }

    public void setKeyMoney(double keyMoney) {
        this.keyMoney = keyMoney;
    }

    @Override
    public String toString() {
        return "reservationDetailsTM{" +
                "student_id='" + student_id + '\'' +
                ", name='" + name + '\'' +
                ", date=" + date +
                ", keyMoney=" + keyMoney +
                '}';
    }
}
