package view.tm;

import dto.RoomReservationDTO;

import java.time.LocalDate;

public class RoomReservationTM extends RoomReservationDTO {
    private String student_id;
    private String name;
    private LocalDate date;


    public RoomReservationTM(String student_id, String name, String reservationDate) {
    }

    public RoomReservationTM(String student_id, String name, LocalDate date) {
        this.student_id = student_id;
        this.name = name;
        this.date = date;

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


    @Override
    public String toString() {
        return "reservationDetailsTM{" +
                "student_id='" + student_id + '\'' +
                ", name='" + name + '\'' +
                ", date=" + date +
                '}';
    }
}
