package entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;


@AllArgsConstructor
@Data
@Entity
public class Room {
    @Id
    private String room_id;
    private String type;
    private double key_money;
    private int qty;

    @OneToMany(mappedBy = "room",cascade = CascadeType.ALL)
    private List<RoomReservation> roomDetails;

    public Room(String room_id, String type, double key_money, int qty) {
        this.room_id = room_id;
        this.type = type;
        this.key_money = key_money;
        this.qty = qty;
    }

    public Room(String room_id) {
        this.room_id = room_id;
    }

    public Room() {

    }
}
