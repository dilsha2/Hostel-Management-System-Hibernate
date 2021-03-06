package bo;

import bo.Custom.impl.*;

public class BOFactory {
    private static BOFactory boFactory;

    private BOFactory() {

    }

    public static BOFactory getBoFactory() {
        if (boFactory == null) {
            boFactory = new BOFactory();
        }
        return boFactory;
    }
    public enum BOTypes {
        MANAGE_ROOM,MANAGE_STUDENT,REGISTER_STUDENT,RESERVATION_DETAILS,
    }
    public SuperBO getBO(BOTypes types) {
        switch (types) {
            case MANAGE_ROOM:
                return new ManageRoomBOImpl();
            case MANAGE_STUDENT:
                return new ManageStudentBOImpl();
            case REGISTER_STUDENT:
                return new RegisterStudentBOImpl();
            case RESERVATION_DETAILS:
                return new ReservationBOImpl();
            default:
                return null;
        }
    }
}
